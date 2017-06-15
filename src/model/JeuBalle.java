package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */

public class JeuBalle extends Jeu
{
    private List<TortueJoueuse> joueurs; // la liste des joueurs enregistrees
    private TortueJoueuse joueurCourant = null;
    private TortueBalle balle = null;
    private int nbJoueurs = 10;

    public JeuBalle() throws InterruptedException {
        joueurs = new ArrayList();
        balle = new TortueBalle();
        initialisation();
    }

    public JeuBalle(TortueJoueuse courante) throws InterruptedException {
        this();
        joueurCourant = courante;
        ajouterTortue(courante);
    }
    
    @Override
    protected TimerTask getTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                for (TortueJoueuse joueur: getJoueurs()) {
                    if (joueur != getJoueurCourant()) {
                        joueur.seDeplacer();
                    }
                }
                incrementerDuree();
                getBalle().incrementerDureePossession();
            }
        };
    }

    @Override
    protected int getTimeIncrement() {
        return 1000;
    }

    public void setTortueCourante(TortueJoueuse courante) {
        // S'il y avait déjà une tortue courante, il faut lui dire qu'elle ne l'est plus
        if (joueurCourant != null) {
            joueurCourant.setCourante(false);
            joueurCourant.setCouleur(joueurCourant.getEtat().getCouleurStrategie());
        }

        // Si l'utilisateur a bien sélectionné une tortue, on la met en tant que tortue courante
        if(courante != null){
            courante.setCourante(true);
            courante.setCouleur(4);
        }
        this.joueurCourant = courante;        
    }
    
    @Override
    public void reinitialiser() {
        joueurCourant = null;
        joueurs.clear();
        TortueJoueuse.reinitialiserCpt();
        super.reinitialiser();
    }

    @Override
    public void ajouterTortue(Tortue tortue) { // Todo fabrique
        super.ajouterTortue(tortue);
        if (tortue instanceof TortueJoueuse) {
            ajouterJoueur((TortueJoueuse) tortue);
        } else if (tortue instanceof TortueBalle) {
            balle = (TortueBalle) tortue;
        }
    }

    public TortueJoueuse getTortueCourante() {
        return joueurCourant;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }
    
    public List<TortueJoueuse> getJoueurs() {
        return joueurs;
    }
    
    public void ajouterJoueur(TortueJoueuse joueur) {
        if (!joueurs.contains(joueur)) {
            joueurs.add(joueur);
            this.setChanged();
            this.notifyObservers();
        }
    }

    public TortueJoueuse getJoueurCourant() {
        return joueurCourant;
    }

    public TortueBalle getBalle() {
        return balle;
    }
    
    public TortueJoueuse getTortueParNom(String nom){
        for (TortueJoueuse joueuse : joueurs) {
            if (joueuse.getNom().equals(nom)) return joueuse;
        }
        return null;
    }
}
