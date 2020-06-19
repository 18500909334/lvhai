package com.ucap.zfw.service.impl;

import java.util.List;






import com.ucap.zfw.entity.Atach;
import com.ucap.zfw.entity.DataMigration;
import com.ucap.zfw.entity.Manuscript;
import com.ucap.zfw.mapper.DataMigrationMapper;
import com.ucap.zfw.service.DataMigrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DataMigrationServiceImpl implements DataMigrationService{
  
	@Autowired
	private DataMigrationMapper dataMigrationMapper;
	
	public List<DataMigration> queryDataList() {
		return dataMigrationMapper.queryDataList();
	}
	public List<Atach> getAtachById(String id){
		return dataMigrationMapper.getAtachById( id);
	}
	public List<Manuscript> queryManuscriptList(){
		return dataMigrationMapper.queryManuscriptList();
	}
}
