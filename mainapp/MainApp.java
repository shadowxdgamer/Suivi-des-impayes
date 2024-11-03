package mainapp;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;


import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;




class CustomIconTreeCellRenderer extends DefaultTreeCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Define your custom icons
    private ImageIcon mainIcon = new ImageIcon(MainApp.class.getResource("/Resources/icons/admins.png"));
    private ImageIcon adminIcon = new ImageIcon(MainApp.class.getResource("/Resources/icons/house.png"));
    private ImageIcon recouvrementIcon = new ImageIcon(MainApp.class.getResource("/Resources/icons/house.png"));
    private ImageIcon defaultIcon = new ImageIcon(MainApp.class.getResource("/Resources/icons/node.png"));

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        // Call the default implementation to get the JLabel for the cell
        JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        // Check the node's text and set the appropriate custom icon
        if (value.toString().equals("RISQUE & RECOUVREMENT")) {
            label.setIcon(mainIcon);
        } else if (value.toString().equals("ADMINISTRATION")) {
            label.setIcon(adminIcon);
        } else if (value.toString().equals("Recouvrement")) {
            label.setIcon(recouvrementIcon);
        } else {
            label.setIcon(defaultIcon);
        }

        return label;
    }
}

public interface PanelActionListener {
    void onButtonPress(String clientInfo);  // You can adjust the parameters based on your requirements
}
public interface MainAppListener {
    void onPanelButtonPress(String clientInfo);  // You can adjust the parameters based on your requirements
}









public class MainApp extends JFrame implements MainAppListener {
	 @Override
	    public void onPanelButtonPress(String clientInfo) {
	        // Handle the button press event from ListesDesClientsPanel
	        // You can perform any actions or updates here
	        System.out.println("Button pressed in " + clientInfo);
	        clientID = clientInfo;
	        compteRendu();
	 }
	 

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String name,matriculenumber;
    JTree tree = new JTree();
    JPanel MainPanel = new JPanel();
    JPanel JtreePanel = new JPanel();
    JLabel titleNode = new JLabel("");
	Font customFont = new Font("Arial", Font.BOLD, 16);
	private String clientID;
	CompteRendu compteRendu = new CompteRendu();
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    // Pass the actual name and matriculenumber values here
					MainApp frame = new MainApp("","");
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
	public MainApp(String name,String matriculenumber) {
		setTitle("Suivi des impayés");
		//set look n feel
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
		    ex.printStackTrace();
		}
		
