package test.com.elevatorsystem;

import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import main.com.elevatorsystem.enums.ElevatorWorking;
import main.com.elevatorsystem.impl.ElevatorImpl;
//import static junit.framework.Assert.assertEquals;

public class ElevatorTest {

	private ElevatorImpl elevator;

	/**
	 * Initializing the elevator.
	 */
	// @Before
	public void initializeElevator() {
		elevator = new ElevatorImpl(0, "Elevator 1", 1);
		TreeSet<Integer> floors = new TreeSet<Integer>();
		floors.add(2);
		floors.add(4);
		floors.add(6);
		floors.add(8);
		elevator.setAccessibleFloors(floors);
		//elevator.setEleWorkingStatus(ElevatorWorking.ELEVATOR_UNDER_MAINTAIENCE);
	}

	/**
	 * Getting the elevator info.
	 */
	public void elevatorInfo() {
		System.out.println("Elevator Name - " + elevator.getElevatorName());
		System.out.println("Elevator Accessed by - " + elevator.getAccessibleFloors());
	}

	/**
	 * Adding new destination to the elevator.
	 */
	// @Test
	public void testAddingDestination() {
		elevator.addNewDestinatoin(8);
		System.out.println("Elevator's Next Destination - " + elevator.nextDestionation());
		// assertEquals(8, elevator.nextDestionation());
	}

	/**
	 * Moving the elevator UP to a specific destination.
	 * @param floor
	 */
	public void moveElevatorUp(int floor) {
		if (elevator.getEleWorkingStatus().equals(ElevatorWorking.ELEVATOR_WORKING)) {
			System.out.println("Current Floor - " + elevator.currentFloor());
			System.out.println("Go To Floor - " + floor);
			for (int i = elevator.currentFloor(); i < floor; i++) {
				elevator.moveUp();
			}
			System.out.println("Destination Floor - " + elevator.currentFloor());
		} else {
			System.out.println("Elevator Not Working. Appologies for the inconvinence caused !");
		}
	}

	/**
	 * Moving the elevator Down to a specific destination.
	 * @param floor
	 */
	public void moveElevatorDown(int floor) {
		if (elevator.getEleWorkingStatus().equals(ElevatorWorking.ELEVATOR_WORKING)) {
			for (int i = elevator.currentFloor(); i < 9; i++) {
				elevator.moveUp();
			}

			System.out.println("Current Floor - " + elevator.currentFloor());
			System.out.println("Go To Floor - " + floor);
			for (int i = elevator.currentFloor(); i > floor; i--) {
				elevator.moveDown();
			}
			System.out.println("Destination Floor - " + elevator.currentFloor());
		} else {
			System.out.println("Elevator Not Working. Appologies for the inconvinence caused ! ");
		}
	}

	/**
	 * Check if the elevator has access to the specified floor.
	 * @param floor
	 */
	public void checkIfAccessible(int floor) {
		if (elevator.getAccessibleFloors().contains(floor)) {
			System.out.println("Entered Floor is Accessible - " + floor);
		} else {
			System.out.println("Elevator cannot be accessed from this floor - " + floor);
		}
	}

	/**
	 * Change elevator working status.
	 * @param eStaus
	 */
	public void changeElevatorWorkingStatus(ElevatorWorking eStaus) {
		elevator.setEleWorkingStatus(eStaus);
	}

	public static void main(String args[]) {
		ElevatorTest eleTest = new ElevatorTest();
		eleTest.initializeElevator();

		// Testing the if Elevator is configured properly
		eleTest.elevatorInfo();

		// Testing the addition of Destination.
		eleTest.testAddingDestination();

		// Check Floors Accessible
		eleTest.checkIfAccessible(4);
		eleTest.checkIfAccessible(3);

		eleTest.changeElevatorWorkingStatus(ElevatorWorking.ELEVATOR_WORKING);
		
		// Testing Up Movement of the elevator
		eleTest.moveElevatorUp(6);

		// Testing Down Movement of the elevator
		eleTest.moveElevatorDown(2);

		eleTest.changeElevatorWorkingStatus(ElevatorWorking.ELEVATOR_UNDER_MAINTAIENCE);
		
		// Testing Up Movement of the elevator
		eleTest.moveElevatorUp(8);

		// Testing Down Movement of the elevator
		eleTest.moveElevatorDown(4);

	}
}
