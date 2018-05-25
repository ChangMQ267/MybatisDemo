package com.sanrxteam.mybatis.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

/**
 * 
 * @author chang
 *
 */
public class ComputerDaoImplTest {

	private static SqlSession session = null;

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
@Test
	public void getAllComputer() {
		List<Map<String, Object>> computerList = session.selectList("com.sanrxteam.mybatis.dao.impl.ComputerDaoImpl.getAllComputer");
		for (Map<String, Object> map : computerList) {
			System.out.println(map);
		}
	}
@Test
	public void getComputerByCommon() {
		
		Map<String,Object> paramMap = new HashMap<>(16);
		paramMap.put("computerName", "Õ‚–«»À");
		paramMap.put("brand", "ideapad");
		paramMap.put("runMem", 8);
		paramMap.put("price", 7000);
		
		List<Map<String, Object>> computerList = session.selectList("com.sanrxteam.mybatis.dao.impl.ComputerDaoImpl.getComputerByCommon", paramMap);
		for (Map<String, Object> map : computerList) {
			System.out.println(map);
		}
	}

}
