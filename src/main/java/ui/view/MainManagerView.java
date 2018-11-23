package ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class MainManagerView extends VerticalLayout implements View {

	 public MainManagerView() {
		ComboBox officeComboBox = new ComboBox("Office");
		ComboBox projectComboBox = new ComboBox("Project");
		ComboBox studentComboBox = new ComboBox("Student");
		setSizeFull();
		
		addComponent(officeComboBox);
		addComponent(projectComboBox);
		addComponent(studentComboBox);
		addComponent(enterStudentProjectButton());
	}
	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Showing view: Main - Manager!");
	}
	private Button enterStudentProjectButton() {
		Button button = new Button("Enter student's profile", new Button.ClickListener() {
			/**
			 * empty for simplicity.
			 * @param event
			 */
			@Override
			public void buttonClick(ClickEvent event) {
				
			}
		});
		return button;
	}
}
