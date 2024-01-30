package Katze.DroneSimulation.logic.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.data.api.DroneDynamic;
import Katze.DroneSimulation.ui.MainWindow;

public class ActionGoToDroneDynamics implements ActionListener {
	private final MainWindow window;
	private final Drone drone;
	
	public ActionGoToDroneDynamics(MainWindow window, Drone drone) {
		this.window = window;
		this.drone = drone;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		window.goToDroneDynamic(drone);
		
	}
	
}
