package com.se4f7.prj301.service;

import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.MessagesModelRequest;
import com.se4f7.prj301.model.response.MessagesModelResponse;

public interface MessagesService {

	
	public boolean create(MessagesModelRequest request, String username);

	public boolean update(String id, MessagesModelRequest request, String username);
	
	public boolean deleteById(String id);
	
	public MessagesModelResponse getByEmail(String email);
	public MessagesModelResponse getById(String id);

	public PaginationModel filter(String page, String size, String name);

}
