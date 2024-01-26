package Katze.DroneSimulation.ui.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Katze.DroneSimulation.data.TestData;
import Katze.DroneSimulation.data.ui.HomepageResultlistData;
import Katze.DroneSimulation.logic.MergeSort;
import Katze.DroneSimulation.ui.ColorTheme;
import Katze.DroneSimulation.ui.MainWindow;
import Katze.DroneSimulation.ui.SwingTools;

public class PageHome extends JPanel  {
	private String attribute;
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
	
	private JList<HomepageResultlistData> resultList;
	private HomepageResultlistData[] originalData;
	
	public static HomepageResultlistData[] filterBySearchAttribute(HomepageResultlistData[] array, String searchBarInput) {
		return Arrays.stream(array)
				.filter(resultlistData -> resultlistData.getDronetype().equalsIgnoreCase(searchBarInput))
				.toArray(HomepageResultlistData[]::new);
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	public PageHome(String attribute) {
		this.attribute = attribute;
		this.originalData = TestData.HOMEPAGERESULTLISTDATA_DATA.clone();
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
		

		originalData = TestData.HOMEPAGERESULTLISTDATA_DATA.clone();
		//Button to Search activate filter Alg.
		JButton searchButton = new JButton("Search");
		JButton mergeButton = new JButton("Sort");
		JButton defaultButton = new JButton("Default view");
		
		//Add ActionListener to the defaultButton
				defaultButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Default view button clicked");
						try {
							//Restore the original data when the Default view button is clicked
							DefaultListModel<HomepageResultlistData> listModel = (DefaultListModel<HomepageResultlistData>) resultList.getModel();
							listModel.clear();
							listModel.addAll(Arrays.asList(originalData));
						} catch (ClassCastException ex) {
							ex.printStackTrace();				}
					}
				});
				
				//Add ActionListener to the searchButton
				searchButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Search button clicked");
						String searchBarInput = searchBar.getText();
						
						HomepageResultlistData[] filteredResults = filterBySearchAttribute(
							TestData.HOMEPAGERESULTLISTDATA_DATA, searchBarInput);
						
						DefaultListModel<HomepageResultlistData> listModel = (DefaultListModel<HomepageResultlistData>) resultList.getModel();
						listModel.clear();
						listModel.addAll(Arrays.asList(filteredResults));
						resultList.repaint();
					}
				});
				
				//Add ActionListener to the mergeButton
				mergeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Sort Button clicked");
						String searchBarInput = searchBar.getText();
						
						DefaultListModel<HomepageResultlistData> listModel = (DefaultListModel<HomepageResultlistData>) resultList.getModel();
						HomepageResultlistData[] dataArray = new HomepageResultlistData[listModel.size()];
						listModel.copyInto(dataArray);
						
						//Sort the data using MergeSort
						MergeSort<HomepageResultlistData> mergeSort = new MergeSort<>();
						mergeSort.mergeSort(dataArray, 0, dataArray.length -1);
						
						//Update the JList with the sorted data
						listModel.clear();
						listModel.addAll(Arrays.asList(dataArray));
						resultList.repaint();
					}
				});
				//Place the searchButton on
				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new BorderLayout());
				buttonPanel.add(searchButton, BorderLayout.EAST);
				
				JPanel mergeButtonPanel = new JPanel();
				mergeButtonPanel.setLayout(new FlowLayout());
				mergeButtonPanel.add(mergeButton);
				buttonPanel.add(mergeButtonPanel, BorderLayout.CENTER);
				
				this.add(buttonPanel, BorderLayout.EAST);
				this.add(defaultButton, BorderLayout.WEST);
				
		return container;
	}

	private JComponent createResultList() {

		DefaultListModel<HomepageResultlistData> listData = new DefaultListModel<>();
		//Alle Testdaten in die Resultlisten testweise einfügen
		listData.addAll(Arrays.asList(TestData.HOMEPAGERESULTLISTDATA_DATA));
		
		resultList = new JList<>();
		//Daten aus listData verwenden
		resultList.setModel(listData);
		//Beeinflusst Zeilenaussehen in KLasse ResultListCellRenderer oben
		resultList.setCellRenderer(new ResultlistCellRenderer());
		

		//Add ListSelectionListener to the JList
		resultList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					//Get the selected item
					HomepageResultlistData selectedData = resultList.getSelectedValue();
					//Handle teh selection
					if(selectedData != null) {
						openPageDroneInfo(selectedData);
						//MainWindow.goToDroneInfo(selectedData);
					}
				}
			}
		});
		
		return new JScrollPane(resultList);
		
	}
	
	private void openPageDroneInfo(HomepageResultlistData selectedData) {
		//Create adn show a new JFrame for PageDroneInfo
		JFrame droneInfoFrame = new JFrame("Drone Information");
		droneInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Create an instance of PageDroneInfo and set the selected data
		PageDroneInfo pageDroneInfo = new PageDroneInfo();
		//Add the PageDroneInfo instance to the JFrame
		droneInfoFrame.getContentPane().add(pageDroneInfo);
		
		//Set frame properties
		droneInfoFrame.setSize(400, 300);
		droneInfoFrame.setLocationRelativeTo(null);
		//Make the frame visible
		droneInfoFrame.setVisible(true);
		
		//Return a new JScrollPane containing the the updated JList
		JScrollPane scrollPane = new JScrollPane(resultList);
		scrollPane.setViewportView(resultList);
		revalidate(); //Refresh the Layout

	}
}