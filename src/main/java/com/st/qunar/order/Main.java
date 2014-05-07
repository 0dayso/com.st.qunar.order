package com.st.qunar.order;

import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

	public static final int PORT = 9000;
	public static final String CONTEXT = "/qunar";

	public static void main(String[] args) throws Exception {
		/*
		 * // 设定Spring的profile1
		 * Profiles.setProfileAsSystemProperty(Profiles.PRODUCTION);
		 * 
		 * // 启动Jetty
		 * Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		 * // JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);
		 * 
		 * try {
		 * server.start();
		 * System.out.println("[INFO] Server running at http://localhost:" + PORT + CONTEXT);
		 * System.out.println("[HINT] Hit Enter to reload the application quickly");
		 * 
		 * // Thread.sleep(10 * 1000);
		 * // 5分钟请求导入一次
		 * // ((OrderExportService) SpringContextUtil.getBean("orderExportService")).run();
		 * } catch (Exception e) {
		 * e.printStackTrace();
		 * System.exit(-1);
		 * }
		 * 
		 * while (true) {
		 * char c = (char) System.in.read();
		 * if (c == '\n') {
		 * JettyFactory.reloadContext(server);
		 * }
		 * }
		 */
		String contextPath = "/order";
		int port = Integer.getInteger("port", 8080);

		Server server = createServer(contextPath, port);

		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}

	private static Server createServer(String contextPath, int port) {
		// use Eclipse JDT compiler
		System.setProperty("org.apache.jasper.compiler.disablejsr199", "true");

		Server server = new Server(port);
		server.setStopAtShutdown(true);

		ProtectionDomain protectionDomain = Main.class.getProtectionDomain();
		URL location = protectionDomain.getCodeSource().getLocation();
		String warFile = location.toExternalForm();

		WebAppContext context = new WebAppContext(warFile, contextPath);
		context.setServer(server);

		// 设置work dir,war包将解压到该目录，jsp编译后的文件也将放入其中。
		String currentDir = new File(location.getPath()).getParent();
		System.out.println(currentDir);
		File workDir = new File(currentDir, "work");
		context.setTempDirectory(workDir);

		server.setHandler(context);
		return server;
	}
}