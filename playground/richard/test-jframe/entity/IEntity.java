package entity;

interface IEntity {
    public void draw();

    public void attack(Entity enemy);

    public void defend();

    public void specialAttack(Entity enemy);
}