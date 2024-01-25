package Katze.DroneSimulation.data.ui;

public class HomepageResultlistData implements Comparable<HomepageResultlistData> {
	private String dronetype;
	private String serialnumber;
	
	public HomepageResultlistData(String dronetype, String serialnumber) {
		this.dronetype = dronetype;
		this.serialnumber = serialnumber;
	}

	public String getDronetype() {
		return dronetype;
	}

	public void setDronetype(String dronetype) {
		this.dronetype = dronetype;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	
	@Override
	public int compareTo(HomepageResultlistData other) {
		return this.getSerialnumber().compareTo(other.getSerialnumber());
	}
	
	
}
