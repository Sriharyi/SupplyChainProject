package com.example.supplychain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
    public class FacilityAddress{
		private String street;
        private String city;
        private String pincode;
		private String state;
		private String country;
	}
