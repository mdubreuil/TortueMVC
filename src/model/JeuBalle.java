package model;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */

public class JeuBalle extends Jeu {

    private int nbJoueurs = 10;

    public JeuBalle(Tortue courante) {
        super(courante);
    }

    public JeuBalle() {
        super();
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }
}
