package controleur;

import static org.junit.jupiter.api.Assertions.*;

import villagegaulois.Village;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControlAfficherMarcheTest {
    private Village village;
    private ControlAfficherMarche controlAfficherMarche;

    @BeforeEach
    public void initialiserSituation() {
        village = new Village("Le village des irréductibles", 10, 5);
        controlAfficherMarche = new ControlAfficherMarche(village);
       
    }

    @Test
    void testControlAfficherMarche() {
        assertNotNull(controlAfficherMarche, "Le constructeur ne doit pas renvoyer null");
    }

    @Test
    void testDonnerInfosMarche() {
       assertNotNull(controlAfficherMarche.donnerInfosMarche(), "Les informations du marché ne doivent pas être null");
    }
}
