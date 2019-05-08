package teamtask;

import java.util.ArrayList;
import java.util.List;

public abstract class User {

	private String id, name, password;
	List<Book> myBookList = new ArrayList<>();
	List<Book> reqBookList = new ArrayList<>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Book> getMyBookList() {
		return myBookList;
	}
	public void setMyBookList(List<Book> myBookList) {
		this.myBookList = myBookList;
	}
	public List<Book> getReqBookList() {
		return reqBookList;
	}
	public void setReqBookList(List<Book> reqBookList) {
		this.reqBookList = reqBookList;
	}
	
}
