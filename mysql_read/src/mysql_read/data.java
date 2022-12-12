package mysql_read;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Scrollbar;

public class data {

	private JFrame frame;
	private JTable table_1;
	private Scrollbar scrollbar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					data window = new data();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public data() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cseb","root","mysql");
					String query = "select * from students";
					Statement st = con.createStatement();
					ResultSet rs=st.executeQuery(query);
					ResultSetMetaData rsmd=rs.getMetaData();
//AbstractButton table = null;
					//					AbstractButton table = null;
					DefaultTableModel m=(DefaultTableModel)table_1.getModel();
					int cols=rsmd.getColumnCount();
					String[]colName=new String[cols];
					for(int i=0;i<cols;i++)
						colName[i]=rsmd.getColumnName(i+1);
						m.setColumnIdentifiers(colName);
						String name,marks;
						while(rs.next()) {
							name=rs.getString(1);
							marks=rs.getString(2);
							String[] row= {name,marks};
							m.addRow(row);
						}
						
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(93, 11, 261, 23);
		frame.getContentPane().add(btnNewButton);
		
		table_1 = new JTable();
		table_1.setForeground(new Color(0, 0, 0));
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_1.setBackground(new Color(255, 255, 255));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table_1.setBounds(128, 85, 185, 123);
		frame.getContentPane().add(table_1);
		
		scrollbar = new Scrollbar();
		scrollbar.setBounds(296, 85, 17, 123);
		frame.getContentPane().add(scrollbar);
		table_1.add(scrollbar);
		
//		
//		JScrollPane js=new JScrollPane();
//		js.setVisible(true);
//		table_1.add(js);
		
	}
}
