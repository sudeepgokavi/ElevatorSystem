# ElevatorSystem

- ConfigureECS is a Swing UI program. Please run this class to configure the Elevator System.
- Use the other Test Clases for testing the specific implementation.

### Use Cases

- Configure Building 
  - Name of the Building
  - No of floors 
  - No of elevators installed.
  
- Configure Elevator
  - Name of the Elevator
  - Floor Accessible by each elevator
  
- Configure Elevator Control System
  - Configure Building
  - Initialize all the Elevators
  - Test all the Elevator Movements
  - Configure each elevator to be accessible on specific floors
  
  
## Running the ConfigureECS UI Program.

- After the JFrame is loaded, enter Building Name, Floors and No of Lifts and click on Configure System.
  - This will Initialize the Building and Elevator Objects and print few details in the below Panel.
  - Test Elevators - Button will be enabled.
  
- Click on Test Elevator Button.
  - Each of the elevators will be tested for UP and Down moments, 
  - All the elevators would be moved from their current location to specific floor (2nd)
  - All the elevators then would be moved from their current location (2nd) to Level - 0
  - Please refer the Panel below for logs
  - In addition to the logs, the Panel will also have Checkboxes (one for each floor) against each elevator.
  - Check these checkboxes to configure the elevators for specific floor access.
 
- Click Configure Elevators Button.
  - Accessiblity of each Elevator would be displayed in the Panel (towards the end)
  



