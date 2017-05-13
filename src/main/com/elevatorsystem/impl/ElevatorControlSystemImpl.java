package main.com.elevatorsystem.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import main.com.elevatorsystem.enums.ElevatorDirection;
import main.com.elevatorsystem.interfaces.ElevatorControlSystem;

public class ElevatorControlSystemImpl implements ElevatorControlSystem {

	private BuildingImpl building;
	private ElevatorImpl elevator;
	ArrayList<ElevatorImpl> elevators;
	Queue<Integer> callLocations;

	public BuildingImpl getBuilding() {
		return building;
	}

	public void setBuilding(BuildingImpl building) {
		this.building = building;
	}

	public void configureBuilding() {
		building = new BuildingImpl("IT-Park");
		building.setNoOfElevators(2);
		building.setNoOfFloors(10);
	}

	public ElevatorControlSystemImpl(Integer numberOfElevators, Integer numberOfFloors, String buildingName) {
		building = new BuildingImpl(buildingName);
		building.setNoOfElevators(numberOfElevators);
		building.setNoOfFloors(numberOfFloors);

		callLocations = new LinkedList<Integer>();
		initializeElevator();
	}

	public void initializeElevator() {
		elevators = new ArrayList<ElevatorImpl>();
		for (int i = 0; i < building.getNoOfElevators(); i++) {
			elevator = new ElevatorImpl(0, "Elevator-" + i, i);
			elevators.add(elevator);
		}
	}

	public ArrayList<ElevatorImpl> getAllElevators() {
		return elevators;
	}

	@Override
	public void call(Integer floor) {
		callLocations.add(floor);
	}

	@Override
	public void destination(Integer elevatorId, Integer destinationFloor) {
		elevators.get(elevatorId).addNewDestinatoin(destinationFloor);
	}

	@Override
	public void move() {
		for (ElevatorImpl currElevator : elevators) {
			// Check to figure out which ones are unoccupied and update call
			switch (currElevator.status()) {
			case ELEVATOR_EMPTY:
				if (!callLocations.isEmpty())
					currElevator.addNewDestinatoin(callLocations.poll());
				break;
			// Move occupied Elevators one step closer to next closest
			// destination in direction
			case ELEVATOR_OCCUPIED:
				switch (currElevator.direction()) {
				case ELEVATOR_UP:
					currElevator.moveUp();
					break;
				case ELEVATOR_DOWN:
					currElevator.moveDown();
					break;
				case ELEVATOR_HOLD:
					// TODO: Check timer here to alert users that they are
					// holding the door open to long
					// TODO: Emergency situation where elevator can't be used
					// TODO: Maintenance Mode e.g. movers or maintenance people
					currElevator.popDestination();
					break;
				}
				if (currElevator.direction() == ElevatorDirection.ELEVATOR_UP)
					break;
			}
		}
	}

}
