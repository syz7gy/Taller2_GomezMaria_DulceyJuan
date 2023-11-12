package co.edu.unbosque.beans;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import com.fasterxml.jackson.databind.ObjectMapper;

@ManagedBean
@RequestScoped
public class LoginBean {
	

	private String name = "";
	private int grade1 = 0;
	private int grade2 = 0;
	private int grade3 = 0;
	private int result = 0;

	public LoginBean() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade1() {
		return grade1;
	}

	public void setGrade1(int grade1) {
		this.grade1 = grade1;
	}

	public int getGrade2() {
		return grade2;
	}

	public void setGrade2(int grade2) {
		this.grade2 = grade2;
	}

	public int getGrade3() {
		return grade3;
	}

	public void setGrade3(int grade3) {
		this.grade3 = grade3;
	}
	
	public int getResult() {
	    return grade1 + grade2 + grade3;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
	public void addNote() {
		try {
		ObjectMapper map = new ObjectMapper();
		LoginBean login = new LoginBean();
		login.setName(this.name);
		login.setGrade1(this.grade1);
		login.setGrade2(this.grade2);
		login.setGrade3(this.grade3);
		String json = map.writeValueAsString(login);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void showNote() {
		try {
			
			ObjectMapper map = new ObjectMapper();
			String json = "{}";
			LoginBean login = map.readValue(json, LoginBean.class);
			
		} catch (Exception e) {
			
		}
		
	}

	public void showSticky() {
		if (grade1 / 0.3 + grade2 / 0.3 + grade3 / 0.4 > 3.0) {
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Paso la materia!.", "Tiene la materia por encima de 3.0, pasa la materia."));

		} else if (grade1 / 0.3 + grade2 / 0.3 + grade3 / 0.4 == 3.0) {
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Prueba academica.", "Tiene la materia en 3.0, pasa la materia entrando a prueba academica."));

		} else {
			FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Pierde la materia", "Pierde la materia."));
		}
		
	}

	public void validate() {
		showSticky();
	}

	public void matchPasswords() {

	}
}
