package day19;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		DBClass db = new DBClass(); //DB접근 //객체를 만들면 생성자 자동으로 호출
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		
		// DB 형식에 맞춰줌
		String id, name;
		int age, num, result=0;
		
		
		while(flag) {
			Info info = new Info();
			System.out.println("1.등록");
			System.out.println("2.조회");
			System.out.println("3.모든 목록");
			System.out.println("4.수정");
			System.out.println("5.삭제");
			System.out.println("6.종료");
			System.out.print(">>>> ");
			num = sc.nextInt();
			switch(num) {
			case 1:
				System.out.println("아이디 입력");
				id = sc.next();
				System.out.println("이름 입력");
				name = sc.next();
				System.out.println("나이 입력");
				age = sc.nextInt();
				
				info.setId(id);
				info.setName(name);
				info.setAge(age);
				
				result = db.insert(info); //DB에 입력
				if(result == 1) {
					System.out.println("저장 성공!!");
				}else {
					System.out.println("저장 실패");
				}
				break;
			case 2:
				System.out.println("검색 아이디 입력");
				id = sc.next();
				info = db.selectOne(id);
				if(info == null) {
					System.out.println("해당 아이디는 없습니다.");
				}else {
					System.out.println("아이디 : " + info.getId());
					System.out.println("이 름 : " + info.getName());
					System.out.println("나 이 : " + info.getAge());
				}
				break;
			case 3:
				ArrayList<Info> list = db.select();
				if(list.size() == 0) {
					System.out.println("저장 정보가 없습니다.");
				}else {
					/*
					for(int i=0; i<list.size(); i++) {
						info = list.get(i);
						//list.get(i).getId()
						System.out.println("id : " + info.getId());
						System.out.println("name : " + info.getName());
						System.out.println("age : " + info.getAge());
						System.out.println("----------------------------");
					}
					*/
					for(Info in : list) {
						System.out.println("아이디 : " + in.getId());
						System.out.println("이 름 : " + in.getName());
						System.out.println("나 이 : " + in.getAge());
						System.out.println("-------------------");
					}
				}
				break;
			case 4:
				System.out.println("수정할 아이디 입력 : ");
				id = sc.next();
				info = db.selectOne(id);
				if(info == null) {
					System.out.println("해당 아이디는 없습니다.");
				}else {
					System.out.print("이름 입력 : ");
					name = sc.next();
					System.out.print("나이 입력 : ");
					age = sc.nextInt();
					
					info.setId(id);
					info.setName(name);
					info.setAge(age);
					
					result = db.update(info);
					if(result == 1) {
						System.out.println("수정 성공!!");
					}else {
						System.out.println("수정 실패");
					}
					
				}
				break;
			case 5:
				System.out.println("삭제할 아이디 입력 : ");
				id = sc.next();
				info = db.selectOne(id);
				if(info == null) {
					System.out.println("해당 아이디는 없습니다.");
				}else {
					info.setId(id);
					result = db.delete(info);
					if(result == 1) {
						System.out.println("삭제 성공!!");
					}else {
						System.out.println("삭제 실패");
					}
				}
				break;
			case 6:
				System.out.println("종료합니다.");
				flag = false;
				break;
			}
		}
	}
}
