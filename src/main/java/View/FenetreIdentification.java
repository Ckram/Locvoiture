package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Controller.UtilitaireDB;
import Model.Agent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class FenetreIdentification extends JFrame {
	private JPanel contentPane;
	private JTextField Identifiant;
	private JPasswordField passwordField;
	public static FenetreIdentification frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreIdentification.frame = new FenetreIdentification();
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
	public FenetreIdentification() {
		this.setIconImage(new ImageIcon("src/main/resources/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 180, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);
		
		JPanel Logo = new JPanel();
		Logo.setBounds(94, 11, 144, 108);
		JLabel image = new JLabel( new ImageIcon("src/main/resources/logo.png"));
		//image.setIcon(new ImageIcon("src/main/resources/logo.png"));
		Logo.add(image);
		
		contentPane.add(Logo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(66, 130, 195, 141);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Identifiant");
		lblNewLabel.setBounds(10, 39, 86, 14);
		panel.add(lblNewLabel);
		
		
		Identifiant = new JTextField();
		Identifiant.setBounds(99, 36, 86, 20);
		panel.add(Identifiant);
		Identifiant.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(99, 82, 86, 20);
		panel.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe");
		lblNewLabel_1.setBounds(6, 85, 90, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_1.setBounds(66, 282, 195, 121);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnInscription = new JButton("Inscription");
		btnInscription.setBounds(32, 27, 124, 23);
		panel_1.add(btnInscription);
		
		JButton connectButton = new JButton("Se connecter");
		connectButton.setBounds(32, 69, 124, 23);
		panel_1.add(connectButton);
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Agent a;
					if (  (a = UtilitaireDB.agentExistant(Identifiant.getText(), passwordField.getText()))!=null)
					{
						JOptionPane.showMessageDialog(frame, "Vous etes connecte en tant que "+Identifiant.getText());
						FenetreIdentification.frame.setVisible(false);
						FenetrePrincipal f = new FenetrePrincipal();
						f.a = a;
						f.setVisible(true);
					}
					else 
					{
						JOptionPane.showMessageDialog(frame, "Utilisateur/Mot de passe inexistant");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FenetreIdentification.frame.setVisible(false);
				FenetreInscription.frame = new FenetreInscription();
				FenetreInscription.frame.setVisible(true);
			}
		});
	}
}
