package FinalProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel identityPanel; // 新增的 JPanel
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Images/logo.png")));
		setResizable(false); // 不能調整視窗大小
		setTitle("大巨巢(LBN)競技系統");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(190, 100, 1535, 865);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 置中

		// 新增 JPanel 作為身分選擇的容器
		identityPanel = new JPanel();
//        identityPanel.setOpaque(false); // 讓背景透明
		identityPanel.setBorder(new LineBorder(Color.BLACK, 2)); // 設定黑色邊框
		identityPanel.setBounds(621, 308, 555, 236);
		contentPane.add(identityPanel);
		identityPanel.setLayout(null);

		JLabel label = new JLabel(new ImageIcon(Main.class.getResource("/Images/logo.png")));
		label.setBounds(15, 10, 158, 157);
		contentPane.add(label);

		JLabel chooseText = new JLabel("Choose your identity!");
		chooseText.setFont(new Font("Bookman Old Style", Font.BOLD, 35));
		chooseText.setHorizontalAlignment(SwingConstants.CENTER);
		chooseText.setBounds(0, 0, 555, 70);
		identityPanel.add(chooseText);

		JButton playerBtn = new JButton("我是玩家");
		playerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPlayerLogin();
			}
		});
		playerBtn.setFont(new Font("標楷體", Font.PLAIN, 15));
		playerBtn.setBounds(199, 80, 150, 40);
		identityPanel.add(playerBtn);

		JButton audienceBtn = new JButton("我是觀眾");
		audienceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAudience();
			}
		});
		audienceBtn.setFont(new Font("標楷體", Font.PLAIN, 15));
		audienceBtn.setBounds(199, 130, 150, 40);
		identityPanel.add(audienceBtn);

		JButton advertiserBtn = new JButton("我是廣告商");
		advertiserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAdvertiser();
			}
		});
		advertiserBtn.setFont(new Font("標楷體", Font.PLAIN, 15));
		advertiserBtn.setBounds(199, 180, 150, 40);
		identityPanel.add(advertiserBtn);

		JLabel license_lbl_1 = new JLabel(
				"<html><p>&copy; Copyright since 2023 by CharlieWu. All rights reserved.</p></html>");
		license_lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		license_lbl_1.setForeground(SystemColor.controlShadow);
		license_lbl_1.setFont(new Font("標楷體", Font.BOLD, 15));
		license_lbl_1.setBounds(15, 770, 500, 35);
		contentPane.add(license_lbl_1);

		JLabel label_1 = new JLabel(new ImageIcon(Main.class.getResource("/Images/MainBK.png")));
		label_1.setBounds(0, 0, 1535, 865);
		contentPane.add(label_1);
		
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
	
	private void showPlayerLogin() {
		PlayerLoginInterface player = new PlayerLoginInterface(); // 創建玩家視窗
		player.setVisible(true); // 在按鈕點擊時，顯示已存在的玩家登入視窗
		setVisible(false); // 隱藏主視窗（可選）
	}

	private void showAudience() {
		Audience audience = new Audience();
		audience.setVisible(true);
		setVisible(false);
	}

	private void showAdvertiser() {
		AdvertisingAgency advertiser = new AdvertisingAgency();
		advertiser.setVisible(true);
		setVisible(false);
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
