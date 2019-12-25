package newcalculator;
import java.awt.EventQueue;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class Mainframe{

	public JFrame frame;
	
	public static double Income=0;
	public static double Expense=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainframe obj2 = new Mainframe();//wriitenbyyash Login constructor will be calld.
					                               /*line1:::if we keep frame as visble and then disposing ,again 
                                                   keeping as visible then setText as some value then it works fine..
                                                    otherwise setText is not working */
					obj2.methodGettingTotalIncome();
					obj2.methodGettingTotaExpense();
					obj2.frame.setVisible(true); 
					
			
					obj2.frame.dispose();
					obj2.frame.setVisible(true);
					obj2.textField1.setText(Double.toString(Mainframe.Income)+"   "+"INR");
					obj2.textField2.setText(Double.toString(Mainframe.Expense)+"   "+"INR");
					obj2.textField3.setText(Double.toString(Mainframe.Income-Mainframe.Expense)+"   "+"INR");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	
	
	Connection connection1 =null;
	public JTextField textField1;
	public JTextField textField2;
	public JTextField textField3;
	/**
	 * Create the application.
	 */
	public Mainframe() {
		initialize();
		
		connection1=sqliteConnection.dbConnector();//after calling login contructor from main initalize()methdrun and then connection to data base will be done 
	
	}

	void methodGettingTotalIncome(){
		try {
			String query="select Amount from IncomeTable";
			PreparedStatement pst=connection1.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				String name=rs.getString(1);
				double value2 = Double.parseDouble(name);//if amount field is null in database then this will throw exception
				Income=Income+value2;
				
			}
			
			pst.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	void methodGettingTotaExpense(){
		
		try {
			String query="select Amount from ExpenseTable";
			PreparedStatement pst=connection1.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				String name=rs.getString(1);
				 
				double value3 = Double.parseDouble(name);//if amount field is null in database then this will throw exception
				Expense=Expense+value3;
				
			}
			
			pst.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("ExpenseManager");
		frame.setBounds(100, 100, 880, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField1 = new JTextField();
		textField1.setEditable(false);
		textField1.setBounds(507, 150, 86, 20);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JButton button1 = new JButton("INCOME");
		button1.setFont(new Font("Calisto MT", Font.PLAIN, 15));
		button1.setBounds(380, 316, 209, 40);
		Image img=new ImageIcon(this.getClass().getResource("/8.png")).getImage();
		button1.setIcon(new ImageIcon(img));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				frame.dispose();
				frame2 obj1=new frame2();
				obj1.setVisible(true);
			
			}
			
			
		});
		frame.getContentPane().add(button1);
		
		textField2 = new JTextField();
		textField2.setEditable(false);
		textField2.setBounds(507, 208, 86, 20);
		frame.getContentPane().add(textField2);
		textField2.setColumns(10);
		
		JButton button2 = new JButton(" EXPENSE");
		button2.setFont(new Font("Calisto MT", Font.PLAIN, 15));
		button2.setBounds(599, 316, 205, 40);
		Image img2=new ImageIcon(this.getClass().getResource("/9.png")).getImage();
		button2.setIcon(new ImageIcon(img2));
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				frame3 obj1=new frame3();
				obj1.setVisible(true);
			}
		});
		frame.getContentPane().add(button2);
		
		textField3 = new JTextField();
		textField3.setEditable(false);
		textField3.setBounds(507, 262, 86, 20);
		frame.getContentPane().add(textField3);
		textField3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Your Income");
		lblNewLabel.setBounds(380, 151, 117, 14);
		lblNewLabel.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Your Expense");
		lblNewLabel_1.setBounds(380, 206, 117, 20);
		lblNewLabel_1.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Your Balance");
		lblNewLabel_2.setBounds(380, 263, 116, 14);
		lblNewLabel_2.setFont(new Font("Calisto MT", Font.PLAIN, 16));
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton button3 = new JButton("View Income Record");
		button3.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		button3.setBounds(635, 149, 179, 23);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.dispose();
				FrameViewDataIncome obj=new FrameViewDataIncome();
				obj.setVisible(true);
				
				try {
					String query="select * from IncomeTable";
					PreparedStatement pst=connection1.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					obj.table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		frame.getContentPane().add(button3);
		
		JButton button4 = new JButton("View Expense Record");
		button4.setFont(new Font("Calisto MT", Font.PLAIN, 14));
		button4.setBounds(635, 207, 179, 23);
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				FrameViewDataExpense obj=new FrameViewDataExpense();
				obj.setVisible(true);
				
				try {
					String query="select * from ExpenseTable";
					PreparedStatement pst=connection1.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					obj.table.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(button4);
		
		JLabel jlabel3 = new JLabel("");
		jlabel3.setBounds(10, 80, 824, 356);
		Image img3=new ImageIcon(this.getClass().getResource("/90.png")).getImage();
		jlabel3.setIcon(new ImageIcon(img3));
		frame.getContentPane().add(jlabel3);
		
		JLabel lblNewLabel_3 = new JLabel("     Expense Manager   ");
		lblNewLabel_3.setBounds(268, 55, 229, 55);
		Image img4=new ImageIcon(this.getClass().getResource("/11.png")).getImage();
		lblNewLabel_3.setIcon(new ImageIcon(img4));
		lblNewLabel_3.setFont(new Font("Calisto MT", Font.PLAIN, 18));
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(499, 55, 46, 55);
		Image img5=new ImageIcon(this.getClass().getResource("/11.png")).getImage();
		lblNewLabel_4.setIcon(new ImageIcon(img5));
		frame.getContentPane().add(lblNewLabel_4);
		
		
	}
}
