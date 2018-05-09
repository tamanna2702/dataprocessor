package com.sainsburys.parser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sainsburys.parser.exception.DataProcessorException;
import com.sainsburys.parser.model.ItemResults;
import com.sainsburys.parser.service.DataProcessorService;

/**
 * The Class DataProcessorController which is the entry point of the application.
 */
@RestController
public class DataProcessorController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DataProcessorController.class);
	
	/** The data processor service. */
	@Autowired
	private DataProcessorService dataProcessorService;
	
	/** The input url to be parsed for data */
	private String url = "http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true";
	
	
	/**
	 * Gets the item results in json format.
	 *
	 * returns null in case of an exception
	 */
	@RequestMapping(value = "/items")
	public ItemResults getItemResults(){
		
		ItemResults itemResults;
		try{
			//Calling the service method to get the results
			itemResults = dataProcessorService.getItemResults(url);
			if(null == itemResults ){
				LOG.debug("Null data returned from service");
			}
		}
		catch(DataProcessorException e){
			LOG.error(e.getMessage());
			return null;
		}
		catch(Exception e){
			LOG.error(e.getMessage());
			return null;
		}
		return itemResults;
	}

}
