package ftt;

import ftt.logic.BetLogic;
import ftt.logic.State;

public class Bet {
	private State state;
	
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
		int bet = -1;
		try {
			bet = BetLogic.getBet(state);
		}catch (Exception e) {
			System.out.println("An error occured");
			System.out.println(e.getMessage());
		}
		return bet ; //BetLogic.getBet(state);
	}
}
