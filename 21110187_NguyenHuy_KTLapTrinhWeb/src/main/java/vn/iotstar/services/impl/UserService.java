package vn.iotstar.services.impl;

import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;

public class UserService implements IUserService {
	// Lay toan bo ham trong tang Dao cua user
	IUserDao userDao = new UserDaoImpl();

	@Override
	public UserModel Login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel FindByUserName(String username) {

		return userDao.findByUserName(username);
	}
	
	public static void main(String[] args) {
		
		IUserService userService = new UserService();
		System.out.println(userService.FindByUserName("huydeptrai"));
		
	}

}
