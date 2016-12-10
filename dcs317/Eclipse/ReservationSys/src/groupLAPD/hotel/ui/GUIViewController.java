package groupLAPD.hotel.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import groupLAPD.hotel.business.DawsonHotelFactory;
import groupLAPD.hotel.business.Hotel;
import groupLAPD.hotel.data.CustomerListDB;
import groupLAPD.hotel.data.ObjectSerializedList;
import groupLAPD.hotel.data.ReservationListDB;
import groupLAPD.hotel.data.SequentialTextFileList;

import java.awt.*;


public class GUIViewController extends JFrame implements Observer{

	private JPanel contentPane;
	private JPanel resultPanel;
	private JTextArea resultText;
	private JPanel getEmailPanel;
	private JTextField email;
	private Hotel hotelModel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String rooms = "datafiles"+File.separator+"database"+File.separator+"rooms.ser";
					String cust = "datafiles"+File.separator+"database"+File.separator+"customers.ser";
					String reserv = "datafiles"+File.separator+"database"+File.separator+"reservations.ser";
					ObjectSerializedList file = new 
							ObjectSerializedList(rooms,cust,reserv);
					System.out.println("\tThe ListPersistenceObject instance was created");
					ReservationListDB r1 = new ReservationListDB(file, DawsonHotelFactory.DAWSON);
					System.out.println("\tThe ReservationListDB instance was created");
					CustomerListDB c1 = new CustomerListDB(file, DawsonHotelFactory.DAWSON);
					System.out.println("\tThe CustomerListDB instance was created");
					
					Hotel hotel = new Hotel(DawsonHotelFactory.DAWSON, c1, r1);
					
					GUIViewController frame = new GUIViewController(hotel);
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIViewController(Hotel hotelModel) {
		
		if(hotelModel == null){
			throw new IllegalArgumentException("Cannot create Hotel view controller" + 
		"with a null Hotel");
		}
		this.hotelModel = hotelModel;
		hotelModel.addObserver(this);
		
		setResizable(false);
		setTitle("Dawson Hotel");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(getTitlePanel(), BorderLayout.NORTH);
		contentPane.add(getCenterPanel(), BorderLayout.CENTER);
		contentPane.add(getBottomPanel(), BorderLayout.SOUTH);

		this.setVisible(true);

	}
	private JPanel getTitlePanel() {
		JPanel titlePanel = new JPanel();
		JLabel lblDawsonHotelInfo = new JLabel("Dawson Hotel - Information Desk");
		lblDawsonHotelInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		titlePanel.add(lblDawsonHotelInfo);
		titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		return titlePanel;
	}
	
	private class buttonPanelListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Customer cust = null;
			try{
				cust = hotelModel.findCustomer(email.getText());
			}catch(NonExistingCustomerException a){
				JOptionPane.showMessageDialog(GUIViewController.this, "Invalid data!", 
						"alert", JOptionPane.ERROR_MESSAGE);
			}
			
			String details = "Customer details: \n";
			String email = cust.getEmail().toString() + "\n";
			String name = cust.getName().toString() + " at " ;
			String credit ="credit card on file: " + cust.getCreditCard().toString();
			String result = details + name + email + credit;
			
			resultText.setText(result);
		}
	
	}
	
	private class buttonPanelListenerRes implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Customer cust = null;
			List<Reservation> res = null;
			try{
				cust = hotelModel.findCustomer(email.getText());
				res = hotelModel.findReservations(cust);
			}catch(NonExistingCustomerException a){
				JOptionPane.showMessageDialog(GUIViewController.this, "Invalid data!", 
						"alert", JOptionPane.ERROR_MESSAGE);
			}
			String result = null;
			String details = "Reservation Details: \n";
			String name = null;
			String checks = null;
			if(!res.isEmpty()){
				name = "Reservations for: "+ cust.getName().toString() +"\n";
				result = details + name;
				
				for(int i = 0; i<res.size(); i++){
					checks = res.get(i).getRoom().toString() + " checking in on " 
				+ res.get(i).getCheckInDate() + " for " + res.get(i).getNumberOfDays() + "\n";
					
					result += checks;
				}
			}
			
			resultText.setText(result);
		}
	}
	
	private class buttonPanelListenerExist implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
	}
	}
	
	private JPanel getCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel dataEntry = new JPanel();
		centerPanel.add(dataEntry);

		getEmailPanel = new JPanel();
		dataEntry.add(getEmailPanel);
		getEmailPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblEmail = new JLabel("Enter email address: ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		getEmailPanel.add(lblEmail, BorderLayout.WEST);

		email = new JTextField();
		getEmailPanel.add(email);
		email.setColumns(25);

		dataEntry.add(getButtonPanel());

		resultPanel = new JPanel();
		centerPanel.add(resultPanel);

		resultText = new JTextArea();
		resultPanel.add(resultText);
						

		return centerPanel;
	}
	
	private JPanel getButtonPanel() {
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton custInfo = new JButton("Customer Info");
		buttonPanel.add(custInfo);

		JButton resInfo = new JButton("Reservation Info");
		buttonPanel.add(resInfo);

		custInfo.addActionListener(new buttonPanelListener());;
		resInfo.addActionListener(new buttonPanelListenerRes());
		
		return buttonPanel;
	}
	
	private JPanel getBottomPanel() {
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		bottomPanel.add(panel);

		JButton exit = new JButton("Exit");
		panel.add(exit);
		
		exit.addActionListener(new buttonPanelListenerExist());

		return bottomPanel;
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
