package equipe5MegaSae.model;

import java.util.ArrayList;
import java.util.List;

public class Artiste {

    private String nom;
    private String mail;
    private String telephone;
    private List<Evenement> evenements = new ArrayList<>();


    public Artiste(String nom, String mail, String telephone) {
        setNom(nom);
        setMail(mail);
        setTelephone(telephone);
    }

    //Getters
    public String getNom() {
        return nom;
    }

    public String getMail() {
        return mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public List<Evenement> getEvenements() {
        return evenements;
    }

    //Setters
    public void setNom(String nom) {
        if(nom == null || nom.trim().isEmpty()){
            throw new IllegalArgumentException("Le nom de l'artiste ne peut pas être vide");
        }
        this.nom = nom.trim();
    }

    public void setMail(String mail) {
        if (mail == null || !mail.contains("@")) {
            throw new IllegalArgumentException("Adresse mail invalide.");
        }
        this.mail = mail.trim().toLowerCase();
    }

    public void setTelephone(String telephone) {
        if (telephone == null || !telephone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Numéro de téléphone invalide.");
        }
        this.telephone = telephone;
    }

    public void ajouterEvenement(Evenement e) {
        if (e != null && !evenements.contains(e)) {
            evenements.add(e);
        }
    }

    public void supprimerEvenement(Evenement e) {
        evenements.remove(e);
    }



    @Override
    public String toString() {
        return "Artiste{" +
                "nom='" + nom + '\'' +
                ", mail='" + mail + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

}
