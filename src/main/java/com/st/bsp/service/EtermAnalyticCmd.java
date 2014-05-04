/**
 * 
 */
package com.st.bsp.service;

import java.io.IOException;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 * @author kxhu
 * 
 */
public class EtermAnalyticCmd implements IBspCmd {

	@Override
	public String execCmd(String cmd) {
		String exportContent = "";
		try {
			exportContent = Request.Post("http://115.182.7.207:8080/GetTn/Ec.asmx?op=sendCmd")
					.bodyForm(Form.form().add("username", "bjs410").add("password", "bjs410").add("cmd", cmd).build())
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("exportContent:" + exportContent);
		return exportContent;
	}

}
