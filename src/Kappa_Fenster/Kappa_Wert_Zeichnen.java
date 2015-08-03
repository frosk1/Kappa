package Kappa_Fenster;

import java.awt.Canvas;
import java.awt.Graphics;

public class Kappa_Wert_Zeichnen extends Canvas{

	public int gemeinsam;
	public float kappa;
	public float pa;
	public float pe;
	
	public void setGemeinsam(int gemeinsam) {
		this.gemeinsam = gemeinsam;
	}

	public void setKappa(float kappa) {
		this.kappa = kappa;
	}
	public Kappa_Wert_Zeichnen(float kappa) {
		this.kappa = kappa;
	}

	public void setPa(float pa) {
		this.pa = pa;
	}

	public void setPe(float pe) {
		this.pe = pe;
	}

	public void paint(Graphics g){
		g.drawString("Kappa-Wert : "+Float.toString(this.kappa), 10, 50);
		g.drawString("Anzahl gleicher Annotationen : "+Integer.toString(this.gemeinsam), 10, 80);
		g.drawString("PA : "+Float.toString(this.pa), 10, 110);
		g.drawString("PE : "+Float.toString(this.pe), 10, 130);
	}
}
	