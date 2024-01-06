package FinalProject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
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
import javax.swing.border.LineBorder;

// 自定義 ListCellRenderer 類別
class MyListCellRenderer extends DefaultListCellRenderer {
	private static final long serialVersionUID = 1L;
	private Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY); // 分隔線的設定

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		// 在每一行底部加上分隔線
		renderer.setBorder(BorderFactory.createCompoundBorder(renderer.getBorder(), border));

		return renderer;
	}
}

public class PlayerInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> allianceList;
	private DefaultListModel<String> listModel;

	private JPanel BullPanel;
	private List<Announcement> announcements; // 存儲布告欄消息和相應的圖片
	private JLabel announcementLabel;
	private JLabel imageLabel;
	private Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerInterface frame = new PlayerInterface();
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
	public PlayerInterface() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PlayerInterface.class.getResource("/Images/logo.png")));
		setResizable(false); // 不能調整視窗大小
		setTitle("玩家");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(190, 100, 1535, 865);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 置中

		JPanel IDPanel = new JPanel();
		IDPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		IDPanel.setBounds(1235, 25, 250, 69);
		IDPanel.setLayout(null);
		contentPane.add(IDPanel);

		BullPanel = new JPanel();
		BullPanel.setBounds(0, 101, 1234, 730);
		BullPanel.setLayout(null);
		contentPane.add(BullPanel);

		JPanel chatPanel = new JPanel();
		chatPanel.setBounds(1235, 760, 300, 75);
		chatPanel.setLayout(null);
		contentPane.add(chatPanel);

		JLabel conversation_lbl = new JLabel(new ImageIcon(PlayerInterface.class.getResource("/Images/conversation.png")));
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

		JLabel quest_lbl = new JLabel(new ImageIcon(PlayerInterface.class.getResource("/Images/quest.png")));
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

		JLabel bug_lbl = new JLabel(new ImageIcon(PlayerInterface.class.getResource("/Images/anti-bug.png")));
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

		JLabel customer_lbl = new JLabel(new ImageIcon(PlayerInterface.class.getResource("/Images/customer-service.png")));
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

		// 建立 JList 並加入聯盟名稱
		listModel = new DefaultListModel<>();
		for (String memberName : AllianceOwner.getMemberNames()) {
			listModel.addElement(memberName);
		}
		allianceList = new JList<>(listModel);
		allianceList.setFont(new Font("標楷體", Font.PLAIN, 40));
		allianceList.setCellRenderer(new MyListCellRenderer()); // 使用自定義的 ListCellRenderer 以顯示行間分隔線
		JScrollPane scrollPane = new JScrollPane(allianceList);
		scrollPane.setBounds(1235, 100, 300, 660);
		contentPane.add(scrollPane);
		if (checkIn(User.getLatestAccount())) {
			// 若已有聯盟，顯示聯盟成員列表
			allianceList.setVisible(true);
		} else {
			// 若沒有聯盟，隱藏聯盟成員列表
			allianceList.setVisible(false);
		}

		JButton buildAlliance_Btn = new JButton("建立聯盟");
		buildAlliance_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkIn(User.getLatestAccount())) {
					JOptionPane.showMessageDialog(contentPane, "您已經有一個聯盟了： " + User.getLatestAlliance(), "提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					showBuildAlliance();
				}
			}
		});
		buildAlliance_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		buildAlliance_Btn.setBounds(800, 40, 100, 40);
		contentPane.add(buildAlliance_Btn);

		JButton joinAlliance_Btn = new JButton("加入聯盟");
		joinAlliance_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showJoinAlliance();
			}
		});
		joinAlliance_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		joinAlliance_Btn.setBounds(1000, 40, 100, 40);
		contentPane.add(joinAlliance_Btn);

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

		JLabel avatar_lbl = new JLabel(new ImageIcon(PlayerInterface.class.getResource("/Images/avatar.jpg")));
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

		JTextPane allianceTextPane = new JTextPane();
		allianceTextPane.setEditable(false);
		allianceTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		allianceTextPane.setBounds(75, 35, 300, 40);
		allianceTextPane.setOpaque(false);
		User.nowAlliance(latestAccount); // 更新目前聯盟
		String latestAllaince = User.getLatestAlliance(); // 取得最新的聯盟
		allianceTextPane.setText("聯盟：" + latestAllaince);
		IDPanel.add(allianceTextPane);

		JButton exitAlliance_Btn_1 = new JButton("退出聯盟");
		exitAlliance_Btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkIn(User.getLatestAccount())) {
					JOptionPane.showMessageDialog(contentPane, "您尚未加入聯盟！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					int result = JOptionPane.showConfirmDialog(contentPane, "您確定要退出聯盟嗎?", "警告",
							JOptionPane.YES_NO_OPTION);

					if (result == JOptionPane.YES_OPTION) {
						// 退出聯盟
						checkExit(User.getLatestAccount());
						// refresh介面
//						showPlayerInterface();
						User.nowAlliance(latestAccount); // 更新目前聯盟
						String latestAllaince = User.getLatestAlliance(); // 取得最新的聯盟
						allianceTextPane.setText("聯盟：" + latestAllaince);
						IDPanel.add(allianceTextPane);
						if (checkIn(User.getLatestAccount())) {
							// 若已有聯盟，顯示聯盟成員列表
							allianceList.setVisible(true);
						} else {
							// 若沒有聯盟，隱藏聯盟成員列表
							allianceList.setVisible(false);
						}
					}
				}
			}
		});
		exitAlliance_Btn_1.setFont(new Font("標楷體", Font.PLAIN, 15));
		exitAlliance_Btn_1.setBounds(900, 40, 100, 40);
		contentPane.add(exitAlliance_Btn_1);

		JButton attendGame_btn = new JButton("加入遊戲");
		attendGame_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkIn(User.getLatestAccount()))
					JOptionPane.showMessageDialog(contentPane, "您尚未加入聯盟！", "提示", JOptionPane.INFORMATION_MESSAGE);
				else
					showJoinedGame();
			}
		});
		attendGame_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		attendGame_btn.setBounds(30, 40, 100, 40);
		contentPane.add(attendGame_btn);

		JButton manageGame_btn = new JButton("遊戲管理");
		manageGame_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String identityNum = checkIdentity(User.getLatestAccount());

				if (!checkIn(User.getLatestAccount())) {
					JOptionPane.showMessageDialog(contentPane, "您尚未加入聯盟！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else if (identityNum.equals("3")) {
					JOptionPane.showMessageDialog(contentPane, "您沒有管理員權限！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					showGameManagement();
				}
			}
		});
		manageGame_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		manageGame_btn.setBounds(500, 40, 100, 40);
		contentPane.add(manageGame_btn);

		JButton watchGame_btn = new JButton("觀看競賽");
		watchGame_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openExternalWebsite("https://www.twitch.tv/directory/category/league-of-legends");
			}
		});
		watchGame_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		watchGame_btn.setBounds(600, 40, 100, 40);
		contentPane.add(watchGame_btn);

		JButton gameInfo_btn = new JButton("競賽資訊");
		gameInfo_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openExternalWebsite("https://www.op.gg/");
			}
		});
		gameInfo_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		gameInfo_btn.setBounds(700, 40, 100, 40);
		contentPane.add(gameInfo_btn);

		JButton allianceManage_btn = new JButton("聯盟管理");
		allianceManage_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String identityNum = checkIdentity(User.getLatestAccount());

				if (!checkIn(User.getLatestAccount())) {
					JOptionPane.showMessageDialog(contentPane, "您尚未加入聯盟！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else if (!identityNum.equals("1")) {
					JOptionPane.showMessageDialog(contentPane, "您沒有聯盟所有者權限！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					showAllManagement();
				}
			}
		});
		allianceManage_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		allianceManage_btn.setBounds(400, 40, 100, 40);
		contentPane.add(allianceManage_btn);

		ImageIcon BkIcon = new ImageIcon(PlayerInterface.class.getResource("/Images/buildAllianceBk.jpg")); // 背景圖片
		Image originalImage = BkIcon.getImage();
		float transparency = 0.5f; // 設定透明度，0.0 是完全透明，1.0 是完全不透明
		Image transparentImage = makeTransparent(originalImage, transparency); // 創建具有指定透明度的新圖像
		ImageIcon transparentIcon = new ImageIcon(transparentImage);
		JLabel Bk_lbl = new JLabel(transparentIcon);
		Bk_lbl.setBounds(0, 0, 1822, 865);
		contentPane.add(Bk_lbl);

		initializeAnnouncements();
		initializeUI();
		setupTimer();

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

	private void initializeUI() {
		// 創建 JLabel 來顯示布告欄消息
		announcementLabel = new JLabel();
		announcementLabel.setFont(new Font("標楷體", Font.BOLD, 25));
		announcementLabel.setHorizontalAlignment(SwingConstants.CENTER);
		announcementLabel.setBounds(0, 0, 1234, 130);
		BullPanel.add(announcementLabel);

		// 創建 JLabel 來顯示圖片
		imageLabel = new JLabel();
		imageLabel.setBounds(0, 130, 1234, 600);
		BullPanel.add(imageLabel);
	}

	private void initializeAnnouncements() {
		// 初始化布告欄消息和相應的圖片
		announcements = new ArrayList<>();
		announcements.add(new Announcement("羅傑這咖都鑽石了，這裡誰還卡在翡翠？？",PlayerInterface.class.getResource("/Images/news8.jpeg")
				));
		announcements.add(new Announcement("這咖的犽凝跟熊班長萎縮的腎一樣小 真的點點點",
				PlayerInterface.class.getResource("/Images/news98.jpg")));
		announcements.add(new Announcement("世界八強AD 單腎貴族 被路人珍娜當狗在噴...",
				PlayerInterface.class.getResource("/Images/news10.jpg")));
		// 可以加入更多布告欄消息和相應的圖片

		// 隨機排序，以便輪播效果
		Collections.shuffle(announcements);
	}

	private void setupTimer() {
		int delay = 5000; // 延遲 5 秒啟動
		timer = new Timer(delay, new ActionListener() {
			int currentIndex = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentIndex >= announcements.size()) {
					currentIndex = 0; // 回到第一條消息
					// 重新隨機排序
					Collections.shuffle(announcements);
				}
				Announcement currentAnnouncement = announcements.get(currentIndex);
				announcementLabel.setText(currentAnnouncement.getMessage());

				// 更新圖片
				ImageIcon imageIcon = new ImageIcon(currentAnnouncement.getImagePath());
				// 調整預覽圖片的大小
				int width = imageLabel.getWidth();
				int height = imageLabel.getHeight();
				Image scaledImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
				imageIcon = new ImageIcon(scaledImage);
				imageLabel.setIcon(imageIcon);

				currentIndex++;
			}
		});
		timer.setInitialDelay(0); // 設定初始延遲為0，即立即顯示第一條消息
		timer.start(); // 啟動計時器
	}

	// 內部類別，表示一條布告欄消息和相應的圖片
	private static class Announcement {
		private String message;
		private URL imagePath;

		public Announcement(String message, URL url) {
			this.message = message;
			this.imagePath = url;
		}

		public String getMessage() {
			return message;
		}

		public URL getImagePath() {
			return imagePath;
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

	// 開啟外部網站
	private void openExternalWebsite(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException | URISyntaxException ex) {
			ex.printStackTrace();
		}
	}

	// 確認身分權限
	private String checkIdentity(String account) {
		for (int i = 0; i < User.userAlliance.size(); i++) {
			if (User.userAlliance.get(i)[0].equals(account)) {
				return User.userAlliance.get(i)[User.userAlliance.get(i).length - 1];
			}
		}
		return account; // 應該不會找不到，但一定要這樣寫一個回傳值
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

	// 退出目前帳號的聯盟
	private void checkExit(String account) {
		for (int i = 0; i < User.userAlliance.size(); i++) {
			if (User.userAlliance.get(i)[0].equals(account)) {
				User.userAlliance.remove(i);
			}
		}
	}

	private void showAllManagement() {
		AllianceManagement All = new AllianceManagement();
		All.setVisible(true);
		setVisible(false);
	}

	private void showGameManagement() {
		GameManagement game = new GameManagement();
		game.setVisible(true);
		setVisible(false);
	}

	private void showBuildAlliance() {
		BuildAlliance build = new BuildAlliance();
		build.setVisible(true);
		setVisible(false);
	}

	private void showJoinAlliance() {
		JoinAlliance join = new JoinAlliance();
		join.setVisible(true);
		setVisible(false);
	}

	private void showPlayerLoginInterface() {
		PlayerLoginInterface login = new PlayerLoginInterface();
		login.setVisible(true);
		setVisible(false);
	}

	private void showJoinedGame() {
		JoinedGame game = new JoinedGame();
		game.setVisible(true);
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
