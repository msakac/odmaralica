package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.post.AmountPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AmountPutDto;
import org.foi.diplomski.msakac.odmaralica.model.Amount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AmountMapper {
    AmountMapper INSTANCE = Mappers.getMapper(AmountMapper.class);
    Amount convert(AmountPostDTO amountPostDTO);
    Amount convert(AmountPutDto amountPostDTO);
}
