package equipe5MegaSae.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestFestival {

    public static void main(String[] args) {

        // Création d'un festival
        Festival festival = new Festival("Festival de Musique", 50000, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 3));

        // Affichage des détails du festival
        System.out.println("Nom du festival: " + festival.getNom());
        System.out.println("Budget: " + festival.getBudget());
        System.out.println("Date de début: " + festival.getDateDebut());
        System.out.println("Date de fin: " + festival.getDateFin());

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
        Planning planning = new Planning(LocalDateTime.of(2023, 6, 2, 20, 0),LocalDateTime.of(2023, 6, 4, 1, 0));
        // Affectation du planning au festival
        festival.setPlanning(planning);

        // Création des événements
        Evenement concertZola = new Evenement("Concert de Zola",
                LocalDateTime.of(2023, 6, 2, 21, 0),
                LocalDateTime.of(2023, 6, 2, 22, 0),
                TypeEvenement.Artistique, "Grande Scène",
                "Concert du rappeur Zola");

        Evenement concertLomepal = new Evenement("Concert de Lomepal",
                LocalDateTime.of(2023, 6, 2, 22, 0),
                LocalDateTime.of(2023, 6, 2, 23, 0),
                TypeEvenement.Artistique, "Grande Scène",
                "Concert de clôture de Lomepal");

        Evenement demontage = new Evenement("Démontage du matériel",
                LocalDateTime.of(2023, 6, 3, 23, 30),
                LocalDateTime.of(2023, 6, 4, 1, 0),
                TypeEvenement.Logistique, null,
                "Démontage des équipements après le festival");

        // Lier les artistes aux événements artistiques
        concertZola.setArtiste(a1); // Zola joue dans son concert
        concertLomepal.setArtiste(a2); // Lomepal joue dans son concert

        // Vérification des artistes associés aux événements
        System.out.println("\n====== Artistes par événement artistique ======");
        for (Evenement e : planning.getEvenements()) {
            if (e.estArtistique()) {
                System.out.println(e.getNom() + " -> " + e.getArtiste().getNom());
            } else {
                System.out.println(e.getNom() + " -> (Événement non artistique)");
            }
        }


        // Ajout des événements au planning
        planning.ajouterEvenement(concertZola);
        planning.ajouterEvenement(concertLomepal);
        planning.ajouterEvenement(demontage);

        /*

        // Affichage du planning du festival
        System.out.println("\n======" + "Planning du festival:" + "======");
        System.out.println("Heure de début: " + festival.getPlanning().getHeureDebut());
        System.out.println("Heure de fin: " + festival.getPlanning().getHeureFin());
        System.out.println("Scène: " + festival.getPlanning().getScene());
        System.out.println("Artistes programmés:");
        for (Artiste artiste : festival.getPlanning().getArtistes()) {
            System.out.println("- " + artiste.getNom() + (""));
        }*/



    }
}
