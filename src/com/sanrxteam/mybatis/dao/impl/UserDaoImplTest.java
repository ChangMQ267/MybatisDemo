package com.sanrxteam.mybatis.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;
import com.sanrxteam.mybatis.entity.User;

/**
 * 
 * 
 * @author chang
 *
 */
public class UserDaoImplTest {
	private static  SqlSession session = null;
	static {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream("mybatis.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory factory = builder.build(inputStream);
		session = factory.openSession();
	}
	
	
	/**
	 * @throws IOException 
	 * 
	 */
//	@Before
//	public void init() throws IOException {
//		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//		InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
//		SqlSessionFactory factory = builder.build(inputStream);
//		session = factory.openSession();
//		System.out.println("----init----------");
//	}
	/**
	 * 无条件查询
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void getAllUser() throws IOException {
		
		List<Map<String, Object>> userMap = session.selectList("com.sanrxteam.mybatis.dao.impl.UserDaoImpl.getAllUser");
		System.out.println(userMap);
	}

	/**
	 * 一个参数查询
	 * 
	 * @throws IOException
	 */
	@Test
	public void getUserById() throws IOException {
		
		Long id = 1L;
		Map<String, Object> userMap = session.selectOne("com.sanrxteam.mybatis.dao.impl.UserDaoImpl.getUserById", id);
		System.out.println(userMap);

	}

	/**
	 * 多个参数查询
	 * 
	 * @throws IOException
	 */
	@Test
	public void getUserByCondition() throws IOException {

	

		Map<String, Object> paramMap = new HashMap<>(16);
		paramMap.put("username", "cmq");
		paramMap.put("password", "123");

		Map<String, Object> userMap = session
				.selectOne("com.sanrxteam.mybatis.dao.impl.UserDaoImpl.getUserByCondition", paramMap);
		System.out.println(userMap);
	}

	/**
	 * 模糊查询
	 * 
	 * @throws IOException
	 */
	@Test
	public void getUserByMh() throws IOException {

		Map<String, Object> paramMap = new HashMap<>(16);
		paramMap.put("username", "二");

		List<Map<String, Object>> userMap = session.selectList("com.sanrxteam.mybatis.dao.impl.UserDaoImpl.getUserByMh",paramMap);
		for (Map<String, Object> map : userMap) {
			System.out.println(map);
		}

	}
	@Test
	public void addUser() {
		Map<String, Object> paramMap = new HashMap<>(16);
		paramMap.put("username", "cmq");
		paramMap.put("password", "123");
		
		session.insert("com.sanrxteam.mybatis.dao.impl.UserDaoImpl.addUser", paramMap);
		session.commit();
		
		
	}
}
