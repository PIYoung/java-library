
/**

 * @author 염윤호
 * @since 2019-01-31
 * 
 * 회원가입
 * 회원가입후 출력하여 txt파일에 저장되게 함
 * 
 * 도서신청
 * 신청 완료후 출력하겨 txt파일에 저장되게 함 
 * 중간에 null값 가능하게 하는것 때문에 NullPointerException발생 하여서 Null을 ""으로 변경함.
 * 변경후 정상적으로 들어감.
 * 
 */

package teamtask;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LibraryGroupService {
	List<Member> members = new ArrayList<>();
	List<Library> libraries = new ArrayList<>();
	List<Book> books = new ArrayList<>();
	List<Book> rbook = new ArrayList<>();
	Member nowUser = null;
	Library nowLib = null;

	Regex regex = new Regex();

	String memfile = "memberlist.txt";
	String libfile = "liblist.txt";
	String rbookfile = "rbooklist.txt";
	FileOutputStream fos;
	BufferedOutputStream bos;
	ObjectOutputStream oos;
	
	void booksSetting() {
		books = new ArrayList<Book>();
		for (Library lib : libraries) {
			books.addAll(lib.myBookList);
		}
	}

	boolean logIn() { // 로그인

		System.out.println("로그인페이지 입니다. 1번을 눌러주세요.");
		String type = Tools.useScanner();
		if (!(type.equals("1"))) {
			System.out.println("잘못누르셨습니다. 1번을 눌러주세요." + "누른값::" + type);
			return false;
		}
		System.out.println(members);
		System.out.println("아이디를 입력해주세요.");
		String id = Tools.useScanner();
		if (type.equals("1")) {
			for (Member member : members) {
				if (member.getId().equals(id)) {
					System.out.println("비밀번호를 입력해주세요.");
					String pas = Tools.useScanner();
					if (member.getPassword().equals(pas)) {
						System.out.println(member.getId() + "님 로그인 되셨습니다.");
						nowUser = member;
						return true;
					} else {
						System.out.println("비밀번호가 일치하지 않습니다");
						break;
					}
				}
			}
			System.out.println("아이디가 일치하지 않습니다.");
			return false;
		} else {
			System.out.println("시스템적 에러입니다. 초기화면으로 돌아갑니다.");
			return false;
		}
	}

	void signUp() { // 회원가입

		System.out.println("회원가입 페이지입니다.");
		System.out.println("이름을 입력해주세요. (도서관 회원일 경우 도서관 이름을 입력해 주세요.)");
		String name = Tools.useScanner();
		if (!(regex.regexName(name))) {
			return;
		}

		System.out.println("아이디를 입력해주세요.");
		String id = Tools.useScanner();

		if (!(regex.regexID(id))) {
			return;
		}

		for (Member member : members) {
			if (member.getId().equals(id)) {
				System.out.println("이미 등록된 아이디 입니다.");
				return;
			}
		}

		System.out.println("비밀번호를 입력해주세요.");
		String password = Tools.useScanner();

		if (!(regex.regexPW(id, password))) {
			return;
		}

		System.out.println("1.일반회원 2.도서관 회원");
		String input = Tools.useScanner();

		switch (Integer.parseInt(input)) {
		case 1:
			members.add(new Member(name, id, password));
			// 출력
			try {
				oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(memfile)));
				oos.writeObject(members);
				oos.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case 2:
			System.out.println("도서관 회원으로 가입시 이전에 적은아이디로는 로그인 되지 않습니다.");
			System.out.println("관리자 계정을 추가해주세요.");
			System.out.println("관리자 아이디를 입력해주세요.");
			String sysid = Tools.useScanner();

			if (!(regex.regexID(sysid))) {
				return;
			}

			if (sysid.equals(id)) {
				System.out.println("같은 아이디로는 관리자 계정을 추가하실 수 없습니다.");
				return;
			}

			boolean pass = true;
			for (Member member : members) {
				if (member.getId().equals(sysid)) {
					System.out.println("member로 이미 등록된 아이디는 도서관 관리자로 가입 할 수 없습니다.");
					pass = false;
					return;
				}
			}
			if (pass) {
				System.out.println("관리자 비밀번호를 입력해주세요.");
				String syspw = Tools.useScanner();
				if (!(regex.regexPW(sysid, syspw))) {
					return;
				}

				System.out.println("관리자 이름을 입력해주세요.");
				String sysname = Tools.useScanner();
				if (!(regex.regexName(sysname))) {
					return;
				}

				nowLib = new Library(name, id, password);
				Member sysmem = new Member(sysname, sysid, syspw);
				members.add(sysmem);
				nowLib.librarian.add(sysmem);
				libraries.add(nowLib);
				// 출력
				try {
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(memfile)));
					oos.writeObject(members);
					oos.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.getStackTrace();
				}

				// nowLib = null;
				// 출력
				try {
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(libfile)));
					oos.writeObject(libraries);
					oos.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.getStackTrace();
				}
			}
			break;
		default:
			System.out.println("잘못누르셨습니다.");
			return;
		}
		System.out.println(name + "님 회원가입을 축하드립니다.");
	}

	List<Book> findBook(String type, String value) { // 도서 검색

		List<Book> tmpBook = new ArrayList<>();
		if (type.equals("도서명")) {
			for (Book book : books) {
				if (book.getName().equals(value)) {
					System.out.println(book);
					tmpBook.add(book);
					continue;
				}
			}
			return tmpBook;
		}
		if (type.equals("작가")) {
			for (Book book : books) {
				if (book.getWriter().equals(value)) {
					System.out.println(book);
					tmpBook.add(book);
					continue;
				}
			}
			return tmpBook;
		}
		if (type.equals("출판사")) {
			for (Book book : books) {
				if (book.getPosLibrary().equals(value)) {
					System.out.println(book);
					tmpBook.add(book);
					continue;
				}
			}
			return tmpBook;
		}
		return tmpBook;
	}

	void reqBook() { // 도서신청

		System.out.println("책 제목을 입력해주세요.");
		String name = Tools.useScanner();
		if (!(regex.regexBookName(name))) {
			return;
		}

		System.out.println("작가명을 입력해주세요.");
		String writer = Tools.useScanner();
		if (!(regex.regexWriterName(name, writer))) {
			return;
		}

		System.out.println("구비처, 이용하실 도서관을 입력해 주세요.");
		String posLibrary = Tools.useScanner();
		if (!(regex.regexPosLibraryName(name, writer, posLibrary))) {
			return;
		}

		Book addBook = new Book(name, writer, "", posLibrary, "");
		rbook.add(addBook);

		for (Library lib : libraries) {
			if (lib.getName().equals(addBook.getPosLibrary())) {
				lib.reqBookList.add(addBook);
				nowUser.reqBookList.add(addBook);
				// 출력
				try {
					oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(rbookfile)));
					oos.writeObject(addBook);
					oos.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return;
			}
		}
		System.out.println("구비도서관이 알맞지 않습니다.");
	}

	boolean librarian() { // 사서로그인

		for (Library library : libraries) {
			for (Member librarian : library.librarian) {
				if (librarian.getId().equals(nowUser.getId())) {
					System.out.println("사서 기능창 입니다.");
					nowLib = library;
					return true;
				}
			}
		}
		System.out.println("권한이 없습니다.");
		return false;
	}
}
