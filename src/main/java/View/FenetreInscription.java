package View;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.UtilitaireDB;
import Model.Agent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;

public class FenetreInscription extends JFrame {

	private JPanel contentPane;
	private JTextField pseudo;
	private JPasswordField confirmation;
	private JPasswordField passwordField;
	private JPasswordField mdp;
	private JButton btnInscription;
	private JTextField nom;
	private JTextField prenom;
	public static FenetreInscription frame;
	private JPanel panel;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FenetreInscription();
					System.out.println(frame);
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
	public FenetreInscription() {
		this.setIconImage(new ImageIcon("src/main/resources/logo.png").getImage());
		System.out.println(frame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 180, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(119, 11, 191, 149);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(10, 11, 46, 14);
		panel.add(lblPrenom);
		
		prenom = new JTextField();
		prenom.setBounds(95, 8, 86, 20);
		panel.add(prenom);
		prenom.setColumns(10);
		
		nom = new JTextField();
		nom.setBounds(95, 39, 86, 20);
		panel.add(nom);
		nom.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 42, 46, 14);
		panel.add(lblNom);
		
				JLabel lblPseudo = new JLabel("Pseudo");
				lblPseudo.setBounds(10, 67, 61, 14);
				panel.add(lblPseudo);
				
						pseudo = new JTextField();
						pseudo.setBounds(95, 70, 86, 20);
						panel.add(pseudo);
						pseudo.setColumns(10);
						
								JLabel lblMotDePasse = new JLabel("Mot de passe");
								lblMotDePasse.setBounds(10, 100, 84, 14);
								panel.add(lblMotDePasse);
								
										mdp = new JPasswordField();
										mdp.setBounds(95, 101, 86, 14);
										panel.add(mdp);
										
												JLabel lblConfirmation = new JLabel("Confirmation");
												lblConfirmation.setBounds(10, 125, 61, 14);
												panel.add(lblConfirmation);
												
														confirmation = new JPasswordField();
														confirmation.setBounds(95, 126, 86, 14);
														panel.add(confirmation);
														
														panel_1 = new JPanel();
														panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
														panel_1.setBounds(119, 165, 191, 37);
														contentPane.add(panel_1);
																panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
														
																btnInscription = new JButton("Inscription");
																panel_1.add(btnInscription);
																btnInscription.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																		if (mdp.getText().equals(confirmation.getText()))
																		{
																			try {
																				Agent agent = new Agent(nom.getText(),prenom.getText(),mdp.getText(),pseudo.getText(),null);
																				UtilitaireDB.insererAgent(agent);
																				FenetreInscription.frame.setVisible(false);
																				FenetreIdentification.frame = new FenetreIdentification();
																				FenetreIdentification.frame.setVisible(true);
																				
																			} catch (SQLException e1) {
																				// TODO Auto-generated catch block
																				e1.printStackTrace();
																			}
																		}
																		else
																		{
																			System.out.println("Mot de passe différent");
																		}
																	}
																});


	}
}
