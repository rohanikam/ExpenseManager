package newcalculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

public class FrameViewDataExpense extends JFrame {

	private JPanel contentPane;
	public JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameViewDataExpense frame = new FrameViewDataExpense();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection1 =null;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JButton btnNewButton_1;
	private JComboBox comboBox_2;
	private JButton btnNewButton_2;

	/**
	 * Create the frame.
	 */
	public FrameViewDataExpense() {
		connection1=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1076, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Expense Record");
		lblNewLabel.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblNewLabel.setBounds(595, 23, 187, 20);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 63, 608, 367);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
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
		
		lblNewLabel_1 = new JLabel("");
		Image img9=new ImageIcon(this.getClass().getResource("/45.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img9));
		lblNewLabel_1.setBounds(197, 24, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		Image img10=new ImageIcon(this.getClass().getResource("/45.png")).getImage();
		lblNewLabel_2.setIcon(new ImageIcon(img10));
		lblNewLabel_2.setBounds(792, 11, 47, 41);
		contentPane.add(lblNewLabel_2);
		
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
		
		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Calisto MT", Font.BOLD, 14));
		comboBox_1.addItem("January");
		 comboBox_1.addItem("February");
		 comboBox_1.addItem("March");
		 comboBox_1.addItem("April");
		 comboBox_1.addItem("May");
		 comboBox_1.addItem("June");
		 comboBox_1.addItem("July");
		 comboBox_1.addItem("August");
		 comboBox_1.addItem("September");
		 comboBox_1.addItem("October");
		 comboBox_1.addItem("November");
		 comboBox_1.addItem("December");
		 comboBox_1.setSelectedItem(null);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		try {
					
					String temp=(String)comboBox_1.getSelectedItem();
				
					
					String query="select * from ExpenseTable where Month=?" ;
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
		
		comboBox_1.setBounds(135, 95, 149, 20);
		contentPane.add(comboBox_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd MMMM yyyy");
		dateChooser.setBounds(135, 168, 149, 20);
		contentPane.add(dateChooser);
		Date obj=new Date();
		 dateChooser.setDate(obj);//set current date 
		 
		 btnNewButton_1 = new JButton("View");
		 btnNewButton_1.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		try {
					SimpleDateFormat dateobj=new SimpleDateFormat("dd");
					SimpleDateFormat monthobj=new SimpleDateFormat("MMMM");
					SimpleDateFormat yearobj=new SimpleDateFormat("yyyy");
					
					String temp1=dateobj.format(dateChooser.getDate());
					String temp2=monthobj.format(dateChooser.getDate());
					String temp3=yearobj.format(dateChooser.getDate());
					//System.out.println(temp1+temp2+temp3);
					
					String query="select * from ExpenseTable where Day=? AND Month=? AND Year=? " ;
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
		 btnNewButton_1.setFont(new Font("Calisto MT", Font.PLAIN, 15));
		 btnNewButton_1.setBounds(288, 165, 75, 23);
		 contentPane.add(btnNewButton_1);
		 
		 comboBox_2 = new JComboBox();
		 comboBox_2.setFont(new Font("Calisto MT", Font.BOLD, 14));
			
			comboBox_2.addItem("College Food");
			comboBox_2.addItem("Education");
			comboBox_2.addItem("Personal");
			comboBox_2.addItem("Room Credit");
			comboBox_2.addItem("Transport");
			comboBox_2.addItem("Home");
			 
			 comboBox_2.setSelectedItem(null);
		 comboBox_2.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		 try {            
						
						String temp=(String)comboBox_2.getSelectedItem();
					
						
						String query="select * from ExpenseTable where Category=?" ;
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
		 
		 JButton btnNewButton_11 = new JButton("View All Record");
			btnNewButton_11.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String query="select * from ExpenseTable";
						PreparedStatement pst=connection1.prepareStatement(query);
						ResultSet rs=pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
			});
			btnNewButton_11.setFont(new Font("Calisto MT", Font.PLAIN, 16));
			btnNewButton_11.setBounds(31, 293, 150, 27);
			contentPane.add(btnNewButton_11);
		
	}
}
