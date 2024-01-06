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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

public class AdvertisementSet extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// preview component
	private JLabel previewLabel;

	private JComboBox<String> sponsorshipComboBox;
	private JDateChooser startDateChooser;
	private JPanel sponsorshipDatePanel;
	private String selectedSponsorship; // 用來儲存選擇的方案
	private String startDate; // 用來儲存選擇的日期
	private String endDate;

	private JPanel balancePanel;
	private long days;
	private JLabel case_lbl;

	private JTextPane endDayTextPane;
	private JTextPane startDayTextPane;
	private JTextPane caseTextPane;
	private JTextPane payDayTextPane;
	private JTextPane remainDayTextPane;
	private JTextPane payTextPane;
	private static boolean check = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvertisementSet frame = new AdvertisementSet();
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
	public AdvertisementSet() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdvertisementSet.class.getResource("/Images/logo.png")));
		setResizable(false); // 不能調整視窗大小
		setTitle("設置廣告");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(190, 100, 1535, 865);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 置中

		// 添加分隔線
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.HORIZONTAL);
		separator.setBounds(0, 100, 1535, 1); // 調整分隔線的座標和大小
		// 設定分隔線的顏色
		Color separatorColor = Color.BLACK; // 更改為你想要的顏色
		separator.setForeground(separatorColor);
		contentPane.add(separator);

		JButton back_Btn = new JButton("返回");
		back_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Advertiser.setAdRemainDays(0);
				Advertiser.setCaseChoose("");
				Advertiser.setStartDay("");
				Advertiser.setEndDay("");
				showAdvertisingAgency();
			}
		});
		back_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		back_Btn.setBounds(1400, 30, 80, 40);
		contentPane.add(back_Btn);

		JLabel audience_lbl = new JLabel("Advertisement Set");
		audience_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		audience_lbl.setFont(new Font("Bookman Old Style", Font.BOLD, 50));
		audience_lbl.setBounds(30, 20, 500, 60);
		contentPane.add(audience_lbl);

		// Choose File Panel
		JPanel chooseFilePanel = new JPanel();
		chooseFilePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		chooseFilePanel.setBounds(30, 125, 600, 675);
		chooseFilePanel.setLayout(null);
		contentPane.add(chooseFilePanel);

		JLabel chooseFile_lbl = new JLabel("1.選擇檔案");
		chooseFile_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		chooseFile_lbl.setFont(new Font("標楷體", Font.BOLD, 40));
		chooseFile_lbl.setBounds(0, 0, 240, 55);
		chooseFilePanel.add(chooseFile_lbl);

		JButton uploadButton = new JButton("上傳檔案");
		uploadButton.setFont(new Font("標楷體", Font.PLAIN, 15));
		uploadButton.setBounds(260, 8, 110, 30);
		uploadButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseAndSetImage();
			}
		});
		chooseFilePanel.add(uploadButton);

		JLabel preview_lbl = new JLabel("預覽圖片");
		preview_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		preview_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		preview_lbl.setBounds(0, 75, 120, 40);
		chooseFilePanel.add(preview_lbl);

		// 預覽圖片
		previewLabel = new JLabel();
		previewLabel.setBounds(0, 125, 600, 550);
		previewLabel.setBackground(Color.GRAY);
		previewLabel.setOpaque(true);
		previewLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 設定邊框，黑色，寬度2像素
		chooseFilePanel.add(previewLabel);

		// Choose Case Panel
		sponsorshipDatePanel = new JPanel();
		sponsorshipDatePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		sponsorshipDatePanel.setBounds(647, 125, 475, 675);
		sponsorshipDatePanel.setLayout(null);
		contentPane.add(sponsorshipDatePanel);

		JLabel chooseCase_lbl = new JLabel("2.選擇方案 & 結束日期");
		chooseCase_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		chooseCase_lbl.setFont(new Font("標楷體", Font.BOLD, 40));
		chooseCase_lbl.setBounds(0, 0, 450, 55);
		sponsorshipDatePanel.add(chooseCase_lbl);

		String[] sponsorshipOptions = { "請選擇...", "競賽贊助商", "聯盟贊助商" };
		sponsorshipComboBox = new JComboBox<>(sponsorshipOptions);
		sponsorshipComboBox.setFont(new Font("標楷體", Font.PLAIN, 15));
		sponsorshipComboBox.setBounds(160, 75, 130, 40);
		sponsorshipDatePanel.add(sponsorshipComboBox);

		// 選擇日期
		startDateChooser = new JDateChooser();
		startDateChooser.setBounds(160, 140, 130, 20);
		sponsorshipDatePanel.add(startDateChooser);

		JDateChooser endDateChooser = new JDateChooser();
		endDateChooser.setBounds(160, 185, 130, 20);
		sponsorshipDatePanel.add(endDateChooser);

		JButton check_btn = new JButton("確認");
		check_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
					JOptionPane.showMessageDialog(contentPane, "選擇成功，請於規定時間內付款！", "提示",
							JOptionPane.INFORMATION_MESSAGE);
					check = true;
				}
				// 在確認按鈕的事件處理中賦值
				selectedSponsorship = (String) sponsorshipComboBox.getSelectedItem();
				startDate = formatDate(startDateChooser.getDate());
				endDate = formatDate(endDateChooser.getDate());

				LocalDate today = LocalDate.now(); // 取得今天的日期
				LocalDate tenDaysLater = today.plusDays(10); // 計算10天後的日期

				// 計算起始日期到結束日期的天數
				long diff = endDateChooser.getDate().getTime() - startDateChooser.getDate().getTime();
				days = diff / (24 * 60 * 60 * 1000);

				Advertiser.setCaseChoose(selectedSponsorship);
				Advertiser.setStartDay(startDate);
				Advertiser.setEndDay(endDate);
				Advertiser.setAdRemainDays(days);

				caseTextPane.setText(Advertiser.getCaseChoose());
				startDayTextPane.setText(Advertiser.getStartDay());
				endDayTextPane.setText(Advertiser.getEndDay());
				remainDayTextPane.setText(Long.toString(Advertiser.getAdRemainDays()));
				payDayTextPane.setText(formatDate(tenDaysLater));
				payTextPane.setText(Long.toString(Advertiser.getAdRemainDays() * 100));
			}
		});
		check_btn.setFont(new Font("標楷體", Font.PLAIN, 20));
		check_btn.setBounds(185, 230, 80, 40);
		sponsorshipDatePanel.add(check_btn);

		JLabel startDay_lbl = new JLabel("起始日期");
		startDay_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		startDay_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		startDay_lbl.setBounds(0, 125, 150, 50);
		sponsorshipDatePanel.add(startDay_lbl);

		JLabel endDay_lbl = new JLabel("結束日期");
		endDay_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		endDay_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		endDay_lbl.setBounds(0, 169, 150, 50);
		sponsorshipDatePanel.add(endDay_lbl);

		balancePanel = new JPanel();
		balancePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		balancePanel.setBounds(1150, 125, 350, 675);
		balancePanel.setLayout(null);
		contentPane.add(balancePanel);

		JLabel cost_lbl = new JLabel("3.應付餘額");
		cost_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		cost_lbl.setFont(new Font("標楷體", Font.BOLD, 40));
		cost_lbl.setBounds(0, 0, 240, 55);
		balancePanel.add(cost_lbl);

		JLabel startDay_lbl_2 = new JLabel("起始日期：");
		startDay_lbl_2.setHorizontalAlignment(SwingConstants.CENTER);
		startDay_lbl_2.setFont(new Font("標楷體", Font.BOLD, 25));
		startDay_lbl_2.setBounds(0, 120, 150, 50);
		balancePanel.add(startDay_lbl_2);

		JLabel endDay_lbl_1 = new JLabel("結束日期：");
		endDay_lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		endDay_lbl_1.setFont(new Font("標楷體", Font.BOLD, 25));
		endDay_lbl_1.setBounds(0, 170, 150, 50);
		balancePanel.add(endDay_lbl_1);

		case_lbl = new JLabel("方案：");
		case_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		case_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		case_lbl.setBounds(0, 70, 150, 50);
		balancePanel.add(case_lbl);

		caseTextPane = new JTextPane();
		caseTextPane.setEditable(false);
		caseTextPane.setOpaque(false); // 設定為透明
		caseTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		caseTextPane.setBounds(160, 75, 130, 30);
		caseTextPane.setText(Advertiser.getCaseChoose());
		balancePanel.add(caseTextPane);

		startDayTextPane = new JTextPane();
		startDayTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		startDayTextPane.setEditable(false);
		startDayTextPane.setOpaque(false); // 設定為透明
		startDayTextPane.setBounds(160, 125, 130, 30);
		startDayTextPane.setText(Advertiser.getStartDay());
		balancePanel.add(startDayTextPane);

		endDayTextPane = new JTextPane();
		endDayTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		endDayTextPane.setEditable(false);
		endDayTextPane.setOpaque(false); // 設定為透明
		endDayTextPane.setBounds(160, 175, 130, 30);
		endDayTextPane.setText(Advertiser.getEndDay());
		balancePanel.add(endDayTextPane);

		JLabel remainDay_lbl = new JLabel("持續天數：");
		remainDay_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		remainDay_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		remainDay_lbl.setBounds(0, 220, 150, 50);
		balancePanel.add(remainDay_lbl);

		JLabel payDay_lbl = new JLabel("繳費期限：");
		payDay_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		payDay_lbl.setFont(new Font("標楷體", Font.BOLD, 25));
		payDay_lbl.setBounds(0, 270, 150, 50);
		balancePanel.add(payDay_lbl);

		remainDayTextPane = new JTextPane();
		remainDayTextPane.setText((String) null);
		remainDayTextPane.setOpaque(false);
		remainDayTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		remainDayTextPane.setEditable(false);
		remainDayTextPane.setBounds(160, 225, 130, 30);
		balancePanel.add(remainDayTextPane);

		payDayTextPane = new JTextPane();
		payDayTextPane.setText((String) null);
		payDayTextPane.setOpaque(false);
		payDayTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		payDayTextPane.setEditable(false);
		payDayTextPane.setBounds(160, 275, 130, 30);
		balancePanel.add(payDayTextPane);

		JLabel payDay_lbl_1 = new JLabel("應付餘額：");
		payDay_lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		payDay_lbl_1.setFont(new Font("標楷體", Font.BOLD, 25));
		payDay_lbl_1.setBounds(0, 320, 150, 50);
		balancePanel.add(payDay_lbl_1);

		payTextPane = new JTextPane();
		payTextPane.setText((String) null);
		payTextPane.setOpaque(false);
		payTextPane.setFont(new Font("標楷體", Font.BOLD, 20));
		payTextPane.setEditable(false);
		payTextPane.setBounds(160, 325, 130, 30);
		balancePanel.add(payTextPane);

		JButton goPay_Btn = new JButton("前往付款");
		goPay_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check) 
					showPaymentImage();
				else
					JOptionPane.showMessageDialog(contentPane, "請先完成先前步驟！", "警告", JOptionPane.ERROR_MESSAGE);
			}
		});
		goPay_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		goPay_Btn.setBounds(125, 390, 100, 40);
		balancePanel.add(goPay_Btn);

		JButton cancelAD_Btn = new JButton("取消廣告");
		cancelAD_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Advertiser.setAdRemainDays(0);
				Advertiser.setCaseChoose("");
				Advertiser.setStartDay("");
				Advertiser.setEndDay("");
				JOptionPane.showMessageDialog(contentPane, "您已取消廣告", "提示", JOptionPane.INFORMATION_MESSAGE);
				showAdvertisementSet();
			}
		});
		cancelAD_Btn.setFont(new Font("標楷體", Font.PLAIN, 15));
		cancelAD_Btn.setBounds(1250, 30, 100, 40);
		contentPane.add(cancelAD_Btn);

		ImageIcon BkIcon = new ImageIcon(
				AdvertisementSet.class.getResource("/Images/buildAllianceBk.jpg")); // 背景圖片
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

	private static void showPaymentImage() {

		// 加載付款圖片
		ImageIcon paymentImage = new ImageIcon(
				AdvertisementSet.class.getResource("/Images/payment.jpg"));

		// 以JLabel的方式顯示圖片
		JLabel label = new JLabel(paymentImage);

		// 加入下載按鈕
		Object[] options = { "OK", "Download" };
		int choice = JOptionPane.showOptionDialog(null, label, "付款圖片", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		// 如果使用者選擇 "Download"
		if (choice == 1) {
			downloadImage(AdvertisementSet.class.getResource("/Images/payment.jpg"));
		}
	}

	// 下載圖片的方法
	private static void downloadImage(URL url) {
		// 實作下載圖片的邏輯，這裡只是一個示例
		// 可以使用 Java 的檔案複製或下載相關的函數，根據需求實作
		// 這裡僅供參考
		JOptionPane.showMessageDialog(null, "下載成功", "提示", JOptionPane.INFORMATION_MESSAGE);
	}

	// 格式化日期
	private String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	// 格式化日期
	private static String formatDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return date.format(formatter);
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

	private void showAdvertisingAgency() {
		AdvertisingAgency advertisingAgency = new AdvertisingAgency();
		advertisingAgency.setVisible(true);
		setVisible(false);
	}

	private void showAdvertisementSet() {
		AdvertisementSet advertisementSet = new AdvertisementSet();
		advertisementSet.setVisible(true);
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
