package controleur;

import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public boolean verifIdentite(String nom) {
		return controlVerifierIdentite.verifierIdentite(nom);
	}
	
	public String[] trouverEtals(String produit) {
		if (village.rechercherVendeursProduit(produit)!=null) {
			int length=village.rechercherVendeursProduit(produit).length;
			String[] nomsVendeurs= new String[length];
			for (int i = 0; i < length; i++) {
				String nom = village.rechercherVendeursProduit(produit)[i].getNom();
				nomsVendeurs[i]=nom;
			}
			return nomsVendeurs;
		} else {
			String[] nomsVendeurs= new String[0];
			return nomsVendeurs;
		}
	}
	
	public int[] acheterEtalVendeur(String nomVendeur, int quantiteAcheter) {
		int quantite=controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur).getQuantite();
		int achat=controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur).acheterProduit(quantiteAcheter);
		int[] resultat=new int[2];
		resultat[0]=quantite;
		resultat[1]=achat;
		return resultat;
	}
}
