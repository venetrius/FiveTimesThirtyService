package ftt.logic;

public class BetLogic {
	static private Boolean dbAccessibe= null;

	public static int getBet(State state) {
		int bet;
		if(!getDbAccessibe()) {
			bet = NonPersistentBet.getBet(state);
		}else {
			//TODO implement
			bet = 6;
		}
		return bet;
	}
	
	public static Boolean getDbAccessibe() {
		if(dbAccessibe == null) {
			//TODO create db...
			dbAccessibe = false;
		}
		return dbAccessibe;
	}
}
