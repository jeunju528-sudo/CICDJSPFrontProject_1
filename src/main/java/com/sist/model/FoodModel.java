package com.sist.model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.FoodDAO;
import com.sist.vo.FoodVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FoodModel {

	@RequestMapping("food/find.do")
	public String foodFindData(HttpServletRequest request, HttpServletResponse response) {
		/*
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		String column = request.getParameter("column");
		if(column == null) {
			column = "address";
		}
		String ss = request.getParameter("ss"); // Search String
		if(ss == null) {
			ss = "마포";
		}
		String[] types = request.getParameterValues("type");
		
		int curPage = Integer.parseInt(page); // 현재 페이지
		final int ROWSIZE = 12; // 한 페이지에 12개씩 데이터 보여줌
		int start = (curPage*ROWSIZE)-ROWSIZE; // 데이터를 몇 번 부터 보여줘야하는지 정하는 것 
				
		Map map = new HashMap();
		map.put("start", start);
		map.put("ss", ss);
		map.put("column", column);
		map.put("fdArr", types);
		
		List<FoodVO> list = FoodDAO.foodFindData(map);
		int count = FoodDAO.foodFindCount(map);
		
		int totalPage = (int)(Math.ceil(count/(double)ROWSIZE)); // 전체 페이지 = 전체 데이터 갯수 / 한 페이지당 보여줄 갯수
		final int BLOCK = 10; // 페이징을 10개씩 1 2 3 4 5 6 7 8 9 10 >>
		
		int startPage = ((curPage-1) / BLOCK * BLOCK) + 1; // -1 하는 이유 :  curPage : 10 > ((7 / 10) * 10) + 1 = 1
		int endPage = ((curPage-1) / BLOCK * BLOCK) + BLOCK; // curPage : 10 > ((7/10) * 10 ) + 10 = 10
		
		if(endPage > totalPage)	endPage = totalPage;
		
		
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("count", count);
		request.setAttribute("ss", ss);
		*/
		return "find.jsp";
	}
	
	@RequestMapping("food/find_ajax.do")
	public void food_ajax(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		String column = request.getParameter("column");
		if(column == null) {
			column = "address";
		}
		String ss = request.getParameter("ss"); // Search String
		if(ss == null) {
			ss = "마포";
		}
		String[] types = request.getParameterValues("type");
		
		int curPage = Integer.parseInt(page); // 현재 페이지
		final int ROWSIZE = 12; // 한 페이지에 12개씩 데이터 보여줌
		int start = (curPage*ROWSIZE)-ROWSIZE; // 데이터를 몇 번 부터 보여줘야하는지 정하는 것 
				
		Map map = new HashMap();
		map.put("start", start);
		map.put("ss", ss);
		map.put("column", column);
		map.put("fdArr", types);
		
		List<FoodVO> list = FoodDAO.foodFindData(map);
		int count = FoodDAO.foodFindCount(map);
		
		int totalPage = (int)(Math.ceil(count/(double)ROWSIZE)); // 전체 페이지 = 전체 데이터 갯수 / 한 페이지당 보여줄 갯수
		final int BLOCK = 10; // 페이징을 10개씩 1 2 3 4 5 6 7 8 9 10 >>
		
		int startPage = ((curPage-1) / BLOCK * BLOCK) + 1; // -1 하는 이유 : 10페이지인 경우 start페이지가 1부터여야하는데 11부터로 되어서 -1해줌 // curPage : 10 > ((9 / 10) * 10) + 1 = 1
		int endPage = ((curPage-1) / BLOCK * BLOCK) + BLOCK; // curPage : 10 > ((9/10) * 10 ) + 10 = 10
		
		if(endPage > totalPage)	endPage = totalPage;
		
		int i=0;
		JSONArray arr = new JSONArray();
		for(FoodVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("no", vo.getNo());
			obj.put("name", vo.getName());
			obj.put("poster", vo.getPoster());
			obj.put("address", vo.getAddress());
			obj.put("type", vo.getType());
						
			if(i==0) {
				obj.put("curPage", curPage);
				obj.put("totalPage", totalPage);
				obj.put("startPage", startPage);
				obj.put("endPage", endPage);
				obj.put("count", count);
			}
			arr.add(obj);
			i++;
		}
		
		try {
			//일반문자열, html : text/html, json전달 : text/plain
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(arr.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
