package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	private ControlAfficherVillage control;
	private Village village;
	
	@BeforeEach
	void setUp(){
		Chef abraracourcix;
		System.out.println("Initialisation...");
		village=new Village("le village des irréductibles", 10, 5);
		abraracourcix= new Chef("abraracourcix", 10, village);
		village.setChef(abraracourcix);
		control = new ControlAfficherVillage(village);
	}

	@Test
	void testControlAfficherVillage() {
		assertNotNull(control,"Constructeur ne renvoie pas null");
	}

	@Test
	void testDonnerNomsVillageois() {
		ControlEmmenager control2= new ControlEmmenager(village);
		control2.ajouterDruide("Panoramix", 2, 5, 8);
		control2.ajouterGaulois("Astérix", 5);
		Gaulois gaulois=new Gaulois("g", 2);
		assertEquals("Gaulois [nom=g, force=2, effetPotion=1]", gaulois.toString());
		String[] nomsVillageoisAttendu = new String[3];
		nomsVillageoisAttendu[0]="abraracourcix";
		nomsVillageoisAttendu[1]="le druide Panoramix";
		nomsVillageoisAttendu[2]="Astérix";
		assertArrayEquals(control.donnerNomsVillageois(), nomsVillageoisAttendu);
	}

	@Test
	void testDonnerNomVillage() {
		assertEquals(control.donnerNomVillage(),"le village des irréductibles");
	}

	@Test
	void testDonnerNbEtals() {
		assertEquals(control.donnerNbEtals(),5);
	}
	
}
