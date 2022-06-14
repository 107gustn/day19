package day19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//DB관련 기능
public class DBClass {
	// 연결이 이루어진 객체
	Connection con;
	// 쿼리문 전송을 하기 위한 전송 객체
	PreparedStatement ps;
	// select의 결과 값을 반기 위한 객체
	ResultSet rs;

	public DBClass() { // 생성자 생성 - 값을 초기화 할려고할때 사용
		System.out.println("생성자 실행");
		try {
			// 자바에서 오라클 명령어를 수행하기 위한 기능을 등록하는 과정
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 최초DB 연결
			// 오라클 연결 하는 과정
			String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 해당 오라클 정보 입력
			con = DriverManager.getConnection(url, "java2", "1234"); // 오라클 정보, 사용자 계정, 사용자 비밀번호 입력
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insert(Info info) {
		String sql = "insert into newst values(?, ?, ?)"; // sql 쿼리문을 통해 DB에 추가 //?: 나중에 값을 채워놓음

		int result = 0; // result값 초기화

		try {
			// 연결된 객체를 이용해서 명령어 전송객체를 얻어온다
			ps = con.prepareStatement(sql); // ps를 통해서 명령어 수행
			// ?자리에 각 값을 채워주는 역할
			ps.setString(1, info.getId());
			ps.setString(2, info.getName());
			ps.setInt(3, info.getAge());
			// 명령어를 전송하겠다는 의미
			// executeUpdate는 보통 select 쿼리를 제외한 나머지에 사용
			// 성공이면 1, 실패면 0 또는 에러
			result = ps.executeUpdate();

		} catch (Exception e) { // 에러 발생시
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
			if (rs.next()) { // 데이터를 가져오면 true, 가져오지 못했다면 false
				info = new Info(); // 객체 생성
				info.setId(rs.getString("id")); // 객체에 저장
				info.setName(rs.getString("name"));
				info.setAge(rs.getInt("age"));
				// System.out.println(rs.getString("id"));//(DB컬럼명)
				// System.out.println(rs.getString("name"));
				// System.out.println(rs.getInt("age"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info; // null값 또는 null이 아닌값이 리턴됨
	}

	public ArrayList<Info> select() {
		String sql = "select * from newst";
		ArrayList<Info> list = new ArrayList<Info>();
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); // 해당명령어 실행
			while (rs.next()) {
				Info info = new Info();
				info.setId(rs.getString("id")); // 객체에 저장
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
