package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlPrendreEtalTest {
	private ControlPrendreEtal control;
	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlEmmenager controlEmmenager;
	private Village village;

	@BeforeEach
	void setUp() throws Exception {
		Chef abraracourcix;
		System.out.println("Initialisation...");
		village=new Village("le village des irréductibles", 10, 5);
		abraracourcix= new Chef("abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlVerifierIdentite= new ControlVerifierIdentite(village);
		control = new ControlPrendreEtal(controlVerifierIdentite,village);
		controlEmmenager = new ControlEmmenager(village);
		for (int i = 0; i < 4; i++) {
			controlEmmenager.ajouterGaulois("a-" + i, 7);
			control.prendreEtal("a-" + i, "f", 10);
		}
		controlEmmenager.ajouterGaulois("Astérix", 3);
	}

	@Test
	void testControlPrendreEtal() {
		assertNotNull(control,"Constructeur ne renvoie pas null");
	}

	@Test
	void testResteEtals() {
		assertTrue(control.resteEtals());
		control.prendreEtal("Astérix", "f", 10);
		assertFalse(control.resteEtals());
	}

	@Test
	void testPrendreEtal() {
		controlEmmenager.ajouterGaulois("Obélix", 20);
		control.prendreEtal("Astérix", "f", 10);
		control.prendreEtal("Obélix", "f", 10);
		assertNotEquals(village.rechercherEtal(village.trouverHabitant("Astérix")),null);
		assertEquals(village.rechercherEtal(village.trouverHabitant("Obélix")),null);
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(control.verifierIdentite("Astérix"));
		assertFalse(control.verifierIdentite("A"));
	}

	@Test
	void testVendeurDejaSurMarche() {
		assertFalse(control.vendeurDejaSurMarche("A"));
		assertFalse(control.vendeurDejaSurMarche("Astérix"));
		control.prendreEtal("Astérix", "fleurs", 20);
		assertTrue(control.vendeurDejaSurMarche("Astérix"));
		village.partirVendeur(village.trouverHabitant("Astérix"));
		assertFalse(control.vendeurDejaSurMarche("Astérix"));
	}

}
