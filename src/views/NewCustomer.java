package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CustomerDAO;
import dto.CustomerDTO;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewCustomer extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfSurnames;
	private JTextField tfEmail;
	public static JTextField tfStore;
	public static JTextField tfAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewCustomer dialog = new NewCustomer();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewCustomer() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 663, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblStore = new JLabel("TIENDA");
		lblStore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStore.setBounds(40, 122, 100, 13);
		contentPanel.add(lblStore);
		{
			JLabel lblName = new JLabel("NOMBRE");
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblName.setBounds(40, 185, 100, 13);
			contentPanel.add(lblName);
		}
		{
			JLabel lblSurnames = new JLabel("APELLIDOS");
			lblSurnames.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblSurnames.setBounds(170, 185, 100, 13);
			contentPanel.add(lblSurnames);
		}
		{
			JLabel lblEmail = new JLabel("EMAIL");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblEmail.setBounds(330, 185, 100, 13);
			contentPanel.add(lblEmail);
		}
		{
			JLabel lblAddress = new JLabel("DIRECCIÓN");
			lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblAddress.setBounds(504, 185, 100, 13);
			contentPanel.add(lblAddress);
		}
		{
			JButton btnSelectStore = new JButton("Elegir tienda");
			btnSelectStore.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Stores dialog = new Stores();
                    dialog.setVisible(true);
				}
			});
			btnSelectStore.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnSelectStore.setBounds(106, 120, 125, 21);
			contentPanel.add(btnSelectStore);
		}
		
		tfName = new JTextField();
		tfName.setBounds(22, 208, 96, 19);
		contentPanel.add(tfName);
		tfName.setColumns(10);
		
		tfSurnames = new JTextField();
		tfSurnames.setColumns(10);
		tfSurnames.setBounds(155, 208, 96, 19);
		contentPanel.add(tfSurnames);
		{
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(275, 208, 146, 19);
			contentPanel.add(tfEmail);
		}
		{
			JButton btnNewAddress = new JButton("Crear");
			btnNewAddress.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NewAddress dialog = new NewAddress();
					dialog.setVisible(true);
				}
			});
			btnNewAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNewAddress.setBounds(450, 208, 85, 21);
			contentPanel.add(btnNewAddress);
		}
		{
			JButton btnSelectAddress = new JButton("Exsistente");
			btnSelectAddress.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Addresses dialog = new Addresses();
					dialog.setVisible(true);
				}
			});
			btnSelectAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnSelectAddress.setBounds(545, 208, 94, 21);
			contentPanel.add(btnSelectAddress);
		}
		{
			JLabel lblNuevoCliente = new JLabel("Nuevo Cliente");
			lblNuevoCliente.setHorizontalAlignment(SwingConstants.CENTER);
			lblNuevoCliente.setFont(new Font("Javanese Text", Font.BOLD, 30));
			lblNuevoCliente.setBounds(109, 22, 426, 47);
			contentPanel.add(lblNuevoCliente);
		}
		{
			tfStore = new JTextField();
			tfStore.setEditable(false);
			tfStore.setBounds(241, 121, 27, 19);
			contentPanel.add(tfStore);
			tfStore.setColumns(10);
		}
		{
			tfAddress = new JTextField();
			tfAddress.setEditable(false);
			tfAddress.setColumns(10);
			tfAddress.setBounds(528, 239, 27, 19);
			contentPanel.add(tfAddress);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Verify that all fields are filled
						if (tfStore.getText().equals("") || tfName.getText().equals("")
								|| tfSurnames.getText().equals("") || tfEmail.getText().equals("")
								|| tfAddress.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos","Error", JOptionPane.ERROR_MESSAGE);
						} else {
							// Save customer
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());
							CustomerDTO customer = new CustomerDTO(Integer.valueOf(tfStore.getText()), tfName.getText(), tfSurnames.getText(), tfEmail.getText(), Integer.valueOf(tfAddress.getText()), 1, timestamp, timestamp);
							CustomerDAO dao = new CustomerDAO();
							dao.insert(customer);
							JOptionPane.showMessageDialog(null, "Cliente guardado correctamente","Información", JOptionPane.INFORMATION_MESSAGE);
							dispose();
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
	}
}
