package equipe5MegaSae.model;

public enum TypeMateriel {
    SCENE("Scène"),
    STANDS("Stands"),
    CLOTURE("Clôture"),
    SON_LUMIERE("Matériel son et lumière"),
    SANITAIRES("Sanitaires");

    private final String libelle;

    TypeMateriel(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
