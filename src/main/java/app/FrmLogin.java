package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtClave;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnIngresar.setBounds(324, 29, 89, 23);
		contentPane.add(btnIngresar);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 30, 161, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(10, 33, 102, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblClave = new JLabel("Clave :");
		lblClave.setBounds(10, 64, 102, 14);
		contentPane.add(lblClave);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(122, 61, 161, 20);
		contentPane.add(txtClave);
		
	}

	private String leerUsuario() {
		if (!txtUsuario.getText().matches("[A-Za-z0-9]+[@][A-Za-z0-9]+[.][a-z]{2,3}")) {
			JOptionPane.showMessageDialog(null, "usuario incorrecto");
			return null;
		}
		return txtUsuario.getText();
	}
	
	private String leerClave() {
		if (!txtClave.getText().matches("[A-Za-z0-9]+")) {
			JOptionPane.showMessageDialog(null, "clave incorrecto");
			return null;
		}
		return txtClave.getText();
	}
	void login() {
		String usuario = leerUsuario();  // txtUsuario.getText();
		String clave = leerClave();  // txtClave.getText();

		// validar
		if (usuario == null || clave == null) {
			return;
		}
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager manager = fabrica.createEntityManager();

		String sql = "select u from Usuario u where" +
		   " u.usr_usua = :xusr and u.cla_usua = :xcla";

		try {
			Usuario u = manager.createQuery(sql, Usuario.class).
					setParameter("xusr", usuario).
					setParameter("xcla", clave)
					.getSingleResult();

			FrmManteProd v = new FrmManteProd();
			v.setVisible(true);
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "usuario o clave incorrecto");
		}

		manager.close();
	}

	
	
}
