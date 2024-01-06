package FinalProject;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AdvertisingAgency extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField AccounttextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvertisingAgency frame = new AdvertisingAgency();
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
	public AdvertisingAgency() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdvertisingAgency.class.getResource("/Images/logo.png")));
		setResizable(false); // 不能調整視窗大小
		setTitle("廣告商");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(190, 100, 1535, 865);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 置中

		JLabel label = new JLabel(new ImageIcon(AdvertisingAgency.class.getResource("/Images/logo.png")));
		label.setBounds(121, 13, 158, 157);
		contentPane.add(label);

		JButton reSelect_btn = new JButton("重新選擇身分");
		reSelect_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMain();
			}
		});
		reSelect_btn.setFont(new Font("標楷體", Font.BOLD, 12));
		reSelect_btn.setBounds(130, 492, 140, 40);
		contentPane.add(reSelect_btn);

		JLabel Username_lbl = new JLabel("AdvertiserName");
		Username_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Username_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		Username_lbl.setBounds(60, 213, 170, 40);
		contentPane.add(Username_lbl);

		JLabel Password_lbl = new JLabel("Password");
		Password_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Password_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		Password_lbl.setBounds(33, 291, 150, 40);
		contentPane.add(Password_lbl);

		AccounttextField = new JTextField();
		AccounttextField.setBounds(60, 252, 280, 40);
		contentPane.add(AccounttextField);
		AccounttextField.setColumns(10);
		Advertiser.nowInfo(AccounttextField.getText()); // 更新帳號
		
		passwordField = new JPasswordField();
		passwordField.setBounds(60, 332, 280, 40);
		contentPane.add(passwordField);

		JButton Login_btn = new JButton("Login");
		Login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account = AccounttextField.getText();
				String password = String.valueOf(passwordField.getPassword());

				if (!isAdevertiserRegistered(account)) {
					// 帳號不存在
					JOptionPane.showMessageDialog(null, "此廣告商不存在！", "錯誤", JOptionPane.ERROR_MESSAGE);
				} else {

					// 帳號存在，檢查密碼
					if (checkPassword(account, password)) {
						// 密碼正確，跳轉到玩家的介面
						Advertiser nowAdevertiser = new Advertiser();
						nowAdevertiser.nowInfo(account);
						showAdvertisementSet();
						dispose(); // 關閉登入介面
					} else {
						// 密碼錯誤
						JOptionPane.showMessageDialog(null, "密碼錯誤！", "錯誤", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		Login_btn.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		Login_btn.setBounds(150, 412, 100, 40);
		contentPane.add(Login_btn);

		JLabel cantLogin_lbl = new JLabel("不能登入?");
		cantLogin_lbl.setForeground(SystemColor.controlShadow);
		cantLogin_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		cantLogin_lbl.setFont(new Font("標楷體", Font.BOLD, 15));
		cantLogin_lbl.setBounds(155, 765, 90, 31);
		contentPane.add(cantLogin_lbl);
		cantLogin_lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定游標為手形
		cantLogin_lbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				openWebPage("https://youtu.be/dQw4w9WgXcQ?si=I2vgjbaVCJddCcfz");
			}
		});

		JLabel version_lbl = new JLabel("V1.0.1");
		version_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		version_lbl.setForeground(SystemColor.controlShadow);
		version_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 15));
		version_lbl.setBounds(270, 765, 90, 31);
		contentPane.add(version_lbl);
		version_lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定游標為手形
		version_lbl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				String message = "<html><div>Foundation:999<br/>UX:999<br/>SDK:12.34.5.6789</div></html>";
				JOptionPane.showMessageDialog(null, message, "V1.0.1", JOptionPane.PLAIN_MESSAGE);
			}
		});

		JLabel license_lbl_1 = new JLabel(
				"<html><p>&copy; Copyright since 2023 by CharlieWu. All rights reserved.</p></html>");
		license_lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		license_lbl_1.setForeground(SystemColor.controlShadow);
		license_lbl_1.setFont(new Font("標楷體", Font.BOLD, 10));
		license_lbl_1.setBounds(50, 790, 300, 35);
		contentPane.add(license_lbl_1);

		JLabel Bk_lbl = new JLabel(new ImageIcon(AdvertisingAgency.class.getResource("/Images/adBK.jpg")));
		Bk_lbl.setBounds(400, 0, 1274, 865);
		contentPane.add(Bk_lbl);

		// 設定 Enter 鍵的映射
		InputMap inputMap1 = contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap1.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enterPressed");
		contentPane.getActionMap().put("enterPressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Component focusedComponent = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
				if (focusedComponent instanceof JButton) {
					JButton focusedButton = (JButton) focusedComponent;
					focusedButton.doClick();
				}
			}
		});

		InputMap inputMap = contentPane.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = contentPane.getActionMap();
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exitFullScreen");
		actionMap.put("exitFullScreen", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				showExitConfirmation();
			}
		});
	}

	// 開啟外部網站
	private void openWebPage(String url) {
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showMain() {
		Main main = new Main();
		main.setVisible(true);
		setVisible(false);
	}

	private void showAdvertisementSet() {
		AdvertisementSet advertisement = new AdvertisementSet();
		advertisement.setVisible(true);
		setVisible(false);
	}

	private boolean isAdevertiserRegistered(String account) {
		Advertiser tempAdevertiser = new Advertiser();
		for (String[] adevertiserInfo : tempAdevertiser.advertiserInformation) {
			if (adevertiserInfo[0].equals(account)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkPassword(String account, String passWord) {
		Advertiser tempAdevertiser = new Advertiser();
		for (String[] adevertiser : tempAdevertiser.advertiserInformation) {
			if (adevertiser[0].equals(account) && adevertiser[1].equals(passWord)) {
				return true;
			}
		}
		return false;
	}

	// ESC離開視窗
	private void showExitConfirmation() {
		int choice = JOptionPane.showConfirmDialog(contentPane, "Do you want to exit the program?", "Exit Program",
				JOptionPane.YES_NO_OPTION);

		if (choice == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
