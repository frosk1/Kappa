package Kappa_Fenster;

import java.awt.BorderLayout;
import java.awt.Canvas;

import Kappa_Fenster.Ergpaar;
import java.awt.EventQueue;
import java.awt.Graphics;

import Kappa_Fenster.Kappa_Wert_Zeichnen;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.Box;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

public class Kappa_Berechnung extends JFrame {

	private JPanel contentPane;
	public JPanel Kappa_Werte;
	public Kappa_Wert_Zeichnen kappa_werte1;
	// Kappa
	Boolean annogleichlaenge = true;
	Boolean annogleichelemente = true;
	int gemeinsamrelevant = 0;
	int a1_1 = 0;
	int a1_0 = 0;
	int a2_1 = 0;
	int a2_0 = 0;
	float pa;
	float pe;
	float kappa;
	
	
	// Datei einlesen
	ArrayList<Ergpaar> ergebnisse1 = new ArrayList<Ergpaar>();
	ArrayList<Ergpaar> ergebnisse2 = new ArrayList<Ergpaar>();
	ArrayList<String> textnummern1 = new ArrayList<String>();
	ArrayList<String> textnummern2 = new ArrayList<String>();
	public File datei1;
	public File datei2;


	/**
	 * Create the frame.
	 */
	public Kappa_Berechnung() {
		setTitle("Kappa 1.0");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLesenSieDie = DefaultComponentFactory.getInstance().createLabel("Die Annotationen einlesen und den Kappa-Wert berechnen");
		lblLesenSieDie.setBounds(11, 11, 430, 15);
		contentPane.add(lblLesenSieDie);
		
		JPanel Buttons = new JPanel();
		Buttons.setBounds(0, 30, 450, 221);
		contentPane.add(Buttons);
		Buttons.setLayout(null);
		
		JButton btnDateiEinlesen_1 = new JButton("Datei-1 einlesen");
		btnDateiEinlesen_1.setBounds(34, 5, 150, 25);
		Buttons.add(btnDateiEinlesen_1);
		btnDateiEinlesen_1.addActionListener(new InnerActionListener1());
		
		
		JButton btnDateiEinlesen_2 = new JButton("Datei-2 einlesen");
		btnDateiEinlesen_2.setBounds(266, 5, 150, 25);
		Buttons.add(btnDateiEinlesen_2);
		
		JButton btnKappa_wert_berechnen = new JButton("Kappa-Wert berechnen");
		btnKappa_wert_berechnen.setBounds(130, 42, 199, 25);
		Buttons.add(btnKappa_wert_berechnen);
		Kappa_Wert_Zeichnen canvas = new Kappa_Wert_Zeichnen(kappa);
		canvas.setBounds(23, 73, 275, 145);
        Buttons.add(canvas);
		
        
		btnDateiEinlesen_2.addActionListener(new InnerActionListener2());
		
	
		btnKappa_wert_berechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				// Alle Variablen auf null initialisieren
				annogleichlaenge = true;
				annogleichelemente = true;
				gemeinsamrelevant = 0;
				a1_1 = 0;
				a1_0 = 0;
				a2_1 = 0;
				a2_0 = 0;
				pa = 0;
				pe = 0;
				kappa = 0;
				// Ueberpruefung der Dateien auf Gleiche Annotationstexte
				if (textnummern1.size() != textnummern2.size()) {
					annogleichlaenge = false;
					//System.out.println("ungleiche Größe");
				}
				if(annogleichlaenge){
					for (int i = 0; i<textnummern1.size() ; i++) {
						//System.out.println("textn1: "+textnummern1.get(i));
						//System.out.println("textn2: "+textnummern2.get(i));
						if (Integer.parseInt(textnummern1.get(i)) != Integer.parseInt(textnummern2.get(i))) {
							annogleichelemente = false;
							//System.out.println("ungleiche Elemente");
						}
					}
				}
				//System.out.println("annogleich : "+annogleichlaenge);
				// Kappa-Berechnung
				if(annogleichlaenge && annogleichelemente){
					for(int i =0;i<ergebnisse1.size();i++){
				          int t1 = ergebnisse1.get(i).gett1();
				          int t2 = ergebnisse1.get(i).gett2();
				          int erg = ergebnisse1.get(i).geterg();
				          
				          
				          for(Ergpaar k:ergebnisse2){
				            if(t1 == k.gett1() && t2 == k.gett2() && erg == k.geterg()){
				              gemeinsamrelevant = gemeinsamrelevant+1;
				              //System.out.println(gemeinsamrelevant);
				            }
				          }
				         
				          if(ergebnisse1.get(i).geterg() == 1){
				            a1_1 = a1_1 +1;
				          }
				          if(ergebnisse1.get(i).geterg() == 0){
				            a1_0 = a1_0 +1;
				          }
				          if(ergebnisse2.get(i).geterg()== 0){
				            a2_0 = a2_0 +1;
				          }
				          if(ergebnisse2.get(i).geterg()== 1){
				            a2_1 = a2_1 +1;
				          }
				        }
					
						//System.out.println("gemeinsam "+ gemeinsamrelevant);
						//System.out.println("a1_1 "+ a1_1);
						//System.out.println("a1_0 "+ a1_0);
						//System.out.println("a2_1 "+ a2_1);
						//System.out.println("a2_0 "+ a2_0);
						
				        pa = (float)(gemeinsamrelevant)/(float)(ergebnisse1.size());
				        //System.out.println("pa "+pa);
				        pe = (((float)(a1_1)/(float)(ergebnisse1.size()))*((float)(a2_1)/(float)(ergebnisse1.size())))
				            +(((float)(a1_0)/(float)(ergebnisse1.size()))*((float)(a2_0)/(float)(ergebnisse1.size())));
				        //System.out.println("pe "+pe);
				        kappa = (pa-pe)/(1-pe);
				        canvas.setPa(pa);
				        canvas.setPe(pe);
				        canvas.setKappa(kappa);
				        canvas.setGemeinsam(gemeinsamrelevant);
				        canvas.repaint();
				        //System.out.println("KAPPA : "+kappa);
				        
				}
				else{
					JOptionPane.showMessageDialog(null, "Unterschiedliche Annotationen","Einlesefehler",JOptionPane.WARNING_MESSAGE);
					//System.out.println("Annotationsdateien sind nicht gleich");
				}
			}
		});
		
	}

	private class InnerActionListener1 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ergebnisse1.clear();
			textnummern1.clear();
			
			JFileChooser c = new JFileChooser();
			int rVal = c.showOpenDialog(Kappa_Berechnung.this);
			c.setDialogTitle("Bitte Annotationsdatei auswählen");
			if (rVal == JFileChooser.APPROVE_OPTION) {
				datei1 = c.getSelectedFile();
				//System.out.println(datei1);
				try(BufferedReader reader = new BufferedReader(new FileReader(datei1)) ){
					String zeile;
				while( (zeile=reader.readLine()) != null){
					Matcher matcher = Pattern.compile("Text (\\d+), Text (\\d+)\\t\\t(\\d)").matcher(zeile);
					Matcher matcher2 = Pattern.compile("Text (\\d+): \\d+").matcher(zeile);  
					if(matcher.find()){
				    //System.out.println("text: "+matcher.group(1));
					//System.out.println("text2: "+matcher.group(2));
					//System.out.println("ergebniss "+matcher.group(3));
				      Ergpaar paar = new Ergpaar(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3)));
				      ergebnisse1.add(paar);			      
				      }
				    if(matcher2.find()){
				    	textnummern1.add(matcher2.group(1));
				    	//System.out.println("matcher group ### "+matcher2.group(1));
				    }
				    //System.out.println(zeile);
				}
					reader.close();
				}
				catch (IOException f){
					f.printStackTrace();
				}
		        
		      }
		      if (rVal == JFileChooser.CANCEL_OPTION) {
		        System.out.println("cancel");
		      }
		    }
		
	}
	private class InnerActionListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			ergebnisse2.clear();
			textnummern2.clear();
			
			JFileChooser c = new JFileChooser();
			int rVal = c.showOpenDialog(Kappa_Berechnung.this);
			c.setDialogTitle("Bitte Annotationsdatei auswählen");
			if (rVal == JFileChooser.APPROVE_OPTION) {
				datei2 =c.getSelectedFile();
				System.out.println(datei2);
				try(BufferedReader reader = new BufferedReader(new FileReader(datei2)) ){
					String zeile;
				while( (zeile=reader.readLine()) != null){
					Matcher matcher = Pattern.compile("Text (\\d+), Text (\\d+)\\t\\t(\\d)").matcher(zeile);
					Matcher matcher2 = Pattern.compile("Text (\\d+): \\d+").matcher(zeile);
				      if(matcher.find()){
				    	  //System.out.println("text: "+matcher.group(1));
				    	  //System.out.println("text2: "+matcher.group(2));
				    	  //System.out.println("ergebniss "+matcher.group(3));
				      Ergpaar paar = new Ergpaar(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3)));
				      ergebnisse2.add(paar);			      
				      }
				      if(matcher2.find()){
					    	textnummern2.add(matcher2.group(1));
					    	//System.out.println("matcher group ### "+matcher2.group(1));
					  }
				      //System.out.println(zeile);
				}
					reader.close();
				}
				catch (IOException f){
					f.printStackTrace();
				}
		        
		      }
		      if (rVal == JFileChooser.CANCEL_OPTION) {
		        System.out.println("cancel");
		      }
		    }
		
	}

	
	
}
