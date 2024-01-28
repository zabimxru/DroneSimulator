package Katze.DroneSimulation.logic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ButtonOpenPopUp {
	public static void openPopUp(JButton popupButton) {
		popupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Handle button click by creating and showing a new JFrame
				showDroneTypeTable();
			}
		});
	}

	private static void showDroneTypeTable() {
		// Create a new JFrame
		JFrame tableFrame = new JFrame("Drone Type Information");
		// Set layout manager to BorderLayout
		tableFrame.setLayout(new BorderLayout());
		// Create column names
		String[] columnNames = { "Manufacturer", "Type Name", "Weight", "Max Speed", "Min Speed", "Battery Capacity",
				"Control Range", "Max Carriage" };

		// Sample data
		String[][] rowData = { { "ManufacturerData", "TypeNameData", "WeightData", "MaxSpeedData", "MinSpeedData",
				"BatteryCapacityData", "ControlRangeData", "MaxCarriageData" } };

		// Create a table model
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
		// Create a JTable with the model
		JTable droneInfoTable = new JTable(model);
		// Add the table to a JScrollPane
		JScrollPane scrollPane = new JScrollPane(droneInfoTable);
		// Add the ScrollPane to the frame
		tableFrame.add(scrollPane, BorderLayout.CENTER);

		// Set frame properties
		tableFrame.setSize(500, 300);
		tableFrame.setLocationRelativeTo(null);
		tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Make the frame visible
		tableFrame.setVisible(true);
	}
}
