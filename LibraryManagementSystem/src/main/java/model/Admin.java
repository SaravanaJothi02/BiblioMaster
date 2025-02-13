package model;

public class Admin {
	private static int id;
	private int adminId;
	private String username;
	private String mailId;
	private String password;
	public Admin(String username,String password,String mailId) {
		id=id+1;
		adminId=id;
		this.username=username;
		this.mailId=mailId;
		this.password=password;
	}
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		Admin.id = id;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
