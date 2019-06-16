package hello.model.report;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingReport {

	private int total_ebay_listing_count;
	private BigDecimal total_ebay_listing_price;
	private BigDecimal average_ebay_listing_price;

	private int total_amazon_listing_count;
	private BigDecimal total_amazon_listing_price;
	private BigDecimal average_amazon_listing_price;

	private String best_lister_email_address;

	public ListingReport(Object[] objects) {

		this.total_ebay_listing_count = ((Integer) objects[0]).intValue();
		this.total_ebay_listing_price = (BigDecimal) objects[1];
		this.average_ebay_listing_price = (BigDecimal) objects[2];

		this.total_amazon_listing_count = ((Integer) objects[3]).intValue();
		this.total_amazon_listing_price = (BigDecimal) objects[4];
		this.average_amazon_listing_price = (BigDecimal) objects[5];

		this.best_lister_email_address = (String) objects[6];
	}

}
