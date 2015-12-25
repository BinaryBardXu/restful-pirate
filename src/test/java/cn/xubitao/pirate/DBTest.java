package cn.xubitao.pirate;

import cn.xubitao.pirate.persistence.project.ProjectTable;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import org.junit.Test;

import java.sql.*;

/**
 * Created by xubitao on 12/24/15.
 */
public class DBTest {
    @Test
    public void 测试数据库连接() {
        try {
            String fileName = this.getClass().getResource("").getPath() + "sqllite/pirate.db";
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM test_table");
            while (rs.next()) {
                String id = rs.getString("country_id");   // Column 1
                String code = rs.getString("country_code"); // Column 2
                String name = rs.getString("country_name"); // Column 3
                System.out.println("ID: " + id + " Code: " + code + " Name: " + name);

            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
    }

    @Test
    public void 通过ORM查询数据库() throws SQLException, ClassNotFoundException {
        String fileName = "sqllite/pirate.db";
        ConnectionSource connectionSource =
                new JdbcConnectionSource("jdbc:sqlite:" + fileName);
        // instantiate the dao
        Dao<ProjectTable, Integer> accountDao =
                DaoManager.createDao(connectionSource, ProjectTable.class);
        accountDao.create(new ProjectTable() {
            {
                setVersion("2");
                setName("Jim");
            }
        });
    }
}
