import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;

public class JDatePickerTest extends JFrame {
	public JDatePickerTest() {
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
		 
		//JDatePickerTest.add(datePicker);
	}
	public static void main(String[] args) {
		JDatePickerTest x = new JDatePickerTest();
		x.setSize(1040, 670);
		x.setVisible(true);
	}

}
