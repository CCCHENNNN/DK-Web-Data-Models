package Algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

/**
 * 
 * @author hchen
 * LazyDFA algorithm
 * - Create a NFA according to the query
 * - Read the input file, create a lazyDFA on the base of NFA
 * - Check and match
 * 
 */

public class LazyDFAAlgo implements AlgoXPath{

	int dfaNum = 0;//node number of the DFA
	int nodeNum = 0;//node number of the input file
	private Map<Integer,NFANode> nfaNodes;//record NFA nodes
	private Map<Integer,DFANode> dfaNodes;//record DFA nodes
	private LinkedList<DFANode> stackDFA;//stack for dealing with DFA nodes when read input file
	private ArrayList<Integer> queryResult;//record the match node'number
	
	@Override
	public void getQuery(String query) {
		nfaNodes = NFAFactory.getNFApath(query);//get a HashMap of NFA nodes
		ArrayList<Integer> firstDFAnfaStates = eClosure(0);
		DFANode firstDFANode = new DFANode(0,false,firstDFAnfaStates);//start state of DFA
		stackDFA = new LinkedList<DFANode>();//initialize
		dfaNodes = new HashMap<Integer,DFANode>();//initialize
		stackDFA.push(firstDFANode);
		dfaNodes.put(dfaNum++, firstDFANode);
		queryResult = new ArrayList<Integer>();//initialize
	}

	//Read the input file and deal with it
	@Override
	public void handleXML(String xmlLine) {
		String[] eleInfo = xmlLine.split("\\s");
        int prefix = Integer.parseInt(eleInfo[0]);
        String element = eleInfo[1];
        if(prefix == 0) {
        	startElement(element);//the prefix is 0: begin tag
        }
        else {
        	endElement();//the prefix is 1: end tag
        }
		
	}

	//Output the result
	@Override
	public void getResult() {
		if(queryResult.size() == 0) {
			System.out.println("No result.");
		}
		else {
			System.out.println("The result is: ");
			for(int i=0;i<queryResult.size();i++) {
				System.out.print(queryResult.get(i) + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 
	 * @param nfaState
	 * @return nfaList
	 * The condition "E" means the empty String
	 * The state with "E" condition can jump to the next state directly
	 * So we can consider that the 2 states is in the same node of DFA
	 * This function is for finding the list of target states of a state in the NFA model 
	 * 
	 */
	public ArrayList<Integer> eClosure(int nfaState) {
		ArrayList<Integer> nfaList = new ArrayList<>();
		nfaList.add(nfaState);
		if(nfaNodes.get(nfaState).getnEdge().getCond() != null) {
			if(nfaNodes.get(nfaState).getnEdge().getCond().equals("E")) {
				nfaList.add(nfaNodes.get(nfaState).getnEdge().getNext());
			}
		}
		return nfaList;
	}
	
	/**
	 * 
	 * @param element
	 * DFA model is created when reading the input file
	 * Each node will have a list of corresponding states of the NFA model
	 * If there comes a element, 
	 * the list of states will change to a new list that the condition of node is this element
	 * 
	 */
	public void startElement(String element) {
		ArrayList<Integer> nfaStates = stackDFA.getFirst().getNfaStates();
		ArrayList<Integer> currentNfaStates = new ArrayList<Integer>();
		for(int i=0;i<nfaStates.size();i++) {
			NFANode nfaNode = nfaNodes.get(nfaStates.get(i));
			if(nfaNode.getnEdge().getCond() != null) {
				if(nfaNode.getnEdge().getCond().equals("E")) {
					currentNfaStates.addAll(eClosure(nfaNode.getnEdge().getNext()));
				}
				if(nfaNode.getnEdge().getCond().equals(element)) {
					currentNfaStates.addAll(eClosure(nfaNode.getnEdge().getNext()));
				}
				if(nfaNode.isSelf() == true) {
					currentNfaStates.addAll(eClosure(nfaNode.getState()));
				}
			}
			
		}
		currentNfaStates = new ArrayList<>(new HashSet<>(currentNfaStates));//avoid the repeat of the ArrayList
		DFANode dfaNode = new DFANode(dfaNum++,false,currentNfaStates);
		stackDFA.push(dfaNode);
		match(dfaNode);//Check if there is a end state of the state list
		nodeNum++;
	}

	/**
	 * 
	 * @param dfaNode
	 * If there is a state with isEnd == true
	 * This state will be added to the result list
	 * 
	 */
	public void match(DFANode dfaNode) {
		ArrayList<Integer> nfaStates = dfaNode.getNfaStates();
		for(int i=0;i<nfaStates.size();i++) {
			if(nfaNodes.get(nfaStates.get(i)).isEnd() == true) {
				queryResult.add(nodeNum);
			}
		}
	}
	
	public void endElement() {
		stackDFA.pop();//when end tag, pop
	}

}
