package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlLibererEtalTest {
	private ControlLibererEtal control;
	private ControlPrendreEtal controlPrendreEtal;
	
	@BeforeEach
	void setUp(){
		ControlTrouverEtalVendeur controlTrouverEtalVendeur;
		ControlVerifierIdentite controlVerifierIdentite;
		ControlEmmenager controlEmmenager;
		Village village;
		Chef abraracourcix;
		System.out.println("Initialisation...");
		village=new Village("le village des irréductibles", 10, 5);
		abraracourcix= new Chef("abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlVerifierIdentite= new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite,village);
		controlEmmenager = new ControlEmmenager(village);
		for (int i = 0; i < 4; i++) {
			controlEmmenager.ajouterGaulois("a-" + i, 7);
			controlPrendreEtal.prendreEtal("a-" + i, "f", 10);
		}
		controlEmmenager.ajouterGaulois("Astérix", 3);
		controlTrouverEtalVendeur=new ControlTrouverEtalVendeur(village);
		control=new ControlLibererEtal(controlTrouverEtalVendeur);
	}

	@Test
	void testControlLibererEtal() {
		assertNotNull(control,"Constructeur ne renvoie pas null");
	}

	@Test
	void testIsVendeur() {
		assertFalse(control.isVendeur("Astérix"));
		controlPrendreEtal.prendreEtal("Astérix", "F", 20);
		assertTrue(control.isVendeur("Astérix"));
		control.libererEtal("Astérix");
		assertFalse(control.isVendeur("Astérix"));
	}

}
