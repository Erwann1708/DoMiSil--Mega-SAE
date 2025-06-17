package equipe5MegaSae.model;

public class Materiel {
    private TypeMateriel type;    // enum au lieu de String
    private int quantite;

    public Materiel(TypeMateriel type, int quantite) {
        this.type = type;
        setQuantite(quantite);
    }

    //Getters
    public TypeMateriel getType() { return type; }
    public int getQuantite() { return quantite; }

    //Setters
    public void setType(TypeMateriel type) { this.type = type; }

    public void setQuantite(int quantite) {
        if (quantite < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative");
        }
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Materiel{" +
                "type=" + type +
                ", quantite=" + quantite +
                '}';
    }

}


