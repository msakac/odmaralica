package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.get.AddressGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AddressPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AddressPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.AddressMapper;
import org.foi.diplomski.msakac.odmaralica.model.Address;
import org.foi.diplomski.msakac.odmaralica.repository.AddressRepository;
import org.foi.diplomski.msakac.odmaralica.service.IAddressService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class AddressServiceImpl extends AbstractBaseService<Address, AddressRepository, AddressMapper, AddressGetDTO, AddressPostDTO, AddressPutDTO> implements IAddressService {
    @Autowired
    public AddressServiceImpl(AddressRepository repository, AddressMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager);
    }

    @Override
    public AddressGetDTO create(AddressPostDTO entityPost) {
        Address existingAddress = repository.findByResidenceId(entityPost.getResidenceId());
        if (existingAddress != null) {
            throw new RuntimeException("Address with residenceId already exists");
        }
        return super.create(entityPost);
    }

    @Override
    public Address convertPost(AddressPostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public Address convertPut(AddressPutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public AddressGetDTO convertGet(Address entity) {
        return mapper.convert(entity);
    }

    public Class<Address> getEntityClass() {
        return Address.class;
    }
}
