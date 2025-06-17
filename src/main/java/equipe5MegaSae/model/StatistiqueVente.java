package equipe5MegaSae.model;

import java.util.Objects;

public class StatistiqueVente {

    private final Festival festival;    // ← nouveau lien
    private int nbBilletsVendus;
    private double revenuTotal;

    /** Construit des stats pour ce festival, en partant de 0 ventes. */
    public StatistiqueVente(Festival festival) {
        this(festival, 0, 0.0);
    }

    /** Construit des stats pour CE festival, avec valeurs initiales. */
    public StatistiqueVente(Festival festival, int nbBillets, double revenuTotal) {
        this.festival = Objects.requireNonNull(festival, "Festival non null");
        if (nbBillets  < 0) throw new IllegalArgumentException("nbBillets négatif");
        if (revenuTotal < 0) throw new IllegalArgumentException("revenuTotal négatif");
        this.nbBilletsVendus = nbBillets;
        this.revenuTotal     = revenuTotal;
    }

    public Festival getFestival() {
        return festival;
    }

    public int getNbBilletsVendus() {
        return nbBilletsVendus;
    }

    public double getRevenuTotal() {
        return revenuTotal;
    }

    /** Incrémente compteur & somme à chaque nouveau billet. */
    public void ajouterBillet(Billet b) {
        Objects.requireNonNull(b, "Billet non null");
        if (b.getFestival() != festival) {
            throw new IllegalArgumentException(
                    "Le billet n'appartient pas à ce festival");
        }
        nbBilletsVendus++;
        revenuTotal += b.getPrix();
    }

    /**
     * Décrémente le compteur et le revenu total
     */
    public void retireBillet(Billet b) {
        Objects.requireNonNull(b, "Billet non null");
        if (b.getFestival() != festival) {
            throw new IllegalArgumentException("Le billet n'appartient pas à ce festival");
        }
        nbBilletsVendus--;
        revenuTotal   -= b.getPrix();
        if (nbBilletsVendus < 0 || revenuTotal < 0) {
            throw new IllegalStateException("Incohérence dans les statistiques");
        }
    }


    /** Reconstruit toutes les stats d’un coup */
    public static StatistiqueVente calculerPour(Festival festival) {
        StatistiqueVente stats = new StatistiqueVente(festival);
        for (Billet b : festival.getBillets()) {
            stats.ajouterBillet(b);
        }
        return stats;
    }

    @Override
    public String toString() {
        return "StatistiqueVente{" +
                "festival=" + festival.getNom() +
                ", nbBilletsVendus=" + nbBilletsVendus +
                ", revenuTotal=" + revenuTotal +
                '}';
    }
}
