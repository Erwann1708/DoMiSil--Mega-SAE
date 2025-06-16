package equipe5MegaSae.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Planning {

    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;
    private String description;
    private List<Evenement> evenements;
    private Festival festival;

    public Planning(){
        this.evenements = new ArrayList<>();
    }

    public Planning(LocalDateTime heureDebut, LocalDateTime heureFin) {
        setHeureDebut(heureDebut);
        setHeureFin(heureFin);
        this.description = "";
        this.evenements = new ArrayList<>();
    }


    //Getters
    public LocalDateTime getHeureDebut() {
        return heureDebut;
    }

    public LocalDateTime getHeureFin() {
        return heureFin;
    }


    public String getDescription() {
        return description;
    }



    public List<Evenement> getEvenements() {
        return evenements;
    }

    //Setters
    public void setHeureDebut(LocalDateTime heureDebut) {
        if (heureFin != null && heureDebut.isAfter(heureFin)) {
            throw new IllegalArgumentException("L'heure de début doit être avant l'heure de fin.");
        }
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(LocalDateTime heureFin) {
        if (heureDebut != null && heureFin.isBefore(heureDebut)) {
            throw new IllegalArgumentException("L'heure de fin doit être après l'heure de début.");
        }
        this.heureFin = heureFin;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setFestival(Festival festival) {
        this.festival = festival;
    }

    public boolean ajouterEvenement(Evenement evenement) {
        if (evenement == null) return false;

        LocalDateTime debut = evenement.getHeureDebut();
        LocalDateTime fin = evenement.getHeureFin();

        if (debut == null || fin == null) {
            throw new IllegalArgumentException("L'événement doit avoir une heure de début et de fin non nulles.");
        }

        if (debut.isBefore(heureDebut) || fin.isAfter(heureFin)) {
            throw new IllegalArgumentException("Les horaires de l'événement doivent être compris dans ceux du planning.");
        }

        // Vérification : artiste doit jouer pendant le festival
        if (evenement.estArtistique() && evenement.getArtiste() != null && festival != null) {
            LocalDateTime debutFestival = festival.getDateDebut().atStartOfDay();
            LocalDateTime finFestival = festival.getDateFin().atTime(23, 59);

            if (debut.isBefore(debutFestival) || fin.isAfter(finFestival)) {
                throw new IllegalArgumentException("L'artiste " + evenement.getArtiste().getNom() +
                        " joue en dehors des dates du festival [" + debutFestival + " → " + finFestival + "].");
            }
        }

        if (!evenements.contains(evenement)) {
            evenements.add(evenement);
            return true;
        }

        return false;
    }

    public boolean supprimerEvenement(Evenement evenement) {
        return evenements.remove(evenement);
    }


    //A Faire
    /*

    -tout tester
    -si le temps faire au moins la classe "lieu"
    -commenter le code

     */


}
