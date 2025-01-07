package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class NewCustomer extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfSurnames;
	private JTextField tfEmail;

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
		
		JLabel lblStoreId = new JLabel("TIENDA");
		lblStoreId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStoreId.setBounds(40, 122, 100, 13);
		contentPanel.add(lblStoreId);
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
			JButton btnNewAddress = new JButton("Nueva");
			btnNewAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNewAddress.setBounds(450, 208, 85, 21);
			contentPanel.add(btnNewAddress);
		}
		{
			JButton btnSelectAddress = new JButton("Exsistente");
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
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
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
