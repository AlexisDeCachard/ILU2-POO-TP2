package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlTrouverEtalVendeurTest {
	private ControlTrouverEtalVendeur control;
	private Village village;
	
	@BeforeEach
	void setUp(){
  ControlEmmenager controlEmmenager;
		Chef abraracourcix;
		System.out.println("Initialisation...");
		village=new Village("le village des irréductibles", 10, 5);
		abraracourcix= new Chef("abraracourcix", 10, village);
		village.setChef(abraracourcix);
		control = new ControlTrouverEtalVendeur(village);
		controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Astérix", 10);
		ControlVerifierIdentite controlVerifierIdentite=new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
	}

	@Test
	void testControlTrouverEtalVendeur() {
		assertNotNull(control,"Constructeur ne renvoie pas null");
	}

	@Test
	void testTrouverEtalVendeur() {
		assertEquals(control.trouverEtalVendeur("a"), null);
		assertEquals(control.trouverEtalVendeur("Astérix"), null);
		ControlVerifierIdentite controlVerifierIdentite=new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		controlPrendreEtal.prendreEtal("Astérix", "f", 10);
		assertNotEquals(control.trouverEtalVendeur("Astérix"), null);
	}

}
