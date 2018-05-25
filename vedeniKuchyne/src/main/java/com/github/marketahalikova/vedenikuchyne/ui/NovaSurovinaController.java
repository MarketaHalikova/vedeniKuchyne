package com.github.marketahalikova.vedenikuchyne.ui;

import java.util.Observable;

import com.github.marketahalikova.vedenikuchyne.logika.Kuchyne;

public class NovaSurovinaController extends Observable {
	
	private Kuchyne kuchyne;
	
	public void inicializuj(Kuchyne kuchyne) {
		this.kuchyne = kuchyne;
	}

}
