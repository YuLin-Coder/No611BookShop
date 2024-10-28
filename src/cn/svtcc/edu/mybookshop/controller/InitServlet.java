package cn.svtcc.edu.mybookshop.controller;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import cn.svtcc.edu.mybookshop.dao.Impl.BookDaoImpl;
import cn.svtcc.edu.mybookshop.dao.Impl.CategoriesDaoImpl;
import cn.svtcc.edu.mybookshop.dao.Impl.PublisherDaoImpl;
import cn.svtcc.edu.mybookshop.entity.Books;
import cn.svtcc.edu.mybookshop.entity.Categories;
import cn.svtcc.edu.mybookshop.entity.Publishers;
@WebServlet(name="InitServlet",urlPatterns={"/initServlet"},loadOnStartup=0)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InitServlet() {
        super();
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		//获取图书分类
		CategoriesDaoImpl dao = new CategoriesDaoImpl();
		List<Categories> clist = dao.getCategories();
		config.getServletContext().setAttribute("clist", clist);
		//获取出版社
		PublisherDaoImpl pdao = new PublisherDaoImpl();
		List<Publishers> plist = pdao.getPublisher();
		config.getServletContext().setAttribute("plist", plist);
		
		//获取热门图书
		BookDaoImpl bDao = new BookDaoImpl();
		List<Books> hotBList = bDao.getHotBooksByClicks();
		config.getServletContext().setAttribute("hotBList", hotBList);
		String ctxPath = config.getServletContext().getContextPath();
		config.getServletContext().setAttribute("ctxPath", ctxPath);
		System.out.println("InitServlet初始化完毕！");
	}

    

}
