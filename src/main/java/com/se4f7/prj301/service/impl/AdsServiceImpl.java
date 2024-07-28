package com.se4f7.prj301.service.impl;

import javax.servlet.http.Part;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.AdsModelRequest;
import com.se4f7.prj301.model.response.AdsModelResponse;
import com.se4f7.prj301.model.response.SettingsModelResponse;
import com.se4f7.prj301.repository.AdsRepository;
import com.se4f7.prj301.service.AdsService;
import com.se4f7.prj301.utils.FileUtil;
import com.se4f7.prj301.utils.StringUtil;

public class AdsServiceImpl implements AdsService {

	private AdsRepository adsRepository = new AdsRepository();

	@Override
	public boolean create(AdsModelRequest request, Part images, String username) {
		// Validate title is exists.
		AdsModelResponse oldAds = adsRepository.getByPosition(request.getPosition());
		if (oldAds != null) {
			throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
		}
		// Saving file from request.
		if (images != null && images.getSubmittedFileName() != null) {
			// Call function save file and return file name.
			String fileName = FileUtil.saveFile(images);
			// Set filename saved to Model.
			request.setImages(fileName);
		}
		// Call repository saving file.
		return adsRepository.create(request, username);
	}

	@Override
	public boolean update(String id, AdsModelRequest request, Part image, String username) {
		// Parse String to Long.
		Long idNumber = StringUtil.parseLong("id", id);
		
		// Get old Posts.
		AdsModelResponse oldAds = adsRepository.getById(idNumber);
		// If Posts is not exists cannot update so will throw Error.
		if (oldAds == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		// Compare is title change.
		if (!request.getPosition().equalsIgnoreCase(oldAds.getPosition())) {
			// Compare new title with other name in database.
			AdsModelResponse otherPosts = adsRepository.getByPosition(request.getPosition());
			if (otherPosts != null) {
				throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
			}
		}
		// Saving file from request.
		if (image != null && image.getSubmittedFileName() != null) {
			// Delete old banner -> saving memory.
			FileUtil.removeFile(oldAds.getImages());
			// Call function save file and return file name.
			String fileName = FileUtil.saveFile(image);
			// Set filename saved to Model.
			request.setImages(fileName);
		} else {
			// If banner not change we don't need replace it.
			// Re-use old name.
			request.setImages(oldAds.getImages());
		}
		// Call repository saving file.
		return adsRepository.update(idNumber, request, username);
	}

	@Override
	public boolean deleteById(String id) {
		Long idNumber = StringUtil.parseLong("id", id);
		AdsModelResponse oldAds = adsRepository.getById(idNumber);
		if (oldAds == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		if (oldAds.getImages() != null) {
			// Delete old banner -> saving memory.
			FileUtil.removeFile(oldAds.getImages());
		}
		return adsRepository.deleteById(idNumber);
	}

	@Override
	public AdsModelResponse getByPosition(String position) {
		
		AdsModelResponse oldAds = adsRepository.getByPosition(position);
		if (oldAds == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		return oldAds;
	}
	public AdsModelResponse getById(String id) {
		Long idNumber = StringUtil.parseLong("id", id);
		AdsModelResponse oldAds = adsRepository.getById(idNumber);
		if (oldAds == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		return oldAds;
	}	
	@Override
	public PaginationModel filter(String page, String size, String name) {
		int pageNumber = StringUtil.parseInt("Page", page);
		int sizeNumber = StringUtil.parseInt("Size", size);
		return adsRepository.filterByName(pageNumber, sizeNumber, name);
	}

}
