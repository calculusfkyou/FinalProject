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
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AllianceManagement extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> allianceList;
	private DefaultListModel<String> listModel;

	private JTextPane allianceTextPane;
	private JTextArea allianceDescriptionTextArea;
	private String[][] memberInfo = { 
		    { "0.17", "100/125", "1小時前", "5269" }, 
		    { "1.25", "40/40", "5小時前", "0" },
		    { "3.75", "66/50", "0小時前", "1490" },
		    { "4.73", "80/100", "30分鐘前", "9875" },
		    { "1.21", "50/75", "45分鐘前", "3248" },
		    { "4.94", "120/150", "15分鐘前", "6321" },
		    { "6.62", "90/110", "50分鐘前", "7543" },
		    { "2.45", "60/80", "20分鐘前", "4896" },
		    { "3.81", "110/130", "25分鐘前", "2157" },
		    { "6.58", "100/120", "40分鐘前", "5023" } };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllianceManagement frame = new AllianceManagement();
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
	public AllianceManagement() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\GIGABYTE\\eclipse" + "" + "-workspace\\Demo\\src\\Images\\logo.png"));
		setResizable(false); // 不能調整視窗大小
		setTitle("聯盟所有者");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(190, 100, 1535, 865);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 置中

		JLabel allManage_lbl = new JLabel("聯盟管理");
		allManage_lbl.setHorizontalAlignment(SwingConstants.LEFT);
		allManage_lbl.setFont(new Font("標楷體", Font.BOLD, 50));
		allManage_lbl.setBounds(30, 23, 400, 60);
		contentPane.add(allManage_lbl);

		JPanel MainPanel = new JPanel();
		MainPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		MainPanel.setBounds(50, 120, 500, 680);
		MainPanel.setLayout(null);
		contentPane.add(MainPanel);

		// 添加分隔線
		JSeparator mainSeparator = new JSeparator();
		mainSeparator.setOrientation(SwingConstants.HORIZONTAL);
		mainSeparator.setBounds(0, 55, 500, 1); // 調整分隔線的座標和大小
		// 設定分隔線的顏色
		Color mainSeparatorColor = Color.BLACK; // 更改為你想要的顏色
		mainSeparator.setForeground(mainSeparatorColor);
		MainPanel.add(mainSeparator);

		JTextPane memberTextPane = new JTextPane();
		memberTextPane.setText("ID：");
		memberTextPane.setOpaque(false);
		memberTextPane.setFont(new Font("標楷體", Font.BOLD, 40));
		memberTextPane.setEditable(false);
		memberTextPane.setBounds(10, 60, 400, 50);
		MainPanel.add(memberTextPane);

		JTextPane memberRateTextPane = new JTextPane();
		memberRateTextPane.setText("專家評分：");
		memberRateTextPane.setOpaque(false);
		memberRateTextPane.setFont(new Font("標楷體", Font.BOLD, 25));
		memberRateTextPane.setEditable(false);
		memberRateTextPane.setBounds(10, 120, 170, 40);
		MainPanel.add(memberRateTextPane);

		JTextPane memberWinLoseTextPane = new JTextPane();
		memberWinLoseTextPane.setText("勝/敗：");
		memberWinLoseTextPane.setOpaque(false);
		memberWinLoseTextPane.setFont(new Font("標楷體", Font.BOLD, 25));
		memberWinLoseTextPane.setEditable(false);
		memberWinLoseTextPane.setBounds(10, 165, 100, 40);
		MainPanel.add(memberWinLoseTextPane);

		JTextPane memberLatestOnlineTextPane = new JTextPane();
		memberLatestOnlineTextPane.setText("上次登錄時間：");
		memberLatestOnlineTextPane.setOpaque(false);
		memberLatestOnlineTextPane.setFont(new Font("標楷體", Font.BOLD, 25));
		memberLatestOnlineTextPane.setEditable(false);
		memberLatestOnlineTextPane.setBounds(10, 255, 200, 40);
		MainPanel.add(memberLatestOnlineTextPane);

		JTextPane rateTextPane = new JTextPane();
		rateTextPane.setText((String) null);
		rateTextPane.setOpaque(false);
		rateTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		rateTextPane.setEditable(false);
		rateTextPane.setBounds(160, 122, 130, 30);
		MainPanel.add(rateTextPane);

		JTextPane winLoseTextPane = new JTextPane();
		winLoseTextPane.setText((String) null);
		winLoseTextPane.setOpaque(false);
		winLoseTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		winLoseTextPane.setEditable(false);
		winLoseTextPane.setBounds(118, 170, 130, 30);
		MainPanel.add(winLoseTextPane);

		JTextPane latestOnlineTextPane = new JTextPane();
		latestOnlineTextPane.setText((String) null);
		latestOnlineTextPane.setOpaque(false);
		latestOnlineTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		latestOnlineTextPane.setEditable(false);
		latestOnlineTextPane.setBounds(209, 257, 130, 30);
		MainPanel.add(latestOnlineTextPane);

		JButton giveManager_btn = new JButton("給予管理者權限");
		giveManager_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (allianceList.getSelectedIndex() != -1) {
					// Get the selected member's name
					String selectedMember = allianceList.getSelectedValue();
					JOptionPane.showMessageDialog(contentPane, "已將成員 " + selectedMember + " 提升為管理員！", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					// No item selected, show a message to the user
					JOptionPane.showMessageDialog(contentPane, "請先選擇一個成員！", "提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		giveManager_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		giveManager_btn.setBounds(50, 310, 160, 40);
		MainPanel.add(giveManager_btn);

		JButton returnManager_btn = new JButton("收回管理者權限");
		returnManager_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (allianceList.getSelectedIndex() != -1) {
					// Get the selected member's name
					String selectedMember = allianceList.getSelectedValue();
					JOptionPane.showMessageDialog(contentPane, "已將成員 " + selectedMember + " 收回管理員權限！", "提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					// No item selected, show a message to the user
					JOptionPane.showMessageDialog(contentPane, "請先選擇一個成員！", "提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		returnManager_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		returnManager_btn.setBounds(290, 310, 160, 40);
		MainPanel.add(returnManager_btn);

		JLabel memberManage_lbl = new JLabel("成員管理");
		memberManage_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		memberManage_lbl.setFont(new Font("標楷體", Font.BOLD, 40));
		memberManage_lbl.setBounds(0, 2, 170, 55);
		MainPanel.add(memberManage_lbl);

		JTextPane memberLatestOnlineTextPane_1 = new JTextPane();
		memberLatestOnlineTextPane_1.setText("成員貢獻：");
		memberLatestOnlineTextPane_1.setOpaque(false);
		memberLatestOnlineTextPane_1.setFont(new Font("標楷體", Font.BOLD, 25));
		memberLatestOnlineTextPane_1.setEditable(false);
		memberLatestOnlineTextPane_1.setBounds(10, 210, 150, 40);
		MainPanel.add(memberLatestOnlineTextPane_1);

		JTextPane memberContributeTextPane = new JTextPane();
		memberContributeTextPane.setText((String) null);
		memberContributeTextPane.setOpaque(false);
		memberContributeTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		memberContributeTextPane.setEditable(false);
		memberContributeTextPane.setBounds(160, 215, 130, 30);
		MainPanel.add(memberContributeTextPane);

		JButton kickAll_btn = new JButton("踢出聯盟");
		kickAll_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < listModel.size(); i++) {
					if (listModel.getElementAt(i).equals(allianceList.getSelectedValue())) {
//						listModel.removeElementAt(i);
						AllianceOwner.kickMember(allianceList.getSelectedValue());
						if (checkIn(User.getLatestAccount())) {
							// 若已有聯盟，顯示聯盟成員列表
							allianceList.setVisible(true);
						} else {
							// 若沒有聯盟，隱藏聯盟成員列表
							allianceList.setVisible(false);
						}
						JOptionPane.showMessageDialog(contentPane, "成功從聯盟踢出此成員！", "提示",
								JOptionPane.INFORMATION_MESSAGE);
						rateTextPane.setText("");
						winLoseTextPane.setText("");
						latestOnlineTextPane.setText("");
						memberContributeTextPane.setText("");
						showAllManagement();
						break;
					}
				}
			}
		});
		kickAll_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		kickAll_btn.setBounds(200, 370, 100, 40);
		MainPanel.add(kickAll_btn);

		JPanel IDPanel = new JPanel();
		IDPanel.setBounds(1235, 25, 250, 69);
		IDPanel.setLayout(null);
		IDPanel.setBorder(new LineBorder(Color.BLACK, 2));
		contentPane.add(IDPanel);

		JPanel chatPanel = new JPanel();
		chatPanel.setBounds(1236, 760, 300, 75);
		chatPanel.setLayout(null);
		contentPane.add(chatPanel);

		JPanel ALLPanel = new JPanel();
		ALLPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		ALLPanel.setBounds(600, 120, 600, 680);
		ALLPanel.setLayout(null);
		contentPane.add(ALLPanel);

		JLabel allSeecondManage_lbl = new JLabel("聯盟設置");
		allSeecondManage_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		allSeecondManage_lbl.setFont(new Font("標楷體", Font.BOLD, 40));
		allSeecondManage_lbl.setBounds(0, 2, 170, 55);
		ALLPanel.add(allSeecondManage_lbl);

		JSeparator mainSeparator_1 = new JSeparator();
		mainSeparator_1.setOrientation(SwingConstants.HORIZONTAL);
		mainSeparator_1.setForeground(Color.BLACK);
		mainSeparator_1.setBounds(0, 54, 600, 1);
		ALLPanel.add(mainSeparator_1);

		JButton chamgeAllName_btn = new JButton("更改聯盟名稱");
		chamgeAllName_btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 彈出對話框，讓用戶輸入新的聯盟名稱
				String newAllName = JOptionPane.showInputDialog(contentPane, "請輸入新的聯盟名稱:", "輸入",
						JOptionPane.PLAIN_MESSAGE);

				// 檢查用戶是否輸入了內容
				if (newAllName != null && !newAllName.trim().isEmpty()) {
					User.nowAlliance(User.getLatestAccount(), newAllName); // 更新聯盟名稱
					allianceTextPane.setText("聯盟：" + User.getLatestAlliance());
					JOptionPane.showMessageDialog(contentPane, "成功更改聯盟名稱！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else if (newAllName == null) {
					// 用戶取消了輸入，不執行任何操作
				} else {
					// 用戶取消了輸入或者沒有輸入任何內容
					JOptionPane.showMessageDialog(contentPane, "請輸入有效的聯盟名稱！", "錯誤", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		chamgeAllName_btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		chamgeAllName_btn.setBounds(10, 300, 160, 40);
		ALLPanel.add(chamgeAllName_btn);

		// Inside the AllianceManagement constructor
		allianceDescriptionTextArea = new JTextArea();
		allianceDescriptionTextArea.setFont(new Font("標楷體", Font.PLAIN, 20));
		allianceDescriptionTextArea.setLineWrap(true);
		allianceDescriptionTextArea.setWrapStyleWord(true);
		allianceDescriptionTextArea.setText(AllianceOwner.getAllianceDescription());
		allianceDescriptionTextArea.setBounds(10, 116, 580, 170); // Adjust the bounds as needed
		ALLPanel.add(allianceDescriptionTextArea);

		JLabel allInfo_lbl = new JLabel("簡介");
		allInfo_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		allInfo_lbl.setFont(new Font("標楷體", Font.BOLD, 40));
		allInfo_lbl.setBounds(0, 65, 100, 55);
		ALLPanel.add(allInfo_lbl);

		JButton updateDescriptionBtn = new JButton("更新簡介");
		updateDescriptionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newDescription = allianceDescriptionTextArea.getText().trim();

				if (!newDescription.isEmpty()) {
					AllianceOwner.setAllianceDescription(newDescription);
					JOptionPane.showMessageDialog(contentPane, "成功更新聯盟簡介！", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(contentPane, "請輸入有效的聯盟簡介！", "錯誤", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		updateDescriptionBtn.setFont(new Font("標楷體", Font.PLAIN, 15));
		updateDescriptionBtn.setBounds(490, 300, 100, 40);
		ALLPanel.add(updateDescriptionBtn);

		ImageIcon cooperation = new ImageIcon(
				"C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\cooperation.png");
		JLabel cooperation_lbl = new JLabel(cooperation);
		cooperation_lbl.setBounds(30, 425, 150, 150);
		cooperation_lbl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// 鼠標進入時顯示的文字
				cooperation_lbl.setToolTipText("邦交");
			}

			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(contentPane, "邦交系統尚未準備完善，請耐心等候！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 鼠標離開時清空文字
				cooperation_lbl.setToolTipText(null);
			}
		});
		cooperation_lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定游標為手形
		ALLPanel.add(cooperation_lbl);

		ImageIcon task = new ImageIcon("C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\task.png");
		JLabel task_lbl = new JLabel(task);
		task_lbl.setBounds(225, 425, 150, 150);
		task_lbl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// 鼠標進入時顯示的文字
				task_lbl.setToolTipText("計畫活動");
			}

			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(contentPane, "計畫功能尚未準備完善，請耐心等候！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 鼠標離開時清空文字
				task_lbl.setToolTipText(null);
			}
		});
		task_lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定游標為手形
		ALLPanel.add(task_lbl);

		ImageIcon dashboard = new ImageIcon("C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\dashboard.png");
		JLabel dashboard_lbl = new JLabel(dashboard);
		dashboard_lbl.setBounds(420, 425, 150, 150);
		dashboard_lbl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// 鼠標進入時顯示的文字
				dashboard_lbl.setToolTipText("儀錶板");
			}

			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(contentPane, "儀錶板尚未準備完善，請耐心等候！", "提示", JOptionPane.INFORMATION_MESSAGE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// 鼠標離開時清空文字
				dashboard_lbl.setToolTipText(null);
			}
		});
		dashboard_lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定游標為手形
		ALLPanel.add(dashboard_lbl);

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
		allianceList.addListSelectionListener(e -> {
			// Randomly select a set of information from memberInfo array
			int randomIndex = (int) (Math.random() * memberInfo.length);
			String[] selectedMemberInfo = memberInfo[randomIndex];
			rateTextPane.setText(selectedMemberInfo[0]);
			winLoseTextPane.setText(selectedMemberInfo[1]);
			latestOnlineTextPane.setText(selectedMemberInfo[2]);
			memberContributeTextPane.setText(selectedMemberInfo[3]);

			String selectedAlliance = allianceList.getSelectedValue();
			memberTextPane.setText("ID：" + selectedAlliance);
		});

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

		allianceTextPane = new JTextPane();
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
		manageGame_btn.setBounds(700, 40, 100, 40);
		contentPane.add(manageGame_btn);

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
