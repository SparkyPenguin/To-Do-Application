package todolist;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class todo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JEditorPane textArea;
	private PreparedStatement pst;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					todo frame = new todo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	Connection con = null;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
//	public void refresh() {
//		
//	}
	
	public todo() {
		con=(Connection) DB.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 718);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 218, 185));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TO-DO LIST");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(572, 20, 140, 76);
		contentPane.add(lblNewLabel);
		textArea = new JEditorPane();
		textArea.setBounds(189, 328, 258, 82);
		contentPane.add(textArea);
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getID());
				System.out.println(e.getActionCommand());
				System.out.println(e.getID());
				System.out.println("Inside Add button");
				try {
					String Title=textField.getText();
					String Priority=textArea.getText();
					String State=textField_1.getText();
					String Date=textField_2.getText();
					String Task_ID=textField_3.getText();
//					System.out.println(Title+" "+Priority+" "+State+" "+Date);
//					System.out.println("debug 1");
					pst=(PreparedStatement)con.prepareStatement("insert into todolist values(?,?,?,?,?)");
					pst.setInt(1,Integer.parseInt(Task_ID));
					pst.setString(2, Title);
					pst.setString(3, State);
					pst.setString(4, Date);
					pst.setString(5, Priority);
//					System.out.println("debug 2");
					pst.executeUpdate();
//					System.out.println("debug 3");
	
					JOptionPane.showMessageDialog(null, "task added");
					textField_3.setText("");
					textField.setText("");
					textArea.setText("");
					textField_1.setText("");
					textField_2.setText("");
					
					pst=(PreparedStatement)con.prepareStatement("select * from todolist");
		            ResultSet rs1 = pst.executeQuery();
		            Vector head = new Vector();
		            head.addElement("Task_ID");
		            head.addElement("Title");
		            head.addElement("State");
		            head.addElement("Date");
		            head.addElement("Priority");	            
		            Vector rows=new Vector();		            
		            while (rs1.next()) {
		                Vector innerVector = new Vector();
		                //System.out.println("debug 4");
		                innerVector.addElement(rs1.getObject("Task_ID"));
		                //System.out.println("debug 5");
		                innerVector.addElement(rs1.getObject("Title"));
		                innerVector.addElement(rs1.getObject("State"));
		                innerVector.addElement(rs1.getObject("Date"));
		                innerVector.addElement(rs1.getObject("Priority"));
		                rows.addElement(innerVector);
		            }          
		            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(rows,head);
		            table.setModel(model);
				}
				catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(238, 593, 99, 21);
		contentPane.add(btnNewButton);
		JButton btnNewButton_1 = new JButton("UPDATE");
		System.out.println("Update Button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				DefaultTableModel df = (DefaultTableModel)table.getModel();
//				int s = table.getSelectedRow();
//				int id= Integer.parseInt(df.getValueAt(s,0).toString());
				try {
					
					String Title=textField.getText();
					String Priority=textArea.getText();
					String State=textField_1.getText();
					String Date=textField_2.getText();
					int Task_ID=Integer.parseInt(textField_3.getText());
					
					pst=con.prepareStatement("update todolist set \"Title\"=?,\"State\"=?,\"Date\"=?,\"Priority\"=? where \"Task_ID\"=?");
					//System.out.println("debug 1");
					pst.setString(1, Title);
					pst.setString(2, State);
					pst.setString(3, Date);
					pst.setString(4, Priority);
					pst.setInt(5, Task_ID); 
					
					//System.out.println("debug 2");
					int x=pst.executeUpdate();
					//System.out.println("debug 3");
					JOptionPane.showMessageDialog(null, "list updated");
					
					textField_3.setText("");
					textField.setText("");
					textArea.setText("");
					textField_1.setText("");
					textField_2.setText("");
					
					pst=(PreparedStatement)con.prepareStatement("select * from todolist");
		            ResultSet rs1 = pst.executeQuery();
		            Vector head = new Vector();
		            head.addElement("Task_ID");
		            head.addElement("Title");
		            head.addElement("State");
		            head.addElement("Date");
		            head.addElement("Priority");	            
		            Vector rows=new Vector();		            
		            while (rs1.next()) {
		                Vector innerVector = new Vector();
		                //System.out.println("debug 4");
		                innerVector.addElement(rs1.getObject("Task_ID"));
		                //System.out.println("debug 5");
		                innerVector.addElement(rs1.getObject("Title"));
		                innerVector.addElement(rs1.getObject("State"));
		                innerVector.addElement(rs1.getObject("Date"));
		                innerVector.addElement(rs1.getObject("Priority"));
		                rows.addElement(innerVector);
		            }          
		            javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(rows,head);
		            table.setModel(model);
				}
				catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(471, 593, 99, 21);
		contentPane.add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setBounds(741, 593, 99, 21);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(80, 226, 130, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Priority");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(80, 328, 99, 21);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(182, 223, 258, 32);
		contentPane.add(textField);
		textField.setColumns(10);		
		
		JLabel lblNewLabel_3 = new JLabel("Existing Tasks");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(1062, 105, 116, 32);
		contentPane.add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(906, 176, 370, 281);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task_ID", "Title", "Date", "State", "Priority"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JButton btnNewButton_3 = new JButton("DONE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3.setBounds(1023, 593, 85, 21);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("Date");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(544, 230, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(614, 223, 130, 32);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("State");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(544, 124, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(614, 114, 147, 32);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Task_ID");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(80, 117, 65, 20);
		contentPane.add(lblNewLabel_6);
		
		textField_3 = new JTextField();
		textField_3.setBounds(182, 114, 258, 32);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
	}
}
