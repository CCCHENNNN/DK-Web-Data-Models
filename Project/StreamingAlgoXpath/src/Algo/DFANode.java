package Algo;

import java.util.ArrayList;

/**
 * 
 * @author hchen
 * DFANode
 * Record each node's information in the automaton 
 * - state number
 * - is the state the end state
 * - the list of corresponding NFA states
 *
 */

public class DFANode {
	private int state;
	private boolean isEnd;
	private ArrayList<Integer> nfaStates;
	
	public DFANode(int state,boolean isEnd,ArrayList<Integer> nfaStates) {
		this.state = state;
		this.isEnd = isEnd;
		this.nfaStates = nfaStates;	
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public ArrayList<Integer> getNfaStates() {
		return nfaStates;
	}

	public void setNfaStates(ArrayList<Integer> nfaStates) {
		this.nfaStates = nfaStates;
	}


}
