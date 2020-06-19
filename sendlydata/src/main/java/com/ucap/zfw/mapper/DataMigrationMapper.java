package com.ucap.zfw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ucap.zfw.entity.Atach;
import com.ucap.zfw.entity.DataMigration;
import com.ucap.zfw.entity.Manuscript;

@Mapper
public interface DataMigrationMapper {

	public List<DataMigration> queryDataList();
	public List<Manuscript> queryManuscriptList();
	public List<Atach> getAtachById(String id);

}
