package org.httpRemoteDesktop.standalone;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class EmbeddedStart {

	public static void main(String[] args) {
		//Paramètres par défaut
		String contextPath = "/";
		//Paramètres
		if(args != null && args.length > 0) {
			contextPath = args[0];
		}
		Server server = new Server(80);
//		Connector connector = new SelectChannelConnector();
//		connector.setPort(8080);
//		server.setConnectors(new Connector[] { connector });
		WebAppContext webappcontext = new WebAppContext();
		webappcontext.setContextPath(contextPath);
		webappcontext.setWar("lib/httpRemoteDesktop.war");
		server.addHandler((Handler) webappcontext);
		server.setStopAtShutdown(true);
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
