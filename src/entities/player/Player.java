package entities.player;

import entities.Entity;
import exceptions.UnknownTypeException;
import items.Inventory;
import items.consumable.Consumable;
import items.weapon.Weapon;
import items.weapon.Weapons;
import scene.TextureId;
import scene.bars.Bars;
import scene.statbox.Statboxes;
import items.Item;
import items.ItemType;
import items.armor.Armor;
import items.armor.Armors;
import static utils.Number.dPercentage;

/**
 * Classe para gerenciar e manipular o Jogador, herdando da classe Entity
 * @see entities.Entity
 */
public final class Player extends Entity {
    private final static int STARTING_EXP_TO_LEVEL = 100;

    private static final int 
        BASE_HP = 1000,
        BASE_MP = 100,
        BASE_DAMAGE = 200,
        BASE_DEFENSE = 80;

    private static final double 
        BASE_CRIT_C = 1.0,
        BASE_CRIT_M = 1.0,
        BASE_ACCURACY = 1.0,
        BASE_DEFENSE_M = 1.0;

    private static final double 
        LUP_MULT_EXP_TO_LEVEL = 0.5, 
        LUP_MULT_MAX_HP = 1.1, 
        LUP_MULT_MAX_MP = 1.1, 
        LUP_MULT_BASE_DAMAGE = 1.1, 
        LUP_MULT_CRIT_CHANCE = 1.05, 
        LUP_MULT_ACC = 1.02;

    private int level;
    private int exp;
    private int expToLevel = 0;
    private final Inventory inventory;
    private final ClassType classType;
    //private int temporaryDamageBoost;

    /**
     * Construtor do jogador, inicializando dados da herança e atributos base
     * @param name o nome do jogador
     * @param classType o tipo de classe do jogador (mago, guerreiro, arqueiro)
     * @throws UnknownTypeException gerado por {@link #setBaseStatistics()}
     * @see entities.player.ClassType
     * @see exceptions.UnknownTypeException 
     */
    public Player(String name, ClassType classType) 
    throws UnknownTypeException 
    {
        super(name, classType.getImageSrc());

        level = 1;
        exp = 0;
        expToLevel = calcExpToLevel(level);
        //temporaryDamageBoost = 0;
        inventory = new Inventory();

        this.classType = classType;

        setBaseStatistics(classType);
        resetToBase();

        applyItemBuff(inventory.getEquippedArmor());
        applyItemBuff(inventory.getEquippedWeapon());

        setHealthBar(Bars.newPlayerHealthBar(this));
        setManaBar(Bars.newPlayerManaBar(this));
        setStatbox(Statboxes.newPlayerStatbox(this));
    }

    /**
     * Atualiza o exp atual com o recebido, {@link #levelUp() subindo de nível} caso necessário
     * @param gainedExp a quantidade de exp a ser recebida
     * @return (int) a quantidade de exp corrente/atual
     */
    public int receiveExp(int gainedExp) {
        int _currExp = exp + gainedExp;

        while(_currExp >= expToLevel) {
            _currExp -= expToLevel;
            levelUp();
        }
        exp = _currExp;

        return exp;
    }

    /**
     * Calcula a experiência para subir de level (do level passado),
     * isto é, a exp necessária para chegar no level + 1
     * @param level o que se quer calcular a experência para ultrapassá-lo
     * @return (int) a exp para ultrapassar o level passado
     */
    private int calcExpToLevel(int level) {
        int _expByLevel = level * STARTING_EXP_TO_LEVEL;
        int _halfPrevExp = (int)Math.floor(expToLevel * LUP_MULT_EXP_TO_LEVEL); 

        return _expByLevel + _halfPrevExp;
    }

    /**
     * Realiza o level-up do jogador, subindo seus atributos (estatísticas)
     * @return (int) o novo level do jogador
     */
    private int levelUp() {
        level += 1;
        expToLevel = calcExpToLevel(level);

        deapplyItemBuff(inventory.getEquippedArmor());
        deapplyItemBuff(inventory.getEquippedWeapon());

        setMaxHP( (int)(getMaxHP() * LUP_MULT_MAX_HP) );
        setCurrHP( getMaxHP() );

        setMaxMP( (int)(getMaxMP() * LUP_MULT_MAX_MP) );
        setCurrMP( getMaxMP() );
        
        setBaseDamage( (int)(getBaseDamage() * LUP_MULT_BASE_DAMAGE) );
        setCurrDamage(getBaseDamage());
        setBaseCritChance( getBaseCritChance() * LUP_MULT_CRIT_CHANCE );
        setCurrCritChance( getCurrCritChance() );
        setBaseAccuracy( getBaseAccuracy() * LUP_MULT_ACC );
        setCurrAccuracy( getCurrAccuracy() );

        applyItemBuff(inventory.getEquippedArmor());
        applyItemBuff(inventory.getEquippedWeapon());

        return level;
    }
    
