package teamtask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 정공명
 * 
 * @date 2019-01-26 bookDelete() 메서드 생성 * => Book클래스 : deleteCode(필드), get/set
 *       생성 , toString deleteCode 생성 => 메서드 작성 내용: myBookList의 도서명과 삭제할 도서명 일치하고
 *       deleteCode가 일치할 경우 myBookList에서 해당 책을 삭제.
 * 
 *       void bookAdd() 메서드 생성 => 메서드 작성 내용: myBookList의 도서명/작가/출판사가 일치 할 경우
 *       myBookList에 Book 생성자를 가져와서 name, writer, publisher, posLibrary,
 *       primaryCode 추가
 * 
 *       List<Book> add = new ArrayList<>() 생성
 * 
 * @date 2019-01-27 void aDelinquentList() 메서드 생성 (연체자 리스트) => getEndDay() 와
 *       현재시간을 비교 했을 때 0보다 클 경우(좌측값이 클 경우 1, 우측값이 클 경우 -1, 같을 경우 0) book에 연체자
 *       리스트 출력
 **/

@SuppressWarnings("serial")
public class Library extends User implements Serializable {
	List<String> aDelinquent = new ArrayList<>(); // 연체자 리스트
	List<Member> librarian = new ArrayList<>();

	public Library(String name, String id, String password) {
		super();
		setName(name);
		setId(id);
		setPassword(password);
	}

	void deleteBook(LibraryGroupService ls, String primaryCode) { // 책 파기
		for (Book book : myBookList) {
			if (book.getPrimaryCode().equals(primaryCode)) {
				System.out.println(book.getName() + "이(가) 파기 되었습니다.");
				ls.books.remove(book);
				myBookList.remove(book);
				ls.books.forEach(System.out::println);
				return;
			}
		}
		System.out.println("잘못된 코드입니다.");
	}

	Book bookAdd(Book addBook) { // 신권 추가
		boolean coi = true;
		addBook.setName(addBook.getName().trim());
		addBook.setWriter(addBook.getWriter().trim());
		addBook.setPublisher(addBook.getPublisher().trim());
		if (addBook.getName().equals(null) || addBook.getWriter().equals(null) || addBook.getPublisher().equals(null)
				|| addBook.getName().isEmpty() || addBook.getWriter().isEmpty() || addBook.getPublisher().isEmpty()) {
			System.out.println("책제목, 작가, 출판사는 필수값입니다.");
			return null;
		}
		for (Book book : myBookList) {
			if (book.getName().equals(addBook.getName()) && book.getWriter().equals(addBook.getWriter())
					&& book.getPublisher().equals(addBook.getPublisher())) {
				System.out.println("이미 도서관에 존재 하는 책입니다. 추가 하시겠습니까?(y/n)");
				String yesNo = Tools.useScanner();
				if (yesNo.equals("n")) {
					coi = false;
					break;
				} else {
					break;
				}
			}
		}
		if (coi) {
			for (Book book : reqBookList) {
				if (book.getName().equals(addBook.getName()) && book.getWriter().equals(addBook.getWriter())
						&& book.getPublisher().equals(addBook.getPublisher())) {
					reqBookList.remove(addBook);
					System.out.println("신청목록에서 " + addBook.getName() + "을 제거했습니다.");
					break;
				}
			}
			DecimalFormat df = new DecimalFormat("0000000000");
			int tmp1;
			if (myBookList.size() != 0) {
				String tmpstr = myBookList.get(myBookList.size() - 1).getPrimaryCode();
				tmpstr = tmpstr.substring((tmpstr.indexOf("_") + 1));
				tmp1 = (Integer.parseInt(tmpstr));
			} else {
				tmp1 = 0;
			}
			tmp1 = tmp1 + 1;
			String priCode = getName() + "_" + df.format(tmp1);
			addBook.setPrimaryCode(priCode);
			addBook.setPosLibrary(getName());
			myBookList.add(addBook);
			System.out.println(addBook.getName() + "이 추가 되었습니다");
			return addBook;
		} else {
			System.out.println("책을 추가하지 않았습니다.");
			return null;
		}
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(getName());
		out.writeUTF(getId());
		out.writeUTF(getPassword());
		out.writeObject(myBookList);
		out.writeObject(reqBookList);
		out.writeObject(librarian);
		out.writeObject(aDelinquent);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		setName(in.readUTF());
		setId(in.readUTF());
		setPassword(in.readUTF());
		myBookList = (ArrayList<Book>) in.readObject();
		reqBookList = (ArrayList<Book>) in.readObject();
		librarian = (List<Member>) in.readObject();
		aDelinquent = (List<String>) in.readObject();
	}
}