package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlVerifierIdentiteTest {
	private ControlVerifierIdentite control;
	private ControlEmmenager controlEmmenager;
	
	@BeforeEach
	void setUp() throws Exception {
		Chef abraracourcix;
		Village village;
		System.out.println("Initialisation...");
		village=new Village("le village des irréductibles", 10, 5);
		abraracourcix= new Chef("abraracourcix", 10, village);
		village.setChef(abraracourcix);
		control = new ControlVerifierIdentite(village);
		controlEmmenager = new ControlEmmenager(village);
	}

	@Test
	void testControlVerifierIdentite() {
		assertNotNull(control,"Constructeur ne renvoie pas null");
	}

	@Test
	void testVerifierIdentite() {
		controlEmmenager.ajouterGaulois("Astérix", 2);
		assertFalse(control.verifierIdentite("obélix"));
		assertTrue(control.verifierIdentite("Astérix"));
		assertTrue(control.verifierIdentite("abraracourcix"));
	}

}
