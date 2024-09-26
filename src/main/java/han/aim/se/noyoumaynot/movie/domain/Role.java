package han.aim.se.noyoumaynot.movie.domain;

public class Role {
    private String name;
    private boolean isAdmin;

    public Role(String name, boolean isAdmin) {
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }
}
