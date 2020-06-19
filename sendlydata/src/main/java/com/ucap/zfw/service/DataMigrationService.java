package com.ucap.zfw.service;

import java.util.List;

import com.ucap.zfw.entity.Atach;
import com.ucap.zfw.entity.DataMigration;
import com.ucap.zfw.entity.Manuscript;

public interface DataMigrationService {
	public List<DataMigration> queryDataList();
	public List<Atach> getAtachById(String id);
	public List<Manuscript> queryManuscriptList();
}
