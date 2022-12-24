package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class addDSView extends JFrame {

	public JPanel contentPane;
	public JTextField NameDS;
	public JButton OKtenButton;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public addDSView() {
		setTitle("Tao danh sach");
	//	setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameNewDanhSach.class.getResource("/Icon/Main.jpg")));
		setBounds(100, 100, 250, 180);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		setVisible(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NameDS = new JTextField();
		NameDS.setSelectedTextColor(new Color(255, 255, 255));
		NameDS.setSelectionColor(new Color(255, 0, 0));
		NameDS.setForeground(new Color(0, 0, 255));
		NameDS.setCaretColor(new Color(255, 0, 0));
		NameDS.setBorder(new TitledBorder(new LineBorder(new Color(30, 144, 255), 1, true), "Ten", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(30, 144, 255)));
		NameDS.setBounds(10, 21, 214, 40);
		contentPane.add(NameDS);
		NameDS.setColumns(10);
		
		OKtenButton = new JButton("OK");
		OKtenButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		OKtenButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		OKtenButton.setBackground(new Color(255, 255, 255));
		OKtenButton.setForeground(new Color(30, 144, 255));
		OKtenButton.setBounds(76, 71, 89, 23);
		contentPane.add(OKtenButton);
		
	}

}
