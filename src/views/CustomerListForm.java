package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.CountryDAO;
import dao.StaffDAO;
import dto.CountryDTO;
import dto.StaffDTO;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CustomerListForm extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public static JComboBox comboCountry;
	public static JComboBox comboSeller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomerListForm dialog = new CustomerListForm();
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomerListForm() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 476, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnAll = new JButton("Mostrar todos");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerList dialog = new CustomerList();
				dialog.setVisible(true);
			}
		});
		btnAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAll.setBounds(49, 159, 181, 29);
		contentPanel.add(btnAll);
		
		JButton btnByCountry = new JButton("Por país");
		btnByCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerListByCountry dialog = new CustomerListByCountry();
				dialog.setVisible(true);
			}
		});
		btnByCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnByCountry.setBounds(49, 234, 181, 29);
		contentPanel.add(btnByCountry);
		
		JButton btnBySeller = new JButton("Por vendedor");
		btnBySeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerListByStaff dialog = new CustomerListByStaff();
				dialog.setVisible(true);
			}
		});
		btnBySeller.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBySeller.setBounds(49, 310, 181, 29);
		contentPanel.add(btnBySeller);
		
		comboCountry = new JComboBox();
		comboCountry.setBounds(254, 240, 111, 21);
		contentPanel.add(comboCountry);
		
		comboSeller = new JComboBox();
		comboSeller.setBounds(254, 316, 111, 21);
		contentPanel.add(comboSeller);
		
		JLabel lblTitle = new JLabel("Lista de Customeres");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Javanese Text", Font.BOLD, 30));
		lblTitle.setBounds(10, 33, 442, 47);
		contentPanel.add(lblTitle);
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
		loadCountries();
		loadSellers();
	}
	
	public void loadCountries() {
		CountryDAO dao = new CountryDAO();
		ArrayList<CountryDTO> countries = dao.selectAll();
		
		for (CountryDTO country : countries) {
			comboCountry.addItem(country.getCountry());
		}
	}
	
	public void loadSellers() {
		StaffDAO dao = new StaffDAO();
		ArrayList<StaffDTO> sellers = dao.selectAll();
		
		for (StaffDTO seller : sellers) {
			comboSeller.addItem(seller.getUsername());
		}
	}
}
