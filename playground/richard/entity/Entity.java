package entity;

//*********************************************//
// BASE OBJECT FOR ALL ENTITIES (BEINGS LIKE THE PLAYER, MONSTERS, BOSSES, ETC.)
//*********************************************//
public class Entity {
    String name;
    int life;
    int attack;
    int deffence;
    int mana;
    int id;

    protected static int id_counter = 0;

    public Entity() {
        this.id = ++Entity.id_counter;
    }
}
