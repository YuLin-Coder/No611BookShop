package cn.svtcc.edu.mybookshop.dao.Impl;

import cn.svtcc.edu.mybookshop.dao.IBookDao;
import cn.svtcc.edu.mybookshop.db.DB;
import cn.svtcc.edu.mybookshop.entity.Books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookDaoImpl implements IBookDao {
    DB db;

    public BookDaoImpl() {
        db = new DB();
    }

    @Override
    public List<Books> getBooksByTitle(String title) {
        List<Books> blist = new ArrayList<>();
        //1.得到连接
        Connection con = db.getCon();
        String sql = "select * from books where title like '%" + title + "%'";
        try {
            //2.创建sql语句
            PreparedStatement stmt = con.prepareStatement(sql);
            //3.ִ执行sql语句
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Books b = getBookByResultSet(rs);
                blist.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blist;
    }

    @Override
    public List<Books> getBooksByCategoryId(int categoryId) {
        List<Books> blist = new ArrayList<Books>();
        //得到数据连接对象
        Connection con = db.getCon();
        String sql = "select * from books where categoryId=?";
        try {
            //创建sql语句
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, categoryId);
            //执行sql
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Books b = getBookByResultSet(rs);
                blist.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blist;
    }

    @Override
    public Books getBooksByIsbn(String isbn) {
        //得到数据连接对象
        Connection con = db.getCon();
        String sql = "select * from books where isbn = '" + isbn + "'";
        Books b = null;
        try {
            //创建sql语句
            PreparedStatement stmt = con.prepareStatement(sql);
            //执行sql
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                b = getBookByResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public List<Books> getHotBooksByClicks() {
        List<Books> blist = new ArrayList<Books>();
        //得到数据连接对象
        Connection con = db.getCon();
        String sql = "select * from books order by Clicks desc";
        try {
            //创建sql语句
            PreparedStatement stmt = con.prepareStatement(sql);
            //3.执行sql
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Books b = getBookByResultSet(rs);
                blist.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blist;
    }

    @Override
    public Books getBooksById(int id) {
        //得到数据连接对象
        Connection con = db.getCon();
        String sql = "select * from books where id=?";
        Books b = null;
        try {
            //创建sql语句
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            //执行sql
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                b = getBookByResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 根据出版社名称查询
     */
    @Override
    public List<Books> getBooksByPublishName(String name) {
        List<Books> blist = new ArrayList<Books>();
        //得到数据连接对象
        Connection con = db.getCon();
        String sql = "SELECT books.* FROM books,publishers where books.PublisherId=publishers.Id AND publishers.`Name` LIKE '%" + name + "%'";
        try {
            //创建sql语句
            PreparedStatement stmt = con.prepareStatement(sql);
            //执行sql
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Books b = getBookByResultSet(rs);
                blist.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blist;
    }


    //封装结果集
    private Books getBookByResultSet(ResultSet rs) throws SQLException {
        Books b = new Books();
        b.setId(rs.getInt(1));
        b.setTitle(rs.getString(2));
        b.setAuthor(rs.getString(3));
        b.setPublisherId(rs.getInt(4));
        b.setPublishDate(rs.getString(5));
        b.setIsbn(rs.getString(6));
        b.setWordsCount(rs.getInt(7));
        b.setUnitPrice(rs.getDouble(8));
        b.setContentDescription(rs.getString(9));
        b.setAuthorDescription(rs.getString(10));
        b.setEditorComment(rs.getString(11));
        b.setToc(rs.getString(12));
        b.setCategoryId(rs.getInt(13));
        b.setClicks(rs.getInt(14));
        b.setPic(rs.getString(15));
        return b;
    }

    @Override
    public List<Books> getBooksByCategoryName(String name) {
        List<Books> blist = new ArrayList<Books>();
        //得到数据连接对象
        Connection con = db.getCon();
        String sql = "SELECT books.* FROM books,categories where books.CategoryId=categories.Id AND categories.`Name` LIKE '%" + name + "%'";
        try {
            //创建sql语句
            PreparedStatement stmt = con.prepareStatement(sql);
            //执行sql
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Books b = getBookByResultSet(rs);
                blist.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blist;
    }

    /**
     * 获取所有图书
     */
    @Override
    public List<Books> getBookList() {
        List<Books> blist = new ArrayList<Books>();
        //得到数据连接对象
        Connection con = db.getCon();
        String sql = "SELECT * FROM books ORDER BY Id DESC;";
        try {
            //创建sql语句
            PreparedStatement stmt = con.prepareStatement(sql);
            //执行sql
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Books b = getBookByResultSet(rs);
                blist.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blist;
    }

    /**
     * 删除图书
     */
    @Override
    public boolean delBookBookId(int bookId) {
        Connection con = db.getCon();
        String sql = "DELETE FROM books WHERE Id=" + bookId;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            int rs = stmt.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新图书信息
     */
    @Override
    public boolean upBook(Books b) {
        Connection con = db.getCon();
        String sql = "UPDATE books SET Title=?,unitPrice=?,author=?,categoryId=?,publisherId=?,publishDate=?,ISBN=?,wordsCount=?,contentDescription=? ,authorDescription=?  WHERE Id=?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            //设置参数值
            stmt.setString(1, b.getTitle());
            stmt.setDouble(2, b.getUnitPrice());
            stmt.setString(3, b.getAuthor());
            stmt.setInt(4, b.getCategoryId());
            stmt.setInt(5, b.getPublisherId());
            stmt.setString(6, b.getPublishDate());
            stmt.setString(7, b.getIsbn());
            stmt.setInt(8, b.getWordsCount());
            stmt.setString(9, b.getContentDescription());
            stmt.setString(10, b.getAuthorDescription());
            stmt.setInt(11, b.getId());
            int rs = stmt.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 添加图书
     */
    @Override
    public boolean addBook(Books b) {
        Connection con = db.getCon();
        //sql语句
        String sql = "insert into books(Title,unitPrice,author,categoryId,publisherId,publishDate,ISBN,wordsCount,contentDescription,authorDescription) value(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            //设置参数
            stmt.setString(1, b.getTitle());
            stmt.setDouble(2, b.getUnitPrice());
            stmt.setString(3, b.getAuthor());
            stmt.setInt(4, b.getCategoryId());
            stmt.setInt(5, b.getPublisherId());
            stmt.setString(6, b.getPublishDate());
            stmt.setString(7, b.getIsbn());
            stmt.setInt(8, b.getWordsCount());
            stmt.setString(9, b.getContentDescription());
            stmt.setString(10, b.getAuthorDescription());
            //执行sql语句
            int rs = stmt.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
