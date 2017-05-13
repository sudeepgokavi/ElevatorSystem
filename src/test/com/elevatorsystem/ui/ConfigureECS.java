package test.com.elevatorsystem.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import main.com.elevatorsystem.impl.ElevatorControlSystemImpl;
import main.com.elevatorsystem.impl.ElevatorImpl;

public class ConfigureECS extends JPanel implements ActionListener {

	private ElevatorControlSystemImpl ecs;

	private JTextField buildingName, noOfFloors, noOfLifts;
	private JLabel buildingNameLabel, noOfFloorsLabel, noOfLiftsLabel;
	private JLabel dash;
	
	private JButton configureSystem, testElevators, configureElevators;
	private JPanel panel, p1;

	ArrayList<ElevatorImpl> elevators;
	private List<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();

	public ConfigureECS() {
		super(new BorderLayout());

		configureSystem = new JButton("Configure System");
		configureSystem.setActionCommand("CONFIGURE");
		configureSystem.addActionListener(this);

		testElevators = new JButton("Test Elevators");
		testElevators.setActionCommand("TEST");
		testElevators.addActionListener(this);
		testElevators.setEnabled(false);


		configureElevators = new JButton("Configure Elevators");
		configureElevators.setActionCommand("CONFIG_ELEVATORS");
		configureElevators.addActionListener(this);
		configureElevators.setEnabled(false);

		buildingNameLabel = new JLabel("Enter Building Name ");
		noOfFloorsLabel = new JLabel("Enter Floors ");
		noOfLiftsLabel = new JLabel("Enter Lifts ");

		buildingName = new JTextField(30);
		noOfFloors = new JTextField(3);
		noOfLifts = new JTextField(3);

		panel = new JPanel();
		panel.add(buildingNameLabel);
		panel.add(buildingName);
		panel.add(noOfFloorsLabel);
		panel.add(noOfFloors);
		panel.add(noOfLiftsLabel);
		panel.add(noOfLifts);

		panel.add(configureSystem);
		panel.add(testElevators);
		panel.add(configureElevators);

		p1 = new JPanel();
		p1.setLayout(new GridLayout(0, 1));
		add(panel, BorderLayout.PAGE_START);
		add(new JScrollPane(p1), BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		dash = new JLabel(
				" --------------------------------------------------------------------------------------------------------------------");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("CONFIG_ELEVATORS")) {
			elevators = ecs.getAllElevators();
			p1.add(dash);
			for(JCheckBox checkBox : checkBoxes){
				if(checkBox.isSelected()){
					String[] tempStr = checkBox.getText().split("-->");
					for(ElevatorImpl e : elevators){
						if(e.getElevatorName().equals(tempStr[0])){
							e.getAccessibleFloors().add(Integer.parseInt(tempStr[1]));
						}
						
					}
				}
			}

			for(ElevatorImpl e : elevators){
				//System.out.println(e.getAccessibleFloors());
				JLabel _l = new JLabel(e.getElevatorName() + " is accessible only on the following floors "+e.getAccessibleFloors());
				p1.add(_l);
			}
			p1.revalidate();
			p1.repaint();
		}
		
		if (arg0.getActionCommand().equals("CONFIGURE")) {
			String tempBuildingName = buildingName.getText();
			Integer totalFloors = Integer.parseInt(noOfFloors.getText());
			Integer totalElevators = Integer.parseInt(noOfLifts.getText());

			ecs = new ElevatorControlSystemImpl(totalElevators, totalFloors, tempBuildingName);

			JLabel l1 = new JLabel(
					" Elevator Configuration System configred for - " + ecs.getBuilding().getBuildingName());
			JLabel l2 = new JLabel(
					" Floors in " + ecs.getBuilding().getBuildingName() + " are " + ecs.getBuilding().getNoOfFloors());
			JLabel l3 = new JLabel(" Elevators To be configured for " + ecs.getBuilding().getBuildingName() + " are "
					+ ecs.getBuilding().getNoOfElevators());

			JLabel l4 = new JLabel(
					" --------------------------------------------------------------------------------------------------------------------");
			p1.add(l1);
			p1.add(l2);
			p1.add(l3);
			p1.add(l4);

			elevators = ecs.getAllElevators();
			for (ElevatorImpl e : elevators) {
				JLabel _l = new JLabel("Elevator Configured - " + e.getElevatorName());
				p1.add(_l);
			}
			JLabel l5 = new JLabel(
					" --------------------------------------------------------------------------------------------------------------------");
			p1.add(l5);

			p1.revalidate();
			p1.repaint();

			testElevators.setEnabled(true);
		}

		if (arg0.getActionCommand().equals("TEST")) {
			JLabel l6 = new JLabel(" Testing the Lifts Going Up ----");
			p1.add(l6);

			elevators = ecs.getAllElevators();
			for (ElevatorImpl e : elevators) {
				for (int i = e.currentFloor(); i <= 3; i++) {
					if (e.currentFloor() == 2) {
						JLabel _l2 = new JLabel(e.getElevatorName() + " @ " + e.currentFloor() + " Stopped ");
						p1.add(_l2);
						break;
					}else{
						JLabel _l1 = new JLabel(e.getElevatorName() + " - " + i);
						p1.add(_l1);
					}
					e.moveUp();
				}
			}

			JLabel l8 = new JLabel(
					" --------------------------------------------------------------------------------------------------------------------");
			p1.add(l8);

			JLabel l7 = new JLabel(" Testing the Lifts Going Down ----");
			p1.add(l7);

			elevators = ecs.getAllElevators();
			for (ElevatorImpl e : elevators) {
				for (int i = e.currentFloor(); i < 2; i++) {
					e.moveUp();
				}

				for (int i = e.currentFloor(); i >= 0; i--) {
					if (e.currentFloor() == 0) {
						JLabel _l4 = new JLabel(e.getElevatorName() + " @ " + e.currentFloor() + " Stopped ");
						p1.add(_l4);
						break;
					}else{
						JLabel _l3 = new JLabel(e.getElevatorName() + " - " + i);
						p1.add(_l3);
					}
					e.moveDown();
				}

			}

			// Configuring the Lifts for accessiblity
			p1.add(dash);
			
			elevators = ecs.getAllElevators();
			
			for(ElevatorImpl e : elevators){
			//for(int elevatorCounter=0;elevatorCounter<totalElevators;elevatorCounter++){
				JLabel _l = new JLabel(" Select Floors ");
				p1.add(_l);
				for(int floorCounter=0; floorCounter<ecs.getBuilding().getNoOfFloors();floorCounter++){
					JCheckBox _cb = new JCheckBox(e.getElevatorName()+"-->"+floorCounter);
					checkBoxes.add(_cb);
					p1.add(_cb);
				}
			}
			
			p1.revalidate();
			p1.repaint();
			
			configureElevators.setEnabled(true);
		}
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Elevator Control System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new ConfigureECS();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setSize(1200, 500);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
