package com.repository;

import com.config.DateBaseConfig;
import com.models.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository extends DateBaseConfig {
    private static EmployeeRepository EmployeeRepository = new EmployeeRepository();
    private List<Employee> allEmployee(ResultSet resultSet) throws SQLException {
        List<Employee>list=new ArrayList<>();
        while (resultSet.next()){
            Employee employee=new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setDate_registration(resultSet.getString("date_registration"));
            employee.setName(resultSet.getString("name"));
            employee.setLast_name(resultSet.getString("last_name"));
            employee.setPatronymic(resultSet.getString("patronymic"));
            employee.setPhone(resultSet.getString("phone"));
            employee.setId_passport(resultSet.getString("id_passport"));
            employee.setSeria_number(resultSet.getString("seria_number"));
            employee.setPhoto(resultSet.getString("avatar"));
            employee.setPosition(resultSet.getString("position"));
            list.add(employee);
        }
        return list;
    }

    public  static EmployeeRepository getEmployeeRepository(){
        return EmployeeRepository;
    }

    public List<Employee> findAllEmployee() throws SQLException, ClassNotFoundException {
        String rquest="select  * from hotel.employee";
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(rquest);
        return allEmployee(preparedStatement.executeQuery());
    }

    public void removeEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String request="delete from hotel.employee where hotel.employee.id ="+employee.getId();
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        preparedStatement.executeUpdate();
    }

    public void registrationEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        String request="insert into hotel.employee( name, last_name, patronymic, phone, id_passport, seria_number, date_registration,avatar,position) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        preparedStatement.setString(1,employee.getName());
        preparedStatement.setString(2,employee.getLast_name());
        preparedStatement.setString(3,employee.getPatronymic());
        preparedStatement.setString(4,employee.getPhone());
        preparedStatement.setString(5,employee.getId_passport());
        preparedStatement.setString(6,employee.getSeria_number());
        preparedStatement.setString(7,employee.getDate_registration());
        preparedStatement.setString(8,employee.getPhoto());
        preparedStatement.setString(9,employee.getPosition());
        preparedStatement.executeUpdate();
    }

}
