package hello;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

	private UUID id;
	private String manager_name;
	private String phone;
	private String address_primary;
	private String address_secondary;
	private String country;
	private String town;
	private String postal_code;

}
