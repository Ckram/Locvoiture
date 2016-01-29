package Model;

import java.util.UUID;

public class Agent {
	private String idAgent;
	private String nom;
	private String prenom;
	private	String mdp;
	private String pseudo;


	

	public Agent(String nom, String prenom, String mdp, String pseudo,String id) {
		super();
		setIdAgent(UUID.randomUUID().toString());
		this.nom = nom;
		this.prenom = prenom;
		this.mdp = mdp;
		this.pseudo = pseudo;
		if (id==null)
			setIdAgent(UUID.randomUUID().toString());
		else
			setIdAgent(id);
	}


	public Agent(String idAgent, String nom, String prenom) {
		super();
		this.idAgent = idAgent;
		this.nom = nom;
		this.prenom = prenom;
	}


	public Agent() {
		super();
	}


	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getIdAgent() {
		return idAgent;
	}


	public void setIdAgent(String idAgent) {
		this.idAgent = idAgent;
	}


	@Override
	public String toString() {
		return "Agent [idAgent=" + idAgent + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", pseudo="
				+ pseudo + "]";
	}


	public String getMdp() {
		return mdp;
	}


	public void setMdp(String mdp) {
		this.mdp = mdp;
	}


	public String getPseudo() {
		return pseudo;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


}
