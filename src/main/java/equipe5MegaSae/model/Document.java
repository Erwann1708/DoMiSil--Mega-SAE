package equipe5MegaSae.model;

import java.io.File;
import java.util.Date;

public class Document {

    private String nom;
    private String type;
    private File fichier;
    private Date dateAjout;
    private Festival festival;

    public Document() {
    }

    public Document(String nom, String type, File fichier, Date dateAjout,Festival festival) {
        setNom(nom);
        setType(type);
        setFichier(fichier);
        setDateAjout(dateAjout);
        setFestival(festival);
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public File getFichier() {
        return fichier;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public Festival getFestival() {
        return festival;
    }

    // Setters
    public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du document ne peut pas être vide.");
        }
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFichier(File fichier) {
        if (fichier == null || !fichier.exists() || !fichier.isFile()) {
            throw new IllegalArgumentException("Le fichier est invalide ou introuvable.");
        }
        this.fichier = fichier;
    }

    public void setDateAjout(Date dateAjout) {
        if (dateAjout == null) {
            throw new IllegalArgumentException("La date d'ajout ne peut pas être nulle.");
        }
        this.dateAjout = dateAjout;
    }

    public void setFestival(Festival festival) {
        if (festival == null) {
            throw new IllegalArgumentException("Le festival associé ne peut pas être nul.");
        }
        this.festival = festival;
    }



    // Méthode utile : pour ouvrir ou copier le fichier
    public void ouvrir() throws Exception {
        java.awt.Desktop.getDesktop().open(fichier);
    }

    @Override
    public String toString() {
        return nom + " (" + type + ") - " + dateAjout;
    }


}
