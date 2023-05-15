package fr.utoka.myplugin.Listener;

import org.bukkit.entity.Axolotl;

public enum EntityInfo {
    CREEPER(3),
    AXOLOTL(-1000),
    BAT(1),
    BEE(-2),
    BLAZE(6),
    CAT(-4),
    CAVA_SPIDER(1),
    CHICKEN(4),
    Cod(2),
    Cow(4),
    DOLPHIN(-3),
    DONKEY(-1),
    DRAGON(750),
    DROWNED(3),
    ELDER_GUARDIAN(250),
    ENDERMAN(10),
    ENDERMITE(7),
    EVOKER(8),
    FOX(-10),
    FROG(-2),
    GHAST(16),
    GIANT_ZOMBIE(250),
    GLOW_SQUID(1),
    GOAT(3),
    GUARDIAN(10),
    HOGLIN(15),
    HORSE(1),
    HUSK(3),
    ILLUSINER(19),
    IRON_GOLEM(20),
    LLAMA(-2),
    MAGMA_CUBE(10),
    MUSHROOM(3),
    MULE(-2),
    PANDA(1),
    PARROT(-3),
    PHANTOM(7),
    PIG(4),
    PIGLIN(9),
    PIGLIN_BRUTE(19),
    PILLAGER(6),
    POLA_BEAR(1),
    PUFFERFISH(4),
    RABBIT(3),
    RAVAGER(23),
    SALMON(5),
    SHEEP(2),
    SHULKER(6),
    SILVERFISH(1),
    SKELETON(3),
    SLIME(4),
    SNOW_GOLEM(-2412),
    SPIDER(2),
    SQUID(3),
    STRAY(5),
    STRIDER(1),
    TADPOLE(1),
    TURTLE(2),
    VEX(3),
    VINDICATOR(2),
    WARDEN(100),
    WITCH(9),
    WITHER_BOSS(1000),
    WITHER_SKELETON(15),
    WOLF(-10),
    ZOGLIN(50),
    ZOMBIE(5);




    private int priceKilled;

    EntityInfo(int priceKilled) {
        this.priceKilled = priceKilled;
    }

    public int priceKilled() {
        return priceKilled;
    }
}
