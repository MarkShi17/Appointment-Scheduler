import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;

public class VaccAppPopup extends JFrame {
	private JTextField textField;
	public VaccAppPopup() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(Color.BLACK);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(211, 134, 285, 179);
		panel.setSize(400, 200);
		getContentPane().add(panel);
		
		textField = new JTextField();
		textField.setForeground(Color.RED);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
	}
	public static void main(String[] args) {
		VaccAppPopup x = new VaccAppPopup();
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes everything associated with this program when closed including calendar
		x.setSize(1040, 670);
		x.setVisible(true);
	}

}
