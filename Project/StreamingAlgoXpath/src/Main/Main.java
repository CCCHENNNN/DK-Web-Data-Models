package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Algo.AlgoXPath;
import Algo.StreamingAlgo;
import Algo.LazyDFAAlgo;

/**
 * 
 * @author hchen
 * Main()
 * - Implementation of algorithm
 * - Evaluation of execution time
 * - Evaluation of memory space
 * 
 */

class Main {
    public static void main(String[] args) throws IOException {
    	//Experimental evaluation
    	//Execution time
        long startTime = System.nanoTime();//start time

    	String filePath = null;
    	String query = null;	
    	AlgoXPath algo;
    	
    	//The input need have the format: arg0 = file path, arg1 = query
    	//If not, there is an exception
    	try {
    		filePath = args[0];
            query = args[1];
    	} catch(ArrayIndexOutOfBoundsException e) {
    		System.out.println("Invalid input");
        	System.exit(0);
    	}
    	
    	//Regular expression of the query
    	String regEx = "([/]{1,2}[a-z]){1,}";// ex://a/a/b
    	Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(query);
        if(matcher.matches() == false) {
        	System.out.println("Invalid format of query");
        	System.exit(0);
        }
        
        //Verify the complexity of the query: simple or complex
        //Simple query -> StreamingAlgo
        //Complex query -> lazyDFAAlgo
        if(!query.substring(2).contains("//")) {
        	algo = new StreamingAlgo();
        }
        else {
        	algo = new LazyDFAAlgo();
        }

        algo.getQuery(query);//analyze the query
        
        //Read the file of input path line by line
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String xmlLine;
            while ((xmlLine = br.readLine()) != null) {
                algo.handleXML(xmlLine);
            }
        } catch(FileNotFoundException e) {
        	System.out.println("Invalid input");
        	System.exit(0);
        }
        
        //Output the result
        algo.getResult();
        

        // Measure execution time
        long stopTime = System.nanoTime();
        System.out.println("Execution time(s): " + (stopTime - startTime) / 1000000000.0);
        
        // Measure memory usage
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long usedBytes = (runtime.totalMemory() - runtime.freeMemory());
        System.out.println("Memory usage(bytes): " + usedBytes);
        
    }
}