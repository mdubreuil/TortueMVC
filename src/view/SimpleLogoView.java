
package view;

import controller.SimpleLogoController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class SimpleLogoView extends JFrame implements ActionListener, Observer {
    
    private final SimpleLogoController controller;

    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);

    private FeuilleDessin feuille;
    private JTextField inputValue;
    private JComboBox colorList;

    public JTextField getInputValue() {
        return inputValue;
    }
    
//    public String getInputValue() {
//        return inputValue.getText();
//    }

    public JComboBox getColorList() {
        return colorList;
    }

    public FeuilleDessin getFeuille() {
        return feuille;
    }

    public SimpleLogoView(SimpleLogoController controller) {
        super("un logo tout simple");
        this.controller = controller;
        
        this.logoInit();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }

    public void logoInit() {
        getContentPane().setLayout(new BorderLayout(10,10));

        // Boutons
        JToolBar toolBar = new JToolBar();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel,"North");

        addButton(toolBar,"Effacer","Nouveau dessin","/icons/index.png");

        toolBar.add(Box.createRigidArea(HGAP));
        inputValue = new JTextField("45",5);
        toolBar.add(inputValue);
        addButton(toolBar, "Avancer", "Avancer 50", null);
        addButton(toolBar, "Droite", "Droite 45", null);
        addButton(toolBar, "Gauche", "Gauche 45", null);
        addButton(toolBar, "Lever", "Lever Crayon", null);
        addButton(toolBar, "Baisser", "Baisser Crayon", null);

        String[] colorStrings = {"noir", "bleu", "cyan","gris fonce","rouge",
                                "vert", "gris clair", "magenta", "orange",
                                "gris", "rose", "jaune"};

        // Create the combo box
        toolBar.add(Box.createRigidArea(HGAP));
        JLabel colorLabel = new JLabel("   Couleur: ");
        toolBar.add(colorLabel);
        JComboBox colorList = new JComboBox(colorStrings);
        toolBar.add(colorList);

        colorList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                int n = cb.getSelectedIndex();
                controller.changeColor(n);
            }
        });

        // Menus
        JMenuBar menubar=new JMenuBar();
        setJMenuBar(menubar);	// on installe le menu bar
        JMenu menuFile=new JMenu("File"); // on installe le premier menu
        menubar.add(menuFile);

        addMenuItem(menuFile, "Effacer", "Effacer", KeyEvent.VK_N);
        addMenuItem(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        JMenu menuCommandes=new JMenu("Commandes"); // on installe le premier menu
        menubar.add(menuCommandes);
        addMenuItem(menuCommandes, "Avancer", "Avancer", -1);
        addMenuItem(menuCommandes, "Droite", "Droite", -1);
        addMenuItem(menuCommandes, "Gauche", "Gauche", -1);
        addMenuItem(menuCommandes, "Lever Crayon", "Lever", -1);
        addMenuItem(menuCommandes, "Baisser Crayon", "Baisser", -1);

        JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
        menubar.add(menuHelp);
        addMenuItem(menuHelp, "Aide", "Help", -1);
        addMenuItem(menuHelp, "A propos", "About", -1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // les boutons du bas
        JPanel p2 = new JPanel(new GridLayout());
        JButton b20 = new JButton("Proc1");
        p2.add(b20);
        b20.addActionListener(this);
        JButton b21 = new JButton("Proc2");
        p2.add(b21);
        b21.addActionListener(this);
        JButton b22 = new JButton("Proc3");
        p2.add(b22);
        b22.addActionListener(this);

        getContentPane().add(p2,"South");

        drawFeuille();

        pack();
        setVisible(true);
    }
    
    
    protected void drawFeuille() {
        feuille = new FeuilleDessin(); //500, 400);
//        feuille.setBackground(Color.white);
//        feuille.setSize(new Dimension(600,400));
//        feuille.setPreferredSize(new Dimension(600,400));
        getContentPane().add(feuille,"Center");
    }

    /** la gestion des actions des boutons
     * @param e */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String c = e.getActionCommand();

        // actions des boutons du haut
        if (c.equals("Avancer")) {
            System.out.println("command avancer");
            try {
                int v = Integer.parseInt(inputValue.getText());
                controller.avancer(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + inputValue.getText());
            }
        } else if (c.equals("Droite")) {
            try {
                int v = Integer.parseInt(inputValue.getText());
                controller.droite(v);
            } catch (NumberFormatException ex) {
                System.err.println("ce n'est pas un nombre : " + inputValue.getText());
            }
        } else if (c.equals("Gauche")) {
            try {
                int v = Integer.parseInt(inputValue.getText());
                controller.gauche(v);
            } catch (NumberFormatException ex){
                System.err.println("ce n'est pas un nombre : " + inputValue.getText());
            }
        } else if (c.equals("Lever")) {
            controller.leverCrayon();
        } else if (c.equals("Baisser")) {
            controller.baisserCrayon();
        } else if (c.equals("Proc1")) { // actions des boutons du bas
            controller.proc1();
        } else if (c.equals("Proc2")) {
            controller.proc2();
        } else if (c.equals("Proc3")) {
            controller.proc3();
        } else if (c.equals("Effacer")) {
            this.effacer();
        } else if (c.equals("Quitter")) {
            controller.quitter();
        }

        feuille.repaint();
    }

    // efface tout et reinitialise la feuille
    public void effacer() {
//        feuille.reset();
        controller.resetFeuille();
//        feuille.repaint();

        // Replace la tortue au centre
        Dimension size = feuille.getSize();
        controller.changePosition(size.width/2, size.height/2);
    }

    //utilitaires pour installer des boutons et des menus
    public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton)p.add(new JButton(name));
        } else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon (u);
                b = (JButton) p.add(new JButton(im));
            } else {
                b = (JButton) p.add(new JButton(name));
            }
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0,0,0,0));
        b.addActionListener(this);
    }

    public void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(this);
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE) {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            } else {
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
//        if(o instanceof Feuille) // Case devrait être générique, donc on peut l'utiliser dans la vue générique
//        {
//            Feuille c = (Feuille)o;
//            feuille.
//            
//            if(c.isVisible() && c.isTrapped()) { // Si la case est une bombe
//                // On perd la partie
//                plateau.propagateExplosion();
//            
//            } else { // Sinon
//                // On notifie le modèle qu'il doit mettre à jour son compteur de drapeaux
//                plateau.updateNbMinesLeft();
//                
//                // Vérification que la partie est gagnée.
//                plateau.updateGameStateIfWin();
//            }
//        }
    }

    public void addTortue(TortueView tortue) {
        feuille.addTortue(tortue);
    }
}
