package fr.uvsq.pglp_9_9;

import java.util.ArrayList;

public class Groupe extends Form {

	private ArrayList<Form> forms;

	public Groupe(String nom, ArrayList<Form> forms) {
		super(nom);
		this.forms = forms;
	}

	public Groupe(String nom) {
		super(nom);
		this.forms = new ArrayList<Form>();
	}

	public ArrayList<Form> getForms() {

		return forms;
	}

	public void setForms(ArrayList<Form> forms) {
		this.forms = forms;
	}

	public void addForm(Form form) {
		this.forms.add(form);
	}

	@Override
	public void afficher() {
		for (Form form : forms) {
			form.afficher();
		}
	}

	@Override
	public void deplacer(int x, int y) {
		for (Form form : forms) {
			form.deplacer(x, y);
		}
	}

	@Override
	public void decaler(int x, int y) {
		for (Form form : forms) {
			form.decaler(x, y);
		}

	}

}
