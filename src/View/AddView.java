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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class AddView extends JFrame {

	JPanel contentPane;
	public JTextArea Loi_bai_hat;
	public JTextField Tac_gia, Ten, Ma;
	public JComboBox Hinh_thuc, The_loai;
	public JButton AddN;

	/**
	 * Create the frame.
	 */
	public AddView() {
//		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameAdd.class.getResource("/Icon/Main.jpg")));
		setResizable(false);
		setTitle("Add");
		setBounds(100, 100, 1000, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(false);
		
		Tac_gia = new JTextField();
		Tac_gia.setCaretColor(new Color(255, 0, 0));
		Tac_gia.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Tac gia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		Tac_gia.setSelectionColor(new Color(30, 144, 255));
		Tac_gia.setSelectedTextColor(new Color(255, 255, 255));
		Tac_gia.setBounds(40, 71, 200, 40);
		contentPane.add(Tac_gia);
		Tac_gia.setColumns(10);
		
		Ten = new JTextField();
		Ten.setCaretColor(new Color(255, 0, 0));
		Ten.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Ten", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		Ten.setSelectionColor(new Color(30, 144, 255));
		Ten.setSelectedTextColor(new Color(255, 255, 255));
		Ten.setBounds(261, 20, 200, 40);
		contentPane.add(Ten);
		Ten.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "L\u1EDDi b\u00E0i h\u00E1t", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		scrollPane.setBounds(471, 20, 503, 280);
		contentPane.add(scrollPane);
		
		Loi_bai_hat = new JTextArea();
		Loi_bai_hat.setCaretColor(new Color(255, 0, 0));
		Loi_bai_hat.setSelectionColor(new Color(30, 144, 255));
		scrollPane.setViewportView(Loi_bai_hat);
		Loi_bai_hat.setWrapStyleWord(true);
		Loi_bai_hat.setLineWrap(true);
		Loi_bai_hat.setBorder(null);
		
		Ma = new JTextField();
		Ma.setCaretColor(new Color(255, 0, 0));
		Ma.setSelectedTextColor(new Color(255, 255, 255));
		Ma.setSelectionColor(new Color(30, 144, 255));
		Ma.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Ma", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		Ma.setBackground(new Color(255, 255, 255));
		Ma.setBounds(40, 20, 200, 40);
		contentPane.add(Ma);
		Ma.setColumns(10);
		
		Hinh_thuc = new JComboBox();
		Hinh_thuc.setBackground(new Color(255, 255, 255));
		Hinh_thuc.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Hinh thuc", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		Hinh_thuc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Hinh_thuc.setModel(new DefaultComboBoxModel(new String[] {"Đơn ca", "Song ca"}));
		Hinh_thuc.setBounds(261, 122, 200, 45);
		contentPane.add(Hinh_thuc);
		
		The_loai = new JComboBox();
		The_loai.setBackground(new Color(255, 255, 255));
		The_loai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		The_loai.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "The loai", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
//		The_loai.setModel(new DefaultComboBoxModel(new String[] {"bolero", "Nhac thieu nhi", "Nhac Trinh",
//				"Remix", "Nhac phim", "EDM", "Nhac tre", "Tru Tinh", "Nhac Que Huong", "Nhac Cach Mang",
//				"Rap", "Rock", "V-Pop", "Khac"}));
		The_loai.setBounds(40, 122, 200, 45);
		contentPane.add(The_loai);
		
		AddN = new JButton("Add");
		AddN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		AddN.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		AddN.setBackground(new Color(255, 255, 255));
		AddN.setBounds(210, 230, 90, 25);
		contentPane.add(AddN);
				
		Refresh();
	}
	
	public void Refresh() {
		Ma.setText("");
		Ten.setText("");
		Tac_gia.setText("");
		Loi_bai_hat.setText("");
	}
}
