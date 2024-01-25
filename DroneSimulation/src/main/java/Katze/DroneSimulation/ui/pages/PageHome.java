package Katze.DroneSimulation.ui.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import Katze.DroneSimulation.data.TestData;
import Katze.DroneSimulation.data.ui.HomepageResultlistData;
import Katze.DroneSimulation.ui.ColorTheme;
import Katze.DroneSimulation.ui.SwingTools;

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

	public PageHome() {

		// Abstand zwischen den Elementen (Searchbar, Resultlist: 10 Pixel vertikal
		this.setLayout(new BorderLayout(0, 10));

		// oben, links, unten, rechts die Grenzen
		this.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
		
		//Container mit Titel und Suchleiste erstellen
		JPanel topContainer = createTopContainer();
		this.add(topContainer, BorderLayout.NORTH);
		
		JComponent resultList = createResultList();

		// Liste hinzufügen
		this.add(resultList, BorderLayout.CENTER);//Später
	}
	
	private JPanel createTopContainer() {

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout(0, 10)); //Parameter geben Pixel an, wie groß der Abstand zwischen Kindelementen sein soll
		
		//Erstellt zentriertes Label mit dem Titel
		JPanel titleContainer = SwingTools.createCenteredLabel("Home");
		container.add(titleContainer, BorderLayout.NORTH);

		// Textfeld Searchbar
		JTextField searchBar = new JTextField();

		// Platziert Searchbar oben
		container.add(searchBar, BorderLayout.SOUTH);
		
		return container;
	}

	private JComponent createResultList() {
		DefaultListModel<HomepageResultlistData> listData = new DefaultListModel<>();
		// Alle Testdaten in die Resultliste testweise einfügen
		listData.addAll(List.of(TestData.HOMEPAGERESULTLISTDATA_DATA));

		JList<HomepageResultlistData> resultList = new JList<>();
		// Daten aus listData verwenden
		resultList.setModel(listData);
		// Beeinflusst Zeilenaussehen s. in Klasse ResultListCellRenderer oben
		resultList.setCellRenderer(new ResultlistCellRenderer());
		
//		JScrollPane keks = new JScrollPane(resultList);
//		return keks;
		return new JScrollPane(resultList);
	}
}