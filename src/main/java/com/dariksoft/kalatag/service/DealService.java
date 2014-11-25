package com.dariksoft.kalatag.service;

import com.dariksoft.kalatag.domain.Deal;

public interface DealService extends CRUDService<Deal> {
	
	int getSold();

}
