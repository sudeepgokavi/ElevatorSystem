package main.com.elevatorsystem.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import main.com.elevatorsystem.enums.ElevatorDirection;
import main.com.elevatorsystem.interfaces.ElevatorControlSystem;
import main.com.elevatorsystem.utilities.ElevatorSearchAlgorithm;

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

	public ElevatorControlSystemImpl(Integer numberOfElevators, Integer numberOfFloors, String buildingName) {
		building = new BuildingImpl(buildingName);
		building.setNoOfElevators(numberOfElevators);
		building.setNoOfFloors(numberOfFloors);

		callLocations = new LinkedList<Integer>();
		initializeElevator();
	}

	/**
	 * This method initializes all the elevators configured for the building.
	 */
	public void initializeElevator() {
		elevators = new ArrayList<ElevatorImpl>();
		for (int i = 0; i < building.getNoOfElevators(); i++) {
			elevator = new ElevatorImpl(0, "Elevator-" + i, i);
			elevators.add(elevator);
		}
	}

	/**
	 * Returns list of all the configured elevators.
	 * 
	 * @return
	 */
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

	/**
	 * This method will identify the nearest elevators to the called floor and
	 * move them to the respective floor.
	 * 
	 * @param calledFromFloor
	 */
	public void callingNearestElevator(final Integer calledFromFloor) {
		Map<ElevatorImpl, Integer> elevatorMap = new HashMap<ElevatorImpl, Integer>();

		for (int i = 0; i < elevators.size(); i++) {
			ElevatorImpl elevatorObj = elevators.get(i);
			elevatorMap.put(elevatorObj, elevatorObj.currentFloor());
		}

		// System.out.println(elevatorMap);
		ElevatorSearchAlgorithm esa = new ElevatorSearchAlgorithm();

		Iterator<Integer> elevatorCurrPosiListItr = elevatorMap.values().iterator();
		Integer[] arr = new Integer[elevatorMap.values().size()];
		int counter = 0;
		while (elevatorCurrPosiListItr.hasNext()) {
			arr[counter] = elevatorCurrPosiListItr.next();
			// System.out.println(arr[counter]);
			counter++;
		}
		Arrays.sort(arr);

		// Get the nearest floor list based on the entered no.
		List<Integer> nearestFloor = esa.nearestNumbers(arr, calledFromFloor, 1, elevatorMap.values().size());
		// System.out.println(nearestFloor);
		// Getting all the Elevator Objects from the nearest floors
		List<ElevatorImpl> nearestElevatorList = new ArrayList<ElevatorImpl>();

		for (Integer i : nearestFloor) {
			for (ElevatorImpl eObj : elevators) {
				if (eObj.currentFloor() == i) {
					nearestElevatorList.add(eObj);
				}
			}
		}

		// System.out.println("Nearest Elevator List - > " +
		// nearestElevatorList);

		for (ElevatorImpl e : nearestElevatorList) {
			e.addNewDestinatoin(calledFromFloor);
			break;
		}
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
			case ELEVATOR_OCCUPIED:
				switch (currElevator.direction()) {
				case ELEVATOR_UP:
					currElevator.moveUp();
					break;
				case ELEVATOR_DOWN:
					currElevator.moveDown();
					break;
				case ELEVATOR_HOLD:
					currElevator.popDestination();
					break;
				}
				if (currElevator.direction() == ElevatorDirection.ELEVATOR_UP)
					break;
			}
		}
	}

}
