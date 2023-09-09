package org.foi.diplomski.msakac.odmaralica.service.security.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.PrivacyRequestPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.security.PrivacyRequestMapper;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.model.security.PrivacyRequest;
import org.foi.diplomski.msakac.odmaralica.repository.PrivacyRequestRepository;
import org.foi.diplomski.msakac.odmaralica.repository.ReviewRepository;
import org.foi.diplomski.msakac.odmaralica.repository.UserRepository;
import org.foi.diplomski.msakac.odmaralica.service.security.IPrivacyRequestService;
import org.foi.diplomski.msakac.odmaralica.utils.SecurityConstants;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;

@Service
public class PrivacyRequestServiceImpl extends AbstractBaseService<PrivacyRequest, PrivacyRequestRepository, PrivacyRequestMapper, PrivacyRequestGetDTO, PrivacyRequestPostDTO, PrivacyRequestPutDTO> implements IPrivacyRequestService {
    
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public PrivacyRequestServiceImpl(PrivacyRequestRepository repository, PrivacyRequestMapper mapper, EntityManager entityManager, UserRepository userRepository, ReviewRepository reviewRepository) {
        super(repository, mapper, entityManager);
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public PrivacyRequestGetDTO create(PrivacyRequestPostDTO entityPost) {
        List<PrivacyRequest> privacyRequests = repository.findByUserId(entityPost.getUserId());
        for (PrivacyRequest privacyRequest : privacyRequests) {
            if (privacyRequest.isAccepted() && !privacyRequest.isRevoked() || !privacyRequest.isAccepted() && !privacyRequest.isRevoked()) {
                return null;
            }
        }
        entityPost.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return super.create(entityPost);
    }

    @Override
    public PrivacyRequest convertPost(PrivacyRequestPostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public PrivacyRequest convertPut(PrivacyRequestPutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public PrivacyRequestGetDTO convertGet(PrivacyRequest entity) {
        return mapper.convert(entity);
    }

    public Class<PrivacyRequest> getEntityClass() {
        return PrivacyRequest.class;
    }

    @Override
    public PrivacyRequestGetDTO update(PrivacyRequestPutDTO entityPut) {
        Long authenticatedUserId = SecurityConstants.getAuthenticatedUserId();
        String role = SecurityConstants.getAuthenticatedUserRole();
        if (authenticatedUserId != entityPut.getUserId() && !role.equals("admin")) {
            return null;
        }
        return super.update(entityPut);
    }

    public PrivacyRequestGetDTO acceptRequest(Long id) {
        // Get the request
        PrivacyRequest request = repository.findById(id).orElse(null);
        if (request == null) {
            return null;    
        }
        // Get the authenticated user
        Long authenticatedUserId = SecurityConstants.getAuthenticatedUserId();
        User acceptedBy = userRepository.findById(authenticatedUserId).orElse(null);

        // Change the user's data
        User requestedUser = request.getUser();
        requestedUser.setEmail(generateUniqueEmail());
        requestedUser.setActivated(false);
        requestedUser.setDescription("");
        requestedUser.setPhoneNumber("");
        requestedUser.setPolicyAccepted(false);
        requestedUser.setName("Deactivated");
        requestedUser.setSurname("Deactivated");

        userRepository.save(requestedUser);
        reviewRepository.deleteByUserId(requestedUser.getId());

        request.setAcceptedBy(acceptedBy);
        request.setAccepted(true);
        repository.save(request);
        return mapper.convert(request);
    }

    private String generateUniqueEmail() {
        String uniqueIdentifier = String.valueOf(System.currentTimeMillis());
        String baseEmail = "@deactivated.com";
        String uniqueEmail = uniqueIdentifier + baseEmail;
        return uniqueEmail;
    }
}
