package Model;

import java.sql.Date;
import java.util.UUID;

public class Client {
	private String nom;
	private String Prenom;
	private Date dateNaissance;
	private String lieuNaissance;
	private PermisConduire permis;
	private String idClient;
	
	
	
	public Client(String nom, String prenom, Date dateNaissance, String lieuNaissance, PermisConduire permis,String id) {
		super();
		
		this.nom = nom;
		Prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
		this.permis = permis;
		if (id==null)
			setIdClient(UUID.randomUUID().toString());
		else
			setIdClient(id);
	}
	
	public Client(String nom, String prenom, PermisConduire permis, String idClient) {
		super();
		this.nom = nom;
		Prenom = prenom;
		this.permis = permis;
		this.idClient = idClient;
	}

	public Client()
	{
		
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	public PermisConduire getPermis() {
		return permis;
	}
	public void setPermis(PermisConduire permis) {
		this.permis = permis;
	}
	public String getIdClient() {
		return idClient;
	}
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	@Override
	public String toString() {
		return nom+" "+Prenom;
	}
	

}
