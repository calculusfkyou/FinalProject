package FinalProject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class Audience extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private List<Announcement> announcements; // 存儲布告欄消息和相應的圖片
	private JLabel announcementLabel;
	private JLabel imageLabel;
	private Timer timer;

	private JLabel marqueeLabel1; // 跑馬燈的 JLabel
	private JLabel marqueeLabel2; // 跑馬燈的 JLabel

	private JPanel btnPanel;

	private JPanel BullPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Audience frame = new Audience();
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
	public Audience() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Audience.class.getResource("/Images/logo.png")));
		setResizable(false); // 不能調整視窗大小
		setTitle("觀眾");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(190, 100, 1535, 865);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 置中

		BullPanel = new JPanel();
		BullPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		BullPanel.setBounds(217, 100, 1100, 600);
		BullPanel.setLayout(null);
		contentPane.add(BullPanel);

		btnPanel = new JPanel();
		btnPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnPanel.setBounds(217, 700, 1100, 130);
		btnPanel.setLayout(null);
		contentPane.add(btnPanel);

		// 添加分隔線
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.HORIZONTAL);
		separator.setBounds(0, 100, 1535, 1); // 調整分隔線的座標和大小
		// 設定分隔線的顏色
		Color separatorColor = Color.BLACK; // 更改為你想要的顏色
		separator.setForeground(separatorColor);
		contentPane.add(separator);

		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setBounds(217, 100, 1, 765); // 調整分隔線的座標和大小
		Color separator2Color = Color.BLACK; // 更改為你想要的顏色
		separator2.setForeground(separator2Color);
		contentPane.add(separator2);

		JSeparator separator3 = new JSeparator();
		separator3.setOrientation(SwingConstants.VERTICAL);
		separator3.setBounds(1317, 100, 1, 765); // 調整分隔線的座標和大小
		Color separator3Color = Color.BLACK; // 更改為你想要的顏色
		separator3.setForeground(separator3Color);
		contentPane.add(separator3);

		JLabel audience_lbl = new JLabel("Audience Mode");
		audience_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		audience_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 50));
		audience_lbl.setBounds(30, 23, 400, 60);
		contentPane.add(audience_lbl);

		// 創建跑馬燈的 JLabel
		marqueeLabel1 = new JLabel("廣\r\n告"); // 這裡的內容可以是你想要的廣告文本
		marqueeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		marqueeLabel1.setFont(new Font("標楷體", Font.PLAIN, 100));
		marqueeLabel1.setForeground(Color.BLACK); // 設定字體顏色
		marqueeLabel1.setBounds(0, 100, 217, 100); // 設定區域位置和大小
		contentPane.add(marqueeLabel1);

		marqueeLabel2 = new JLabel("廣\r\n告"); // 這裡的內容可以是你想要的廣告文本
		marqueeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		marqueeLabel2.setFont(new Font("標楷體", Font.PLAIN, 100));
		marqueeLabel2.setForeground(Color.BLACK); // 設定字體顏色
		marqueeLabel2.setBounds(1317, 100, 217, 100); // 設定區域位置和大小
		contentPane.add(marqueeLabel2);

		// 設定 Timer 用於更新跑馬燈
		Timer marqueeTimer = new Timer(100, new ActionListener() {
			int currentY = 100;

			@Override
			public void actionPerformed(ActionEvent e) {
				// 移動跑馬燈的 Y 座標
				currentY += 5; // 可以根據需要調整移動速度
				marqueeLabel1.setBounds(0, currentY, 217, 100);
				marqueeLabel2.setBounds(1317, 965 - currentY, 217, 100);

				// 如果跑馬燈到達底部，重置 Y 座標
				if (currentY >= 865) {
					currentY = 100; // 回到頂部
				}
			}
		});
		marqueeTimer.start(); // 啟動 Timer

		JButton watch_Btn = new JButton("觀看比賽");
		watch_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openExternalWebsite("https://www.twitch.tv/directory/category/league-of-legends"); // 想要前往的網站 URL
			}
		});
		watch_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		watch_Btn.setBounds(850, 45, 150, 40);
		btnPanel.add(watch_Btn);

		JButton reSelect_Btn = new JButton("重新選擇身分");
		reSelect_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMain();
			}
		});
		reSelect_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		reSelect_Btn.setBounds(100, 45, 150, 40);
		btnPanel.add(reSelect_Btn);

		JButton viewInfo_Btn = new JButton("查看比賽資訊");
		viewInfo_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openExternalWebsite("https://www.op.gg/");
			}
		});
		viewInfo_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		viewInfo_Btn.setBounds(475, 45, 150, 40);
		btnPanel.add(viewInfo_Btn);

		// 文字圖片輪播
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

	private void initializeUI() {
		// 創建 JLabel 來顯示布告欄消息
		announcementLabel = new JLabel();
		announcementLabel.setFont(new Font("標楷體", Font.BOLD, 30));
		announcementLabel.setHorizontalAlignment(SwingConstants.CENTER);
		announcementLabel.setBounds(0, 25, 1140, 50);
		BullPanel.add(announcementLabel);
//		contentPane.setComponentZOrder(announcementLabel, 0);

		// 創建 JLabel 來顯示圖片
		imageLabel = new JLabel();
		imageLabel.setBounds(0, 100, 1100, 500);
		BullPanel.add(imageLabel);
	}

	private void initializeAnnouncements() {
		// 初始化布告欄消息和相應的圖片
		announcements = new ArrayList<>();
		announcements.add(new Announcement("恭喜TPA獲得S99總冠軍！",
				Audience.class.getResource("/Images/news1.jpg")));
		announcements.add(new Announcement("冠軍造型竟然不是阿璃！阿璃最黑暗的第十一年！",
				Audience.class.getResource("/Images/news2.jpg")));
		announcements.add(new Announcement("世界賽打得不像人！被調侃精忠報國！？",
				Audience.class.getResource("/Images/news3.jpg")));
		announcements.add(new Announcement("把我當戰犯？我直接續約！",
				Audience.class.getResource("/Images/news6.jpg")));
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

	private void showMain() {
		Main mainPage = new Main();
		mainPage.setVisible(true);
		setVisible(false);
	}

	// 開啟外部網站
	private void openExternalWebsite(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException | URISyntaxException ex) {
			ex.printStackTrace();
		}
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
