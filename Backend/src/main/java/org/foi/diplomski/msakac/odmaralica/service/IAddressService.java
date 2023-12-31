package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.dto.get.AddressGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.AddressPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.AddressPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Address;

import java.util.List;

public interface IAddressService {
    Address convertPost(AddressPostDTO entityPost);

    Address convertPut(AddressPutDTO entityPut);

    AddressGetDTO convertGet(Address entity);

    AddressGetDTO create(AddressPostDTO entityPost);

    AddressGetDTO findById(Long id);

    List<AddressGetDTO> findAll();

    AddressGetDTO update(AddressPutDTO entityPut);

    void delete(Long id);

    List<AddressGetDTO> find(String queryParams);

    Class<Address> getEntityClass();
}
