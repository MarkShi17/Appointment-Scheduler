import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

public class calenderTest extends JFrame implements PropertyChangeListener {
	
	JFormattedTextField textField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
			
	public calenderTest() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1040, 670);
		setTitle("Jcalender Test");
		
		Container cp = getContentPane();
		FlowLayout flowLayout = new FlowLayout();
		cp.setLayout(flowLayout);
		cp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		//textField.setValue(new Date());
		textField.setPreferredSize(new Dimension(130,30));
		cp.add(textField);
		
		JButton btnCal = new JButton("Pick a date");
		cp.add(btnCal);
		
		CalendarTestWindow calendarTestWindow = new CalendarTestWindow();
		calendarTestWindow.setUndecorated(true);
		calendarTestWindow.addPropertyChangeListener(this);
		
		btnCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendarTestWindow.setLocation(textField.getLocationOnScreen().x, 
						(textField.getLocationOnScreen().y + textField.getHeight()));
				
				//NEXT TWO LINES: only works if line 31 is running, highlights current and next date
				//Date selectedDate = (Date)textField.getValue();
				//calendarTestWindow.resetSelection(selectedDate); 
				
				calendarTestWindow.setVisible(true);
			}
		});
	}
	public static void main(String[] args) {
		calenderTest x = new calenderTest();
		x.setVisible(true);

	}
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		
		if(event.getPropertyName().equals("selectedDate")) {
			java.util.Calendar cal = (java.util.Calendar)event.getNewValue();
			Date selDate = cal.getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
			String dateString = dateFormat.format(selDate);  
			System.out.println("old:"+selDate+" new:"+dateString);
			textField.setValue(selDate);
		}
		
	}

}
