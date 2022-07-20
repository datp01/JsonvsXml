import entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface DAOStudent {
    public int insert(StudentIT student) throws SQLException;
    public int update(StudentIT student) throws SQLException;
    public int delete(int id) throws SQLException;

    public StudentIT select(int id) throws SQLException;
    public List<StudentIT> getAll() throws SQLException;
}
