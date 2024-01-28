package Katze.DroneSimulation.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.ui.pages.PageDroneDynamics;
import Katze.DroneSimulation.ui.pages.PageDroneInfo;
import Katze.DroneSimulation.ui.pages.PageHome;


public class MainWindow {
	private final JFrame frame;
	private final PageHome pageHome;
	private final PageDroneInfo pageDroneInfo;
	private final PageDroneDynamics pageDroneDynamics;

	
	public MainWindow(String title, int width, int height) {
		this.frame = new JFrame();
		this.frame.setTitle(title);
		this.frame.setSize(width, height);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.pageHome = new PageHome(this);

		
		this.pageDroneInfo = new PageDroneInfo(this); //gibt Instanz von MainWindow an PageDroneInfo

		
		this.pageDroneDynamics = new PageDroneDynamics(this);

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
			this.frame.setContentPane(this.pageHome);
			this.frame.revalidate();
		});
	}
	
	public void goToDroneInfo(Drone drone) {
		SwingUtilities.invokeLater(() -> {
			this.pageDroneInfo.setDroneData(drone);
			this.frame.setContentPane(this.pageDroneInfo);
			this.frame.revalidate();
		});
	}
	
	public void goToDroneDynamic() {
		SwingUtilities.invokeLater(() -> {
			this.frame.setContentPane(this.pageDroneDynamics);
			this.frame.revalidate();
		});
	}
	
}
