package main.com.elevatorsystem.impl;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import main.com.elevatorsystem.enums.ElevatorDirection;
import main.com.elevatorsystem.enums.ElevatorStatus;
import main.com.elevatorsystem.interfaces.Elevator;

public class ElevatorImpl implements Elevator {

	private Integer currentFloor;
	private Queue<Integer> destinationFloors;
	private String elevatorName;
	private int elevatorId;
	private Set<Integer> accessibleFloors;

	public ElevatorImpl(Integer currentFloor, String elevatorName, int elevatorId) {
		super();
		this.currentFloor = currentFloor;
		this.destinationFloors = new LinkedList<Integer>();
		this.elevatorName = elevatorName;
		this.elevatorId = elevatorId;
		this.accessibleFloors = new TreeSet<Integer>();
	}

	public String getElevatorName() {
		return this.elevatorName;
	}

	public int getElevatorId() {
		return this.elevatorId;
	}

	public Set<Integer> getAccessibleFloors() {
		return this.accessibleFloors;
	}

	public void setAccessibleFloors(Set<Integer> accessibleFloors) {
		this.accessibleFloors = accessibleFloors;
	}

	public int nextDestionation() {
		return this.destinationFloors.peek();
	}

	public int currentFloor() {
		return this.currentFloor;
	}

	public void popDestination() {
		this.destinationFloors.remove();
	}

	@Override
	public void addNewDestinatoin(Integer destination) {
		this.destinationFloors.add(destination);
	}

	@Override
	public void moveUp() {
		currentFloor++;
	}

	@Override
	public void moveDown() {
		currentFloor--;
	}

	@Override
	public ElevatorDirection direction() {
		if (destinationFloors.size() > 0) {
			if (currentFloor < destinationFloors.peek()) {
				return ElevatorDirection.ELEVATOR_UP;
			} else if (currentFloor > destinationFloors.peek()) {
				return ElevatorDirection.ELEVATOR_DOWN;
			}
		}
		return ElevatorDirection.ELEVATOR_HOLD;
	}

	@Override
	public ElevatorStatus status() {
		return (destinationFloors.size() > 0) ? ElevatorStatus.ELEVATOR_OCCUPIED : ElevatorStatus.ELEVATOR_EMPTY;
	}

	@Override
	public void accessibleFloors(Set floorSet) {
		// TODO Auto-generated method stub

	}

}