        this.name = name;
        this.matriculenumber = matriculenumber;

        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 479);
		setMinimumSize(new Dimension(450, 300));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(95, 117, 147));
		menuBar.setForeground(new Color(0, 0, 0));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Guide utilisateur");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Help");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Fenêtre");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Changer utilisateur");
		mnNewMenu_1.add(mntmNewMenuItem_1);

		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Fermer");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

        // Add an icon to the custom title bar
        ImageIcon icon = new ImageIcon(Authentication.class.getResource("/Resources/icon2_20x20.png"));
        
        JPanel topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(new Color(41, 56, 76), 2, true));
        contentPane.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BorderLayout(0, 0));
        
        // Create title bar
        JPanel titleBar = new JPanel();
        topPanel.add(titleBar, BorderLayout.NORTH);
        titleBar.setBackground(new Color(41, 56, 76));
        JLabel iconLabel = new JLabel(icon);
        
        // Use a layout manager for the title bar panel
        titleBar.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        titleBar.add(iconLabel);
        
        JButton btnsave = new JButton("");
        btnsave.setIcon(new ImageIcon(MainApp.class.getResource("/Resources/icons/save.png")));
        btnsave.setContentAreaFilled(false);
        btnsave.setOpaque(false);


        titleBar.add(btnsave);
        
        JButton btnback = new JButton("");
        btnback.setIcon(new ImageIcon(MainApp.class.getResource("/Resources/icons/back.png")));
        btnback.setOpaque(false);
        btnback.setContentAreaFilled(false);
        titleBar.add(btnback);
                
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setPreferredSize(new Dimension(200, 75));
        topPanel.add(panel, BorderLayout.SOUTH);
        
        JLabel logoBank = new JLabel("");
        logoBank.setIcon(new ImageIcon(MainApp.class.getResource("/Resources/LogoAmenBank100x40.png")));
        
        JLabel matricule = new JLabel(matriculenumber);
        
        JLabel nom_matricule = new JLabel(name);
        
        JLabel logoMatricule = new JLabel("");
        logoMatricule.setIcon(new ImageIcon(MainApp.class.getResource("/Resources/icons/house.png")));
        
        JLabel logoNom = new JLabel("");
        logoNom.setIcon(new ImageIcon(MainApp.class.getResource("/Resources/icons/admins.png")));
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(MainApp.class.getResource("/Resources/icons/time.jpg")));
      
        //set today's date
        JLabel date = new JLabel("31/12/9999");
        date.setText(String.valueOf(today.format(formatter)));
        
        //NODE TITLE PARAM
        titleNode.setForeground(new Color(0, 0, 128));
        titleNode.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
        titleNode.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(logoBank)
        			.addGap(38)
        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        				.addComponent(logoMatricule)
        				.addComponent(logoNom, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(nom_matricule, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(matricule, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(titleNode, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        			.addGap(51)
        			.addComponent(lblNewLabel_3)
        			.addGap(18)
        			.addComponent(date)
        			.addGap(30))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(21)
        					.addComponent(logoBank, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        						.addComponent(logoMatricule)
        						.addComponent(matricule))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        						.addComponent(logoNom, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        						.addComponent(nom_matricule)))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(23)
        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblNewLabel_3)
        						.addComponent(date)
        						.addComponent(titleNode, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        

        JtreePanel.setBorder(new LineBorder(new Color(1, 0, 128), 4));
        JtreePanel.setBackground(new Color(255, 255, 255));
        contentPane.add(JtreePanel, BorderLayout.WEST);
        

        tree.setModel(new DefaultTreeModel(
        	    new DefaultMutableTreeNode("RISQUE & RECOUVREMENT") {
        	        /**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					{
        	            DefaultMutableTreeNode node_1;
        	            DefaultMutableTreeNode node_2;
        	            DefaultMutableTreeNode node_3;
        	            DefaultMutableTreeNode node_4;
        	            node_1 = new DefaultMutableTreeNode("ADMINISTRATION");
        	            node_1.add(new DefaultMutableTreeNode("test"));
        	            add(node_1);
        	            node_1 = new DefaultMutableTreeNode("RECOUVREMENT");
        	            node_2 = new DefaultMutableTreeNode("Recouvrement Commercial");
        	            node_3 = new DefaultMutableTreeNode("Suivi des clients impayés");
        	            node_4 = new DefaultMutableTreeNode(" Clients à contacter");
        	            node_4.add(new DefaultMutableTreeNode("Listes des clients à contacter"));
        	            node_3.add(node_4);
        	            node_4 = new DefaultMutableTreeNode("Visualisation");
        	            node_4.add(new DefaultMutableTreeNode("Etat des clients contactés"));
        	            node_3.add(node_4);
        	            node_2.add(node_3);
        	            node_1.add(node_2);
        	            add(node_1);
        	        }
        	    }
        	));
        JtreePanel.add(tree);

             
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

                    if (selectedNode != null) {
                        // Handle double-click on the selected node
                        String nodeName = selectedNode.toString();
                        handleDoubleClick(nodeName);
                    }
                }
            }
        });

        
        
        // Set a custom cell renderer to display custom icons
        tree.setCellRenderer(new CustomIconTreeCellRenderer());
        
        JPanel footer = new JPanel();
        footer.setBorder(new LineBorder(new Color(1, 0, 128), 4, true));
        footer.setBackground(new Color(255, 255, 255));
        contentPane.add(footer, BorderLayout.SOUTH);
        footer.setPreferredSize(new Dimension(200, 80));
        
        JLabel Version = new JLabel("Version 1.0.0.0");
        Version.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
        Version.setForeground(new Color(0, 0, 128));
        
        JLabel DateOfCreation = new JLabel("18/01/2024");
        DateOfCreation.setForeground(new Color(0, 0, 128));
        DateOfCreation.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
        
        JLabel Copyright = new JLabel("Copyright Amen Bank");
        Copyright.setForeground(new Color(0, 0, 128));
        Copyright.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
        GroupLayout gl_footer = new GroupLayout(footer);
        gl_footer.setHorizontalGroup(
        	gl_footer.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_footer.createSequentialGroup()
        			.addGap(31)
        			.addComponent(Version)
        			.addGap(54)
        			.addComponent(DateOfCreation, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
        			.addGap(32)
        			.addComponent(Copyright, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(163, Short.MAX_VALUE))
        );
        gl_footer.setVerticalGroup(
        	gl_footer.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_footer.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_footer.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_footer.createParallelGroup(Alignment.BASELINE)
        					.addComponent(DateOfCreation, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        					.addComponent(Copyright, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
        				.addComponent(Version))
        			.addContainerGap(39, Short.MAX_VALUE))
        );
        footer.setLayout(gl_footer);
        
        
        MainPanel.setBorder(new LineBorder(new Color(1, 0, 128), 4, true));
        MainPanel.setBackground(new Color(255, 255, 255));
        contentPane.add(MainPanel, BorderLayout.CENTER);
        MainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 5));
        
        // Add the scroll pane to the contentPane
        JScrollPane mainPanelScrollPane = new JScrollPane(MainPanel);
        contentPane.add(mainPanelScrollPane, BorderLayout.CENTER);
        // Add the scroll pane to the jtree breaks the loading of interfaces needs to be fixed
        //JScrollPane mainjtreescrollpane = new JScrollPane(tree);
        //contentPane.add(mainjtreescrollpane, BorderLayout.WEST);
	
	
        btnback.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (titleNode.getText() ==  "Compte Rendu") {
        			backBtn();
        			loadListesDesClientsAContacterInterface();
        		}
        		else {
        			backBtn();
        		}
        	}
        });
        btnsave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(titleNode.getText() == "Compte Rendu") {

        			compteRendu.save();

        		}
        		
        	}
        });
	}
	private void handleDoubleClick(String nodeName) {
	    switch (nodeName) {
	        case "Listes des clients à contacter":
	            // Load the interface for "Listes des clients à contacter" in MainPanel
	            loadListesDesClientsAContacterInterface();
	            titleNode.setText("Listes des clients à contacter");
	            break;
	        case "Etat des clients contactés":
	            // Load the interface for "Etat des clients contactés" in MainPanel
	        	titleNode.setText("Etat des clients contactés");
	            loadEtatDesClientsContactesInterface();
	            break;
	        
	        default:
	            break;
	    }
	}
	private void loadListesDesClientsAContacterInterface() {
	    // Create an instance of ListesDesClientsPanel
	    ListesDesClientsPanel listesDesClientsPanel = new ListesDesClientsPanel();
	    listesDesClientsPanel.setMainAppListener(this);
    	contentPane.remove(JtreePanel);
	    MainPanel.removeAll();
	    titleNode.setText("Listes des clients à contacter");
	    MainPanel.add(listesDesClientsPanel);
	    MainPanel.revalidate();
	    MainPanel.repaint();
	}

	private void loadEtatDesClientsContactesInterface() {
		EtatDesClientContactes etatDesClientsContactes = new EtatDesClientContactes();
	    contentPane.remove(JtreePanel);
	    MainPanel.removeAll();
	    MainPanel.add(etatDesClientsContactes);
	    MainPanel.revalidate();
	    MainPanel.repaint();
	}
	
	private void compteRendu() {
	    //CompteRendu compteRendu = new CompteRendu(); declared it as classlevel for the save button to work
	    
	    MainPanel.removeAll();
	    titleNode.setText("Compte Rendu");
	    MainPanel.add(compteRendu);
	    compteRendu.updatePanelWithClientInfo(clientID);
	    MainPanel.revalidate();
	    MainPanel.repaint();
	}
	public void backBtn(){
		titleNode.setText("");
    	MainPanel.removeAll();
    	contentPane.add(JtreePanel, BorderLayout.WEST);
        MainPanel.revalidate();
        MainPanel.repaint();
	}
	
	
}
