package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Sakila VideoClub");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Javanese Text", Font.BOLD, 30));
		lblTitle.setBounds(139, 10, 426, 47);
		contentPane.add(lblTitle);
		
		JButton btnExit = new JButton("Salir");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnExit.setBounds(572, 312, 104, 40);
		contentPane.add(btnExit);
		
		JButton btnGestionDeClientes = new JButton("Gestión de clientes");
		btnGestionDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientView clientView = new ClientView();
				clientView.setVisible(true);
			}
		});
		btnGestionDeClientes.setForeground(new Color(255, 128, 128));
		btnGestionDeClientes.setBackground(new Color(255, 255, 255));
		btnGestionDeClientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGestionDeClientes.setBounds(37, 147, 190, 40);
		contentPane.add(btnGestionDeClientes);
	}
}
