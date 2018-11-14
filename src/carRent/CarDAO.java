package carRent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    
    public CarDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
    
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
     
    public boolean insertBook(Car car) throws SQLException {
        String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, car.getModel());
        statement.setString(2, car.getVehicle_assembler());
        statement.setString(3, car.getYear());
        statement.setFloat(4, car.getPrice());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<Car> listAllCars() throws SQLException {
        List<Car> listCar = new ArrayList<>();
         
        String sql = "SELECT * FROM car";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("car_id");
            String model = resultSet.getString("model");
            String vehicle_assembler = resultSet.getString("vehicle_assembler");
            String year = resultSet.getString("year");
            float price = resultSet.getFloat("price");
             
            Car car = new Car(id, model, vehicle_assembler, year, price);
            listCar.add(car);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listCar;
    }
     
    public boolean deleteBook(Car car) throws SQLException {
        String sql = "DELETE FROM car where car_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, car.getId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateCar(Car car) throws SQLException {
        String sql = "UPDATE car SET model = ?, vehicle_assembler = ?, year = ?, price = ?";
        sql += " WHERE car_id = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, car.getModel());
        statement.setString(2, car.getVehicle_assembler());
        statement.setFloat(3, car.getPrice());
        statement.setString(4, car.getYear());
        statement.setInt(5, car.getId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public Car getCar(int id) throws SQLException {
        Car car = null;
        String sql = "SELECT * FROM car WHERE car_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String model = resultSet.getString("model");
            String vehicle_assembler = resultSet.getString("vehicle_assembler");
            String year = resultSet.getString("year");
            float price = resultSet.getFloat("price");
             
            car = new Car(id, model, vehicle_assembler, year, price);
        }
         
        resultSet.close();
        statement.close();
         
        return car;
    }
}
