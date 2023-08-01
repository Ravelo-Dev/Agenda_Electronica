package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Informacion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Informacion frame = new Informacion();
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
	public Informacion() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 256, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Pnl_main_info = new JPanel();
		Pnl_main_info.setBackground(SystemColor.activeCaption);
		Pnl_main_info.setBounds(0, 0, 240, 357);
		contentPane.add(Pnl_main_info);
		Pnl_main_info.setLayout(null);
		
		JLabel icon_info = new JLabel("");
		icon_info.setIcon(new ImageIcon(Informacion.class.getResource("/Img/usuario (1).png")));
		icon_info.setBounds(88, 11, 68, 88);
		Pnl_main_info.add(icon_info);
		
		JLabel lbl_perfil = new JLabel("P E R F I L");
		lbl_perfil.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_perfil.setBounds(88, 101, 84, 14);
		Pnl_main_info.add(lbl_perfil);
		
		JLabel lbl_nombre = new JLabel("NOMBRE");
		lbl_nombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_nombre.setBounds(92, 149, 100, 14);
		Pnl_main_info.add(lbl_nombre);
		
		JLabel lbl_matricula = new JLabel("MATRICULA");
		lbl_matricula.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_matricula.setBounds(83, 209, 100, 14);
		Pnl_main_info.add(lbl_matricula);
		
		JLabel lbl_carrera = new JLabel("CARRERA");
		lbl_carrera.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_carrera.setBounds(92, 272, 100, 14);
		Pnl_main_info.add(lbl_carrera);
		
		JLabel D1 = new JLabel("Marcos M. Ravelo");
		D1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		D1.setBounds(73, 174, 154, 14);
		Pnl_main_info.add(D1);
		
		JLabel D2 = new JLabel("2022-1947");
		D2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		D2.setBounds(88, 233, 139, 14);
		Pnl_main_info.add(D2);
		
		JLabel D3 = new JLabel("Desarrollo de Software");
		D3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		D3.setBounds(62, 297, 165, 14);
		Pnl_main_info.add(D3);
	}
}
