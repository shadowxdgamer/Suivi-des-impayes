package mainapp;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;


import jtableRendering.TableActionCellEditor;
import jtableRendering.TableActionCellRenderer;
import jtableRendering.TableActionEvent;
import net.proteanit.sql.DbUtils;

import javax.swing.border.LineBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class ListesDesClientsPanel extends JPanel implements PanelActionListener {
	@Override
    public void onButtonPress(String clientInfo) {
        // Notify MainApp that a button is pressed in ListesDesClientsPanel
        if (mainAppListener != null) {
            mainAppListener.onPanelButtonPress(clientInfo);
        }
    }

    private MainAppListener mainAppListener;

    public void setMainAppListener(MainAppListener listener) {
        this.mainAppListener = listener;
    }
	
	
	Font customFont = new Font("Arial", Font.BOLD, 16);

	private static final long serialVersionUID = 1L;
	private JTextField IDclient;
	private JTextField jourGreater;
	private JTextField jourLower;
	private JTextField Agence;
	private JTextField Groupe;
	JTable tableCNC = new JTable();
    JScrollPane jScrollPane = new JScrollPane(tableCNC);
	Connection connection = null;
	public int count,count2;
	private String query;
	private JTextField cmnt;
	private JLabel FolderNumber = new JLabel("");
	private JLabel FolderNumberCC = new JLabel("");
	private JTextField IDclientCC;
	private JTextField jourGreaterCC;
	private JTextField jourLowerCC;
	private JTextField cmntCC;
	private JTable tableCC;
	//create new instance of CompteRendu
	CompteRendu compteRendu = new CompteRendu();

	/**
	 * Create the panel.
	 */
    public ListesDesClientsPanel() {
    	//Creat db object for connection
    	connection=dbConnection.dbConnector();
        initComponents();
        setupTableActions();
        

    }
	private void initComponents() {
        
		setBackground(new Color(255, 255, 255));
		
		JPanel listeClients = new JPanel();
		listeClients.setLayout(null);
		listeClients.setPreferredSize(new Dimension(1200, 226));
		listeClients.setBorder(new TitledBorder(new LineBorder(new Color(38, 38, 147), 2, true), "Liste des clients non contact\u00E9s", TitledBorder.LEFT, TitledBorder.TOP, customFont, new Color(0, 0, 128)));
		listeClients.setBackground(Color.WHITE);
		
		JLabel lblIDclient = new JLabel("ID client");
		lblIDclient.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIDclient.setBounds(12, 38, 72, 16);
		listeClients.add(lblIDclient);
		
		IDclient = new JTextField();
		IDclient.setColumns(10);
		IDclient.setBackground(new Color(225, 225, 225));
		IDclient.setBounds(157, 36, 238, 20);
		listeClients.add(IDclient);
		
		JLabel lblNombreDeJour = new JLabel("Nombre de jour > à");
		lblNombreDeJour.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombreDeJour.setBounds(12, 69, 135, 16);
		listeClients.add(lblNombreDeJour);
		
		jourGreater = new JTextField();
		jourGreater.setColumns(10);
		jourGreater.setBackground(new Color(225, 225, 225));
		jourGreater.setBounds(157, 68, 66, 20);
		listeClients.add(jourGreater);
		
		JLabel lblEt = new JLabel("et < à");
		lblEt.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEt.setBounds(242, 68, 52, 16);
		listeClients.add(lblEt);
		
		jourLower = new JTextField();
		jourLower.setColumns(10);
		jourLower.setBackground(new Color(225, 225, 225));
		jourLower.setBounds(329, 68, 66, 20);
		listeClients.add(jourLower);
		
		JLabel lblAgence = new JLabel("Agence");
		lblAgence.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAgence.setBounds(583, 38, 66, 16);
		listeClients.add(lblAgence);
		
		Agence = new JTextField();
		Agence.setColumns(10);
		Agence.setBackground(new Color(225, 225, 225));
		Agence.setBounds(661, 36, 96, 20);
		listeClients.add(Agence);
		
		JLabel lblGroupe = new JLabel("Groupe");
		lblGroupe.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGroupe.setBounds(812, 40, 52, 16);
		listeClients.add(lblGroupe);
		
		Groupe = new JTextField();
		Groupe.setColumns(10);
		Groupe.setBackground(new Color(225, 225, 225));
		Groupe.setBounds(885, 38, 96, 20);
		listeClients.add(Groupe);
		
		JPanel listeClientsContactees = new JPanel();
		listeClientsContactees.setBorder(new TitledBorder(new LineBorder(new Color(38, 38, 147), 2, true), "Liste Des clients contact\u00E9s", TitledBorder.LEFT, TitledBorder.TOP, customFont, new Color(0, 0, 128)));
		listeClientsContactees.setBackground(new Color(255, 255, 255));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(listeClientsContactees, 0, 0, Short.MAX_VALUE))
						.addComponent(listeClients, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 1287, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(listeClients, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listeClientsContactees, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
					.addContainerGap())
		);
		listeClientsContactees.setLayout(null);
		
		JLabel lblIDclientCC = new JLabel("ID client");
		lblIDclientCC.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIDclientCC.setBounds(12, 31, 53, 16);
		listeClientsContactees.add(lblIDclientCC);
		
		IDclientCC = new JTextField();
		IDclientCC.setColumns(10);
		IDclientCC.setBackground(new Color(225, 225, 225));
		IDclientCC.setBounds(83, 29, 308, 20);
		listeClientsContactees.add(IDclientCC);
		
		JLabel lblNombreDeJourCC = new JLabel("Nombre de jour > à");
		lblNombreDeJourCC.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombreDeJourCC.setBounds(12, 64, 135, 16);
		listeClientsContactees.add(lblNombreDeJourCC);
		
		jourGreaterCC = new JTextField();
		jourGreaterCC.setColumns(10);
		jourGreaterCC.setBackground(new Color(225, 225, 225));
		jourGreaterCC.setBounds(150, 62, 66, 20);
		listeClientsContactees.add(jourGreaterCC);
		
		JLabel lblEtCC = new JLabel("et < à");
		lblEtCC.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEtCC.setBounds(234, 64, 52, 16);
		listeClientsContactees.add(lblEtCC);
		
		jourLowerCC = new JTextField();
		jourLowerCC.setColumns(10);
		jourLowerCC.setBackground(new Color(225, 225, 225));
		jourLowerCC.setBounds(289, 62, 66, 20);
		listeClientsContactees.add(jourLowerCC);
		
		JLabel DossiersNombreCC = new JLabel("Nombre des dossiers");
		DossiersNombreCC.setFont(new Font("Tahoma", Font.BOLD, 13));
		DossiersNombreCC.setBounds(12, 221, 134, 16);
		listeClientsContactees.add(DossiersNombreCC);
		
		JLabel lblCommentaireCC = new JLabel("Commentaire");
		lblCommentaireCC.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCommentaireCC.setBounds(12, 249, 98, 16);
		listeClientsContactees.add(lblCommentaireCC);
		
		cmntCC = new JTextField();
		cmntCC.setColumns(10);
		cmntCC.setBackground(new Color(225, 225, 225));
		cmntCC.setBounds(105, 242, 1123, 30);
		listeClientsContactees.add(cmntCC);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 92, 1216, 117);
		listeClientsContactees.add(scrollPane);
		
		tableCC = new JTable();
		
		
		
		
		scrollPane.setViewportView(tableCC);
		tableCC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableCC.setRowHeight(35);
        tableCC.setSelectionBackground(new java.awt.Color(0, 0, 128));
        tableCC.setBackground(new Color(225, 225, 225));
        tableCC.setShowGrid(true);
        tableCC.setGridColor(new Color(0, 0, 128)); // Adjust the color as needed
        

        FolderNumberCC.setFont(new Font("Tahoma", Font.BOLD, 13));
        FolderNumberCC.setBounds(158, 221, 134, 16);
        listeClientsContactees.add(FolderNumberCC);

		setLayout(groupLayout);
		
		
		
		//local variables cause error
		//JTable tableCNC = new JTable();
        //JScrollPane jScrollPane = new JScrollPane(tableCNC);
        


        




        
        tableCNC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableCNC.setRowHeight(35);
        tableCNC.setSelectionBackground(new java.awt.Color(0, 0, 128));
        tableCNC.setBackground(new Color(225, 225, 225));
        tableCNC.setShowGrid(true);
        tableCNC.setGridColor(new Color(0, 0, 128)); // Adjust the color as needed
        jScrollPane.setViewportView(tableCNC);

        
        JLabel DossiersNombre = new JLabel("Nombre des dossiers");
        DossiersNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        //JLabel FolderNumber = new JLabel("");
        FolderNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        JLabel lblCommentaire = new JLabel("Commentaire");
        lblCommentaire.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        cmnt = new JTextField();
        cmnt.setColumns(10);
        cmnt.setBackground(new Color(225, 225, 225));

        // Combine both listeClients_1 and table layout
        GroupLayout gl_listeClients = new GroupLayout(listeClients);
        gl_listeClients.setHorizontalGroup(
        	gl_listeClients.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_listeClients.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_listeClients.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 1233, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_listeClients.createSequentialGroup()
        					.addComponent(lblIDclient)
        					.addGap(18)
        					.addComponent(IDclient, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_listeClients.createSequentialGroup()
        					.addComponent(DossiersNombre)
        					.addGap(18)
        					.addComponent(FolderNumber, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_listeClients.createSequentialGroup()
        					.addComponent(lblCommentaire, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(cmnt)))
        			.addContainerGap(32, Short.MAX_VALUE))
        );
        gl_listeClients.setVerticalGroup(
        	gl_listeClients.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_listeClients.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_listeClients.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblIDclient)
        				.addComponent(IDclient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(62)
        			.addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_listeClients.createParallelGroup(Alignment.BASELINE)
        				.addComponent(DossiersNombre)
        				.addComponent(FolderNumber, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_listeClients.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblCommentaire, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
        				.addComponent(cmnt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(77, Short.MAX_VALUE))
        );
        listeClients.setLayout(gl_listeClients);
     
        
        //update table once it loads
        filterDB();
        countRows();
		filterDBCC();
		countRowsCC();

		IDclient.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterDB();
				countRows();
				setupTableActions();
			}
		});
		Agence.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterDB();
				countRows();
				setupTableActions();
			}
		});
		Groupe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterDB();
				countRows();
				setupTableActions();
			}
		});
		jourGreater.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterDB();
				countRows();
				setupTableActions();
			}
		});
		jourLower.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterDB();
				countRows();
				setupTableActions();
			}
		});
		
		IDclientCC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterDBCC();
				countRowsCC();
				setupTableActions();
			}
		});
		jourGreaterCC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterDBCC();
				countRowsCC();
				setupTableActions();
			}
		});
		jourLowerCC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterDBCC();
				countRowsCC();
				setupTableActions();
				
			}

			
		});
		

	}
	
    	private void setupTableActions() {
            TableActionEvent event = new TableActionEvent() {
                @Override
                public void onPress(int row) {
                    // Get the value of the first column for the pressed row
                    Object value = tableCNC.getValueAt(row, 0);
                    System.out.println(value.toString());
                    onButtonPress(value.toString());
            	    add(compteRendu);
            	    revalidate();
            	    repaint();
 
                }
            };
            tableCNC.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRenderer());
            tableCNC.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(event));
            
            TableActionEvent eventCC = new TableActionEvent() {
                @Override
                public void onPress(int row) {
                    // Get the value of the first column for the pressed row
                    Object value = tableCC.getValueAt(row, 0);
                    if (value != null) {
                        System.out.println(value.toString());
                    } else {
                        System.out.println("Value is null");
                    }
                }
            };
            tableCC.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRenderer());
            tableCC.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(eventCC));

    }
    	private void filterDB() {
    		try {
                query = "SELECT A.CLI, A.NOM, A.AGENCE, A.GROUPE, A.MNT_IMP, A.NOMBRE_JOURS, A.ENGAGEMENT, A.SD, A.flgContacted FROM CLIENT_RECOUV A";
                
                // Add filtering conditions based on text field values
                query += " WHERE (? IS NULL OR A.CLI LIKE ?) AND (? IS NULL OR A.NOMBRE_JOURS > ?) AND (? IS NULL OR A.NOMBRE_JOURS < ?) AND (? IS NULL OR A.AGENCE = ?) AND (? IS NULL OR A.GROUPE = ?) AND (A.flgContacted = 0)";
                
                PreparedStatement pst = connection.prepareStatement(query);

                // Set parameters for filtering
                pst.setObject(1, IDclient.getText().isEmpty() ? null : "%" + IDclient.getText() + "%");
                pst.setObject(2, IDclient.getText().isEmpty() ? null : "%" + IDclient.getText() + "%");
                pst.setObject(3, jourGreater.getText().isEmpty() ? null : jourGreater.getText());
                pst.setObject(4, jourGreater.getText().isEmpty() ? null : jourGreater.getText());
                pst.setObject(5, jourLower.getText().isEmpty() ? null : jourLower.getText());
                pst.setObject(6, jourLower.getText().isEmpty() ? null : jourLower.getText());
                pst.setObject(7, Agence.getText().isEmpty() ? null : Agence.getText());
                pst.setObject(8, Agence.getText().isEmpty() ? null : Agence.getText());
                pst.setObject(9, Groupe.getText().isEmpty() ? null : Groupe.getText());
                pst.setObject(10, Groupe.getText().isEmpty() ? null : Groupe.getText());
                
                ResultSet rs = pst.executeQuery();
                tableCNC.setModel(DbUtils.resultSetToTableModel(rs));
                tableCNC.getColumnModel().getColumn(0).setHeaderValue("ID Client");
                tableCNC.getColumnModel().getColumn(1).setHeaderValue("Nom");
                tableCNC.getColumnModel().getColumn(2).setHeaderValue("Agence");
                tableCNC.getColumnModel().getColumn(3).setHeaderValue("Groupe");
                tableCNC.getColumnModel().getColumn(4).setHeaderValue("Mnt.impayee");
                tableCNC.getColumnModel().getColumn(5).setHeaderValue("Nombre Jours");
                tableCNC.getColumnModel().getColumn(6).setHeaderValue("Engagement");
                tableCNC.getColumnModel().getColumn(7).setHeaderValue("Solde débiteur");
                tableCNC.getColumnModel().getColumn(8).setHeaderValue("");
                // Set the column width for specific columns
                tableCNC.getColumnModel().getColumn(1).setPreferredWidth(250);

                // Set the horizontal alignment for specific columns
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                tableCNC.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                tableCNC.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                tableCNC.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                tableCNC.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
                DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
                tableCNC.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
                tableCNC.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
                tableCNC.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
                while (rs.next()) {
                    // Process each row if needed
                }
                rs.close();
                pst.close();
    		}catch(Exception ee)
    		{
    			JOptionPane.showMessageDialog(null, ee);
    		}
    		
    	}

    	private void countRows() {
    		try {
    		    // ...
    		    query = "SELECT COUNT(*) FROM CLIENT_RECOUV A";
    		    
    		    // Add filtering conditions based on text field values
    		    query += " WHERE (? IS NULL OR A.CLI LIKE ?) AND (? IS NULL OR A.NOMBRE_JOURS > ?) AND (? IS NULL OR A.NOMBRE_JOURS < ?) AND (A.flgContacted = 0)";
    	        
    		    PreparedStatement pstCount = connection.prepareStatement(query);

    		    // Set parameters for filtering
    		    pstCount.setObject(1, IDclient.getText().isEmpty() ? null : "%" + IDclient.getText() + "%");
    	        pstCount.setObject(2, IDclient.getText().isEmpty() ? null : "%" + IDclient.getText() + "%");
    		    pstCount.setObject(3, jourGreater.getText().isEmpty() ? null : jourGreater.getText());
    		    pstCount.setObject(4, jourGreater.getText().isEmpty() ? null : jourGreater.getText());
    		    pstCount.setObject(5, jourLower.getText().isEmpty() ? null : jourLower.getText());
    		    pstCount.setObject(6, jourLower.getText().isEmpty() ? null : jourLower.getText());

    		    
    		    ResultSet rsCount = pstCount.executeQuery();

    		    // Assuming the result set has only one column with the count
    		    if (rsCount.next()) {
    		        int count = rsCount.getInt(1);

    		        // Assuming FolderNumber is a JLabel, set its text to the count
    		        FolderNumber.setText(""+String.valueOf(count));
    		    }

    		    rsCount.close();
    		    pstCount.close();
    		} catch (Exception ee) {
    		    JOptionPane.showMessageDialog(null, ee);
    		}

    	}
    	
    	private void filterDBCC() {
    		try {
                query = "SELECT A.CLI, A.NOM, A.AGENCE, A.GROUPE, A.MNT_IMP, A.NOMBRE_JOURS, A.ENGAGEMENT, A.SD, A.flgContacted FROM CLIENT_RECOUV A";
                
                // Add filtering conditions based on text field values
                query += " WHERE (? IS NULL OR A.CLI LIKE ?) AND (? IS NULL OR A.NOMBRE_JOURS > ?) AND (? IS NULL OR A.NOMBRE_JOURS < ?) AND (A.flgContacted = 1)";
                
                PreparedStatement pst = connection.prepareStatement(query);

    		    // Set parameters for filtering
                pst.setObject(1, IDclientCC.getText().isEmpty() ? null : "%" + IDclientCC.getText() + "%");
    		    pst.setObject(2, IDclientCC.getText().isEmpty() ? null : "%" + IDclientCC.getText() + "%");
    	        pst.setObject(3, jourGreaterCC.getText().isEmpty() ? null : jourGreaterCC.getText());
    		    pst.setObject(4, jourGreaterCC.getText().isEmpty() ? null : jourGreaterCC.getText());
    		    pst.setObject(5, jourLowerCC.getText().isEmpty() ? null : jourLowerCC.getText());
    		    pst.setObject(6, jourLowerCC.getText().isEmpty() ? null : jourLowerCC.getText());
                
                ResultSet rs = pst.executeQuery();
                tableCC.setModel(DbUtils.resultSetToTableModel(rs));
                tableCC.getColumnModel().getColumn(0).setHeaderValue("ID Client");
                tableCC.getColumnModel().getColumn(1).setHeaderValue("Nom");
                tableCC.getColumnModel().getColumn(2).setHeaderValue("Agence");
                tableCC.getColumnModel().getColumn(3).setHeaderValue("Groupe");
                tableCC.getColumnModel().getColumn(4).setHeaderValue("Mnt.impayee");
                tableCC.getColumnModel().getColumn(5).setHeaderValue("Nombre Jours");
                tableCC.getColumnModel().getColumn(6).setHeaderValue("Engagement");
                tableCC.getColumnModel().getColumn(7).setHeaderValue("Solde débiteur");
                tableCC.getColumnModel().getColumn(8).setHeaderValue("");
                // Set the column width for specific columns
                tableCC.getColumnModel().getColumn(1).setPreferredWidth(250);

                // Set the horizontal alignment for specific columns
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                tableCC.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
                tableCC.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
                tableCC.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
                tableCC.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
                DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
                rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
                tableCC.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
                tableCC.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
                tableCC.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
                while (rs.next()) {
                    // Process each row if needed
                }

                rs.close();
                pst.close();
    		}catch(Exception ee)
    		{
    			JOptionPane.showMessageDialog(null, ee);
    		}
    	}
    	private void countRowsCC() {
    		try {
    		    // ...
    		    query = "SELECT COUNT(*) FROM CLIENT_RECOUV A";
    		    
    		    // Add filtering conditions based on text field values
    		    query += " WHERE (? IS NULL OR A.CLI LIKE ?) AND (? IS NULL OR A.NOMBRE_JOURS > ?) AND (? IS NULL OR A.NOMBRE_JOURS < ?) AND (A.flgContacted = 1)";
    	        
    		    PreparedStatement pstCount = connection.prepareStatement(query);

    		    // Set parameters for filtering
    		    pstCount.setObject(1, IDclientCC.getText().isEmpty() ? null : "%" + IDclientCC.getText() + "%");
    	        pstCount.setObject(2, IDclientCC.getText().isEmpty() ? null : "%" + IDclientCC.getText() + "%");
    		    pstCount.setObject(3, jourGreaterCC.getText().isEmpty() ? null : jourGreaterCC.getText());
    		    pstCount.setObject(4, jourGreaterCC.getText().isEmpty() ? null : jourGreaterCC.getText());
    		    pstCount.setObject(5, jourLowerCC.getText().isEmpty() ? null : jourLowerCC.getText());
    		    pstCount.setObject(6, jourLowerCC.getText().isEmpty() ? null : jourLowerCC.getText());
    		    
    		    ResultSet rsCount = pstCount.executeQuery();

    		    // Assuming the result set has only one column with the count
    		    if (rsCount.next()) {
    		        int count2 = rsCount.getInt(1);

    		        // Assuming FolderNumber is a JLabel, set its text to the count
    		        FolderNumberCC.setText(""+String.valueOf(count2));
    		    }

    		    rsCount.close();
    		    pstCount.close();
    		} catch (Exception ee) {
    		    JOptionPane.showMessageDialog(null, ee);
    		}

    	}

}
