package Katze.DroneSimulation.ui.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;  // Import SimpleDateFormat for formatting Date to String
import java.util.Arrays;
import java.util.Comparator;
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
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import Katze.DroneSimulation.data.TestData;
import Katze.DroneSimulation.data.ui.DroneDynamicsResultListData;
import Katze.DroneSimulation.logic.ui.ActionGoToHome;
import Katze.DroneSimulation.ui.ColorTheme;
import Katze.DroneSimulation.ui.MainWindow;

public class PageDroneDynamics extends JPanel {
	
    public static DroneDynamicsResultListData[] filterBySearchAttribute(DroneDynamicsResultListData[] array, String searchBarInput) {
		return Arrays.stream(array)
				.filter(resultlistData -> {
					//Convert Date to String for comparison
					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
					String formattimestamp = dateformat.format(resultlistData.getTimestamp());
					
					//Compare the formatted timestamp with searchBarInput
					return formattimestamp.contains(searchBarInput);
				})
				.toArray(DroneDynamicsResultListData[]::new);
	}
	
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
            labelSpeed.setText(String.valueOf(value.getSpeed()));  // Assuming getSpeed returns an int
            labelAlignmentRoll.setText(String.valueOf(value.getAlignmentRoll()));
            labelControlRange.setText(String.valueOf(value.getControlRage()));
            labelLongitude.setText(String.valueOf(value.getLongitude()));
            labelLatitude.setText(String.valueOf(value.getLatitude()));
            labelBatteryStatus.setText(String.valueOf(value.getBatteryStatus()));
            labelStatus.setText(String.valueOf(value.getStatus()));
            
            return panelRow;
        }
    }
    
    private DroneDynamicsResultListData[] originalData;
    
    public PageDroneDynamics(MainWindow window) {
    	originalData = TestData.DRONEDYNAMICS_DATA.clone();
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

        JList<DroneDynamicsResultListData> resultList = new JList<>(listData);
        //resultList.setModel(listData);
        resultList.setCellRenderer(new ResultListCellRenderer());
        centerPanel.add(new JScrollPane(resultList), BorderLayout.CENTER);
        
        

        add(centerPanel, BorderLayout.CENTER);

        // Der untere Panel für die Knöpfe
        JPanel bottomPanel = new JPanel();
        JButton DroneInfo = new JButton("Zurück zur DroneInfo Seite");
        JButton Homepage = new JButton("Zurück zur Homepage");
        JButton mergeButton = new JButton("Sort");
        JButton searchButton = new JButton("Search");
        JButton defaultButton = new JButton("Default view");
        
        
        Homepage.addActionListener(new ActionGoToHome(window));
        
        bottomPanel.add(DroneInfo);
        bottomPanel.add(Homepage);
        add(bottomPanel, BorderLayout.SOUTH);
        
        mergeButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("Sort Button clicked");
				String searchBarInput = searchBar.getText();
				
				DefaultListModel<DroneDynamicsResultListData> listModel = (DefaultListModel<DroneDynamicsResultListData>) resultList.getModel();
				DroneDynamicsResultListData[] dataArray = new DroneDynamicsResultListData[listModel.size()];
				listModel.copyInto(dataArray);
				
				//Sort the data using Comparator because of timestamp
				 Arrays.sort(dataArray, Comparator.comparing(DroneDynamicsResultListData::getTimestamp));
				
				//Update the JList with the sorted data
				listModel.clear();
				listModel.addAll(Arrays.asList(dataArray));
				resultList.repaint();
        	}
        });
        
        searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Search button clicked");
				String searchBarInput = searchBar.getText();
				
				DroneDynamicsResultListData[] filteredResults = filterBySearchAttribute(
					TestData.DRONEDYNAMICS_DATA, searchBarInput);
				
				DefaultListModel<DroneDynamicsResultListData> listModel = (DefaultListModel<DroneDynamicsResultListData>) resultList.getModel();
				listModel.clear();
				listModel.addAll(Arrays.asList(filteredResults));
				resultList.repaint();
			}
		});
		
      //Add ActionListener to the defaultButton
		defaultButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Default view button clicked");
				try {
					//Restore the original data when the Default view button is clicked
					DefaultListModel<DroneDynamicsResultListData> listModel = (DefaultListModel<DroneDynamicsResultListData>) resultList.getModel();
					listModel.clear();
					listModel.addAll(Arrays.asList(originalData));
				} catch (ClassCastException ex) {
					ex.printStackTrace();				}
			}
		});
		
		//Panel for Merge, Sort, DefaultButtons
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(mergeButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(defaultButton);
		//buttonPanel.add(mergeButtonPanel);
		
    }

}
