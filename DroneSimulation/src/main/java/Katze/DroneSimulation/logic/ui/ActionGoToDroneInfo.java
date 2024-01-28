package Katze.DroneSimulation.logic.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.ui.MainWindow;

public class ActionGoToDroneInfo implements ActionListener {
	private final MainWindow window;
	private final Drone drone;
	
	public ActionGoToDroneInfo(MainWindow window, Drone drone) {
		this.window = window;
		this.drone = drone;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		window.goToDroneInfo(drone);
		
	}
	
}
