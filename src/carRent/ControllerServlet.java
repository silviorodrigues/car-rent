package carRent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarDAO carDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		carDAO = new CarDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertCar(request, response);
				break;
			case "/delete":
				deleteCar(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateCar(request, response);
				break;
			default:
				listCar(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listCar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Car> listCar = carDAO.listAllCars();
		request.setAttribute("listCar", listCar);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CarList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("CarForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Car existingCar = carDAO.getCar(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("CarForm.jsp");
		request.setAttribute("car", existingCar);
		dispatcher.forward(request, response);
	}
	
	private void insertCar(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String model = request.getParameter("model");
		String vehicle_assembler = request.getParameter("vehicle_assembler");
		String year = request.getParameter("year");
		float price = Float.parseFloat(request.getParameter("price"));

		Car newCar = new Car(model, vehicle_assembler, year, price);
		carDAO.insertBook(newCar);
		response.sendRedirect("list");
	}
	
	private void updateCar(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String model = request.getParameter("model");
		String vehicle_assembler = request.getParameter("vehicle_assembler");
		String year = request.getParameter("year");
		float price = Float.parseFloat(request.getParameter("price"));;

		Car car = new Car(id, model, vehicle_assembler, year, price);
		carDAO.updateCar(car);
		response.sendRedirect("list");
	}
	
	private void deleteCar(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Car car = new Car(id);
		carDAO.deleteBook(car);
		response.sendRedirect("list");
	}
}
