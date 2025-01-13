package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.AddressDAO;
import dao.StoreDAO;
import dto.AddressDTO;
import dto.StoreDTO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Addresses extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Addresses dialog = new Addresses();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Addresses() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1022, 469);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 988, 356);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		// Disable write in table
		table.setDefaultEditor(Object.class, null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Elegir");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
							NewCustomer.tfAddress.setText(table.getValueAt(selectedRow, 0).toString());
							dispose();
                        } else {
                        	//Show error message
                        	JOptionPane.showMessageDialog(null, "Por favor, seleccione una dirección", "Error", JOptionPane.ERROR_MESSAGE);
                        }
					}
				});
				okButton.setFont(new Font("Tahoma", Font.BOLD, 14));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		initializeTable();
		loadAddresses();
	}
	
	private void initializeTable() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Dirección");
		model.addColumn("Dirección 2");
		model.addColumn("Distrito");
		model.addColumn("ID Ciudad");
		model.addColumn("Código Postal");
		model.addColumn("Teléfono");
		model.addColumn("Última Actualización");
		table.setModel(model);
	}
	
	public void loadAddresses() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0)
			model.removeRow(0);

		AddressDAO dao = new AddressDAO();
		ArrayList<AddressDTO> adresses = dao.selectAll();

		for (AddressDTO address : adresses) {
			model.addRow(new Object[] { address.getAddress_id(), address.getAddress(), address.getAddress2(), address.getDistrict(), address.getCity_id(), address.getPostal_code(), address.getPhone(), address.getLast_update() });
		}
	}
}
