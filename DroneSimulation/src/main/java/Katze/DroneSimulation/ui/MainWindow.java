package Katze.DroneSimulation.ui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Katze.DroneSimulation.ui.pages.PageDroneDynamics;
import Katze.DroneSimulation.ui.pages.PageDroneInfo;
import Katze.DroneSimulation.ui.pages.PageHome;

//
public class MainWindow {
	private final JFrame frame;
	private final JPanel pageHome;
	private final JPanel pageDroneInfo;
	private final JPanel pageDroneDynamics;

	
	public MainWindow(String title, int width, int height) {
		this.frame = new JFrame();
		this.frame.setTitle(title);
		this.frame.setSize(width, height);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.pageHome = new PageHome();

		
		this.pageDroneInfo = new PageDroneInfo();

		
		this.pageDroneDynamics = new PageDroneDynamics();

	}
	
	public void show() {
		this.frame.setVisible(true);
	}
	
	public void hide() {
		this.frame.setVisible(false);
	}
	
	public void goToHome() {
		this.frame.setContentPane(this.pageHome);
	}
	
	public void goToDroneInfo() {
		this.frame.setContentPane(this.pageDroneInfo);
	}
	
	public void goToDroneDynamic() {
		this.frame.setContentPane(this.pageDroneDynamics);
	}
	
}
