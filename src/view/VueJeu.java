
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
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import model.Jeu;
import util.TimeFormatter;

/**
 * Fenêtre de l'application
 * @author Mélanie DUBREUIL et Ophélie EOUZAN - POLYTECH LYON 4APP - 2017
 */
public class VueJeu extends JFrame implements ActionListener, Observer {
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

    private JLabel labelTemps;
    private VueJeuBalle vueTerrain;
    private final VueAdministration vueStrategie;
    
    /**
     * Retourne la vue du terrain de jeu
     *
     * @return VueJeuBalle
     */
    public VueJeuBalle getVueTerrain() {
        return vueTerrain;
    }
    
    /**
     * Retourne la vue peremttant de modifier la stratégie des tortues
     *
     * @return VueStrategie
     */
    public VueAdministration getVueStrategie() {
        return vueStrategie;
    }
    
    public VueJeu(ControllerJeu controller, VueJeuBalle vueTerrain) {
        super("TurtleSoccer");
        this.controller = controller;
        this.vueTerrain = vueTerrain;
        this.vueStrategie =  new VueAdministration(controller);
        
        this.initialisationFenetreJeu();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }
    
    /**
     * Itinialisation de la vue du jeu : ajout des composants Swing
     */
    public void initialisationFenetreJeu() {
        getContentPane().setLayout(new BorderLayout(10,10));

        // Boutons
        JToolBar toolBar = new JToolBar();
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(toolBar);

        getContentPane().add(buttonPanel,"North");

        toolBar.add(Box.createRigidArea(HGAP));
        ajouterBouton(toolBar, "Start/Resume", "Lance une nouvelle partie", null);
        ajouterBouton(toolBar, "Stop", "Arrête la partie", null);
        ajouterBouton(toolBar, "Pause", "Suspend la partie", null);
        
        toolBar.add(Box.createRigidArea(HGAP));
        labelTemps = new JLabel("   00:00");
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

    /** Gestion des actions des boutons
     * @param e évènement utilisateur
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String c = e.getActionCommand();

        // actions des boutons du haut
        if (c.equals("Start/Resume")) {
            controller.start();
        } else if (c.equals("Stop")) {
            controller.stop();
        } else if (c.equals("Pause")) {
            controller.pause();
        } else if (c.equals("Quitter")) {
            controller.quitter();
        }
    }

    /**
     * Utilisataire permettant d'ajouter les boutons
     *
     * @param p JComponent
     * @param name String
     * @param tooltiptext String
     * @param imageName String
     */
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
    
    /**
     * Utilisataire permettant d'ajouter un item au menu
     *
     * @param m JMenu
     * @param label String
     * @param command Command
     * @param key Key
     */
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
    
    /**
     * Ajoute une tortue dans la vue du jeu
     *
     * @param tortue à ajouter
     */
    public void ajouterTortue(VueTortue tortue) {
        vueTerrain.ajouterTortues(tortue);
    }
    
    /**
     * Ajoute la vue du jeu au centre de la fenêtre
     *
     * @param vueTerrain vue d'un jeu de balles
     */
    public void ajouterVueJeu(VueJeuBalle vueTerrain) {
        this.vueTerrain = vueTerrain;
        getContentPane().add(this.vueTerrain, "Center");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Jeu) {
            int duree = 0;
            if (o != null) {
                Jeu jeu = (Jeu) o;
                duree = jeu.getDuree();
            }

            labelTemps.setText(TimeFormatter.getMinuteSecondeFormat(TimeFormatter.TimeChoice.SECONDE, duree));
        }
    }
}
