package Algo;


/**
 * 
 * @author hchen
 * NFANode
 * Record each node's information in the automaton 
 * - state number
 * - is the state the end state
 * - does the state have the transition to itself
 * - it's edge(transition to the next state)
 * 
 */

public class NFANode {
	private int state;
	private boolean isEnd;
	private boolean isSelf;
	private NEdge nEdge;
	
	public NFANode(int state, boolean isSelf, boolean isEnd) {
		this.state = state;
		this.isSelf = isSelf;
		this.isEnd = isEnd;
		this.nEdge = new NEdge();
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

	public boolean isSelf() {
		return isSelf;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	public NEdge getnEdge() {
		return nEdge;
	}

	public void setnEdge(NEdge nEdge) {
		this.nEdge = nEdge;
	}
	
	
	
}
