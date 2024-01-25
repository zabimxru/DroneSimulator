package Katze.DroneSimulation.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingTools {
	//Workaround, um den Text horizontal zu zentrieren
	public static JPanel createCenteredLabel(String txtlabel) {
		JPanel labelContainer = new JPanel();
		labelContainer.setLayout(new BorderLayout());
		

        JLabel label = new JLabel(txtlabel);
        
        label.setHorizontalAlignment(JLabel.CENTER);
       
        labelContainer.add(label, BorderLayout.CENTER);
        
        return labelContainer;
	}
}
