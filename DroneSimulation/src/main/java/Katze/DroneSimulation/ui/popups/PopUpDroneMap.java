package Katze.DroneSimulation.ui.popups;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Katze.DroneSimulation.data.api.DroneDynamic;
import Katze.DroneSimulation.logic.DroneMapPanel;

public class PopUpDroneMap extends JFrame {
	
	public PopUpDroneMap(DroneDynamic droneDynamic) {
		DroneMapPanel panel = new DroneMapPanel();
		panel.setDroneLocation(droneDynamic.getLatitude(), droneDynamic.getLongitude());
		this.setTitle("Map");
		this.setContentPane(panel);
		// Set frame properties
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Make the frame visible
		this.setVisible(true);
	}
}
