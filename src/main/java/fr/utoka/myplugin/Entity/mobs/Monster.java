package fr.utoka.myplugin.Entity.mobs;


public abstract class Monster {
    protected float maxlifeOfMonster;
    protected float lifeOfMonster;
    protected float degatOfMonster;
    protected String Capacity1;
    protected String Capacity2; // for later ;)


    public Monster(float life, float dommage) {
        this.maxlifeOfMonster = life;
        lifeOfMonster = maxlifeOfMonster;
        this.degatOfMonster = dommage;
    }
}
