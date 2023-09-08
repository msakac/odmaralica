package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.AccommodationUnitGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ReservationGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ReservationPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ReservationPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.security.UserMapper;
import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;
import org.foi.diplomski.msakac.odmaralica.model.Reservation;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.service.IAccommodationUnitService;
import org.foi.diplomski.msakac.odmaralica.service.security.implementation.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ReservationMapper {
    @Autowired
    protected UserServiceImpl userService;
    @Autowired
    protected IAccommodationUnitService accommodationUnitService;

    protected UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    protected AccommodationUnitMapper accommodationUnitMapper = Mappers.getMapper(AccommodationUnitMapper.class);

    @Mapping(source = "reservationPostDTO.userId", target = "user")
    @Mapping(source = "reservationPostDTO.accommodationUnitId", target = "accommodationUnit")
    @Mapping(source = "reservationPostDTO.createdAt", target = "createdAt")
    public abstract Reservation convert(ReservationPostDTO reservationPostDTO);

    @Mapping(source = "reservationPutDTO.userEmail", target = "user")
    @Mapping(source = "reservationPutDTO.accommodationUnitId", target = "accommodationUnit")
    public abstract Reservation convert(ReservationPutDTO reservationPutDTO);

    @Mapping(source = "reservation.startAt", target = "startAt", qualifiedByName = "formatLocalDate")
    @Mapping(source = "reservation.endAt", target = "endAt", qualifiedByName = "formatLocalDate")
    public abstract ReservationGetDTO convert(Reservation reservation);

    protected User mapToUser(Long userId) {
        return userService.findById(userId);
    }

    protected User mapToUser(String email) {
        return userService.findByEmail(email);
    }

    protected AccommodationUnit mapToAccommodationUnit(Long accommodationUnitId) {
        AccommodationUnitGetDTO accommodationUnitGetDTO = accommodationUnitService.findById(accommodationUnitId);
        return accommodationUnitMapper.convert(accommodationUnitGetDTO);
    }

    @Named("formatLocalDate")
    protected Date formatLocalDate(LocalDate localDate) {
        if (localDate != null) {
            return Date.valueOf(localDate);
        }
        return null;
    }

}
