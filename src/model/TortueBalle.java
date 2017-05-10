
package model;

/**
 *
 * @author Mélanie DUBREUIL
 * @author Ophélie EOUZAN
 */
public class TortueBalle extends Tortue {
    protected Tortue follow;

    public Tortue getFollow() {
        return follow;
    }

    public void setFollow(Tortue follow) {
        this.follow = follow;
    }
}
