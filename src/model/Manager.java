package model;

public class Manager {
	private int id;
	private String userName;
	private String userPassWord;
	//alt + shift + s
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manager(String userName, String userPassWord) {
		super();
		this.userName = userName;
		this.userPassWord = userPassWord;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassWord() {
		return userPassWord;
	}
	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
	

}
