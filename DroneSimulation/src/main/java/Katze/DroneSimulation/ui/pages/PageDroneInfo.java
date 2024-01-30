package Katze.DroneSimulation.ui.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.data.api.DroneDynamic;
import Katze.DroneSimulation.data.api.DroneType;
import Katze.DroneSimulation.logic.APIDataHandler;
import Katze.DroneSimulation.logic.ui.ActionGoToDroneDynamics;
import Katze.DroneSimulation.logic.ui.ActionGoToHome;
import Katze.DroneSimulation.ui.MainWindow;
import Katze.DroneSimulation.ui.SwingTools;
import Katze.DroneSimulation.ui.popups.PopUpDroneTypeInfo;

public class PageDroneInfo extends JPanel {

	private final MainWindow window;
	private final Drone droneData;


	public PageDroneInfo(MainWindow window, Drone droneData) { // wir wollen hier MainWindow haben weil der
																// Actionlistener Mainwindow braucht um die jw. Seite
																// aufzurufen
		this.window = window;
		this.droneData = droneData;
	
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

		// Allgemeines Panel panelMid erzeugen
		JPanel panelMid = new JPanel();
		this.add(panelMid, BorderLayout.CENTER);
		panelMid.setLayout(new BorderLayout(0, 10));

		// panel fpr SerialNr und DroneType Information in panelMid oben erzeugen mit
		// Boxlayout
		JPanel boxPanel = new JPanel();
		panelMid.add(boxPanel, BorderLayout.NORTH);
		boxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.X_AXIS));

		// Label links in panel für Seriennummmer
		JLabel labelTopLeft = new JLabel("Serial Nr.:");
		boxPanel.add(labelTopLeft);

		// Abstand zur SeriennummerLabel
		boxPanel.add(Box.createHorizontalStrut(50));

		// Label rechts neben "Serial Nr" für eigentliche Seriennummer
		JLabel serialNr = new JLabel(droneData.getSerialnumber());
		// serialNr.setForeground(Color.RED);
		boxPanel.add(serialNr);
		Font fontSerialNr = new Font("Arial", Font.ITALIC, 15);
		serialNr.setFont(fontSerialNr);

		// Abstand erzeugen
		boxPanel.add(Box.createHorizontalStrut(100));

		// Label links in panel für "DroneType"
		JLabel labelTopRight = new JLabel("Drone Type:");
		boxPanel.add(labelTopRight);
		// Abstand
		boxPanel.add(Box.createHorizontalStrut(50));
		// Label für eigentlichen Drohnentyp
		JLabel labelDroneType = new JLabel(droneData.getDronetype());
		boxPanel.add(labelDroneType);
		Font fontDroneType = new Font("Arial", Font.ITALIC, 15);
		labelDroneType.setFont(fontDroneType);

		// Abstand
		boxPanel.add(Box.createHorizontalStrut(25));

		// PopUp
		JButton popupButton = new JButton("i");
		popupButton.addActionListener(e -> {
			DroneType droneType = APIDataHandler.getTypeFromDrone(droneData);
			new PopUpDroneTypeInfo(droneType);
		});
		boxPanel.add(popupButton);

		// Panel erzeugen für die Drone Infos der jw. Seriennummer
		JPanel panelFuerTabelle = new JPanel();
		panelMid.add(panelFuerTabelle, BorderLayout.CENTER);

		String[] columns = { "Created", "Carriage Weight", "Carriage Type" };
		Object[][] data = { { droneData.getCreated(), droneData.getCarriageWeight(), droneData.getCarriageType() },

		};
		JTable table = new JTable(data, columns);
		table.setRowSelectionAllowed(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(550, 38));
		panelFuerTabelle.add(scroll);

	}

	private void createBotContent() {

		JPanel panelBottom = new JPanel();
		this.add(panelBottom, BorderLayout.SOUTH);

		JPanel buttonPanel = new JPanel();
		panelBottom.add(buttonPanel);

		JButton buttonHome = new JButton("Back");
		buttonHome.addActionListener(new ActionGoToHome(window));
		buttonPanel.add(buttonHome, BorderLayout.WEST);

		JButton buttonDyn = new JButton("Dynamics");
		buttonDyn.addActionListener(new ActionGoToDroneDynamics(window, droneData));
		buttonPanel.add(buttonDyn, BorderLayout.SOUTH);
	}

}
