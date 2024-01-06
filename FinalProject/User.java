package FinalProject;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class User {
	private String account;
	private String passWord;
	static ArrayList<String[]> userInformation = new ArrayList<>(); // {帳號, 密碼}，用於儲存帳密資訊
	private static String latestAccount; // 目前帳號
	private static String latestAlliance = "無"; // 聯盟預設為無
	static ArrayList<String[]> userAlliance = new ArrayList<>(); // 建立者：{帳號, 聯盟名稱, 人數}, 玩家：{帳號, 聯盟名稱}，用於儲存聯盟資訊
	private static String latestGame = "無";
	static ArrayList<String[]> userGame = new ArrayList<>(); // {帳號，遊戲}

	public User() { // 預設
		User.userInformation.add(new String[] { "12345", "12345" }); // {帳號, 密碼}
		User.userInformation.add(new String[] { "abcde", "xyz123" });
		User.userInformation.add(new String[] { "1", "1" });
	}

	public User(String account, String passWord) {
		this.account = account;
		this.passWord = passWord;
		User.userInformation.add(new String[] { "12345", "12345" });
		User.userInformation.add(new String[] { "abcde", "xyz123" });
		User.userInformation.add(new String[] { "1", "1" });
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return passWord;
	}

	public static String getLatestAccount() {
		return latestAccount;
	}

	public static String getLatestAlliance() {
		return latestAlliance;
	}

	public ArrayList<String[]> getUserInformation() {
		return userInformation;
	}

	public static ArrayList<String[]> getUserAlliance() {
		return userAlliance;
	}

	public void addInfo(String account, String passWord) { // 新增帳號對應密碼資訊
		User.userInformation.add(new String[] { account, passWord });
	}

	public static void addAlliance(String account, String alliance, String amount) { // 新增帳號對應聯盟資訊，for聯盟所有者
		userAlliance.add(new String[] { account, alliance, amount, "1" }); // 1代表聯盟所有人
	}

	public static void addGame(String account, String game) {
		boolean check = false;
		for (String[] i : userGame) {
			if (i[0].equals(account)) {
				i[1] = game; // 更新目前的遊戲
				check = true;
				break;
			}
		}
		if (!check)
			userGame.add(new String[] { account, game });
	}

	public static String getLatestGame() {
		return latestGame;
	}

	public static void nowGame(String account) { // 確認現在遊戲
		boolean check = false;
		for (String[] i : userGame) {
			if (i[0].equals(account)) { // 找到符合帳號
				latestGame = i[1];
				check = true;
				break;
			}
		}
		if (!check) {
			latestGame = "無";
		}
	}

	public static void addAlliance(String account, String alliance) { // 新增帳號對應聯盟資訊，for一般玩家
		boolean check = false;
		for (String[] i : userAlliance) { // 若有玩家已經有聯盟但想加入(更換)聯盟
			if (i[0].equals(account)) {
				if(i[i.length-1].equals("1")) {
					AllianceOwner.nowMemberAmount(0);
					userAlliance.remove(i);
				}else {
					i[1] = alliance;
					check = true;
				}
				break;
			}
		}
		if (!check) {
			userAlliance.add(new String[] { account, alliance, "3" }); // 3 代表一般玩家，2代表管理者
		}
	}

	public void nowInfo(String account) { // 更新帳號
		latestAccount = account; // 更新目前的帳號
	}

	public static void nowAlliance(String account) { // 確認現在聯盟, for 退出聯盟
		boolean check = false;
		for (String[] i : userAlliance) {
			if (i[0].equals(account)) { // 找到符合帳號
				latestAlliance = i[1]; // 更新目前的聯盟
				check = true;
				break;
			}
		}
		if (!check) {
			latestAlliance = "無";
		}
	}

	public static void nowAlliance(String account, String newAllname) { // 確認現在聯盟, for 退出聯盟
		boolean check = false;
		for (String[] i : userAlliance) {
			if (i[0].equals(account)) { // 找到符合帳號
				latestAlliance = newAllname; // 更新目前的聯盟
				i[1] = newAllname;
				check = true;
				break;
			}
		}
		if (!check) {
			latestAlliance = "無";
		}
	}
}
