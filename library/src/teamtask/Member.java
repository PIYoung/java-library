//package teamProject20190117;
package teamtask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 정공명 
 * @since 2019-01-18
 *  List<Request> 추가
 *  -----------------------------
 *  List<Request> -> List<book> request 변경
 *
 */

@SuppressWarnings("serial")
public class Member extends User implements Serializable{ 
	List<Book> request = new ArrayList<>();   

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String name, String id, String password) {
		super();
		setName(name);
		setId(id);
		setPassword(password);

	}

	@Override
	public String toString() {
		return "Member [id=" + getId() + ", name=" + getName() + "]";
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
			out.writeUTF(getName());
			out.writeUTF(getId());
			out.writeUTF(getPassword());
			out.writeObject(myBookList);
			out.writeObject(reqBookList);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		setName(in.readUTF());
		setId(in.readUTF());
		setPassword(in.readUTF());
		myBookList = (ArrayList<Book>) in.readObject();
		reqBookList = (ArrayList<Book>) in.readObject();
	}
}