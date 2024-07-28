package com.se4f7.prj301.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.se4f7.prj301.constants.ErrorMessage;
import com.se4f7.prj301.enums.StatusEnum;
import com.se4f7.prj301.model.PaginationModel;
import com.se4f7.prj301.model.request.SettingsModelRequest;
import com.se4f7.prj301.model.response.SettingsModelResponse;

import com.se4f7.prj301.utils.DBUtil;

public class SettingsRepository {
	private static final String INSERT_SQL = "INSERT INTO web_setting (content, createdBy, updatedBy, type, image) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_SQL = "UPDATE web_setting SET content = ?, updatedBy = ?, type = ?, image = ? WHERE id = ?";
	private static final String GET_BY_TYPE_SQL = "SELECT * FROM web_setting AS w WHERE w.type = ? ";
	private static final String GET_BY_ID_SQL = "SELECT * FROM web_setting WHERE id = ?";
	private static final String DELETE_BY_ID_SQL = "DELETE FROM web_setting  WHERE id= ? ";
	private static final String SEARCH_LIST_SQL = "SELECT * FROM web_setting WHERE type LIKE ? LIMIT ? OFFSET ?";
	private static final String COUNT_BY_NAME_SQL = "SELECT COUNT(id) AS totalRecord FROM web_setting WHERE type LIKE ?";

	public boolean create(SettingsModelRequest request, String username) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
			// Set parameters.
			preparedStatement.setString(1, request.getContent());;
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, request.getType());
			preparedStatement.setString(5, request.getImage());
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public boolean update(Long id, SettingsModelRequest request, String username) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
			// Set parameters.
			preparedStatement.setString(1, request.getContent());
			preparedStatement.setString(2, username);	
			preparedStatement.setString(3, request.getType());
			preparedStatement.setString(4, request.getImage());
			preparedStatement.setLong(5, id);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public SettingsModelResponse getById(Long id) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL)) {
			// Set parameters.
			preparedStatement.setLong(1, id);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst()) {
				return null;
			}
			SettingsModelResponse response = new SettingsModelResponse();
			while (rs.next()) {
				response.setId(rs.getLong("id"));
				response.setContent(rs.getString("content"));
				response.setCreatedDate(rs.getString("createdDate"));
				response.setUpdatedDate(rs.getString("updatedDate"));
				response.setCreatedBy(rs.getString("createdBy"));
				response.setUpdatedBy(rs.getString("updatedBy"));
				response.setStatus(StatusEnum.valueOf(rs.getString("status")));
				response.setType(rs.getString("type"));
				response.setImage(rs.getString("image"));
			}
			return response;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public SettingsModelResponse getByType(String type) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_TYPE_SQL)) {
			// Set parameters.
			preparedStatement.setString(1, type);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.isBeforeFirst()) {
				return null;
			}
			SettingsModelResponse response = new SettingsModelResponse();
			while (rs.next()) {
				response.setId(rs.getLong("id"));
				response.setContent(rs.getString("content"));
				response.setCreatedDate(rs.getString("createdDate"));
				response.setUpdatedDate(rs.getString("updatedDate"));
				response.setCreatedBy(rs.getString("createdBy"));
				response.setUpdatedBy(rs.getString("updatedBy"));
				response.setStatus(StatusEnum.valueOf(rs.getString("status")));
				response.setType(rs.getString("type"));
				response.setImage(rs.getString("image"));
			}
			return response;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public boolean deleteById(Long id) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
			// Set parameters.
			preparedStatement.setLong(1, id);
			// Show SQL query.
			System.out.println(preparedStatement);
			// Execute query.
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}

	public PaginationModel filterByName(int page, int size, String name) {
		// Open connection and set SQL query into PreparedStatement.
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement stmtSelect = connection.prepareStatement(SEARCH_LIST_SQL);
				PreparedStatement stmtCount = connection.prepareStatement(COUNT_BY_NAME_SQL)) {
			// Set parameters.
			stmtSelect.setString(1, name != null ? "%" + name + "%" : "%%");
			stmtSelect.setInt(2, size);
			stmtSelect.setInt(3, page * size);
			// Show SQL query.
			System.out.println(stmtSelect);
			// Execute query.
			// Select records.
			ResultSet rs = stmtSelect.executeQuery();
			List<SettingsModelResponse> results = new ArrayList<SettingsModelResponse>();
			while (rs.next()) {
				SettingsModelResponse response = new SettingsModelResponse();
				response.setId(rs.getLong("id"));
				response.setContent(rs.getString("content"));
				response.setCreatedDate(rs.getString("createdDate"));
				response.setUpdatedDate(rs.getString("updatedDate"));
				response.setCreatedBy(rs.getString("createdBy"));
				response.setUpdatedBy(rs.getString("updatedBy"));
				response.setStatus(StatusEnum.valueOf(rs.getString("status")));
				response.setType(rs.getString("type"));
				response.setImage(rs.getString("image"));
				results.add(response);
			}

			// Count records;
			stmtCount.setString(1, name != null ? "%" + name + "%" : "%%");
			ResultSet rsCount = stmtCount.executeQuery();
			int totalRecord = 0;
			while (rsCount.next()) {
				totalRecord = rsCount.getInt("totalRecord");
			}
			return new PaginationModel(page, size, totalRecord, results);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SQL_ERROR + e.getMessage());
		}
	}
}
