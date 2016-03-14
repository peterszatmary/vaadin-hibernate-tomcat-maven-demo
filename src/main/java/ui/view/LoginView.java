package ui.view;

import ui.EvidenceUI;
import org.hibernate.Session;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

import core.db.HibernateUtil;
import core.db.entity.Office;

import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LoginView extends VerticalLayout implements View {

	public LoginView() {
		setSizeFull();
		setSpacing(true);
		
		Label label = new Label("Enter your information below to log in.");
		TextField username = new TextField("Username");
		TextField password = new TextField("Password");
		
		
		addComponent(label);
		addComponent(username);
		addComponent(password);
		addComponent(loginButton());
		addComponent(registerButton());
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome! Please log in.");
	}
	private Button registerButton() {
		Button button = new Button("Register", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(EvidenceUI.REGVIEW);
			}
		});
		return button;
	}
	private Button loginButton() {
		Button button = new Button("Log In", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(EvidenceUI.MAINMANAGERVIEW);
				Session session = HibernateUtil.getSessionFactory().openSession();
				Office office = new Office();
				office.setName("munich");
				session.save(office);
				session.close();
			}
		});
		return button;
	}

}
