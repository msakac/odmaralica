package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.custom.*;
import org.foi.diplomski.msakac.odmaralica.dto.get.AmountGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.CityGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ResidencePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ResidencePutDTO;
import org.foi.diplomski.msakac.odmaralica.dto.security.UserGetDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.AmountMapper;
import org.foi.diplomski.msakac.odmaralica.mapper.CityMapper;
import org.foi.diplomski.msakac.odmaralica.mapper.ResidenceMapper;
import org.foi.diplomski.msakac.odmaralica.mapper.ResidenceTypeMapper;
import org.foi.diplomski.msakac.odmaralica.mapper.security.UserMapper;
import org.foi.diplomski.msakac.odmaralica.model.*;
import org.foi.diplomski.msakac.odmaralica.repository.*;
import org.foi.diplomski.msakac.odmaralica.service.IResidenceService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ResidenceServiceImpl extends AbstractBaseService<Residence, ResidenceRepository, ResidenceMapper, ResidenceGetDTO, ResidencePostDTO, ResidencePutDTO> implements IResidenceService {
    //FIXME: vlasnik apartmana nebre biti korisnik koji nije iznajmljivac
    //FIXME: Frontend moderator da more birati ko bude vlasnik apartmana

    private static final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


    private final AddressRepository addressRepository;
    private final AccommodationUnitRepository accommodationUnitRepository;
    private final PricePeriodRepository pricePeriodRepository;
    private final ImageRepository imageRepository;
    private final CityMapper cityMapper;
    private final UserMapper userMapper;
    private final ResidenceTypeMapper residenceTypeMapper;
    private final AmountMapper amountMapper;
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ResidenceServiceImpl(ResidenceRepository repository,
                                ResidenceMapper mapper, EntityManager entityManager,
                                AddressRepository addressRepository,
                                AccommodationUnitRepository accommodationUnitRepository,
                                PricePeriodRepository pricePeriodRepository,
                                ImageRepository imageRepository,
                                CityMapper cityMapper,
                                UserMapper userMapper,
                                ResidenceTypeMapper residenceTypeMapper,
                                AmountMapper amountMapper,
                                ReservationRepository reservationRepository,
                                ReviewRepository reviewRepository
    ) {
        super(repository, mapper, entityManager);
        this.addressRepository = addressRepository;
        this.accommodationUnitRepository = accommodationUnitRepository;
        this.pricePeriodRepository = pricePeriodRepository;
        this.imageRepository = imageRepository;
        this.cityMapper = cityMapper;
        this.userMapper = userMapper;
        this.residenceTypeMapper = residenceTypeMapper;
        this.amountMapper = amountMapper;
        this.reservationRepository = reservationRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ResidenceGetDTO create(ResidencePostDTO entityPost) {
        final Residence residence = this.convertPost(entityPost);
        entityPost.setIsPublished(false);
        if (residence.getOwner().getRole().getId() <= 1) {
            throw new RuntimeException("Owner must belong to the renter role");
        }
        return super.create(entityPost);
    }

    @Override
    public Residence convertPost(ResidencePostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public Residence convertPut(ResidencePutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public ResidenceGetDTO convertGet(Residence entity) {
        return mapper.convert(entity);
    }

    public Class<Residence> getEntityClass() {
        return Residence.class;
    }

    @Override
    public ResidenceAggregateDTO aggregateData(Long id) {
        Residence r = repository.findById(id).orElse(null);
        if (r == null) {
            return null;
        }

        UserGetDTO owner = userMapper.convertToUserGetDTO(r.getOwner());
        ResidenceTypeGetDTO type = residenceTypeMapper.convert(r.getType());
        List<Long> imageIds = getResidenceImages(r.getId());
        if (imageIds.isEmpty()) {
            return null;
        }
        List<CustomAccommodationUnitDTO> units = getCustomUnits(r.getId());
        if (units.isEmpty()) {
            return null;
        }

        List<CustomReviewGetDTO> reviews = getCustomReviews(r.getId());
        ResidenceAggregateDTO agregate = new ResidenceAggregateDTO(
                r.getId(), r.getName(), type, r.getDescription(), owner, r.getIsPublished(),
                r.getIsParkingFree(), r.getIsWifiFree(), r.getIsAirConFree(), r.getDistanceBeach(),
                r.getDistanceCenter(), r.getDistanceSea(), r.getDistanceStore(), imageIds, getCustomAddress(r), units, reviews
        );

        return agregate;
    }

    @Override
    public List<ResidenceAggregateDTO> aggregateAllData() {
        List<Residence> residences = repository.findByIsPublishedTrue();
        List<ResidenceAggregateDTO> aggregate = new ArrayList<>();
        for (Residence r : residences) {
            UserGetDTO owner = userMapper.convertToUserGetDTO(r.getOwner());
            ResidenceTypeGetDTO type = residenceTypeMapper.convert(r.getType());
            List<Long> imageIds = getResidenceImages(r.getId());
            if (imageIds.isEmpty()) {
                continue;
            }
            List<CustomAccommodationUnitDTO> units = getCustomUnits(r.getId());
            if (units.isEmpty()) {
                continue;
            }

            List<CustomReviewGetDTO> reviews = getCustomReviews(r.getId());

            ResidenceAggregateDTO dto = new ResidenceAggregateDTO(
                    r.getId(), r.getName(), type, r.getDescription(), owner, r.getIsPublished(),
                    r.getIsParkingFree(), r.getIsWifiFree(), r.getIsAirConFree(), r.getDistanceBeach(),
                    r.getDistanceCenter(), r.getDistanceSea(), r.getDistanceStore(), imageIds, getCustomAddress(r), units, reviews
            );
            aggregate.add(dto);
        }
        return aggregate;
    }

    private CustomAddressDTO getCustomAddress(Residence r) {
        Address a = addressRepository.findByResidenceId(r.getId());
        CityGetDTO city = cityMapper.convert(a.getCity());
        CustomAddressDTO customAddress = new CustomAddressDTO(a.getId(), a.getStreet(), city, a.getAdditional());
        return customAddress;
    }

    private List<Long> getResidenceImages(Long id) {
        List<Image> images = imageRepository.findAllByResidenceId(id);
        //return only list of image ids
        List<Long> imageIds = new ArrayList<>();
        for (Image i : images) {
            imageIds.add(i.getId());
        }
        return imageIds;
    }

    private List<CustomAccommodationUnitDTO> getCustomUnits(Long id) {
        List<AccommodationUnit> units = accommodationUnitRepository.findAllByResidenceId(id);
        List<CustomAccommodationUnitDTO> customUnits = new ArrayList<>();
        for (AccommodationUnit u : units) {
            List<CustomPricePeriodDTO> pricePeriods = getPricePeriods(u.getId());
            if (pricePeriods.isEmpty()) {
                continue;
            }
            List<String> availableDates = getAvailableDates(pricePeriods, u.getId());
            if (availableDates.isEmpty()) {
                continue;
            }
            List<Long> imageIds = getAccomUnitImages(u.getId());
            if (imageIds.isEmpty()) {
                continue;
            }
            CustomAccommodationUnitDTO unit = new CustomAccommodationUnitDTO(
                    u.getId(), u.getName(), u.getDescription(), u.getUnitSize(), u.getNumOfGuests(), u.getBeds(),
                    u.getPrivateKitchen(), u.getPrivateBathroom(), u.getTerrace(), u.getSeaView(), u.getTv(), u.getPets(),
                    u.getSmoking(), pricePeriods, availableDates, imageIds
            );
            customUnits.add(unit);
        }
        return customUnits;
    }

    private List<Long> getAccomUnitImages(Long id) {
        List<Image> images = imageRepository.findAllByAccommodationUnitId(id);
        //return only list of image ids
        List<Long> imageIds = new ArrayList<>();
        for (Image i : images) {
            imageIds.add(i.getId());
        }
        return imageIds;
    }

    private List<CustomPricePeriodDTO> getPricePeriods(Long id) {
        List<PricePeriod> pricePeriods = pricePeriodRepository.findByAccommodationUnitId(id);
        List<CustomPricePeriodDTO> customPricePeriods = new ArrayList<>();
        for (PricePeriod p : pricePeriods) {
            //if price period endAt is in the past then skip it
            if (p.getEndAt().before(new Date())) {
                continue;
            }
            AmountGetDTO amount = amountMapper.convert(p.getAmount());
            CustomPricePeriodDTO pricePeriod = new CustomPricePeriodDTO(p.getId(), p.getStartAt(), p.getEndAt(), amount);
            customPricePeriods.add(pricePeriod);
        }
        return customPricePeriods;
    }

    private List<String> getAvailableDates(List<CustomPricePeriodDTO> pricePeriods, Long id) {
        List<Reservation> reservations = reservationRepository.findByAccommodationUnitIdAndCancelledFalse(id);
        /* Get all reserved dates */
        /* Ako je rezervacija od 13.09 do 23.09 to znaci da je apartman slobodni od x to 13.09 i od 23.09 do.
         */
        List<Date> reservedDates = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.isCancelled()) {
                continue;
            }
            Date reservationDate = new Date(reservation.getStartAt().getTime());
            Date reservationEndDate = new Date(reservation.getEndAt().getTime());

            while (reservationDate.before(reservationEndDate)) {
                try {
                    Date dateNoTime = formatter.parse(formatter.format(reservationDate));
                    reservedDates.add(dateNoTime);
                    reservationDate = new Date(reservationDate.getTime() + (24 * 60 * 60 * 1000));
                } catch (Exception e) {
                    throw new RuntimeException("Error parsing date");
                }
            }
        }

        //check if two reservations have same start and end date if they have add them to reservedDates
        for (Reservation reservation : reservations) {
            try {
                Date startAt = formatter.parse(formatter.format(reservation.getStartAt()));
                for (Reservation reservation2 : reservations) {
                    Date endAt = formatter.parse(formatter.format(reservation2.getEndAt()));
                    if (startAt.equals(endAt)) {
                        reservedDates.add(startAt);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Error parsing date");
            }
        }

        /* Get all price period dates */
        List<Date> pricePeriodDates = new ArrayList<>();
        for (CustomPricePeriodDTO pricePeriod : pricePeriods) {
            Date pricePeriodDate = new Date(pricePeriod.getStartAt().getTime());
            Date pricePeriodEndDate = new Date(pricePeriod.getEndAt().getTime() + (24 * 60 * 60 * 1000));
            while (pricePeriodDate.before(pricePeriodEndDate)) {
                try {
                    Date dateNoTime = formatter.parse(formatter.format(pricePeriodDate));
                    pricePeriodDates.add(dateNoTime);
                    pricePeriodDate = new Date(pricePeriodDate.getTime() + (24 * 60 * 60 * 1000));// Increment by one day
                } catch (Exception e) {
                    throw new RuntimeException("Error parsing date");
                }
            }
        }

        List<Date> availableDates = new ArrayList<>();
        Date startDate = new Date();
        Date endDate = calculateEndDate();
        while (startDate.before(endDate)) {
            try {
                Date dateNoTime = formatter.parse(formatter.format(startDate));
                if (!reservedDates.contains(dateNoTime) && pricePeriodDates.contains(dateNoTime)) {
                    availableDates.add(dateNoTime);
                }
                startDate = new Date(startDate.getTime() + (24 * 60 * 60 * 1000));
            } catch (Exception e) {
                throw new RuntimeException("Error parsing date");
            }
        }
        List<String> availableDatesString = new ArrayList<>();
        for (Date d : availableDates) {
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(d);
            availableDatesString.add(dateString);
        }
        return availableDatesString;
    }

    //End date mi je danas plus jedna godina
    private Date calculateEndDate() {
        long oneYearInMillis = 365 * 24 * 60 * 60 * 1000L;
        return new Date(System.currentTimeMillis() + oneYearInMillis);
    }

    private List<CustomReviewGetDTO> getCustomReviews(Long id) {
        List<Review> reviews = reviewRepository.findByResidenceId(id);
        List<CustomReviewGetDTO> customReviews = new ArrayList<>();
        for (Review r : reviews) {
            UserGetDTO reviewer = userMapper.convertToUserGetDTO(r.getUser());
            CustomReviewGetDTO review = new CustomReviewGetDTO(r.getId(), reviewer, r.getGrade(), r.getMessage());
            customReviews.add(review);
        }
        return customReviews;
    }

}
