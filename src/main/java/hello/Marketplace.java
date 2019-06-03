package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Marketplace {

	private int id;
	private String marketplace_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarketplace_name() {
		return marketplace_name;
	}

	public void setMarketplace_name(String marketplace_name) {
		this.marketplace_name = marketplace_name;
	}

    @Override
    public String toString() {
        return "Marketplace{" +
                "id='" + id + '\'' +
                ", marketplace_name=" + marketplace_name +
                '}';
    }
}
