package com.st.qunar.order;

import org.eclipse.jetty.server.Server;
import org.springside.modules.test.jetty.JettyFactory;
import org.springside.modules.test.spring.Profiles;

public class Main {

	public static final int PORT = 8080;
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

			// 5分钟请求导入一次
			// new OrderExportService().run();
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
