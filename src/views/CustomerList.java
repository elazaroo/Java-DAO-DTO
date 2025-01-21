package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDAO;
import dto.CustomerDTO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CustomerList extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomerList dialog = new CustomerList();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomerList() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 818, 482);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 784, 294);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblTitle = new JLabel("Lista de Clientes");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Javanese Text", Font.BOLD, 30));
		lblTitle.setBounds(187, 21, 426, 47);
		contentPanel.add(lblTitle);
		// Disable write in table
		table.setDefaultEditor(Object.class, null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
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
		loadCustomers();
	}
	
	private void initializeTable() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("store_id");
		model.addColumn("first_name");
		model.addColumn("last_name");
		model.addColumn("email");
		model.addColumn("address_id");
		model.addColumn("active");
		model.addColumn("create_date");
		model.addColumn("last_update");
		table.setModel(model);
	}
	
	public void loadCustomers() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() > 0)
			model.removeRow(0);

		CustomerDAO dao = new CustomerDAO();
		ArrayList<CustomerDTO> customers = dao.selectAll();
		
		for (CustomerDTO customer : customers) {
			model.addRow(new Object[] {customer.getCustomer_id(), customer.getStore_id(), customer.getFirst_name(), customer.getLast_name(), customer.getEmail(), customer.getAddress_id(), customer.getActive(), customer.getCreate_date(), customer.getLast_update()});
		}
	}
}
