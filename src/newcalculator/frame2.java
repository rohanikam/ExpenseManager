package newcalculator;

import java.awt.BorderLayout;
import javax.swing.*;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import com.toedter.calendar.JDateChooser;

import java.util.Date;
import java.text.SimpleDateFormat;




public class frame2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField14;
	private JDateChooser dateChooser;/*i have declred this as instance variable...bcoz this instance i can use anywhere in my class.
	                                  i had to use that same instance datechooser as datechooser.getdata()in try block.
	                                  .so i have to make it as instance variale   ...similary i have done same thing for Jcombobox  */
	private JComboBox comboBox;
	                                  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame2 frame = new frame2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static int isNumber(String str2) {
	    try {
	        double v = Double.parseDouble(str2);
	        return 1;
	    } catch (NumberFormatException nfe) {
	    }
	    return 0;
	}
	
	Connection connection1 =null;
	private JTextField textField16;
	
	

	/**
	 * Create the frame.
	 */
	public frame2() {
		connection1=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Amount");
		lblNewLabel.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblNewLabel.setBounds(174, 214, 89, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(174, 277, 89, 18);
		contentPane.add(lblNewLabel_1);
		
		textField14 = new JTextField();
		textField14.setBounds(273, 213, 86, 20);
		contentPane.add(textField14);
		textField14.setColumns(10);
		
		JButton Button5 = new JButton("OK");
		Image img12=new ImageIcon(this.getClass().getResource("/60.png")).getImage();
		Button5.setIcon(new ImageIcon(img12));
		Button5.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		Button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {	
					
				String str=textField14.getText();
				int check=frame2.isNumber(str);//if amount is other than number then check will be 0 
					if(str.equals(""))
					{
						
						JOptionPane.showMessageDialog(null," Amount Can Not Be Empty");
					}
					
					else if(check==0)
					{
						JOptionPane.showMessageDialog(null," Enter Proper Amount");
					}
					
					else
					{
					
					SimpleDateFormat dateobj=new SimpleDateFormat("dd");
					SimpleDateFormat monthobj=new SimpleDateFormat("MMMM");
					SimpleDateFormat yearobj=new SimpleDateFormat("yyyy");
					
					
					
					String query="insert into IncomeTable (Day,Month,Year,Amount,category,Description) values (?,?,?,?,?,?)";
				    PreparedStatement pst=connection1.prepareStatement(query);
				    
					pst.setString(1,dateobj.format(dateChooser.getDate()));
					pst.setString(2,monthobj.format(dateChooser.getDate()));
					pst.setString(3,yearobj.format(dateChooser.getDate()));
					pst.setString(4,textField14.getText());
					pst.setString(5,(String)comboBox.getSelectedItem());
					pst.setString(6,textField16.getText());
					
					pst.execute();
					
					 JOptionPane.showMessageDialog(null," DATA SAVED SUCCESSFULLY"); 
					pst.close();
					
			dispose();
			Mainframe obj2=new Mainframe();
			
			double value = Double.valueOf(textField14.getText());
			Mainframe.Income=(Mainframe.Income)+value;
			obj2.frame.setVisible(true);
			obj2.textField1.setText(Double.toString(Mainframe.Income)+"   "+"INR");
			obj2.textField2.setText(Double.toString(Mainframe.Expense)+"   "+"INR");
			obj2.textField3.setText(Double.toString(Mainframe.Income-Mainframe.Expense)+"   "+"INR");
					
					}//else cloed
				}//try closed
				 catch (Exception e3) {
					e3.printStackTrace();
				}
					}
				
				
			

		});
		Button5.setBounds(145, 399, 118, 23);
		contentPane.add(Button5);
		
		JLabel lblNewLabel_5 = new JLabel("Date");
		lblNewLabel_5.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(174, 155, 81, 20);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Income");
		lblNewLabel_2.setFont(new Font("Calisto MT", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(205, 83, 123, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_6 = new JLabel("");
		Image img6=new ImageIcon(this.getClass().getResource("/70.png")).getImage();
		 lblNewLabel_6.setIcon(new ImageIcon(img6));
		lblNewLabel_6.setBounds(10, 108, 125, 287);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("BACK");
		Image img16=new ImageIcon(this.getClass().getResource("/50.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img16));
		btnNewButton.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Mainframe obj2=new Mainframe();
				

			    obj2.frame.setVisible(true);
				obj2.textField1.setText(Double.toString(Mainframe.Income)+"   "+"INR");
				obj2.textField2.setText(Double.toString(Mainframe.Expense)+"   "+"INR");
				obj2.textField3.setText(Double.toString(Mainframe.Income-Mainframe.Expense)+"   "+"INR");
				

			}
		});
		btnNewButton.setBounds(274, 399, 134, 23);
		contentPane.add(btnNewButton);
		
		 dateChooser = new JDateChooser();
		 dateChooser.getCalendarButton().setFont(new Font("Calisto MT", Font.PLAIN, 16));/*equvivaent to JDateChooser dateChooser = new JDateChooser();i have already declared JDateChooser dateChooser as instance
		                                     variable;
		                                     this 4 lines were already created by IDE hen we drag jdatechooser*/
		dateChooser.setDateFormatString("dd MMMM yyyy");
		dateChooser.setBounds(271, 155, 166, 20);
		contentPane.add(dateChooser);
		Date obj=new Date();
		 dateChooser.setDate(obj);//set current date 
		 
		 JLabel lblNewLabel_3 = new JLabel("");
		 Image img110=new ImageIcon(this.getClass().getResource("/20.png")).getImage();
		 lblNewLabel_3.setIcon(new ImageIcon(img110));
		 lblNewLabel_3.setBounds(145, 77, 50, 31);
		 contentPane.add(lblNewLabel_3);
		 
		 JLabel lblNewLabel_4 = new JLabel("");
		 Image img111=new ImageIcon(this.getClass().getResource("/20.png")).getImage();
		 lblNewLabel_4.setIcon(new ImageIcon(img111));
		 
		 lblNewLabel_4.setBounds(326, 77, 95, 31);
		 contentPane.add(lblNewLabel_4);
		 
		 comboBox = new JComboBox();
		 comboBox.setFont(new Font("Calisto MT", Font.BOLD, 13));
		 comboBox.addItem("Loan");
		 comboBox.addItem("Salary");
		 comboBox.addItem("Pocket Money");
		 comboBox.addItem("Gift");
		 comboBox.addItem("Other");
		 comboBox.setSelectedItem("Pocket Money");
		 
		 comboBox.setBounds(270, 278, 151, 23);
		 contentPane.add(comboBox);
		 
		 textField16 = new JTextField();
		 textField16.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		 textField16.setBounds(270, 345, 123, 20);
		 contentPane.add(textField16);
		 textField16.setColumns(10);
		 
		 JLabel lblNewLabel_7 = new JLabel("Description");
		 lblNewLabel_7.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		 lblNewLabel_7.setBounds(174, 343, 89, 23);
		 contentPane.add(lblNewLabel_7);
	}
}
