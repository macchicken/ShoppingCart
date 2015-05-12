package flickr.rest;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import commons.Constants;


/**
 * This showcase the request for flickr panda service using REST style
 * @author zhouy
 *
 */
public class PhotoQuery {
	
	public void call(){		
		HttpURLConnection urlConnection=null;
		try{
			String tags="tiger";
			String callUrlStr = Constants.REST_ENDPOINT+"?method="+Constants.METHOD+
			"&format=rest"+"&per_page="+Constants.DEFAULT_NUMBER+"&api_key="+Constants.API_KEY+"&extras=tags,url_sq&tags="+tags;
			System.out.println(callUrlStr);
			URL callUrl = new URL(callUrlStr);			
			urlConnection = (HttpURLConnection)callUrl.openConnection();
			InputStream urlStream = urlConnection.getInputStream();
		
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document response = db.parse(urlStream);
			//print out all titles,tags and small pic url
			System.out.println("The titles returned are: ");
			NodeList nl = response.getElementsByTagName("photo");
			NamedNodeMap temp;
			String[] tagsl=null;
			for (int i = 0; i < nl.getLength(); i ++){
				temp=nl.item(i).getAttributes();
				System.out.println(temp.getNamedItem("title").getTextContent());
				tagsl=temp.getNamedItem("tags").getTextContent().split(" ");
				System.out.println(temp.getNamedItem("tags").getTextContent());
				System.out.println(tags==null?0:tagsl.length);
				System.out.println(temp.getNamedItem("url_sq").getTextContent());
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally{
			if (urlConnection!=null){
				urlConnection.disconnect();
			}
		}
	}
	
	
	public static void main(String argv[]){	

		System.setProperty("http.proxyHost", "www-cache.usyd.edu.au");
		System.setProperty("http.proxyPort","8080");
		
		PhotoQuery pq = new PhotoQuery();
		pq.call();
	}
}
