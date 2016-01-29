package Model;

import java.sql.Date;
import java.util.UUID;

public class Contrat {

	private Client client;
	private Agent agent;
	private Voiture voiture;
	private Date dateDebut;
	private Date dateFin;
	private int kmDebut;
	private int kmFin;
	private double prixFinale;
	private double penalite;
	private String idContrat;
	
	public Contrat(Client client, Agent agent, Voiture voiture, Date dateDebut, Date dateFin, int kmDebut, int kmFin,
			double prixFinale, double penalite) {
		super();
		
		setIdContrat(UUID.randomUUID().toString());
		this.client = client;
		this.agent = agent;
		this.voiture = voiture;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.kmDebut = voiture.getKm();
		this.kmFin = kmFin;
		this.prixFinale = prixFinale;
		this.penalite = penalite;
	}
	


	public Contrat() {
		super();
	}

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Voiture getVoiture() {
		return voiture;
	}
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public int getKmDebut() {
		return kmDebut;
	}
	public void setKmDebut(int kmDebut) {
		this.kmDebut = kmDebut;
	}
	public int getKmFin() {
		return kmFin;
	}
	public void setKmFin(int kmFin) {
		this.kmFin = kmFin;
	}
	public double getPrixFinale() {
		return prixFinale;
	}
	public void setPrixFinale(double prixFinale) {
		this.prixFinale = prixFinale;
	}
	public double getPenalite() {
		return penalite;
	}
	public void setPenalite(double penalite) {
		this.penalite = penalite;
	}
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getIdContrat() {
		return idContrat;
	}

	public void setIdContrat(String idContrat) {
		this.idContrat = idContrat;
	}

	@Override
	public String toString() {
		return "Contrat [client=" + client + ", agent=" + agent + ", voiture=" + voiture + ", dateDebut=" + dateDebut
				+ ", dateFin=" + dateFin + ", kmDebut=" + kmDebut + ", kmFin=" + kmFin + ", prixFinale=" + prixFinale
				+ ", penalite=" + penalite + "]";
	}
	
	public void calculerPrix(){
		prixFinale = (dateFin.getDate()-dateDebut.getDate())*voiture.getPrixJour();
	}
	
	
	
	
	
}
