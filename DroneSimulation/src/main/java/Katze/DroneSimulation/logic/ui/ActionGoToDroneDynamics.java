package Katze.DroneSimulation.logic.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Katze.DroneSimulation.ui.MainWindow;

public class ActionGoToDroneDynamics implements ActionListener {
	private final MainWindow window;
	
	public ActionGoToDroneDynamics(MainWindow window) {
		this.window = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		window.goToDroneDynamic();
		
	}
	
}
