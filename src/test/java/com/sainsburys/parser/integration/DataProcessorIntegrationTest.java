package com.sainsburys.parser.integration;

import static org.junit.Assert.assertTrue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sainsburys.parser.model.ItemResults;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class DataProcessorIntegrationTest {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DataProcessorIntegrationTest.class);
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	ItemResults itemResults;
	
	@Test
	public void checkCountOfRecordsResults() throws JSONException{
		
		String url = "http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true";
		
		ResponseEntity<String> responseEntity =
				restTemplate.getForEntity("/items", String.class);
		String jsonrResponseString = responseEntity.getBody();

		LOG.info("########## JSON Response ########## \n"+jsonrResponseString);
		JSONObject json = new JSONObject(jsonrResponseString);
		
		JSONArray itemDetailsArray = json.getJSONArray("results");
		JSONObject firstItem = itemDetailsArray.getJSONObject(0);
		
		assertTrue(((Double)json.get("total")) == 18.05);
		assertTrue(((Double)firstItem.get("unitPrice")) > 0);

	}

}
