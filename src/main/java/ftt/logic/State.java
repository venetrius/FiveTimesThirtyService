package ftt.logic;

import org.apache.commons.lang3.math.NumberUtils;

public class State {
	
	private int[] aiBetHistory;
	private int[] pBetHistory;
	
	public State(String token) {
		// TODO use lambda exp
		String[] vals = token.split("_");
		int len = vals.length;
		if(len % 2 != 1 || NumberUtils.isCreatable(vals[0]) || Integer.parseInt(vals[0]) != len || len > 11) {
			throw new IllegalArgumentException("provided token does not represents a valid state");
		}
		initHistory(vals);
	}

	private void initHistory(String[] vals) {
		int rounds = vals.length / 2;
		aiBetHistory = new int[rounds];
		pBetHistory = new int[rounds];
		for(int i = 1; i < rounds+1; ) {
			try {
				aiBetHistory[i-1] = Integer.parseInt(vals[i]);
			}catch (NumberFormatException e) {
				throw new IllegalArgumentException("One of the value is not a number");
			}
		}
		for(int i = 1+rounds; i < vals.length; ) {
			try {
				pBetHistory[i-1-rounds] = Integer.parseInt(vals[i]);
			}catch (NumberFormatException e) {
				throw new IllegalArgumentException("One of the value is not a number");
			}
		}
		
	}
}
