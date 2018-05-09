package com.sainsburys.parser.controller;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sainsburys.parser.DataProcesserApplication;
import com.sainsburys.parser.exception.DataProcessorException;
import com.sainsburys.parser.model.ItemDetails;
import com.sainsburys.parser.model.ItemResults;
import com.sainsburys.parser.service.DataProcessorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DataProcesserApplication.class})
public class DataProcessorControllerTest {
	
	@InjectMocks
	private DataProcessorController dataProcessorController = new DataProcessorController();
	
	@Mock
	@Autowired
	private DataProcessorService dataProcessorService;
	
	private ItemResults items;
	
	private String url;
	
	@Before
	public void setUp() throws Exception 
	{
		MockitoAnnotations.initMocks(this);
		//createSampleInputData();
		prepareRequestAndResponse();

	}
	
	@Test
	public void testDataProcessorForValidResponse() throws DataProcessorException
	{
		BigDecimal taxRate = new BigDecimal(3.2);
		
		Mockito.when(dataProcessorService.getItemResults(url)).thenReturn(items);
		
		ItemResults results = dataProcessorController.getItemResults();
		
		assertTrue(results.getTotal() == 18.05);
	}
	
	private void prepareRequestAndResponse(){

		List<ItemDetails> itemDetails = new ArrayList<>();
		itemDetails.add(new ItemDetails());
		itemDetails.add(new ItemDetails());
		items = new ItemResults(itemDetails, 18.05d);
		
		url = "http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137&searchTerm=&categoryId=185749&listId=&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true";
		
	}

}
