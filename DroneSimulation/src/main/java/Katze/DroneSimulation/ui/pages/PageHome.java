package Katze.DroneSimulation.ui.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import Katze.DroneSimulation.data.api.Drone;
import Katze.DroneSimulation.data.api.DroneDynamic;
import Katze.DroneSimulation.data.ui.HomepageResultlistData;
import Katze.DroneSimulation.logic.APIDataHandler;
import Katze.DroneSimulation.ui.ColorTheme;
import Katze.DroneSimulation.ui.MainWindow;
import Katze.DroneSimulation.ui.SwingTools;
import Katze.DroneSimulation.ui.elements.SearchResultElementHome;

public class PageHome extends JPanel {
	private static final int RESULTLIST_ROW_BORDER_SIZE = 6;

	private class ResultlistCellRenderer implements ListCellRenderer<HomepageResultlistData> {
		private final JPanel panelRow;
		private final JLabel labelDronetype;
		private final JLabel labelSerialnumber;

		public ResultlistCellRenderer() {
			this.panelRow = new JPanel();
			this.panelRow.setLayout(new GridLayout(1, 2));

			this.labelDronetype = new JLabel();
			this.labelDronetype.setBorder(BorderFactory.createEmptyBorder(RESULTLIST_ROW_BORDER_SIZE,
					RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE));
			this.panelRow.add(labelDronetype);

			this.labelSerialnumber = new JLabel();
			this.labelSerialnumber.setBorder(BorderFactory.createEmptyBorder(RESULTLIST_ROW_BORDER_SIZE,
					RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE, RESULTLIST_ROW_BORDER_SIZE));
			this.panelRow.add(labelSerialnumber);

		}

		@Override
		public Component getListCellRendererComponent(JList<? extends HomepageResultlistData> list,
				HomepageResultlistData value, int index, boolean isSelected, boolean cellHasFocus) {

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

			labelDronetype.setText(value.getDronetype());

			labelSerialnumber.setText(value.getSerialnumber());

			return panelRow;
		}

	}

	private final MainWindow window;

	public PageHome(MainWindow window) {

		this.window = window;

		// Abstand zwischen den Elementen (Searchbar, Resultlist: 10 Pixel vertikal
		this.setLayout(new BorderLayout(0, 10));
		// oben, links, unten, rechts die Grenzen
		this.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

		// Container mit Titel erstellen
		JPanel topContainer = SwingTools.createCenteredLabel("Home");
		this.add(topContainer, BorderLayout.NORTH);
		
		//Container mit Searchbar und Tabelle erstellen
		JPanel searchResultContainer = new SearchResultElementHome<>(HomepageResultlistData.TABLE_HEADERS,
				APIDataHandler::getResultlistData, this::onRowClicked);
		this.add(searchResultContainer, BorderLayout.CENTER);
	}

	private void onRowClicked(HomepageResultlistData data) {
		Drone droneInfo = APIDataHandler.getDroneBySerialNr(data.getSerialnumber());
		DroneDynamic droneDynamic;
		window.goToDroneInfo(droneInfo);
	}
}