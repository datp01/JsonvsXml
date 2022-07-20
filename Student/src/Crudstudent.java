import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Crudstudent implements DAOStudent{
    Connection connection = Connect.getMssqlConnection();
    List<StudentIT> list = new ArrayList<>();

    public Crudstudent() throws SQLException {
        super();
    }
    private final String MYSQL_SELECT = "select * from StudentIT";
    private final String MYSQL_SELECT_ONE = "select * from StudentIT where id = ?";
    private final String MYSQL_INSERT = "Insert into info value(?,?,?)";
    private final String MYSQL_UPDATE = "update info set name = ? , mark = ? , where id = ?";
    private final String MYSQL_DELETE = "delete from info where id = ?";

    @Override
    public int insert(StudentIT student) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        preparedStatement = connection.prepareStatement(MYSQL_INSERT);
        preparedStatement.setInt(1, student.getId());
        preparedStatement.setString(2, student.getName());
        preparedStatement.setDouble(3, student.getMark());
        result = preparedStatement.executeUpdate();
        return result;
    }

    @Override
    public int update(StudentIT student) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        preparedStatement = connection.prepareStatement(MYSQL_UPDATE);
        preparedStatement.setString(1,student.getName());
        preparedStatement.setDouble(2,student.getMark());
        preparedStatement.setInt(3,student.getId());
        result = preparedStatement.executeUpdate();
        return result;
    }

    @Override
    public int delete(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        preparedStatement = connection.prepareStatement(MYSQL_DELETE);
        preparedStatement.setInt(1,id);
        result = preparedStatement.executeUpdate();
        if (result > 0) {
            System.out.println("Xóa Thành Công");
        } else {
            System.out.println("Xóa không thành công");
        }
        return result;
    }

    @Override
    public StudentIT select(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StudentIT student = null;

        preparedStatement = connection.prepareStatement(MYSQL_SELECT_ONE);
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet != null) {
            System.out.println("Tìm thành công");
        } else {
            System.out.println("Tìm không thành công");
        }
        while (resultSet.next()) {
            System.out.println("ID: " +resultSet.getInt("id")+"Name"+ resultSet.getString("name"+ "Mark" +resultSet.getDouble("mark")));
            student = new StudentIT(resultSet.getInt("id"), resultSet.getString("name") ,resultSet.getDouble("mark"));
        }
        return student;
    }

    @Override
    public List<StudentIT> getAll() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StudentIT studentIT = null;
        preparedStatement= connection.prepareStatement(MYSQL_SELECT);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            Double mark = resultSet.getDouble("mark");
            String name = resultSet.getString("name");
            StudentIT studentIT1 = new StudentIT(id,name,mark);
            addStudent(studentIT1);
            System.out.println("Id" + id + "| Name" + name + "| Mark" + mark);
        }
        return list;
    }

        public void addStudent(StudentIT studentIT) {
            list.add(studentIT);
        }
}

