package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Model.AtClient;
import Model.AtServer;
import Model.Nhac;
import Model.User;
import Services.AtClientImpl;
import Services.AtServerImpl;

public class MainView {

	public JFrame frame;
	public JTable table;
	public JButton AddButton, EditButton, DropButton, FindButton, RefreshButton, addDSYTButton, ShowDSButton;
	public JButton LikeDSButton, LogOutButton;
	public JComboBox FindHinhThuc, FindTheLoai;
	public JTextField FindMa, FindTen, FindLoiMoDau, FindTacGia;
	public JTextPane DetailsNPane;
	public JLabel lblNewLabel;
	public JScrollPane scrollPane;
	public JSeparator separator, separator_1, separator_2, separator_3, separator_4, separator_5, separator_6, separator_7;
	
	public DefaultTableModel model;
//	public ArrayList<Nhac> ListNhac = new ArrayList<>();
	public User user = new User();
	public boolean buttonDropMod = true;
	private JScrollPane scrollPane_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public MainView() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setResizable(false);
//		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(NhacView.class.getResource("/Icon/Main.jpg")));
		frame.setTitle("Danh sach nhac");
		frame.setBounds(100, 100, 925, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//====
		lblNewLabel = new JLabel("Danh sach nhac");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Adobe Caslon Pro Bold", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(10, 10, 889, 50);
		frame.getContentPane().add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		scrollPane.setBounds(10, 65, 664, 200);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setSelectionForeground(new Color(255, 255, 255));
		table.setSelectionBackground(new Color(30, 144, 255));
		table.setGridColor(new Color(30, 144, 255));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);		
		table.setAutoCreateRowSorter(true);
		
		FindMa = new JTextField();
		FindMa.setCaretColor(new Color(255, 0, 0));
		FindMa.setSelectionColor(new Color(255, 0, 0));
		FindMa.setSelectedTextColor(new Color(255, 255, 255));
		FindMa.setBackground(new Color(255, 255, 255));
		FindMa.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Ma", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		FindMa.setBounds(690, 70, 200, 40);
		frame.getContentPane().add(FindMa);
		FindMa.setColumns(10);
		
		FindTen = new JTextField();
		FindTen.setCaretColor(new Color(255, 0, 0));
		FindTen.setSelectedTextColor(new Color(255, 255, 255));
		FindTen.setSelectionColor(new Color(255, 0, 0));
		FindTen.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Ten", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		FindTen.setBackground(new Color(255, 255, 255));
		FindTen.setBounds(690, 115, 200, 40);
		frame.getContentPane().add(FindTen);
		FindTen.setColumns(10);
		
		FindLoiMoDau = new JTextField();
		FindLoiMoDau.setCaretColor(new Color(255, 0, 0));
		FindLoiMoDau.setSelectionColor(new Color(255, 0, 0));
		FindLoiMoDau.setSelectedTextColor(new Color(255, 255, 255));
		FindLoiMoDau.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Loi", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		FindLoiMoDau.setColumns(10);
		FindLoiMoDau.setBounds(690, 160, 200, 40);
		frame.getContentPane().add(FindLoiMoDau);
		
		FindTacGia = new JTextField();
		FindTacGia.setCaretColor(new Color(255, 0, 0));
		FindTacGia.setSelectionColor(new Color(255, 0, 0));
		FindTacGia.setSelectedTextColor(new Color(255, 255, 255));
		FindTacGia.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Tac gia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		FindTacGia.setColumns(10);
		FindTacGia.setBounds(690, 205, 200, 40);
		frame.getContentPane().add(FindTacGia);
		
		FindHinhThuc = new JComboBox();
		FindHinhThuc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FindHinhThuc.setBackground(new Color(255, 255, 255));
		FindHinhThuc.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Hinh thuc", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		FindHinhThuc.setModel(new DefaultComboBoxModel(new String[] {"", "Đơn ca", "Song ca"}));
		FindHinhThuc.setBounds(690, 300, 200, 45);
		frame.getContentPane().add(FindHinhThuc);
		
		FindTheLoai = new JComboBox();
		FindTheLoai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FindTheLoai.setBackground(new Color(255, 255, 255));
		FindTheLoai.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "The loai", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
//		FindTheLoai.setModel(new DefaultComboBoxModel(new String[] {"", "bolero", "Nhac thieu nhi", "Nhac Trinh",
//				"Remix", "Nhac phim", "EDM", "Nhac tre", "Tru Tinh", "Nhac Que Huong", "Nhac Cach Mang", "Rap",
//				"Rock", "V-Pop", "Khac"}));
		FindTheLoai.setBounds(690, 250, 200, 45);
		frame.getContentPane().add(FindTheLoai);
		
