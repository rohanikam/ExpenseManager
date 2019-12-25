package newcalculator;

import java.awt.BorderLayout;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JLocaleChooser;
import com.toedter.components.JSpinField;

public class FrameViewDataIncome extends JFrame {

	private JPanel contentPane;
	public JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameViewDataIncome frame = new FrameViewDataIncome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection connection1 =null;
	private JLabel lblYourIncomeRecord;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	/**
	 * Create the frame.
	 */
	public FrameViewDataIncome() {
		
		connection1=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1047, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 73, 623, 370);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblYourIncomeRecord = new JLabel("Your Income Record");
		lblYourIncomeRecord.setBounds(616, 26, 156, 14);
		lblYourIncomeRecord.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		contentPane.add(lblYourIncomeRecord);
		
		btnNewButton = new JButton("GO BACK");
		Image img11=new ImageIcon(this.getClass().getResource("/50.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img11));
		btnNewButton.setBounds(191, 293, 150, 27);
		btnNewButton.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Mainframe obj2=new Mainframe();
				

			    obj2.frame.setVisible(true);
				obj2.textField1.setText(Double.toString(Mainframe.Income)+"   "+"INR");
				obj2.textField2.setText(Double.toString(Mainframe.Expense)+"   "+"INR");
				obj2.textField3.setText(Double.toString(Mainframe.Income-Mainframe.Expense)+"   "+"INR");
				
				
				
				
			
			}
			
			
			
		});
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("");
		Image img7=new ImageIcon(this.getClass().getResource("/30.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img7));
		lblNewLabel.setBounds(573, 11, 47, 41);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		Image img8=new ImageIcon(this.getClass().getResource("/30.png")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(img8));
		lblNewLabel_1.setBounds(776, 11, 47, 41);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Calisto MT", Font.BOLD, 14));
		 comboBox.addItem("January");
		 comboBox.addItem("February");
		 comboBox.addItem("March");
		 comboBox.addItem("April");
		 comboBox.addItem("May");
		 comboBox.addItem("June");
		 comboBox.addItem("July");
		 comboBox.addItem("August");
		 comboBox.addItem("September");
		 comboBox.addItem("October");
		 comboBox.addItem("November");
		 comboBox.addItem("December");
		 comboBox.setSelectedItem(null);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			
				
				try {
					
					String temp=(String)comboBox.getSelectedItem();
				
					
					String query="select * from IncomeTable where Month=?" ;
					
					PreparedStatement pst=connection1.prepareStatement(query);
					pst.setString(1, temp);/*wasted almost 5 hours to find this code and month=?..if you write month='temp'...then it
					                                will search as month =temp in database..but we want the month=string which is inside temp*/
					ResultSet rs=pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		comboBox.setBounds(135, 95, 149, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Record By Month");
		lblNewLabel_2.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 94, 118, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Record By Date");
		lblNewLabel_3.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 165, 102, 23);
		contentPane.add(lblNewLabel_3);
		 
		
		JLabel lblNewLabel_4 = new JLabel("Record by Category");
		lblNewLabel_4.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 239, 130, 20);
		contentPane.add(lblNewLabel_4);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Calisto MT", Font.BOLD, 14));
		comboBox_2.addItem("Loan");
		 comboBox_2.addItem("Salary");
		 comboBox_2.addItem("Pocket Money");
		 comboBox_2.addItem("Gift");
		 comboBox_2.addItem("Other");
		 comboBox_2.setSelectedItem(null);
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                 try {            
					
					String temp=(String)comboBox_2.getSelectedItem();
				
					
					String query="select * from IncomeTable where Category=?" ;
					PreparedStatement pst=connection1.prepareStatement(query);
					pst.setString(1, temp);/*wasted almost 5 hours to find this code and month=?..if you write month='temp'...then it
					                                will search as month =temp in database..but we want the month=string which is inside temp*/
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		comboBox_2.setBounds(135, 240, 149, 20);
		contentPane.add(comboBox_2);
		
		JButton btnNewButton_1 = new JButton("View All Record");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from IncomeTable";
					PreparedStatement pst=connection1.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		btnNewButton_1.setBounds(31, 293, 150, 27);
		contentPane.add(btnNewButton_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				}
		});
		dateChooser.setDateFormatString("dd MMMM yyyy");
		dateChooser.setBounds(135, 168, 149, 20);
		contentPane.add(dateChooser);
		Date obj=new Date();
		 dateChooser.setDate(obj);//set current date 
		
		JButton btnNewButton_2 = new JButton("View ");
		btnNewButton_2.setFont(new Font("Calisto MT", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					SimpleDateFormat dateobj=new SimpleDateFormat("dd");
					SimpleDateFormat monthobj=new SimpleDateFormat("MMMM");
					SimpleDateFormat yearobj=new SimpleDateFormat("yyyy");
					
					String temp1=dateobj.format(dateChooser.getDate());
					String temp2=monthobj.format(dateChooser.getDate());
					String temp3=yearobj.format(dateChooser.getDate());
					//System.out.println(temp1+temp2+temp3);
					
					String query="select * from IncomeTable where Day=? AND Month=? AND Year=? " ;
					PreparedStatement pst=connection1.prepareStatement(query);
					pst.setString(1, temp1);/*wasted almost 5 hours to find this code and month=?..if you write month='temp'...then it
					                                will search as month =temp in database..but we want the month=string which is inside temp*/
					pst.setString(2, temp2);
					pst.setString(3, temp3);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(288, 165, 75, 23);
		contentPane.add(btnNewButton_2);
		
		
		
		
		
		
			}
}
