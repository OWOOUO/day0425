package nowon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import nowon.domain.dto.MemberDTO;

@WebServlet("/member/join")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SqlSessionFactory ssf;
    
	@Override
	public void init() throws ServletException {
		ssf=(SqlSessionFactory)getServletContext().getAttribute("ssf");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		
		//MemberDTO dto=new MemberDTO(email, name, pass);
		MemberDTO dto=new MemberDTO();
		dto.setEmail(email);
		dto.setName(name);
		dto.setPass(pass);
		
		//mybatis를 이용해서 DB처리
		System.out.println(ssf);
		//openSession() 메서드를 이용해서
		SqlSession sqlSession=ssf.openSession(true);
					//namespace.id
		sqlSession.insert("memberMapper.save", dto);
		//sqlSession.commit();//접속할때 설정할수 있다..
		sqlSession.close();
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
