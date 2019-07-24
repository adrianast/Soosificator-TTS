package xyz.basiccoding.tts;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.sun.speech.freetts.VoiceManager;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;



public class SoosWindow {

	private JFrame frmSoosifikator;
	private JTextField textField;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SoosWindow window = new SoosWindow();
					window.frmSoosifikator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SoosWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSoosifikator = new JFrame();
		frmSoosifikator.setTitle("Soosifikator");
		frmSoosifikator.setBounds(100, 100, 450, 300);
		frmSoosifikator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSoosifikator.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(10, 36, 409, 54);
		frmSoosifikator.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSoosifizierendesWort = new JLabel("Soosifizierender Satz:");
		lblSoosifizierendesWort.setBounds(10, 11, 179, 14);
		frmSoosifikator.getContentPane().add(lblSoosifizierendesWort);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(10, 156, 409, 94);
		frmSoosifikator.getContentPane().add(label);
		
		JButton btnSoos = new JButton("Los - soos!");
		btnSoos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eingabe = textField.getText();
				String finalesWort = makeSoos(eingabe);
				
				//Wort ausgabe + TTS
				label.setText(eingabe +  " -> " + finalesWort);
				frmSoosifikator.revalidate();
				frmSoosifikator.repaint();
				
				Voice voice = new Voice ("kevin16");
				
				voice.say(eingabe);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				voice.say(finalesWort);
				
			}
		});
		btnSoos.setBounds(10, 101, 102, 23);
		frmSoosifikator.getContentPane().add(btnSoos);
		

	}
	
	
	//Methode macht aus Wörten Soos (los->soos)
	public String makeSoos(String eingabe) {
		//Einagbe normalisieren & nur letztes Wort nutzen
		String cut [] = eingabe.trim().toLowerCase().split("[ ]+");
		String wort []= cut[cut.length-1].split("");
		
		
		//Wort von Hinten aus nach Vokalen durchsuchen, dann als substring speichern.
		String wortTeil1 = new String();
		String vorhandenesVokal = new String();
		for(int i=wort.length-2; i > 0; i--) {
			if(wort[i].contentEquals("a") || wort[i].contentEquals("i") || wort[i].contentEquals("u") || wort[i].contentEquals("e") || wort[i].contentEquals("o")) {
				
				//Vokal speichern für Ausgabe
				vorhandenesVokal = wort[i];
				
				//Via String Builder von String-Array zu String
				StringBuilder builder = new StringBuilder();
				for(String s: wort) {
					builder.append(s);
				}
				String wortInString = builder.toString();
				
				//der Hintere Teil des Wortes:
				wortTeil1 = wortInString.substring(i,wortInString.length());
				break;
			}
		}
		//der Vordere Teil des Wortes:
		String wortTeil2 = new String();
        char[] wortToChar = wortTeil1.toCharArray(); 
		for(int j=wortTeil1.length()-1; j > 0; j--) {
			wortTeil2 += wortToChar[j];
		}
		//Wort zusammen setzen
		String finalesWort = wortTeil2 + vorhandenesVokal+ wortTeil1;
		

		
		return finalesWort;
	}

}
