package teamtask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Tools {
	static Calendar cal;
	static Scanner scanner = new Scanner(System.in);
	static String input;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	static String useScanner() {
		System.out.print(">>");
		input = scanner.nextLine();
		return input;
	}
	
	static String getToday() {
		cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}
	
	static String getToday(int addDay) {
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, addDay);
		return sdf.format(cal.getTime());
	}
	
	static String delinquency(String day1, String day2) throws ParseException{
		long day1ms, day2ms, dif;
		
		day1ms = sdf.parse(day1).getTime();
		day2ms = sdf.parse(day2).getTime();
		
		dif = day1ms-day2ms;
		day2ms -= dif;
		
		return sdf.format(new Date(day2ms));
	}
	
	
	//선생님한테 물어볼 것
	static void fileInput(String fileName, List<?> list) {
		
//		try {
//			fis = new FileInputStream(fileName);
//			bis = new BufferedInputStream(fis);
//			ois = new ObjectInputStream(bis);
//			System.out.println(list.get(0));
//			System.out.println(list.get(0).getClass());
//			System.out.println(list.get(0) instanceof Library);
//			
//			if (list.get(0) instanceof Library) {
//				System.out.println("is library list");
//				list.remove(0);
//				list = (ArrayList<Library>)ois.readObject();
//			}else if (list.get(0) instanceof Library) {
//				list.remove(0);
//				System.out.println("is member list");
//				list = (ArrayList<Member>)ois.readObject();
//			}else if (list.get(0) instanceof Library) {
//				list.remove(0);
//				System.out.println("is book list");
//				list = (ArrayList<Book>)ois.readObject();
//			}
//			ois.close();
//
//		} catch (EOFException e) {
//			// TODO: handle exception
//			// e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO: handle exception
//			// e.printStackTrace();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}
	
	static ArrayList<Member> fileInputMember(String fileName) {
		
		ArrayList<Member> list = new ArrayList<>();
		FileInputStream fis;
		BufferedInputStream bis;
		ObjectInputStream ois;

		try {
			fis = new FileInputStream(fileName);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			
			list = (ArrayList<Member>)ois.readObject();
			list.forEach(System.out::println);
			ois.close();
			return list;
			
		} catch (EOFException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("member file input err");
		return new ArrayList<Member>();
	}
	static ArrayList<Library> fileInputLib(String fileName) {
		
		ArrayList<Library> list = new ArrayList<>();
		FileInputStream fis;
		BufferedInputStream bis;
		ObjectInputStream ois;
		
		try {
			fis = new FileInputStream(fileName);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			System.out.println("sys ck1");
			list = (ArrayList<Library>)ois.readObject();
			System.out.println("sys ck2");
			list.forEach(System.out::println);
			ois.close();
			return list;
			
		} catch (EOFException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("lib file input err");
		return new ArrayList<Library>();
	}
	static ArrayList<Book> fileInputBook(String fileName) {
		
		ArrayList<Book> list = new ArrayList<>();
		FileInputStream fis;
		BufferedInputStream bis;
		ObjectInputStream ois;
		
		try {
			fis = new FileInputStream(fileName);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			
			list = (ArrayList<Book>)ois.readObject();
			list.forEach(System.out::println);
			ois.close();
			return list;
			
		} catch (EOFException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			// e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("book file input err");
		return new ArrayList<Book>();
	}
	
	static void fileOutput(String fileName, List<?> list) {
		FileOutputStream fos;
		BufferedOutputStream bos;
		ObjectOutputStream oos;
		try {

			// memberlist file
			fos = new FileOutputStream(fileName);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);

			oos.writeObject(list);
			oos.close();

		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
