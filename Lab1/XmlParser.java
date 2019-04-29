import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class XmlParser {

  public static void main(String argv[]) {
  	// System.out.println("hello");
    try {

	File fXmlFile = new File("map.osm");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);

	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	// System.out.println("hello");
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("tag");

	System.out.println("----------------------------");

	String s = "";

	String dataTotal = "";

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);

		Element eElement = (Element) nNode;

		if(eElement.getAttribute("k").equals("name")){

			if(!s.equals(eElement.getAttribute("v"))){
				s =  eElement.getAttribute("v");
				System.out.println("Name: " + s);
				try {
            //创建一个URL实例
	            URL url = new URL("https://fr.wikipedia.org/w/api.php?format=xml&action=query&prop=extracts&exintro=&explaintext=&titles="+s);

	            try {
	                //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
	                InputStream is = url.openStream();
	                DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
					Document doc1 = dBuilder1.parse(is);
					doc1.getDocumentElement().normalize();
					// System.out.println("abc");
					// System.out.println("Root element :" + doc1.getDocumentElement().getNodeName());
					String ss = "";
					ss =  doc1.getElementsByTagName("extract").item(0).getTextContent();
					System.out.println(ss);
					// NodeList nList1 = doc1.getElementsByTagName("extract");
					// System.out.println("----------------------------");
					// for (int temp = 0; temp < nList1.getLength(); temp++) {

					// Node nNode1 = nList1.item(temp);

					// Element eElement1 = (Element) nNode1;
					
					// if(eElement1.getAttribute("xml:space").equals("preserve")){
					// 	ss =  eElements.getElementsByTagName("firstname").item(0).getTextContent()
					// 	System.out.println("Name: " + s);

	                // InputStreamReader isr = new InputStreamReader(is,"utf-8");

	                // //为字符输入流添加缓冲
	                // BufferedReader br = new BufferedReader(isr);
	                // String data = br.readLine();//读取数据

	                // while (data!=null){//循环读取数据
	                // 	dataTotal += data;
	                //     // System.out.println(data);//输出数据
	                //     data = br.readLine();
	                // }
	                // br.close();
	                // isr.close();
	                is.close();
	            } catch (IOException e) {
	                // e.printStackTrace();
	                System.out.println();
		        }
		        } catch (MalformedURLException e) {
		            // e.printStackTrace();
		            System.out.println();
		        }
	   //          DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
				// DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
				// Document doc1 = dBuilder1.parse(dataTotal);
				// doc1.getDocumentElement().normalize();
				// System.out.println("abc");
				// System.out.println("Root element :" + doc1.getDocumentElement().getNodeName());

			}
		
		}

			// System.out.println("Staff id : " + eElement.getAttribute("id"));
			// System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
			// System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
			// System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
			// System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
	
	}
    } catch (Exception e) {
	e.printStackTrace();
    }
  }

}