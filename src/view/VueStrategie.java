package view;

import controller.ControllerJeu;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Strategie;
import model.StrategieIntelligente;
import model.TortueJoueuse;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class VueStrategie extends JPanel implements Observer 
{    
    private final JComboBox listeTortues = new javax.swing.JComboBox();
    private final JCheckBox checkBoxStrategie = new javax.swing.JCheckBox();
    private final JLabel labelInstructions = new javax.swing.JLabel();
    private final ControllerJeu controller;
    private String nomTortueSelectionnee;
	
    public VueStrategie(ControllerJeu controller) {
        this.controller = controller;
        
        setSize(new Dimension(200,200));
        setPreferredSize(new Dimension(200,200));
        checkBoxStrategie.setText("Intelligente ?");        
        labelInstructions.setText("Changement de stratégie");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelInstructions)
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
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listeTortues, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkBoxStrategie))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        
        // La liste des tortues + case à cocher doivent être cachées tant qu'une partie n'est pas lançée
        this.checkBoxStrategie.setVisible(false);
        this.listeTortues.setVisible(false); 
        
        checkBoxStrategie.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    controller.setStrategie(nomTortueSelectionnee,true);
                } else {
                    controller.setStrategie(nomTortueSelectionnee,false);
                };
            }
        });
    }
    
    public void ajouterListeTortuesListener() {
        listeTortues.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                nomTortueSelectionnee = cb.getSelectedItem().toString();
                Strategie s = controller.getStrategie(nomTortueSelectionnee);
                if(s != null){
                    if(s instanceof StrategieIntelligente){
                        checkBoxStrategie.setSelected(true);
                        checkBoxStrategie.setVisible(true);
                    } else {
                        checkBoxStrategie.setSelected(false);
                        checkBoxStrategie.setVisible(true); 
                    }  
                }
            }
        });
    }
    
    public void visibiliteListeTortues(boolean visible){
        this.listeTortues.setVisible(visible);
    }
    
    public void visibiliteCheckBox(boolean visible){
        this.checkBoxStrategie.setVisible(visible);
    }
    
    public void ajouterTortue(VueTortue tVue){
        TortueJoueuse tortue = (TortueJoueuse) tVue.getTortue();
        listeTortues.addItem(tortue.getNom());
    }

    public JComboBox getListeTortues() {
        return listeTortues;
    }

    public JCheckBox getCheckBoxStrategie() {
        return checkBoxStrategie;
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
