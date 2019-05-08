package teamtask;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author 정공명
 * @since : 2019-01-31
 * 변경사항: App 실행 후 메뉴 선택화면에서 숫자외 입력할 경우 발생되는 예외 처리 코드 작성
 *
 */

public class MenuExeption {

	boolean inp(String input) {
		String pattern = "^[(1)(2)(3)(0)]$";
		Matcher matcher = Pattern.compile(pattern).matcher(input);
		pattern = "(.)\\1\\1";
		Matcher matcher3 = Pattern.compile(pattern).matcher(input);
		if (!(matcher.matches())) {
			System.err.println("화면에 출력되는 숫자만 입력해주세요.");
			return false;
		}
		return true;
	}


	boolean inp1(String input) {
		String pattern = "^[(1)(2)(3)(4)(0)]$";
		Matcher matcher = Pattern.compile(pattern).matcher(input);
		pattern = "(.)\\1\\1";
		Matcher matcher3 = Pattern.compile(pattern).matcher(input);
		if (!(matcher.matches())) {
			System.err.println("화면에 출력되는 숫자만 입력해주세요.");
			return false;
		}
		return true;
	}

	boolean inp2(String input) {
		String pattern = "^[(1)(2)(3)(4)(5)(6)(0)]$";
		Matcher matcher = Pattern.compile(pattern).matcher(input);
		pattern = "(.)\\1\\1";
		Matcher matcher3 = Pattern.compile(pattern).matcher(input);
		if (!(matcher.matches())) {
			System.err.println("화면에 출력되는 숫자만 입력해주세요.");
			return false;
		}
		return true;
	}
	boolean inp3(String input) {
		String pattern = "^[(1)(2)]$";
		Matcher matcher = Pattern.compile(pattern).matcher(input);
		pattern = "(.)\\1\\1";
		Matcher matcher3 = Pattern.compile(pattern).matcher(input);
		if (!(matcher.matches())) {
			System.err.println("화면에 출력되는 숫자만 입력해주세요.");
			return false;
		}
		return true;
	}

	boolean inp4(String input) {
		String pattern = "^[(1)(2)(3)]$";
		Matcher matcher = Pattern.compile(pattern).matcher(input);
		pattern = "(.)\\1\\1";
		Matcher matcher3 = Pattern.compile(pattern).matcher(input);
		if (!(matcher.matches())) {
			System.err.println("화면에 출력되는 숫자만 입력해주세요.");
			return false;
		}
		return true;
	}
}
