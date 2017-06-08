package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Tortue;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class VueStrategie extends JPanel implements Observer
{
    protected List<Tortue> tortues; // la liste des tortues à afficher
    
    private javax.swing.JScrollPane panelScroll;
    private javax.swing.JTable tableauTortues;
	
    public VueStrategie() {     
        tortues = new ArrayList();
        
        panelScroll = new javax.swing.JScrollPane();
        tableauTortues = new javax.swing.JTable();
        
        setSize(new Dimension(200,200));
        setPreferredSize(new Dimension(200,200));

        tableauTortues.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tortue", "Aléatoire", "Poursuiveuse"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        panelScroll.setViewportView(tableauTortues);
        
        // TODO : Centrer le tableau
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(panelScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
