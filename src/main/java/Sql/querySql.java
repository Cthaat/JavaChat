package Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class querySql
{
    private querySql()
    {
    }

    public static int isHavaUser(String username , String password)
    {
        try
        {
            Connection conn = SQLUtils.getConnection();
            PreparedStatement ps = null;
            String sql = "select count(*) from users where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            int count = ps.executeUpdate();
            SQLUtils.closeConnection(ps , conn);
            return count;
        }
        catch (SQLException e)
        {
            return -1;
        }
    }
}
