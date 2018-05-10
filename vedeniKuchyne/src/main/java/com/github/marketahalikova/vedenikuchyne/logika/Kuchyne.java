package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.Observable;




/**
 * Class Kuchyne 
 * 
 * 
 *
 * @author   Markéta Halíková, Johanna Švugerová, Martin Weisser  
 * @version    
 */
public class Kuchyne extends Observable{

	public Menu menu;
	public NakupniSeznam nakupniSeznam;
	public Sklad sklad;
	public SeznamReceptu seznamReceptu;
	
	
	public Kuchyne() {
        
        this.menu = new Menu();
        this.nakupniSeznam = new NakupniSeznam();
        this.sklad = new Sklad();
        this.seznamReceptu = new SeznamReceptu();
        
        naplneniDaty();
    }
	
	
	private void naplneniDaty() {
		
		// tady bude naplneni provizornimi daty
	}
	
	 /**
     * Metoda vrací aktuální menu
     * @return    Menu
     */
    public NakupniSeznam getAktualniNakupniSeznam() {
        return nakupniSeznam;
    }
    
	 /**
     * Metoda vrací aktuální menu
     * @return    Menu
     */
    public Sklad getAktualniSklad() {
        return sklad;
    }
    
	 /**
     * Metoda vrací aktuální menu
     * @return    Menu
     */
    public Menu getAktualniMenu() {
        return menu;
    }
    
	 /**
     * Metoda vrací aktuální menu
     * @return    Menu
     */
    public SeznamReceptu getAktualniSeznamReceptu() {
        return seznamReceptu;
    }
	
}
