package hello;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Listing {

	private UUID id;
	private String title;
	private String description;
	private UUID inventory_item_location_id;
	private BigDecimal listing_price;
	private String currency;
	private int quantity;
	private int listing_status;
	private int marketplace;
	private Date upload_time;
	private String owner_email_address;

}
