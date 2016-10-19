package com.u1.service.impl;

import java.util.List;

import com.u1.model.SearchResult;
import com.u1.model.Slaconfig;
import com.u1.service.SlaService;

public class SlaServiceImpl extends CommonServiceImpl implements SlaService {

	@Override
	public Slaconfig getSla() {
		return (Slaconfig)commonDao.get(Slaconfig.class, 1);
	}

	@Override
	public void updateSla(Slaconfig sla) {
		// TODO Auto-generated method stub
		commonDao.myMerge(sla, 1);
	}

}
