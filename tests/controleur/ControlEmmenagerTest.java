package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlEmmenagerTest {
	private ControlEmmenager controlEmmenager;

	@BeforeEach
	void initialiserSituation(){
		Chef abraracourcix;
		Village village;
		System.out.println("Initialisation...");
		village=new Village("le village des irréductibles", 10, 5);
		abraracourcix= new Chef("abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlEmmenager = new ControlEmmenager(village);
	}

	@Test
	void testControlEmmenager() {
		assertNotNull(controlEmmenager,"Constructeur ne renvoie pas null");
	}

	@Test
	void testIsHabitant() {
		controlEmmenager.ajouterGaulois("Astérix", 9);
		boolean exceptionCatch=false;
		try {
			controlEmmenager.ajouterGaulois("Bonemine", -9);
		}catch (AssertionError e) {
			exceptionCatch=true;
		}
		assertTrue(controlEmmenager.isHabitant("Astérix"));
		assertTrue(exceptionCatch);
		assertFalse(controlEmmenager.isHabitant("Obélix"));
		assertTrue(controlEmmenager.isHabitant("abraracourcix"));
	}

	@Test
	void testAjouterDruide() {
		controlEmmenager.ajouterDruide("Panoramix", 2, 5, 8);
		assertTrue(controlEmmenager.isHabitant("Panoramix"));
	}

	@Test
	void testAjouterGaulois() {
		controlEmmenager.ajouterGaulois("Astérix", 9);
		assertTrue(controlEmmenager.isHabitant("Astérix"));
		for (int i = 1; i < 10; i++) {
			controlEmmenager.ajouterGaulois("A-" + i, 9);
		}
		controlEmmenager.ajouterGaulois("Obélix", 9);
		assertFalse(controlEmmenager.isHabitant("Obélix"));
		
	}

}
