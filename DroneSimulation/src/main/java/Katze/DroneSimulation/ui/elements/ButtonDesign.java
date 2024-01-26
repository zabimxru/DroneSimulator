package Katze.DroneSimulation.ui.elements;

//https://www.java-forum.org/thema/jbutton-design-aendern.17240/
import javax.swing.*;

public class ButtonDesign extends JButton {
	public ButtonDesign(String _file) {	
		super(new ImageIcon(_file));
	}
}
