package com.st.qunar.order;

import org.eclipse.jetty.server.Server;
import org.springside.modules.test.jetty.JettyFactory;
import org.springside.modules.test.spring.Profiles;

import com.st.qunar.order.service.OrderExportService;
import com.st.qunar.order.web.util.SpringContextUtil;

public class Main {

	public static final int PORT = 9000;
	public static final String CONTEXT = "/qunar";

	public static void main(String[] args) throws Exception {
		// 设定Spring的profile1
		Profiles.setProfileAsSystemProperty(Profiles.PRODUCTION);

		// 启动Jetty
		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		// JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);

		try {
			server.start();
			System.out.println("[INFO] Server running at http://localhost:" + PORT + CONTEXT);
			System.out.println("[HINT] Hit Enter to reload the application quickly");

			Thread.sleep(10 * 1000);
			// 5分钟请求导入一次
			((OrderExportService) SpringContextUtil.getBean("orderExportService")).run();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

		while (true) {
			char c = (char) System.in.read();
			if (c == '\n') {
				JettyFactory.reloadContext(server);
			}
		}
	}
}