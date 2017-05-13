package test.com.elevatorsystem;

import java.util.TreeSet;

import main.com.elevatorsystem.impl.ElevatorImpl;

public class ElevatorTest {

	private ElevatorImpl elevator;

	// @Before
	public void initializeElevator() {
		elevator = new ElevatorImpl(0, "Elevator 1", 1);
		TreeSet<Integer> floors = new TreeSet<Integer>();
		floors.add(2);
		floors.add(4);
		floors.add(6);
		floors.add(8);
		elevator.setAccessibleFloors(floors);
	}

	public void elevatorInfo() {
		System.out.println("Elevator Name - " + elevator.getElevatorName());
		System.out.println("Elevator Accessed by - " + elevator.getAccessibleFloors());
	}

	public void testAddingDestination() {
		elevator.addNewDestinatoin(8);
		System.out.println("Elevator's Next Destination - " + elevator.nextDestionation());
		// assertEquals(TENTH_FLOOR, elevator.nextDestionation());
	}

	public void moveElevatorUp(int floor) {
		System.out.println("Current Floor - " + elevator.currentFloor());
		System.out.println("Go To Floor - "+floor);
		for (int i = elevator.currentFloor(); i < floor; i++) {
			elevator.moveUp();
		}
		System.out.println("Destination Floor - " + elevator.currentFloor());
	}

	public void moveElevatorDown(int floor) {
		for (int i = elevator.currentFloor(); i < 9; i++) {
			elevator.moveUp();
		}
		
		System.out.println("Current Floor - " + elevator.currentFloor());
		System.out.println("Go To Floor - "+floor);
		for (int i = elevator.currentFloor(); i > floor; i--) {
			elevator.moveDown();
		}
		System.out.println("Destination Floor - " + elevator.currentFloor());
	}
	
	public void checkIfAccessible(int floor){
		if(elevator.getAccessibleFloors().contains(floor)){
			System.out.println("Entered Floor is Accessible - "+floor);	
		}else{
			System.out.println("Elevator cannot be accessed from this floor - "+floor);
		}
		
	}
	public static void main(String args[]) {
		ElevatorTest eleTest = new ElevatorTest();
		eleTest.initializeElevator();

		// Testing the if Elevator is configured properly
		eleTest.elevatorInfo();

		// Testing the addition of Destination.
		eleTest.testAddingDestination();
		
		// Testing Up Movement of the elevator
		eleTest.moveElevatorUp(6);
		
		// Testing Down Movement of the elevator
		eleTest.moveElevatorDown(2);
		
		// Check Floors Accessible
		eleTest.checkIfAccessible(4);
		eleTest.checkIfAccessible(3);
	}
}
