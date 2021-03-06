package view;

import controller.ControllerJeu;
import controller.ControllerJeuBalle;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.StrategieAleatoire;
import model.StrategieIntelligente;
import model.TortueBalle;
import model.TortueJoueuse;
import util.TimeFormatter;
import util.TimeFormatter.TimeChoice;

/**
 * Vue permettant de changer la stratégie des tortues
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class VueAdministration extends JPanel implements Observer 
{    
    private final JComboBox listeTortues = new javax.swing.JComboBox();
    private final JCheckBox checkBoxStrategie = new javax.swing.JCheckBox();
    private final JLabel labelInstructions = new javax.swing.JLabel();
    private final JLabel labelTimerBalle = new javax.swing.JLabel();
    private final ControllerJeuBalle controller;
    private String nomTortueSelectionnee;
    
    /**
     * Initialise la vue Administration
     * @param controller controller à associer à la vue
     */
    public VueAdministration(ControllerJeu controller) {
        this.controller = (ControllerJeuBalle) controller;
        
        setSize(new Dimension(200,200));
        setPreferredSize(new Dimension(200,200));
        checkBoxStrategie.setText("Intelligente ?");        
        labelInstructions.setText("Changement de stratégie");
        this.changerDureePossessionBalle();
        this.ajouterListeners();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelInstructions)
                    .addComponent(labelTimerBalle)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(listeTortues, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(checkBoxStrategie)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstructions)
                .addComponent(labelTimerBalle)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listeTortues, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxStrategie))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        
        // La liste des tortues + case à cocher doivent être cachées tant qu'une partie n'est pas lançée
        this.checkBoxStrategie.setVisible(false);
        this.listeTortues.setVisible(false);
    }
    
    /**
     * Ajoute les listeners à la vue : changement de checkbox et sélection d'un item dans la liste des tortues
     */
    public void ajouterListeners() {
        // Listener sur la liste des tortues
        listeTortues.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                nomTortueSelectionnee = cb.getSelectedItem().toString();
                TortueJoueuse tortue = controller.getJeu().getTortueParNom(nomTortueSelectionnee);
                if (tortue != null && tortue.getEtat() != null) {
                    if (tortue.getEtat() instanceof StrategieIntelligente){
                        checkBoxStrategie.setSelected(true);
                        checkBoxStrategie.setVisible(true);
                    } else {
                        checkBoxStrategie.setSelected(false);
                        checkBoxStrategie.setVisible(true); 
                    }  
                }
            }
        });
        
        checkBoxStrategie.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                TortueJoueuse t = controller.getJeu().getTortueParNom(nomTortueSelectionnee);
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    t.setEtat(new StrategieIntelligente(t));
                } else {
                    t.setEtat(new StrategieAleatoire(t));
                };
            }
        });
    }
    
    /**
     * Cache/Rend visible la liste des tortues dans la vue
     *
     * @param visible booléen : la liste des tortues doit être visible ou non
     */
    public void visibiliteListeTortues(boolean visible){
        this.listeTortues.setVisible(visible);
    }
    
    /**
     * Cache/Rend visible la checkbox permettant de changer la stratégie d'une tortue
     *
     * @param visible booléen : la checkbox doit être visible ou non
     */
    public void visibiliteCheckBox(boolean visible){
        this.checkBoxStrategie.setVisible(visible);
    }
    
    /**
     * Ajoute une tortue dans la la liste des tortues
     *
     * @param tVue tortue à ajouter
     */
    public void ajouterTortue(VueTortue tVue){
        TortueJoueuse tortue = (TortueJoueuse) tVue.getTortue();
        listeTortues.addItem(tortue.getNom());
    }
    
    /**
     * Récupère le composant JComboBox correspondant à la liste des tortues
     *
     * @return liste des tortues
     */
    public JComboBox getListeTortues() {
        return listeTortues;
    }
    
    /**
     * Récupère le composant JCheckBox permettant de changer la stratégie
     *
     * @return JCheckBox
     */
    public JCheckBox getCheckBoxStrategie() {
        return checkBoxStrategie;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof TortueBalle) {
            this.changerDureePossessionBalle();
        }
    }
    
    /**
     * Met à jour l'affichage de la durée de possession de la balle
     */
    private void changerDureePossessionBalle() {
        if (controller.getJeu() != null && controller.getJeu().getBalle() != null) {
            TortueBalle balle = controller.getJeu().getBalle();
            TortueJoueuse detenteurBalle = balle.getTortueSuivie();
            String duree = TimeFormatter.getMinuteSecondeFormat(TimeChoice.SECONDE, balle.getDureePossession());

            String s = "Possession de la balle: ";
            if (detenteurBalle != null) {
                s += detenteurBalle + " - ";
            }
            s += duree + "s";

            this.labelTimerBalle.setText(s);
        }
    }
}
