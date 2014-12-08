package com.kalatag.dao;

import com.kalatag.domain.PersonRole;

public interface PersonRoleDao extends GenericDao<PersonRole>{
	PersonRole findByRoleName(String roleName);
}
