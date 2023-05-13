package fr.utoka.myplugin.Listener;

public enum EntityInfo {
    CREEPER(3),
    SKELETON(2),
    ZOMBIE(2),
    SPIDE(3),
    SHEEP(1);

    private int priceKilled;

    EntityInfo(int priceKilled) {
        this.priceKilled = priceKilled;
    }

    public int priceKilled() {
        return priceKilled;


    }
}
