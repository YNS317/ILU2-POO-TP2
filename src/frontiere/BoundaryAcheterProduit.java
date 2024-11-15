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
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Désolé " + nomAcheteur + ", il faut être un habitant.");
			return;
		}
		System.out.println("Quel produit voulez-vous acheter ?");
		String produit = scan.nextLine();
		String[] vendeurs = controlAcheterProduit.rechercherNomsVendeursProduit(produit);
		if (vendeurs == null) {
			System.out.println("Personne ne vend ce produit.");
			return;
		}
		for (int i = 0; i < vendeurs.length; i++) System.out.println((i + 1) + " - " + vendeurs[i]);
		int choix = Clavier.entrerEntier("Choisissez un vendeur :");
		int quantite = Clavier.entrerEntier("Combien de " + produit + " voulez-vous ?");
		int achetee = controlAcheterProduit.acheterProduit(vendeurs[choix - 1], quantite);
		System.out.println(nomAcheteur + " achète " + (achetee > 0 ? achetee : "rien, car il n'y en a plus.") + " " + produit);
	}
}
