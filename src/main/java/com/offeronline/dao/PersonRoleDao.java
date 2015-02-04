package com.offeronline.dao;

import com.offeronline.domain.PersonRole;

public interface PersonRoleDao extends GenericDao<PersonRole>{
	PersonRole findByRoleName(String roleName);
}
