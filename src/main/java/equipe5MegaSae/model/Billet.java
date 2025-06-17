package equipe5MegaSae.model;

import java.util.Date;

public class Billet {

    private double prix;
    private Date dateAchat;
    private Festival festival;


    public Billet(double prix, Date dateAchat, Festival festival) {
        setPrix(prix);
        setDateAchat(dateAchat);
        setFestival(festival);
    }

    // Getters
    public double getPrix() {
        return prix;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public Festival getFestival() {
        return festival;
    }

    // Setters
    public void setPrix(double prix) {
        if (prix < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        this.prix = prix;
    }

    public void setDateAchat(Date dateAchat) {
        if (dateAchat == null) {
            throw new IllegalArgumentException("La date d'achat ne peut pas être nulle");
        }
        this.dateAchat = dateAchat;
    }

    public void setFestival(Festival festival) {
        if (festival == null) {
            throw new IllegalArgumentException("Le festival ne peut pas être nul");
        }
        this.festival = festival;
    }


    @Override
    public String toString() {
        return "Billet{" +
                "prix=" + prix +
                ", dateAchat=" + dateAchat +
                '}';
    }

    /*

    Relier le billet à un festival spécifique.
     */

}
