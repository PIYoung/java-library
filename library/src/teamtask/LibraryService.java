package teamtask;

import java.text.ParseException;

public class LibraryService {
	void returnBook(LibraryGroupService ls, Library lib, Book book) throws ParseException {
		// 반납
		int returnDay = book.getEndDay().compareTo(Tools.getToday());
		
		String delday;
		String delId;
		if (returnDay < 0) {
			System.out.println("반납일이 지난 책입니다.");
			for (String delUser : lib.aDelinquent) {
				delday = delUser.substring(delUser.indexOf(",")+1);
				delId = delUser.substring(0, delUser.indexOf(","));
				if (delUser.equals(ls.nowUser.getId())) {
					System.out.println(delUser);
					if (Tools.getToday().compareTo(delday)<=0) {
						lib.aDelinquent.remove(delUser);
						System.out.println("기존 연체자 입니다. 남은 연체일수에 현재 반납한 책의 연체일수가 더해집니다.");
						break;
					}else {
						lib.aDelinquent.remove(delUser);
					}
				}
			}
			System.out.println("현재 날짜 : "+Tools.getToday());
			System.out.println(Tools.delinquency(book.getEndDay(), Tools.getToday())+"까지 대여가 제한됩니다.");
			lib.aDelinquent.add(ls.nowUser.getId()+","+Tools.delinquency(book.getEndDay(), Tools.getToday()));
		}
		ls.nowUser.myBookList.remove(book);
		for (Book nowBook : lib.myBookList) {
			if (nowBook.getPrimaryCode().equals(book.getPrimaryCode())) {
				nowBook.setState(!book.isState());
				nowBook.setStartDay("");
				nowBook.setEndDay("");
				nowBook.setBorrowUser("");
				break;
			}
		}
		System.out.println("반납되었습니다.");
	}

	public void borrowBook(LibraryGroupService ls, Library lib, String bookCode) {
		// TODO Auto-generated method stub
		for (Book book : ls.nowUser.myBookList) {
			if (book.getEndDay().compareTo(Tools.getToday()) < 0) {
				System.out.println("연체중인 도서가 있습니다. 연체중에는 도서를 대여하실수없습니다.");
				return;
			}
		}
		if (ls.nowUser.myBookList.size() >= 5) {
			System.out.println("5권 이상은 대여 하실 수 없습니다.");
			return;
		}
		for (String adel : lib.aDelinquent) {
			String adelday = adel.substring(adel.indexOf(",")+1);
			adel = adel.substring(0,adel.indexOf(","));
			if (ls.nowUser.getId().equals(adel)) {
				if (adelday.compareTo(Tools.getToday())>0) {
					System.out.println(adelday+"까지 연체기간입니다.");
					return;
				}
			}
		}
		for (Book tmpBook : lib.myBookList) {
			if (tmpBook.getPrimaryCode().equals(bookCode)) {
				if (!(tmpBook.isState() && lib.myBookList.get((lib.myBookList.indexOf(tmpBook))).isState())) {
					System.out.println("현재 대여중인 도서입니다.");
					return;
				}
				lib.myBookList.get(lib.myBookList.indexOf(tmpBook)).setBorrowUser(ls.nowUser.getId());
				lib.myBookList.get(lib.myBookList.indexOf(tmpBook))
						.setState(!lib.myBookList.get(lib.myBookList.indexOf(tmpBook)).isState());
				lib.myBookList.get(lib.myBookList.indexOf(tmpBook)).setStartDay(Tools.getToday());
				lib.myBookList.get(lib.myBookList.indexOf(tmpBook)).setEndDay(Tools.getToday(7));
				ls.nowUser.myBookList.add(tmpBook);
				System.out.println(tmpBook.getName() + "이 정상적으로 대여되었습니다.");
			}
		}
	}

	void borrowList(Library lib, int input) { // 대여반납 현황 보는거만
		switch (input) {
		case 1:
			// 대여중인거
			for (Book book : lib.myBookList) {
				if (!book.isState()) {
					System.out.println(book);
				}
			}
			break;
		case 2:
			// 대여가능한거
			for (Book book : lib.myBookList) {
				if (book.isState()) {
					System.out.println(book);
				}
			}
			break;
		default:
			// 잘못누른거
			System.out.println("잘못 입력하셨습니다.");
			break;
		}
	}

	public void addLibrarian(Library lib, Member member) {
		// TODO Auto-generated method stub
		System.out.println("기존 회원을 사서로 등록");
		lib.librarian.add(member);
	}

}
