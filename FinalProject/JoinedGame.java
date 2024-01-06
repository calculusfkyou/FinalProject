package FinalProject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextPane;

public class JoinedGame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JList<String> allianceList;
	private DefaultListModel<String> listModel;

	private DefaultListModel<String> gameListModel;
	private JList<String> gameList;
	private String[][] gameInfo = { 
		    { "10", "淘汰賽", "賴皮豬", "2024-01-13" }, 
		    { "23", "冠軍賽", "糾結輪", "2024-03-10" },
		    { "99", "系列賽", "喔是喔55555", "2025-12-31" },
		    { "30", "冠軍賽", "薯條怪獸", "2024-01-29" },
		    { "34", "系列賽", "睡覺怪", "2024-05-13" },
		    { "50", "冠軍賽", "我就爛", "2025-01-24" },
		    { "66", "淘汰賽", "西進中出", "2024-09-20" },
		    { "33", "冠軍賽", "芭蕉農", "2024-07-08" },
		    { "78", "淘汰賽", "林老師尾", "2024-08-09" },
		    { "69", "冠軍賽", "法老", "2025-04-14" },
		    { "69", "系列賽", "虛偽的老人", "2024-11-11" }};

	private JLabel marqueeLabel1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinedGame frame = new JoinedGame();
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
	public JoinedGame() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\logo.png"));
		setResizable(false); // 不能調整視窗大小
		setTitle("遊戲");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(190, 100, 1535, 865);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 置中

		JPanel IDPanel = new JPanel();
		IDPanel.setBounds(1235, 25, 250, 69);
		IDPanel.setLayout(null);
		IDPanel.setBorder(new LineBorder(Color.BLACK, 2));
		contentPane.add(IDPanel);

		JPanel chatPanel = new JPanel();
		chatPanel.setBounds(1236, 760, 300, 75);
		chatPanel.setLayout(null);
		contentPane.add(chatPanel);

		JLabel chooseGame_lbl = new JLabel("選擇你的遊戲");
		chooseGame_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		chooseGame_lbl.setFont(new Font("標楷體", Font.BOLD, 50));
		chooseGame_lbl.setBounds(30, 23, 400, 60);
		contentPane.add(chooseGame_lbl);

		ImageIcon conversation = new ImageIcon(
				"C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\conversation.png");
		JLabel conversation_lbl = new JLabel(conversation);
		conversation_lbl.setBounds(0, -2, 70, 70);
		conversation_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 鼠標進入時顯示的文字
				conversation_lbl.setToolTipText("聊天");
			}

			public void mouseClicked(MouseEvent e) {
				openWebPage("https://chat.openai.com/");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 鼠標離開時清空文字
				conversation_lbl.setToolTipText(null);
			}
		});
		conversation_lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定游標為手形
		chatPanel.add(conversation_lbl);

		ImageIcon quest = new ImageIcon("C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\quest.png");
		JLabel quest_lbl = new JLabel(quest);
		quest_lbl.setBounds(71, -2, 70, 70);
		quest_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 鼠標進入時顯示的文字
				quest_lbl.setToolTipText("任務");
			}

			public void mouseClicked(MouseEvent e) {
				openWebPage("https://www.yourator.co/");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 鼠標離開時清空文字
				conversation_lbl.setToolTipText(null);
			}
		});
		quest_lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定游標為手形
		chatPanel.add(quest_lbl);

		ImageIcon bug = new ImageIcon("C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\anti-bug.png");
		JLabel bug_lbl = new JLabel(bug);
		bug_lbl.setBounds(142, -2, 70, 70);
		bug_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 鼠標進入時顯示的文字
				bug_lbl.setToolTipText("偵錯");
			}

			public void mouseClicked(MouseEvent e) {
				openWebPage("https://forms.gle/xyNR1Z8kiSL6FZHH6");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 鼠標離開時清空文字
				bug_lbl.setToolTipText(null);
			}
		});
		bug_lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定游標為手形
		chatPanel.add(bug_lbl);

		ImageIcon customer = new ImageIcon(
				"C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\customer-service.png");
		JLabel customer_lbl = new JLabel(customer);
		customer_lbl.setBounds(213, -2, 70, 70);
		customer_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// 鼠標進入時顯示的文字
				customer_lbl.setToolTipText("聯絡我們");
			}

			public void mouseClicked(MouseEvent e) {
				openWebPage("https://mail.google.com/mail/u/0/?view=cm&fs=1&tf=1&to=charlie930320@gmail.com");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 鼠標離開時清空文字
				customer_lbl.setToolTipText(null);
			}
		});
		customer_lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定游標為手形
		chatPanel.add(customer_lbl);

		// 添加分隔線
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.HORIZONTAL);
		separator.setBounds(0, 99, 1535, 1); // 調整分隔線的座標和大小
		// 設定分隔線的顏色
		Color separatorColor = Color.BLACK; // 更改為你想要的顏色
		separator.setForeground(separatorColor);
		contentPane.add(separator);

		// 添加分隔線
		JSeparator separator3 = new JSeparator();
		separator3.setOrientation(SwingConstants.VERTICAL);
		separator3.setBounds(1235, 100, 1, 765); // 調整分隔線的座標和大小
		// 設定分隔線的顏色
		Color separator3Color = Color.BLACK; // 更改為你想要的顏色
		separator3.setForeground(separator3Color);
		contentPane.add(separator3);

		// 建立 JList 並加入聯盟名稱
		listModel = new DefaultListModel<>();
		for (String memberName : AllianceOwner.getMemberNames()) {
			listModel.addElement(memberName);
		}
		allianceList = new JList<>(listModel);
		allianceList.setFont(new Font("標楷體", Font.PLAIN, 40));
		allianceList.setCellRenderer(new MyListCellRenderer()); // 使用自定義的 ListCellRenderer 以顯示行間分隔線
		JScrollPane scrollPane = new JScrollPane(allianceList);
		scrollPane.setBounds(1236, 100, 300, 660);
		contentPane.add(scrollPane);
		if (checkIn(User.getLatestAccount())) {
			// 若已有聯盟，顯示聯盟成員列表
			allianceList.setVisible(true);
		} else {
			// 若沒有聯盟，隱藏聯盟成員列表
			allianceList.setVisible(false);
		}

		JButton logout_Btn = new JButton("Logout");
		logout_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPlayerLoginInterface();
			}
		});
		logout_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		logout_Btn.setBounds(1100, 40, 100, 40);
		contentPane.add(logout_Btn);

		// ID部分
		JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
		Color separatorColor2 = Color.BLACK;
		separator2.setForeground(separatorColor2);
		separator2.setBounds(68, 0, 1, 69);
		IDPanel.add(separator2);

		ImageIcon avatar = new ImageIcon("C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\avatar.jpg");
		JLabel avatar_lbl = new JLabel(avatar);
		avatar_lbl.setBounds(3, 2, 65, 65);
		IDPanel.add(avatar_lbl);

		JTextPane playerTextPane = new JTextPane();
		playerTextPane.setEditable(false);
		playerTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		playerTextPane.setOpaque(false);
		playerTextPane.setBounds(75, 2, 94, 30);
		String latestAccount = User.getLatestAccount(); // 取得最新的帳號
		playerTextPane.setText("ID：" + latestAccount);
		IDPanel.add(playerTextPane);

		JPanel GamePanel = new JPanel();
		GamePanel.setLayout(null);
		GamePanel.setBounds(624, 120, 500, 680);
		GamePanel.setBorder(new LineBorder(Color.BLACK, 2));
		contentPane.add(GamePanel);

		JLabel memberManage_lbl = new JLabel("遊戲資訊");
		memberManage_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		memberManage_lbl.setFont(new Font("標楷體", Font.BOLD, 40));
		memberManage_lbl.setBounds(0, 2, 170, 55);
		GamePanel.add(memberManage_lbl);

		JSeparator mainSeparator = new JSeparator();
		mainSeparator.setOrientation(SwingConstants.HORIZONTAL);
		mainSeparator.setForeground(Color.BLACK);
		mainSeparator.setBounds(0, 55, 500, 1);
		GamePanel.add(mainSeparator);

		JTextPane gameNameTextPane = new JTextPane();
		gameNameTextPane.setText("遊戲名稱：");
		gameNameTextPane.setOpaque(false);
		gameNameTextPane.setFont(new Font("標楷體", Font.BOLD, 40));
		gameNameTextPane.setEditable(false);
		gameNameTextPane.setBounds(10, 60, 400, 50);
		GamePanel.add(gameNameTextPane);

		JTextPane onlineAmountTextPane = new JTextPane();
		onlineAmountTextPane.setText("在線人數：");
		onlineAmountTextPane.setOpaque(false);
		onlineAmountTextPane.setFont(new Font("標楷體", Font.BOLD, 25));
		onlineAmountTextPane.setEditable(false);
		onlineAmountTextPane.setBounds(10, 120, 170, 40);
		GamePanel.add(onlineAmountTextPane);

		JTextPane gameModeTextPane = new JTextPane();
		gameModeTextPane.setText("遊戲類型：");
		gameModeTextPane.setOpaque(false);
		gameModeTextPane.setFont(new Font("標楷體", Font.BOLD, 25));
		gameModeTextPane.setEditable(false);
		gameModeTextPane.setBounds(10, 165, 150, 40);
		GamePanel.add(gameModeTextPane);

		JTextPane managerTextPane = new JTextPane();
		managerTextPane.setText("管理員：");
		managerTextPane.setOpaque(false);
		managerTextPane.setFont(new Font("標楷體", Font.BOLD, 25));
		managerTextPane.setEditable(false);
		managerTextPane.setBounds(10, 210, 130, 40);
		GamePanel.add(managerTextPane);

		JTextPane endDayTextPane = new JTextPane();
		endDayTextPane.setText("結束日期：");
		endDayTextPane.setOpaque(false);
		endDayTextPane.setFont(new Font("標楷體", Font.BOLD, 25));
		endDayTextPane.setEditable(false);
		endDayTextPane.setBounds(10, 255, 150, 40);
		GamePanel.add(endDayTextPane);

		JTextPane amountTextPane = new JTextPane();
		amountTextPane.setText((String) null);
		amountTextPane.setOpaque(false);
		amountTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		amountTextPane.setEditable(false);
		amountTextPane.setBounds(160, 122, 130, 30);
		GamePanel.add(amountTextPane);

		JTextPane modeTextPane = new JTextPane();
		modeTextPane.setText((String) null);
		modeTextPane.setOpaque(false);
		modeTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		modeTextPane.setEditable(false);
		modeTextPane.setBounds(160, 167, 130, 30);
		GamePanel.add(modeTextPane);

		JTextPane lastDayTextPane = new JTextPane();
		lastDayTextPane.setText((String) null);
		lastDayTextPane.setOpaque(false);
		lastDayTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		lastDayTextPane.setEditable(false);
		lastDayTextPane.setBounds(160, 257, 130, 30);
		GamePanel.add(lastDayTextPane);

		JTextPane manageRTextPane = new JTextPane();
		manageRTextPane.setText((String) null);
		manageRTextPane.setOpaque(false);
		manageRTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		manageRTextPane.setEditable(false);
		manageRTextPane.setBounds(140, 212, 130, 30);
		GamePanel.add(manageRTextPane);

		// 遊戲列表
		String[] gameNames = new String[50];
		for (int i = 0; i < gameNames.length; i++) {
			gameNames[i] = "遊戲" + Integer.toString(i + 1);
		}
		gameListModel = new DefaultListModel<>();
		for (String gameName : gameNames) {
			gameListModel.addElement(gameName);
		}
		gameList = new JList<>(gameListModel);
		gameList.setFont(new Font("標楷體", Font.PLAIN, 40));
		gameList.setCellRenderer(new MyListCellRenderer()); // 使用自定義的 ListCellRenderer 以顯示行間分隔線
		JScrollPane scrollPane2 = new JScrollPane(gameList);
		scrollPane2.setBounds(30, 120, 500, 500);
		scrollPane2.setBorder(new LineBorder(Color.BLACK, 2));
		contentPane.add(scrollPane2);
		gameList.setVisible(true);
		gameList.addListSelectionListener(e -> {
			// Randomly select a set of information from memberInfo array
			int randomIndex = (int) (Math.random() * gameInfo.length);
			String[] selectedMemberInfo = gameInfo[randomIndex];
			amountTextPane.setText(selectedMemberInfo[0]);
			modeTextPane.setText(selectedMemberInfo[1]);
			manageRTextPane.setText(selectedMemberInfo[2]);
			lastDayTextPane.setText(selectedMemberInfo[3]);

			String selectedGame = gameList.getSelectedValue();
			gameNameTextPane.setText("遊戲名稱：" + selectedGame);
		});

		JTextPane gameTextPane = new JTextPane();
		gameTextPane.setEditable(false);
		gameTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		gameTextPane.setBounds(75, 35, 300, 40);
		gameTextPane.setOpaque(false);

		User.nowGame(latestAccount);
		String latestGame = User.getLatestGame(); // 取得最新的遊戲
		gameTextPane.setText("遊戲：" + latestGame);
		IDPanel.add(gameTextPane);

		JButton enterGame_btn = new JButton("進入遊戲");
		enterGame_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedGame = gameList.getSelectedValue();

				if (selectedGame != null) {
					int result = 0;
					User.addGame(latestAccount, gameList.getSelectedValue());

					// 如果使用者按下確認按鈕
					if (result == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(contentPane, "此功能尚未實現，請耐心等候！", "提醒", JOptionPane.WARNING_MESSAGE);
						User.nowGame(latestAccount);
						String latestGame = User.getLatestGame(); // 取得最新的遊戲
						gameTextPane.setText("遊戲：" + latestGame);
						IDPanel.add(gameTextPane);
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "請選擇一個遊戲", "提醒", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		enterGame_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		enterGame_btn.setBounds(200, 310, 100, 40);
		GamePanel.add(enterGame_btn);

		JButton quitGame_btn = new JButton("退出遊戲");
		quitGame_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkInGame(User.getLatestAccount())) {
					JOptionPane.showMessageDialog(contentPane, "您尚未加入任何遊戲！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					int result = JOptionPane.showConfirmDialog(contentPane, "您確定要退出此遊戲嗎?", "警告",
							JOptionPane.YES_NO_OPTION);

					if (result == JOptionPane.YES_OPTION) {
						// 退出遊戲
						checkExit(User.getLatestAccount());

						User.nowGame(latestAccount); // 更新目前遊戲
						String latestGame = User.getLatestGame(); // 取得最新的遊戲
						gameTextPane.setText("遊戲：" + latestGame);
						IDPanel.add(gameTextPane);
					}
				}
			}
		});
		quitGame_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		quitGame_btn.setBounds(200, 370, 100, 40);
		GamePanel.add(quitGame_btn);

		JButton back_Btn_1 = new JButton("返回");
		back_Btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPlayerInterface();
			}
		});
		back_Btn_1.setFont(new Font("標楷體", Font.PLAIN, 15));
		back_Btn_1.setBounds(1000, 40, 100, 40);
		contentPane.add(back_Btn_1);

		JPanel ADPanel = new JPanel();
		ADPanel.setBounds(30, 640, 500, 160);
		ADPanel.setBorder(new LineBorder(Color.BLACK, 2));
		ADPanel.setLayout(null);
		contentPane.add(ADPanel);

		// 創建跑馬燈的 JLabel
		marqueeLabel1 = new JLabel("廣\r\n告");
		marqueeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		marqueeLabel1.setFont(new Font("標楷體", Font.PLAIN, 80));
		marqueeLabel1.setForeground(Color.BLACK); // 設定字體顏色
		marqueeLabel1.setBounds(0, 0, 220, 100); // 設定區域位置和大小
		ADPanel.add(marqueeLabel1);

		// 設定 Timer 用於更新跑馬燈
		Timer marqueeTimer = new Timer(100, new ActionListener() {
			int currentX = 0;
			int currentY = 0;
			int deltaX = 5; // 可以根據需要調整 X 座標的移動速度
			int deltaY = 5; // 可以根據需要調整 Y 座標的移動速度

			@Override
			public void actionPerformed(ActionEvent e) {
				// 移動跑馬燈的 X 和 Y 座標
				currentX += deltaX;
				currentY += deltaY;

				// 取得跑馬燈的寬和高
				int labelWidth = marqueeLabel1.getWidth();
				int labelHeight = marqueeLabel1.getHeight();

				// 取得面板的寬和高
				int panelWidth = ADPanel.getWidth();
				int panelHeight = ADPanel.getHeight();

				// 調整跑馬燈的座標
				marqueeLabel1.setBounds(currentX, currentY, labelWidth, labelHeight);

				// 如果跑馬燈碰到左邊或右邊，反射角
				if (currentX <= 0 || currentX + labelWidth >= panelWidth) {
					deltaX = -deltaX; // 反射 X 方向
				}

				// 如果跑馬燈碰到頂部或底部，反射角
				if (currentY <= 0 || currentY + labelHeight >= panelHeight) {
					deltaY = -deltaY; // 反射 Y 方向
				}
			}
		});
		marqueeTimer.start(); // 啟動 Timer

		ImageIcon BkIcon = new ImageIcon(
				"C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\buildAllianceBk.jpg"); // 背景圖片
		Image originalImage = BkIcon.getImage();
		float transparency = 0.5f; // 設定透明度，0.0 是完全透明，1.0 是完全不透明
		Image transparentImage = makeTransparent(originalImage, transparency); // 創建具有指定透明度的新圖像
		ImageIcon transparentIcon = new ImageIcon(transparentImage);
		JLabel Bk_lbl = new JLabel(transparentIcon);
		Bk_lbl.setBounds(0, 0, 1822, 865);
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

	// 退出目前帳號的遊戲
	private void checkExit(String account) {
		for (int i = 0; i < User.userGame.size(); i++) {
			if (User.userGame.get(i)[0].equals(account)) {
				User.userGame.remove(i);
			}
		}
	}

	private void openWebPage(String url) {
		try {
			java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		} catch (java.io.IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// 設定圖片透明度的方法
	private static Image makeTransparent(Image image, float transparency) {
		// 創建一個 BufferedImage，並設定透明度
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		return bufferedImage;
	}

	// 檢查目前帳號是否已經有聯盟
	private boolean checkIn(String account) {
		for (String[] i : User.userAlliance) {
			if (i[0].equals(account)) {
				return true; // 已有聯盟
			}
		}
		return false; // 沒有聯盟
	}

	private boolean checkInGame(String account) { // 檢查目前帳號是否已經有遊戲
		for (String[] i : User.userGame) {
			if (i[0].equals(account)) {
				return true; // 已有遊戲
			}
		}
		return false; // 沒有遊戲
	}

	private void showPlayerInterface() {
		PlayerInterface player = new PlayerInterface();
		player.setVisible(true);
		setVisible(false);
	}

	private void showPlayerLoginInterface() {
		PlayerLoginInterface login = new PlayerLoginInterface();
		login.setVisible(true);
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
