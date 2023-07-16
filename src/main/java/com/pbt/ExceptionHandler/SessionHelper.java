package com.pbt.ExceptionHandler;


import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

	public void removeMessageFromSession() {

		try {

			System.out.println("Session message remove ");
			HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
					.getSession();
			session.removeAttribute("message");

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
