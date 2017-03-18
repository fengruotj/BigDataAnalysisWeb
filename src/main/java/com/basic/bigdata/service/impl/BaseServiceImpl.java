package com.basic.bigdata.service.impl;
import com.basic.bigdata.dao.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("baseService")
public class BaseServiceImpl {

	@Resource
	protected AdministratorDAO tAdministratorDAO;

	@Resource
	protected TTuplecountDAO tTuplecountDAO;

	@Resource
	protected TTuplelatencyDAO tTuplelatencyDAO;

	@Resource
	protected TSpouttuplecountDAO tSpouttuplecountDAO;

	@Resource
	protected TWordcountDAO twordcountDAO;

}
