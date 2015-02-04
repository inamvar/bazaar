package com.offeronline.service;

import com.offeronline.domain.PersonRole;

public interface PersonRoleService extends CRUDService<PersonRole> {
	PersonRole findByRoleName(String roleName);
}