    /**
     * Adiciona um item ao {@link items.Inventory inventário} do jogador
     * @param item o item a ser adicionado
     * @see items.Item
     * @see items.Inventory#add(Item)
     */
    public void addItemInventory(Item item) {
        inventory.add(item);
    }

    /**
     * Pega um item do {@link items.Inventory inventário} do jogador sem removê-lo
     * @param index o índice do item no inventário
     * @return (Item) o item buscado ou null caso não encontrado
     * @see items.Item
     * @see items.Inventory#getItem(int)
     */
    public Item getItemInventory(int index) {
        return inventory.getItem(index);
    }

    /**
     * Tenta adicionar vários itens ao {@link items.Inventory inventário} do jogador
     * @param items os items a serem adicionados em sequência
     * @see items.Item
     * @see items.Inventory#add(Item)
     */
    public void addItemsInventory(Item[] items) {
        for(final Item _item: items) {
            inventory.add(_item);
        }
    }

    /**
     * Tenta usar um item do {@link items.Inventory inventário} do jogador, removendo-o
     * @param index o índice do item a ser usado
     * @return (Consumable) o consumível que foi usado 
     * @see items.consumable.Consumable
     * @see items.Inventory#use(int)
     */
    public Consumable useConsumable(int index) {
        Consumable _consumable = inventory.use(index);
        if (_consumable != null) {
            healHP(_consumable.getBoostHP());
            recoverMP(_consumable.getBoostMP());
        }
        return _consumable;
    }

    /**
     * Aplica todos os buffs no jogador de um equipamento
     * @param item o equipamento a ser equipado
     * @see items.armor.Armor 
     * @see items.weapon.Weapon
     */
    private void applyItemBuff(Item equipment) {
        final int _boostDefense = (int)(equipment.getBoostDefense() * getCurrDefenseMultiplier());
        setCurrDefense( getCurrDefense() + _boostDefense);
        setCurrDamage( getCurrDamage() + equipment.getBoostDamage() );
        setCurrCritChance( getCurrCritChance() + equipment.getBoostCritChance() );
        setCurrCritMultiplier( getCurrCritMultiplier() + equipment.getBoostCritMultiplier() );
        setCurrAccuracy( getCurrAccuracy() + equipment.getBoostAccuracy() );
    }

    /**
     * Desaplica todos os buffs no jogador de um equipamento
     * @param item o equipamento a ser desequipado
     * @see items.armor.Armor 
     * @see items.weapon.Weapon
     */
    private void deapplyItemBuff(Item equipment) {
        final int _boostDefense = (int)(equipment.getBoostDefense() * getCurrDefenseMultiplier());
        setCurrDefense( getCurrDefense() - _boostDefense);
        setCurrDamage( getCurrDamage() - equipment.getBoostDamage() );
        setCurrCritChance( getCurrCritChance() - equipment.getBoostCritChance() );
        setCurrCritMultiplier( getCurrCritMultiplier() - equipment.getBoostCritMultiplier() );
        setCurrAccuracy( getCurrAccuracy() - equipment.getBoostAccuracy() );
    }

    /**
     * Tenta equipar um item e aplica seus buffs, o item é trocado com itens já equipados,
     * além disso, remove buffs de itens desequipados
     * @param index o índice do item a ser equipado
     * @see items.Inventory#equip(int)
     */
    public void equipInventory(int index) {
        Item _equipment = inventory.getItem(index);
        if(_equipment == null) return;

        if(_equipment.getItemType() == ItemType.ARMOR)
            deapplyItemBuff( inventory.getEquippedArmor() );

        else if(_equipment.getItemType() == ItemType.WEAPON)
            deapplyItemBuff( inventory.getEquippedWeapon() );

        inventory.equip(index);
        applyItemBuff(_equipment);
    }

