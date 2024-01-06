package FinalProject;

import java.util.ArrayList;

public class Advertiser {
	private String account;
	private String passWord;
	static ArrayList<String[]> advertiserInformation = new ArrayList<>(); // {帳號, 密碼}，用於儲存帳密資訊
	private static String latestAccount; // 目前帳號
	private static long adRemainDays;
	private static String caseChoose;
	private static String startDay;
	private static String endDay;

	public Advertiser() { // 預設
		Advertiser.advertiserInformation.add(new String[] { "12345", "12345" }); // {帳號, 密碼}
		Advertiser.advertiserInformation.add(new String[] { "abcde", "xyz123" });
		Advertiser.advertiserInformation.add(new String[] { "1", "1" });
	}

	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return passWord;
	}

	public String getLatestAccount() {
		return latestAccount;
	}

	public static long getAdRemainDays() {
		return adRemainDays;
	}

	public static void setAdRemainDays(long days) {
		adRemainDays = days;
	}

	public static String getCaseChoose() {
		return caseChoose;
	}
	
	public static void setCaseChoose(String CaseChoose) {
		caseChoose = CaseChoose; 
	}
	
	public static String getStartDay() {
		return startDay;
	}
	
	public static void setStartDay(String StartDay) {
		startDay = StartDay;
	}

	public static String getEndDay() {
		return endDay;
	}
	
	public static void setEndDay(String EndDay) {
		endDay = EndDay;
	}
	
	public static ArrayList<String[]> getAdvertiserInformation() {
		return advertiserInformation;
	}

	public static void nowInfo(String account) { // 更新帳號
		latestAccount = account; // 更新目前的帳號
	}
}
