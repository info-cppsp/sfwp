package hello.model;

import hello.ArrayHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyListingReport extends ListingReport {

	public MonthlyListingReport(Object[] objects) {

		super(ArrayHelper.skipFirstElement(objects));
		this.year_month = (String) objects[0];
	}

	private String year_month;

}
