package Katze.DroneSimulation.ui.pages;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Katze.DroneSimulation.data.ui.HomepageResultlistData;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import Katze.DroneSimulation.data.TestData;
import Katze.DroneSimulation.ui.SwingTools;

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

		this.setLayout(new BorderLayout(0, 10));
		this.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
		
		createTopContent();
		createMidContent();
		createBotContent();			

	}
	
	private void createTopContent() {
		JPanel panelTop = new JPanel();
		this.add(panelTop, BorderLayout.NORTH);
		
		JPanel titleContainer = SwingTools.createCenteredLabel("Drone Information");
		panelTop.add(titleContainer, BorderLayout.NORTH);
	}
	
	private void createMidContent() {
		JPanel panelMid = new JPanel();
		this.add(panelMid, BorderLayout.CENTER);
		panelMid.setBorder(BorderFactory.createLineBorder(Color.black));
		panelMid.setLayout(new BorderLayout(0, 10));

			JPanel panel = new JPanel();
			panelMid.add(panel, BorderLayout.NORTH);
			panel.setBorder(BorderFactory.createLineBorder(Color.blue));
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			
				JLabel labelTopLeft = new JLabel("Serial Nr.:");
				panel.add(labelTopLeft);
				
				panel.add(Box.createHorizontalStrut(50));
				
				JLabel serialNr = new JLabel(TestData.SERIAL_NUMBER);
				//serialNr.setForeground(Color.RED);
				panel.add(serialNr);
				Font fontSerialNr = new Font("Arial",Font.ITALIC, 15);
				serialNr.setFont(fontSerialNr);
				
				
				panel.add(Box.createHorizontalStrut(200));
				
				JLabel labelTopRight = new JLabel("Drone Type:");
				panel.add(labelTopRight);
				
				panel.add(Box.createHorizontalStrut(50));
				
				JLabel droneType = new JLabel(TestData.DRONE_TYPE);
				panel.add(droneType);
				Font fontDroneType = new Font("Arial",Font.ITALIC, 15);
				droneType.setFont(fontDroneType);
				
			
			JPanel panelFuerAkkordion = new JPanel();
			panelMid.add(panelFuerAkkordion, BorderLayout.CENTER);
			panelFuerAkkordion.setBorder(BorderFactory.createLineBorder(Color.blue));
	   
			
			
	}
	
	private void createBotContent() {
		
		JPanel panelBottom = new JPanel();
		this.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setBorder(BorderFactory.createLineBorder(Color.black));
		
			JPanel buttonPanel = new JPanel();
			panelBottom.add(buttonPanel);
				JButton button1 = new JButton("Back");
				buttonPanel.add(button1, BorderLayout.WEST);
				JButton button2 = new JButton("Dynamics");
				buttonPanel.add(button2, BorderLayout.SOUTH);
		
		

	}
}



