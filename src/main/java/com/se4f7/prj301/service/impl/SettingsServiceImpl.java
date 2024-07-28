package com.se4f7.prj301.service.impl;

import javax.servlet.http.Part;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.SettingsModelRequest;
import com.se4f7.prj301.model.response.SettingsModelResponse;
import com.se4f7.prj301.repository.SettingsRepository;
import com.se4f7.prj301.service.SettingsService;
import com.se4f7.prj301.utils.FileUtil;
import com.se4f7.prj301.utils.StringUtil;

public class SettingsServiceImpl implements SettingsService {

	private SettingsRepository settingsRepository = new SettingsRepository();

	@Override
	public boolean create(SettingsModelRequest request, Part image, String username) {
		// Validate title is exists.
		SettingsModelResponse oldSettings = settingsRepository.getByType(request.getType());
		if (oldSettings != null) {
			throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
		}
		// Saving file from request.
		if (image != null && image.getSubmittedFileName() != null) {
			// Call function save file and return file name.
			String fileName = FileUtil.saveFile(image);
			// Set filename saved to Model.
			request.setImage(fileName);
		}
		// Call repository saving file.
		return settingsRepository.create(request, username);
	}

	@Override
	public boolean update(String id, SettingsModelRequest request, Part image, String username) {
		// Parse String to Long.
		Long idNumber = StringUtil.parseLong("id", id);
		
		// Get old Posts.
		SettingsModelResponse oldSettings = settingsRepository.getById(idNumber);
		// If Posts is not exists cannot update so will throw Error.
		if (oldSettings == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		// Compare is title change.
		if (!request.getType().equalsIgnoreCase(oldSettings.getType())) {
			// Compare new title with other name in database.
			SettingsModelResponse otherPosts = settingsRepository.getByType(request.getType());
			if (otherPosts != null) {
				throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
			}
		}
		// Saving file from request.
		if (image != null && image.getSubmittedFileName() != null) {
			// Delete old banner -> saving memory.
			FileUtil.removeFile(oldSettings.getImage());
			// Call function save file and return file name.
			String fileName = FileUtil.saveFile(image);
			// Set filename saved to Model.
			request.setImage(fileName);
		} else {
			// If banner not change we don't need replace it.
			// Re-use old name.
			request.setImage(oldSettings.getImage());
		}
		// Call repository saving file.
		return settingsRepository.update(idNumber, request, username);
	}

	@Override
	public boolean deleteById(String id) {
		Long idNumber = StringUtil.parseLong("id", id);
		SettingsModelResponse oldSettings = settingsRepository.getById(idNumber);
		if (oldSettings == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		if (oldSettings.getImage() != null) {
			// Delete old banner -> saving memory.
			FileUtil.removeFile(oldSettings.getImage());
		}
		return settingsRepository.deleteById(idNumber);
	}

	@Override
	public SettingsModelResponse getByType(String type) {

		SettingsModelResponse oldSettings = settingsRepository.getByType(type);
		if (oldSettings == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		return oldSettings;
	}
	public SettingsModelResponse getById(String id) {
		Long idNumber = StringUtil.parseLong("id", id);
		SettingsModelResponse oldSettings = settingsRepository.getById(idNumber);
		if (oldSettings == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		return oldSettings;
	}	
	@Override
	public PaginationModel filter(String page, String size, String name) {
		int pageNumber = StringUtil.parseInt("Page", page);
		int sizeNumber = StringUtil.parseInt("Size", size);
		return settingsRepository.filterByName(pageNumber, sizeNumber, name);
	}

}
