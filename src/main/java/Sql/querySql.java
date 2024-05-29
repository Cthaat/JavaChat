package Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class querySql
{
    private querySql()
    {
    }

    public static boolean isHavaUser(String username , String password)
    {
        try
        {
            Connection conn = SQLUtils.getConnection();
            PreparedStatement ps = null;
            String sql = "select count(*) from users where username = ? and password = ?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(SQLUtils.getDataSource());
            // 查询是否有该用户
            int count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
            if (count == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            return false;
        }
    }
}
