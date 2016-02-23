package com.hdc.service;

import java.util.List;

import com.hdc.entity.RoleAndResource;

public interface IRoleAndResourceService {
	
	public List<RoleAndResource> getResource(Integer roleId) throws Exception;
	
	public void doAdd(RoleAndResource rar) throws Exception;
	
	public void doDelete(RoleAndResource rar) throws Exception;
	
	public Integer doDelByRole(Integer roleId) throws Exception;
	
	public Integer doDelByResource(Integer resourceId) throws Exception;
}
