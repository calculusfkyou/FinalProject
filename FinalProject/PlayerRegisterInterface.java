package FinalProject;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.junit.jupiter.api.Test;

import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class PlayerRegisterInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField AccounttextField;
	private JPasswordField passwordField;
	private JPasswordField checkPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerRegisterInterface frame = new PlayerRegisterInterface();
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
	public PlayerRegisterInterface() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PlayerRegisterInterface.class.getResource("/Images/logo.png")));
		setResizable(false); // 禁止調整視窗大小
		setTitle("玩家註冊");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(190, 100, 1535, 865);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 置中

		JLabel label = new JLabel(new ImageIcon(PlayerRegisterInterface.class.getResource("/Images/logo.png")));
		label.setBounds(121, 13, 158, 157);
		contentPane.add(label);

		JLabel Register_lbl = new JLabel("Register");
		Register_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Register_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
		Register_lbl.setBounds(135, 180, 130, 49);
		contentPane.add(Register_lbl);

		JLabel Username_lbl = new JLabel("Username");
		Username_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Username_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		Username_lbl.setBounds(33, 213, 150, 40);
		contentPane.add(Username_lbl);

		JLabel Password_lbl = new JLabel("Password");
		Password_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Password_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		Password_lbl.setBounds(33, 291, 150, 40);
		contentPane.add(Password_lbl);

		AccounttextField = new JTextField();
		AccounttextField.setColumns(10);
		AccounttextField.setBounds(60, 252, 280, 40);
		contentPane.add(AccounttextField);

		passwordField = new JPasswordField();
		passwordField.setBounds(60, 332, 280, 40);
		contentPane.add(passwordField);

		JButton Register_btn = new JButton("Register");
		Register_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // 註冊
				// 取得用戶輸入的資訊
				String account = AccounttextField.getText();
				String passWord = String.valueOf(passwordField.getPassword());
				String checkPassWord = String.valueOf(checkPasswordField.getPassword());

				// 檢查是否有輸入帳號和密碼
				if (account.isEmpty()) {
					JOptionPane.showMessageDialog(null, "請輸入帳號！");
					return;
				} else if (passWord.isEmpty()) {
					JOptionPane.showMessageDialog(null, "請輸入密碼！");
					return;
				} else if (checkPassWord.isEmpty()) {
					JOptionPane.showMessageDialog(null, "請輸入確認密碼！");
					return;
				}

				// 檢查密碼是否一致
				if (!passWord.equals(checkPassWord)) {
					JOptionPane.showMessageDialog(null, "確認密碼錯誤，請重新輸入！");
					return;
				}

				// 檢查用戶名是否已存在
				if (isUsernameExists(account)) {
					JOptionPane.showMessageDialog(null, "此帳號已經被使用，請選擇其他帳號！");
					return;
				}

				saveRegisteredUsers(account, passWord); // 儲存用戶資訊到檔案

				JOptionPane.showMessageDialog(null, "註冊成功！");
				AccounttextField.setText("");
				passwordField.setText("");
				checkPasswordField.setText("");
			}
		});
		Register_btn.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		Register_btn.setBounds(150, 480, 100, 40);
		contentPane.add(Register_btn);

		JLabel checkPassword_lbl = new JLabel("Check Password");
		checkPassword_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		checkPassword_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		checkPassword_lbl.setBounds(45, 373, 194, 40);
		contentPane.add(checkPassword_lbl);

		checkPasswordField = new JPasswordField();
		checkPasswordField.setBounds(60, 414, 280, 40);
		contentPane.add(checkPasswordField);

		JButton backToLogin_btn = new JButton("Back to Login!");
		backToLogin_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 跳轉至登入介面
				showPlayerLogin();
				dispose(); // 關閉註冊介面
			}
		});
		backToLogin_btn.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		backToLogin_btn.setBounds(135, 546, 130, 40);
		contentPane.add(backToLogin_btn);

		JLabel cantLogin_lbl = new JLabel("不能註冊?");
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
		
		JLabel license_lbl_1 = new JLabel("<html><p>&copy; Copyright since 2023 by CharlieWu. All rights reserved.</p></html>");
		license_lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		license_lbl_1.setForeground(SystemColor.controlShadow);
		license_lbl_1.setFont(new Font("標楷體", Font.BOLD, 10));
		license_lbl_1.setBounds(50, 790, 300,35);
		contentPane.add(license_lbl_1);
		
		JLabel Bk_lbl = new JLabel(new ImageIcon(PlayerRegisterInterface.class.getResource("/Images/RegBk.jpg")));
		Bk_lbl.setBounds(400, 0, 1240, 1080);
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

	private void showPlayerLogin() {
		PlayerLoginInterface player = new PlayerLoginInterface(); // 創建玩家視窗
		player.setVisible(true); // 在按鈕點擊時，顯示已存在的玩家登入視窗
		setVisible(false); // 隱藏主視窗（可選）
	}
	
	@Test
	// 儲存已註冊的用戶列表到陣列(假裝)
	private void saveRegisteredUsers(String account, String passWord) {
		User tempUser = new User();
		tempUser.addInfo(account, passWord);
		System.out.println("player register！");
	}

	// 檢查用戶名是否已存在
	private boolean isUsernameExists(String username) {
		User tempUser = new User();
		for (String[] user : tempUser.userInformation) {
			if (user[0].equals(username)) {
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
