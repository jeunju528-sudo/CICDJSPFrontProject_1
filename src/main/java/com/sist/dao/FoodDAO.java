package com.sist.dao;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.FoodVO;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * Java에서 보낸 값을 JavaScript의 Ajax가 이해하기 위해서 보내는 값의 형식을 맞춰서 해야한다.
	 * Java               list   vo 
	 *   ↓                 ↓     ↓
	 * JavaScript(Ajax)    []    {}
	 * */
	public static List<FoodVO> foodFindData(Map map){
		SqlSession session = ssf.openSession();
		List<FoodVO> list = session.selectList("foodFindData", map);
		session.close();
		return list;
	}
	
	public static int foodFindCount(Map map){
		SqlSession session = ssf.openSession();
		int count = session.selectOne("foodFindCount", map);
		session.close();
		return count;
	}
}
