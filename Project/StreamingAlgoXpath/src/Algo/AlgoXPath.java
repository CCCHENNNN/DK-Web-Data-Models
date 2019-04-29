package Algo;

/**
 * 
 * @author hchen
 * Interface for Streaming algo and lazyDFA algo
 *
 */

public interface AlgoXPath {
   
	void getQuery(String query);

    void handleXML(String xmlLine);

    void getResult();
}