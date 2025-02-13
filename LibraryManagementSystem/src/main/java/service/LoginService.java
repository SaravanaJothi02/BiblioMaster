package service;

import model.Admin;
import repository.AdminRepository;

public class LoginService {
	private AdminRepository repository;
	public LoginService() {
		repository=new AdminRepository();
	}

	public boolean validateLogin(String username, String password) {
		Admin admin=repository.validateLogin(username,password);
		if(admin!=null && password.equals(admin.getPassword()))
			return true;
		return false;
	}
}
