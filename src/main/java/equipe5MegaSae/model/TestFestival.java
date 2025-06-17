package equipe5MegaSae.model;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TestFestival {

    public static void main(String[] args) {

        //Création d’un lieu
        Lieu lieu = new Lieu("Parc des Expos", "Lyon", "69000", "10 rue de la scène", 5000);

        //Creation d'une logistique
        Logistique log = new Logistique(0, 0, "SecuCo", "FournCo", "En attente");

        //Ajout de matériel à la logistique
        Materiel materielScene = new Materiel(TypeMateriel.SCENE, 5);
        log.modifierQuantiteMateriel(TypeMateriel.SCENE, 5);
        System.out.println("\nAprès ajout de SCÈNE (qte=5) :");
        log.getMateriels().forEach(m ->
                System.out.println("  " + m)
        );

        //Modification de quantité
        System.out.println("\nModification de la quantité de SCÈNE à 10 :");
        materielScene.setQuantite(10);
        System.out.println(materielScene + "\n");

        // Suppression du matériel
        System.out.println("\nSuppression de SCÈNE :");
        log.supprimerMateriel(new Materiel(TypeMateriel.SCENE, 0));
        for (Materiel m : log.getMateriels()) {
            System.out.println("  " + m);
        }

        // Création d'un festival
        Festival festival = new Festival("Festival de Musique", 50000, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 3), lieu);

        // Affichage des détails du festival
        System.out.println("Nom du festival: " + festival.getNom());
        System.out.println("Budget: " + festival.getBudget());
        System.out.println("Date de début: " + festival.getDateDebut());
        System.out.println("Date de fin: " + festival.getDateFin());
        System.out.println("Lieu: " + festival.getLieu());

        //Creations des artistes
        Artiste a1 = new Artiste("Zola", "zola@gmail.com", "0601020304");
        Artiste a2 = new Artiste("Lomepal", "lomepal@gmail.com", "0605060708");

        // Ajout des artistes au festival
        festival.ajouterArtiste(a1);
        festival.ajouterArtiste(a2);

        // Affichage des artistes du festival
        System.out.println("\n======" + "Artistes du festival:" + "======");
        for (Artiste artiste : festival.getArtistes()) {
            System.out.println("- " + artiste.getNom() + " (" + artiste.getMail() + ")");
        }

        //Création d'un planning
        Planning planning = new Planning(LocalDateTime.of(2023, 6, 1, 0, 0),LocalDateTime.of(2023, 6, 6, 1, 0));
        // Affectation du planning au festival
        festival.setPlanning(planning);

        // Création des événements
        Evenement concertZola = new Evenement("Concert de Zola",
                LocalDateTime.of(2023, 6, 2, 21, 0),
                LocalDateTime.of(2023, 6, 3, 22, 0),
                TypeEvenement.Artistique, "Grande Scène",
                "Concert du rappeur Zola");

        Evenement concertLomepal = new Evenement("Concert de Lomepal",
                LocalDateTime.of(2023, 6, 2, 22, 0),
                LocalDateTime.of(2023, 6, 2, 23, 0),
                TypeEvenement.Artistique, "Grande Scène",
                "Concert de Lomepal");

        Evenement demontage = new Evenement("Démontage du matériel",
                LocalDateTime.of(2023, 6, 3, 20, 0),
                LocalDateTime.of(2023, 6, 4, 23, 0),
                TypeEvenement.Logistique, null,
                "Démontage des équipements après le festival");

        // Lier les artistes aux événements artistiques
        concertZola.setArtiste(a1); // Zola joue dans son concert
        concertLomepal.setArtiste(a2); // Lomepal joue dans son concert

        // Ajout des événements au planning
        planning.ajouterEvenement(concertZola);
        planning.ajouterEvenement(concertLomepal);
        planning.ajouterEvenement(demontage);


        // Vérification des artistes associés aux événements
        System.out.println("\n====== événement artistique ======");
        //DateTimeFormatter pour formater les dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Affichage des événements du planning
        for (Evenement e : planning.getEvenements()) {
            String debut = e.getHeureDebut().format(formatter);
            String fin = e.getHeureFin().format(formatter);

            if (e.estArtistique() && e.getArtiste() != null) {
                System.out.println(e.getNom() + " [" + debut + " - " + fin + "] -> " + e.getArtiste().getNom());
            } else {
                System.out.println(e.getNom() + " [" + debut + " - " + fin + "] -> (Événement non artistique)");
            }
        }

        // Affichage du planning complet
        System.out.println("\n====== PLANNING COMPLET ======");
        for (Evenement e : planning.getEvenements()) {
            String nom = e.getNom();
            String debut = e.getHeureDebut().format(formatter);
            String fin = e.getHeureFin().format(formatter);
            String type = e.getType().toString();

            String artiste;
            if (e.getArtiste() != null) {
                artiste = e.getArtiste().getNom();
            } else {
                artiste = "(aucun)";
            }

            String scene;
            if (e.getScene() != null) {
                scene = e.getScene();
            } else {
                scene = "(non précisé)";
            }

            String description;
            if (e.getDescription() != null) {
                description = e.getDescription();
            } else {
                description = "(aucune description)";
            }

            System.out.println("- " + nom);
            System.out.println("  - Type       : " + type);
            System.out.println("  - Horaire    : " + debut + " -> " + fin);

        }

        // === Tests Billets & StatistiqueVente ===
        System.out.println("\n=== Tests Billets & StatistiqueVente ===");

// Création et ajout de deux billets
        Billet billet1 = new Billet(20.0, new Date(), festival);
        festival.ajouterBillet(billet1);

        Billet billet2 = new Billet(35.0, new Date(), festival);
        festival.ajouterBillet(billet2);

// Affichage de tous les billets vendus
        System.out.println("Billets vendus :");
        for (Billet b : festival.getBillets()) {
            System.out.println("  " + b);
        }

// Récupération et affichage des statistiques
        StatistiqueVente stats = festival.getStatistiqueVente();
        System.out.println("Nombre de billets vendus : " + stats.getNbBilletsVendus());
        System.out.println("Revenu total : "            + stats.getRevenuTotal());

// Test de suppression et mise à jour des stats
        System.out.println("\nSuppression du 1er billet et recalcul :");
        festival.supprimerBillet(billet1);

        System.out.println("Billets restants :");
        for (Billet b : festival.getBillets()) {
            System.out.println("  " + b);
        }

        stats = festival.getStatistiqueVente();
        System.out.println("Nombre de billets vendus : " + stats.getNbBilletsVendus());
        System.out.println("Revenu total : "            + stats.getRevenuTotal());




    }


}
