package com.github.marketahalikova.vedenikuchyne.logika;

import java.util.Observable;



/**
 * Class Kuchyne 
 * 
 * 
 *
 * @author     
 * @version    
 */
public class Kuchyne extends Observable{

	public Menu menu;
	public NakupniSeznam nakupniSeznam;
	public Sklad sklad;
	
	
	public Kuchyne() {
        
        this.menu = new Menu();
        this.nakupniSeznam = new NakupniSeznam();
        this.sklad = new Sklad();
        
        naplneniDaty();
    }
	
	
	private void naplneniDaty() {
		
		// tady bude naplneni provizornimi daty
	}
	
}
