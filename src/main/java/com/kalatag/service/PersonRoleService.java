package com.kalatag.service;

import com.kalatag.domain.PersonRole;

public interface PersonRoleService extends CRUDService<PersonRole> {
	PersonRole findByRoleName(String roleName);
}
