package com.sainsburys.parser.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sainsburys.parser.exception.DataProcessorException;
import com.sainsburys.parser.model.ItemDetails;
import com.sainsburys.parser.model.ItemResults;

/**
 * The Class DataProcessorServiceImpl
 */
@Service
public class DataProcessorServiceImpl implements DataProcessorService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DataProcessorServiceImpl.class);
	
	/** The list of all items in the input url with all details */
	List<ItemDetails> results;

	/** The total of all the prices of the items */
	double total;

	
	/**
	 * Processes the input url and return the results
	 *
	 * @param the input url to be parsed
	 * @return an object of itemResults
	 */
	public ItemResults getItemResults(String url) throws DataProcessorException {

		ItemResults itemResults = null;
		try{
			
			//get all item urls from the mail url
			List<String> listOfUrls = getAllProductsLinks(url);

			//Check if there are any items in the input url or not
			if (null != listOfUrls && listOfUrls.size() > 0) {
				results = new ArrayList<>();
				
				//Iterate over each item url and create the list to be returned
				for (String link : listOfUrls) {

					createItemList(link);

				}
				itemResults = new ItemResults(results,new BigDecimal(new Double(total).toString()).setScale(2,RoundingMode.HALF_UP).doubleValue() );
			}
			else{
				LOG.debug("No products or urls found in the input");
				return itemResults;
			}
		}
		catch(IOException e){
			LOG.error(e.getMessage());
			throw new DataProcessorException(e.getMessage());
		}
		
		return itemResults;
	}

	/**
	 * Processes the input url and returns all the possible item urls present
	 *
	 * @param the input url to be parsed
	 * @return a list of item urls
	 */
	private List<String> getAllProductsLinks(String url) throws IOException {

		if(null == url || url.length()<1){
			LOG.debug("Input url is emtpy");
			return null;
		}
		List<String> listOfurls = new ArrayList<>();
		Document document = Jsoup.connect(url).get();
		
		//this is the div class in the page for each item or product
		Elements links = document.select("div.productInfo");
		
		//iterate over each product and fetch its link
		for (Element link : links) {
			listOfurls.add(link.select("a").attr("href"));
		}
		return listOfurls;
	}

	/**
	 * Processes an item url and creates the list of items 
	 *
	 * @param the item url to be parsed
	 */
	private void createItemList(String url) throws IOException {

		ItemDetails item = new ItemDetails();
		
		//gets the document from the url
		Document doc = Jsoup.connect(url).get();

		//Calling the private method gettitle to fetch the title of the item
		item.setTitle(getTitle(doc));
		
		//Calling the private method getUnitPrice to fetch the unit price of the item
		item.setUnitPrice(getUnitPrice(doc));
		
		//Calling the private method getDescription to fetch the description of the item
		item.setDescription(getDescription(doc));
		// item.setSize(Jsoup.connect(url).request().maxBodySize()+"kb");
		
		//Calling the private method getSize to fetch the size of the item
		item.setSize(getSize(url) + "kb");
		total = total + item.getUnitPrice();
		
		//Adding each item to the list to be returned
		results.add(item);
	}

	/**
	 * Processes the document for an item and gets the title
	 *
	 * @param the document for the item to be parsed
	 * @return the title 
	 */
	private String getTitle(Document doc) {
		
		//get the div with the class name productTitleDescriptionContainer, this also contains the promotion details if present
		String completeTitle = doc.getElementsByClass("productTitleDescriptionContainer").get(0).text();
		
		//get the div with the class name promotion
		Elements elements = doc.getElementsByClass("promotion");
		if (null != elements && elements.size() > 0) {
			
			//removing the promotion details from the title
			return completeTitle.replaceAll(elements.get(0).text(), "");
		}
		return completeTitle;

	}

	/**
	 * Processes the document for an item and gets the unit price
	 *
	 * @param the document for the item to be parsed
	 * @return the unit price 
	 */
	private double getUnitPrice(Document doc) {
		
		//get the div with the class name pricePerUnit
		Elements body = doc.getElementsByClass("pricePerUnit");
		
		//the element text has the unit price per weight, so removing the weight
		String[] completePPU = doc.getElementsByClass("pricePerUnit").get(0).text().split("/");
		
		//Remove the currency letter from the unit price
		return Double.parseDouble(completePPU[0].replaceAll("£", ""));
	}

	/**
	 * Processes the document for an item and gets the description
	 *
	 * @param the document for the item to be parsed
	 * @return the description for the item
	 */
	private String getDescription(Document doc) {
		
		//get the div with the class name productText
		return doc.getElementsByClass("productText").get(0).text();
	}

	/**
	 * Processes the document for an item and gets size
	 *
	 * @param the document for the item to be parsed
	 * @return the size of the document
	 */
	private long getSize(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//Unfortunately this method is always returning -1 which is not the expected result
		return conn.getContentLengthLong();
	}

}
