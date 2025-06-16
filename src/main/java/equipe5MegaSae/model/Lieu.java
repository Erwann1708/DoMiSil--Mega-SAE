package equipe5MegaSae.model;

public class Lieu {

    private String labelLieu;
    private String ville;
    private String codePostal;
    private String adresse;
    private int superficie;

    public Lieu(){}

    public Lieu(String labelLieu, String ville, String codePostal, String adresse, int superficie) {
        setLabelLieu(labelLieu);
        setVille(ville);
        setCodePostal(codePostal);
        setAdresse(adresse);
        setSuperficie(superficie);
    }

    //Getters
    public String getLabelLieu() {
        return labelLieu;
    }

    public String getVille() {
        return ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getSuperficie() {
        return superficie;
    }

    //Setters
    public void setLabelLieu(String labelLieu) {
        if (labelLieu == null || labelLieu.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du lieu ne peut pas être vide.");
        }
        this.labelLieu = labelLieu;
    }

    public void setVille(String ville) {
        if (ville == null || ville.trim().isEmpty()) {
            throw new IllegalArgumentException("La ville ne peut pas être vide.");
        }if(ville.length() > 25) {
            throw new IllegalArgumentException("La ville ne peut pas dépasser 50 caractères.");
        }
        this.ville = ville;
    }

    public void setCodePostal(String codePostal) {
        if (codePostal == null || !codePostal.matches("\\d{5}")) {
            throw new IllegalArgumentException("Le code postal doit contenir exactement 5 chiffres.");
        }
        this.codePostal = codePostal;
    }

    public void setAdresse(String adresse) {
        if (adresse == null || adresse.trim().isEmpty()) {
            throw new IllegalArgumentException("L'adresse ne peut pas être vide.");
        }
        this.adresse = adresse;
    }

    public void setSuperficie(int superficie) {
        if (superficie <= 0) {
            throw new IllegalArgumentException("La superficie doit être un entier positif.");
        }
        this.superficie = superficie;
    }

    @Override
    public String toString() {
        return labelLieu + " - " + adresse + ", " + codePostal + " " + ville + " (" + superficie + " m²)";
    }



}
