package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListingStatus {

	private int id;
	private String status_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

    @Override
    public String toString() {
        return "ListingStatus{" +
                "id='" + id + '\'' +
                ", status_name=" + status_name +
                '}';
    }
}
