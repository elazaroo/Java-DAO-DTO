
package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dao.StoreDAO;
import dto.StoreDTO;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Stores extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Stores dialog = new Stores();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Stores() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(28, 35, 375, 156);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
				// Disable write in table
				table.setDefaultEditor(Object.class, null);
			}
		}
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
							NewCustomer.tfStore.setText(table.getValueAt(selectedRow, 0).toString());
							dispose();
                        } else {
                        	//Show error message
                        	JOptionPane.showMessageDialog(null, "Por favor, seleccione una tienda", "Error", JOptionPane.ERROR_MESSAGE);
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
		loadStores();
	}

	private void initializeTable() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID Tienda");
		model.addColumn("ID Encargado");
		model.addColumn("ID Dirección");
		model.addColumn("Ultima Actualización");
		table.setModel(model);
	}

	public void loadStores() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0)
			model.removeRow(0);

		StoreDAO dao = new StoreDAO();
		ArrayList<StoreDTO> stores = dao.selectAll();

		for (StoreDTO store : stores) {
			model.addRow(new Object[] { store.getStore_id(), store.getManager_staff_id(), store.getAddress_id(),
					store.getLast_update() });
		}
	}
}
