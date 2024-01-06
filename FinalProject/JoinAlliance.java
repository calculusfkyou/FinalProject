package FinalProject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class JoinAlliance extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> allianceList;
	private DefaultListModel<String> listModel;

	private JPanel ADPanel;

	private JLabel marqueeLabel1; // 跑馬燈的 JLabel

	private String[][] allInfo = { { "50", "競技", "賴皮豬王", "每個人5269" }, 
			{ "10", "休閒", "聖潔十", "不准帶乾哥進來" },
			{ "99", "娛樂", "人頭馬", "我們聯盟都是蚵粉" }, 
			{ "25", "競技", "猴友誼", "人與人之間有猴友誼，歡迎加入我們！" }, 
			{ "1", "休閒", "馬angel", "you are my angel.我是瑪嬰九，快點加入我的聯盟，超級缺人" },
			{ "42", "娛樂", "雄問安", "有沒有人要救救我萎縮的腎，啊哈" }, 
			{ "36", "競技", "滷夫", "愛因斯坦有說過，人只有獻身於社會，才能找出那短暫而有風險的生命的意義。我希望諸位也能好好地體會這句話。吉姆·羅恩告訴我們，要麽你主宰生活，要麽你被生活主宰。這啟發了我，我要成為海賊王的意義其實就隱藏在我們的生活中，為什麼是這樣呢？" }, 
			{ "15", "休閒", "閒雲野鶴", "山水之間" },
			{ "78", "娛樂", "你啊罵", "既然如此，我們不得不面對一個非常尷尬的事實，那就是，現在，解決加入我的聯盟的問題，是非常非常重要的。 所以，所謂加入我的聯盟，關鍵是加入我的聯盟需要如何寫。" }, 
			{ "63", "競技", "大蛇玩", "這裡的簡介都是廢文產生器生的" }, 
			{ "12", "休閒", "飲料哥", "達·芬奇曾經告訴世人，大膽和堅定的決心能夠抵得上武器的精良。這讓我思索了許久，我每天都要喝飲料真的是很值得探究，培根曾經說過一句發人深省的話，深窺自己的心，而後發覺一切的奇跡在你自己。帶著這句話，我們還要更加慎重的審視這個問題：所以說，你真的了解我每天都要喝飲料嗎？" },
			{ "88", "娛樂", "cph", "我為啥在這裡，我是來招人進來的，至於是實驗室還是聯盟我不知道。" } };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinAlliance frame = new JoinAlliance();
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
	public JoinAlliance() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JoinAlliance.class.getResource("/Images/logo.png")));
		setResizable(false); // 不能調整視窗大小
		setTitle("加入聯盟");
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

		ADPanel = new JPanel();
		ADPanel.setBounds(1070, 120, 400, 680);
		ADPanel.setBorder(new LineBorder(Color.BLACK, 2));
		ADPanel.setLayout(null);
		contentPane.add(ADPanel);

		// 添加分隔線
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.HORIZONTAL);
		separator.setBounds(0, 100, 1535, 1); // 調整分隔線的座標和大小
		Color separatorColor = Color.BLACK; // 更改為你想要的顏色
		separator.setForeground(separatorColor);
		contentPane.add(separator);

		JPanel AllPanel = new JPanel();
		AllPanel.setLayout(null);
		AllPanel.setBorder(new LineBorder(Color.BLACK, 2));
		AllPanel.setBounds(510, 120, 500, 680);
		contentPane.add(AllPanel);

		JLabel allianceIInfo_lbl = new JLabel("聯盟資訊");
		allianceIInfo_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		allianceIInfo_lbl.setFont(new Font("標楷體", Font.BOLD, 40));
		allianceIInfo_lbl.setBounds(0, 2, 170, 55);
		AllPanel.add(allianceIInfo_lbl);

		JSeparator mainSeparator = new JSeparator();
		mainSeparator.setOrientation(SwingConstants.HORIZONTAL);
		mainSeparator.setForeground(Color.BLACK);
		mainSeparator.setBounds(0, 55, 500, 1);
		AllPanel.add(mainSeparator);

		JTextPane allianceNameTextPane = new JTextPane();
		allianceNameTextPane.setText("聯盟名稱：");
		allianceNameTextPane.setOpaque(false);
		allianceNameTextPane.setFont(new Font("標楷體", Font.BOLD, 40));
		allianceNameTextPane.setEditable(false);
		allianceNameTextPane.setBounds(10, 60, 400, 50);
		AllPanel.add(allianceNameTextPane);

		JTextPane AmountTextPane = new JTextPane();
		AmountTextPane.setText("人數：");
		AmountTextPane.setOpaque(false);
		AmountTextPane.setFont(new Font("標楷體", Font.BOLD, 25));
		AmountTextPane.setEditable(false);
		AmountTextPane.setBounds(10, 120, 140, 40);
		AllPanel.add(AmountTextPane);

		JTextPane allianceModeTextPane = new JTextPane();
		allianceModeTextPane.setText("聯盟類型：");
		allianceModeTextPane.setOpaque(false);
		allianceModeTextPane.setFont(new Font("標楷體", Font.BOLD, 25));
		allianceModeTextPane.setEditable(false);
		allianceModeTextPane.setBounds(10, 165, 150, 40);
		AllPanel.add(allianceModeTextPane);

		JTextPane ownerTextPane = new JTextPane();
		ownerTextPane.setText("所有者：");
		ownerTextPane.setOpaque(false);
		ownerTextPane.setFont(new Font("標楷體", Font.BOLD, 25));
		ownerTextPane.setEditable(false);
		ownerTextPane.setBounds(10, 210, 130, 40);
		AllPanel.add(ownerTextPane);

		JTextPane InfoTextPane = new JTextPane();
		InfoTextPane.setText("聯盟簡介：");
		InfoTextPane.setOpaque(false);
		InfoTextPane.setFont(new Font("標楷體", Font.BOLD, 25));
		InfoTextPane.setEditable(false);
		InfoTextPane.setBounds(10, 255, 150, 40);
		AllPanel.add(InfoTextPane);

		JTextPane amountTextPane = new JTextPane();
		amountTextPane.setText((String) null);
		amountTextPane.setOpaque(false);
		amountTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		amountTextPane.setEditable(false);
		amountTextPane.setBounds(130, 122, 130, 30);
		AllPanel.add(amountTextPane);

		JTextPane modeTextPane = new JTextPane();
		modeTextPane.setText((String) null);
		modeTextPane.setOpaque(false);
		modeTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		modeTextPane.setEditable(false);
		modeTextPane.setBounds(160, 167, 130, 30);
		AllPanel.add(modeTextPane);

		JTextPane owneRTextPane = new JTextPane();
		owneRTextPane.setText((String) null);
		owneRTextPane.setOpaque(false);
		owneRTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		owneRTextPane.setEditable(false);
		owneRTextPane.setBounds(140, 212, 130, 30);
		AllPanel.add(owneRTextPane);

		JTextArea allianceDescriptionTextArea = new JTextArea();
		allianceDescriptionTextArea.setWrapStyleWord(true);
		allianceDescriptionTextArea.setText("");
		allianceDescriptionTextArea.setLineWrap(true);
		allianceDescriptionTextArea.setFont(new Font("標楷體", Font.PLAIN, 20));
		allianceDescriptionTextArea.setBounds(10, 300, 480, 170);
		AllPanel.add(allianceDescriptionTextArea);

		String[] allianceNames = new String[50];
		for (int i = 0; i < allianceNames.length; i++) {
			allianceNames[i] = "聯盟" + Integer.toString(i + 1);
		}
		// 建立 JList 並加入聯盟名稱
		listModel = new DefaultListModel<>();
		for (int i = 0; i < allianceNames.length; i++) {
			listModel.addElement(allianceNames[i]);
		}
		allianceList = new JList<>(listModel);
		allianceList.setFont(new Font("標楷體", Font.PLAIN, 40));
		allianceList.setCellRenderer(new MyListCellRenderer()); // 使用自定義的 ListCellRenderer 以顯示行間分隔線
		JScrollPane scrollPane = new JScrollPane(allianceList);
		scrollPane.setBounds(50, 120, 400, 680);
		scrollPane.setBorder(new LineBorder(Color.BLACK, 2));
		contentPane.add(scrollPane);
		allianceList.addListSelectionListener(e -> {
			// Randomly select a set of information from memberInfo array
			int randomIndex = (int) (Math.random() * allInfo.length);
			String[] selectedMemberInfo = allInfo[randomIndex];
			amountTextPane.setText(selectedMemberInfo[0]);
			modeTextPane.setText(selectedMemberInfo[1]);
			owneRTextPane.setText(selectedMemberInfo[2]);
			allianceDescriptionTextArea.setText(selectedMemberInfo[3]);

			String selectedAlliance = allianceList.getSelectedValue();
			allianceNameTextPane.setText("聯盟名稱：" + selectedAlliance);
		});

		JLabel buildAlliance_lbl = new JLabel("加入聯盟");
		buildAlliance_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		buildAlliance_lbl.setFont(new Font("標楷體", Font.BOLD, 50));
		buildAlliance_lbl.setBounds(25, 25, 220, 60);
		contentPane.add(buildAlliance_lbl);

		// 創建跑馬燈的 JLabel
		marqueeLabel1 = new JLabel("給我吃廣\r\n告");
		marqueeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		marqueeLabel1.setFont(new Font("標楷體", Font.PLAIN, 50));
		marqueeLabel1.setForeground(Color.BLACK); // 設定字體顏色
		marqueeLabel1.setBounds(10, 10, 250, 50); // 設定區域位置和大小
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

		JButton back_btn = new JButton("返回");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPlayerInterface();
			}
		});
		back_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		back_btn.setBounds(1000, 40, 100, 40);
		contentPane.add(back_btn);

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

		JTextPane playerTextPane = new JTextPane();
		playerTextPane.setOpaque(false); // 設定背景為透明
		playerTextPane.setEditable(false);
		playerTextPane.setFont(new Font("標楷體", Font.BOLD, 40));
		playerTextPane.setBounds(75, 0, 200, 65);
		String latestAccount = User.getLatestAccount(); // 取得最新的帳號
		playerTextPane.setText("ID：" + latestAccount);
		IDPanel.add(playerTextPane);

		JLabel avatar_lbl = new JLabel(new ImageIcon(JoinAlliance.class.getResource("/Images/avatar.jpg")));
		avatar_lbl.setBounds(3, 2, 65, 65);
		IDPanel.add(avatar_lbl);

		JButton joinButton = new JButton("加入選定聯盟");
		joinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedAlliance = allianceList.getSelectedValue();

				if (selectedAlliance != null) {
					int result = 0;

					if (checkIn(User.getLatestAccount())) { // 已有聯盟
						result = JOptionPane.showConfirmDialog(null, "您確定要更換聯盟嗎?", "警告", JOptionPane.YES_NO_OPTION);
					}

					// 如果使用者按下確認按鈕
					if (result == JOptionPane.YES_OPTION) {
						// 執行聯盟更換的相應操作
						saveUserAlliance(User.getLatestAccount(), selectedAlliance);
						AllianceOwner.initMember();
						showPlayerInterface();
					}
				} else {
					// 使用者沒有選擇聯盟，提醒使用者選擇一個聯盟
					JOptionPane.showMessageDialog(contentPane, "請選擇一個聯盟", "提醒", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		joinButton.setFont(new Font("標楷體", Font.PLAIN, 15));
		joinButton.setBounds(175, 490, 150, 40);
		AllPanel.add(joinButton);

		ImageIcon BkIcon = new ImageIcon(
				JoinAlliance.class.getResource("/Images/buildAllianceBk.jpg")); // 背景圖片
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

	private boolean checkIn(String account) {
		// 檢查目前帳號是否已經有聯盟
		for (String[] i : User.userAlliance) {
			if (i[0].equals(account)) {
				return true; // 已有聯盟
			}
		}
		return false; // 沒有聯盟
	}

	// for一般玩家
	private void saveUserAlliance(String account, String alliance) { // 儲存用戶聯盟資料
		User.addAlliance(account, alliance);
	}

	private void showPlayerLoginInterface() {
		PlayerLoginInterface login = new PlayerLoginInterface();
		login.setVisible(true);
		setVisible(false);
	}

	private void showPlayerInterface() {
		PlayerInterface player = new PlayerInterface();
		player.setVisible(true);
		setVisible(false);
	}

	private void showExitConfirmation() {
		int choice = JOptionPane.showConfirmDialog(contentPane, "Do you want to exit the program?", "Exit Program",
				JOptionPane.YES_NO_OPTION);

		if (choice == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
