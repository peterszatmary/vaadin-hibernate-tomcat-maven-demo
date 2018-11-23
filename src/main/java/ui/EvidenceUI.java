package ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import ui.view.*;

@SuppressWarnings("serial")
@Theme("evidence")
@Widgetset("war.MyAppWidgetset")
public class EvidenceUI extends UI {

	public Navigator navigator;
	public static final String REGVIEW = "register";
	public static final String MAINMANAGERVIEW = "mainManager";

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = EvidenceUI.class)
	public static class Servlet extends VaadinServlet {
	
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);
		ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(layout);
		navigator = new Navigator(UI.getCurrent(), viewDisplay);
		navigator.addView("", new LoginView());
		navigator.addView(MAINMANAGERVIEW, new MainManagerView());
	}
}