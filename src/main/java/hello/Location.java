package hello;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Location {

	@Id
	private UUID id;
	private String manager_name;
	private String phone;
	private String address_primary;
	private String address_secondary;
	private String country;
	private String town;
	private String postal_code;

}
