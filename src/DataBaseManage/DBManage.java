package DataBaseManage;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManage {
    private static String user = "root";
    private static String password = "1707004219";
    private static String url = "jdbc:mysql://localhost:3306/宿舍管理系统?&useSSL=true";

    public static Connection CreatConnection(){
        Connection conn = null; // 数据库的连接
        /*连接数据库*/
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("连接数据库成功");
        } catch (Exception e) {
            System.out.println("连接数据库失败");
            e.getStackTrace();
        }
        return conn;
    }
    public void closeCon(Connection con)throws Exception{
        if(con!=null) {
            con.close();
        }
    }
}