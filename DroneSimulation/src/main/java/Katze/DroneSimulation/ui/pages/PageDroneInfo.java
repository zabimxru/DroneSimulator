package Katze.DroneSimulation.ui.pages;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Katze.DroneSimulation.data.TestData;
import Katze.DroneSimulation.ui.MainWindow;
import Katze.DroneSimulation.ui.SwingTools;

public class PageDroneInfo extends JPanel {
	
	public PageDroneInfo() {
		this.setLayout(new BorderLayout(0, 10));
		this.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
		createTopContent();
		createMidContent();
		createBotContent();	
	}
	
	private void showDroneTypeTable() {
		//Create a new JFrame
		JFrame tableFrame = new JFrame("Drone Type Information");
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
		
		//Set frame properties
		tableFrame.setSize(500, 300);
		tableFrame.setLocationRelativeTo(null);
		tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Make the frame visible
		tableFrame.setVisible(true);
	}
	
	private void createTopContent() {
		JPanel panelTop = new JPanel();
		this.add(panelTop, BorderLayout.NORTH);
		
		JPanel titleContainer = SwingTools.createCenteredLabel("Drone Information");
		panelTop.add(titleContainer, BorderLayout.NORTH);
	}
	
	private void createMidContent() {
		
		//Allgemeines Panel panelMid erzeugen
		JPanel panelMid = new JPanel();
		this.add(panelMid, BorderLayout.CENTER);
		panelMid.setLayout(new BorderLayout(0, 10));

			//panel in panelMid oben erzeugen mit Boxlayout
			JPanel boxPanel = new JPanel();
			panelMid.add(boxPanel, BorderLayout.NORTH);
			boxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.X_AXIS));
			
				//Label links in panel für Seriennummmer
				JLabel labelTopLeft = new JLabel("Serial Nr.:");
				boxPanel.add(labelTopLeft);
				
				//Abstand zur SeriennummerLabel
				boxPanel.add(Box.createHorizontalStrut(50));
				
				//Label rechts neben "Serial Nr" für eigentliche Seriennummer
				JLabel serialNr = new JLabel(TestData.SERIAL_NUMBER);
				//serialNr.setForeground(Color.RED);
				boxPanel.add(serialNr);
				Font fontSerialNr = new Font("Arial",Font.ITALIC, 15);
				serialNr.setFont(fontSerialNr);
			
				
				//Abstand erzeugen
				boxPanel.add(Box.createHorizontalStrut(100));
				
				
				//Label links in panel für "DroneType"
				JLabel labelTopRight = new JLabel("Drone Type:");
				boxPanel.add(labelTopRight);
				//Abstand
				boxPanel.add(Box.createHorizontalStrut(50));
				//Label für eigentlichen Drohnentyp
				JLabel droneType = new JLabel(TestData.DRONE_TYPE);
				boxPanel.add(droneType);
				Font fontDroneType = new Font("Arial",Font.ITALIC, 15);
				droneType.setFont(fontDroneType);
				
				//Abstand
				boxPanel.add(Box.createHorizontalStrut(25));
				
				//PopUp
				JButton popupButton = new JButton("i");
				popupButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//Handle button click by creating and showing a new JFrame
						showDroneTypeTable();
					}
				});
				boxPanel.add(popupButton);

				
			JPanel panelFuerAkkordion = new JPanel();
			panelMid.add(panelFuerAkkordion, BorderLayout.CENTER);
			
			String[] columns = {"Created", "Carriage Weight", "Carriage Type"};
			Object[][] data = {
					{new Date(), new Float(11.01), "miaumiau"},

			};
			JTable table = new JTable(data, columns);
			table.setRowSelectionAllowed(true);
			JScrollPane scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(550, 38));
			panelFuerAkkordion.add(scroll);
	   
			
			
	}
	
	private void createBotContent() {
		
		JPanel panelBottom = new JPanel();
		this.add(panelBottom, BorderLayout.SOUTH);
		
		
			JPanel buttonPanel = new JPanel();
			panelBottom.add(buttonPanel);
			JButton button1 = new JButton("Back");
			
			button1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//Handle button click by creating and showing a new JFrame
					goToHome();
					
				}
			});
			
			buttonPanel.add(button1, BorderLayout.WEST);
			
			
			JButton button2 = new JButton("Dynamics");
			buttonPanel.add(button2, BorderLayout.SOUTH);
			
			button2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//Handle button click by creating and showing a new JFrame
					goToDroneDynamic();
					
				}
			});
			
		
		

	}
	
}





