package FinalProject;

import java.util.ArrayList;

public class AllianceOwner extends User {
	private static int memberAmount = 0;
	private static String allianceDescription = ""; // 聯盟簡介
	private static ArrayList<String> memberNames = new ArrayList<>();

	public static void initMember() {
		memberNames.clear();
		if (memberAmount == 0)
			memberAmount = 50;
		for (int i = 0; i < memberAmount; i++) {
			memberNames.add("聯盟成員" + Integer.toString(i + 1));
		}
	}

	public static String[] getMemberNames() {
		return memberNames.toArray(new String[memberNames.size()]);
	}

	public static void kickMember(String member) {
		for (String i : memberNames) {
			if (i.equals(member)) {
				memberNames.remove(i);
				break;
			}
		}
	}

	public static void nowMemberAmount(int amount) {
		memberAmount = amount;
	}

	public static void setAllianceDescription(String AllianceDescription) {
		allianceDescription = AllianceDescription;
	}

	public static String getAllianceDescription() {
		return allianceDescription;
	}
}
