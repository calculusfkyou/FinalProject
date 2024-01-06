package FinalProject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;

public class GameManagement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> allianceList;
	private DefaultListModel<String> listModel;

	private JComboBox<String> sponsorshipComboBox;
	private JDateChooser startDateChooser;
	private JPanel gameCasePanel;
	private String selectedSponsorship; // 用來儲存選擇的方案
	private String startDate; // 用來儲存選擇的日期
	private String endDate;

	private JPanel announceGamePanel;
	private long days;
	private JLabel case_lbl;

	private JTextPane endDayTextPane;
	private JTextPane startDayTextPane;
	private JTextPane caseTextPane;
	private JTextPane remainDayTextPane;
	private JTextPane scoreFormulaTextPane;
	private JTextPane gameNameTextPane;

	private static boolean check = false;
	private JTextField scoreFormulaTextField; // 新增的元件
	private JTextField gameNameTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameManagement frame = new GameManagement();
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
	public GameManagement() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\logo.png"));
		setResizable(false); // 不能調整視窗大小
		setTitle("遊戲管理員");
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

		JPanel chatPanel = new JPanel();
		chatPanel.setBounds(1235, 760, 300, 75);
		chatPanel.setLayout(null);
		contentPane.add(chatPanel);

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

		JTextPane allianceTextPane = new JTextPane();
		allianceTextPane.setEditable(false);
		allianceTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		allianceTextPane.setBounds(75, 35, 300, 40);
		allianceTextPane.setOpaque(false);
		User.nowAlliance(latestAccount); // 更新目前聯盟
		String latestAllaince = User.getLatestAlliance(); // 取得最新的聯盟
		allianceTextPane.setText("聯盟：" + latestAllaince);
		IDPanel.add(allianceTextPane);

		JButton back_Btn = new JButton("返回");
		back_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPlayerInterface();
			}
		});
		back_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		back_Btn.setBounds(1000, 40, 100, 40);
		contentPane.add(back_Btn);

		JButton watchGame_btn = new JButton("觀看競賽");
		watchGame_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openExternalWebsite("https://www.twitch.tv/directory/category/league-of-legends");
			}
		});
		watchGame_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		watchGame_btn.setBounds(800, 40, 100, 40);
		contentPane.add(watchGame_btn);

		JButton gameInfo_btn = new JButton("競賽資訊");
		gameInfo_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openExternalWebsite("https://www.op.gg/");
			}
		});
		gameInfo_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		gameInfo_btn.setBounds(900, 40, 100, 40);
		contentPane.add(gameInfo_btn);

		JLabel gameManage_lbl = new JLabel("遊戲管理");
		gameManage_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		gameManage_lbl.setFont(new Font("標楷體", Font.BOLD, 50));
		gameManage_lbl.setBounds(30, 23, 400, 60);
		contentPane.add(gameManage_lbl);

		// Choose Case Panel
		gameCasePanel = new JPanel();
		gameCasePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		gameCasePanel.setLayout(null);
		gameCasePanel.setBounds(85, 125, 475, 675);
		contentPane.add(gameCasePanel);

		JLabel chooseCase_lbl = new JLabel("1.選擇遊戲類型");
		chooseCase_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		chooseCase_lbl.setFont(new Font("標楷體", Font.BOLD, 40));
		chooseCase_lbl.setBounds(0, 0, 310, 55);
		gameCasePanel.add(chooseCase_lbl);

		String[] sponsorshipOptions = { "請選擇...", "淘汰賽", "冠軍賽", "系列賽" };
		sponsorshipComboBox = new JComboBox<>(sponsorshipOptions);
		sponsorshipComboBox.setFont(new Font("標楷體", Font.PLAIN, 15));
		sponsorshipComboBox.setBounds(160, 75, 130, 40);
		gameCasePanel.add(sponsorshipComboBox);

		startDateChooser = new JDateChooser();
		startDateChooser.setBounds(160, 140, 130, 20);
		gameCasePanel.add(startDateChooser);

		JDateChooser endDateChooser = new JDateChooser();
		endDateChooser.setBounds(160, 190, 130, 20);
		gameCasePanel.add(endDateChooser);

		JButton check_btn = new JButton("確認");
		check_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 獲取使用者輸入的評分方式
				String scoreFormula = scoreFormulaTextField.getText();
				String gameName = gameNameTextField.getText();

				if (scoreFormula.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "請輸入評分方式", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (gameName.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "請輸入遊戲名稱", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 檢查方案是否選擇
				if (sponsorshipComboBox.getSelectedItem().equals("請選擇...")) {
					JOptionPane.showMessageDialog(contentPane, "請選擇方案", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 檢查起始日期是否選擇
				if (startDateChooser.getDate() == null) {
					JOptionPane.showMessageDialog(contentPane, "請選擇起始日期", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 檢查結束日期是否選擇
				if (endDateChooser.getDate() == null) {
					JOptionPane.showMessageDialog(contentPane, "請選擇結束日期", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 確認結束日期在起始日期之後
				if (endDateChooser.getDate().before(startDateChooser.getDate())) {
					JOptionPane.showMessageDialog(contentPane, "結束日期必須在起始日期之後", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					JOptionPane.showMessageDialog(contentPane, "選擇成功，請確認遊戲設定！", "提示", JOptionPane.INFORMATION_MESSAGE);
					check = true;
				}

				// 在確認按鈕的事件處理中賦值
				selectedSponsorship = (String) sponsorshipComboBox.getSelectedItem();
				startDate = formatDate(startDateChooser.getDate());
				endDate = formatDate(endDateChooser.getDate());

				// 計算起始日期到結束日期的天數
				long diff = endDateChooser.getDate().getTime() - startDateChooser.getDate().getTime();
				days = diff / (24 * 60 * 60 * 1000);

				Game.setCaseChoose(selectedSponsorship);
				Game.setStartDay(startDate);
				Game.setEndDay(endDate);
				Game.setGameRemainDays(days);
				Game.setScoreFormula(scoreFormula);
				Game.setGameName(gameName);

				caseTextPane.setText(Game.getCaseChoose());
				startDayTextPane.setText(Game.getStartDay());
				endDayTextPane.setText(Game.getEndDay());
				remainDayTextPane.setText(Long.toString(Game.getGameRemainDays()));
				scoreFormulaTextPane.setText(Game.getScoreFormula());
				gameNameTextPane.setText(Game.getGameName());
			}
		});
		check_btn.setFont(new Font("標楷體", Font.PLAIN, 20));
		check_btn.setBounds(185, 350, 80, 40);
		gameCasePanel.add(check_btn);

		JLabel startDay_lbl = new JLabel("起始日期");
		startDay_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		startDay_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		startDay_lbl.setBounds(0, 125, 150, 50);
		gameCasePanel.add(startDay_lbl);

		JLabel endDay_lbl = new JLabel("結束日期");
		endDay_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		endDay_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		endDay_lbl.setBounds(0, 175, 150, 50);
		gameCasePanel.add(endDay_lbl);

		JLabel scoreFormulaLabel = new JLabel("評分公式");
		scoreFormulaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreFormulaLabel.setFont(new Font("標楷體", Font.BOLD, 25));
		scoreFormulaLabel.setBounds(0, 225, 150, 50);
		gameCasePanel.add(scoreFormulaLabel);

		scoreFormulaTextField = new JTextField();
		scoreFormulaTextField.setFont(new Font("標楷體", Font.BOLD, 20));
		scoreFormulaTextField.setBounds(160, 230, 200, 40);
		gameCasePanel.add(scoreFormulaTextField);

		JLabel case_lbl_1 = new JLabel("方案");
		case_lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		case_lbl_1.setFont(new Font("標楷體", Font.BOLD, 25));
		case_lbl_1.setBounds(0, 75, 150, 50);
		gameCasePanel.add(case_lbl_1);

		JLabel gameNamel_1 = new JLabel("遊戲名稱");
		gameNamel_1.setHorizontalAlignment(SwingConstants.CENTER);
		gameNamel_1.setFont(new Font("標楷體", Font.BOLD, 25));
		gameNamel_1.setBounds(0, 275, 150, 50);
		gameCasePanel.add(gameNamel_1);

		gameNameTextField = new JTextField();
		gameNameTextField.setFont(new Font("標楷體", Font.BOLD, 20));
		gameNameTextField.setBounds(160, 280, 200, 40);
		gameCasePanel.add(gameNameTextField);

		announceGamePanel = new JPanel();
		announceGamePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		announceGamePanel.setLayout(null);
		announceGamePanel.setBounds(660, 125, 475, 675);
		contentPane.add(announceGamePanel);

		JLabel finalCheck_lbl = new JLabel("2.遊戲設置確認");
		finalCheck_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		finalCheck_lbl.setFont(new Font("標楷體", Font.BOLD, 40));
		finalCheck_lbl.setBounds(0, 0, 300, 55);
		announceGamePanel.add(finalCheck_lbl);

		JLabel startDay_lbl_2 = new JLabel("起始日期：");
		startDay_lbl_2.setHorizontalAlignment(SwingConstants.CENTER);
		startDay_lbl_2.setFont(new Font("標楷體", Font.BOLD, 25));
		startDay_lbl_2.setBounds(0, 120, 150, 50);
		announceGamePanel.add(startDay_lbl_2);

		JLabel endDay_lbl_1 = new JLabel("結束日期：");
		endDay_lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		endDay_lbl_1.setFont(new Font("標楷體", Font.BOLD, 25));
		endDay_lbl_1.setBounds(0, 170, 150, 50);
		announceGamePanel.add(endDay_lbl_1);

		case_lbl = new JLabel("方案：");
		case_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		case_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		case_lbl.setBounds(0, 70, 150, 50);
		announceGamePanel.add(case_lbl);

		caseTextPane = new JTextPane();
		caseTextPane.setText((String) null);
		caseTextPane.setOpaque(false);
		caseTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		caseTextPane.setEditable(false);
		caseTextPane.setBounds(160, 75, 130, 30);
		caseTextPane.setText(Game.getCaseChoose());
		announceGamePanel.add(caseTextPane);

		startDayTextPane = new JTextPane();
		startDayTextPane.setText((String) null);
		startDayTextPane.setOpaque(false);
		startDayTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		startDayTextPane.setEditable(false);
		startDayTextPane.setBounds(160, 125, 130, 30);
		startDayTextPane.setText(Game.getStartDay());
		announceGamePanel.add(startDayTextPane);

		endDayTextPane = new JTextPane();
		endDayTextPane.setText((String) null);
		endDayTextPane.setOpaque(false);
		endDayTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		endDayTextPane.setEditable(false);
		endDayTextPane.setBounds(160, 175, 130, 30);
		endDayTextPane.setText(Game.getEndDay());
		announceGamePanel.add(endDayTextPane);

		JLabel remainDay_lbl = new JLabel("持續天數：");
		remainDay_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		remainDay_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		remainDay_lbl.setBounds(0, 220, 150, 50);
		announceGamePanel.add(remainDay_lbl);

		remainDayTextPane = new JTextPane();
		remainDayTextPane.setText((String) null);
		remainDayTextPane.setOpaque(false);
		remainDayTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		remainDayTextPane.setEditable(false);
		remainDayTextPane.setBounds(160, 225, 130, 30);
		announceGamePanel.add(remainDayTextPane);

		JLabel checkFormulaLabel = new JLabel("評分公式：");
		checkFormulaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		checkFormulaLabel.setFont(new Font("標楷體", Font.BOLD, 25));
		checkFormulaLabel.setBounds(0, 270, 150, 50);
		announceGamePanel.add(checkFormulaLabel);

		scoreFormulaTextPane = new JTextPane();
		scoreFormulaTextPane.setText((String) null);
		scoreFormulaTextPane.setOpaque(false);
		scoreFormulaTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		scoreFormulaTextPane.setEditable(false);
		scoreFormulaTextPane.setBounds(160, 275, 300, 30);
		announceGamePanel.add(scoreFormulaTextPane);

		JLabel gameNamel_1_1 = new JLabel("遊戲名稱：");
		gameNamel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		gameNamel_1_1.setFont(new Font("標楷體", Font.BOLD, 25));
		gameNamel_1_1.setBounds(0, 320, 150, 50);
		announceGamePanel.add(gameNamel_1_1);

		gameNameTextPane = new JTextPane();
		gameNameTextPane.setText((String) null);
		gameNameTextPane.setOpaque(false);
		gameNameTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		gameNameTextPane.setEditable(false);
		gameNameTextPane.setBounds(160, 328, 300, 30);
		announceGamePanel.add(gameNameTextPane);

		JButton announceGame_Btn = new JButton("宣布遊戲");
		announceGame_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check) {
					JOptionPane.showMessageDialog(contentPane, "恭喜！遊戲已成功發佈", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(contentPane, "請先完成先前步驟！", "警告", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		announceGame_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		announceGame_Btn.setBounds(187, 400, 100, 40);
		announceGamePanel.add(announceGame_Btn);

		JButton cancelAD_Btn = new JButton("取消設置");
		cancelAD_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.setGameRemainDays(0);
				Game.setCaseChoose("");
				Game.setStartDay("");
				Game.setEndDay("");
				Game.setScoreFormula("");
				Game.setGameName("");
				JOptionPane.showMessageDialog(contentPane, "您已取消遊戲設置", "提示", JOptionPane.INFORMATION_MESSAGE);
				showGameManagement();
			}
		});
		cancelAD_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		cancelAD_Btn.setBounds(700, 40, 100, 40);
		contentPane.add(cancelAD_Btn);

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

	// 格式化日期
	private String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
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

	// 檢查目前帳號是否已經有聯盟
	private boolean checkIn(String account) {
		for (String[] i : User.userAlliance) {
			if (i[0].equals(account)) {
				return true; // 已有聯盟
			}
		}
		return false; // 沒有聯盟
	}

	private void showGameManagement() {
		GameManagement game = new GameManagement();
		game.setVisible(true);
		setVisible(false);
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
