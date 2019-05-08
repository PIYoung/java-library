package teamtask;

/**
 * @author 염윤호
 * @since 2019-01-29 
 * 
 * 정규식을 활용
 * 회원가입, 도서신청에 쓰임
 * 
 * regexID : 숫자, 영문소문자만 가능하게 하고 글자수 제한(3이상 ~ 12이하)줌
 * regexPW : 숫자, 영문소문자 대문자, 특수문자(Shift + 1~10) 가능하게 하고 글자수 제한 (10이상~ 20이하)으로 줌
 * regexName : 한글만 가능하게 함 글자수 (2이상 ~ 5이하) 
 * 
 * regexBookName : 한글, 영문, 숫자 가능 (1이상 ~ 10이하), 같은문자 3개이상 불가능
 * regexWriterName : 한글, 영문, 숫자 가능 (1이상 ~ 10이하), 같은문자 3개이상 불가능, 책제목과 동일하면 불가능
 * regexPosLibraryName : 한글, 숫자 가능 (3이상 ~ 10이하), 같은문자 3개이상 불가능, 책제목 또는 작가와 동일하면 불가능
 *  
 * 
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Regex {

	boolean regexID(String id) {
		String idPattern = "^(?=.*[a-z])(?=.*[0-9])[a-z[0-9]]{3,12}";
		Matcher matcher1 = Pattern.compile(idPattern).matcher(id);
		idPattern = "(.)\\1\\1";
		Matcher matcher2 = Pattern.compile(idPattern).matcher(id);

		if (!(matcher1.matches())) {
			System.out.println("숫자,영문(소문자) 포함 3~12글자만 가능합니다. (공백,특수문자,한글,영문대문자 불가)");
			return false;
		}
		if (matcher2.find()) {
			System.out.println("같은 문자 3개 이상 사용불가합니다.");
			return false;
		}
		return true;
	}

	boolean regexPW(String id, String pw) {
		String pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*()])[A-Za-z[0-9]!@#$%^&*()]{10,20}$";
		Matcher matcher1 = Pattern.compile(pwPattern).matcher(pw);
		pwPattern = "(.)\\1\\1";
		Matcher matcher2 = Pattern.compile(pwPattern).matcher(pw);

		if (!(matcher1.matches())) {
			System.out.println("숫자,영문,특수문자(Shift + 1~10)포함 10~20글자만 가능합니다. (공백,한글 불가)");
			return false;
		}
		if (matcher2.find()) {
			System.out.println("같은 문자 3개 이상 사용불가합니다.");
			return false;
		}
		if (pw.contains(id)) {
			System.out.println("비밀번호에 아이디를 사용하실수 없습니다.");
			return false;
		}
		return true;
	}

	boolean regexName(String name) {
		String namePattern = "^(?=.[a-zA-Z가-힣])[a-zA-Z가-힣]{2,10}$";
		Matcher matcher = Pattern.compile(namePattern).matcher(name);
		if (!(matcher.matches())) {
			System.out.println("2글자이상 한글,영문만 입력가능합니다.");
			return false;
		}
		return true;
	}

	boolean regexBookName(String name) {
		String bookPattern = "^[a-zA-Z0-9가-힣]{1,10}$";
		Matcher matcher = Pattern.compile(bookPattern).matcher(name);
		bookPattern = "(.)\\1\\1";
		Matcher matcher2 = Pattern.compile(bookPattern).matcher(name);
		
		if (!(matcher.matches())) {
			System.out.println("알맞은 책제목을 입력해주세요. (한글,영문,숫자 가능)");
			return false;
		}
		
		if (matcher2.find()) {
			System.out.println("같은 문자 3개 이상 사용불가합니다.");
			return false;
		}
		
		return true;
	}

	boolean regexWriterName(String name,String writer) {
		String writerPattern = "^[a-zA-Z0-9가-힣]{1,10}$";
		Matcher matcher = Pattern.compile(writerPattern).matcher(writer);
		writerPattern = "(.)\\1\\1";
		Matcher matcher2 = Pattern.compile(writerPattern).matcher(writer);

		if (!(matcher.matches())) {
			System.out.println("알맞은 작가를 입력해주세요. (한글,영문,숫자 가능)");
			return false;
		}
		
		if (matcher2.find()) {
			System.out.println("같은 문자 3개 이상 사용불가합니다.");
			return false;
		}
		
		if (name.contains(writer)) {
			System.out.println("알맞은 작가명을 입력해주세요. (책제목과 중복)");
			return false;
		}
		
		return true;
	}

	boolean regexPosLibraryName(String name, String writer,String posLibrary) {
		String posLibraryPattern = "^[a-zA-Z가-힣]{2,10}$";
		Matcher matcher = Pattern.compile(posLibraryPattern).matcher(posLibrary);
		posLibraryPattern = "(.)\\1\\1\\1\\1";
		Matcher matcher2 = Pattern.compile(posLibraryPattern).matcher(posLibrary);

		if (!(matcher.matches())) {
			System.out.println("알맞은 구비처를 입력해주세요.");
			return false;
		}
		
		if (matcher2.find()) {
			System.out.println("같은 문자 5개 이상 사용불가합니다.");
			return false;
		}
		
		if (name.contains(writer) || name.contains(posLibrary) || writer.contains(posLibrary)) {
			System.out.println("알맞은 구비처를 입력해주세요. (책제목 또는 작가와 중복)");
			return false;
		}
		return true;
	}
}
