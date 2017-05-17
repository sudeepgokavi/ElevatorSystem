package test.com.elevatorsystem;

import java.util.ArrayList;

import main.com.elevatorsystem.impl.ElevatorControlSystemImpl;
import main.com.elevatorsystem.impl.ElevatorImpl;

public class ElevatorControlSystemTest {

	private ElevatorImpl elevator;
	private ElevatorControlSystemImpl ecs;
	private ArrayList<ElevatorImpl> elevators;

	/**
	 * Initialized the Elevator System
	 */
	public void initSystem() {
		ecs = new ElevatorControlSystemImpl(2, 10, "IT-Park");

		// Check the configuration
		System.out.println("Building Name - " + ecs.getBuilding().getBuildingName());
		System.out.println("No of Floors - " + ecs.getBuilding().getNoOfFloors());
		System.out.println("No of Elevators - " + ecs.getBuilding().getNoOfElevators());
	}

	/**
	 * Calling an elevator to a floor.
	 * @param floor
	 */
	public void callElevator(int floor) {
		System.out.println("Elevator Called from - " + floor);
		ecs.call(floor);

		for (int i = 0; i < ecs.getBuilding().getNoOfFloors(); i++) {
			ecs.move();
		}

		for (ElevatorImpl e : ecs.getAllElevators()) {
			System.out.println(e.getElevatorName() + " is at " + e.currentFloor());
		}
	}

	/**
	 * Calling 2 elevators to separate floors.
	 * @param call1
	 * @param call2
	 */
	public void call2Elevators(int call1, int call2) {
		System.out.println("Elevator Called from - " + call1 + " & " + call2);
		ecs.call(call1);
		ecs.call(call2);

		for (int i = 0; i < ecs.getBuilding().getNoOfFloors(); i++) {
			ecs.move();
		}

		for (ElevatorImpl e : ecs.getAllElevators()) {
			System.out.println(e.getElevatorName() + " is at " + e.currentFloor());
		}
	}

	/**
	 * Identifying the nearest elevator and calling it.
	 * @param floor
	 */
	public void callNearestElevator(int floor) {
		// Setting random desitnation for the elevators for testing purpose.
		System.out.println("--------------------------------------------------------------");
		System.out.println("Setting Random Destinations to the elevators.");
		ecs.call(3);
		ecs.call(8);

		for (int i = 0; i < ecs.getBuilding().getNoOfFloors(); i++) {
			ecs.move();
		}
		
		for (ElevatorImpl e : ecs.getAllElevators()) {
			System.out.println(e.getElevatorName() + " IS AT --- > "+e.currentFloor());
		}
		// Setting random Destinations for elevators.

		System.out.println("--------------------------------------------------------------");
		System.out.println("Elevator called from floor -- > "+floor);

		ecs.callingNearestElevator(floor);
		
		for (int i = 0; i < ecs.getBuilding().getNoOfFloors(); i++) {
			ecs.move();
		}

		for (ElevatorImpl e : ecs.getAllElevators()) {
			if(e.currentFloor() == floor){
				System.out.println("Nearest Elevator - "+e.getElevatorName() + " Called and Reached Floor - " + e.currentFloor());
				break;
			}
		}
	}

	/**
	 * Calling a floor from an elevator.
	 * @param floor
	 */
	public void callingFromElevator(int floor) {
		System.out.println("Floor Called from Elevator1 - " + floor);
		ecs.destination(0, floor);
		for (int i = 0; i < ecs.getBuilding().getNoOfFloors(); i++) {
			ecs.move();
		}
		elevators = ecs.getAllElevators();
		System.out.println(" Elevator - 1 reached - " + elevators.get(0).currentFloor());
	}

	/**
	 * Adding multiple destination to an elevator.
	 * @param floor1
	 * @param floor2
	 */
	public void elevatorToMultipleFloors(int floor1, int floor2) {
		ecs.destination(0, floor1);
		ecs.destination(0, floor2);
		for (int i = 0; i < ecs.getBuilding().getNoOfFloors(); i++) {
			ecs.move();
			if (i == floor1 || i == floor2)
				System.out.println("Elevator Stopped Floor - " + i);
		}
		elevators = ecs.getAllElevators();
		System.out.println(" Elevator - 1 reached - " + elevators.get(0).currentFloor());
	}

	public static void main(String args[]) {
		ElevatorControlSystemTest ecsTest = new ElevatorControlSystemTest();
		ecsTest.initSystem();

		//ecsTest.callElevator(8);
		
		ecsTest.callNearestElevator(4);

		ecsTest.callNearestElevator(7);
		// ecsTest.call2Elevators(4, 8);

		// ecsTest.callingFromElevator(4);

		// ecsTest.elevatorToMultipleFloors(4, 8);

	}

}
