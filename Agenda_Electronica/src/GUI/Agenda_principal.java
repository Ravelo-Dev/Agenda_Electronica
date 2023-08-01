package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import javax.swing.JScrollPane;

import Config.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Toolkit;


public class Agenda_principal extends JFrame {
	Conexion con = new Conexion();
	Connection Cn;
	Statement St;
	ResultSet Rs;
	DefaultTableModel ModelTable;
	int ID;
	

	public JPanel contentPane;
	public JTextField Txt_ID;
	public JTextField Txt_NOMBRE;
	public JTextField Txt_LUGAR;
	public JTextField Txt_FECHA;
	public JTable Tabla_Eventos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda_principal frame = new Agenda_principal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Agenda_principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Agenda_principal.class.getResource("/Img/usuario (1).png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 580);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar Main_menu = new JMenuBar();
		Main_menu.setBounds(0, 0, 509, 28);
		contentPane.add(Main_menu);
		
		JMenu NMenu_Perfil = new JMenu("PERFIL           ");
		Main_menu.add(NMenu_Perfil);
		
		JMenuItem NMenu_Informacion = new JMenuItem("INFORMACION");
		NMenu_Informacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Informacion informacionFrame = new Informacion();
		        informacionFrame.setVisible(true);
			}
		});
		NMenu_Perfil.add(NMenu_Informacion);
		
		JMenuItem NMenu_Guardar = new JMenuItem("GUARDAR");
		NMenu_Guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guardar();
				Listar();
				Txt_ID.setText("");
				Txt_NOMBRE.setText("");
				Txt_LUGAR.setText("");
				Txt_FECHA.setText("");
			}
		});
		Main_menu.add(NMenu_Guardar);
		
		JMenuItem NMenu_Actualizar = new JMenuItem("ACTUALIZAR");
		NMenu_Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar();
				Listar();
				Txt_ID.setText("");
				Txt_NOMBRE.setText("");
				Txt_LUGAR.setText("");
				Txt_FECHA.setText("");
			}
		});
		Main_menu.add(NMenu_Actualizar);
		
		JMenuItem NMenu_Borrar = new JMenuItem("BORRAR");
		NMenu_Borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Borrar();
				Listar();
				Txt_ID.setText("");
				Txt_NOMBRE.setText("");
				Txt_LUGAR.setText("");
				Txt_FECHA.setText("");
			}
		});
		Main_menu.add(NMenu_Borrar);
		
		JMenuItem NMenu_Buscar = new JMenuItem("BUSCAR");
		NMenu_Buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreEvento = JOptionPane.showInputDialog("Ingrese el nombre del evento");
		        if(nombreEvento != null && !nombreEvento.trim().isEmpty()) {
		            Buscar(nombreEvento);
		        }
			}
		});
		Main_menu.add(NMenu_Buscar);
		
		JLabel lblAgentaElectronica = new JLabel("A G E N D A   E L E C T R O N I C A");
		lblAgentaElectronica.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAgentaElectronica.setBounds(112, 72, 333, 14);
		contentPane.add(lblAgentaElectronica);
		
		JPanel Panel_Datos = new JPanel();
		Panel_Datos.setBackground(SystemColor.activeCaption);
		Panel_Datos.setToolTipText("");
		Panel_Datos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "  D A T O S     E V E N T O  ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Panel_Datos.setBounds(187, 139, 308, 168);
		contentPane.add(Panel_Datos);
		Panel_Datos.setLayout(null);
		
		JLabel lbl_ID = new JLabel("I D");
		lbl_ID.setBounds(32, 27, 35, 14);
		Panel_Datos.add(lbl_ID);
		
		Txt_ID = new JTextField();
		Txt_ID.setBounds(93, 24, 184, 20);
		Panel_Datos.add(Txt_ID);
		Txt_ID.setColumns(10);
		
		JLabel lbl_NOMBRE = new JLabel("N O M B R E");
		lbl_NOMBRE.setBounds(10, 58, 112, 14);
		Panel_Datos.add(lbl_NOMBRE);
		
		Txt_NOMBRE = new JTextField();
		Txt_NOMBRE.setBounds(93, 55, 184, 20);
		Panel_Datos.add(Txt_NOMBRE);
		Txt_NOMBRE.setColumns(10);
		
		JLabel lbl_LUGAR = new JLabel("L U G A R");
		lbl_LUGAR.setBounds(10, 89, 112, 14);
		Panel_Datos.add(lbl_LUGAR);
		
		Txt_LUGAR = new JTextField();
		Txt_LUGAR.setBounds(93, 86, 184, 20);
		Panel_Datos.add(Txt_LUGAR);
		Txt_LUGAR.setColumns(10);
		
		JLabel lbl_FECHA = new JLabel("F E C H A");
		lbl_FECHA.setBounds(10, 120, 112, 14);
		Panel_Datos.add(lbl_FECHA);
		
		Txt_FECHA = new JTextField();
		Txt_FECHA.setBounds(93, 117, 184, 20);
		Panel_Datos.add(Txt_FECHA);
		Txt_FECHA.setColumns(10);
		
		JPanel Panel_Lista = new JPanel();
		Panel_Lista.setBackground(SystemColor.activeCaption);
		Panel_Lista.setBorder(new TitledBorder(null, "  E V E N T O S   ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Panel_Lista.setBounds(10, 320, 485, 210);
		contentPane.add(Panel_Lista);
		Panel_Lista.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 465, 177);
		Panel_Lista.add(scrollPane);
		
		Tabla_Eventos = new JTable();
		ModelTable = (DefaultTableModel)Tabla_Eventos.getModel(); 
		Tabla_Eventos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int Fila = Tabla_Eventos.getSelectedRow();
				if (Fila == -1) {
					JOptionPane.showMessageDialog(null, "Usuario no seleccionado");
				}else {
					String ID = (String)Tabla_Eventos.getValueAt(Fila, 0);
					String Nombre = (String)Tabla_Eventos.getValueAt(Fila, 1);
					String Lugar = (String)Tabla_Eventos.getValueAt(Fila, 2);
					String Fecha = (String)Tabla_Eventos.getValueAt(Fila, 3);
					Txt_ID.setText("" + ID);
					Txt_NOMBRE.setText(Nombre);
					Txt_LUGAR.setText(Lugar);
					Txt_FECHA.setText(Fecha);
				}
			}
		});
		Tabla_Eventos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Lugar", "Fecha"
			}
		));
		scrollPane.setViewportView(Tabla_Eventos);
		
		JLabel IMG_Principal = new JLabel("");
		IMG_Principal.setIcon(new ImageIcon(Agenda_principal.class.getResource("/Img/contactos.png")));
		IMG_Principal.setBounds(10, 128, 172, 179);
		contentPane.add(IMG_Principal);
		Listar();
	}
	
	void Listar() {
		CleanTable();
		String sql = "select * from eventos";
		try {
			Cn = con.getConnection();
			St = Cn.createStatement();
			Rs = St.executeQuery(sql);
			Object[] eventos = new Object[4];
			ModelTable = (DefaultTableModel)Tabla_Eventos.getModel();
			while (Rs.next()) {
				eventos[0] = Rs.getString("ID");
				eventos[1] = Rs.getString("Nombre");
				eventos[2] = Rs.getString("Lugar");
				eventos[3] = Rs.getString("Fecha");
				ModelTable.addRow(eventos);
			}
			
			Tabla_Eventos.setModel(ModelTable);
			
		} catch (Exception e) {
			
		}
	
	}
	
	public void CleanTable() {
		for (int i = 0; i < Tabla_Eventos.getRowCount(); i++) {
			ModelTable.removeRow(i);
			i = i - 1;
		}
	}
	
	public void Guardar() {
		String ID = Txt_ID.getText();
		String Nombre = Txt_NOMBRE.getText();
		String Lugar = Txt_LUGAR.getText();
		String Fecha = Txt_FECHA.getText();
		
		if (ID.equals("") | Nombre.equals("") | Lugar.equals("") | Fecha.equals("")) {
			JOptionPane.showMessageDialog(null, "Complete el registro de evento, campos vacios!!!");
		} else {
			String checkSql = "select * from eventos where ID = '" + ID + "'";
	        try {
	            Cn = con.getConnection();
	            St = Cn.createStatement();
	            Rs = St.executeQuery(checkSql);
	            if (Rs.next()) {
	                JOptionPane.showMessageDialog(null, "El ID del evento ya existe. Por favor ingrese un nuevo ID.");
	            } else {
	                String sql = "insert into eventos (ID, Nombre, Lugar, Fecha)values('"+ID+"','"+Nombre+"','"+Lugar+"','"+Fecha+"')";
	                St.executeUpdate(sql);
	                JOptionPane.showMessageDialog(null, "Evento guardado en la agenda");
	                CleanTable();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	}
	
	public void Actualizar() {
		String ID = Txt_ID.getText();
		String Nombre = Txt_NOMBRE.getText();
		String Lugar = Txt_LUGAR.getText();
		String Fecha = Txt_FECHA.getText();
				
		if (ID.equals("") | Nombre.equals("") | Lugar.equals("") | Fecha.equals("")) {
			JOptionPane.showMessageDialog(null, "Campos vacios!!!");
		} else {
			String sql = "update eventos set Nombre ='"+Nombre+"', Lugar ='"+Lugar+"', Fecha ='"+Fecha+"' where ID="+ID;
			
			try {
				Cn = con.getConnection();
				St = Cn.createStatement();
				St.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Evento actualizado");
				CleanTable();
			} catch (Exception e) {
				
			}
		}
	}
	
	public void Borrar() {
		int Seleccionado = Tabla_Eventos.getSelectedRow();
		String ID = Txt_ID.getText();
		if (Seleccionado == -1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar el evento a eliminar");
		} else {
			String sql = "delete from eventos where ID="+ID;
			try {
				Cn = con.getConnection();
				St = Cn.createStatement();
				St.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Evento eliminado con exito!!");
				CleanTable();
			} catch (Exception e) {
				
			}
		}		
	}
	
	public void Buscar(String nombreEvento) {
	    CleanTable();
	    String sql = "select * from eventos where Nombre LIKE ?";
	    try {
	        Cn = con.getConnection();
	        PreparedStatement pstm = Cn.prepareStatement(sql);
	        pstm.setString(1, "%" + nombreEvento + "%");
	        Rs = pstm.executeQuery();
	        Object[] eventos = new Object[4];
	        ModelTable = (DefaultTableModel)Tabla_Eventos.getModel();
	        while (Rs.next()) {
	            eventos[0] = Rs.getString("ID");
	            eventos[1] = Rs.getString("Nombre");
	            eventos[2] = Rs.getString("Lugar");
	            eventos[3] = Rs.getString("Fecha");
	            ModelTable.addRow(eventos);
	        }
	        Tabla_Eventos.setModel(ModelTable);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

