package com.STL.stl_admin.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.lf5.viewer.LogFactor5InputDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.STL.stl_admin.dao.LoginDao;



@Controller
public class LoginController {

	@Autowired
	SqlSession sqlSession;

	private LoginDao loginDao;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public void loginCheck(
			@RequestParam(value="user_Id", defaultValue="") String userId, 
			@RequestParam(value="user_Pw", defaultValue="") String userPw,
			HttpServletRequest request,HttpSession session, HttpServletResponse response
			) {
		String script;
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		param.put("userPw", userPw);		
		loginDao = sqlSession.getMapper(LoginDao.class);
		int check = sqlSession.selectOne("loginCheck",param);
		if(check>0){
			session.setAttribute("loginId", userId);
			session.setMaxInactiveInterval(30 * 60);
		}else{
			
		}
	}

}
