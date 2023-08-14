package org.foi.diplomski.msakac.odmaralica.service.implementation;

import javax.persistence.EntityManager;

import org.foi.diplomski.msakac.odmaralica.dto.get.AddressGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AddressPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AddressPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.AddressMapper;
import org.foi.diplomski.msakac.odmaralica.model.Address;
import org.foi.diplomski.msakac.odmaralica.repository.AddressRepository;
import org.foi.diplomski.msakac.odmaralica.service.AddressService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends AbstractBaseService<Address, AddressRepository, AddressMapper, AddressGetDTO, AddressPostDTO, AddressPutDTO> implements AddressService {
    //FIXME: Apartnan more imati samo jednu adresu
    @Autowired
    public AddressServiceImpl(AddressRepository repository, AddressMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager);
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
