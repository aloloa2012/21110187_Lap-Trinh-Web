package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface IUserService {
	UserModel Login(String username, String password);
	
	UserModel FindByUserName(String username);
}
