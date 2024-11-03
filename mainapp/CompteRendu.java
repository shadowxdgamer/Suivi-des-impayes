package mainapp;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;


public class CompteRendu extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tclientID;
	private JTextField name;
	private JTextField groupe;
	private JTextField lieuClient;
	private JLabel lblGroupe;
	private JTextField agence;
	private JTextField nomAgence;
	private JLabel lblAgence;
	private JLabel lblMntImp;
	private JTextField mntImp;
	private JLabel lblClasseRisque;
	private JTextField classeRisque;
	private JLabel lblsoldeDeb;
	private JTextField soldeDeb;
	private JLabel lblNbreImpay;
	private JTextField nombreImpaye;
	private JLabel lblNombreDeJours;
	private JTextField nombreJours;
	private JTextField totEnga;
	private JLabel lbltotEngagement;
	private JLabel lblencours;
	private JTextField encours;
	private JLabel lblTlphone;
	private JTextField telephoneNbr;
	private String clientID,query;
	
	Connection connection = null;
	private JPanel Promesse;
	private JPanel coordonnees;
	private JPanel facilite;
	private JPanel creance;
	private JPanel visite;
	private JPanel Promesse2;
	private JLabel lblMontant;
	private JTextField montant;
	private JTextField date;
	private JTextField lieu;
	private JPanel clientInjoignable;
	private JLabel lblApprciationGnrale;


	/**
	 * Create the panel.
	 */
	public CompteRendu() {
		//Creat db object for connection
		connection=dbConnection.dbConnector();
		 //

		setBackground(new Color(255, 255, 255));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(60, 60, 158), 2, true));
		panel.setLayout(null);
		
		JLabel client = new JLabel("Client");
		client.setBounds(37, 13, 53, 16);
		client.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(client);
		
		tclientID = new JTextField();
		tclientID.setEditable(false);
		tclientID.setColumns(10);
		tclientID.setBackground(new Color(225, 225, 225));
		tclientID.setBounds(133, 13, 111, 20);
		panel.add(tclientID);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(60, 60, 158), 2, true));
		panel_1.setBackground(Color.WHITE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		clientInjoignable = new JPanel();
		clientInjoignable.setLayout(null);
		clientInjoignable.setBorder(new LineBorder(new Color(60, 60, 158), 2, true));
		clientInjoignable.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(clientInjoignable, GroupLayout.PREFERRED_SIZE, 1069, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1005, GroupLayout.PREFERRED_SIZE)
								.addGap(74))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 1069, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(clientInjoignable, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		lblApprciationGnrale = new JLabel("Appréciation générale");
		lblApprciationGnrale.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblApprciationGnrale.setBounds(10, 11, 150, 16);
		clientInjoignable.add(lblApprciationGnrale);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "En attente de contact", "Mauvais numéro de téléphone", "Absence de réponse", "Occupé", "Non disponible actuellement"}));
		comboBox.setBackground(new Color(225, 225, 225));
		comboBox.setBounds(170, 9, 253, 22);
		clientInjoignable.add(comboBox);
		
		JLabel lblCompteRendu = new JLabel("Compte rendu");
		lblCompteRendu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCompteRendu.setBounds(10, 38, 150, 16);
		clientInjoignable.add(lblCompteRendu);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(225, 225, 225));
		textArea.setBounds(170, 35, 847, 45);
		clientInjoignable.add(textArea);
		
		Promesse = new JPanel();
		Promesse.setBackground(new Color(235, 235, 235));
		tabbedPane.addTab("Promesse de règlement", null, Promesse, null);
		tabbedPane.setEnabledAt(0, true);
		Promesse.setLayout(null);
		
		Promesse2 = new JPanel();
		Promesse2.setBackground(new Color(215, 215, 215));
		Promesse2.setBounds(0, 0, 990, 90);
		Promesse.add(Promesse2);
		Promesse2.setLayout(null);
		
		lblMontant = new JLabel("Montant");
		lblMontant.setBounds(10, 11, 64, 16);
		lblMontant.setFont(new Font("Tahoma", Font.BOLD, 13));
		Promesse2.add(lblMontant);
		
		montant = new JTextField();
		montant.setColumns(10);
		montant.setBackground(new Color(255, 255, 255));
		montant.setBounds(84, 10, 203, 20);
		Promesse2.add(montant);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(10, 38, 64, 16);
		Promesse2.add(lblDate);
		
		date = new JTextField();
		date.setColumns(10);
		date.setBackground(Color.WHITE);
		date.setBounds(84, 37, 203, 20);
		Promesse2.add(date);
		
		JLabel lblLieu = new JLabel("Lieu");
		lblLieu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLieu.setBounds(10, 63, 64, 16);
		Promesse2.add(lblLieu);
		
		lieu = new JTextField();
		lieu.setColumns(10);
		lieu.setBackground(Color.WHITE);
		lieu.setBounds(84, 62, 499, 20);
		Promesse2.add(lieu);
		
		coordonnees = new JPanel();
		tabbedPane.addTab("Nouvelles coordonnées", null, coordonnees, null);
		
		facilite = new JPanel();
		tabbedPane.addTab("Facilité de payement", null, facilite, null);
		
		creance = new JPanel();
		tabbedPane.addTab("Non reconnaissance de la créance", null, creance, null);
		
		visite = new JPanel();
		tabbedPane.addTab("Visite", null, visite, null);
		
		JRadioButton rdbtnPromesse = new JRadioButton("Promesse de règlement");
		rdbtnPromesse.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnPromesse.setBackground(new Color(255, 255, 255));
		rdbtnPromesse.setBounds(33, 17, 187, 23);
		panel_1.add(rdbtnPromesse);
		
		JRadioButton rdbtnClient = new JRadioButton("Client injoignable");
		rdbtnClient.setFont(new Font("Tahoma", Font.BOLD, 13));
		rdbtnClient.setBackground(Color.WHITE);
		rdbtnClient.setBounds(239, 18, 143, 23);
		panel_1.add(rdbtnClient);
		
		name = new JTextField();
		name.setEditable(false);
		name.setColumns(10);
		name.setBackground(new Color(225, 225, 225));
		name.setBounds(246, 13, 202, 20);
		panel.add(name);
		
		groupe = new JTextField();
		groupe.setEditable(false);
		groupe.setColumns(10);
		groupe.setBackground(new Color(225, 225, 225));
		groupe.setBounds(133, 40, 111, 20);
		panel.add(groupe);
		
		lieuClient = new JTextField();
		lieuClient.setEditable(false);
		lieuClient.setColumns(10);
		lieuClient.setBackground(new Color(225, 225, 225));
		lieuClient.setBounds(246, 40, 202, 20);
		panel.add(lieuClient);
		
		lblGroupe = new JLabel("Groupe");
		lblGroupe.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGroupe.setBounds(37, 40, 53, 16);
		panel.add(lblGroupe);
		
		agence = new JTextField();
		agence.setEditable(false);
		agence.setColumns(10);
		agence.setBackground(new Color(225, 225, 225));
		agence.setBounds(133, 67, 111, 20);
		panel.add(agence);
		
		nomAgence = new JTextField();
		nomAgence.setEditable(false);
		nomAgence.setColumns(10);
		nomAgence.setBackground(new Color(225, 225, 225));
		nomAgence.setBounds(246, 67, 202, 20);
		panel.add(nomAgence);
		
		lblAgence = new JLabel("Agence");
		lblAgence.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAgence.setBounds(37, 67, 53, 16);
		panel.add(lblAgence);
		
		lblMntImp = new JLabel("<html><u>Mnt Imp</html></u>");
		lblMntImp.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMntImp.setBounds(458, 13, 53, 16);
		panel.add(lblMntImp);
		
		mntImp = new JTextField();
		mntImp.setEditable(false);
		mntImp.setColumns(10);
		mntImp.setBackground(new Color(225, 225, 225));
		mntImp.setBounds(582, 13, 202, 20);
		panel.add(mntImp);
		
		lblClasseRisque = new JLabel("Classe risque");
		lblClasseRisque.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblClasseRisque.setBounds(808, 14, 92, 16);
		panel.add(lblClasseRisque);
		
		classeRisque = new JTextField();
		classeRisque.setEditable(false);
		classeRisque.setColumns(10);
		classeRisque.setBackground(new Color(225, 225, 225));
		classeRisque.setBounds(936, 13, 108, 20);
		panel.add(classeRisque);
		
		lblsoldeDeb = new JLabel("<html><u>Solde deb</html></u>");
		lblsoldeDeb.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblsoldeDeb.setBounds(458, 41, 82, 16);
		panel.add(lblsoldeDeb);
		
		soldeDeb = new JTextField();
		soldeDeb.setEditable(false);
		soldeDeb.setColumns(10);
		soldeDeb.setBackground(new Color(225, 225, 225));
		soldeDeb.setBounds(582, 39, 202, 20);
		panel.add(soldeDeb);
		
		lblNbreImpay = new JLabel("<html><u>Nbre impayé</html></u>");
		lblNbreImpay.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNbreImpay.setBounds(808, 40, 92, 16);
		panel.add(lblNbreImpay);
		
		nombreImpaye = new JTextField();
		nombreImpaye.setEditable(false);
		nombreImpaye.setColumns(10);
		nombreImpaye.setBackground(new Color(225, 225, 225));
		nombreImpaye.setBounds(936, 39, 108, 20);
		panel.add(nombreImpaye);
		
		lblNombreDeJours = new JLabel("Nombre de jours");
		lblNombreDeJours.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombreDeJours.setBounds(808, 68, 118, 16);
		panel.add(lblNombreDeJours);
		
		nombreJours = new JTextField();
		nombreJours.setEditable(false);
		nombreJours.setColumns(10);
		nombreJours.setBackground(new Color(225, 225, 225));
		nombreJours.setBounds(936, 67, 108, 20);
		panel.add(nombreJours);
		
		totEnga = new JTextField();
		totEnga.setEditable(false);
		totEnga.setColumns(10);
		totEnga.setBackground(new Color(225, 225, 225));
		totEnga.setBounds(582, 66, 202, 20);
		panel.add(totEnga);
		
		lbltotEngagement = new JLabel("<html><u>Tot engagement</html></u>");
		lbltotEngagement.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbltotEngagement.setBounds(458, 66, 114, 16);
		panel.add(lbltotEngagement);
		
		lblencours = new JLabel("<html><u>Encours</html></u>");
		lblencours.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblencours.setBounds(458, 93, 53, 16);
		panel.add(lblencours);
		
		encours = new JTextField();
		encours.setEditable(false);
		encours.setColumns(10);
		encours.setBackground(new Color(225, 225, 225));
		encours.setBounds(582, 93, 202, 20);
		panel.add(encours);
		
		lblTlphone = new JLabel("Téléphone");
		lblTlphone.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTlphone.setBounds(458, 120, 82, 16);
		panel.add(lblTlphone);
		
		telephoneNbr = new JTextField();
		telephoneNbr.setEditable(false);
		telephoneNbr.setColumns(10);
		telephoneNbr.setBackground(new Color(225, 225, 225));
		telephoneNbr.setBounds(582, 120, 462, 20);
		panel.add(telephoneNbr);
		setLayout(groupLayout);
		//set horizontal alignment
		tclientID.setHorizontalAlignment(SwingConstants.CENTER);
		groupe.setHorizontalAlignment(SwingConstants.CENTER);
		agence.setHorizontalAlignment(SwingConstants.CENTER);
		name.setHorizontalAlignment(SwingConstants.LEFT);
		mntImp.setHorizontalAlignment(SwingConstants.RIGHT);
		soldeDeb.setHorizontalAlignment(SwingConstants.RIGHT);
		totEnga.setHorizontalAlignment(SwingConstants.RIGHT);
		classeRisque.setHorizontalAlignment(SwingConstants.CENTER);
		nombreImpaye.setHorizontalAlignment(SwingConstants.CENTER);
		nombreJours.setHorizontalAlignment(SwingConstants.CENTER);
		telephoneNbr.setHorizontalAlignment(SwingConstants.LEFT);
		montant.setHorizontalAlignment(SwingConstants.RIGHT);
		
        // Groupe JCheckBoxes
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnPromesse);
        buttonGroup.add(rdbtnClient);
        
        rdbtnPromesse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //montant.setText(mntImp.getText());
                updatePromesse();
            }
        });

        // ActionListener for radio button 2
        rdbtnClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Option 2 selected");
            }
        });
        
        
		

	}
	
	public void updateClientDB() {
		try {
			query ="SELECT * FROM CLIENT_RECOUV A WHERE A.CLI = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, clientID);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				name.setText(rs.getString("nom"));
				groupe.setText(rs.getString("groupe"));
				agence.setText(rs.getString("agence"));
				mntImp.setText(rs.getString("mnt_imp"));
				soldeDeb.setText(rs.getString("sd"));
				totEnga.setText(rs.getString("engagement"));
				encours.setText(rs.getString("encours"));
				classeRisque.setText(rs.getString("classe"));
				nombreImpaye.setText(rs.getString("nbre_imp"));
				nombreJours.setText(rs.getString("nombre_jours"));
				telephoneNbr.setText(rs.getString("tel"));
				
				
			}
			rs.close();
			pst.close();
		}catch(Exception ee)
		{
			JOptionPane.showMessageDialog(null, ee);
		
	}

}
	public void updatePromesse() {
		try {
			query ="SELECT * FROM CLIENT_RECOUV A WHERE A.CLI = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, clientID);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				montant.setText(rs.getString("mnt_imp"));
				
				
			}
			rs.close();
			pst.close();
		}catch(Exception ee)
		{
			JOptionPane.showMessageDialog(null, ee);
		}
		
		
		
	}
	//this method is being called from MainApp
    public void updatePanelWithClientInfo(String clientInfo) {
        // Update the components in the panel based on the provided clientInfo
        clientID = clientInfo;
        tclientID.setText(clientID);
		updateClientDB();
    }
    public void save() {
		try {
			query ="Update CLIENT_RECOUV set flgContacted=1 WHERE CLI = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, clientID);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Client enregistré avec succès.", "Sauvegarde réussie", JOptionPane.INFORMATION_MESSAGE);

			pst.close();
		}catch(Exception ee)
		{
			JOptionPane.showMessageDialog(null, ee);
		
	}
    }
}

