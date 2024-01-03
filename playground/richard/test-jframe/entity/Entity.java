package entity;

//*********************************************//
// BASE OBJECT FOR ALL ENTITIES (BEINGS LIKE THE PLAYER, MONSTERS, BOSSES, ETC.)
//*********************************************//
public abstract class Entity {
    protected String name;   
    protected int life;   // 
    protected int attack;
    protected int deffence;
    protected int mana;
    protected int mana_regen; 
    protected int id;
    protected int inteligence;
    protected int strength;

    private static int id_counter = 0;

    public Entity() {
        this.id = ++Entity.id_counter;
    }
}
