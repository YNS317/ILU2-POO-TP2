package controleur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import villagegaulois.Etal;
import personnages.Gaulois;

class ControlLibererEtalTest {

    private ControlLibererEtal controlLibererEtal;
    private Etal etal;
    private Gaulois gaulois;

    @BeforeEach
    void setUp() {
        // Initialisation d'un Gaulois et d'un Etal
        gaulois = new Gaulois("Asterix", 10);
        etal = new Etal();
        etal.occuperEtal(gaulois, "Poisson", 50);  // L'étal est occupé avec un produit

        // Initialisation du contrôleur
        controlLibererEtal = new ControlLibererEtal(null);
    }

    @Test
    void testIsVendeur() {
        // Vérifier si le vendeur existe
        assertTrue(controlLibererEtal.isVendeur("Asterix"), "Le vendeur Asterix doit être trouvé");
    }

    @Test
    void testLibererEtal() {
        // Libérer l'étal et vérifier que l'étal est effectivement libéré
        String[] etatAvantLiberation = controlLibererEtal.libererEtal("Asterix");
        assertNotNull(etatAvantLiberation, "L'état de l'étal doit être retourné avant de le libérer");
        assertFalse(etal.isEtalOccupe(), "L'étal doit être libéré après appel de libererEtal");
    }

    @Test
    void testLibererEtalVendeurInexistant() {
        // Vérifier que l'étal n'est pas libéré si le vendeur n'existe pas
        String[] etatLiberationInvalide = controlLibererEtal.libererEtal("NonExist");
        assertNull(etatLiberationInvalide, "L'état de l'étal ne doit pas être retourné si le vendeur n'existe pas");
    }
}
