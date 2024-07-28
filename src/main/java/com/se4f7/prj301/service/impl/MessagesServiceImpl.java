package com.se4f7.prj301.service.impl;



import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.model.PaginationModel;

import com.se4f7.prj301.model.request.MessagesModelRequest;
import com.se4f7.prj301.model.response.MessagesModelResponse;

import com.se4f7.prj301.repository.MessagesRepository;
import com.se4f7.prj301.service.MessagesService;

import com.se4f7.prj301.utils.StringUtil;

public class MessagesServiceImpl implements MessagesService {

	private MessagesRepository messagesRepository = new MessagesRepository();

	@Override
	public boolean create(MessagesModelRequest request, String username) {
		MessagesModelResponse oldMessages = messagesRepository.getByEmail(request.getEmail());
		if (oldMessages != null) {
			throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
		}
		return messagesRepository.create(request, username);
	}

	@Override
	public boolean update(String id, MessagesModelRequest request, String username) {
		Long idNumber = StringUtil.parseLong("Id", id);
		MessagesModelResponse oldMessages = messagesRepository.getById(idNumber);
		if (oldMessages == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		if (!request.getEmail().equalsIgnoreCase(oldMessages.getEmail())) {
			MessagesModelResponse otherMessages = messagesRepository.getByEmail(request.getEmail());
			if (otherMessages != null) {
				throw new RuntimeException(ErrorMessage.NAME_IS_EXISTS);
			}
		}
		return messagesRepository.update(idNumber, request, username);
	}
	@Override
	public boolean deleteById(String id) {
		Long idNumber = StringUtil.parseLong("Id", id);
		MessagesModelResponse oldMessages = messagesRepository.getById(idNumber);
		if (oldMessages == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		return messagesRepository.deleteById(idNumber);
	}
	public MessagesModelResponse getByEmail(String email) {

		MessagesModelResponse oldSettings = messagesRepository.getByEmail(email);
		if (oldSettings == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		return oldSettings;
	}
	@Override
	public MessagesModelResponse getById(String id) {
		Long idNumber = StringUtil.parseLong("Id", id);
		MessagesModelResponse oldMessages = messagesRepository.getById(idNumber);
		if (oldMessages == null) {
			throw new RuntimeException(ErrorMessage.RECORD_NOT_FOUND);
		}
		return oldMessages;
	}

	@Override
	public PaginationModel filter(String page, String size, String name) {
		int pageNumber = StringUtil.parseInt("Page", page);
		int sizeNumber = StringUtil.parseInt("Size", size);
		return messagesRepository.filterByName(pageNumber, sizeNumber, name);
	}

}
