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
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CustomerView extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomerView dialog = new CustomerView();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomerView() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 611, 434);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(252, 254, 237));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnNew = new JButton("Alta de clientes");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewCustomer dialog = new NewCustomer();
				dialog.setVisible(true);
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNew.setBounds(111, 150, 152, 29);
		contentPanel.add(btnNew);
		
		JButton btnDelete = new JButton("Baja de clientes");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(345, 150, 152, 29);
		contentPanel.add(btnDelete);
		
		JButton btnModify = new JButton("Modificación de clientes");
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModify.setBounds(96, 233, 181, 29);
		contentPanel.add(btnModify);
		
		JButton btnQueries = new JButton("Consultas de clientes");
		btnQueries.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQueries.setBounds(331, 233, 181, 29);
		contentPanel.add(btnQueries);
		
		JLabel lblMenuDeClientes = new JLabel("Menu de Clientes");
		lblMenuDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuDeClientes.setFont(new Font("Javanese Text", Font.BOLD, 30));
		lblMenuDeClientes.setBounds(86, 10, 426, 47);
		contentPanel.add(lblMenuDeClientes);
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
	}
}
