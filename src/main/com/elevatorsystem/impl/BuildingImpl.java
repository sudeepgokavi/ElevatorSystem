package main.com.elevatorsystem.impl;

public class BuildingImpl {

	private String buildingName;
	private Integer noOfFloors;
	private Integer noOfElevators;

	public BuildingImpl(String buildingName) {
		super();
		this.buildingName = buildingName;
	}

	public Integer getNoOfFloors() {
		return noOfFloors;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setNoOfFloors(Integer noOfFloors) {
		this.noOfFloors = noOfFloors;
	}

	public Integer getNoOfElevators() {
		return noOfElevators;
	}

	public void setNoOfElevators(Integer noOfElevators) {
		this.noOfElevators = noOfElevators;
	}

}
