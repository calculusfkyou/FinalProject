package FinalProject;

import java.awt.AlphaComposite;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class BuildAlliance extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputAllaince_textField;
	private Choice choice;
	// preview component
	private JLabel previewLabel;

	private List<Announcement> announcements; // 存儲布告欄消息和相應的圖片
	private JLabel announcementLabel;
	private JLabel imageLabel;
	private Timer timer;

	private JPanel BullPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuildAlliance frame = new BuildAlliance();
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
	public BuildAlliance() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\logo.png"));
		setResizable(false); // 不能調整視窗大小
		setTitle("建立聯盟");
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
		BullPanel.setBounds(381, 101, 1140, 730);
		BullPanel.setLayout(null);
		contentPane.add(BullPanel);

		// 添加分隔線
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.HORIZONTAL);
		separator.setBounds(0, 100, 1535, 1); // 調整分隔線的座標和大小
		Color separatorColor = Color.BLACK; // 更改為你想要的顏色
		separator.setForeground(separatorColor);
		contentPane.add(separator);

		// 添加分隔線
		JSeparator separator3 = new JSeparator();
		separator3.setOrientation(SwingConstants.VERTICAL);
		separator3.setBounds(380, 100, 1, 765); // 調整分隔線的座標和大小
		Color separator3Color = Color.BLACK; // 更改為你想要的顏色
		separator3.setForeground(separator3Color);
		contentPane.add(separator3);

		JButton logout_Btn = new JButton("Logout");
		logout_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPlayerLoginInterface();
			}
		});
		logout_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		logout_Btn.setBounds(1100, 40, 100, 40);
		contentPane.add(logout_Btn);

		JLabel buildAlliance_lbl = new JLabel("創建聯盟");
		buildAlliance_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		buildAlliance_lbl.setFont(new Font("標楷體", Font.BOLD, 50));
		buildAlliance_lbl.setBounds(25, 25, 220, 60);
		contentPane.add(buildAlliance_lbl);

		JPanel allPanel = new JPanel();
		allPanel.setLayout(null);
		allPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		allPanel.setBounds(20, 120, 350, 675);
		contentPane.add(allPanel);

		JLabel buildAll_lbl = new JLabel("建立聯盟");
		buildAll_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		buildAll_lbl.setFont(new Font("標楷體", Font.BOLD, 40));
		buildAll_lbl.setBounds(0, 0, 180, 55);
		allPanel.add(buildAll_lbl);

		JLabel allianceName_lbl = new JLabel("聯盟名稱：");
		allianceName_lbl.setBounds(0, 70, 150, 50);
		allPanel.add(allianceName_lbl);
		allianceName_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		allianceName_lbl.setFont(new Font("標楷體", Font.BOLD, 25));

		JLabel allianceCount_lbl = new JLabel("聯盟人數：");
		allianceCount_lbl.setBounds(0, 170, 150, 50);
		allPanel.add(allianceCount_lbl);
		allianceCount_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		allianceCount_lbl.setFont(new Font("標楷體", Font.BOLD, 25));

		// 加入 JComboBox
		choice = new Choice();
		for (int i = 1; i <= 50; i++) {
			choice.add(Integer.toString(i));
		}
		choice.setBounds(15, 220, 80, 30);
		allPanel.add(choice);

		JButton back_Btn = new JButton("返回");
		back_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPlayerInterface();
			}
		});
		back_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		back_Btn.setBounds(1000, 40, 100, 40);
		contentPane.add(back_Btn);

		inputAllaince_textField = new JTextField();
		inputAllaince_textField.setBounds(15, 120, 250, 40);
		allPanel.add(inputAllaince_textField);
		inputAllaince_textField.setColumns(10);

		JLabel allianceAvatar_lbl = new JLabel("聯盟圖片：");
		allianceAvatar_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		allianceAvatar_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		allianceAvatar_lbl.setBounds(0, 330, 150, 50);
		allPanel.add(allianceAvatar_lbl);

		JButton uploadButton = new JButton("上傳檔案");
		uploadButton.setFont(new Font("標楷體", Font.PLAIN, 15));
		uploadButton.setBounds(155, 332, 110, 40);
		uploadButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseAndSetImage();
			}
		});
		allPanel.add(uploadButton);

		JLabel preview_lbl = new JLabel("預覽圖片");
		preview_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		preview_lbl.setFont(new Font("標楷體", Font.BOLD, 20));
		preview_lbl.setBounds(0, 377, 100, 40);
		allPanel.add(preview_lbl);

		// 預覽圖片
		previewLabel = new JLabel();
		previewLabel.setBounds(10, 417, 150, 150);
		previewLabel.setBackground(Color.GRAY);
		previewLabel.setOpaque(true);
		previewLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 設定邊框，黑色，寬度2像素
		allPanel.add(previewLabel);

		JLabel allianceType_lbl = new JLabel("聯盟類型：");
		allianceType_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		allianceType_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		allianceType_lbl.setBounds(0, 250, 150, 50);
		allPanel.add(allianceType_lbl);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("競技");
		rdbtnNewRadioButton.setFont(new Font("標楷體", Font.PLAIN, 15));
		rdbtnNewRadioButton.setBounds(15, 300, 60, 23);
		allPanel.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("娛樂");
		rdbtnNewRadioButton_1.setFont(new Font("標楷體", Font.PLAIN, 15));
		rdbtnNewRadioButton_1.setBounds(90, 300, 60, 23);
		allPanel.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("休閒");
		rdbtnNewRadioButton_2.setFont(new Font("標楷體", Font.PLAIN, 15));
		rdbtnNewRadioButton_2.setBounds(165, 300, 60, 23);
		allPanel.add(rdbtnNewRadioButton_2);

		// 將JRadioButton放入ButtonGroup中，這樣一次只能選中一個
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(rdbtnNewRadioButton);
		radioButtonGroup.add(rdbtnNewRadioButton_1);
		radioButtonGroup.add(rdbtnNewRadioButton_2);

		JButton build_Btn = new JButton("創建");
		build_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String allianceName = inputAllaince_textField.getText(); // 取得使用者輸入的聯盟名稱

				// 檢查是否有輸入聯盟名稱
				if (allianceName.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "請輸入聯盟名稱！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 檢查是否有選擇聯盟圖片
				if (previewLabel.getIcon() == null) {
					JOptionPane.showMessageDialog(contentPane, "請上傳聯盟圖片！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 檢查是否有選擇聯盟圖片
				if (previewLabel.getIcon() == null) {
					JOptionPane.showMessageDialog(contentPane, "請上傳聯盟圖片！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 取得選擇的創建人數
				int playerCount = Integer.parseInt(choice.getSelectedItem());
				AllianceOwner.nowMemberAmount(playerCount);

				// 在這裡處理建立聯盟的邏輯，例如儲存到資料庫或顯示訊息
				JOptionPane.showMessageDialog(contentPane, "成功建立聯盟：" + allianceName, "成功",
						JOptionPane.INFORMATION_MESSAGE);
				AllianceOwner.initMember();
				saveUserAlliance(User.getLatestAccount(), allianceName, Integer.toString(playerCount));
				showPlayerInterface();
				dispose();
			}
		});
		build_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		build_Btn.setBounds(135, 600, 80, 40);
		allPanel.add(build_Btn);

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

		ImageIcon avatar = new ImageIcon("C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images/avatar.jpg");
		JLabel avatar_lbl = new JLabel(avatar);
		avatar_lbl.setBounds(3, 2, 65, 65);
		IDPanel.add(avatar_lbl);

		initializeAnnouncements();
		initializeUI();
		setupTimer();
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

	// 上傳檔案
	private void chooseAndSetImage() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
		fileChooser.setFileFilter(filter);

		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			String imagePath = fileChooser.getSelectedFile().getPath();
			ImageIcon imageIcon = new ImageIcon(imagePath);

			// 調整預覽圖片的大小
			int width = previewLabel.getWidth();
			int height = previewLabel.getHeight();
			Image scaledImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			imageIcon = new ImageIcon(scaledImage);

			previewLabel.setIcon(imageIcon);
		}
	}

	private void initializeUI() {
		// 創建 JLabel 來顯示布告欄消息
		announcementLabel = new JLabel();
		announcementLabel.setFont(new Font("標楷體", Font.BOLD, 25));
		announcementLabel.setHorizontalAlignment(SwingConstants.CENTER);
		announcementLabel.setBounds(0, 0, 1140, 130);
		BullPanel.add(announcementLabel);

		// 創建 JLabel 來顯示圖片
		imageLabel = new JLabel();
		imageLabel.setBounds(0, 130, 1140, 600);
		BullPanel.add(imageLabel);
	}

	private void initializeAnnouncements() {
		// 初始化布告欄消息和相應的圖片
		announcements = new ArrayList<>();
		announcements.add(new Announcement("2024世界賽即將開打！歡迎各位購票入場",
				"C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\news4.jpg"));
		announcements.add(new Announcement("的賽斷開連接！微博和奧迪沒有贊助",
				"C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\news5.jpg"));
		announcements.add(new Announcement("嘎痛：覺得牌位積分不重要 不講是誰 \"西X\"..不練角 永遠站在老山那邊 每次出去都被雷茲虐",
				"C:\\Users\\GIGABYTE\\eclipse-workspace\\Demo\\src\\Images\\news7.jpg"));
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
		private String imagePath;

		public Announcement(String message, String imagePath) {
			this.message = message;
			this.imagePath = imagePath;
		}

		public String getMessage() {
			return message;
		}

		public String getImagePath() {
			return imagePath;
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

	// for聯盟所有者
	private void saveUserAlliance(String account, String alliance, String amount) { // 儲存用戶聯盟資料
		User.addAlliance(account, alliance, amount);
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

	// ESC離開視窗
	private void showExitConfirmation() {
		int choice = JOptionPane.showConfirmDialog(contentPane, "Do you want to exit the program?", "Exit Program",
				JOptionPane.YES_NO_OPTION);

		if (choice == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
