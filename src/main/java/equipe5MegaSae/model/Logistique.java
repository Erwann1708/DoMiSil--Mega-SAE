package equipe5MegaSae.model;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Logistique {

    private int nbBenevoles;
    private int nbMusiciens;
    private String entrepriseSecurite;
    private String fournisseur;
    private String etatLivraison;
    private List<Materiel> materiels;

    /** Types de matériel définis dans l'enum TypeMateriel */
    private static final List<TypeMateriel> DEFAULT_TYPES = List.of(TypeMateriel.values());

    /**
     * Constructeur principal :
     * initialise la liste avec les types par défaut à quantité 0
     */
    public Logistique(int nbBenevoles,int nbMusiciens, String entrepriseSecurite, String fournisseur, String etatLivraison) {
        setNbBenevoles(nbBenevoles);
        setNbMusiciens(nbMusiciens);
        setEntrepriseSecurite(entrepriseSecurite);
        setFournisseur(fournisseur);
        setEtatLivraison(etatLivraison);

        this.materiels = new ArrayList<>();
        initMaterielsParDefaut();
    }

    /**
     * Surcharge : permet de passer directement une liste de Matériel
     */
    public Logistique(int nbBenevoles, int nbMusiciens, String entrepriseSecurite, String fournisseur, String etatLivraison, List<Materiel> materiels) {
        this(nbBenevoles, nbMusiciens, entrepriseSecurite, fournisseur, etatLivraison);
        // remplace la liste par défaut si nécessaire
        this.materiels.clear();
        for (Materiel mInit : materiels) {
            modifierQuantiteMateriel(mInit.getType(), mInit.getQuantite());
        }
    }



    /**
     * Initialise la liste de matériel avec les types par défaut
     */
    private void initMaterielsParDefaut() {
        for (TypeMateriel type : DEFAULT_TYPES) {
            materiels.add(new Materiel(type, 0));
        }
    }

    //Getters

    public int getNbBenevoles() {
        return nbBenevoles;
    }

    public int getNbMusiciens() {
        return nbMusiciens;
    }

    public String getEntrepriseSecurite() {
        return entrepriseSecurite;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public String getEtatLivraison() {
        return etatLivraison;
    }

    public List<Materiel> getMateriels() {
        return materiels;
    }

    //Setters
    public void setNbBenevoles(int nbBenevoles) {
        if (nbBenevoles < 0){
            throw new IllegalArgumentException("Le nombre de bénévoles ne peut pas être négatif");
        }
        this.nbBenevoles = nbBenevoles;
    }


    public void setNbMusiciens(int nbMusiciens) {
        if (nbMusiciens < 0) {
            throw new IllegalArgumentException("Le nombre de musiciens ne peut pas être négatif");
        }
        this.nbMusiciens = nbMusiciens;
    }


    public void setEntrepriseSecurite(String entrepriseSecurite) {
        this.entrepriseSecurite = entrepriseSecurite;
    }


    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }


    public void setEtatLivraison(String etatLivraison) {
        this.etatLivraison = etatLivraison;
    }







    public void modifierQuantiteMateriel(TypeMateriel type, int quantite) {
        for (Materiel m : materiels) {
            if (m.getType() == type) {
                m.setQuantite(quantite);
                return;
            }
        }
        throw new IllegalArgumentException(
                "Aucun matériel de type " + type + " dans la logistique");
    }

    /**
     * Supprime un matériel de la logistique.
     * Si le matériel n'existe pas, lève une exception.
     * @param m le matériel à supprimer
     */
    public void supprimerMateriel(Materiel m) {
        if (m == null) {
            throw new NullPointerException("Le matériel ne peut pas être null");
        }

        Iterator<Materiel> it = materiels.iterator();
        while (it.hasNext()) {
            Materiel actuel = it.next();
            if (actuel.getType().equals(m.getType())) {
                it.remove();
            }
        }
    }

}
