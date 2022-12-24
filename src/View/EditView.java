package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class EditView extends JFrame {

//	public JPanel contentPane;
	public JTextField EditMa, EditTen, EditTac_Gia;
	public JTextArea EditLoi_mo_dau;
	public JComboBox EditHinh_Thuc, EditThe_loai;
	public JButton SaveButton;
	public JSeparator separator, separator_1, separator_2, separator_3;
	public boolean modnumber = true;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public EditView() {
		
		getContentPane().setBackground(new Color(255, 255, 255));
		//setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameEdit.class.getResource("/Icon/Main.jpg")));
		setResizable(false);
		setTitle("Edit");
		setBounds(100, 100, 1000, 350);
		getContentPane().setLayout(null);
		setVisible(false);
		
		EditMa = new JTextField();
		EditMa.setSelectionColor(new Color(255, 0, 0));
		EditMa.setSelectedTextColor(new Color(255, 255, 255));
		EditMa.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Ma", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		EditMa.setBackground(new Color(255, 255, 255));
		EditMa.setEditable(false);
		EditMa.setColumns(10);
		EditMa.setBounds(129, 9, 200, 40);
		getContentPane().add(EditMa);
		
		EditTen = new JTextField();
		EditTen.setCaretColor(new Color(255, 0, 0));
		EditTen.setSelectionColor(new Color(255, 0, 0));
		EditTen.setSelectedTextColor(new Color(255, 255, 255));
		EditTen.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255)), "Ten", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		EditTen.setColumns(10);
		EditTen.setBounds(12, 60, 200, 40);
		getContentPane().add(EditTen);
		
		EditTac_Gia = new JTextField();
		EditTac_Gia.setCaretColor(new Color(255, 0, 0));
		EditTac_Gia.setSelectedTextColor(new Color(255, 255, 255));
		EditTac_Gia.setSelectionColor(new Color(255, 0, 0));
		EditTac_Gia.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Tac gia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		EditTac_Gia.setColumns(10);
		EditTac_Gia.setBounds(235, 60, 200, 40);
		getContentPane().add(EditTac_Gia);
		
		EditHinh_Thuc = new JComboBox();
		EditHinh_Thuc.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Hinh thuc", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		EditHinh_Thuc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		EditHinh_Thuc.setModel(new DefaultComboBoxModel(new String[] {"Đơn ca", "Song ca"}));
		EditHinh_Thuc.setBounds(235, 122, 200, 45);
		getContentPane().add(EditHinh_Thuc);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "L\u1EDDi b\u00E0i h\u00E1t", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		scrollPane.setBounds(471, 20, 503, 280);
		getContentPane().add(scrollPane);
		
		EditLoi_mo_dau = new JTextArea();
		EditLoi_mo_dau.setCaretColor(new Color(255, 0, 0));
		EditLoi_mo_dau.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Loi mo dau", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		EditLoi_mo_dau.setSelectedTextColor(new Color(255, 255, 255));
		EditLoi_mo_dau.setSelectionColor(new Color(30, 144, 255));
		scrollPane.setViewportView(EditLoi_mo_dau);
		EditLoi_mo_dau.setText("");
		EditLoi_mo_dau.setWrapStyleWord(true);
		EditLoi_mo_dau.setLineWrap(true);
		EditLoi_mo_dau.setBorder(null);
		
		EditThe_loai = new JComboBox();
		EditThe_loai.setBackground(new Color(255, 255, 255));
		EditThe_loai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		EditThe_loai.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "The loai", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
//		EditThe_loai.setModel(new DefaultComboBoxModel(new String[] {"bolero", "Nhac thieu nhi", "Nhac Trinh",
//				"Remix", "Nhac phim", "EDM", "Nhac tre", "Tru Tinh", "Nhac Que Huong", "Nhac Cach Mang", "Rap",
//				"Rock", "V-Pop", "Khac"}));
		EditThe_loai.setBounds(12, 122, 200, 45);
		getContentPane().add(EditThe_loai);
		
		SaveButton = new JButton("Save");
		SaveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SaveButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		SaveButton.setBackground(new Color(255, 255, 255));
		SaveButton.setBounds(175, 193, 90, 25);
		getContentPane().add(SaveButton);
		
		separator = new JSeparator();
		separator.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator.setBounds(0, 0, 982, 2);
		getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator_1.setBounds(0, 309, 982, 2);
		getContentPane().add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator_2.setBounds(0, 0, 2, 311);
		getContentPane().add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		separator_3.setBounds(980, 0, 2, 311);
		getContentPane().add(separator_3);
		
	}
	
	public void changeModEditDS() {
		setBounds(100, 100, 275, 150);
		EditTen.setBounds(40, 10, 150, 40);
		SaveButton.setBounds(70, 60, 90, 25);
		
		separator.setBounds(0, 0, 259, 2);
		separator_1.setBounds(0, 109, 259, 2);
		separator_2.setBounds(0, 0, 2, 131);
		separator_3.setBounds(257, 0, 2, 131);
		
		EditHinh_Thuc.setVisible(false);
		EditLoi_mo_dau.setVisible(false);
		EditMa.setVisible(false);
		EditThe_loai.setVisible(false);
		EditTac_Gia.setVisible(false);
		
		modnumber = false;
	}
	
	public void changeMod() {
		
		setBounds(100, 100, 1000, 350);
		EditTen.setBounds(40, 60, 200, 40);
		SaveButton.setBounds(225, 205, 90, 25);
		
		separator.setBounds(0, 0, 534, 2);
		separator_1.setBounds(0, 309, 534, 2);
		separator_2.setBounds(0, 0, 2, 311);
		separator_3.setBounds(532, 0, 2, 311);
		
		EditHinh_Thuc.setVisible(true);
		EditLoi_mo_dau.setVisible(true);
		EditMa.setVisible(true);
		EditThe_loai.setVisible(true);
		EditTac_Gia.setVisible(true);
		
		modnumber = true;
	}

}
