package frontiere;

import java.util.Scanner;

import controleur.ControlPrendreEtal;

public class BoundaryPrendreEtal {
	private ControlPrendreEtal controlPrendreEtal;
	private Scanner scan = new Scanner(System.in);

	public BoundaryPrendreEtal(ControlPrendreEtal controlChercherEtal) {
		this.controlPrendreEtal = controlChercherEtal;
	}

	public void prendreEtal(String nomVendeur) {
		if (!controlPrendreEtal.verifierIdentite(nomVendeur)) {
			System.out.println("Je suis désolé " + nomVendeur + ", mais il faut être habitant de notre village pour commercer ici.");
		} else {
			System.out.println("Bonjour " + nomVendeur + ", je vais regarder si je peux trouver un étal pour toi.");
			if (!controlPrendreEtal.resteEtals()) {
				System.out.println("Désolé " + nomVendeur + ", je n'ai plus d'étals qui ne soit pas déjà occupé.");
			} else {
				installerVendeur(nomVendeur);
			}
		}
	}

	private void installerVendeur(String nomVendeur) {
		StringBuilder questionProduit=new StringBuilder();
		questionProduit.append("C'est parfait, il me reste un étal pour vous !\n");
		questionProduit.append("Il me faudrait quelques renseignements.\n");
		questionProduit.append("Quel produit souhaitez-vous vendre ?");
		String produit=Clavier.entrerChaine(questionProduit.toString());
		String questionQuantite="Combien souhaitez-vous en vendre ?";
		int quantite=Clavier.entrerEntier(questionQuantite);
		int numeroEtal=controlPrendreEtal.prendreEtal(nomVendeur, produit, quantite);
		if (numeroEtal!=-1) {
			numeroEtal++;
			System.out.println("Le vendeur " + nomVendeur + " s'est installé à l'étal n°" + numeroEtal);
		}
	}
}
