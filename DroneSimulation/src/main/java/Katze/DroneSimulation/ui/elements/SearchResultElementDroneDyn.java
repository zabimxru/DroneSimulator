package Katze.DroneSimulation.ui.elements;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Katze.DroneSimulation.data.TableViewable;
import Katze.DroneSimulation.data.api.Drone;

public class SearchResultElementDroneDyn <T extends TableViewable> extends JPanel{
	private final Drone droneData;
	private final String[] tableHeaders;
	private final BiFunction<Integer, String, List<T>> searchResultProvider;
	private final Consumer<T> rowClickedConsumer;
	
	
	private JTextField searchbar;
	private DefaultTableModel tableModel;
	private List<T> currentData;
	
	
	public SearchResultElementDroneDyn(Drone droneData, String[] tableHeaders, BiFunction<Integer, String, List<T>> searchResultProvider, Consumer<T> rowClickedConsumer) {
		this.droneData = droneData;
		this.tableHeaders = tableHeaders;
		this.searchResultProvider = searchResultProvider;
		this.rowClickedConsumer = rowClickedConsumer;
		
		this.setLayout(new BorderLayout(0, 10));
		
		//1. Searchbar + Resultbutton
		this.add(createSearchbar(), BorderLayout.NORTH);
		//2. Resultlist
		this.add(createResultTable(), BorderLayout.CENTER);
		
	}
	
	private JPanel createSearchbar() {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		
//		1. JTextfield
		searchbar = new JTextField();
		searchbar.addActionListener(e -> updateTable());
		container.add(searchbar);
		
		//2. Button mit x zum Zurücksetzen der Suche
		JButton resetButton = new JButton("X");
		resetButton.addActionListener(e -> {
			searchbar.setText("");
			updateTable();
		});
		container.add(resetButton);
		
		return container;
	}
	
	
	private JComponent createResultTable() {
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(tableHeaders);
		updateTable();
		
		JTable table = new JTable();
		table.setModel(tableModel);
		table.setRowSelectionAllowed(true);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getClickCount() == 2 && table.getSelectedRow() != -1) {
					T selectedData = currentData.get(table.getSelectedRow());
					rowClickedConsumer.accept(selectedData);
				}
			}
		});
	
		return new JScrollPane(table);

	}
	
	//Zeigt DefaultView an vom Table
	private void updateTable() {
		String searchInput = searchbar.getText();
		currentData = searchResultProvider.apply(droneData.getId(), searchInput);
		
		tableModel.setRowCount(0);
		for(T t : currentData) {
			Object[] rowData = t.getRowData();
			tableModel.addRow(rowData);
		}
	}
		
}

