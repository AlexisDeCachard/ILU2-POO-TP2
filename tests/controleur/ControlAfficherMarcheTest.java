package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAfficherMarcheTest {
	private ControlAfficherMarche control;
	private Village village;
	private ControlEmmenager controlEmmenager;
	private ControlPrendreEtal controlPrendreEtal;
	
	@BeforeEach
	void setUp() {
		Chef abraracourcix;
		System.out.println("Initialisation...");
		village=new Village("le village des irréductibles", 10, 5);
		abraracourcix= new Chef("abraracourcix", 10, village);
		village.setChef(abraracourcix);
		control = new ControlAfficherMarche(village);
		controlEmmenager = new ControlEmmenager(village);
		ControlVerifierIdentite verif = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(verif,village);
		controlEmmenager.ajouterGaulois("A", 7);
		controlEmmenager.ajouterGaulois("B", 7);
			
	}

	@Test
	void testControlAfficherMarche() {
		assertNotNull(control,"Constructeur ne renvoie pas null");
	}

	@Test
	void testDonnerInfosMarcheVide() {
		int nbEtals=0;
		String[] tab=new String[nbEtals];
		assertArrayEquals(control.donnerInfosMarche(),tab);
	}
	
	@Test
	void testDonnerInfosMarche() {
		controlPrendreEtal.prendreEtal("A", "f", 20);
		controlPrendreEtal.prendreEtal("B", "f", 20);
		int nbEtals=6;
		String[] tab=new String[nbEtals];
		tab[0]="A";
		tab[1]="20";
		tab[2]="f";
		tab[3]="B";
		tab[4]="20";
		tab[5]="f";
		assertArrayEquals(control.donnerInfosMarche(),tab);
	}

}
