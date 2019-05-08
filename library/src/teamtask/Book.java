/**
 * 
 * @author 박인영
 * @date 2019-01-18
 * 구별코드, 대여일, 반납일 필드 추가함.
 * 
 * @author 정공명
 * @date 2019-01-26
 * deleteCode(필드), get/set 추가 , toString deleteCode 추가
 * 
 */
package teamtask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {
	private String name; // 도서명
	private String writer; // 작가
	private String publisher; // 출판사
	private String posLibrary; // 구비 도서관 (possessingLibrary)
	private boolean state = true; // 대여가능 불가능
	private String primaryCode; // 구별코드
	private String startDay="";
	private String endDay="";
	private String borrowUser="";
	
	public Book(String name, String writer, String publisher, String posLibrary, String primaryCode) {
		this.name = name;
		this.writer = writer;
		this.publisher = publisher;
		this.posLibrary = posLibrary;
		this.primaryCode = primaryCode;
	}
	
	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getPrimaryCode() {
		return primaryCode;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPosLibrary() {
		return posLibrary;
	}

	public void setPosLibrary(String posLibrary) {
		this.posLibrary = posLibrary;
	}

	public String getBorrowUser() {
		return borrowUser;
	}

	public void setBorrowUser(String borrowUser) {
		this.borrowUser = borrowUser;
	}

	public void setPrimaryCode(String primaryCode) {
		this.primaryCode = primaryCode;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		if(state) {
			return "도서명 : " + name + ", 작가 : " + writer + ", 출판사 : " + publisher + ", 구비 도서관 : " + posLibrary
				+ ", 대여 가능 상태 : " + state + ", 책 코드 : " + primaryCode + "]";
		}else {
			return "도서명 : " + name + ", 작가 : " + writer + ", 출판사 : " + publisher + ", 구비 도서관 : " + posLibrary
					+ ", 대여 가능 상태 : " + state + ", 책 코드 : " + primaryCode + "]" + ", 대여일 : " + startDay + ", 반납일 : " + endDay
					+ ", 빌린 사람 : " + borrowUser + "]";
		}
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(name);
		out.writeUTF(writer);
		out.writeUTF(publisher);
		out.writeUTF(posLibrary);
		out.writeBoolean(state);
		out.writeUTF(primaryCode);
		out.writeUTF(startDay);
		out.writeUTF(endDay);
		out.writeUTF(borrowUser);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		name=in.readUTF();
		writer=in.readUTF();
		publisher=in.readUTF();
		posLibrary=in.readUTF();
		state=in.readBoolean();
		primaryCode=in.readUTF();
		startDay=in.readUTF();
		endDay=in.readUTF();
		borrowUser=in.readUTF();
	}
}
