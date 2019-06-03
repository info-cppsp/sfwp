package hello;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UUID getInventory_item_location_id() {
		return inventory_item_location_id;
	}

	public void setInventory_item_location_id(UUID inventory_item_location_id) {
		this.inventory_item_location_id = inventory_item_location_id;
	}

	public BigDecimal getListing_price() {
		return listing_price;
	}

	public void setListing_price(BigDecimal listing_price) {
		this.listing_price = listing_price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getListing_status() {
		return listing_status;
	}

	public void setListing_status(int listing_status) {
		this.listing_status = listing_status;
	}

	public int getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(int marketplace) {
		this.marketplace = marketplace;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}

	public String getOwner_email_address() {
		return owner_email_address;
	}

	public void setOwner_email_address(String owner_email_address) {
		this.owner_email_address = owner_email_address;
	}

    @Override
    public String toString() {
        return "Listing{" +
                "id='" + id.toString() + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", inventory_item_location_id='" + inventory_item_location_id.toString() + '\'' +
                ", listing_price='" + listing_price.toPlainString() + '\'' +
                ", currency='" + currency + '\'' +
                ", quantity='" + quantity + '\'' +
                ", listing_status='" + listing_status + '\'' +
                ", marketplace='" + marketplace + '\'' +
                ", upload_time='" + upload_time.toString() + '\'' +
                ", owner_email_address=" + owner_email_address +
                '}';
    }
}
