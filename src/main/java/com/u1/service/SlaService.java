package com.u1.service;

import com.u1.model.Slaconfig;

public interface SlaService extends CommonService {
	public Slaconfig getSla();
	public void updateSla(Slaconfig sla);
}
