package Algo;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author hchen
 * Factory for NFA model
 * Read the query, return a NFA model
 * Ex: "//a//b/c" -> "0--E--1(self)--a--2--E--3(self)--b--4--c--5"
 * 
 */

public class NFAFactory {

	public static Map<Integer,NFANode> getNFApath(String query) {
		Map<Integer,NFANode> NFANodes = new HashMap<Integer,NFANode>();
		String[] queries = query.substring(2).split("//");
		int stateNum = 0;
		NFANode firstState = new NFANode(stateNum++, false, false);//start state
		NFANodes.put(firstState.getState(), firstState);
		
		// "//p1//p2//...//pn"
		// "pi=e1/e2/.../em"
		// Separate the query and deal with each 'p'
		for(int i=0;i<queries.length;i++) {
			String[] subqueries = queries[i].split("/");
			for(int j=0;j<subqueries.length;j++) {
				if(j == 0) {//the start of each 'p', it need to connect to last node
					NFANode last = NFANodes.get(stateNum - 1);
					NFANode current = new NFANode(stateNum++,true,false);
					NFANode next = new NFANode(stateNum++,false,false);
					NEdge lastEdge = new NEdge(current.getState(),"E");
					NEdge currentEdge = new NEdge(next.getState(),subqueries[j]);
					last.setnEdge(lastEdge);
					current.setnEdge(currentEdge);
					NFANodes.put(last.getState(), last);
					NFANodes.put(current.getState(), current);
					NFANodes.put(next.getState(), next);
				}
				else {//normal connection
					NFANode current = NFANodes.get(stateNum - 1);
					NFANode next = new NFANode(stateNum++,false,false);
					NEdge currentEdge = new NEdge(next.getState(),subqueries[j]);
					current.setnEdge(currentEdge);
					NFANodes.put(current.getState(), current);
					NFANodes.put(next.getState(), next);
				}
			}
		}
		NFANode endNode = NFANodes.get(stateNum - 1);
		endNode.setEnd(true);//set the last element of the query the end element
		NFANodes.put(endNode.getState(), endNode);
		
        return NFANodes;
    }
}
