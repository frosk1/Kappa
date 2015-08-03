package Kappa_Fenster;

public class Ergpaar {
		  int t1 = 0;
		  int t2 = 0;
		  int erg = 0;
		  
		  public Ergpaar(int t1, int t2, int erg){
		    this.t1 = t1;
		    this.t2 = t2;
		    this.erg = erg;
		  }
		  public Ergpaar(){
		  }
		  public void sett1(int t1){
		    this.t1 = t1;
		  }
		  public void sett2(int t2){
		    this.t2 = t2;
		  }
		  public void seterg(int erg){
		    this.erg = erg;
		  }
		  public int gett1(){
		    return this.t1;
		  }
		  public int gett2(){
		    return this.t2;
		  }
		  public int geterg(){
		    return this.erg;
		  }
		
}
