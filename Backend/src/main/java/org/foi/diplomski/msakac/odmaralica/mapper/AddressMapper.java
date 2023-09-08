package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.AddressGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.CityGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ResidenceGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AddressPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AddressPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Address;
import org.foi.diplomski.msakac.odmaralica.model.City;
import org.foi.diplomski.msakac.odmaralica.model.Residence;
import org.foi.diplomski.msakac.odmaralica.service.ICityService;
import org.foi.diplomski.msakac.odmaralica.service.IResidenceService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AddressMapper {
    @Autowired
    protected IResidenceService residenceService;
    @Autowired
    protected ICityService cityService;
    protected CityMapper cityMapper = Mappers.getMapper(CityMapper.class);
    protected ResidenceMapper residenceMapper = Mappers.getMapper(ResidenceMapper.class);

    @Mapping(source = "addressPostDTO.residenceId", target = "residence")
    @Mapping(source = "addressPostDTO.cityId", target = "city")
    public abstract Address convert(AddressPostDTO addressPostDTO);

    @Mapping(source = "addressPutDTO.cityId", target = "city")
    @Mapping(source = "addressPutDTO.residenceId", target = "residence")
    public abstract Address convert(AddressPutDTO addressPutDTO);

    public abstract AddressGetDTO convert(Address address);

    protected City mapToCity(Long cityId) {
        CityGetDTO cityGet = cityService.findById(cityId);
        return cityMapper.convert(cityGet);
    }

    protected Residence mapToResidence(Long residenceId) {
        ResidenceGetDTO residenceGet = residenceService.findById(residenceId);
        return residenceMapper.convert(residenceGet);
    }
}
