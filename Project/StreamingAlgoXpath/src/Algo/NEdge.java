package Algo;

/**
 * 
 * @author hchen
 * Transition information of a NFA node
 * - Condition of transition
 * - Target of the transition
 *
 */

public class NEdge {
	private int next;
	private String cond;
	
	public NEdge() {
		this.next = -1;
	}
	
	public NEdge(int next,String cond) {
		this.next = next;
		this.cond = cond;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}
}
