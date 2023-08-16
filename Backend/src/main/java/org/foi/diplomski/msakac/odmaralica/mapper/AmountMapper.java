package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.AmountGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AmountPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AmountPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Amount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AmountMapper {
    AmountMapper INSTANCE = Mappers.getMapper(AmountMapper.class);

    Amount convert(AmountPostDTO amountPostDTO);

    Amount convert(AmountPutDTO amountPostDTO);

    AmountGetDTO convert(Amount amount);

    Amount convert(AmountGetDTO amountGetDTO);
}
