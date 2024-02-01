package Katze.DroneSimulation.ui.popups;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Katze.DroneSimulation.data.api.DroneType;

public class PopUpDroneTypeInfo extends JFrame {
	public PopUpDroneTypeInfo(DroneType droneType) {
		

		this.setTitle("Drone Type Information");

		// Set layout manager to BorderLayout
		this.setLayout(new BorderLayout());
		// Create column names
		String[] columnNames = { "Manufacturer", "Type Name", "Weight", "Max Speed", "Battery Capacity",
				"Control Range", "Max Carriage" };

		// Sample data
		Object[][] rowData = { { droneType.getManufacturer(), droneType.getTypename(), droneType.getWeight(),
				droneType.getMaxSpeed(), droneType.getBatteryCap(),
				droneType.getControlRange(), droneType.getMaxCarriage()

				} };

		// Create a table model
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
		// Create a JTable with the model
		JTable droneInfoTable = new JTable(model);
		// Add the table to a JScrollPane
		JScrollPane scrollPane = new JScrollPane(droneInfoTable);
		// Add the ScrollPane to the frame
		this.add(scrollPane, BorderLayout.CENTER);

		// Set frame properties
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Make the frame visible
		this.setVisible(true);
	}
}
