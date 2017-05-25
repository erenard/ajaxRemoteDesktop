package org.httpRemoteDesktop;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHolder implements ApplicationContextAware {
	 
	/** Contexte Spring qui sera injecte par Spring directement */
	private static ApplicationContext context = null;
 

	/**
	* Méthode de ApplicationContextAware, qui sera appellée automatiquement par le conteneur
	*/
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		context = ctx;
	}
 
 
	/**
	 * Methode statique pour récupérer le contexte
	 */
	public static ApplicationContext getContext() {
		return context;
	}
 
}