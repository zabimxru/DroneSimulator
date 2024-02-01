package Katze.DroneSimulation.ui.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat; // Import SimpleDateFormat for formatting Date to String
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.WindowConstants;

import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.data.api.DroneDynamic;
import Katze.DroneSimulation.data.api.DroneType;
import Katze.DroneSimulation.data.ui.HomepageResultlistData;
import Katze.DroneSimulation.logic.APIDataHandler;
import Katze.DroneSimulation.logic.DroneMapPanel;
import Katze.DroneSimulation.logic.ui.ActionGoToDroneInfo;
import Katze.DroneSimulation.logic.ui.ActionGoToHome;
import Katze.DroneSimulation.ui.ColorTheme;
import Katze.DroneSimulation.ui.MainWindow;
import Katze.DroneSimulation.ui.SwingTools;
import Katze.DroneSimulation.ui.elements.SearchResultElementDroneDyn;
import Katze.DroneSimulation.ui.elements.SearchResultElementHome;
import Katze.DroneSimulation.ui.popups.PopUpDroneMap;
import Katze.DroneSimulation.ui.popups.PopUpDroneTypeInfo;

public class PageDroneDynamics extends JPanel {
	private static final int RESULTLIST_ROW_BORDER_SIZE = 6;

	private class ResultListCellRenderer implements ListCellRenderer<DroneDynamic> {
		private final JPanel panelRow;
		private final JLabel labelTimestamp;
		private final JLabel labelSpeed;
		private final JLabel labelAlignmentRoll;
		private final JLabel labelControlRange;
		private final JLabel labelLongitude;
		private final JLabel labelLatitude;
		private final JLabel labelBatteryStatus;
		private final JLabel labelStatus;

		public ResultListCellRenderer() {
			this.panelRow = new JPanel();
			this.panelRow.setLayout(new GridLayout(1, 8));

			this.labelTimestamp = new JLabel();
			this.labelTimestamp.setBorder(BorderFactory.createEmptyBorder(RESULTLIST_ROW_BORDER_SIZE,
					RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE));

			this.labelSpeed = new JLabel();
			this.labelSpeed.setBorder(BorderFactory.createEmptyBorder(RESULTLIST_ROW_BORDER_SIZE,
					RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE));

			this.labelAlignmentRoll = new JLabel();
			this.labelAlignmentRoll.setBorder(BorderFactory.createEmptyBorder(RESULTLIST_ROW_BORDER_SIZE,
					RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE));

			this.labelControlRange = new JLabel();
			this.labelControlRange.setBorder(BorderFactory.createEmptyBorder(RESULTLIST_ROW_BORDER_SIZE,
					RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE));

			this.labelLongitude = new JLabel();
			this.labelLongitude.setBorder(BorderFactory.createEmptyBorder(RESULTLIST_ROW_BORDER_SIZE,
					RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE));

			this.labelLatitude = new JLabel();
			this.labelLatitude.setBorder(BorderFactory.createEmptyBorder(RESULTLIST_ROW_BORDER_SIZE,
					RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE));

			this.labelBatteryStatus = new JLabel();
			this.labelBatteryStatus.setBorder(BorderFactory.createEmptyBorder(RESULTLIST_ROW_BORDER_SIZE,
					RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE));

			this.labelStatus = new JLabel();
			this.labelStatus.setBorder(BorderFactory.createEmptyBorder(RESULTLIST_ROW_BORDER_SIZE,
					RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE));

			this.panelRow.add(labelTimestamp);
			this.panelRow.add(labelSpeed);
			this.panelRow.add(labelAlignmentRoll);
			this.panelRow.add(labelControlRange);
			this.panelRow.add(labelLongitude);
			this.panelRow.add(labelLatitude);
			this.panelRow.add(labelBatteryStatus);
			this.panelRow.add(labelStatus);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends DroneDynamic> list, DroneDynamic value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if (isSelected) {
				this.panelRow.setBorder(BorderFactory.createLineBorder(ColorTheme.COLOR_PRIMARY.darker(), 2));
			} else {
				this.panelRow.setBorder(null);
			}

			if (index % 2 == 0) {
				this.panelRow.setBackground(ColorTheme.COLOR_PRIMARY);
			} else {
				this.panelRow.setBackground(Color.white);
			}

			// Format the timestamp to a readable String
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formattedTimestamp = dateFormat.format(value.getTimestamp());

			labelTimestamp.setText(formattedTimestamp);
			labelSpeed.setText(String.valueOf(value.getSpeed())); // Assuming getSpeed returns an int
			labelAlignmentRoll.setText(String.valueOf(value.getAlignRoll()));
			labelControlRange.setText(String.valueOf(value.getControlRange()));
			labelLongitude.setText(String.valueOf(value.getLongitude()));
			labelLatitude.setText(String.valueOf(value.getLatitude()));
			labelBatteryStatus.setText(String.valueOf(value.getBatteryStat()));
			labelStatus.setText(String.valueOf(value.getStat()));

			return panelRow;
		}
	}

	private final MainWindow window;
	private final Drone droneData;

	public PageDroneDynamics(MainWindow window, Drone droneData) {

		this.window = window;
		this.droneData = droneData;

		// Abstand zwischen den Elementen (Searchbar, Resultlist: 10 Pixel vertikal
		setLayout(new BorderLayout(0, 10));
		// oben, links, unten, rechts die Grenzen
		this.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

		// Container mit Titel erstellen
		JPanel topContainer = SwingTools.createCenteredLabel("Dynamics");
		this.add(topContainer, BorderLayout.NORTH);

		// Container mit Searchbar und Tabelle erstellen
		JPanel searchResultContainer = new SearchResultElementDroneDyn<>(droneData, DroneDynamic.TABLE_HEADERS,
				APIDataHandler::getDroneDynamicData, this::openMapPopUp);
		this.add(searchResultContainer, BorderLayout.CENTER);
		
		createBotContent();

	}
	
	private void createBotContent() {

		JPanel panelBottom = new JPanel();
		this.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel buttonPanel = new JPanel();
		panelBottom.add(buttonPanel);

		JButton buttonDroneInfo = new JButton("Back");
		buttonDroneInfo.addActionListener(new ActionGoToDroneInfo(window, droneData));
		buttonPanel.add(buttonDroneInfo, BorderLayout.WEST);

		JButton buttonHome = new JButton("Home");
		buttonHome.addActionListener(new ActionGoToHome(window));
		buttonPanel.add(buttonHome, BorderLayout.SOUTH);	
	}

	private void openMapPopUp(DroneDynamic droneDyn) {
		new PopUpDroneMap(droneDyn);
	}
	
}
