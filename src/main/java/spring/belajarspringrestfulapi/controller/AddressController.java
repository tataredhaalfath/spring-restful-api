package spring.belajarspringrestfulapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.belajarspringrestfulapi.entity.User;
import spring.belajarspringrestfulapi.model.AddrressResponse;
import spring.belajarspringrestfulapi.model.CreateAddressRequest;
import spring.belajarspringrestfulapi.model.UpdateAddressRequest;
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

    @PutMapping(path = "/api/contacts/{contactId}/addresses/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<AddrressResponse> update(
            User user,
            @RequestBody UpdateAddressRequest request,
            @PathVariable("contactId") String contactId,
            @PathVariable("addressId") String addressId) {
        request.setContactId(contactId);
        request.setAddressId(addressId);

        AddrressResponse addrressResponse = addressService.update(user, request);
        return WebResponse.<AddrressResponse>builder().data(addrressResponse).build();
    }

    @DeleteMapping(path = "/api/contacts/{contactId}/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> remove(
            User user,
            @PathVariable("contactId") String contactId,
            @PathVariable("addressId") String addressId) {
        addressService.remove(user, contactId, addressId);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(path = "/api/contacts/{contactId}/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<AddrressResponse>> list(User user, @PathVariable("contactId") String contactId) {
        List<AddrressResponse> addrressResponses = addressService.list(user, contactId);
        return WebResponse.<List<AddrressResponse>>builder().data(addrressResponses).build();
    }

}
