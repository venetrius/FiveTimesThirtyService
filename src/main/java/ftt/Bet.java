package ftt;

import ftt.logic.State;

public class Bet {
	private State state;
	private int bet = 6;
	
	public Bet(String token) {
		try {
			this.state = new State(token);
		}catch (Exception e) {
			this.state = null;
		}
	}
	
	public int getBet() {
		if(state == null) {
			return -1;
		}
		return this.bet;
	}
}
