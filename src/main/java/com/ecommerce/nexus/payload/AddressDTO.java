package com.ecommerce.nexus.payload;

//import com.ecommerce.nexus.model.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long addressId;
    private String street;
    private String building;
    private String city;
    private String state;
    private String country;
    private String pincode;
}
