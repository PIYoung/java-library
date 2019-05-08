package teamtask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Test123 {
	static List<Book> myBookList = new ArrayList<>();
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	static Book book = new Book("해리포터", "조앤롤링", "호그와트", "libaaa", "libaaa_0000000001");
	

	public static void main(String[] args) throws ParseException {
//		Test123.borrowBook(new LibraryGroupService());
		String str1 = "2019/01/02";
		String str2 = "2019/01/03";
		
		System.out.println("==================================================");
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(Tools.delinquency(str1, str2));
		
		System.out.println(str1.substring(0, str1.indexOf("/")));
	}
}
