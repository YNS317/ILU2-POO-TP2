package controleur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAfficherVillageTest {
    private Village village;
    private Chef abraracourcix;
    private ControlAfficherVillage controlAfficherVillage;

    @BeforeEach
    public void initialiserSituation() {
        System.out.println("Initialisation...");
        village = new Village("le village des irréductibles", 10, 5);
        abraracourcix = new Chef("Abraracourcix", 10, village);
        village.setChef(abraracourcix);
        controlAfficherVillage = new ControlAfficherVillage(village);
    }

    @Test
    void testControlAfficherVillage() {
        assertNotNull(controlAfficherVillage, "Le constructeur ne doit pas renvoyer null");
    }

    @Test
    void testDonnerNomsVillageois() {
        // Ajouter quelques villageois
        village.ajouterHabitant(new Gaulois("Astérix", 8));
        village.ajouterHabitant(new Druide("Panoramix", 5, 1, 10));

        String[] nomsVillageois = controlAfficherVillage.donnerNomsVillageois();
        assertNotNull(nomsVillageois, "La liste des noms de villageois ne doit pas être null");
        assertEquals(3, nomsVillageois.length, "Le village doit contenir 3 habitants : le chef et les deux ajoutés");
        assertEquals("Abraracourcix", nomsVillageois[0], "Le chef doit être en première position");
        assertEquals("Astérix", nomsVillageois[1], "Astérix doit être le premier villageois ajouté");
        assertEquals("le druide Panoramix", nomsVillageois[2], "Panoramix doit être identifié comme un druide");
    }

    @Test
    void testDonnerNomVillage() {
        String nomVillage = controlAfficherVillage.donnerNomVillage();
        assertEquals("le village des irréductibles", nomVillage, "Le nom du village doit correspondre");
    }

    @Test
    void testDonnerNbEtals() {
        int nbEtals = controlAfficherVillage.donnerNbEtals();
        assertEquals(5, nbEtals, "Le nombre d'étals doit être celui initialisé dans le village");
    }
}
