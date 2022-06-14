package day19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//DB���� ���
public class DBClass {
	// ������ �̷���� ��ü
	Connection con;
	// ������ ������ �ϱ� ���� ���� ��ü
	PreparedStatement ps;
	// select�� ��� ���� �ݱ� ���� ��ü
	ResultSet rs;

	public DBClass() { // ������ ���� - ���� �ʱ�ȭ �ҷ����Ҷ� ���
		System.out.println("������ ����");
		try {
			// �ڹٿ��� ����Ŭ ��ɾ �����ϱ� ���� ����� ����ϴ� ����
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ����DB ����
			// ����Ŭ ���� �ϴ� ����
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // �ش� ����Ŭ ���� �Է�
			con = DriverManager.getConnection(url, "java2", "1234"); // ����Ŭ ����, ����� ����, ����� ��й�ȣ �Է�
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insert(Info info) {
		String sql = "insert into newst values(?, ?, ?)"; // sql �������� ���� DB�� �߰� //?: ���߿� ���� ä������

		int result = 0; // result�� �ʱ�ȭ

		try {
			// ����� ��ü�� �̿��ؼ� ��ɾ� ���۰�ü�� ���´�
			ps = con.prepareStatement(sql); // ps�� ���ؼ� ��ɾ� ����
			// ?�ڸ��� �� ���� ä���ִ� ����
			ps.setString(1, info.getId());
			ps.setString(2, info.getName());
			ps.setInt(3, info.getAge());
			// ��ɾ �����ϰڴٴ� �ǹ�
			// executeUpdate�� ���� select ������ ������ �������� ���
			// �����̸� 1, ���и� 0 �Ǵ� ����
			result = ps.executeUpdate();

		} catch (Exception e) { // ���� �߻���
//			e.printStackTrace();
		}
		return result;
	}

	public Info selectOne(String id) {
		String sql = "select * from newst where id = '" + id + "'";
		Info info = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) { // �����͸� �������� true, �������� ���ߴٸ� false
				info = new Info(); // ��ü ����
				info.setId(rs.getString("id")); // ��ü�� ����
				info.setName(rs.getString("name"));
				info.setAge(rs.getInt("age"));
				// System.out.println(rs.getString("id"));//(DB�÷���)
				// System.out.println(rs.getString("name"));
				// System.out.println(rs.getInt("age"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info; // null�� �Ǵ� null�� �ƴѰ��� ���ϵ�
	}

	public ArrayList<Info> select() {
		String sql = "select * from newst";
		ArrayList<Info> list = new ArrayList<Info>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); // �ش��ɾ� ����
			while (rs.next()) {
				Info info = new Info();
				info.setId(rs.getString("id")); // ��ü�� ����
				info.setName(rs.getString("name"));
				info.setAge(rs.getInt("age"));
				list.add(info); 
				// System.out.println(rs.getString("id"));
				// System.out.println(rs.getString("name"));
				// System.out.println(rs.getInt("age"));
				// System.out.println("--------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int update(Info info) {
		String sql = "update newst set name = ?, age = ? where id = ?";
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, info.getName());
			ps.setInt(2, info.getAge());
			ps.setString(3, info.getId());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public int delete(Info info) {

		String sql = "delete from newst where id = ?";
		int result = 0;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, info.getId());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
