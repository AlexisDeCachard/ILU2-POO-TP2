package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	private ControlAcheterProduit control;
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
		control=new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
	}

	@Test
	void testControlAcheterProduit() {
		assertNotNull(control,"Constructeur ne renvoie pas null");
	}

	@Test
	void testVerifIdentite() {
		assertTrue(control.verifIdentite("Astérix"));
		assertFalse(control.verifIdentite("A"));
	}

	@Test
	void testTrouverEtalsMarcheVide() {
		String[] vendeursF=new String[4];
		String[] vide = new String[0];
		for (int i = 0; i < 4; i++) {
			vendeursF[i]="a-" + i;
		}
		assertArrayEquals(vendeursF, control.trouverEtals("f"));
		assertArrayEquals(vide, control.trouverEtals("b"));
	}

	@Test
	void testAcheterEtalVendeur() {
		int[] cas1=new int[2];
		int[] cas2=new int[2];
		int[] cas3=new int[2];
		int[] cas4=new int[2];
		cas1[0]=10;
		cas1[1]=10;
		cas2[0]=0;
		cas2[1]=0;
		cas3[0]=10;
		cas3[1]=10;
		cas4[0]=-1;
		cas4[1]=-1;
		assertArrayEquals(cas1,control.acheterEtalVendeur("a-0", 10));
		assertArrayEquals(cas2,control.acheterEtalVendeur("a-0", 0));
		assertArrayEquals(cas3,control.acheterEtalVendeur("a-1", 21));
		assertArrayEquals(cas4,control.acheterEtalVendeur("Astérix", 10));
		
	}


}
