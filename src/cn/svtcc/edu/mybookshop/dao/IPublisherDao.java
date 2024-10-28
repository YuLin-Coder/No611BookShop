package cn.svtcc.edu.mybookshop.dao;

import java.util.List;

import cn.svtcc.edu.mybookshop.entity.Publishers;


public interface IPublisherDao {
	//获取出版社
	public List<Publishers> getPublisher();
	//添加出版社
	public boolean addPublisher(Publishers p);
	//更新出版社
	public boolean upPublisher(Publishers p);
}
