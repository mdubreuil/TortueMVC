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

    public JeuBalle() {
        joueurs = new ArrayList();
        balle = new TortueBalle();
    }

    public JeuBalle(TortueJoueuse courante) {
        this();
        joueurCourant = courante;
        ajouterTortue(courante);
    }

    @Override
    public void run() {
        // Start game
        Timer timer = getTimer();
        setEtat(Etat.EN_COURS);
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Etat etat = getEtat();
                if (etat == Etat.EN_COURS) {
                    for (TortueJoueuse joueur: getJoueurs()) {
                        if (joueur != getJoueurCourant()) {
                            joueur.seDeplacer();
                        }
                    }
                    incrementerDuree();
                } else if (etat == Etat.ARRETE) {
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 0, 1000); // Wait 1 second between each tick
    }
    
    public void setTortueCourante(TortueJoueuse courante) {
        // S'il y avait déjà une tortue courante, il faut lui dire qu'elle ne l'est plus
        if (joueurCourant != null) {
            joueurCourant.setCourante(false);
        }
//        // Si la tortue est une balle, elle ne peut pas être la tortue courante
//        if(courante instanceof TortueBalle) return;

        // Si l'utilisateur a bien sélectionné une tortue, on la met en tant que tortue courante
        if(courante != null){
            courante.setCourante(true);  
        }
        this.joueurCourant = courante;        
    }
    
    @Override
    public void reinitialiser() {
        joueurs.clear();
        joueurCourant.reinitialiser();
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
}
