/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.st.qunar.order.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.st.qunar.order.entity.User;
import com.st.qunar.order.service.account.AccountService;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private AccountService userSerivce;

	@RequestMapping(method = RequestMethod.GET)
	public void list(Model model, User user) {
		System.out.println(user.getName());
		List<User> allUser = userSerivce.getAllUser();
		for (User u : allUser) {
			System.out.println(u.getLoginName());
		}
	}

}
