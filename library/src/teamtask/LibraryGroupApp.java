package teamtask;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 이유진
 * @since 01/18 로그인 후 페이지{ 로그아웃 추가 로그아웃시 앱 첫페이지로 이동 로그인 되었습니다 문구 삭제
 *
 */
public class LibraryGroupApp {
	public static void main(String[] args) throws ClassNotFoundException, ParseException {
		LibraryGroupService ls = new LibraryGroupService();
		LibraryService libs = new LibraryService();
		String memfile = "memberlist.txt";
		String libfile = "liblist.txt";
		String bookfile = "booklist.txt";
		String rbookfile = "rbooklist.txt";
		MenuExeption mep = new MenuExeption();
		ls.members = Tools.fileInputMember(memfile);
		ls.libraries = Tools.fileInputLib(libfile);
		ls.books = Tools.fileInputBook(bookfile);
		ls.rbook = Tools.fileInputBook(rbookfile);
		
		// 앱첫페이지
		System.out.println();
		System.out.println();
		System.out.println("                       ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		System.out.println("                       ■                                                        ■");
		System.out.println("                       ■        +--------------------------------------+        ■");
		System.out.println("                       ■        |          2조 더나은 도서관           |        ■");
		System.out.println("                       ■        +--------------------------------------+        ■");
		System.out.println("                       ■    +---+ +---+ +---+ +---+  +---+ +---+ +---+ +---+    ■");
		System.out.println("                       ■    |   | |   | |   | |   |  |   | |   | |   | |   |    ■");
		System.out.println("                       ■    +---+ +---+ +---+ +---+  +---+ +---+ +---+ +---+    ■");
		System.out.println("                       ■    +---+ +---+ +---+ +---+  +---+ +---+ +---+ +---+    ■");
		System.out.println("                       ■    |   | |   | |   | |   |  |   | |   | |   | |   |    ■");
		System.out.println("                       ■    +---+ +---+ +---+ +---+  +---+ +---+ +---+ +---+    ■");
		System.out.println("                       ■                ◆◆◆◆◆◆◆◆◆◆◆◆                ■");
		System.out.println("                       ■                ◆◆◆◆◆◆◆◆◆◆◆◆                ■");
		System.out.println("                       ■                ◇    ◇        ◇    ◇                ■");
		System.out.println("                       ■                ◇    ◇        ◇    ◇                ■");
		System.out.println("                       ■                ◇    ◇        ◇    ◇                ■");
		System.out.println("                       ■                ◇    ◇        ◇    ◇                ■");
		System.out.println("                       ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		
		while (true) {
			System.out.println("                                               더나은 도서관");
			System.out.println("                                            +----------------+");
			System.out.println("                                            | 1. 로그인      |");
			System.out.println("                                            +----------------+");
			System.out.println("                                            +----------------+");
			System.out.println("                                            | 2. 회원가입    |");
			System.out.println("                                            +----------------+");
			System.out.println("                                            +----------------+");
			System.out.println("                                            | 0. 종료        |");
			System.out.println("                                            +----------------+");
			
			
				String input = Tools.useScanner();
				if(!(mep.inp(input))){
					continue;
				}

				switch (Integer.parseInt(input)) {
				
				case 1: // 로그인
					// 로그인 후 페이지
					boolean logck = ls.logIn();

					while (logck) {
						System.out.println("1. 내 책장 2. 도서 검색 3. 도서 신청 4. 사서 0.종료");
						input = Tools.useScanner();
						if(!(mep.inp1(input))){
							continue;
						}
					
						
						switch (Integer.parseInt(input)) {
						case 1: // 내 책장
							System.out.println("현재 회원님이 신청하신 책(들)입니다.");
							for (int i=0; i<ls.nowUser.reqBookList.size(); i++) {
								System.out.println(i+". "+ls.nowUser.reqBookList.get(i));
							}
							System.out.println("현재 회원님이 대여하신 책(들)입니다.");
							for (int i=0; i<ls.nowUser.myBookList.size(); i++) {
								System.out.println(i+". "+ls.nowUser.myBookList.get(i));
							}
							System.out.println("1. 반납 0. 복귀");
							input = Tools.useScanner();
							switch (Integer.parseInt(input)) {
							case 1:
								if(ls.nowUser.myBookList.size()==0) {
									System.out.println("반납할 책이 없습니다.");
									break;
								}
								System.out.println("현재 회원님이 대여하신 책(들)입니다.");
								for (int i=0; i<ls.nowUser.myBookList.size(); i++) {
									System.out.println(i+". "+ls.nowUser.myBookList.get(i));
								}
								System.out.println("반납하실 책 번호를 입력해주세요.");
								input = Tools.useScanner();
								int intInput = Integer.parseInt(input);
								if (0<intInput && intInput<6) {
									System.out.println("정확한 숫자를 입력해주세요");
									continue;
								}
								Book rebook = ls.nowUser.myBookList.get(intInput);
								for (Library lib : ls.libraries) {
									if (rebook.getPosLibrary().equals(lib.getName())) {
										ls.nowLib = lib;
										break;
									}
								}
								libs.returnBook(ls, ls.nowLib, rebook);
								ls.booksSetting();
								break;
							default:
								break;
							}
							break;
						case 2: // 도서 검색
							System.out.println("1. 도서명 검색 2. 작가 검색 3. 출판사명 검색 0.복귀");
							input = Tools.useScanner();
							if(!(mep.inp4(input))){
								continue;
							}
							List<Book> tmpBooks = new ArrayList<>();
							switch (Integer.parseInt(input)) {
							
							case 1: // 도서명 검색
								System.out.println("도서명을 입력해주세요");
								input = Tools.useScanner();
								tmpBooks = new ArrayList<>(ls.findBook("도서명", input));
								break;
							case 2: // 작가 검색
								System.out.println("작가를 입력해주세요");
								input = Tools.useScanner();
								tmpBooks = new ArrayList<>(ls.findBook("작가", input));
								break;
							case 3: // 출판사명 검색
								System.out.println("출판사를 입력해주세요");
								input = Tools.useScanner();
								tmpBooks = new ArrayList<>(ls.findBook("출판사", input));
								break;
							case 0:
								break;
							default:
								System.out.println("잘못 누르셨습니다. 이전 화면으로 돌아갑니다.");
								break;
							}
							if (tmpBooks.size()==0) {
								System.out.println("검색된 결과가 없습니다");
								break;
							}
							System.out.println("1. 대여 0. 복귀");
							switch (Integer.parseInt(Tools.useScanner())) {
							case 1:
								for (int i = 0; i < tmpBooks.size(); i++) {
									System.out.println(i+". "+tmpBooks.get(i).getName()+" / "+tmpBooks.get(i).getWriter()+"-대출가능: "+tmpBooks.get(i).isState());
								}
								System.out.println("대여하실 책의 번호를 선택해주세요");
								
								System.out.println();
								Book tmpBook = tmpBooks.get(Integer.parseInt(Tools.useScanner()));
								String tmpLibName = tmpBook.getPosLibrary();
								for (Library lib : ls.libraries) {
									if (lib.getName().equals(tmpLibName)) {
										ls.nowLib = lib;
										break;
									}
								}
								libs.borrowBook(ls, ls.nowLib, tmpBook.getPrimaryCode());
								ls.booksSetting();
								break;
							case 0:
								
								break;

							default:
								System.out.println("잘못 누르셨습니다. 이전 화면으로 돌아갑니다.");
								break;
							}
							break;
						case 3: // 도서 신청
							ls.reqBook();
							break;
						case 4: // 사서
							Librarian: while (ls.librarian()) {
								System.out
										.println("1. 대여/반납 현황 2. 신권 추가 3. 연체자 목록 4. 도서 삭제 5. 도서 신청 목록 6. 사서 추가 0. 복귀");
								input = Tools.useScanner();
								if(!(mep.inp2(input))){
									continue;
								}
								
								switch (Integer.parseInt(input)) {
								case 1: // 대여/반납 현황
									System.out.println("도서관 책 목록");
									ls.nowLib.myBookList.forEach(System.out::println);

									System.out.println("1. 대여중인 도서현황 2. 대여가능한 도서현황");
									input = Tools.useScanner();
									if(!(mep.inp3(input))){
										continue;
									}
									switch (Integer.parseInt(input)) {
									case 1:
										libs.borrowList(ls.nowLib,1);
										break;
									case 2:
										libs.borrowList(ls.nowLib, 2);
										break;
									default:
										System.out.println("잘못누르셨습니다.");
										break;
									}
									break;
								case 2: // 신권 추가
									System.out.println("도서명을 입력해주세요");
									String name = Tools.useScanner();
									System.out.println("작가명을 입력해주세요");
									String writer = Tools.useScanner();
									System.out.println("출판사를 입력해주세요");
									String publisher = Tools.useScanner();

									Book addBook = new Book(name, writer, publisher, ls.nowLib.getName(), null);

									System.out.println(ls.nowLib.getName());
									ls.nowLib.bookAdd(addBook);
									ls.booksSetting();
									// ls.libraries.get(ls.libraries.indexOf(ls.nowLib)).myBookList.add(addBook);
//									 ls.books.add(addBook);
									break;
								case 3: // 연체자 목록
									System.out.println("연체자 목록입니다.");
									ls.nowLib.aDelinquent.forEach(System.out::println);
									break;
								case 4: // 도서 삭제
									System.out.println("도서코드를 입력해주세요.");
									String delCode = Tools.useScanner();
									ls.nowLib.deleteBook(ls, delCode);
									ls.booksSetting();
									break;
								case 5: // 도서 신청 목록
									System.out.println("도서 신청 목록입니다.");
									ls.nowLib.reqBookList.forEach(System.out::println);
									break;
								case 6: // 사서 추가
									System.out.println("등록 하실 사서 ID를 입력해주세요");
									String librarianId = Tools.useScanner();
									for (Member member : ls.members) {
										if (member.getId().equals(librarianId)) {
											ls.nowLib.librarian.add(member);
											System.out.println("사서 추가 완료");
											break;
										}
									}
									System.out.println("회원가입한 회원만 사서로 추가 가능합니다.");
									break;
								case 0:
									break Librarian;
								default:
									break;
								}
							}
							continue;
						case 0:
							System.out.println("======logout============");
							logck = false;
							break;
						default:
							System.out.println("잘못 누르셨습니다. 올바른 값을 입력해주세요(1~4)");
							break;
						}
						if (!logck) {
							break;
						}
					}
					continue;
				case 2: // 회원가입
					ls.signUp();
					continue;
				case 0: // 종료
					System.out.println("APP exit");
					break;
//				case 9: // 관리자
//					System.out.println("관리자 기능입니다.");
//					System.out.println("id >>");
//					if (scanner.nextLine().equals("libAppSys")) {
//						System.out.println("pw >>");
//						if (scanner.nextLine().equals("sys123")) {
//
//						}
//					}
//					System.out.println("관리자 기능은 관리자만 사용가능합니다. app을 종료합니다.");
//					break;
				default:
					System.out.println("알맞은 값을 입력해주세요");
					continue;
				}

				break;
			
		}

		
		Tools.fileOutput(memfile, ls.members);
		Tools.fileOutput(libfile, ls.libraries);
		Tools.fileOutput(bookfile, ls.books);
		Tools.fileOutput(rbookfile, ls.rbook);
	}
}
