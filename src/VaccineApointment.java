import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JSlider;

public class VaccineApointment extends JFrame implements PropertyChangeListener {
	static myValidators regexValidators = new myValidators();
	static verifyShotNum verifyVaccInfo = new verifyShotNum();
	private JTextField txtVaccinationAppointmentFill;
	private JTextField txtFirstName;
	private JTextField txtSurname;
	private JTextField txtDOB;
	private JTextField txtSSN;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JLabel lblFirstName;
	private JLabel lblSurname;
	private JLabel lblDOB;
	private JLabel lblSSN;
	private JLabel lblEmail;
	private JLabel lblPhone;
	private JLabel lblGender;
	private JLabel lblRace;
	private JLabel lblEthnicity;
	private JLabel lblVaccName;
	private JLabel lblVaccSite;
	private JLabel lblDate;
	private JLabel lblTime;
	private JComboBox<String> comboBoxRace;
	private JComboBox<String> comboBoxEthn;
	private JComboBox<String> comboBoxVaccName;
	private JComboBox<String> comboBoxVaccSite;
	private JComboBox<String> comboBoxGender;
	private JComboBox<String> comboBoxTime;
	private JCheckBox chbReminder;
	private JCheckBox chbParentConsent;
	private JButton btnSubmit;
	public JTextArea txtWarning;
	public static List<String[]> cols;
	private static final String NOT_SELECTABLE_OPTION = " - Select an Option - ";
	public String validateResults;
	public boolean btnTimePressed;
	public boolean btnGroupPress;
	private JButton btnDate;
	private JButton btnTime;
	private JRadioButton rdbtn07;
	private JRadioButton rdbtn08;
	private JRadioButton rdbtn09;
	private JRadioButton rdbtn10;
	private JRadioButton rdbtn11;
	private JRadioButton rdbtn12;
	private JRadioButton rdbtn13;
	private JRadioButton rdbtn14;
	private JRadioButton rdbtn15;
	private JRadioButton rdbtn16;
	private JRadioButton rdbtn17;
	private JRadioButton rdbtn18;
	private ButtonGroup btngrptime;
	public String gsVaccDate;
	public int piVaccSiteId;
	public Date selDate;
	JFormattedTextField txtDate = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
	Popup p;
	
