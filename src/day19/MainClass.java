package day19;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		DBClass db = new DBClass(); //DB���� //��ü�� ����� ������ �ڵ����� ȣ��
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		
		// DB ���Ŀ� ������
		String id, name;
		int age, num, result=0;
		
		
		while(flag) {
			Info info = new Info();
			System.out.println("1.���");
			System.out.println("2.��ȸ");
			System.out.println("3.��� ���");
			System.out.println("4.����");
			System.out.println("5.����");
			System.out.println("6.����");
			System.out.print(">>>> ");
			num = sc.nextInt();
			switch(num) {
			case 1:
				System.out.println("���̵� �Է�");
				id = sc.next();
				System.out.println("�̸� �Է�");
				name = sc.next();
				System.out.println("���� �Է�");
				age = sc.nextInt();
				
				info.setId(id);
				info.setName(name);
				info.setAge(age);
				
				result = db.insert(info); //DB�� �Է�
				if(result == 1) {
					System.out.println("���� ����!!");
				}else {
					System.out.println("���� ����");
				}
				break;
			case 2:
				System.out.println("�˻� ���̵� �Է�");
				id = sc.next();
				info = db.selectOne(id);
				if(info == null) {
					System.out.println("�ش� ���̵�� �����ϴ�.");
				}else {
					System.out.println("���̵� : " + info.getId());
					System.out.println("�� �� : " + info.getName());
					System.out.println("�� �� : " + info.getAge());
				}
				break;
			case 3:
				ArrayList<Info> list = db.select();
				if(list.size() == 0) {
					System.out.println("���� ������ �����ϴ�.");
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
						System.out.println("���̵� : " + in.getId());
						System.out.println("�� �� : " + in.getName());
						System.out.println("�� �� : " + in.getAge());
						System.out.println("-------------------");
					}
				}
				break;
			case 4:
				System.out.println("������ ���̵� �Է� : ");
				id = sc.next();
				info = db.selectOne(id);
				if(info == null) {
					System.out.println("�ش� ���̵�� �����ϴ�.");
				}else {
					System.out.print("�̸� �Է� : ");
					name = sc.next();
					System.out.print("���� �Է� : ");
					age = sc.nextInt();
					
					info.setId(id);
					info.setName(name);
					info.setAge(age);
					
					result = db.update(info);
					if(result == 1) {
						System.out.println("���� ����!!");
					}else {
						System.out.println("���� ����");
					}
					
				}
				break;
			case 5:
				System.out.println("������ ���̵� �Է� : ");
				id = sc.next();
				info = db.selectOne(id);
				if(info == null) {
					System.out.println("�ش� ���̵�� �����ϴ�.");
				}else {
					info.setId(id);
					result = db.delete(info);
					if(result == 1) {
						System.out.println("���� ����!!");
					}else {
						System.out.println("���� ����");
					}
				}
				break;
			case 6:
				System.out.println("�����մϴ�.");
				flag = false;
				break;
			}
		}
	}
}
