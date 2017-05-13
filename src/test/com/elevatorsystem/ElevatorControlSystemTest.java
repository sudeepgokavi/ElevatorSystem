package test.com.elevatorsystem;

import java.util.ArrayList;

import main.com.elevatorsystem.impl.ElevatorControlSystemImpl;
import main.com.elevatorsystem.impl.ElevatorImpl;

public class ElevatorControlSystemTest {

	private ElevatorImpl elevator;
	private ElevatorControlSystemImpl ecs;
	private ArrayList<ElevatorImpl> elevators;

	public void initSystem() {
		ecs = new ElevatorControlSystemImpl(2, 10, "IT-Park");

		// Check the configuration
		System.out.println("Building Name - " + ecs.getBuilding().getBuildingName());
		System.out.println("No of Floors - " + ecs.getBuilding().getNoOfFloors());
		System.out.println("No of Elevators - " + ecs.getBuilding().getNoOfElevators());
	}

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

	public void callingFromElevator(int floor) {
		System.out.println("Floor Called from Elevator1 - " + floor);
		ecs.destination(0, floor);
		for (int i = 0; i < ecs.getBuilding().getNoOfFloors(); i++) {
			ecs.move();
		}
		elevators = ecs.getAllElevators();
		System.out.println(" Elevator - 1 reached - " + elevators.get(0).currentFloor());
	}

	public void elevatorToMultipleFloors(int floor1, int floor2){
		ecs.destination(0, floor1);
		ecs.destination(0, floor2);
		for (int i = 0; i < ecs.getBuilding().getNoOfFloors(); i++) {
			ecs.move();
			if(i == floor1 || i == floor2)
				System.out.println("Elevator Stopped Floor - "+i);
		}
		elevators = ecs.getAllElevators();
		System.out.println(" Elevator - 1 reached - " + elevators.get(0).currentFloor());
	}
	
	public static void main(String args[]) {
		ElevatorControlSystemTest ecsTest = new ElevatorControlSystemTest();
		ecsTest.initSystem();

		// ecsTest.callElevator(8);

		// ecsTest.call2Elevators(4, 8);

		//ecsTest.callingFromElevator(4);
		
		ecsTest.elevatorToMultipleFloors(4, 8);
	}

}