	public VaccineApointment() {
		final JFrame frame = new JFrame();
		//validateResults = "";
		piVaccSiteId = 10000;
		getContentPane().setEnabled(false);
		getContentPane().setLayout(null);
		
		txtVaccinationAppointmentFill = new JTextField();
		txtVaccinationAppointmentFill.setBounds(0, 0, 908, 32);
		txtVaccinationAppointmentFill.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtVaccinationAppointmentFill.setHorizontalAlignment(SwingConstants.CENTER);
		txtVaccinationAppointmentFill.setText("Vaccination Appointment ");
		getContentPane().add(txtVaccinationAppointmentFill);
		txtVaccinationAppointmentFill.setColumns(10);
		
		lblFirstName = new JLabel("First Name:");
		lblFirstName.setForeground(Color.BLACK);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstName.setBounds(10, 71, 83, 40);
		getContentPane().add(lblFirstName);
		
		lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSurname.setBounds(10, 110, 83, 32);
		getContentPane().add(lblSurname);
		
		txtFirstName = new JTextField();
		txtFirstName.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtFirstName.setBackground(Color.WHITE);
		txtFirstName.setBounds(113, 81, 166, 20);
		getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		txtFirstName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int firstName = txtFirstName.getText().trim().length();
            	if (firstName == 0) {
            		//print 'first name is invalid'
            	}
            }
        });
		
		txtSurname = new JTextField();
		txtSurname.setBounds(113, 118, 166, 20);
		getContentPane().add(txtSurname);
		txtSurname.setColumns(10);
		txtSurname.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int surnameValue = txtSurname.getText().trim().length();
            	if (surnameValue == 0) {
            		//print 'surname is invalid'
            	}
            }
        });
		
		lblDOB = new JLabel("Date of Birth \r\n");
		lblDOB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDOB.setBounds(10, 153, 96, 20);
		getContentPane().add(lblDOB);
		
		txtDOB = new JTextField();
		txtDOB.setBounds(113, 155, 166, 20);
		getContentPane().add(txtDOB);
		txtDOB.setColumns(10);
		txtDOB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String DOBvalue = txtDOB.getText();
            	String DOBresult = regexValidators.dobValidator(DOBvalue);
            	if (DOBresult != "yes") {
            		//print DOB error message 
            	}
            }
        });
		
	
		lblSSN = new JLabel("Last 4 Digits of SSN");
		lblSSN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSSN.setBounds(10, 188, 136, 22);
		getContentPane().add(lblSSN);
		
		txtSSN = new JTextField();
		txtSSN.setBounds(156, 188, 123, 20);
		getContentPane().add(txtSSN);
		txtSSN.setColumns(10);
		
		lblPhone = new JLabel("Phone Number");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhone.setBounds(10, 226, 110, 14);
		getContentPane().add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(116, 220, 163, 20);
		getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		
		lblEmail = new JLabel("Email Address");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 282, 123, 14);
		getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(116, 281, 163, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String emailValue = txtEmail.getText();
            	Boolean emailResult = regexValidators.validateEmail(emailValue);
            	if (emailResult = false) {
            		//print 'your email is invalid'
            	}
            }
        });
		
		JLabel lblNewLabel_6 = new JLabel("Text or Email Reminder");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(10, 318, 163, 14);
		getContentPane().add(lblNewLabel_6);
		
		chbReminder = new JCheckBox(" ");
		chbReminder.setBounds(173, 317, 123, 20);
		getContentPane().add(chbReminder);
		
		lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGender.setBounds(10, 356, 49, 14);
		getContentPane().add(lblGender);
		
		comboBoxGender = new JComboBox<String>();
		comboBoxGender.setModel(new DefaultComboBoxModel<String>() {
		      private static final long serialVersionUID = 1L;
		      boolean selectionAllowed = true;

		      @Override
		      public void setSelectedItem(Object anObject) {
		        if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
		          super.setSelectedItem(anObject);
		        } else if (selectionAllowed) {
		          // Allow this just once
		          selectionAllowed = false;
		          super.setSelectedItem(anObject);
		        }
		      }
		    });
		comboBoxGender.addItem(NOT_SELECTABLE_OPTION);
		comboBoxGender.addItem("Male");
		comboBoxGender.addItem("Female");
		comboBoxGender.addItem("Other");
		//comboBoxGender.setSelectedIndex(2);
		comboBoxGender.setBounds(79, 354, 145, 22);
		getContentPane().add(comboBoxGender);
		
		lblRace = new JLabel("Race");
		lblRace.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRace.setBounds(10, 387, 49, 14);
		getContentPane().add(lblRace);
		
		
		comboBoxRace = new JComboBox<String>();
		comboBoxRace.setModel(new DefaultComboBoxModel<String>() {
		      private static final long serialVersionUID = 1L;
		      boolean selectionAllowed = true;

		      @Override
		      public void setSelectedItem(Object anObject) {
		        if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
		          super.setSelectedItem(anObject);
		        } else if (selectionAllowed) {
		          // Allow this just once
		          selectionAllowed = false;
		          super.setSelectedItem(anObject);
		        }
		      }
		    });
		comboBoxRace.addItem(NOT_SELECTABLE_OPTION);
		comboBoxRace.addItem("Caucasian");
		comboBoxRace.addItem("Asian");
		comboBoxRace.addItem("African American");
		comboBoxRace.addItem("Latino");
		comboBoxRace.addItem("American Indian");
		comboBoxRace.setBounds(79, 385, 145, 22);
		getContentPane().add(comboBoxRace);
		
		lblEthnicity = new JLabel("Ethnicity");
		lblEthnicity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEthnicity.setBounds(10, 418, 72, 20);
		getContentPane().add(lblEthnicity);
		
		comboBoxEthn = new JComboBox<String>();
		comboBoxEthn.setModel(new DefaultComboBoxModel<String>() {
		      private static final long serialVersionUID = 1L;
		      boolean selectionAllowed = true;

		      @Override
		      public void setSelectedItem(Object anObject) {
		        if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
		          super.setSelectedItem(anObject);
		        } else if (selectionAllowed) {
		          // Allow this just once
		          selectionAllowed = false;
		          super.setSelectedItem(anObject);
		        }
		      }
		    });
		comboBoxEthn.addItem(NOT_SELECTABLE_OPTION);
		comboBoxEthn.addItem("White");
		comboBoxEthn.addItem("Black");
		comboBoxEthn.addItem("Hispanic/Latino");
		comboBoxEthn.addItem("None");
		comboBoxEthn.setBounds(79, 416, 145, 22);
		getContentPane().add(comboBoxEthn);
		
		lblVaccName = new JLabel("Vaccine Name");
		lblVaccName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVaccName.setBounds(325, 80, 123, 20);
		getContentPane().add(lblVaccName);
		
		comboBoxVaccName = new JComboBox<String>();
		//comboBoxVaccineName.addActionListener(this);
		//String mySql ="Select name from dbo.vaccines order by name";
		comboBoxVaccName.setModel(new DefaultComboBoxModel<String>() {
		      private static final long serialVersionUID = 1L;
		      boolean selectionAllowed = true;

		      @Override
		      public void setSelectedItem(Object anObject) {
		        if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
		          super.setSelectedItem(anObject);
		        } else if (selectionAllowed) {
		          // Allow this just once
		          selectionAllowed = false;
		          super.setSelectedItem(anObject);
		        }
		      }
		    });
		comboBoxVaccName.addItem(NOT_SELECTABLE_OPTION);
		//comboBoxVaccineName.setsel
		String mySql ="Select name,id from dbo.vaccines";
		List<String[]> DBdata = new ArrayList<>();
		DBdata = getDBdata(mySql);
		//System.out.println("0,1 "+ DBdata.get(0)[1]);
		for(int i = 0; i <DBdata.size();i++) {
			System.out.println(DBdata.get(i)[1]+" "+DBdata.get(0).length);
			comboBoxVaccName.addItem(DBdata.get(i)[1]);
		}
		comboBoxVaccName.setBounds(453, 79, 445, 22);
		getContentPane().add(comboBoxVaccName);
		
		lblVaccSite = new JLabel("Vaccination Site");
		lblVaccSite.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVaccSite.setBounds(325, 116, 136, 14);
		getContentPane().add(lblVaccSite);
		
		comboBoxVaccSite = new JComboBox<String>();
		comboBoxVaccSite.setModel(new DefaultComboBoxModel<String>() {
		      private static final long serialVersionUID = 1L;
		      boolean selectionAllowed = true;

		      @Override
		      public void setSelectedItem(Object anObject) {
		        if (!NOT_SELECTABLE_OPTION.equals(anObject)) {
		          super.setSelectedItem(anObject);
		        } else if (selectionAllowed) {
		          // Allow this just once
		          selectionAllowed = false;
		          super.setSelectedItem(anObject);
		        }
		      }
		    });
		comboBoxVaccSite.addItem(NOT_SELECTABLE_OPTION);
		//comboBoxVaccineName.setsel
		String mySql2 ="select name + ': ' + street + ' ' +  city + ' '+ CONVERT(varchar(10), zip_code), id from dbo.vaccine_sites order by id;";
		List<String[]> DBdata2 = new ArrayList<>();
		DBdata2 = getDBdata(mySql2);
		//System.out.println("0,1 "+ DBdata.get(0)[1]);
		for(int i = 0; i <DBdata2.size();i++) {
			//System.out.println(DBdata2.get(i)[1]+" "+DBdata2.get(0).length);
			comboBoxVaccSite.addItem(DBdata2.get(i)[1]);
		}
		//comboBoxVaccSite.addItem(NOT_SELECTABLE_OPTION);
		/*(comboBoxVaccSite.addItem("filler1");
		comboBoxVaccSite.addItem("filler2");
		comboBoxVaccSite.addItem("filler3");*/
		comboBoxVaccSite.setBounds(453, 112, 445, 22);
		getContentPane().add(comboBoxVaccSite);
		comboBoxVaccSite.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	piVaccSiteId = comboBoxVaccSite.getSelectedIndex();
            }
        });
		
		
		lblDate = new JLabel("Schedule Date ");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDate.setBounds(325, 150, 123, 14);
		getContentPane().add(lblDate);
		
		lblTime = new JLabel("Schedule Time");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTime.setBounds(325, 183, 123, 14);
		getContentPane().add(lblTime);
		
		txtWarning = new JTextArea();
		txtWarning.setLineWrap(true);
		txtWarning.setWrapStyleWord(true);
		txtWarning.setText("");
		txtWarning.append(" ");
		txtWarning.setRows(3);
		txtWarning.setFont(new Font("Cambria", Font.PLAIN, 22));
		txtWarning.setColumns(10);
		txtWarning.setBounds(325, 268, 573, 162);
		getContentPane().add(txtWarning);
		
		chbParentConsent = new JCheckBox("Parent's Consent");
		chbParentConsent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chbParentConsent.setBounds(325, 483, 154, 23);
		getContentPane().add(chbParentConsent);
		
		JLabel lblNewLabel_16 = new JLabel("(MM/DD/YYYY)");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_16.setBounds(10, 170, 100, 14);
		getContentPane().add(lblNewLabel_16);
		
		JLabel lblNewLabel_10 = new JLabel("Use (000)000-0000 Format");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(10, 245, 163, 14);
		getContentPane().add(lblNewLabel_10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 514, 89, 23);
		getContentPane().add(btnSubmit);
		
		txtDate.setBounds(580, 148, 96, 20);
		getContentPane().add(txtDate);
		txtDate.setColumns(10);
		//txtDate.setValue(new Date());
		
		CalendarTestWindow calendarTestWindow = new CalendarTestWindow();
		calendarTestWindow.setUndecorated(true);
		calendarTestWindow.addPropertyChangeListener(this);
		
		btnDate = new JButton("Select Date");
		btnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendarTestWindow.setLocation(txtDate.getLocationOnScreen().x, 
						(txtDate.getLocationOnScreen().y + txtDate.getHeight()));
				
				//Date selectedDate = (Date)txtDate.getValue();
				//calendarTestWindow.resetSelection(selectedDate);
				
				calendarTestWindow.setVisible(true);
			}
		});
		btnDate.setBounds(453, 146, 117, 23);
		getContentPane().add(btnDate);
		
		btnTime = new JButton("Check Availability");
		btnTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//piVaccSiteId = comboBoxVaccSite.getSelectedIndex();
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            	Date dMaxDate = null;
				try {
					dMaxDate = formatter.parse("07/01/2022");
				} 
				catch (ParseException e1) {
					e1.printStackTrace();
				}
            	Date dMinDate = new Date();
            	Date dVaccDateResult = (Date) txtDate.getValue();
            	System.out.println(dVaccDateResult);
            	String sCbxVaccSiteResult = (String)comboBoxVaccSite.getSelectedItem();
				btngrptime.clearSelection();
				rdbtn07.setEnabled(false);
				rdbtn08.setEnabled(false);
				rdbtn09.setEnabled(false);
				rdbtn10.setEnabled(false);
				rdbtn11.setEnabled(false);
				rdbtn12.setEnabled(false);
				rdbtn13.setEnabled(false);
				rdbtn14.setEnabled(false);
				rdbtn15.setEnabled(false);
				rdbtn16.setEnabled(false);
				rdbtn17.setEnabled(false);
				rdbtn18.setEnabled(false);
				/*if(gsVaccDate == null) {
					//System.out.println("invalid date");
				}
				else if(piVaccSiteId == 10000) {
					//System.out.println("Please select a vaccine site");
				} */
				if(gsVaccDate == null && sCbxVaccSiteResult.equals(NOT_SELECTABLE_OPTION)) {
					JOptionPane.showMessageDialog(frame, "Date not selected.\nNo vaccine site selected.",
	                        "Availability Check Error", JOptionPane.WARNING_MESSAGE);
				}
				else if(gsVaccDate == null) {
					JOptionPane.showMessageDialog(frame, "Date not selected.",
	                        "Availability Check Error", JOptionPane.WARNING_MESSAGE);
				}
				else if(dVaccDateResult.after(dMaxDate) || dVaccDateResult.before(dMinDate) && sCbxVaccSiteResult.equals(NOT_SELECTABLE_OPTION)) {
            		JOptionPane.showMessageDialog(frame, "No vaccine site selected.\nInvalid date.",
	                        "Availability Check Error", JOptionPane.WARNING_MESSAGE);
            	}
				else if(sCbxVaccSiteResult.equals(NOT_SELECTABLE_OPTION)) {
            		JOptionPane.showMessageDialog(frame, "No vaccine site selected.",
	                        "Availability Check Error", JOptionPane.WARNING_MESSAGE);
        		}
				else if(dVaccDateResult.after(dMaxDate) || dVaccDateResult.before(dMinDate)) {
					JOptionPane.showMessageDialog(frame, "Invalid date.",
	                        "Availability Check Error", JOptionPane.WARNING_MESSAGE);
				}
				
				else {
					int vaccSiteId = comboBoxVaccSite.getSelectedIndex();
					String sVaccDate = txtDate.getText(); 
            		sVaccDate.replace('/', '-');
					btnTimePressed = true;
					String getMaxAppts = "select max_appts_hourly, 1 from dbo.vaccine_sites where id = "+vaccSiteId+";";
					List<String[]> maxApptsHour = new ArrayList<>();
					maxApptsHour = getDBdata(getMaxAppts);
					String sMaxAppts = maxApptsHour.get(0)[1];
					int iMaxAppts = Integer.parseInt(sMaxAppts);
					
					Enumeration enumeration1 = btngrptime.getElements();
					int count = 0;
				    while (enumeration1.hasMoreElements()) {
				        AbstractButton button = (AbstractButton) enumeration1.nextElement();
				        String timeSlot = button.getText().substring(0,2).strip();
				        if(Integer.parseInt(timeSlot) < 7) {
				        	int iTimeSlot = Integer.parseInt(timeSlot) + 12;
				        	timeSlot = iTimeSlot + "";
				        }
				        //System.out.println("time "+timeSlot);
				        boolean ifAvailable = verifyVaccInfo.ifSpotAvailable(vaccSiteId, sVaccDate, timeSlot,iMaxAppts, vaccSiteId);
				        if(ifAvailable == true) { 
				        	button.setEnabled(true);
				        	//System.out.println("good");
				        }
				        else {
				        	count +=1;
				        }
				        if(count == 12) {
				        	JOptionPane.showMessageDialog(frame,  "There are no open slots on the day you chose. Please choose a different date.",
	                                "Error", JOptionPane.WARNING_MESSAGE);
				        }
				        
				    }
					
					/*for (int i = 0; i < 12; i++ ) {
						int j = i + 7;
						String timeSlot = j +"";
						boolean ifAvailable = verifyVaccInfo.ifSpotAvailable(vaccSiteId, sVaccDate, timeSlot,iMaxAppts);
						//btngrptime.
					}*/
					/*String sql = "select time, count(*), 1 from dbo.vaccine_calendar where date = '" +gsVaccDate.toString()+"' and site_id = " +piVaccSiteId+" and appointment_id is null group by time order by time";
					List<String[]> DBdata3 = new ArrayList<>();
					DBdata3 = getDBdata(sql);
					//System.out.println(DBdata3.size());
					for(int i = 0; i <DBdata3.size();i++) {
						//System.out.println("1 "+DBdata3.get(i)[1]+" "+DBdata3.get(i)[2]);
						//comboBoxVaccSite.addItem(DBdata2.get(i)[1]);
						if (DBdata3.get(i)[1].equals("07")) {
							//System.out.println("part 1");
							if (DBdata3.get(i)[2] != "0"){
								rdbtn07.setEnabled(true);
								//System.out.println("2 enabled radio button ");
							}
						}
						if (DBdata3.get(i)[1].equals("08")) {
							if (DBdata3.get(i)[2] != "0"){
								rdbtn08.setEnabled(true);
							}
						}
						if (DBdata3.get(i)[1].equals("09")) {
							if (DBdata3.get(i)[2] != "0"){
								rdbtn09.setEnabled(true);
							}
						}
						if (DBdata3.get(i)[1].equals("10")) {
							if (DBdata3.get(i)[2] != "0"){
								rdbtn10.setEnabled(true);
							}
						}
						if (DBdata3.get(i)[1].equals("11")) {
							if (DBdata3.get(i)[2] != "0"){
								rdbtn11.setEnabled(true);
							}
						}
						if (DBdata3.get(i)[1].equals("12")) {
							if (DBdata3.get(i)[2] != "0"){
								rdbtn12.setEnabled(true);
							}
						}
						if (DBdata3.get(i)[1].equals("13")) {
							if (DBdata3.get(i)[2] != "0"){
								rdbtn13.setEnabled(true);
							}
						}
						if (DBdata3.get(i)[1].equals("14")) {
							if (DBdata3.get(i)[2] != "0"){
								rdbtn14.setEnabled(true);
							}
						}
						if (DBdata3.get(i)[1].equals("15")) {
							if (DBdata3.get(i)[2] != "0"){
								rdbtn15.setEnabled(true);
							}
						}
						if (DBdata3.get(i)[1].equals("16")) {
							if (DBdata3.get(i)[2] != "0"){
								rdbtn16.setEnabled(true);
							}
						}
						if (DBdata3.get(i)[1].equals("17")) {
							if (DBdata3.get(i)[2] != "0"){
								rdbtn17.setEnabled(true);
							}
						}
						if (DBdata3.get(i)[1].equals("18")) {
							if (DBdata3.get(i)[2] != "0"){
								rdbtn18.setEnabled(true);
							}
						}
					}*/
				}
				
					
			}
		});
		btnTime.setBounds(453, 179, 145, 23);
		getContentPane().add(btnTime);
		
		rdbtn07 = new JRadioButton("7 am");
		rdbtn07.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn07.setBounds(325, 214, 72, 23);
		getContentPane().add(rdbtn07);
		
		rdbtn08 = new JRadioButton("8 am");
		rdbtn08.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn08.setBounds(399, 214, 72, 23);
		getContentPane().add(rdbtn08);
		
		rdbtn09 = new JRadioButton("9 am");
		rdbtn09.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn09.setBounds(473, 214, 72, 23);
		getContentPane().add(rdbtn09);
		
		rdbtn10 = new JRadioButton("10 am");
		rdbtn10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn10.setBounds(547, 214, 83, 23);
		getContentPane().add(rdbtn10);
		
		rdbtn11 = new JRadioButton("11 am");
		rdbtn11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn11.setBounds(632, 214, 83, 23);
		getContentPane().add(rdbtn11);
		
		rdbtn12 = new JRadioButton("12 pm");
		rdbtn12.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn12.setBounds(717, 214, 90, 23);
		getContentPane().add(rdbtn12);
		
		rdbtn13 = new JRadioButton("1 pm");
		rdbtn13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn13.setBounds(325, 240, 72, 23);
		getContentPane().add(rdbtn13);
		
		rdbtn14 = new JRadioButton("2 pm");
		rdbtn14.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn14.setBounds(399, 240, 72, 23);
		getContentPane().add(rdbtn14);
		
		rdbtn15 = new JRadioButton("3 pm");
		rdbtn15.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn15.setBounds(473, 240, 72, 23);
		getContentPane().add(rdbtn15);
		
		rdbtn16 = new JRadioButton("4 pm");
		rdbtn16.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn16.setBounds(547, 240, 72, 23);
		getContentPane().add(rdbtn16);
		
		rdbtn17 = new JRadioButton("5 pm");
		rdbtn17.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn17.setBounds(632, 240, 72, 23);
		getContentPane().add(rdbtn17);
		
		rdbtn18 = new JRadioButton("6 pm");
		rdbtn18.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtn18.setBounds(717, 240, 72, 23);
		getContentPane().add(rdbtn18);
		
		btngrptime = new ButtonGroup();
		btngrptime.add(rdbtn07);
		btngrptime.add(rdbtn08);
		btngrptime.add(rdbtn09);
		btngrptime.add(rdbtn10);
		btngrptime.add(rdbtn11);
		btngrptime.add(rdbtn12);
		btngrptime.add(rdbtn13);
		btngrptime.add(rdbtn14);
		btngrptime.add(rdbtn15);
		btngrptime.add(rdbtn16);
		btngrptime.add(rdbtn17);
		btngrptime.add(rdbtn18);
		System.out.println("btngrp count "+btngrptime.getButtonCount());
		pack();
		
		JLabel lblNewLabel_11 = new JLabel("Between Today and 7/1/2022");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_11.setBounds(686, 149, 212, 19);
		getContentPane().add(lblNewLabel_11);
		
		Enumeration<AbstractButton> enumeration = btngrptime.getElements();
		while (enumeration.hasMoreElements()) {
		    enumeration.nextElement().setEnabled(false);
		}

		
		btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	validateResults = "";
            	int firstName = txtFirstName.getText().trim().length();
            	if (firstName == 0) {
            		validateResults = validateResults + "\nMissing First Name.";
            		lblFirstName.setForeground(Color.RED);
            	}
            	else {
            		lblFirstName.setForeground(Color.BLACK);
            	}
            	int surnameValue = txtSurname.getText().trim().length();
            	if (surnameValue == 0) {
            		validateResults = validateResults + "\nMissing Surname.";
            		lblSurname.setForeground(Color.RED);
            	}
            	else {
            		lblSurname.setForeground(Color.BLACK);
            	}
            	String DOBvalue = txtDOB.getText();
            	String DOBresult = regexValidators.dobValidator(DOBvalue);
            	if (DOBresult != "yes") {
            		validateResults = validateResults + "\n" + regexValidators.dobValidator(DOBvalue);
            		lblDOB.setForeground(Color.RED);
            	}
            	else {
            		lblDOB.setForeground(Color.BLACK);
            	}
            	
            	String SSNvalue = txtSSN.getText();
            	String SSNresult = regexValidators.SSNvalidator4digit(SSNvalue);
            	if (SSNresult != "yes" ) {
            		validateResults = validateResults + "\n" + regexValidators.SSNvalidator4digit(SSNvalue);
            		lblSSN.setForeground(Color.RED);
            	}
            	else {
            		lblSSN.setForeground(Color.BLACK);
            	}
            	String phoneNum = txtPhone.getText();
				String phoneResult = regexValidators.phoneNumValidator(phoneNum);
				if (phoneResult != "yes") { 
					validateResults = validateResults + "\n"+phoneResult;
					lblPhone.setForeground(Color.RED);
				}
				else {
            		lblPhone.setForeground(Color.BLACK);
            	}
            	String emailValue = txtEmail.getText();
            	Boolean emailResult = regexValidators.validateEmail(emailValue);
            	if (emailResult == false) {
            		validateResults = validateResults + "\nInvalid Email Address.";
            		lblEmail.setForeground(Color.RED);
            	}
            	else {
            		lblEmail.setForeground(Color.BLACK);
            	}
            	String sCbxGenderResult = (String)comboBoxGender.getSelectedItem();
            	if(sCbxGenderResult.equals(NOT_SELECTABLE_OPTION)) {
            		validateResults = validateResults + "\nGender not selected.";
            		lblGender.setForeground(Color.RED);
            	}
            	else {
            		lblGender.setForeground(Color.BLACK);
            	}
            	String sCbxRaceResult = (String)comboBoxRace.getSelectedItem();
            	if(sCbxRaceResult.equals(NOT_SELECTABLE_OPTION)) {
            		validateResults = validateResults + "\nRace not selected.";
            		lblRace.setForeground(Color.RED);
            	}
            	else {
            		lblRace.setForeground(Color.BLACK);
            	}
            	String sCbxEthnicityResult = (String)comboBoxEthn.getSelectedItem();
            	if(sCbxEthnicityResult.equals(NOT_SELECTABLE_OPTION)) {
            		validateResults = validateResults + "\nEthnicity not selected.";
            		lblEthnicity.setForeground(Color.RED);
            	}
            	else {
            		lblEthnicity.setForeground(Color.BLACK);
            	}
            	String sCbxVaccNameResult = (String)comboBoxVaccName.getSelectedItem();
            	if(sCbxVaccNameResult.equals(NOT_SELECTABLE_OPTION)) {
            		validateResults = validateResults + "\nVaccine Name not selected.";
            		lblVaccName.setForeground(Color.RED);
            	}
            	else {
            		lblVaccName.setForeground(Color.BLACK);
            	}
            	String sCbxVaccSiteResult = (String)comboBoxVaccSite.getSelectedItem();
            	if(sCbxVaccSiteResult.equals(NOT_SELECTABLE_OPTION)) {
            		validateResults = validateResults + "\nVaccine Site not selected.";
            		lblVaccSite.setForeground(Color.RED);
            	}
            	else {
            		lblVaccSite.setForeground(Color.BLACK);
            	}
            	//-------------------------------------------------------
            	//---------------------------------------------------------------------------------------------------
            	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            	Date dMaxDate = null;
				try {
					dMaxDate = formatter.parse("07/01/2022");
				} 
				catch (ParseException e1) {
					e1.printStackTrace();
				}
            	Date dMinDate = new Date();
            	Date dVaccDateResult = (Date) txtDate.getValue();
            	if(dVaccDateResult == null) {
            		//validateResults = validateResults + "\nVaccine Date not selected.";
            		lblDate.setForeground(Color.RED);
            	}
            	else if(dVaccDateResult.after(dMaxDate) || dVaccDateResult.before(dMinDate)) {
            		validateResults = validateResults + "\nVaccine Date not in the range.";
            		lblDate.setForeground(Color.RED);
            	}
            	else {
            		lblDate.setForeground(Color.BLACK);
            	}
            	
            	//---------------------------------------------------------------------------------------------------
            	btnGroupPress = false;
            	if(rdbtn07.isSelected()==true || rdbtn08.isSelected()==true || rdbtn09.isSelected()==true || rdbtn10.isSelected()==true || rdbtn11.isSelected()==true || rdbtn12.isSelected()==true || rdbtn13.isSelected()==true || rdbtn14.isSelected()==true || rdbtn15.isSelected()==true || rdbtn16.isSelected()==true || rdbtn17.isSelected()==true || rdbtn18.isSelected()==true) {
            		btnGroupPress = true;
            	}
            	//if(dVaccDateResult != null) {
            	if(dVaccDateResult == null) {
            		validateResults = validateResults + "\nVaccine Date not selected.\nPlease choose a date\nPlease check for availability\nPlease choose a time slot";
            		lblTime.setForeground(Color.RED);
            	}
            	else if(btnTimePressed == true && btnGroupPress == true) {
            		System.out.println("one");
            		lblTime.setForeground(Color.BLACK);
            	}
            	else if (btnTimePressed == true && dVaccDateResult.after(dMaxDate) || dVaccDateResult.before(dMinDate)) {
            		System.out.println("Two");
            		validateResults = validateResults + "\nPlease choose a new date\nPlease check for availability\nPlease choose a time slot";
            		lblDate.setForeground(Color.RED);
            		lblTime.setForeground(Color.BLACK);
            	}
            	else if (btnTimePressed == true) {
            		System.out.println("three");
            		validateResults = validateResults + "\nPlease select a time slot";
            		lblTime.setForeground(Color.BLACK);
            	}
            	else {
            		System.out.println("four");
            		validateResults = validateResults + "\nPlease check for availability\nPlease choose a time slot ";
            		lblTime.setForeground(Color.RED);
            		//lblDate.setForeground(Color.RED);
            	}
            	//}
            	//---------------------------------------------------------------------------------------------------
            	Boolean bParentCheck = chbParentConsent.isSelected();
            	if(bParentCheck.equals(false)) {
            		validateResults = validateResults + "\nParent's Consent not selected.";
            		chbParentConsent.setForeground(Color.RED);
            	}
            	else {
            		chbParentConsent.setForeground(Color.BLACK);
            	}
            	//------------------------------------------- put at bottom
            	if(validateResults.length() != 0) {
            		JOptionPane.showMessageDialog(frame, validateResults+"\n\nPlease make correction.",
                            "Submit Error", JOptionPane.WARNING_MESSAGE);
            		//System.out.println(comboBoxVaccName.getSelectedIndex());
            		String selectedButton = getSelectedButton(btngrptime);
            		System.out.println("01 "+selectedButton); 
            		
            	}
            	//-------------------------------------------
            	else {
            		validateResults = "";
            		//get everything for customer id
            		int dosage = 1;
            		String FirstName = txtFirstName.getText();
            		FirstName = FirstName.toLowerCase();
					String Surname = txtSurname.getText();
					Surname = Surname.toLowerCase();
					String DOB = txtDOB.getText();
					String SSN = txtSSN.getText();
					String Email = txtEmail.getText();
					Email = Email.toLowerCase();
					String Phone = txtPhone.getText();
					Boolean bParentConsent = chbParentConsent.isSelected();
					Boolean bReminder = chbReminder.isSelected();
					int iParentConsent = 0;
					int iReminder = 0;
					if(bParentConsent == true) {
						iParentConsent = 1;
					}
					else {
						iParentConsent = 0;
					}
					if(bReminder == true) {
						iReminder = 1;
					}
					else {
						iReminder = 0;
					}
					String Gender = (String) comboBoxGender.getSelectedItem();
					String Race = (String) comboBoxRace.getSelectedItem();
					String Ethnicity = (String) comboBoxEthn.getSelectedItem();
					int VaccineId = comboBoxVaccName.getSelectedIndex();
					int VaccSiteId = comboBoxVaccSite.getSelectedIndex();
					String selectedButton = getSelectedButton(btngrptime);
            		//get the id and add 1
            		String mySqlId1 = "select max(id), 1 from dbo.vaccine_appointments;";
            		//String mySqlId2 = "select max(id), 1 from dbo.vacc_customers;";
    		    	List<String[]> vaccAppId = new ArrayList<>();
    		    	//List<String[]> customerId = new ArrayList<>();
    		    	vaccAppId = getDBdata(mySqlId1);
    				//customerId = getDBdata(mySqlId2);
    				String sVaccAppId = vaccAppId.get(0)[1];
    				//String sCustId = customerId.get(0)[1];
    				//System.out.println("100 " +sCustId + "200 "+customerId.get(0)[1]);
    				int iVaccAppId = 0;
    				if(sVaccAppId == null) {
    					iVaccAppId = 1;	
    				}
    				else {
    					iVaccAppId = Integer.parseInt(sVaccAppId) + 1;  
    				}
    				int errors = 0;
    				int iNextCustId = 0;
    				String mySqlCustCount = "select count(*), 123 from dbo.vacc_customers where SURNAME = '"+Surname+
    						"' and FIRST_NAME = '"+FirstName+"' and DOB = '"+DOB+"' and SSN = "+SSN+";";
    				System.out.println("Count "+mySqlCustCount);
    				List<String[]> custCount = new ArrayList<>();
    				custCount = getDBdata(mySqlCustCount);
    				String sCount = custCount.get(0)[1];
    				System.out.println("sCount "+sCount);
    				int iCount = Integer.parseInt(sCount);
    				if (iCount != 0) { //if reoccuring person
    					String mySqlCustId = "select ID, 1 from dbo.vacc_customers where SURNAME = '"+Surname+
    							"' and FIRST_NAME = '"+FirstName+"' and DOB = '"+DOB+"' and SSN = "+SSN+";";
    					System.out.println("get id "+mySqlCustId);
    					List<String[]> custId = new ArrayList<>();
    					custId = getDBdata(mySqlCustId);
    					String sId = custId.get(0)[1];
    					iNextCustId = Integer.parseInt(sId);   
    					String isVaccSame = verifyVaccInfo.verifyVaccName(iNextCustId, VaccineId);
    					String sVaccDosage = verifyVaccInfo.verifyShotNum(iNextCustId, VaccineId);
    					if(!isVaccSame.equals("yes")) {
    						JOptionPane.showMessageDialog(frame,  "Selected vaccine not same as previous one ("+isVaccSame+"). Please choose previous vaccine.",
	                                "Error", JOptionPane.WARNING_MESSAGE);
    						//validateResults = validateResults + "\n Selected vaccine not same as previous one ("+isVaccSame+"). Please choose previous vaccine.";
    						errors = 1;
    					}
    					else if(sVaccDosage.substring(0,2).equals("0:")) {
	    					JOptionPane.showMessageDialog(frame, sVaccDosage.substring(2,sVaccDosage.length()),
	                                "Alert", JOptionPane.WARNING_MESSAGE);
	    					errors = 1;
	    				}
    					else { 
	    					String sVaccDate = txtDate.getText(); 
	                		sVaccDate = sVaccDate.replace('/', '-');
	    					String sCheckAppDates = verifyVaccInfo.verifyAppDates(iNextCustId, sVaccDate, VaccineId);
	    					if(!sCheckAppDates.equals("yes")) {
	    						JOptionPane.showMessageDialog(frame, sCheckAppDates,
	                                    "Alert", JOptionPane.WARNING_MESSAGE);
	    						errors = 1;
	    						//System.out.println("step 1.5");
	    					}
    					}
    				}
    				else {
    		    		String mySqlId2 = "select max(id), 1 from dbo.vacc_customers;";
    			    	List<String[]> customerId = new ArrayList<>();
    					customerId = getDBdata(mySqlId2);
    					String sCustId = customerId.get(0)[1];
    					if(sCustId == null) { //if blank table
    						iNextCustId = 1;
    					}
    					else { //if new person
    						iNextCustId = Integer.parseInt(sCustId) + 1;  
    						System.out.println("101 Customer id " + iNextCustId);
    					}
    				}
    				//------------------------check if available
    				//-------------------------------- get dosage
    				if(errors == 0) {
	    				System.out.println("step 0");
	    				String sVaccDosage = verifyVaccInfo.verifyShotNum(iNextCustId, VaccineId);
	    				if(sVaccDosage.substring(0,2).equals("0:")) {
	    					JOptionPane.showMessageDialog(frame, sVaccDosage.substring(2,sVaccDosage.length()),
	                                "Alert", JOptionPane.WARNING_MESSAGE);
	    				}
	    				else {
	    					dosage = Integer.parseInt(sVaccDosage.substring(2,sVaccDosage.length()));
	    					System.out.println("step 1");
	    					//------------------------------------ check the app dates
	    					/*String sVaccDate = txtDate.getText(); 
	                		sVaccDate.replace('/', '-');
	    					String sCheckAppDates = verifyVaccInfo.verifyAppDates(iNextCustId, sVaccDate, VaccineId);
	    					if(!sCheckAppDates.equals("yes")) {
	    						JOptionPane.showMessageDialog(frame, sCheckAppDates,
	                                    "Alert", JOptionPane.WARNING_MESSAGE);
	    						System.out.println("step 1.5");
	    					}
	    					else {*/
							System.out.println("step 2");
							//----------------------------------
							if(validateResults.length() != 0) {
			            		JOptionPane.showMessageDialog(frame, validateResults+"\n\nPlease make correction.",
			                            "Submit Error", JOptionPane.WARNING_MESSAGE);
			            	}
							else {
								String sVaccDate = txtDate.getText(); 
		                		sVaccDate.replace('/', '-');
								System.out.println("step 3");
		        				//put everything together for customer id
		        				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		        				LocalDateTime now = LocalDateTime.now();  
		        				//System.out.println(dtf.format(now));  
		        				String SqlCustomerId = "Insert into dbo.vacc_customers(ID, SURNAME, FIRST_NAME, SEX, DOB, EMAIL, PHONE_NUMBER, SSN, RACE, ETHNICITY, PARENT_CONSENT, UPD_TIME) "
		        						+ "values("+iNextCustId+", '"+Surname+"', '"+FirstName+"', '"+Gender+"', '"+DOB+"', '"+Email+"', '"+Phone+"', "+SSN+", '"+Race+"', '"+Ethnicity+"', "+iParentConsent+", '"+dtf.format(now)+"');";
		        				System.out.println("1 "+SqlCustomerId);
		        				
		        				String SqlVaccApp = "insert into dbo.vaccine_appointments(id, customer_id, vaccine_id, date, time, vaccine_site_id, send_text, shot_number, vcc_lot_number, shot_giver, done_date, upd_time) "
		        						+ "values("+iVaccAppId+", "+iNextCustId+", "+VaccineId+", '"+sVaccDate+"', '"+selectedButton+"', "+VaccSiteId+", "+iReminder+", " +dosage+", null, null, null, '"+dtf.format(now)+"');";
		        				
		        				System.out.println("2 "+SqlVaccApp);
		        				
		        				String sqlIdCheck = "select count(*), 1 from dbo.vacc_customers where ID = "+iNextCustId+";";
		    					List<String[]> checkId = new ArrayList<>();
		        				checkId = getDBdata(sqlIdCheck);
		        				String sCheckId = checkId.get(0)[1];
		        				int iCheckId = Integer.parseInt(sCheckId);
		        				if(iCheckId == 0) {
		        					if(DML(SqlCustomerId) == true && DML(SqlVaccApp) == true) {
		            					//DML(SqlVaccApp);
		            					System.out.println("Inserted into both");
		            					//JOptionPane.showMessageDialog(frame, validateResults+"",
					                    //      "Submit Error", JOptionPane.WARNING_MESSAGE);
		            					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		            				}
		        				}
		        				else {
		        					if(DML(SqlVaccApp) == true) {
		        						System.out.println("Inserted into one");
		            					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		        					}
		        				};
		        				
		        				String sVaccTime = "0";
		        				String sTimeSlot = selectedButton.substring(0, 2);
		        				char checkSecond = sTimeSlot.charAt(1);
		        				System.out.println("full "+sTimeSlot);
		        				if(checkSecond == ':') {
		        					sTimeSlot = sTimeSlot.substring(0,1);
		        					System.out.println("single "+sTimeSlot);
		        				}
		        				//System.out.println(Integer.parseInt(sTimeSlot)+" sTimeSlot int raw");
		        				if(Integer.parseInt(sTimeSlot) > 12) {	
		        					int iVaccTime = Integer.parseInt(sTimeSlot) - 12;
		        					sVaccTime = iVaccTime + " pm";
		        				}
		        				else {
		        					sVaccTime = Integer.parseInt(sTimeSlot) + " am";
		        				}
		        				
		        				//System.out.println(sVaccTime + " selectedbutton after conversion");
		        				String VaccineName = (String)comboBoxVaccName.getSelectedItem();
		        				String VaccSiteName = (String) comboBoxVaccSite.getSelectedItem();
		        				
		        				JOptionPane.showMessageDialog(frame, "You have successfully scheduled a vaccination appointment on "+sVaccDate+" at "+sVaccTime+" at "+VaccSiteName+". You have scheduled your "
		        				+dosage+" dose of the "+VaccineName+" vaccine.",
					                          "", JOptionPane.WARNING_MESSAGE);
		        				btnTimePressed = false;
		        				btnGroupPress = false;
		        				btngrptime.clearSelection();
		        				rdbtn07.setEnabled(false);
		        				rdbtn08.setEnabled(false);
		        				rdbtn09.setEnabled(false);
		        				rdbtn10.setEnabled(false);
		        				rdbtn11.setEnabled(false);
		        				rdbtn12.setEnabled(false);
		        				rdbtn13.setEnabled(false);
		        				rdbtn14.setEnabled(false);
		        				rdbtn15.setEnabled(false);
		        				rdbtn16.setEnabled(false);
		        				rdbtn17.setEnabled(false);
		        				rdbtn18.setEnabled(false);
	    						//}
		        				
		        				//make max of 10 people per time slot per date in sql table
		        				//make function where it checks if there are 10 people in time slot already
		        				//refresh availability when submit is pressed.
	    					}
	    				}
    				}
    				
    				//-------------------------------------
    				//get unique data for 2nd dosage
    				/*if(comboBoxVaccName.getSelectedIndex() == 2 || comboBoxVaccName.getSelectedIndex() == 3) {
    					String mySqlCustInfo = "select ID, 1 from dbo.vacc_customers where SURNAME = '"+Surname+"' and FIRST_NAME = '"+FirstName+"' and DOB = "+DOB+" and SSN = "+SSN +";";
    					List<String[]> CustInfo = new ArrayList<>();
        				CustInfo = getDBdata(mySqlCustInfo);
        				if(CustInfo.isEmpty() == true) {
        					//nothing
        				}
        				else {
        					String custId = CustInfo.get(0)[1];
        					String sqlShot = "Select shot_number, 1 from dbo.vaccine_appointments where customer_id = "+custId+";";
        					List<String[]> shotNum = new ArrayList<>();
            				shotNum = getDBdata(sqlShot);
            				if(shotNum.size() == 1) {
            					dosage = 2;
            				}
            				else if(shotNum.size() == 2) {
            					//show warning
            				}
        				}
    				}*/
    				
            	}
            }
		});
                
		comboBoxVaccName.addActionListener ((ActionListener) new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("addActionListener");
		    	//String varName = (String)comboBoxVaccineName.getSelectedItem();
		    	String selectedVaccine = comboBoxVaccName.getSelectedItem().toString();
		    	String sSelectedVaccineName = (String) comboBoxVaccName.getSelectedItem();
		    	System.out.println("vaccine name "+sSelectedVaccineName);
		    	String mySql2 = "Select side_effects, id from dbo.vaccines where name = '"+selectedVaccine+"';";
		    	//System.out.println("statement= " +mySql2);
		    	List<String[]> vaccineWarning = new ArrayList<>();
				vaccineWarning = getDBdata(mySql2);
				//System.out.println("sql results "+vaccineWarning);
				txtWarning.setText(vaccineWarning.get(0)[1]);
				//System.out.println(vaccineWarning.get(0)[1]);
				//txtWarning.setText(vaccineWarning);
				String mySqlVaccId = "select num_of_shots, 1 from dbo.vaccines where name = '"+sSelectedVaccineName+"';";
				List<String[]> vaccShot = new ArrayList<>();
				vaccShot = getDBdata(mySqlVaccId);
				String sShotNum = vaccShot.get(0)[1];
				int iShotNum = Integer.parseInt(sShotNum);
				
			}
		});
		txtSSN.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				String SSNvalue = txtSSN.getText();
				String SSNresult = regexValidators.SSNvalidator4digit(SSNvalue);
            	if (SSNresult != "yes" ) {
            		//print SSNresult errorMsg
            	}
		    }
		});
		txtPhone.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				String phoneNum = txtPhone.getText();
				String phoneResult = regexValidators.phoneNumValidator(phoneNum);
				if (phoneResult != "yes") { 
					//print phoneResult errorMsg
				}
		    }
		});
    
};

	
	/*public void itemStateChanged(ItemEvent e)
    {
        // if the state combobox is changed
		System.out.println("itemStateChanged");
        if (e.getSource() == comboBoxVaccineName) {
        	String selectedVaccine = comboBoxVaccineName.getSelectedItem().toString();
	    	System.out.println(selectedVaccine);
	    	String mySql2 = "Select side_effects from dbo.vaccines where name = '"+selectedVaccine+"';";
	    	List<String[]> vaccineWarning = new ArrayList<>();
			vaccineWarning = getDBdata(mySql2);
			txtWarning.setText(vaccineWarning.get(0)[0]);
			System.out.println(vaccineWarning.get(0)[0]);
        }
    }*/
	public String getSelectedButton(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
            	String btnTime = button.getText();
            	//System.out.println("111 "+btnTime);
            	int length = btnTime.length();
            	String sTimeSlot = btnTime.substring(0,2).trim();
            	int iTimeSlot = Integer.parseInt(sTimeSlot);
            	String sAmPm = btnTime.substring(length-2, length);
            	//System.out.println("time=|"+sTimeSlot + "| amPm=|"+sAmPm+"|");
            	if(sAmPm.equals("pm") && iTimeSlot != 12) {
            		iTimeSlot = iTimeSlot + 12;
            		System.out.println("1020 "+iTimeSlot);
            	}
            	else {
            		if(sTimeSlot.length() == 1) {
            			sTimeSlot = "0"+sTimeSlot;
            		}
            	}
            	//System.out.println("time=|"+iTimeSlot + "| amPm=|"+sAmPm+"|");
            	sTimeSlot = iTimeSlot + "";
            	sTimeSlot = sTimeSlot+":00:00";
            	
                return sTimeSlot;
            }
        }
        return null;
	};
	public static Boolean DML(String mySql) {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // Start JDBC
	    String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=MARK";
	    String userName="sa";
	    String userPwd="SA";
	    Connection dbConn=null;
	    boolean rs2;
	    try {
	       Class.forName(driverName);
	       dbConn = DriverManager.getConnection(dbURL,userName,userPwd);
	       //System.out.println("1");
	       Statement myDbConn2 = dbConn.createStatement();
	       //System.out.println("2");
	       rs2 = myDbConn2.execute(mySql);
	       //System.out.println("insert returned " + rs2);
	    }
	    catch (Exception e){
	    	e.printStackTrace();
	    	rs2 = true;
	    	//System.out.println("error");
	    }
	    
	    return !rs2;
	}
	
	public static List<String[]> getDBdata(String mySql) {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // Start JDBC
	    String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=MARK";
	     String userName="sa";
	     String userPwd="SA";
	     Connection dbConn=null;
	     try {
	        Class.forName(driverName);
	        dbConn = DriverManager.getConnection(dbURL,userName,userPwd);
	        Statement myDbConn2 = dbConn.createStatement();
	        ResultSet rs2 = myDbConn2.executeQuery(mySql);
	        ResultSetMetaData rsmd = rs2.getMetaData();
	        int colCount = rsmd.getColumnCount();
	        int rowCount = 0;
	        String[][] arQrs;
	        cols = new ArrayList<>();
	        int iRow;
	        iRow = 0;
	        if(rs2!=null){
	            while (rs2.next()) {
	          	  String[] tempRow;
	          	  tempRow = new String[colCount];
	                for(int i = 1; i <colCount;i++) {
	                    tempRow[i]=rs2.getString(i);
	                }
	                cols.add(tempRow);
	                iRow++;
	            }
	        }
	        /*for(int i = 0; i <iRow;i++) {
	      	  for(int j = 1; j <colCount;j++) {
	      		  System.out.println("row "+ i + " Column " + j +" " +cols.get(i)[j]);
	      	  }
	        }*/
	     }
	     catch (Exception e) {
	         e.printStackTrace();
	     }
		return cols;
	  }

	public static void main(String[] args) {
		List<String[]> cols = new ArrayList<>();

		//------------------------------------ For DB Connection
		String mySql ="Select * from dbo.vaccines";
		List<String[]> DBdata = new ArrayList<>();
		DBdata = getDBdata(mySql);
		//System.out.println(DBdata.size() + " rows");
		//System.out.println(DBdata.get(0).length + " Columns");
		for(int i = 0; i <DBdata.size();i++) {
			//comboBoxVaccineName.addItem(DBdata.get(i)[1]);
	      	  //for(int j = 1; j <DBdata.get(0).length;j++) {
	      		 // System.out.println("row "+ i + " Column " + j +" " +DBdata.get(i)[j]);
	      	  //}
		}
	    //------------------------------------
		VaccineApointment x = new VaccineApointment();
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes everything associated with this program when closed including calendar
		x.setSize(1040, 670);
		x.setVisible(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if(event.getPropertyName().equals("selectedDate")) {
			btnTimePressed = false;
			btnGroupPress = false;
			btngrptime.clearSelection();
			rdbtn07.setEnabled(false);
			rdbtn08.setEnabled(false);
			rdbtn09.setEnabled(false);
			rdbtn10.setEnabled(false);
			rdbtn11.setEnabled(false);
			rdbtn12.setEnabled(false);
			rdbtn13.setEnabled(false);
			rdbtn14.setEnabled(false);
			rdbtn15.setEnabled(false);
			rdbtn16.setEnabled(false);
			rdbtn17.setEnabled(false);
			rdbtn18.setEnabled(false);
			java.util.Calendar cal = (java.util.Calendar)event.getNewValue();
			selDate = cal.getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			gsVaccDate = dateFormat.format(selDate);  
			//System.out.println("1 gsVaccDate = "+gsVaccDate);
			txtDate.setValue(selDate);
		}
	}
}


