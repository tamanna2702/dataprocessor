package com.sainsburys.parser.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sainsburys.parser.DataProcesserApplication;
import com.sainsburys.parser.exception.DataProcessorException;
import com.sainsburys.parser.model.ItemResults;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataProcesserApplication.class)
public class DataProcessorServiceTest {

	String url ;
	
	private DataProcessorService dataProcessorService = new DataProcessorServiceImpl();
	
	@Before
	public void setUp() throws Exception 
	{
	    createSampleInputData();
	}
	
	@Test
	public void checkItemResultsTotal() throws DataProcessorException{
		ItemResults items = dataProcessorService.getItemResults(url);
		
	    assertTrue(items.getTotal() >0);
	}
	
	@Test
	public void checkItemResultsList() throws DataProcessorException{
		ItemResults items = dataProcessorService.getItemResults(url);
		
	    assertFalse(items.getResults() == null);
	}
	
	@Test
	public void checkItemResultsListSize() throws DataProcessorException{
		ItemResults items = dataProcessorService.getItemResults(url);
		
	    assertTrue(items.getResults().size() > 0);
	}
	
	@Test
	public void checkItemResultsForEmptyUrl() throws DataProcessorException{
		ItemResults items = dataProcessorService.getItemResults(null);
		
	    assertEquals(items,  null);
	}
	
	private void createSampleInputData(){
		
		url = "http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true";
		
	}
}
