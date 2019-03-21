package ftt.logic;

import org.apache.commons.lang3.math.NumberUtils;

public final class State {
	
	private int[] aiBetHistory;
	private int[] pBetHistory;
	private final int roundNumber;
	private final int aiSum;
	private final int pSum;
	private final int aiPoints;
	
	public State(String token) {
		// TODO use lambda exp
		String[] vals = token.split("_");
		System.out.println(vals.length);
		int len = vals.length;
		if(len % 2 != 1 ||  len > 11) {
			throw new IllegalArgumentException("provided token does not represents a valid state");
		}
		initHistory(vals);
		roundNumber = aiBetHistory.length;
		int[] partRes = calcPointsAndSums();
		aiSum = partRes[0];
		pSum = partRes[1];
		aiPoints = partRes[2];
	}

	private int[] calcPointsAndSums() { 
		int aiSum = 0;
		int pSum = 0;
		int aiPoint = 0;
		for(int i = 0; i < roundNumber; i++) {
			aiSum += aiBetHistory[i];
			pSum += pBetHistory[i];
			aiPoint += Math.max(((Integer) aiBetHistory[i]).compareTo(pBetHistory[i]),0);
		}
		
		return new int[] {aiSum, pSum, aiPoint, roundNumber - aiPoint};
	}

	private void initHistory(String[] vals) {
		int rounds = vals.length / 2;
		aiBetHistory = new int[rounds];
		pBetHistory = new int[rounds];
		for(int i = 1; i < rounds+1; i++) {
			try {
				aiBetHistory[i-1] = Integer.parseInt(vals[i]);
			}catch (NumberFormatException e) {
				throw new IllegalArgumentException("One of the value is not a number");
			}
		}
		for(int i = 1+rounds; i < vals.length; i++) {
			try {
				pBetHistory[i-1-rounds] = Integer.parseInt(vals[i]);
			}catch (NumberFormatException e) {
				throw new IllegalArgumentException("One of the value is not a number");
			}
		}
	}
	
	public int[] getAiBetHistory() {
		return aiBetHistory;
	}

	public int[] getpBetHistory() {
		return pBetHistory;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public int getAiSum() {
		return aiSum;
	}

	public int getpSum() {
		return pSum;
	}

	public int getAiPoints() {
		return aiPoints;
	}
	
	public int getPlayerPoints() {
		return roundNumber - aiPoints;
	}
	
}
