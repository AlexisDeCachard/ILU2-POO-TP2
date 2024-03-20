package frontiere;

import java.util.Scanner;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (controlAcheterProduit.verifIdentite(nomAcheteur)) {
			String produit=Clavier.entrerChaine("Quel produit voules-vous acheter ?");
			String[] nomsVendeurs=controlAcheterProduit.trouverEtals(produit);
			if (nomsVendeurs.length<1) {
				System.out.println("Désolé, personne ne vend de " + produit + " au marché.");
			} else {
				String question=creerQuestionCommercant(nomsVendeurs, produit);
				int indexVendeur=Clavier.entrerEntier(question);
				gestionVente(nomAcheteur,nomsVendeurs[indexVendeur-1], produit);
			}
		} else {
			System.out.println("Je suis désolé " + nomAcheteur + ", mais il faut être habitant de notre village pour commercer ici.");
		}
	}
	
	private String creerQuestionCommercant(String[] nomsVendeurs, String produit) {
		StringBuilder question = new StringBuilder();
		question.append("Chez quel commerçant voulez-vous acheter des " + produit+ "?\n");
		for (int i = 0; i < nomsVendeurs.length; i++) {
			int index=i+1;
			question.append(index + " - " + nomsVendeurs[i]);
		}
		return question.toString();
	}
	
	private void gestionVente(String nomAcheteur,String nomVendeur, String produit) {
		if (nomAcheteur.equals(nomVendeur)) {
			System.out.println("Tu ne peux pas acheter ta propre marchandise !");
		} else {
			System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " +  nomVendeur);
			System.out.println("Bonjour " + nomAcheteur + "!");
			int quantiteAcheter=Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
			int[] resultatVente=controlAcheterProduit.acheterEtalVendeur(nomVendeur, quantiteAcheter);
			if (resultatVente[1]==0) {
				System.out.println(nomAcheteur + " veut acheter " + quantiteAcheter + " " + produit + ", malheureusement il n'y en a plus !");
			} else {
				if (quantiteAcheter>resultatVente[1]) {
					System.out.println(nomAcheteur + " veut acheter " + quantiteAcheter + " " + produit + ", malheureusement " 
					+ nomVendeur + " n'en a plus que " + resultatVente[0] + ". " + nomAcheteur + " achète tout le stock de " + nomVendeur + ".");
				} else {
					System.out.println(nomAcheteur + " achète " + quantiteAcheter + " " + produit + " à " + nomVendeur );
				}
			}
		}
		
	}
}
