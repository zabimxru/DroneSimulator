package Katze.DroneSimulation.ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.ui.pages.PageDroneDynamics;
import Katze.DroneSimulation.ui.pages.PageDroneInfo;
import Katze.DroneSimulation.ui.pages.PageHome;


public class MainWindow {
	private final JFrame frame;
//	private final PageHome pageHome;
//	private final PageDroneInfo pageDroneInfo;
//	private final PageDroneDynamics pageDroneDynamics;

	
	public MainWindow(String title, int width, int height) {
		this.frame = new JFrame();
		this.frame.setTitle(title);
		this.frame.setSize(width, height);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
//		this.pageHome = new PageHome(this);
//
//		
//		this.pageDroneInfo = new PageDroneInfo(this); //gibt Instanz von MainWindow an PageDroneInfo
//
//		
//		this.pageDroneDynamics = new PageDroneDynamics(this);

	}
	
	public void show() {
		this.frame.setVisible(true);
	}
	
	public void hide() {
		this.frame.setVisible(false);
	}
	
	public void goToHome() {
		//Lambda Ausdruck der die Methode in dem Functional Interface
		//führt die methode aus, wenn Swing feritg ist mit "Zeichnen"
		SwingUtilities.invokeLater(() -> {
			PageHome pageHome = new PageHome(this);
			this.frame.setContentPane(pageHome);
			this.frame.revalidate();
		});
	}
	
	public void goToDroneInfo(Drone drone) {
		SwingUtilities.invokeLater(() -> {
			PageDroneInfo pageDroneInfo = new PageDroneInfo(this, drone);
			this.frame.setContentPane(pageDroneInfo);
			this.frame.revalidate();
		});
	}
	
	public void goToDroneDynamic() {
		SwingUtilities.invokeLater(() -> {
			PageDroneDynamics pageDroneDynamics = new PageDroneDynamics(this);
			this.frame.setContentPane(pageDroneDynamics);
			this.frame.revalidate();
		});
	}
	

	
}
