
package view;

import controller.ControllerJeu;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class VueJeu extends JFrame implements ActionListener {
    /**
     * - La vue peut faire des requêtes d'état sur le modèle, signifie qu'elle peut avoir une dépendance vers le modèle. Slide 4/10 rappel MVC
     * - Chaque vue est associée à un controlleur, et chaque controleur est associé à une vue (écouteur d'évènements)
     * - Chaque controleur connait le modèle
     * - Le modèle ne connait ni la vue, ni le controlleur
     * Exemple MVC : http://baptiste-wicht.developpez.com/tutoriels/conception/mvc/
     */
    private final ControllerJeu controller;

    public static final Dimension VGAP = new Dimension(1,5);
    public static final Dimension HGAP = new Dimension(5,1);
    private JComboBox listeTortues;
    
    private VueJeuBalle vueTerrain;
    private VueStrategie vueStrategie;
    
    public VueJeuBalle getVueTerrain() {
        return vueTerrain;
    }

    public VueJeu(ControllerJeu controller, VueJeuBalle vueTerrain, VueStrategie vueStrategie) {
        super("TurtleSoccer");
        this.controller = controller;
        this.vueTerrain = vueTerrain;
        this.vueStrategie = vueStrategie;
        
        this.initialisationFenetreJeu();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }

    public void initialisationFenetreJeu() {
        getContentPane().setLayout(new BorderLayout(10,10));

        // Boutons
        JToolBar toolBar = new JToolBar();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel,"North");

        toolBar.add(Box.createRigidArea(HGAP));
        ajouterBouton(toolBar, "NouveauJeu", "Lance une nouvelle partie", null);
        ajouterBouton(toolBar, "Stop", "Arrête la partie", null);
        ajouterBouton(toolBar, "Pause", "Suspend la partie", null);
        
        toolBar.add(Box.createRigidArea(HGAP));
        JLabel labelTemps = new JLabel("   00:00");
        toolBar.add(labelTemps);

        // Menus
        JMenuBar menubar=new JMenuBar();
        setJMenuBar(menubar);	// on installe le menu bar
        JMenu menuFile=new JMenu("Menu"); // on installe le premier menu
        menubar.add(menuFile);

        ajouterObjetMenu(menuFile, "Stop", "Arrête la partie", KeyEvent.VK_N);
        ajouterObjetMenu(menuFile, "Quitter", "Quitter", KeyEvent.VK_Q);

        JMenu menuHelp=new JMenu("Aide"); // on installe le premier menu
        menubar.add(menuHelp);
        ajouterObjetMenu(menuHelp, "Aide", "Help", -1);
        ajouterObjetMenu(menuHelp, "A propos", "About", -1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // la feuille de dessin
        getContentPane().add(vueTerrain, "Center");
        getContentPane().add(vueStrategie, "South");

        pack();
        setVisible(true);
    }

    /** la gestion des actions des boutons
     * @param e */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String c = e.getActionCommand();

        // actions des boutons du haut
        if (c.equals("NouveauJeu")) {
            // TODO
            System.out.println("Une nouvelle partie sera lançée très prochainement");
            controller.start();
        } else if (c.equals("Stop")) {
            // TODO
            System.out.println("Une partie déjà en cours sera arrêtée très prochainement");
        } else if (c.equals("Pause")) {
            // TODO
            System.out.println("Une partie déjà en cours sera suspendue très prochainement");
        } else if (c.equals("Quitter")) {
            controller.quitter();
        }
        vueTerrain.repaint();
    }

    // efface tout et reinitialise la feuille
    public void effacer() {
        controller.reinitialiserJeu();
        // Replace la tortue au centre
        Dimension size = vueTerrain.getSize();
        controller.changerPosition(size.width/2, size.height/2);
    }

    //utilitaires pour installer des boutons et des menus
    public void ajouterBouton(JComponent p, String name, String tooltiptext, String imageName) {
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

    public void ajouterObjetMenu(JMenu m, String label, String command, int key) {
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

    public void ajouterTortue(VueTortue tortue) {
        vueTerrain.ajouterTortues(tortue);
    }

    public void ajouterFeuilleDessin(VueJeuBalle vueTerrain) {
        this.vueTerrain = vueTerrain;
        getContentPane().add(this.vueTerrain, "Center");
    }
}
