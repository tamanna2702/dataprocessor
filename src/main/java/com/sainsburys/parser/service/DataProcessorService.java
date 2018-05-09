package com.sainsburys.parser.service;

import com.sainsburys.parser.exception.DataProcessorException;
import com.sainsburys.parser.model.ItemResults;

public interface DataProcessorService {

	public ItemResults getItemResults(String url) throws DataProcessorException;
}
