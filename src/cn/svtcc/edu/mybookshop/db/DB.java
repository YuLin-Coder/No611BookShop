package cn.svtcc.edu.mybookshop.db;

import java.sql.*;

public class DB {
    private Connection con;

    private PreparedStatement pstm;

    private String user = "root";

    private String password = "root";

    private String className = "com.mysql.cj.jdbc.Driver";

    private String url = "jdbc:mysql://localhost:3306/mybookshop?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";

    public DB() {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     */
    public Connection getCon() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            con = null;
            e.printStackTrace();
        }
        return con;
    }

    public void doPstm(String sql, Object[] params) {
        if (sql != null && !sql.equals("")) {
            if (params == null)
                params = new Object[0];

            getCon();
            if (con != null) {
                try {
                    System.out.println(sql);
                    pstm = con.prepareStatement(sql,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    for (int i = 0; i < params.length; i++) {
                        pstm.setObject(i + 1, params[i]);
                    }
                    pstm.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ResultSet getRs() throws SQLException {
        return pstm.getResultSet();
    }

    public ResultSet getEditRs() throws SQLException {
        return pstm.getGeneratedKeys();
    }

    public int getCount() throws SQLException {
        return pstm.getUpdateCount();
    }

    public void closed() {
        try {
            if (pstm != null)
                pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
