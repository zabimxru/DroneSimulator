package Katze.DroneSimulation.ui.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat; // Import SimpleDateFormat for formatting Date to String
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import Katze.DroneSimulation.data.TestData;
import Katze.DroneSimulation.data.api.DroneDynamic;
import Katze.DroneSimulation.data.ui.DroneDynamicsResultListData;
import Katze.DroneSimulation.logic.DroneMapPanel;
import Katze.DroneSimulation.logic.ui.ActionGoToHome;
import Katze.DroneSimulation.ui.ColorTheme;
import Katze.DroneSimulation.ui.MainWindow;

public class PageDroneDynamics extends JPanel {
	private class ResultListCellRenderer implements ListCellRenderer<DroneDynamicsResultListData> {
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
			this.labelSpeed = new JLabel();
			this.labelAlignmentRoll = new JLabel();
			this.labelControlRange = new JLabel();
			this.labelLongitude = new JLabel();
			this.labelLatitude = new JLabel();
			this.labelBatteryStatus = new JLabel();
			this.labelStatus = new JLabel();

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
		public Component getListCellRendererComponent(JList<? extends DroneDynamicsResultListData> list,
				DroneDynamicsResultListData value, int index, boolean isSelected, boolean cellHasFocus) {
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
			labelAlignmentRoll.setText(String.valueOf(value.getAlignmentRoll()));
			labelControlRange.setText(String.valueOf(value.getControlRage()));
			labelLongitude.setText(String.valueOf(value.getLongitude()));
			labelLatitude.setText(String.valueOf(value.getLatitude()));
			labelBatteryStatus.setText(String.valueOf(value.getBatteryStatus()));
			labelStatus.setText(String.valueOf(value.getStatus()));

			return panelRow;
		}
	}

	public PageDroneDynamics(MainWindow window) {
		setLayout(new BorderLayout(0, 10));

		// Der obere Panel für den Titel
		JPanel topPanel = new JPanel(new BorderLayout());
		JTextPane homeTitle = new JTextPane();
		homeTitle.setText("Drone Dynamics");
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		homeTitle.setParagraphAttributes(center, true);
		topPanel.add(homeTitle, BorderLayout.NORTH);
		add(topPanel, BorderLayout.NORTH);

		// Das Panel im Zentrum für die Suchleiste und der Liste an Ergebnissen
		JPanel centerPanel = new JPanel(new BorderLayout());
		JTextField searchBar = new JTextField();
		centerPanel.add(searchBar, BorderLayout.NORTH);

		DefaultListModel<DroneDynamicsResultListData> listData = new DefaultListModel<>();
		listData.addAll(List.of(TestData.DRONEDYNAMICS_DATA));

		JList<DroneDynamicsResultListData> resultList = new JList<>();
		resultList.setModel(listData);
		resultList.setCellRenderer(new ResultListCellRenderer());
		centerPanel.add(new JScrollPane(resultList), BorderLayout.CENTER);

		add(centerPanel, BorderLayout.CENTER);

		// Der untere Panel für die Knöpfe
		JPanel bottomPanel = new JPanel();
		JButton DroneInfo = new JButton("Zurück zur DroneInfo Seite");
		JButton ViewMap = new JButton("View Drone Route");
		JButton Homepage = new JButton("Zurück zur Homepage");

		ViewMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create a new map frame for the map
				JFrame mapFrame = new JFrame("Drone Route Map");
				mapFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

				// Create a new DroneMapPanel
				DroneMapPanel mapPanel = new DroneMapPanel();
				mapFrame.getContentPane().add(mapPanel);

				// Iterate over the drone dynamics data and add waypoints to the map
				DefaultListModel<DroneDynamicsResultListData> model = (DefaultListModel<DroneDynamicsResultListData>) resultList
						.getModel();
				for (int i = 0; i < model.getSize(); i++) {
					DroneDynamicsResultListData data = model.getElementAt(i);
					mapPanel.setDroneLocation(data.getLatitude(), data.getLongitude());
				}

				// Set frame properties
				mapFrame.setSize(800, 600);
				mapFrame.setLocationRelativeTo(null);
				mapFrame.setVisible(true);
			}
		});

		Homepage.addActionListener(new ActionGoToHome(window));


		bottomPanel.add(DroneInfo);
		bottomPanel.add(ViewMap);
		bottomPanel.add(Homepage);
		add(bottomPanel, BorderLayout.SOUTH);
	}

}
