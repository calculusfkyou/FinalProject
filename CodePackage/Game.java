package FinalProject;

public class Game {
	private static long gameRemainDays;
	private static String caseChoose;
	private static String startDay;
	private static String endDay;
	private static String scoreFormula;
	private static String gameName;

	public static long getGameRemainDays() {
		return gameRemainDays;
	}

	public static void setGameRemainDays(long days) {
		gameRemainDays = days;
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

	public static String getScoreFormula() {
		return scoreFormula;
	}

	public static void setScoreFormula(String ScoreFormula) {
		scoreFormula = ScoreFormula;
	}

	public static String getGameName() {
		return gameName;
	}

	public static void setGameName(String GameName) {
		gameName = GameName;
	}
}
