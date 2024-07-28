package com.se4f7.prj301.service;


import javax.servlet.http.Part;

import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.SettingsModelRequest;
import com.se4f7.prj301.model.response.PostsModelResponse;
import com.se4f7.prj301.model.response.SettingsModelResponse;

public interface SettingsService {
	public boolean create(SettingsModelRequest request, Part image, String username);

	public boolean update(String id, SettingsModelRequest request, Part image, String username);

	public boolean deleteById(String id);

	public SettingsModelResponse getByType(String type);
	public SettingsModelResponse getById(String id);
	public PaginationModel filter(String page, String size, String name);
		
}
