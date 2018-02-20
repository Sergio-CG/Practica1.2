

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmailList
 */
@WebServlet("/EmailList")
public class EmailList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// Se leen los parámetros
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String email = request.getParameter("email");
			// Se crea el objeto usuario (se supone que existe la clase Usuario)
			Usuario usuario = new Usuario(email, nombre, apellidos);
			// Y se guarda en una base de datos (igualmente se supone implementada)
			UsuarioDB basededatos = new UsuarioDB( );
			basededatos.add (usuario);
			// A continuación se guarda en la sesión el mismo objeto que en la base de datos
			HttpSession session = request.getSession( );
			session.setAttribute ("usuario",usuario);
			// Y se almacena el email en una cookie para poder identificar en el futuro
			// al usuario mediante su email cuando vuelva a navegar por el sitio web
			Cookie c = new Cookie("emailCookie", email);
			c.setMaxAge(60*60*24*365*2);
			c.setPath("/");
			response.addCookie(c);
	}

}