    /**
     * Seta todos os atributos base do jogador dependendo do 
     * seu {@link entities.player.ClassType tipo de classe}
     * @throws UnknownTypeException 
     * @see exceptions.UnknownTypeException 
     */
    private void setBaseStatistics(ClassType classType) throws UnknownTypeException {
        int _hp = 0, _mp = 0, _damage = 0, _defense = 0;
        double _critC = 0.0f, _critM = 0.0f, _accuracy = 0.0f, _defenseM = 0.0f;
        Armor _armor = null;
        Weapon _weapon = null;
        TextureId _textureId = null;

        switch(classType) {
            case MAGE: 
                _hp = (int)( BASE_HP * dPercentage(70) );
                _mp = (int)( BASE_MP * dPercentage(100) );
                _damage = (int)( BASE_DAMAGE * dPercentage(90) );
                _defense = (int)( BASE_DEFENSE * dPercentage(70) );
                _critC = BASE_CRIT_C * dPercentage(20);
                _critM = BASE_CRIT_M * dPercentage(115);
                _accuracy = BASE_ACCURACY * dPercentage(85);
                _defenseM = BASE_DEFENSE_M * dPercentage(75);
                _armor = Armors.getArmorMage(Armors.INDEX_INITIAL_ARMOR);
                _weapon = Weapons.getWeaponMage(Weapons.INDEX_INITIAL_WEAPON);
                _textureId = TextureId.CLASS_1;
                break;

            case WARRIOR: 
                _hp = (int)( BASE_HP * dPercentage(100) );
                _mp = (int)( BASE_MP * dPercentage(70) );
                _damage = (int)( BASE_DAMAGE * dPercentage(80) );
                _defense = (int)( BASE_DEFENSE * dPercentage(90) );
                _critC = BASE_CRIT_C * dPercentage(20);
                _critM = BASE_CRIT_M * dPercentage(110);
                _accuracy = BASE_ACCURACY * dPercentage(80);
                _defenseM = BASE_DEFENSE_M * dPercentage(75);
                _armor = Armors.getArmorWarrior(Armors.INDEX_INITIAL_ARMOR);
                _weapon = Weapons.getWeaponWarrior(Weapons.INDEX_INITIAL_WEAPON);
                _textureId = TextureId.CLASS_2;
                break;

            case ARCHER: 
                _hp = (int)( BASE_HP * dPercentage(85) );
                _mp = (int)( BASE_MP * dPercentage(85) );
                _damage = (int)( BASE_DAMAGE * dPercentage(70) );
                _defense = (int)( BASE_DEFENSE * dPercentage(80) );
                _critC = BASE_CRIT_C * dPercentage(20);
                _critM = BASE_CRIT_M * dPercentage(115);
                _accuracy = BASE_ACCURACY * dPercentage(90);
                _defenseM = BASE_DEFENSE_M * dPercentage(75);
                _armor = Armors.getArmorArcher(Armors.INDEX_INITIAL_ARMOR);
                _weapon = Weapons.getWeaponArcher(Weapons.INDEX_INITIAL_WEAPON);
                _textureId = TextureId.CLASS_3;
                break;

            default: 
                throw new UnknownTypeException("The class type specified is unknown");
        }

        setMaxHP(_hp);
        setMaxMP(_mp);
        
        setBaseDamage(_damage);
        setBaseDefense(_defense);

        setBaseCritChance(_critC);
        setBaseCritMultiplier(_critM);
        setBaseAccuracy(_accuracy);
        setBaseDefenseMultiplier(_defenseM);

        setTextureId(_textureId);
        
        inventory.equipArmor(_armor);
        inventory.equipWeapon(_weapon);
    }
    
    // ------------ GETTERS ------------ //
    
    /**
     * Retorna o level atual do jogador
     * @return (int) o level atual
     */
    public int getLevel() { return level; }

    /**
     * Retorna a exp atual do jogador
     * @return (int) a exp atual
     */
    public int getExp() { return exp; }

    /**
     * Retorna a quantidade de exp para o próximo level
     * @return (int) a quantidade de exp
     */ 
    public int getExpToLevel() { return expToLevel; }

    /**
     * Retorna o {@link items.Inventory inventário} do jogador
     * @return (Inventory) o inventário
     * @see items.Inventory
     */
    public Inventory getInventory() { return inventory; }

    /**
     * Retorna o {@link entities.player.ClassType tipo de classe} do jogador
     * @return (ClassType) o tipo de classe
     * @see entities.player.ClassType
     */
    public ClassType getClassType() { return classType; }
}
