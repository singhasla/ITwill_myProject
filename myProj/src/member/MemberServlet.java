package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//���������� : ����
@WebServlet("/mem")
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		//������ ������ ���� ����
		response.setContentType("text/html;charset=utf-8");

		//Ŭ���̾�Ʈ�� ���������� ������(�����) ��Ʈ����� ��ü ����
		PrintWriter writer = response.getWriter();
		
		
		//1. ��û�� �� ���
		String id = request.getParameter("id");	//ajax3.html�� �ִ� data : { id: _id }�ȿ� �Ӽ���"id"�� ����Ŵ (_id���� �ҷ���) 
		
		//2. ������ �� ����
		//		(MemberDAO��ü�� overLappedID(String id)�޼ҵ� ȣ��� �Է��� ���̵� �����Ͽ�
		//		DB�� ����� ID�� ���ؼ� �ߺ��Ǿ����� �ߺ����� �ʾҴ��� �Ǵ��� ���� ������)
		MemberDAO memberDAO = new MemberDAO();
		
		//ID�ߺ� ���θ� üũ�ϱ� ���� �޼ҵ� ȣ�� �� ��ȯ�ޱ�
		boolean overlappedID = memberDAO.overLappedID(id);
		
		//3. ���� -> ���������� ���� -> ajax����� ��û�� ajax3.html�� ����
		if(overlappedID == true){	//���̵� �ߺ��� ���
			writer.print("not_useable");
		} else {	//���̵� �ߺ��� �ƴ� ���
			writer.print("useable");
		}
		
	}
}