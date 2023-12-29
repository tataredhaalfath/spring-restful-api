package spring.belajarspringrestfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.belajarspringrestfulapi.entity.User;
import spring.belajarspringrestfulapi.model.AddrressResponse;
import spring.belajarspringrestfulapi.model.CreateAddressRequest;
import spring.belajarspringrestfulapi.model.WebResponse;
import spring.belajarspringrestfulapi.service.AddressService;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping(path = "/api/contacts/{contactId}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<AddrressResponse> create(User user, @RequestBody CreateAddressRequest request,
            @PathVariable("contactId") String contactId) {
        request.setContactId(contactId);
        AddrressResponse addrressResponse = addressService.create(user, request);
        return WebResponse.<AddrressResponse>builder().data(addrressResponse).build();
    }

    @GetMapping(path = "/api/contacts/{contactId}/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<AddrressResponse> get(User user, @PathVariable("contactId") String contactId,
            @PathVariable("addressId") String addressId) {
        AddrressResponse addrressResponse = addressService.get(user, contactId, addressId);
        return WebResponse.<AddrressResponse>builder().data(addrressResponse).build();
    }

}
