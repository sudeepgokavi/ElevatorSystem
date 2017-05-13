package main.com.elevatorsystem.interfaces;

import java.util.Set;

import main.com.elevatorsystem.enums.ElevatorDirection;
import main.com.elevatorsystem.enums.ElevatorStatus;

public interface Elevator {

	public void moveUp();

	public void moveDown();

	public void addNewDestinatoin(Integer destination);

	public void accessibleFloors(Set floorSet);

	public ElevatorDirection direction();

	public ElevatorStatus status();

}
