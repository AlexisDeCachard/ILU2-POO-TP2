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
	}

	@Test
	void testControlPrendreEtal() {
		assertNotNull(controlEmmenager,"Constructeur ne renvoie pas null");
	}

	@Test
	void testResteEtals() {
		assertTrue(control.resteEtals());
		for (int i = 0; i < 5; i++) {
			Gaulois gaulois=new Gaulois("a-" + i, 7);
			village.installerVendeur(gaulois, "fleurs", 5);
		}
		assertFalse(control.resteEtals());
	}

	@Test
	void testPrendreEtal() {
		fail("not implemented");
	}

	@Test
	void testVerifierIdentite() {
		controlEmmenager.ajouterGaulois("Astérix", 7);
		assertTrue(control.verifierIdentite("Astérix"));
		assertFalse(control.verifierIdentite("A"));
	}

	@Test
	void testVendeurDejaSurMarche() {
		assertFalse(control.vendeurDejaSurMarche("Astérix"));
		controlEmmenager.ajouterGaulois("Astérix", 3);
		Gaulois gaulois=new Gaulois("Astérix",3);
		village.installerVendeur(gaulois, "fleurs", 20);
		assertTrue(control.vendeurDejaSurMarche("Astérix"));
		village.partirVendeur(gaulois);
		assertFalse(control.vendeurDejaSurMarche("Astérix"));
	}

}
