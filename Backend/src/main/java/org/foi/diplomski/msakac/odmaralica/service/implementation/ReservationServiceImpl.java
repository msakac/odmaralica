package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.get.ReservationGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ReservationPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ReservationPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.ReservationMapper;
import org.foi.diplomski.msakac.odmaralica.model.PricePeriod;
import org.foi.diplomski.msakac.odmaralica.model.Reservation;
import org.foi.diplomski.msakac.odmaralica.repository.PricePeriodRepository;
import org.foi.diplomski.msakac.odmaralica.repository.ReservationRepository;
import org.foi.diplomski.msakac.odmaralica.service.IReservationService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl extends AbstractBaseService<Reservation, ReservationRepository, ReservationMapper, ReservationGetDTO, ReservationPostDTO, ReservationPutDTO> implements IReservationService {
    private static final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private final PricePeriodRepository pricePeriodRepository;


    @Autowired
    public ReservationServiceImpl(ReservationRepository repository, ReservationMapper mapper, EntityManager entityManager, PricePeriodRepository pricePeriodRepository) {
        super(repository, mapper, entityManager);
        this.pricePeriodRepository = pricePeriodRepository;
    }

    @Override
    public ReservationGetDTO create(ReservationPostDTO entityPost) {
        entityPost.setCreatedAt(Timestamp.from(new Date().toInstant()));
        Reservation reservation = convertPost(entityPost);
        this.validate(reservation);
        return super.create(entityPost);
    }

    @Override
    public ReservationGetDTO update(ReservationPutDTO entityPut) {
        Reservation reservation = convertPut(entityPut);
        this.validate(reservation);
        return super.update(entityPut);
    }

    @Override
    public Reservation convertPost(ReservationPostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public Reservation convertPut(ReservationPutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public ReservationGetDTO convertGet(Reservation entity) {
        return mapper.convert(entity);
    }

    public Class<Reservation> getEntityClass() {
        return Reservation.class;
    }

    private void validate(Reservation entity) {
        // Check if start date is before end date
        if (entity.getStartAt().after(entity.getEndAt())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        // Check if created at is before start date
        if (entity.getCreatedAt().after(entity.getStartAt())) {
            throw new IllegalArgumentException("Start date cannot be in the past");
        }
        // Check if reservation overlaps with existing reservation
        List<Reservation> reservations = repository.findByAccommodationUnitIdAndCancelledFalse(entity.getAccommodationUnit().getId());
        for (Reservation reservation : reservations) {
            if (reservation.getId().equals(entity.getId())) {
                continue;
            }
            if (reservation.getStartAt().compareTo(entity.getStartAt()) <= 0 && reservation.getEndAt().compareTo(entity.getStartAt()) >= 0) {
                throw new RuntimeException("Reservation overlaps with existing reservation");
            }
        }

        List<PricePeriod> pricePeriods = pricePeriodRepository.findByAccommodationUnitId(entity.getAccommodationUnit().getId());
        List<Date> reservationDateList = new ArrayList<>();

        //Write all reservation dates to list
        Date reservationDate = new Date(entity.getStartAt().getTime());
        while (reservationDate.before(entity.getEndAt())) {
            try {
                Date dateNoTime = formatter.parse(formatter.format(reservationDate));
                reservationDateList.add(dateNoTime);
                reservationDate = new Date(reservationDate.getTime() + (24 * 60 * 60 * 1000)); // Increment date by one day
            } catch (Exception e) {
                throw new RuntimeException("Error parsing date");
            }
        }

        //Write all price period date to list
        List<Date> pricePeriodDateList = new ArrayList<>();
        for (PricePeriod pricePeriod : pricePeriods) {
            Date pricePeriodDate = new Date(pricePeriod.getStartAt().getTime());
            Date pricePeriodEndDate = new Date(pricePeriod.getEndAt().getTime() + (24 * 60 * 60 * 1000));
            while (pricePeriodDate.before(pricePeriodEndDate)) {
                try {
                    Date dateNoTime = formatter.parse(formatter.format(pricePeriodDate));
                    pricePeriodDateList.add(dateNoTime);
                    pricePeriodDate = new Date(pricePeriodDate.getTime() + (24 * 60 * 60 * 1000)); // Increment date by one day
                } catch (Exception e) {
                    throw new RuntimeException("Error parsing date");
                }
            }
        }

        //Check if reservation dates are in price period dates
        for (Date reservationDateItem : reservationDateList) {
            if (!pricePeriodDateList.contains(reservationDateItem)) {
                throw new RuntimeException("Reservation dates are not in price period dates");
            }
        }
    }
}
