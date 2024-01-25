package Katze.DroneSimulation.ui.pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Katze.DroneSimulation.data.ui.HomepageResultlistData;

public class PageDroneInfo extends JPanel {
	
	private static final String DRONE_INFO_TEXT = "Drone Info";
	private static final String POPUP_BUTTON_LABEL = "i";
	public PageDroneInfo() {
		//Create JLable
		JLabel droneInfo = new JLabel(DRONE_INFO_TEXT);
		droneInfo.setHorizontalAlignment(SwingConstants.CENTER);
				
		//Create JButton
		JButton popupButton = new JButton(POPUP_BUTTON_LABEL);
		
		//Set layout manager to BorderLayout
		setLayout(new BorderLayout());
		
		//Add JLabel to the center
		add(droneInfo, BorderLayout.CENTER);
		
		//Add ActionListener to handle button click
		popupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Handle button click by creating and showing a new JFrame
				showDroneInfoTable();
			}
		});
		
		//Add JButton to the right corner
		add(popupButton, BorderLayout.EAST);
	}
	
	public PageDroneInfo(HomepageResultlistData selectedData) {
		JLabel label = new JLabel("Selected Drone Type: " + selectedData.getDronetype());
		add(label);
	}
	
	private void showDroneInfoTable() {
		//Create a new JFrame
		JFrame tableFrame = new JFrame("Drone Information Table");
		//Set layout manager to BorderLayout
		tableFrame.setLayout(new BorderLayout());
		//Create column names
		String[] columnNames = {"Manufacturer", "Type Name", "Weight", "Max Speed",
                "Min Speed", "Battery Capacity", "Control Range", "Max Carriage"};
		
		//Sample data
		String[][] rowData = { 
				{"ManufacturerData", "TypeNameData", "WeightData", "MaxSpeedData",
                "MinSpeedData", "BatteryCapacityData", "ControlRangeData", "MaxCarriageData"}
		};
		
		//Create a table model
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
		//Create a JTable with the model
		JTable droneInfoTable = new JTable(model);
		//Add the table to a JScrollPane
		JScrollPane scrollPane = new JScrollPane(droneInfoTable);
		//Add the ScrollPane to the frame
		tableFrame.add(scrollPane, BorderLayout.CENTER);
		
		//Set frame poperties
		tableFrame.setSize(500, 300);
		tableFrame.setLocationRelativeTo(null);
		tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Make the frame visible
		tableFrame.setVisible(true);
	}
}
	

