package equipe5MegaSae.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evenement {

    private String nom; // nom de l'artiste ou nom de l'événement ("Démontage par exemple")
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;
    private TypeEvenement type; // "artistique", "logistique", etc.
    private String scene; // peut être null pour la logistique
    private String description;
    private Artiste artiste;

    public Evenement(String nom, LocalDateTime heureDebut, LocalDateTime heureFin, TypeEvenement type, String scene, String description) {
        setType(type);  // d'abord, pour éviter les erreurs de validation qui utilisent le type
        setNom(nom);
        setHeureDebut(heureDebut);
        setHeureFin(heureFin);
        setScene(scene);
        setDescription(description);
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public LocalDateTime getHeureDebut() {
        return heureDebut;
    }

    public LocalDateTime getHeureFin() {
        return heureFin;
    }

    public TypeEvenement getType() {
        return type;
    }

    public String getScene() {
        return scene;
    }

    public String getDescription() {
        return description;
    }

    public Artiste getArtiste() {
        return artiste;
    }

    // Setters
    public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'événement ne peut pas être vide.");
        }
        this.nom = nom.trim();
    }

    public void setHeureDebut(LocalDateTime heureDebut) {
        if (heureDebut == null) {
            throw new IllegalArgumentException("L'heure de début ne peut pas être nulle.");
        }
        if (this.heureFin != null && heureDebut.isAfter(this.heureFin)) {
            throw new IllegalArgumentException("L'heure de début doit être avant l'heure de fin.");
        }
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(LocalDateTime heureFin) {
        if (heureFin != null && this.heureDebut != null && heureFin.isBefore(this.heureDebut)) {
            throw new IllegalArgumentException("L'heure de fin doit être après l'heure de début.");
        }
        this.heureFin = heureFin;
    }

    public void setType(TypeEvenement type) {
        if (type == null) {
            throw new IllegalArgumentException("Le type d'événement ne peut pas être nul.");
        }
        this.type = type;
    }

    public void setScene(String scene) {
        if (estArtistique() && (scene == null || scene.trim().isEmpty())) {
            throw new IllegalArgumentException("Un événement artistique doit avoir une scène.");
        }
        this.scene = scene;
    }

    public void setDescription(String description) {
        if(description == null) {
            this.description = "";
        }else {
            this.description = description;
        }
    }

    public boolean estArtistique() {
        return type == TypeEvenement.Artistique;
    }

    public boolean estLogistique() {
        return type == TypeEvenement.Logistique;
    }

    public boolean estAutre() {
        return type == TypeEvenement.Autres;
    }


    public void setArtiste(Artiste artiste) {
        if (type != TypeEvenement.Artistique) {
            throw new UnsupportedOperationException("Seuls les événements artistiques peuvent avoir un artiste.");
        }
        if (artiste == null) {
            throw new IllegalArgumentException("L'artiste ne peut pas être nul pour un événement artistique.");
        }

        if (this.artiste != null) {
            this.artiste.supprimerEvenement(this);
        }

        this.artiste = artiste;
        artiste.ajouterEvenement(this);
    }



}

