package com.diegovargasg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import com.diegovargasg.model.Contact;

public class ContactDaoImpl implements ContactDao {

	private JdbcTemplate jdbcTemplate;
	
	public ContactDaoImpl(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public int save(Contact contact) {
		String sql = "INSERT INTO contact(name, email, address, phone) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAddress(), contact.getPhone());
	}

	@Override
	public int update(Contact contact) {
		String sql = "UPDATE contact SET name=?, email=?, address=?, phone=? WHERE id=? LIMIT 1";
		return jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAddress(), contact.getPhone(), contact.getId());
	}

	@Override
	public Contact get(Integer id) {
		String sql = "SELECT * FROM contact WHERE id="+id;
		
		ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>() {
			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					return new Contact(id, name, email, address, phone);
				}
				return null;
			}
		};
		
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM contact WHERE id=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Contact> list() {
		String sql = "SELECT * FROM contact";
		
		RowMapper<Contact> rowMapper = new RowMapper<Contact>() {
			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				return new Contact(id, name, email, address, phone);
			}
		};
		
		return jdbcTemplate.query(sql, rowMapper);
	}

}
