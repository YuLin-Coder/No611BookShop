package cn.svtcc.edu.mybookshop.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.svtcc.edu.mybookshop.dao.ICategoriesDao;
import cn.svtcc.edu.mybookshop.db.DB;
import cn.svtcc.edu.mybookshop.entity.Categories;

public class CategoriesDaoImpl implements ICategoriesDao {

	DB db;
	public CategoriesDaoImpl(){
		db=new DB();
	}
	
	//获取出版社
	@Override
	public List<Categories> getCategories() {
		//创建集合
		List<Categories> clist = new ArrayList<Categories>();
		//得到连接
		Connection con=db.getCon();
		String sql="select * from categories order by Id desc";
		try {
			//sql语句
			PreparedStatement stmt=con.prepareStatement(sql);
            //ִ执行sql
			ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				Categories c = new Categories();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				clist.add(c);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clist;
	}

	
	//添加分类
	@Override
	public boolean addCategory(Categories c) {
		Connection con = db.getCon();
		String sql = "insert into categories(Name) value(?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			//设置参数
			stmt.setString(1, c.getName());
			//ִ执行
			int rs = stmt.executeUpdate();
			if(rs>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	//更新分类
	@Override
	public boolean updateCategory(Categories c) {
		Connection con = db.getCon();
		String sql = "update categories set Name=? where Id=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			//设置参数
			stmt.setString(1, c.getName());
			stmt.setInt(2, c.getId());
			//ִ执行
			int rs = stmt.executeUpdate();
			if(rs>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
