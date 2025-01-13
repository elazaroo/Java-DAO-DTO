package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import dao.AddressDAO;
import dto.AddressDTO;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class NewAddress extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public static JTextField tfCity;
	private JTextField tfAddress;
	private JTextField tfAddress2;
	private JTextField tfDistrict;
	private JTextField tfPostalCode;
	private JTextField tfPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewAddress dialog = new NewAddress();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewAddress() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 615, 369);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNuevaDireccion = new JLabel("Nueva Dirección");
		lblNuevaDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevaDireccion.setFont(new Font("Javanese Text", Font.BOLD, 30));
		lblNuevaDireccion.setBounds(81, 10, 426, 47);
		contentPanel.add(lblNuevaDireccion);
		
		JLabel lblAddress = new JLabel("DIRECCIÓN:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(10, 109, 100, 13);
		contentPanel.add(lblAddress);
		
		JLabel lblAddress2 = new JLabel("DIRECCIÓN 2:");
		lblAddress2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress2.setBounds(10, 157, 100, 13);
		contentPanel.add(lblAddress2);
		
		JLabel lblDistrict = new JLabel("DISTRITO:");
		lblDistrict.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDistrict.setBounds(10, 212, 100, 13);
		contentPanel.add(lblDistrict);
		
		JLabel lblcity = new JLabel("CIUDAD:");
		lblcity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblcity.setBounds(10, 263, 100, 13);
		contentPanel.add(lblcity);
		
		tfCity = new JTextField();
		tfCity.setEditable(false);
		tfCity.setColumns(10);
		tfCity.setBounds(224, 262, 27, 19);
		contentPanel.add(tfCity);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(120, 108, 96, 19);
		contentPanel.add(tfAddress);
		
		tfAddress2 = new JTextField();
		tfAddress2.setColumns(10);
		tfAddress2.setBounds(120, 156, 96, 19);
		contentPanel.add(tfAddress2);
		
		tfDistrict = new JTextField();
		tfDistrict.setColumns(10);
		tfDistrict.setBounds(120, 211, 96, 19);
		contentPanel.add(tfDistrict);
		
		JButton btnSelectCity = new JButton("Exsistente");
		btnSelectCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cities dialog = new Cities();
				dialog.setVisible(true);
			}
		});
		btnSelectCity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSelectCity.setBounds(120, 260, 94, 21);
		contentPanel.add(btnSelectCity);
		
		JLabel lblPostalCode = new JLabel("CODIGO POSTAL:");
		lblPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPostalCode.setBounds(314, 109, 129, 13);
		contentPanel.add(lblPostalCode);
		
		JLabel lblPhone = new JLabel("TELEFONO:");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhone.setBounds(314, 159, 129, 13);
		contentPanel.add(lblPhone);
		
		tfPostalCode = new JTextField();
		tfPostalCode.setColumns(10);
		tfPostalCode.setBounds(453, 108, 96, 19);
		contentPanel.add(tfPostalCode);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(453, 156, 96, 19);
		contentPanel.add(tfPhone);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Check if all fields are filled
						if (tfAddress.getText().isEmpty() || tfAddress2.getText().isEmpty()
								|| tfDistrict.getText().isEmpty() || tfPostalCode.getText().isEmpty()
								|| tfPhone.getText().isEmpty()) {
							// Show error message
							JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());
							AddressDTO address = new AddressDTO(tfAddress.getText(), tfAddress2.getText(), tfDistrict.getText(),
									Integer.parseInt(tfCity.getText()), tfPostalCode.getText(), tfPhone.getText(),
									timestamp);
							// Save address
							AddressDAO addressDAO = new AddressDAO();
							addressDAO.insert(address);
							JOptionPane.showMessageDialog(null, "Dirección guardada correctamente", "Información",
									JOptionPane.INFORMATION_MESSAGE);
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
