package Model;

import java.util.UUID;

public class Voiture {
	private String idVoiture;
	private String plaqueImm;
	private String marque;
	private int km;
	private String couleur;
	private double prixJour;
	
	public Voiture() {
		super();
		}
	public Voiture(String plaqueImm, String marque, int km, String couleur, double prixJour, String id) {
		super();
		this.plaqueImm = plaqueImm;
		this.marque = marque;
		this.km = km;
		this.couleur = couleur;
		this.prixJour = prixJour;
		if (id==null)
			setIdVoiture(UUID.randomUUID().toString());
		else
			setIdVoiture(id);
	}
	
	public String getPlaqueImm() {
		return plaqueImm;
	}
	public void setPlaqueImm(String plaqueImm) {
		this.plaqueImm = plaqueImm;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public double getPrixJour() {
		return prixJour;
	}
	public void setPrixJour(double prixJour) {
		this.prixJour = prixJour;
	}
	public String getIdVoiture() {
		return idVoiture;
	}
	public void setIdVoiture(String idVoiture) {
		this.idVoiture = idVoiture;
	}
	@Override
	public String toString() {
		return plaqueImm +" "+ marque;
	}
	
	
}
