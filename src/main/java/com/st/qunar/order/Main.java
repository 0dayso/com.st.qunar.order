package com.st.qunar.order;

import org.eclipse.jetty.server.Server;
import org.springside.modules.test.jetty.JettyFactory;
import org.springside.modules.test.spring.Profiles;

public class Main {

	public static final int PORT = 8080;
	public static final String CONTEXT = "/order";

	// public static final String[] TLD_JAR_NAMES = new String[] { "spring-webmvc", "springside-core" };

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
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
