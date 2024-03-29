package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import villagegaulois.*;

class ControlAcheterProduitTest {
    private Village village;
    private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
    private ControlVerifierIdentite controlVerifierIdentite;

    @BeforeEach
    public void initialiserSituation() {
        village = new Village("Le village", 10, 5); // Création d'un village réel pour les tests
        controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village); // Utilisation des vraies dépendances
        controlVerifierIdentite = new ControlVerifierIdentite(); // Pas besoin de dépendances pour ce contrôle
    }

    @Test
    void testObtenirMarchand_ProduitExiste() {
        ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite,
                controlTrouverEtalVendeur, village);

        village.ajouterHabitant(new Gaulois("Marchand1", 10));
        village.ajouterHabitant(new Gaulois("Marchand2", 10));

        String[] result = controlAcheterProduit.obtenirMarchand("Potion");
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals("Marchand1", result[0]);
        assertEquals("Marchand2", result[1]);
    }

    @Test
    void testObtenirMarchand_ProduitInexistant() {
        ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite,
                controlTrouverEtalVendeur, village);

        String[] result = controlAcheterProduit.obtenirMarchand("ProduitInexistant");
        assertNull(result);
    }

    @Test
    void testVerifierIdentite_VendeurExiste() {
        ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite,
                controlTrouverEtalVendeur, village);

        assertTrue(controlAcheterProduit.verifierIdentite("VendeurExistant"));
    }

    @Test
    void testVerifierIdentite_VendeurInexistant() {
        ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite,
                controlTrouverEtalVendeur, village);

        assertFalse(controlAcheterProduit.verifierIdentite("VendeurInexistant"));
    }

    @Test
    void testAcheterProduit() {
        ControlAcheterProduit controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite,
                controlTrouverEtalVendeur, village);

        village.ajouterHabitant(new Gaulois("Vendeur", 10));
        Etal etal = village.getEtal("Vendeur");

        // On vérifie que l'achat fonctionne comme prévu
        int result = controlAcheterProduit.acheterProduit("Vendeur", 10);
        assertEquals(10, result); // Étal vide donc on obtient ce qu'on a demandé
    }
}
