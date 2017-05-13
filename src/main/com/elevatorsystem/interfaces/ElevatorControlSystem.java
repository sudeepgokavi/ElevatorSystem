package main.com.elevatorsystem.interfaces;

public interface ElevatorControlSystem {

	public void call(Integer floor);

	public void destination(Integer elevatorId, Integer destinationFloor);

	public void move();

}
