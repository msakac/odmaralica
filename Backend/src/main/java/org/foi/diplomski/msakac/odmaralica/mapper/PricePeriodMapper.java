package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.AccommodationUnitGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.AmountGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.PricePeriodGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.PricePeriodPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.PricePeriodPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.AccommodationUnit;
import org.foi.diplomski.msakac.odmaralica.model.Amount;
import org.foi.diplomski.msakac.odmaralica.model.PricePeriod;
import org.foi.diplomski.msakac.odmaralica.service.IAccommodationUnitService;
import org.foi.diplomski.msakac.odmaralica.service.IAmountService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PricePeriodMapper {

    @Autowired
    protected IAccommodationUnitService accommodationUnitService;
    @Autowired
    protected IAmountService amountService;

    protected AccommodationUnitMapper accommodationUnitMapper = Mappers.getMapper(AccommodationUnitMapper.class);
    protected AmountMapper amountMapper = Mappers.getMapper(AmountMapper.class);

    @Mapping(source="pricePeriodPostDTO.accommodationUnitId", target="accommodationUnit")
    @Mapping(source="pricePeriodPostDTO.amountId", target="amount")
    public abstract PricePeriod convert(PricePeriodPostDTO pricePeriodPostDTO);

    @Mapping(source="pricePeriodPutDTO.accommodationUnitId", target="accommodationUnit")
    @Mapping(source="pricePeriodPutDTO.amountId", target="amount")
    public abstract PricePeriod convert(PricePeriodPutDTO pricePeriodPutDTO);

    @Mapping(source="pricePeriod.startAt", target="startAt", dateFormat = "yyyy-MM-dd")
    @Mapping(source="pricePeriod.endAt", target="endAt", dateFormat = "yyyy-MM-dd")
    public abstract PricePeriodGetDTO convert(PricePeriod pricePeriod);

    protected AccommodationUnit mapToAccommodationUnit(Long accommodationUnitId) {
        AccommodationUnitGetDTO accommodationUnitGetDTO = accommodationUnitService.findById(accommodationUnitId);
        return accommodationUnitMapper.convert(accommodationUnitGetDTO);
    }

    protected Amount mapToAmount(Long amountId) {
        AmountGetDTO amountGetDTO = amountService.findById(amountId);
        return amountMapper.convert(amountGetDTO);
    }
}
