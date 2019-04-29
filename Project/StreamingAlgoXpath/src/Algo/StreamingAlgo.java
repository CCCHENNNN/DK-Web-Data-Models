package Algo;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 
 * @author hchen
 * Algo1: query for the form //e1/e2/.../en
 * Read the file in the stack, 0 -> push  1-> pop
 * If the list of elements of query matches the top elements in the stack
 * Mark it as the result
 * 
 */

public class StreamingAlgo implements AlgoXPath{

	private String[] queryEles;
	private LinkedList<String> stack;
	private int currentNum;
	private ArrayList<Integer> queryResult;
	
	
	public StreamingAlgo() {
		currentNum = -1; //the number of the node, begin with 0
		stack = new LinkedList<String>(); //stack for traversing the file(XML)
		queryResult = new ArrayList<>(); //record the result
	}
	
	// Deal with the query: //a/b/c -> [a,b,c]
	@Override
	public void getQuery(String query) {
		queryEles = query.substring(2).split("/");
	}

	@Override
	public void handleXML(String xmlLine) {
		String[] eleInfo = xmlLine.split("\\s");
        int prefix = Integer.parseInt(eleInfo[0]);
        String element = eleInfo[1];
        if(prefix == 0) {//push in the stack
        	currentNum++;
        	stack.push(element);
        	if(stack.size() >= queryEles.length) {
        		int match = 1;
        		// According to the elements of the query, we check the same number of element in the top 
        		// of the stack. If they match, we record the current top element in the stack 
        		for(int i=0;i<queryEles.length;i++) {
        			if(!queryEles[queryEles.length-i-1].equals(stack.get(i))) {
        				match = 0;
        			}
        		}
        		if(match == 1) {
        			queryResult.add(currentNum);
        		}
        	}
        }
        if(prefix == 1) {
        	stack.pop();//pop from the stack
        }
	}

	//Output the match element's numbers
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

}
