package ftt.logic;

public class NonPersistentBet {
	private static int[] firstRound =
			new int[] {1, 2, 3, 3, 4, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 10, 11, 12};
	
	protected static int getBet(State state) {
		int bet = -1;
		int avialabe = 30 - state.getAiSum();
		int enemyHas = 30 - state.getpSum();
		switch (state.getRoundNumber()) {
		case 0: 
		case 1:
			bet =  firstRound[(int)((Math.random() * firstRound.length))];
			break;
		case 2:
			if(state.getAiPoints() == 2) {
				bet = getDefensiveBet(3, avialabe, 30 - state.getpSum());
			}
			else if(state.getAiPoints() >= 1) {
				bet = getRandomDistribution(3, avialabe, 30 - state.getpSum());
			}else if(state.getAiPoints() == 0){
				bet = getAgressiveBet(3, avialabe, enemyHas);
			}else {
				// TODO implements something here....
				bet = getAgressiveBet(3, avialabe, enemyHas);
			}
			break;
		case 3:
			if(avialabe > (30 - state.getpSum())*2) {
				bet = (30 - state.getAiSum()) / 2;
			}else {
				int pMax = getMax(2, enemyHas);
				bet = Math.random() > 0.5 ? 1 : avialabe -1;
				bet = Math.min(pMax+1, bet);
				if(bet < pMax+1 && avialabe > pMax) {
					bet = avialabe - pMax - 1;
				}
			}
			break;
		case 4:
			bet = avialabe;
			break;
		default:
			throw new IllegalStateException("Round number " + state.getRoundNumber() + " is not possible");
		}
		return Math.max(1,bet);
	}

	private static int getDefensiveBet(int roundLeft, int avialabe, int against) {
		int bet;
		int max = getMax(roundLeft, avialabe);
		if(Math.random() > 0.1) {
			if(Math.random() >= (1d/3)) {
				bet = max;
			}else {
				bet = 1;
			}
		}else {
			bet =(int) (Math.random() * max);
		}
		return bet;
	}

	private static int getRandomDistribution(int roundsLeft, int toUse, int enemyHas) {
		int bet;
		double proprotion =(toUse -  enemyHas) / 3d;
		if(proprotion < 0.5 && proprotion > -0.5) {
			if(Math.random() < 0.66) {
				bet = (int) ((Math.random() * (toUse - 1) / 2) + (toUse - 1) / 4);
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
	
	private static int getAgressiveBet(int roundLeft, int avialabe, int against) {
		int bet;
		int enemyMax = getMax(roundLeft, against);
		int forEachTurn = equalliDistibuted(roundLeft, avialabe);
		if(forEachTurn >= enemyMax) {
			bet = forEachTurn;
		}else if(twoOutOfThree(avialabe)> enemyMax) {
			bet = twoOutOfThree(avialabe);
		}else {
			bet = 3 + (int) (Math.random() * 10);
		}
		return Math.max(bet, enemyMax);
	}
	
	private static int hasHugePointAdvantage(int roundLeft, int avialabe, int against) {
		int enemyMax = getMax(roundLeft, against);
		int maxForEachTurn = equalliDistibuted(roundLeft, avialabe);
		return enemyMax > maxForEachTurn ? -1 : maxForEachTurn;
	}
	
	private static int getMax(int roundLeft, int pointLeft) {
		return (pointLeft + 1 - roundLeft);
	}
	
	private static int equalliDistibuted(int roundLeft, int pointLeft) {
		return  (pointLeft + 1 - roundLeft) / roundLeft;
	}
	
	private static int twoOutOfThree(int pointLeft) {
		return (pointLeft -1) /2; 
	}
}
