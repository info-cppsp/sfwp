package hello.model;

import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import hello.ArrayHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingReportSummary extends ListingReport {

	private int total_listing_count;
	private ArrayList<MonthlyListingReport> monthlyReports;

	// monthlyReports not set!
	public ListingReportSummary(Object[] objects) {

		super(ArrayHelper.skipFirstElement(objects));
		this.total_listing_count = ((Integer) objects[0]).intValue();
	}

	public static ListingReportSummary generateFromObjectArrays(ArrayList<Object[]> objectArrays) {

		ListingReportSummary listingReportSummary = null;
		ArrayList<MonthlyListingReport> monthlyReports = new ArrayList<MonthlyListingReport>();

		for (Object[] objects : objectArrays) {

			if (Objects.equals(objects[0], "total")) {
				listingReportSummary = new ListingReportSummary(ArrayHelper.skipFirstElement(objects));
				continue;
			}

			monthlyReports.add(new MonthlyListingReport(ArrayHelper.skipSecondElement(objects)));
		}

		listingReportSummary.setMonthlyReports(monthlyReports);
		return listingReportSummary;
	}

	public String toJSONString() {

		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		String returnJSON = "";

		try {
			returnJSON = objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnJSON;
	}
}
