package hello.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Listing {

	@Id @NotNull
	private UUID id;
	@NotNull
	private String title;
	@NotNull
	private String description;
	@NotNull
	private UUID inventory_item_location_id;
	@NotNull @Positive @Digits(integer = 0, fraction = 2)
	private BigDecimal listing_price;
	@NotNull @Size(min = 3, max = 3)
	private String currency;
	@NotNull @Positive
	private int quantity;
	@NotNull
	private int listing_status;
	@NotNull
	private int marketplace;
	@JsonFormat(pattern="MM/dd/yyyy")
	private Date upload_time;
	@NotNull @Email
	private String owner_email_address;

}
