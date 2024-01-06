package FinalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class PlayerLoginInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField AccounttextField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerLoginInterface frame = new PlayerLoginInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PlayerLoginInterface() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PlayerLoginInterface.class.getResource("/Images/logo.png")));
		setResizable(false); // 不能調整視窗大小
		setTitle("玩家登入");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(190, 100, 1535, 865);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 置中
		
		JLabel logo_lbl = new JLabel(new ImageIcon(PlayerLoginInterface.class.getResource("/Images/logo.png")));
		logo_lbl.setBounds(121, 13, 158, 157);
		contentPane.add(logo_lbl);

		JLabel fb_lbl = new JLabel(new ImageIcon(PlayerLoginInterface.class.getResource("/Images/FB.png")));
		fb_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openWebPage("https://www.facebook.com/");
			}
		});
		fb_lbl.setBounds(60, 391, 66, 31);
		contentPane.add(fb_lbl);

		JLabel google_lbl = new JLabel(new ImageIcon(PlayerLoginInterface.class.getResource("/Images/google.png")));
		google_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openWebPage("https://accounts.google.com/");
			}
		});
		google_lbl.setBounds(130, 392, 66, 31);
		contentPane.add(google_lbl);

		JLabel apple_lbl = new JLabel(new ImageIcon(PlayerLoginInterface.class.getResource("/Images/apple.png")));
		apple_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openWebPage("https://appleid.apple.com/sign-in");
			}
		});
		apple_lbl.setBounds(202, 391, 66, 31);
		contentPane.add(apple_lbl);

		JLabel XBOX_lbl = new JLabel(new ImageIcon(PlayerLoginInterface.class.getResource("/Images/XBOX.png")));
		XBOX_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				openWebPage("https://login.live.com/");
			}
		});
		XBOX_lbl.setBounds(274, 390, 66, 31);
		contentPane.add(XBOX_lbl);

		JButton reSelect_btn = new JButton("重新選擇身分");
		reSelect_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMain();
			}
		});
		reSelect_btn.setFont(new Font("標楷體", Font.BOLD, 12));
		reSelect_btn.setBounds(199, 486, 141, 40);
		contentPane.add(reSelect_btn);

		JLabel Login_lbl = new JLabel("Login");
		Login_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Login_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
		Login_lbl.setBounds(141, 180, 118, 49);
		contentPane.add(Login_lbl);

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
		AccounttextField.setBounds(60, 252, 280, 40);
		contentPane.add(AccounttextField);
		AccounttextField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(60, 332, 280, 40);
		contentPane.add(passwordField);

		JButton Login_btn = new JButton("Login");
		Login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account = AccounttextField.getText();
				String password = String.valueOf(passwordField.getPassword());

				if (!isUserRegistered(account)) {
					// 帳號不存在
					JOptionPane.showMessageDialog(null, "此用戶不存在！", "錯誤", JOptionPane.ERROR_MESSAGE);
				} else {

					// 帳號存在，檢查密碼
					if (checkPassword(account, password)) {
						// 密碼正確，跳轉到玩家的介面
						User nowUser = new User();
						nowUser.nowInfo(account);
						showTransitionScreen(); // 過場畫面
						dispose(); // 關閉登入介面
					} else {
						// 密碼錯誤
						JOptionPane.showMessageDialog(null, "密碼錯誤！", "錯誤", JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
		Login_btn.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		Login_btn.setBounds(150, 611, 100, 40);
		contentPane.add(Login_btn);

		JLabel FirstTime_lbl = new JLabel("First time?");
		FirstTime_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		FirstTime_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		FirstTime_lbl.setBounds(43, 432, 150, 40);
		contentPane.add(FirstTime_lbl);

		PlayerRegisterInterface register = new PlayerRegisterInterface();
		JButton Register_btn = new JButton("Register");
		Register_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register.setVisible(true);
				dispose();
			}
		});
		Register_btn.setFont(new Font("Bookman Old Style", Font.BOLD, 12));
		Register_btn.setBounds(240, 434, 100, 40);
		contentPane.add(Register_btn);

		JLabel FirstTime_lbl_1 = new JLabel("or");
		FirstTime_lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		FirstTime_lbl_1.setFont(new Font("Bookman Old Style", Font.BOLD, 20));
		FirstTime_lbl_1.setBounds(33, 485, 91, 40);
		contentPane.add(FirstTime_lbl_1);

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

		JLabel Bk_lbl = new JLabel(new ImageIcon(PlayerLoginInterface.class.getResource("/Images/LoginBK.jpg")));
		Bk_lbl.setBounds(400, 0, 1720, 865);
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
	
	private void showTransitionScreen() {
		ImageIcon transitionImage = new ImageIcon(
				"C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\cutscene.png");
		JLabel transitionLabel = new JLabel(transitionImage);
		transitionLabel.setOpaque(false);

		// 建立一個 JFrame 來顯示過場畫面
		JFrame transitionFrame = new JFrame("過場畫面");
		transitionFrame.setUndecorated(true); // 設置為無邊框
		transitionFrame.setSize(transitionImage.getIconWidth(), transitionImage.getIconHeight());
		transitionFrame.setLocationRelativeTo(null);
		transitionFrame.setBackground(new Color(0, 0, 0, 0)); // 背景設定透明
		transitionFrame.getContentPane().add(transitionLabel);
		

		transitionFrame.setVisible(true);

		Timer timer = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transitionFrame.dispose(); // 關閉過場畫面
				showPlayerInterface();
			}
		});

		// 啟動計時器
		timer.setRepeats(false); // 設定為單次執行
		timer.start();
	}
	
	// 開啟外部網站
	private void openWebPage(String url) {
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showPlayerInterface() {
		PlayerInterface player = new PlayerInterface();
		player.setVisible(true);
		setVisible(false);
	}
	
	private void showMain() {
		Main main = new Main();
		main.setVisible(true);
		setVisible(false);
	}
	
	private boolean isUserRegistered(String account) {
		// Check if the account exists in userInformation array
		User tempUser = new User();
		for (String[] userInfo : tempUser.userInformation) {
			if (userInfo[0].equals(account)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkPassword(String account, String passWord) {
		User tempUser = new User();
		for (String[] user : tempUser.userInformation) {
			if (user[0].equals(account) && user[1].equals(passWord)) {
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
