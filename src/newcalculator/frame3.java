package newcalculator;

import java.awt.BorderLayout;
import javax.swing.*;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import java.util.Date;
import java.text.SimpleDateFormat;

public class frame3 extends JFrame {

	private JPanel contentPane;
	private JTextField textField24;
	
	private JDateChooser dateChooser2;/*i have declred this as instance variable...bcoz this instance i can use anywhere in my class.
                               i had to use that same instance datechooser as datechooser.getdata()in try block.
                               .so i have to make it as instance variale     */
	JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame3 frame = new frame3();
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
	private JTextField textField17;

	/**
	 * Create the frame.
	 */
	public frame3() {
		connection1=sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblNewLabel.setBounds(185, 156, 67, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Amount");
		lblNewLabel_3.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(185, 220, 67, 17);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Category");
		lblNewLabel_5.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(185, 282, 75, 18);
		contentPane.add(lblNewLabel_5);
		
		textField24 = new JTextField();
		textField24.setBounds(286, 220, 86, 20);
		contentPane.add(textField24);
		textField24.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		Image img13=new ImageIcon(this.getClass().getResource("/60.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img13));
		btnNewButton.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {	
					String str=textField24.getText();
					int check=frame3.isNumber(str);//if amount is other than number then check will be 0 
						if(str.equals(""))
						{
							
							JOptionPane.showMessageDialog(null," Amount Can Not Be Empty");
						}
						
						else if(check==0)
						{
							JOptionPane.showMessageDialog(null," Enter Proper Amount");
						}
					
					
				else {
					SimpleDateFormat dateobj=new SimpleDateFormat("dd");
					SimpleDateFormat monthobj=new SimpleDateFormat("MMMM");
					SimpleDateFormat yearobj=new SimpleDateFormat("yyyy");
					
					
					String query="insert into ExpenseTable (Day,Month,Year,Amount,category,Description) values (?,?,?,?,?,?)";
				    PreparedStatement pst=connection1.prepareStatement(query);
				
				    pst.setString(1,dateobj.format(dateChooser2.getDate()));
					pst.setString(2,monthobj.format(dateChooser2.getDate()));
					pst.setString(3,yearobj.format(dateChooser2.getDate()));
					pst.setString(4,textField24.getText());
					pst.setString(5,(String)comboBox.getSelectedItem());
					pst.setString(6,textField17.getText());
					
					pst.execute();
					
					 JOptionPane.showMessageDialog(null," DATA SAVED SUCCESSFULLY"); 
					pst.close();
					
			dispose();
			
			Mainframe obj2=new Mainframe();
			double value = Double.valueOf(textField24.getText());
			Mainframe.Expense=Mainframe.Expense+value;
			obj2.frame.setVisible(true);
			obj2.textField1.setText(Double.toString(Mainframe.Income)+"   "+"INR");
			obj2.textField2.setText(Double.toString(Mainframe.Expense)+"   "+"INR");
			obj2.textField3.setText(Double.toString((Mainframe.Income)-(Mainframe.Expense))+"   "+"INR");
				}//else closed
				}//try closed
			catch (Exception e3) {
					e3.printStackTrace();
				}
			}
			
			
		});
		btnNewButton.setBounds(147, 405, 115, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Enter Expense");
		lblNewLabel_4.setFont(new Font("Calisto MT", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(226, 78, 146, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("");
		Image img14=new ImageIcon(this.getClass().getResource("/70.png")).getImage();
		 lblNewLabel_6.setIcon(new ImageIcon(img14));
		lblNewLabel_6.setBounds(32, 140, 129, 218);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton_1 = new JButton("BACK");
		Image img17=new ImageIcon(this.getClass().getResource("/50.png")).getImage();
		btnNewButton_1.setIcon(new ImageIcon(img17));
		btnNewButton_1.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				Mainframe obj2=new Mainframe();
				

			    obj2.frame.setVisible(true);
				obj2.textField1.setText(Double.toString(Mainframe.Income)+"   "+"INR");
				obj2.textField2.setText(Double.toString(Mainframe.Expense)+"   "+"INR");
				obj2.textField3.setText(Double.toString(Mainframe.Income-Mainframe.Expense)+"   "+"INR");
				
			}
		});
		btnNewButton_1.setBounds(272, 405, 129, 23);
		contentPane.add(btnNewButton_1);
		
	    dateChooser2 = new JDateChooser();
	    dateChooser2.getCalendarButton().setFont(new Font("Calisto MT", Font.PLAIN, 16));
		dateChooser2.setDateFormatString("dd MMMM yyyy");
		dateChooser2.setBounds(283, 156, 146, 20);
		contentPane.add(dateChooser2);
		
		Date obj=new Date();
		 dateChooser2.setDate(obj);
		 
		 JLabel lblNewLabel_1 = new JLabel("");
		 Image img112=new ImageIcon(this.getClass().getResource("/20.png")).getImage();
		 lblNewLabel_1.setIcon(new ImageIcon(img112));
		 lblNewLabel_1.setBounds(160, 66, 57, 45);
		 contentPane.add(lblNewLabel_1);
		 
		 JLabel lblNewLabel_2 = new JLabel("");
		 Image img113=new ImageIcon(this.getClass().getResource("/20.png")).getImage();
		 lblNewLabel_2.setIcon(new ImageIcon(img113));
		 lblNewLabel_2.setBounds(361, 66, 86, 45);
		 contentPane.add(lblNewLabel_2);
		 
		 
		 comboBox = new JComboBox();
		 comboBox.setFont(new Font("Calisto MT", Font.BOLD, 13));
		 comboBox.addItem("College Food");
		 comboBox.addItem("Education");
		 comboBox.addItem("Personal");
		 comboBox.addItem("Room Credit");
		 comboBox.addItem("Transport");
		 comboBox.addItem("Home");
		 
		 
		 
		 comboBox.addItem("Other");
		 comboBox.setSelectedItem("Personal");
		 comboBox.setBounds(286, 281, 139, 23);
		 contentPane.add(comboBox);
		 
		 textField17 = new JTextField();
		 textField17.setBounds(286, 338, 146, 20);
		 contentPane.add(textField17);
		 textField17.setColumns(10);
		 
		 JLabel lblNewLabel_7 = new JLabel("Description");
		 lblNewLabel_7.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		 lblNewLabel_7.setBounds(185, 340, 102, 18);
		 contentPane.add(lblNewLabel_7);
	}
}
