import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import javax.swing.*;



public class CCWindow extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton cQuitButton = new JButton("Quit");
	private JButton cConvertButton = new JButton("Convert");
	
	private JTextField cAmtToConv = new JTextField(3);
	private JLabel cInst = new JLabel("Enter amount to convert, choose currencies, press convert");
	Font f1 = new Font("Serif", Font.BOLD, 36);
	
	
	
	
	
	public CCWindow() {
		initUI();
	}
	
	private void initUI() {
		
		setTitle("Currency converter by Tom Booker");
		setLayout(new FlowLayout());
		setSize(1000,1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		cInst.setFont(f1);
		cQuitButton.setFont(f1);
		cConvertButton.setFont(f1);
		
		add(cQuitButton); add(cConvertButton);
		
		add(cAmtToConv);
		add(cInst);
		
		getContentPane().setBackground(Color.darkGray);
		
		cQuitButton.addActionListener(this);
		cConvertButton.addActionListener(this);
		
		setVisible(true);
		
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cQuitButton) {
			cQuit();
		}
		if (e.getSource() == cConvertButton) {
			cConvert();
		}
	}
	
	public void setFont() {
		
	}
	
	public void cQuit() {
		System.exit(0);
	}
	@SuppressWarnings("resource")
	public void cConvert()
	{
		
		double USD = findExchangeRateAndConvert("GBP", "USD", 1);
		double EUR = findExchangeRateAndConvert("GBP", "EUR", 1);
		double AUD = findExchangeRateAndConvert("GBP", "AUD", 1);
		
		Scanner sc = new Scanner(System.in);
		double pounds = 0;
		System.out.println("Please enter number of £ to convert: ");
		
		try {
			pounds = sc.nextDouble();
		} catch (Exception e) {
			System.out.println("Data error :" + e.getMessage());
			return;
		}
		
		System.out.println("Convert to.. ");
		System.out.println("A - US Dollars");
		System.out.println("B - Euros");
		System.out.println("C - Australian Dollars");
		
		char letter;
		System.out.println("Please choose an option: A, B, or C");
		letter = sc.next().charAt(0);
		
		switch (letter)
		{
		case 'A':
		case 'a':
			System.out.println("Option A chosen");
			System.out.println("Dollars = " + pounds * USD);
			break;
		case 'B':
		case 'b':
			System.out.println("Option B chosen");
			System.out.println("Euros = " + pounds * EUR);
			break;
		case 'C':
		case 'c':
			System.out.println("Option C chosen");
			System.out.println("Australian dollars = " + pounds * AUD);
			break;
		default:
			System.out.println("Invalid option: " + letter + ".");
			break;
		}
		
	}
	
	private static Double findExchangeRateAndConvert(String from, String to, int amount) {
		try {
			URL url = new URL("http://download.finance.yahoo.com/d/quotes.csv?f=l1&s="+ from + to + "=X");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = reader.readLine();
			if (line.length() > 0) {
				return Double.parseDouble(line) * amount;
			
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
	
	
	


