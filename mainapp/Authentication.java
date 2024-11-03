package mainapp;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Authentication extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField matricule;
	private JPasswordField matriculepswrd;
	private String query,name,matriculenumber;
	public int count,count2;
	private int mouseX, mouseY;
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentication frame = new Authentication();
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
	public Authentication() {
		//Creat db object for connection
		connection=dbConnection.dbConnector();
		//
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Authentication.class.getResource("/Resources/icon2.png")));
		setTitle("Ouverture d'une Session ...");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 490);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(95, 117, 147), 3, true));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Remove the default window decorations
        setUndecorated(true);

        // Create a custom title bar panel with a blue background color
        JPanel titleBar = new JPanel();
        titleBar.setBorder(new LineBorder(new Color(41, 56, 76), 2, true));
        titleBar.setBackground(new Color(41, 56, 76));
        titleBar.setBounds(0, 0, getWidth(), 20);

        // Add a title label to the custom title bar
        JLabel titleLabel = new JLabel("Ouverture d'une Session ...");
        titleLabel.setBounds(35, 2, 152, 16);
        titleLabel.setForeground(Color.WHITE);
        
        // Set horizontal alignment to left for the title bar text
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
         // Add an icon to the custom title bar
        ImageIcon icon = new ImageIcon(Authentication.class.getResource("/Resources/icon2_20x20.png"));
        titleBar.setLayout(null);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(5, 0, 20, 20);
        titleBar.add(iconLabel);
        titleBar.add(titleLabel);
        
        // Add mouse listener for dragging Get X,Y coordinated of mouse
        titleBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        //Drag mouse logic and setting new location of Window
        titleBar.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = e.getXOnScreen() - mouseX;
                int newY = e.getYOnScreen() - mouseY;
                setLocation(newX, newY);
            }
        });

        // Adding custom titlebar to Main content pane
        getContentPane().add(titleBar);


		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(284, 179, 399, 167);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Authentification ...");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(21, 11, 161, 25);
		panel.add(lblNewLabel);
		
		JLabel lblMatricule = new JLabel("Matricule");
		lblMatricule.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMatricule.setBounds(21, 47, 139, 25);
		panel.add(lblMatricule);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMotDePasse.setBounds(21, 114, 139, 25);
		panel.add(lblMotDePasse);
		
		//properties for matricule text field
		matricule = new JTextField();

		matricule.setBackground(new Color(235, 235, 235));
		matricule.setHorizontalAlignment(SwingConstants.CENTER);
		matricule.setFont(new Font("Tahoma", Font.PLAIN, 16));
		matricule.setBounds(165, 47, 193, 25);
		matricule.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.add(matricule);
		matricule.setColumns(10);
		
		//properties for matriculepswrd password field
		matriculepswrd = new JPasswordField();
		
		matriculepswrd.setBackground(new Color(235, 235, 235));
		matriculepswrd.setHorizontalAlignment(SwingConstants.CENTER);
		matriculepswrd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		matriculepswrd.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		matriculepswrd.setBounds(165, 114, 193, 25);
		panel.add(matriculepswrd);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 245));
		panel_1.setBorder(new LineBorder(new Color(179, 190, 203)));
		panel_1.setBounds(68, 154, 636, 269);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel accLoginLogo = new JLabel("");
		accLoginLogo.setIcon(new ImageIcon(Authentication.class.getResource("/Resources/accLogin.png")));
		accLoginLogo.setBounds(24, 21, 170, 156);
		panel_1.add(accLoginLogo);
		
		JButton btnConnexion = new JButton("Connexion");
		
		
		//login check connection with enter key press
		matricule.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            // Call the login logic when Enter key is pressed
		            handleLogin();
		        }
		    }
		});
		
		//login check connection with enter key press
		matriculepswrd.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            // Call the login logic when Enter key is pressed
		            handleLogin();
		        }
		    }
		});
		
		// Logic to check Connexion with button
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call login logic when the button is prsesed
				handleLogin();
			}
		});
		

		btnConnexion.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConnexion.setBounds(514, 210, 100, 35);
		panel_1.add(btnConnexion);
		
		//Exit Program
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        System.exit(0);
			}
		});
		btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnQuitter.setBounds(405, 210, 99, 35);
		panel_1.add(btnQuitter);
		
		JLabel bankLogo = new JLabel("");
		bankLogo.setIcon(new ImageIcon(Authentication.class.getResource("/Resources/logo_bank.png")));
		bankLogo.setBounds(454, 69, 250, 73);
		contentPane.add(bankLogo);
		
		JLabel Background = new JLabel("");
		Background.setIcon(new ImageIcon(Authentication.class.getResource("/Resources/background.png")));
		Background.setBounds(10, 26, 723, 452);
		contentPane.add(Background);
	}
	
	
	//handle login logic
	private void handleLogin(){
		
		try {
			query ="select * from UTILISATEUR where usr_matricule = ? and usr_motdepasse = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, matricule.getText());
			pst.setString(2, String.valueOf(matriculepswrd.getPassword()));
			ResultSet rs=pst.executeQuery();
			count = 0;
			while(rs.next()) {
				count++;
				name = rs.getString("usr_nomprenom");
				matriculenumber = rs.getString("usr_matricule");
			}
			if(count == 1)
			{
				JOptionPane.showMessageDialog(Authentication.this, "Bienvenue "+name, "Authentification Réussie", JOptionPane.INFORMATION_MESSAGE);
	            MainApp mainApp = new MainApp(name,matriculenumber);
	            mainApp.setVisible(true);
	            setVisible(false);

			}
			else if(count > 1) {
				JOptionPane.showMessageDialog(Authentication.this, "Le mot de passe ou le matricule est déjà utilisé. Veuillez choisir un autre.", "Enregistrement Échoué", JOptionPane.WARNING_MESSAGE);

			}
			else {
				JOptionPane.showMessageDialog(Authentication.this, "Le mot de passe est incorrect. Veuillez réessayer.", "Authentification Échouée", JOptionPane.ERROR_MESSAGE);
			}
			rs.close();
			pst.close();
		}catch(Exception ee)
		{
			JOptionPane.showMessageDialog(null, ee);
		}
	}

}
