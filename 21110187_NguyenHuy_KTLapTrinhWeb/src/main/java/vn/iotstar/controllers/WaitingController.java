package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.UserModel;

@WebServlet(urlPatterns = { "/waiting" })
public class WaitingController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy HttpSession
		HttpSession session = req.getSession(false);  // Sử dụng 'false' để không tạo session mới nếu không tồn tại
		
		if (session != null && session.getAttribute("account") != null) {
			// Kiểm tra thông tin người dùng từ session
			UserModel u = (UserModel) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());  // Thiết lập tên người dùng trong request
			
			// Chuyển hướng đến trang chính của người dùng sau khi đăng nhập thành công
			resp.sendRedirect(req.getContextPath() + "/home");
		} else {
			// Nếu không có session hoặc chưa đăng nhập, chuyển hướng đến trang login
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
}
