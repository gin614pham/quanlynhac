package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Login extends JFrame {

	public JPanel contentPane;
	public JTextField UserField;
	public JPasswordField PassWordField;
	public JLabel lblNewLabel;
	public JPasswordField ConfirmPassField;
	public JButton logInButton, SignUpButton, ShowHideButton;
	public boolean modShowHide = true, modSignUp = true, modLogin = true;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(JFrameAccount.class.getResource("/Icon/Main.jpg")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(false);
		setTitle("account");
		setBounds(100, 100, 633, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Log-in");
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 617, 49);
		contentPane.add(lblNewLabel);
		
		UserField = new JTextField();
		UserField.setCaretColor(new Color(255, 0, 0));
		UserField.setSelectionColor(new Color(255, 0, 0));
		UserField.setSelectedTextColor(new Color(255, 255, 255));
		UserField.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255)), "user", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 191, 255)));
		UserField.setForeground(new Color(30, 144, 255));
		UserField.setBounds(300, 71, 150, 40);
		contentPane.add(UserField);
		UserField.setColumns(10);
		
		PassWordField = new JPasswordField();
		PassWordField.setCaretColor(new Color(255, 0, 0));
		PassWordField.setSelectionColor(new Color(255, 0, 0));
		PassWordField.setSelectedTextColor(new Color(255, 255, 255));
		PassWordField.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255)), "password", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 191, 255)));
		PassWordField.setForeground(new Color(30, 144, 255));
		PassWordField.setBounds(300, 112, 150, 40);
		contentPane.add(PassWordField);
		
		ConfirmPassField = new JPasswordField();
		ConfirmPassField.setCaretColor(new Color(255, 0, 0));
		ConfirmPassField.setSelectionColor(new Color(255, 0, 0));
		ConfirmPassField.setSelectedTextColor(new Color(255, 255, 255));
		ConfirmPassField.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255)), "confirm password", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 191, 255)));
		ConfirmPassField.setForeground(new Color(30, 144, 255));
		ConfirmPassField.setBounds(300, 153, 150, 40);
		contentPane.add(ConfirmPassField);
		
		logInButton = new JButton("login");
		logInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logInButton.setBackground(new Color(255, 255, 255));
		logInButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		logInButton.setBounds(255, 204, 89, 23);
		contentPane.add(logInButton);
		
		SignUpButton = new JButton("Sign up");
		SignUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		SignUpButton.setBackground(new Color(255, 255, 255));
		SignUpButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		SignUpButton.setBounds(410, 204, 89, 23);
		contentPane.add(SignUpButton);
		
		ShowHideButton = new JButton("Show");
		ShowHideButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ShowHideButton.setBorder(new LineBorder(new Color(30, 144, 255), 1, true));
		ShowHideButton.setBackground(new Color(255, 255, 255));	
		ShowHideButton.setBounds(460, 118, 89, 23);
		contentPane.add(ShowHideButton);
		
		lblNewLabel_4 = new JLabel("");
	//	lblNewLabel_4.setIcon(new ImageIcon(JFrameAccount.class.getResource("/Icon/D\u1EF1 \u00E1n m\u1EDBi (2).png")));
		lblNewLabel_4.setBounds(0, 0, 617, 250);
		contentPane.add(lblNewLabel_4);
		
		ChangeMod();	
	}
	
	public void ChangeMod() {
		if (modSignUp) {
			modShowHide = true;
			ShowHideButton.setVisible(true);
			lblNewLabel.setText("Log in");
			ConfirmPassField.setVisible(false);
			modSignUp = false;
			SignUpButton.setText("Sign up");
			logInButton.setText("Log in");
			modLogin = true;
		} else {
			ShowHideButton.setVisible(false);
			lblNewLabel.setText("Sign up");
			ConfirmPassField.setVisible(true);
			modSignUp = true;
			SignUpButton.setText("Log in");
			logInButton.setText("Sign up");
			modLogin = false;
		}
		
		PassWordField.setText(null);
		ConfirmPassField.setText(null);
		UserField.setText(null);
		PassWordField.setEchoChar('•');
	}
	
	// 
	public String getPass() {
		String pass = "";
		for (char x: PassWordField.getPassword()) {
			pass += x;
		}
		return pass;
	}
	
	//
	public String getConfirmPass() {
		String pass = "";
		for (char x: ConfirmPassField.getPassword()) {
			pass += x;
		}
		return pass;
	}
	
	//
	public String getUser() {
		return UserField.getText();
	}

}
