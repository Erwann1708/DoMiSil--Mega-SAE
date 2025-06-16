package equipe5MegaSae.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Festival {

    private String nom;
    private double budget;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private List<Artiste> artistes;
    private Planning planning;
    private Lieu lieu;
    private List<Document> documents;
    private Logistique logistique;

    public Festival(){
        this.artistes = new ArrayList<>();
        this.planning = new Planning();
        this.documents = new ArrayList<>();
        this.logistique = new Logistique(0,0,"","", "En attente");

    }

    public Festival(String nom, double budget, LocalDate dateDebut, LocalDate dateFin,Lieu lieu) {
        this(); // Appel du constructeur par défaut pour initialiser les listes
        setNom(nom);
        setBudget(budget);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setLieu(lieu);
    }

    // Getters
    public String getNom() {
        return nom;
    }
    public double getBudget() {
        return budget;
    }
    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public LocalDate getDateFin() {
        return dateFin;
    }

    public Planning getPlanning() {
        return planning;
    }

    public List<Artiste> getArtistes() {
        return artistes;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public Logistique getLogistique() {
        return logistique;
    }

    //Setters
    public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du festival ne peut pas être vide");
        }
        this.nom = nom.trim();
    }

    public void setBudget(double budget) {
        if (budget < 0) {
            throw new IllegalArgumentException("Le budget du festival ne peut pas être négatif");
        }
        this.budget = budget;
    }

    public void setDateDebut(LocalDate dateDebut) {
        if (dateDebut == null) {
            throw new IllegalArgumentException("La date de début ne peut pas être nulle.");
        }
        if (this.dateFin != null && dateDebut.isAfter(this.dateFin)) {
            throw new IllegalArgumentException("La date de début doit être avant ou égale à la date de fin.");
        }
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        if (dateFin == null) {
            throw new IllegalArgumentException("La date de fin ne peut pas être nulle.");
        }
        if (this.dateDebut != null && dateFin.isBefore(this.dateDebut)) {
            throw new IllegalArgumentException("La date de fin doit être après ou égale à la date de début.");
        }
        this.dateFin = dateFin;
    }

    public void setLieu(Lieu lieu) {
        if (lieu == null) {
            throw new IllegalArgumentException("Le lieu du festival ne peut pas être nul.");
        }
        this.lieu = lieu;
    }

    public void setPlanning(Planning planning) {
        if (planning == null) {
            throw new IllegalArgumentException("Le planning ne peut pas être nul.");
        }

        this.planning = planning;
        planning.setFestival(this);

        // Ajouter un évenement par défaut au planning : "Début du festival"
        Evenement debut = new Evenement(
                "Début du festival",
                getDateDebut().atStartOfDay(),
                getDateDebut().atStartOfDay().plusHours(1),
                TypeEvenement.Debut,
                null,
                "Ouverture du festival : " + getNom());

        // Ajouter "Fin du festival"
        Evenement fin = new Evenement(
                "Fin du festival",
                getDateFin().atStartOfDay().minusHours(1),
                getDateFin().atStartOfDay(),
                TypeEvenement.Fin,
                null,
                "Fermeture du festival : " + getNom());

        this.planning.ajouterEvenement(debut);
        this.planning.ajouterEvenement(fin);
    }

    public boolean ajouterArtiste(Artiste artiste) {
        if (!artistes.contains(artiste)) {
            artistes.add(artiste);
            return true;
        }
        return false;
    }

    public void supprimerArtiste(Artiste artiste) {
        artistes.remove(artiste);
    }

    public void ajouterDocument(Document document) {
        if (document == null) {
            throw new IllegalArgumentException("Le document ne peut pas être nul.");
        }

        if (document.getFestival() != this) {
            throw new IllegalArgumentException("Le document n'est pas lié à ce festival.");
        }

        if (documents.contains(document)) {
            throw new IllegalArgumentException("Ce document est déjà associé à ce festival.");
        }

        documents.add(document);
    }

    public boolean supprimerDocument(Document d) {
        return documents.remove(d);
    }


}
