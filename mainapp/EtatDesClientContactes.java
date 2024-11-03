package mainapp;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EtatDesClientContactes extends JPanel {

	private static final long serialVersionUID = 1L;
	Font customFont = new Font("Arial", Font.BOLD, 16);
	private JTextField IDclient;
	private JTextField agence;
	private JTextField groupe;
	private JTable tableCC;
	Connection connection = null;
	private String query;
	/**
	 * Create the panel.
	 */
	public EtatDesClientContactes() {
		//Creat db object for connection
		connection=dbConnection.dbConnector();
		 //
		setBackground(new Color(255, 255, 255));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 2, true), "Crit\u00E8re d'interrogation", TitledBorder.LEADING, TitledBorder.TOP, customFont, new Color(0, 0, 128)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		panel_1.setBackground(Color.WHITE);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		panel_1_1.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 1111, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE)
							.addGap(230)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)))
					.addGap(65))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1091, 279);
		panel_1_1.add(scrollPane);
		
		tableCC = new JTable();
		scrollPane.setViewportView(tableCC);
		tableCC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableCC.setRowHeight(35);
        tableCC.setSelectionBackground(new java.awt.Color(0, 0, 128));
        tableCC.setBackground(new Color(225, 225, 225));
        tableCC.setShowGrid(true);
        tableCC.setGridColor(new Color(0, 0, 128)); // Adjust the color as needed
		
		JButton btnExport = new JButton("");

		btnExport.setIcon(new ImageIcon(EtatDesClientContactes.class.getResource("/Resources/excel.png")));
		btnExport.setBounds(10, 11, 99, 69);
		panel_1.add(btnExport);
		panel.setLayout(null);
		
		JLabel lblIdClient = new JLabel("ID Client");
		lblIdClient.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdClient.setBounds(10, 21, 64, 16);
		panel.add(lblIdClient);
		
		IDclient = new JTextField();
		IDclient.setHorizontalAlignment(SwingConstants.LEFT);
		IDclient.setColumns(10);
		IDclient.setBackground(new Color(225, 225, 225));
		IDclient.setBounds(84, 20, 387, 20);
		panel.add(IDclient);
		
		JLabel lblAgence = new JLabel("Agence");
		lblAgence.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAgence.setBounds(10, 55, 64, 16);
		panel.add(lblAgence);
		
		agence = new JTextField();
		agence.setHorizontalAlignment(SwingConstants.LEFT);
		agence.setColumns(10);
		agence.setBackground(new Color(225, 225, 225));
		agence.setBounds(84, 51, 108, 20);
		panel.add(agence);
		
		JLabel lblGroupe = new JLabel("Groupe");
		lblGroupe.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGroupe.setBounds(239, 53, 64, 16);
		panel.add(lblGroupe);
		
		groupe = new JTextField();
		groupe.setHorizontalAlignment(SwingConstants.LEFT);
		groupe.setColumns(10);
		groupe.setBackground(new Color(225, 225, 225));
		groupe.setBounds(363, 51, 108, 20);
		panel.add(groupe);
		setLayout(groupLayout);
		filterDB();
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportToExcel(tableCC);
			}
		});
		IDclient.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterDB();
			}
		});
		agence.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterDB();
			}
		});
		groupe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterDB();
			}
		});
		

	}
	private void filterDB() {
		try {
            query = "SELECT A.CLI, A.NOM, A.GROUPE, A.AGENCE,A.NOMBRE_JOURS, A.MNT_IMP,A.CLASSE, A.ENGAGEMENT, A.SD FROM CLIENT_RECOUV A";
            
            // Add filtering conditions based on text field values
            query += " WHERE (? IS NULL OR A.CLI LIKE ?) AND (? IS NULL OR A.AGENCE = ?) AND (? IS NULL OR A.GROUPE = ?) AND (A.flgContacted = 1)";
            
            PreparedStatement pst = connection.prepareStatement(query);

            // Set parameters for filtering
            pst.setObject(1, IDclient.getText().isEmpty() ? null : "%" + IDclient.getText() + "%");
            pst.setObject(2, IDclient.getText().isEmpty() ? null : "%" + IDclient.getText() + "%");
            pst.setObject(3, agence.getText().isEmpty() ? null : agence.getText());
            pst.setObject(4, agence.getText().isEmpty() ? null : agence.getText());
            pst.setObject(5, groupe.getText().isEmpty() ? null : groupe.getText());
            pst.setObject(6, groupe.getText().isEmpty() ? null : groupe.getText());
            
            ResultSet rs = pst.executeQuery();
            tableCC.setModel(DbUtils.resultSetToTableModel(rs));
            tableCC.getColumnModel().getColumn(0).setHeaderValue("ID Client");
            tableCC.getColumnModel().getColumn(1).setHeaderValue("Nom");
            tableCC.getColumnModel().getColumn(2).setHeaderValue("Groupe");
            tableCC.getColumnModel().getColumn(3).setHeaderValue("Agence");
            tableCC.getColumnModel().getColumn(4).setHeaderValue("NB. Jours");
            tableCC.getColumnModel().getColumn(5).setHeaderValue("Mnt. impayés");
            tableCC.getColumnModel().getColumn(6).setHeaderValue("Classe");
            tableCC.getColumnModel().getColumn(7).setHeaderValue("Engagement");
            tableCC.getColumnModel().getColumn(8).setHeaderValue("Solde débiteur");
            // Set the column width for specific columns
            tableCC.getColumnModel().getColumn(1).setPreferredWidth(250);

            // Set the horizontal alignment for specific columns
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            tableCC.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
            tableCC.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            tableCC.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
            tableCC.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
            tableCC.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
            tableCC.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
            tableCC.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
            tableCC.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
            tableCC.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
            tableCC.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);

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
	public static void exportToExcel(JTable table) {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Specify a file to save");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
	    fileChooser.setFileFilter(filter);

	    int userSelection = fileChooser.showSaveDialog(null);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File fileToSave = fileChooser.getSelectedFile();
	        String filePath = fileToSave.getAbsolutePath();
	        
	    // Append ".xlsx" extension if not already present
	    if (!filePath.toLowerCase().endsWith(".xlsx")) {
	            filePath += ".xlsx";
	    }
	    

	        try (Workbook workbook = new XSSFWorkbook()) {
	            Sheet sheet = workbook.createSheet("Sheet 1");

	            // Header row
	            Row headerRow = sheet.createRow(0);
	            headerRow.createCell(0).setCellValue("ID Client");
	            headerRow.createCell(1).setCellValue("Nom");
	            headerRow.createCell(2).setCellValue("Groupe");
	            headerRow.createCell(3).setCellValue("Agence");
	            headerRow.createCell(4).setCellValue("NB. Jours");
	            headerRow.createCell(5).setCellValue("Mnt. impayés");
	            headerRow.createCell(6).setCellValue("Classe");
	            headerRow.createCell(7).setCellValue("Engagement");
	            headerRow.createCell(8).setCellValue("Solde débiteur");

	            // Data rows
	            for (int row = 0; row < table.getRowCount(); row++) {
	                Row dataRow = sheet.createRow(row + 1);
	                for (int col = 0; col < table.getColumnCount(); col++) {
	                    Object value = table.getValueAt(row, col);
	                    if (value != null) {
	                        dataRow.createCell(col).setCellValue(value.toString());
	                    }
	                }
	            }

	            // Write the workbook to the specified file path
	            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
	                workbook.write(fileOut);
	                JOptionPane.showMessageDialog(null, "L'exportation a réussi!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Erreur lors de l'exportation vers Excel", "Erreur", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

}
