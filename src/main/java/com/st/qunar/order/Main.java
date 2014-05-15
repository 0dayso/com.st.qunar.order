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
		Server server = createServer(CONTEXT, PORT);
		try {
			server.start();
			System.out.println("jetty server start");
			server.join();
			System.out.println("jetty server join");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}

	// jetty server run
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
		File workDir = new File(currentDir, "work");
		context.setTempDirectory(workDir);

		server.setHandler(context);
		return server;
	}
}