package com.dariksoft.kalatag.service;

import com.dariksoft.kalatag.domain.PersonRole;

public interface PersonRoleService extends CRUDService<PersonRole> {
	PersonRole findByRoleName(String roleName);
}
