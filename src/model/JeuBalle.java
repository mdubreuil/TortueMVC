package model;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * Modélise un jeu de balles : ce jeu comporte un nombre de tortues qui vont s'échanger une balle
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class JeuBalle extends Jeu
{
    private List<TortueJoueuse> joueurs;
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
    
    
    /**
     * Change la tortue courante du jeu ie la tortue sélectionnée
     * 
     * @param courante : TortueJoueuse
     */
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
    
    /**
     * Réinitialise le jeu de balle
     */
    @Override
    public void reinitialiser() {
        joueurCourant = null;
        joueurs.clear();
        TortueJoueuse.reinitialiserCpt();
        super.reinitialiser();
    }
    
    /**
     * Ajoute la tortue à la liste du jeu de balles
     * 
     * @param tortue : tortue à ajouter dans le jeu de balle
     */
    @Override
    public void ajouterTortue(Tortue tortue) {
        super.ajouterTortue(tortue);
        if (tortue instanceof TortueJoueuse) {
            ajouterJoueur((TortueJoueuse) tortue);
        } else if (tortue instanceof TortueBalle) {
            balle = (TortueBalle) tortue;
        }
    }
    
    /**
     * Récupère la tortue courante du jeu de balles
     * 
     * @return TortueJoueuse
     */
    public TortueJoueuse getTortueCourante() {
        return joueurCourant;
    }
    
    /**
     * Retourne le nombre de joueurs du jeu de balles
     * 
     * @return nombreJoueurs
     */
    public int getNbJoueurs() {
        return nbJoueurs;
    }
    
    /**
     * Modifie le nombre de joueurs du jeu de balles
     * 
     * @param nbJoueurs nombre de joueurs du jeu de balles
     */
    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }
    
    /**
     * Récupère la liste des joueurs du jeu de balles
     * 
     * @return liste de TortueJoueuse
     */
    public List<TortueJoueuse> getJoueurs() {
        return joueurs;
    }
    
    /**
     * Ajoute une tortue joueuse dans la liste des tortues du jeu
     * 
     * @param joueur : TortueJoueuse
     */
    public void ajouterJoueur(TortueJoueuse joueur) {
        if (!joueurs.contains(joueur)) {
            joueurs.add(joueur);
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    /**
     * Retourne le joueur courant du jeu de balles
     * 
     * @return joueurCourant
     */
    public TortueJoueuse getJoueurCourant() {
        return joueurCourant;
    }
    
    /**
     * Retourne la balle du jeu de balles
     * 
     * @return balle
     */
    public TortueBalle getBalle() {
        return balle;
    }
    
    /**
     * Permet de récupérer une tortue selon son nom
     * 
     * @param nom : nom de la tortue que l'on souhaite retrouver
     * @return tortue : TortueJoueuse
     */
    public TortueJoueuse getTortueParNom(String nom){
        for (TortueJoueuse joueuse : joueurs) {
            if (joueuse.getNom().equals(nom)) return joueuse;
        }
        return null;
    }
}
