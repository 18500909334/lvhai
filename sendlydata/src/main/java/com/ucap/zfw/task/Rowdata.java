package com.ucap.zfw.task;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class Rowdata implements RowMapper<Rowdata> {

    private String item;

    private String id;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Rowdata mapRow(ResultSet rs, int rowNum) throws SQLException {
		Rowdata data = new Rowdata();
		data.setId(rs.getString("id"));
		data.setItem(rs.getString("item"));
		return data;
	}
}