package Katze.DroneSimulation.logic;

public class DataRefresher {
	private final long timeBeforeUpdate;

	public DataRefresher(long timeBeforeUpdate) {
		this.timeBeforeUpdate = timeBeforeUpdate;
	}
	
	public void start() {
		Thread thread = new Thread(this::runRefreshLoop); //Methodenreferenz
		thread.start();
	}
	
	private void runRefreshLoop() {
		while(true) {
			try {
				Thread.sleep(timeBeforeUpdate);
			} catch (InterruptedException e) {
				break;
			}
			System.out.println("Refreshing Data.....miau");
			APIDataHandler.refreshData();
		}
	}
}
