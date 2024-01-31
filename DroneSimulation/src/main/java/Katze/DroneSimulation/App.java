package Katze.DroneSimulation;

import Katze.DroneSimulation.ui.MainWindow;

public class App {
	public static final String WINDOW_TITLE = "DroneSimulator";
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 600;
	
    public static void main( String[] args ) {
        MainWindow window = new MainWindow(WINDOW_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT);
        window.show();
        window.goToHome();        
    }
}
