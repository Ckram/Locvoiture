package View;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.management.ImmutableDescriptor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import Controller.UtilitaireDB;
import Controller.listModelClient;
import Controller.listModelVoitures;
import Controller.tableModelReservations;
import Model.Agent;
import Model.Client;
import Model.Contrat;
import Model.PermisConduire;
import Model.Voiture;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

public class FenetrePrincipal extends JFrame {
	private JPanel contentPane;
	private JTable tableResas;
	private JTable tableClients;
	private JTable tableVoitures;
	JDatePickerImpl datePickerFin;
	JDatePickerImpl datePicker ;
	JDatePickerImpl datePickerClient;
	JDatePickerImpl datePickerPermis;
	JComboBox<Voiture> listVoitureResa = null;
	JComboBox<Client> listClientResa = null;
	static Agent a;
	public static JFrame frame;
	private JTextField PrenomClient;
	private JTextField NomClient;
	private JTextField lieuNaissanceClient;
	private JTextField NumeroPermis;
	private JTextField LieuPermis;
	private JTextField TypePermis;
	private JTextField PlaqueVoiture;
	private JTextField marqueVoiture;
	private JTextField KmVoiture;
	private JTextField couleurVoiture;
	private JTextField prixVoiture;
	private JTextField KmFin;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePrincipal frame = new FenetrePrincipal();
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenetrePrincipal() {
		this.setIconImage(new ImageIcon("src/main/resources/logo.png").getImage());
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1145, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);
		UtilDateModel modelDateDebut = new UtilDateModel();
		JDatePanelImpl datePanelDebut = new JDatePanelImpl(modelDateDebut);
		datePicker = new JDatePickerImpl(datePanelDebut);
		UtilDateModel modelDateFin = new UtilDateModel();
		JDatePanelImpl datePanelFin = new JDatePanelImpl(modelDateFin);
		datePickerFin = new JDatePickerImpl(datePanelFin);
		UtilDateModel modelDateClient = new UtilDateModel();
		JDatePanelImpl datePanelClient = new JDatePanelImpl(modelDateClient);
		datePickerClient = new JDatePickerImpl(datePanelClient);
		datePickerClient.setSize(130, 35);
		datePickerClient.setLocation(76, 92);
		UtilDateModel modelDatePermis = new UtilDateModel();
		JDatePanelImpl datePanelPermis = new JDatePanelImpl(modelDatePermis);
		datePickerPermis = new JDatePickerImpl(datePanelPermis);
		datePickerPermis.setLocation(198, 53);
		datePickerPermis.setSize(130, 33);
		
		 
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(250, 235, 215));
		tabbedPane.setBounds(0, 0, 1129, 541);
		contentPane.add(tabbedPane);

		JPanel Reservations = new JPanel();
		Reservations.setBackground(new Color(210, 180, 140));
		tabbedPane.addTab("Reservations", null, Reservations, null);
		Reservations.setLayout(null);


		TableModel modeleResas = null;
		TableModel modeleClients =null;
		TableModel modeleVoiture =null;
		try {
			modeleResas = new tableModelReservations(UtilitaireDB.connexion(), "Contrat");
			modeleClients = new tableModelReservations(UtilitaireDB.connexion(), "Client");
			modeleVoiture = new tableModelReservations(UtilitaireDB.connexion(), "Voiture");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(740, 395, 338, 40);
		Reservations.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date utilDateDebut = (java.util.Date)datePicker.getModel().getValue();
				java.sql.Date sqlDateDebut = new java.sql.Date(utilDateDebut.getTime());
				java.util.Date utilDateFin = (java.util.Date)datePickerFin.getModel().getValue();
				java.sql.Date sqlDateFin = new java.sql.Date(utilDateFin.getTime());
				if (sqlDateFin.before(sqlDateDebut))
				{
					JOptionPane.showMessageDialog(frame, "Date de fin avant Date de debut");
				}
				else {
					UtilitaireDB.insererReservation(new Contrat((Client)listClientResa.getSelectedItem(), FenetrePrincipal.a, (Voiture)listVoitureResa.getSelectedItem(),sqlDateDebut, sqlDateFin, 0, 0, 0, 0));
					try {
						tableResas.setModel(new tableModelReservations(UtilitaireDB.connexion(), "Contrat"));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		panel_2.add(btnAjouter);

		JButton btnModifier = new JButton("Modifier");
		panel_2.add(btnModifier);

		JButton btnSupprimer = new JButton("Supprimer");
		panel_2.add(btnSupprimer);
		tableClients = new JTable(modeleClients);
		tableClients.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		tableClients.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tableVoitures = new JTable(modeleVoiture);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_3.setBounds(740, 33, 338, 226);
		Reservations.add(panel_3);
		panel_3.setLayout(null);

		


		listVoitureResa = new JComboBox<Voiture>();
		listVoitureResa.setModel(new listModelVoitures());
		listClientResa = new JComboBox<Client>();
		listClientResa.setBounds(118, 75, 210, 40);
		listClientResa.setModel(new listModelClient());


		listVoitureResa.setBounds(118, 24, 210, 40);
		
		panel_3.add(listVoitureResa);
		panel_3.add(listClientResa);
		datePicker.setBounds(22, 145, 130, 33);
		datePickerFin.setBounds(178, 145, 130, 33);
		panel_3.add(datePicker);
		panel_3.add(datePickerFin);
		
		JLabel lblVoiture = new JLabel("Voiture");
		lblVoiture.setBounds(10, 37, 46, 14);
		panel_3.add(lblVoiture);
		
		JLabel lblDateDebut = new JLabel("Date Debut");
		lblDateDebut.setBounds(57, 189, 63, 14);
		panel_3.add(lblDateDebut);
		
		JLabel lblDateFin = new JLabel("Date Fin");
		lblDateFin.setBounds(219, 189, 46, 14);
		panel_3.add(lblDateFin);
		
		JLabel lblClient = new JLabel("Client");
		lblClient.setBounds(10, 88, 46, 14);
		panel_3.add(lblClient);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_5.setBounds(740, 270, 338, 119);
		Reservations.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblKmFin = new JLabel("Km Fin");
		lblKmFin.setBounds(10, 22, 96, 14);
		panel_5.add(lblKmFin);
		
		KmFin = new JTextField();
		KmFin.setBounds(116, 19, 182, 20);
		panel_5.add(KmFin);
		KmFin.setColumns(10);
		
		JButton btnFacturer = new JButton("Facturer");
		btnFacturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(tableResas.getModel().getValueAt(tableResas.getSelectedRow(), 9));
				try {
					UtilitaireDB.contratExistant((String)tableResas.getModel().getValueAt(tableResas.getSelectedRow(), 9),Integer.parseInt(KmFin.getText()));
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnFacturer.setBounds(118, 67, 89, 23);
		panel_5.add(btnFacturer);
		tableResas = new JTable(modeleResas);
		tableResas.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		JScrollPane ScrollResas = new JScrollPane(tableResas,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ScrollResas.setBounds(10, 33, 707, 402);
		Reservations.add(ScrollResas);

		System.out.println(listVoitureResa.getModel().getSelectedItem());


		JPanel Clients = new JPanel();
		Clients.setBackground(new Color(210, 180, 140));
		tabbedPane.addTab("Clients", null, Clients, null);
		Clients.setLayout(null);

		JScrollPane ScrollClients = new JScrollPane(tableClients, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ScrollClients.setBounds(10, 33, 707, 402);
		Clients.add(ScrollClients);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 204, 153));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(740, 396, 338, 42);
		Clients.add(panel);

		JButton buttonAjouterClient = new JButton("Ajouter");
		buttonAjouterClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date date =(java.util.Date) datePickerPermis.getModel().getValue();
				java.sql.Date dateSql = new Date(date.getTime());
				java.util.Date dateNaissance =(java.util.Date) datePickerClient.getModel().getValue();
				java.sql.Date dateNaissanceSql = new Date(dateNaissance.getTime());
				
				PermisConduire p = new PermisConduire(Integer.parseInt(NumeroPermis.getText()),dateSql,LieuPermis.getText(),TypePermis.getText());
				UtilitaireDB.insererPermis(p);
				Client c = new Client(NomClient.getText(), PrenomClient.getText(), dateNaissanceSql, lieuNaissanceClient.getText(), p, null);
				UtilitaireDB.insererClient(c);
				try {
					tableClients.setModel(new tableModelReservations(UtilitaireDB.connexion(), "Client"));
					listClientResa.setModel(new listModelClient());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(buttonAjouterClient);

		JButton buttonModifierClient = new JButton("Modifier");
		panel.add(buttonModifierClient);

		JButton buttonSupprimerClient = new JButton("Supprimer");
		panel.add(buttonSupprimerClient);
		
		JPanel panelClientInfo = new JPanel();
		panelClientInfo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelClientInfo.setBounds(740, 33, 338, 200);
		Clients.add(panelClientInfo);
		panelClientInfo.setLayout(null);
		panelClientInfo.add(datePickerClient);
		
		PrenomClient = new JTextField();
		PrenomClient.setBounds(89, 30, 86, 20);
		panelClientInfo.add(PrenomClient);
		PrenomClient.setColumns(10);
		
		NomClient = new JTextField();
		NomClient.setBounds(89, 61, 86, 20);
		panelClientInfo.add(NomClient);
		NomClient.setColumns(10);
		
		JLabel lblPrenomClient = new JLabel("Prenom");
		lblPrenomClient.setBounds(24, 39, 46, 14);
		panelClientInfo.add(lblPrenomClient);
		
		JLabel lblNomClient = new JLabel("Nom");
		lblNomClient.setBounds(24, 64, 46, 14);
		panelClientInfo.add(lblNomClient);
		
		JLabel lblDateNaissance = new JLabel("Date Naissance");
		lblDateNaissance.setBounds(86, 126, 91, 14);
		panelClientInfo.add(lblDateNaissance);
		
		lieuNaissanceClient = new JTextField();
		lieuNaissanceClient.setBounds(120, 163, 86, 20);
		panelClientInfo.add(lieuNaissanceClient);
		lieuNaissanceClient.setColumns(10);
		
		JLabel lblLieuNaissance = new JLabel("Lieu Naissance");
		lblLieuNaissance.setBounds(10, 166, 86, 14);
		panelClientInfo.add(lblLieuNaissance);
		
		JLabel lblInformationsClient = new JLabel("Informations Client");
		lblInformationsClient.setBounds(120, 5, 111, 14);
		panelClientInfo.add(lblInformationsClient);
		
		JPanel InfoPermis = new JPanel();
		InfoPermis.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		InfoPermis.setBounds(740, 244, 338, 141);
		Clients.add(InfoPermis);
		InfoPermis.setLayout(null);
		InfoPermis.add(datePickerPermis);
		
		NumeroPermis = new JTextField();
		NumeroPermis.setBounds(106, 39, 86, 20);
		InfoPermis.add(NumeroPermis);
		NumeroPermis.setColumns(10);
		
		JLabel lblNumeroPermis = new JLabel("Numero Permis");
		lblNumeroPermis.setBounds(10, 39, 86, 14);
		InfoPermis.add(lblNumeroPermis);
		
		JLabel lblInformationsPermis = new JLabel("Informations Permis");
		lblInformationsPermis.setBounds(116, 11, 122, 14);
		InfoPermis.add(lblInformationsPermis);
		
		JLabel lblLieuPermis = new JLabel("Lieu");
		lblLieuPermis.setBounds(10, 60, 46, 14);
		InfoPermis.add(lblLieuPermis);
		
		LieuPermis = new JTextField();
		LieuPermis.setBounds(106, 60, 86, 20);
		InfoPermis.add(LieuPermis);
		LieuPermis.setColumns(10);
		
		JLabel lblTypePermis = new JLabel("Type Permis");
		lblTypePermis.setBounds(10, 81, 74, 14);
		InfoPermis.add(lblTypePermis);
		
		TypePermis = new JTextField();
		TypePermis.setBounds(106, 81, 86, 20);
		InfoPermis.add(TypePermis);
		TypePermis.setColumns(10);
		
		JLabel lblDateObtention = new JLabel("Date Obtention");
		lblDateObtention.setBounds(223, 87, 85, 14);
		InfoPermis.add(lblDateObtention);

		JPanel Voitures = new JPanel();
		Voitures.setBackground(new Color(210, 180, 140));
		tabbedPane.addTab("Voitures", null, Voitures, null);
		Voitures.setLayout(null);

		JScrollPane scrollVoitures = new JScrollPane(tableVoitures, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollVoitures.setBounds(10, 33, 707, 402);
		Voitures.add(scrollVoitures);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_1.setBounds(727, 392, 387, 43);
		Voitures.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton button = new JButton("Ajouter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilitaireDB.insererVoiture(new Voiture(PlaqueVoiture.getText(), marqueVoiture.getText(), Integer.parseInt(KmVoiture.getText()), couleurVoiture.getText(), Double.parseDouble(prixVoiture.getText()), null));
				try {
					tableVoitures.setModel(new tableModelReservations(UtilitaireDB.connexion(), "Voiture"));
					listVoitureResa.setModel(new listModelVoitures());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(button);

		JButton button_1 = new JButton("Modifier");
		panel_1.add(button_1);

		JButton button_2 = new JButton("Supprimer");
		panel_1.add(button_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_4.setBounds(727, 33, 387, 230);
		Voitures.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblPlaqueImm = new JLabel("Plaque Immatriculation");
		lblPlaqueImm.setBounds(10, 42, 108, 14);
		panel_4.add(lblPlaqueImm);
		
		PlaqueVoiture = new JTextField();
		PlaqueVoiture.setBounds(146, 39, 135, 20);
		panel_4.add(PlaqueVoiture);
		PlaqueVoiture.setColumns(10);
		
		JLabel lblMarque = new JLabel("Marque");
		lblMarque.setBounds(10, 77, 46, 14);
		panel_4.add(lblMarque);
		
		marqueVoiture = new JTextField();
		marqueVoiture.setBounds(146, 74, 135, 20);
		panel_4.add(marqueVoiture);
		marqueVoiture.setColumns(10);
		
		JLabel lblKilomtres = new JLabel("Kilom\u00E8tres");
		lblKilomtres.setBounds(10, 116, 108, 14);
		panel_4.add(lblKilomtres);
		
		KmVoiture = new JTextField();
		KmVoiture.setBounds(146, 113, 135, 20);
		panel_4.add(KmVoiture);
		KmVoiture.setColumns(10);
		
		JLabel lblCouleur = new JLabel("Couleur");
		lblCouleur.setBounds(10, 155, 108, 14);
		panel_4.add(lblCouleur);
		
		couleurVoiture = new JTextField();
		couleurVoiture.setBounds(146, 152, 135, 20);
		panel_4.add(couleurVoiture);
		couleurVoiture.setColumns(10);
		
		JLabel lblPrixJournalier = new JLabel("Prix journalier");
		lblPrixJournalier.setBounds(10, 194, 108, 14);
		panel_4.add(lblPrixJournalier);
		
		prixVoiture = new JTextField();
		prixVoiture.setBounds(146, 191, 135, 20);
		panel_4.add(prixVoiture);
		prixVoiture.setColumns(10);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);

		JMenuItem mntmOuvrir = new JMenuItem("Ouvrir");
		mnFichier.add(mntmOuvrir);

		JMenuItem mntmSauvegarder = new JMenuItem("Sauvegarder");
		mnFichier.add(mntmSauvegarder);
	}
}