		AddButton = new JButton("Them nhac");
		AddButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AddButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		AddButton.setBackground(new Color(255, 255, 255));
		AddButton.setBounds(10, 320, 120, 30);
		frame.getContentPane().add(AddButton);
		
		EditButton = new JButton("Chinh sua");
		EditButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		EditButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		EditButton.setBackground(new Color(255, 255, 255));
		EditButton.setBounds(10, 360, 120, 30);
		frame.getContentPane().add(EditButton);
			
		DropButton = new JButton("Xoa");	
		DropButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DropButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		DropButton.setBackground(new Color(255, 255, 255));
		DropButton.setBounds(10, 400, 120, 30);
		frame.getContentPane().add(DropButton);
		
		FindButton = new JButton("Tim kiem");		
		FindButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		FindButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		FindButton.setBackground(new Color(255, 255, 255));
		FindButton.setBounds(730, 360, 120, 30);
		frame.getContentPane().add(FindButton);	
		
		RefreshButton = new JButton("Refresh");	
		RefreshButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		RefreshButton.setBackground(new Color(255, 255, 255));
		RefreshButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		RefreshButton.setBounds(10, 280, 120, 30);
		frame.getContentPane().add(RefreshButton);
		
		addDSYTButton = new JButton("Them vao danh sach yeu thich");
		addDSYTButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addDSYTButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		addDSYTButton.setBackground(new Color(255, 255, 255));
		addDSYTButton.setBounds(140, 280, 225, 30);
		frame.getContentPane().add(addDSYTButton);
		
		ShowDSButton = new JButton("Danh sach yeu thich");
		ShowDSButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ShowDSButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		ShowDSButton.setBackground(new Color(255, 255, 255));
		ShowDSButton.setBounds(449, 280, 225, 30);
		frame.getContentPane().add(ShowDSButton);
		
		LogOutButton = new JButton("");
		//LogOutButton.setIcon(new ImageIcon(NhacView.class.getResource("/Icon/D\u1EF1 \u00E1n m\u1EDBi (3).png")));
		LogOutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LogOutButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		LogOutButton.setBackground(new Color(255, 255, 255));
		LogOutButton.setToolTipText("Dang xuat");
		LogOutButton.setBounds(869, 420, 30, 30);
		frame.getContentPane().add(LogOutButton);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(30, 144, 255)));
		scrollPane_1.setBounds(140, 320, 534, 130);
		frame.getContentPane().add(scrollPane_1);
		
		DetailsNPane = new JTextPane();
		scrollPane_1.setViewportView(DetailsNPane);
		DetailsNPane.setSelectionColor(new Color(255, 0, 0));
		DetailsNPane.setSelectedTextColor(new Color(255, 255, 255));
		DetailsNPane.setBorder(null);
		DetailsNPane.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		DetailsNPane.setEditable(false);
		
		separator = new JSeparator();
		separator.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(135, 276, 1, 184);
		frame.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(680, 58, 1, 402);
		frame.getContentPane().add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator_2.setBounds(0, 276, 680, 1);
		frame.getContentPane().add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator_3.setBounds(0, 459, 909, 2);
		frame.getContentPane().add(separator_3);
		
		separator_4 = new JSeparator();
		separator_4.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator_4.setBounds(0, 0, 909, 2);
		frame.getContentPane().add(separator_4);
		
		separator_5 = new JSeparator();
		separator_5.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator_5.setBounds(0, 0, 2, 460);
		frame.getContentPane().add(separator_5);
		
		separator_6 = new JSeparator();
		separator_6.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator_6.setBounds(907, 0, 2, 460);
		frame.getContentPane().add(separator_6);
		
		separator_7 = new JSeparator();
		separator_7.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator_7.setBounds(0, 58, 909, 1);
		frame.getContentPane().add(separator_7);
		//====

		
	}
	
	public void LoginApp() {
		frame.setVisible(true);
		
	}
	
	public void changeMod() {
		switch (user.getMod()) {
		case 1: {
			AddButton.setVisible(false);
			EditButton.setVisible(false);
			DropButton.setVisible(false);
			break;
		}
		case 2: {
			AddButton.setVisible(true);
			EditButton.setVisible(true);
			DropButton.setVisible(true);
			break;
		}
		}
	}
	
	public void Logout() {
		frame.setVisible(false);
		user = new User();
	}

	
	
	
	
	
	
	
	
	

}
