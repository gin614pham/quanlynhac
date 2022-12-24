package View;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ListView extends JFrame {

	public JPanel contentPane;
	public JScrollPane scrollPane;
	public JTable tableDS;
	public JButton NewDSButton, OkButton, DeleteDSButton, EditDSButton;
	public DefaultTableModel modelDS;
	public boolean ok = true;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ListView() {
		setTitle("Danh sach");
//		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameDanhSach.class.getResource("/Icon/Main.jpg")));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		scrollPane.setBounds(10, 40, 219, 210);
		contentPane.add(scrollPane);
		
		tableDS = new JTable();
		tableDS.setSelectionForeground(new Color(255, 255, 255));
		tableDS.setSelectionBackground(new Color(30, 144, 255));
		tableDS.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		tableDS.setBackground(new Color(255, 255, 255));
		tableDS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDS.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setViewportView(tableDS);
		
		OkButton = new JButton("OK");
		OkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		OkButton.setBackground(new Color(255, 255, 255));
		OkButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		OkButton.setBounds(250, 80, 142, 23);
		contentPane.add(OkButton);
		
		NewDSButton = new JButton("Tao danh sach moi");
		NewDSButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		NewDSButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		NewDSButton.setBackground(new Color(255, 255, 255));
		NewDSButton.setBounds(250, 125, 142, 23);
		contentPane.add(NewDSButton);
		
		DeleteDSButton = new JButton("Xoa danh sach");
		DeleteDSButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DeleteDSButton.setBackground(new Color(255, 255, 255));
		DeleteDSButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		DeleteDSButton.setBounds(250, 170, 142, 23);
		contentPane.add(DeleteDSButton);
		
		EditDSButton = new JButton("Doi ten");
		EditDSButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		EditDSButton.setBackground(new Color(255, 255, 255));
		EditDSButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		EditDSButton.setBounds(250, 211, 142, 23);
		contentPane.add(EditDSButton);
		
	}

}
