package ftt.logic;

public class NonPersistentBet {
	private static int[] firstRound =
			new int[] {1, 2, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 10, 11, 12};
	
	protected static int getBet(State state) {
		int bet = -1;
		int avialabe = 30 - state.getAiSum();
		switch (state.getRoundNumber()) {
		case 0: 
		case 1:
			bet =  firstRound[(int)(Math.random() * firstRound.length)];
			break;
		case 2:
			if(state.getAiPoints() == 1) {
				bet = getRandomDistribution(3, avialabe, 30 - state.getpSum());
			}else {
				// TODO implements something here....
				bet = 1 + (int) Math.random() * (avialabe -2);
			}
			break;
		case 3:
			if(avialabe > (30 - state.getpSum())*2) {
				bet = (30 - state.getAiSum()) / 2;
			}else {
				bet = Math.random() > 0.5 ? 1 : avialabe;
			}
			break;
		case 4:
			bet = avialabe;
			break;
		default:
			throw new IllegalStateException("Round number " + state.getRoundNumber() + " is not possible");
		}
		return bet;
	}

	private static int getRandomDistribution(int roundsLeft, int toUse, int enemyHas) {
		int bet;
		double proprotion =(toUse -  enemyHas) / 3d;
		if(proprotion < 0.5 && proprotion > -0.5) {
			if(Math.random() < 0.66) {
				bet = (int) (Math.random() * (toUse - 1) / 2) + (toUse - 1) / 4;
			}
			else {
				bet = (int)(1 + Math.random() * 2);
			}
		}else if(proprotion > 0.5) {
			bet = 8;
		}else {
			bet = 3;
		}
		return bet;
	}
}
