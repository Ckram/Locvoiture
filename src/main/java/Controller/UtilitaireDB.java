package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Model.Agent;
import Model.Client;
import Model.Contrat;
import Model.PermisConduire;
import Model.Voiture;

public class UtilitaireDB {
	static public Connection c= null;
	static public Connection connexion (){



		try {
			if (c==null)
			{
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/baseDeDonnee.db");
			}else if (c.isClosed())
			{
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/baseDeDonnee.db");
			}

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return c;
	}

	static public void insererAgent(Agent a) throws SQLException
	{

		Connection c = UtilitaireDB.connexion();
		c.setAutoCommit(false);
		Statement s = c.createStatement();
		String requete = "INSERT INTO Agent (idAgent,prenomAgent,nomAgent,pseudo,mdp) VALUES ('"+a.getIdAgent()+"','"+a.getPrenom()+"','"+a.getNom()+"','"+a.getPseudo()+"','"+a.getMdp()+"');";

		s.executeUpdate(requete);
		s.close();
		c.commit();
		c.close();
	}

	static public Agent agentExistant (String pseudo, String mdp) throws SQLException
	{
		Connection c = UtilitaireDB.connexion();
		c.setAutoCommit(false);
		Statement s = c.createStatement();
		String requete = "SELECT * FROM Agent WHERE pseudo LIKE'"+pseudo+"' AND mdp LIKE'"+mdp+"';";

		ResultSet resultats = s.executeQuery(requete);
		System.out.println(resultats);
		if (resultats.next())
		{
			Agent a = new Agent(resultats.getString(3), resultats.getString(2), resultats.getString(5), resultats.getString(4),resultats.getString(1));
			System.out.println(a);
			return a;
		}
		s.close();
		c.commit();
		c.close();
		return null;
	}

	static public Contrat contratExistant (String id, int kmFinale) throws SQLException
	{
		Connection c = UtilitaireDB.connexion();
		c.setAutoCommit(false);
		Statement s = c.createStatement();
		String requete = "SELECT * FROM Contrat NATURAL JOIN Client NATURAL JOIN Voiture NATURAL JOIN Agent WHERE idContrat LIKE'"+id+"' ;";


		ResultSet resultats = s.executeQuery(requete);
		System.out.println(resultats);
		if (resultats.next())
		{
			for (int i =0 ;i<resultats.getMetaData().getColumnCount();i++)
			{
				System.out.println(resultats.getMetaData().getColumnName(i+1));
				System.out.println(resultats.getString(i+1));
			}
			Client client = new Client(resultats.getString("nom"), resultats.getString("prenom"), null, resultats.getString("idClient"));
			Voiture v = new Voiture(resultats.getString("plaqueImm"), resultats.getString("marque"), resultats.getInt("km"), resultats.getString("couleur"), resultats.getDouble("prixJour"), resultats.getString("idvoiture"));
			Agent a = new Agent(resultats.getString("idAgent"),resultats.getString("nomAgent"), resultats.getString("prenomAgent") );
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateDeb=null;
			java.util.Date dateFin=null;
			java.sql.Date sqlDateDeb;
			java.sql.Date sqlDateFin;
			try {
				dateDeb = format.parse(resultats.getString("dateDebut"));
				dateFin = format.parse(resultats.getString("dateFin"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDateDeb = new java.sql.Date(dateDeb.getTime());
			sqlDateFin = new java.sql.Date(dateFin.getTime());
			Contrat cont = new Contrat(client, a, v, sqlDateDeb, sqlDateFin, resultats.getInt("kmDebut"), kmFinale, 0, 0);
			cont.calculerPrix();
			System.out.println(cont);
			CreerPdf.creationPdf(cont);
					
			/*String requeteClient ="SELECT * FROM Client NATURAL JOIN C WHERE idClient LIKE'"+resultats.getString(1)+"' ;";
			Statement sClient = c.createStatement();
			ResultSet resultatsClient = sClient.executeQuery(requeteClient);
			resultatsClient.next();
			String requetePermis ="SELECT * FROM Permis WHERE idPermis LIKE'"+resultatsClient.getString(6)+"' ;";
			Statement sPermis = c.createStatement();
			ResultSet resultatsPermis = sPermis.executeQuery(requetePermis);
			resultatsPermis.next();
			Contrat cont = new Contrat(new Client(resultatsClient.getString("nom"), resultatsClient.getString("prenom"), new PermisConduire(resultatsPermis.getInt("num"),resultatsPermis.getString("typePermis"),resultatsClient.getString(6)),resultats.getString(1)),
					);
			System.out.println(cont);
			return cont;*/
		}
		s.close();
		c.commit();
		c.close();
		return null;
	}


	static public boolean insererReservation (Contrat cont)
	{
		Connection c = UtilitaireDB.connexion();
		try {

			c.setAutoCommit(false);
			Statement s = c.createStatement();
			System.out.println(cont);
			String requete = "INSERT INTO Contrat (idClient,idAgent,idVoiture,dateDebut,dateFin,kmDebut,kmFin,prixFinale,penalite,idContrat)"
					+ "VALUES ('"+cont.getClient().getIdClient()+"','"+cont.getAgent().getIdAgent()+"','"+cont.getVoiture().getIdVoiture()+"','"+cont.getDateDebut()+"','"+cont.getDateFin()+"','"+cont.getKmDebut()+"','"+cont.getKmFin()
					+"','"+cont.getPrixFinale()+"','"+cont.getPenalite()+"','"+cont.getIdContrat()+"');";

			s.executeUpdate(requete);
			s.close();
			c.commit();
			c.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	static public boolean insererClient (Client cl){
		Connection c = UtilitaireDB.connexion();
		try {
			c.setAutoCommit(false);
			Statement s = c.createStatement();
			String requete = "INSERT INTO Client (idClient,prenom,nom,dateNaissance,lieuNaissance,PermisConduire) VALUES ('"+cl.getIdClient()+"','"+cl.getPrenom()+"','"+cl.getNom()+"','"+cl.getDateNaissance()+"','"+cl.getLieuNaissance()+
					"','"+cl.getPermis().getIdPermis()+"');";

			s.executeUpdate(requete);
			s.close();
			c.commit();
			c.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	static public boolean insererPermis (PermisConduire p)
	{
		Connection c = UtilitaireDB.connexion();
		try {
			c.setAutoCommit(false);
			Statement s = c.createStatement();
			String requete = "INSERT INTO PermisConduire (num,date,lieu,typePermis,idPermis) VALUES ('"+p.getNum()+"','"+p.getDate()+"','"+p.getLieu()+"','"+p.getType()+"','"+p.getIdPermis()+"');";
			s.executeUpdate(requete);
			s.close();
			c.commit();
			c.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	static public boolean insererVoiture (Voiture v)
	{
		Connection c = UtilitaireDB.connexion();
		try {
			c.setAutoCommit(false);
			Statement s = c.createStatement();
			String requete = "INSERT INTO Voiture (idVoiture,plaqueImm,marque,km,couleur,prixJour) VALUES ('"+v.getIdVoiture()+"','"+v.getPlaqueImm()+"','"+v.getMarque()+"','"+v.getKm()+"','"+v.getCouleur()+"','"+v.getPrixJour()+"');";
			s.executeUpdate(requete);
			s.close();
			c.commit();
			c.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	static public Voiture [] recupererVoitures () throws SQLException
	{
		ArrayList<Voiture> voitures = new ArrayList<Voiture>();
		Connection c = UtilitaireDB.connexion();
		c.setAutoCommit(false);
		Statement s = c.createStatement();
		String requete = "SELECT * FROM Voiture;";
		ResultSet resultats = s.executeQuery(requete);
		System.out.println(resultats);
		while (resultats.next())
		{
			voitures.add(new Voiture(resultats.getString(2), resultats.getString(3), resultats.getInt(4), resultats.getString(5), resultats.getDouble(6),resultats.getString(1)));
		}
		s.close();
		c.commit();
		c.close();

		return voitures.toArray(new Voiture[voitures.size()]);
	}

	static public Client [] recupererClients () throws SQLException
	{
		ArrayList<Client> clients = new ArrayList<Client>();
		Connection c = UtilitaireDB.connexion();
		c.setAutoCommit(false);
		Statement s = c.createStatement();
		String requete = "SELECT * FROM Client;";

		ResultSet resultats = s.executeQuery(requete);
		System.out.println(resultats);
		while (resultats.next())
		{
			Statement sPermis =c.createStatement();
			String requetePermis ="SELECT * FROM PermisConduire WHERE idPermis LIKE '"+resultats.getString(6)+"';";
			ResultSet resultatPermis = sPermis.executeQuery(requetePermis);
			System.out.println(resultatPermis.next()+""+ resultats.getString(6)+" yolo "+resultats.getString(1));
			System.out.println("String "+resultats.getString(4));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date=null;
			java.util.Date datePermis=null;
			java.sql.Date sqlDate;
			java.sql.Date sqlDatePermis;
			try {
				date = format.parse(resultats.getString(4));
				datePermis = format.parse(resultatPermis.getString(2));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlDate = new java.sql.Date(date.getTime());
			sqlDatePermis = new java.sql.Date(datePermis.getTime());

			//System.out.println("DATE "+resultats.getDate(4));
			clients.add(new Client(resultats.getString(3), resultats.getString(2), sqlDate, resultats.getString(5), new PermisConduire(resultatPermis.getInt(1),sqlDatePermis , resultatPermis.getString(3), resultatPermis.getString(4)),resultats.getString(1)));
		}
		s.close();
		c.commit();
		c.close();

		return clients.toArray(new Client[clients.size()]);
	}

}


