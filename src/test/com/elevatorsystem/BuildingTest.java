package test.com.elevatorsystem;

import main.com.elevatorsystem.impl.BuildingImpl;

public class BuildingTest {

	private BuildingImpl building;

	/**
	 * Configuring the building.
	 * 
	 */
	public void configureBuilding() {
		building = new BuildingImpl("IT-Park");
		building.setNoOfElevators(6);
		building.setNoOfFloors(42);
	}

	public void buildingDetails() {
		System.out.println("Building Name - " + building.getBuildingName());
		System.out.println("No of Floors - " + building.getNoOfFloors());
		System.out.println("No of Elevators - " + building.getNoOfElevators());
	}

	public static void main(String args[]) {
		BuildingTest buldingTest = new BuildingTest();
		buldingTest.configureBuilding();
		buldingTest.buildingDetails();
	}
}
