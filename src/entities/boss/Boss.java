package entities.boss;

import entities.player.Player;
import entities.player.ClassType;
import config.Config;
import entities.Entity;
import exceptions.NumberOverflowException;
import exceptions.UnknownTypeException;
import utils.Number;
import utils.Randomic;
import utils.Text;
import scene.DrawableEntity;
import scene.TextureId;
import scene.box.Statbox;
import items.Item;
import items.weapon.Weapons;
import items.armor.Armors;
import items.consumable.Consumables;

/**
 * Classe para gerenciar e manipular o Chefão, herdando da classe Entity
 * @see entities.Entity
 */
public final class Boss extends DrawableEntity {
    public static final int DEFENDED = -1;
    // constants to change the boss BERSERK state statistics
    private static final double 
        BERSERK_DAMAGE_MULTIPLIER  = 1.5, 
        BERSERK_CRITCHANCE_ADD = 0.2,  
        BERSERK_CRITMULT_ADD = 0.2,
        BERSERK_ACCURACY_ADD = -0.1; 
    // constants to change the boss WEAK state statistics
    private static final double 
        WEAK_DAMAGE_MULTIPLIER = 0.5, 
        WEAK_CRITCHANCE_ADD = -0.1, 
        WEAK_CRITMULT_ADD = -0.2;

    private static final int BASE_DURATION = 0;

    private StateType currState;
    private int stateDuration;

    private final double berserkThreshold;
    private final double berserkChance;
    private final double baseSpecialChance;
    private final double baseDefendChance;
    private final int durationBerserk;
    private final int durationWeak;
    
    private final int expReward;
    private final String description;

    /**
     * Construtor do chefão, inicializando dados da herança e atributos base
     * @param name o nome do chefão
     * @param textureId o enum ID da textura
     * @param expReward a quantidade de exp dada ao ser derrotado
     * @param maxHP a quantidade máxima de HP
     * @param maxMP a quantidade máxima de MP
     * @param baseDamage a quantidade de dano base
     * @param baseDef a quantidade de defesa base
     * @param baseCritChance a quantidade de chance de crítico base
     * @param baseCritMult a quantidade de multiplicador de crítico base
     * @param baseAccuracy a quantidade de precisão base
     * @param baseDefenseMult a quantidade de multiplicador de defesa base
     * @param berserkChance o valor de probabilidade para atingir o estado berserk
     * @param berserkThreshold o limite de algo a ser atingido para o estado berserk
     * @param baseSpecialChance o valor de probabilidade para executar o ataque especial
     * @param baseDefendChance o valor de probabilidade para executar a defesa
     * @param durationBerserk a duração do estado BERSERK
     * @param durationWeak a duração do estado WEAK
     * @param description a descrição/história do chefão
     * @throws NumberOverflowException gerado por 
     *   {@link utils.Number#limitTo(double, double, double) Number.limitTo()}
     * @see scene.TextureId
     * @see entities.boss.StateType
     * @see exceptions.NumberOverflowException 
     */
    public Boss(
        String name, 
        TextureId textureId,
        int expReward,
        int maxHP, 
        int maxMP,
        int baseDamage,
        int baseDef,
        double baseCritChance,
        double baseCritMult,
        double baseAccuracy,
        double baseDefenseMult,
        double berserkChance,
        double berserkThreshold,
        double baseSpecialChance,
        double baseDefendChance,
        int durationBerserk,
        int durationWeak,
        String description
    )
    throws NumberOverflowException
    {
        super(name/*, textureId.getPath()*/);

        Number.limitTo(Number.MIN_DPERCENTAGE, Number.MAX_DPERCENTAGE, berserkThreshold);
        currState = StateType.BASE;
        this.setTextureId(textureId);
        this.expReward = expReward;

        setMaxHP(maxHP);
        setCurrHP(maxHP);
        setMaxMP(maxMP);
        setCurrMP(maxMP);
        setBaseDamage(baseDamage);
        setCurrDamage(baseDamage);
        setBaseDefense(baseDef);
        setCurrDefense(baseDef);
        setBaseCritChance(baseCritChance);
        setCurrCritChance(baseCritChance);
        setBaseCritMultiplier(baseCritMult);
        setCurrCritMultiplier(baseCritMult);
        setBaseAccuracy(baseAccuracy);
        setCurrAccuracy(baseAccuracy);
        setBaseDefenseMultiplier(baseDefenseMult);
        setCurrDefenseMultiplier(baseDefenseMult);
        
        this.berserkChance = berserkChance;
        this.berserkThreshold = berserkThreshold;
        this.baseSpecialChance = baseSpecialChance;
        this.baseDefendChance = baseDefendChance;
        this.description = description;
        this.durationBerserk = durationBerserk;
        this.durationWeak = durationWeak;

        setStatbox(new Statbox(this));
    }
    
    /**
     * Executa atualização de estatísticas, dados e estado 
     * do Chefão a cada turno
     * @see entities.Entity#updateEndTurn()
     */
    @Override
    public void updateEndTurn() {
        super.updateEndTurn();
        updateState();
    }
    
    /**
     * Reseta os dados do chefão para a base, sendo esses dados
     * apenas aqueles que se modificam entre estados do chefão.
     * Além disso muda seu estado para BASE
     * @see entities.boss.StateType
     */
    private void setBase() {
        currState = StateType.BASE;
        stateDuration = BASE_DURATION;
        setCurrDamage(getBaseDamage());
        setCurrAccuracy(getBaseAccuracy());
        setCurrCritChance(getBaseCritChance());
        setCurrCritMultiplier(getBaseCritMultiplier());
    }

    /**
     * Muda o estado do chefão para BERSERK, aumentando os dados do chefão, 
     * como dano, chance de crítico e multiplicador de crítico.
     * Além disso, diminuí sua precisão
     * @see entities.boss.StateType
     */
    private void setBerserk() { 
        currState = StateType.BERSERK;
        stateDuration = durationBerserk;
        setCurrDamage((int)(getBaseDamage() * BERSERK_DAMAGE_MULTIPLIER));
        setCurrCritChance(getBaseCritChance() + BERSERK_CRITCHANCE_ADD);
        setCurrCritMultiplier(getBaseCritMultiplier() + BERSERK_CRITMULT_ADD);
        setCurrAccuracy(getBaseAccuracy() + BERSERK_ACCURACY_ADD);
    }

    /**
     * Muda o estado do chefão para WEAK, diminuindo os dados do chefão, 
     * como dano, chance de crítico e multiplicador de crítico.
     * Além disso, sua precisão se mantém na precisão base
     * @see entities.boss.StateType
     */
    private void setWeak() { // When weak, boss does less damage and has less accuracy
        currState = StateType.WEAK;
        stateDuration = durationWeak;
        setCurrDamage((int)(getBaseDamage() * WEAK_DAMAGE_MULTIPLIER));
        setCurrCritChance(getBaseCritChance() + WEAK_CRITCHANCE_ADD);
        setCurrCritMultiplier(getBaseCritMultiplier() + WEAK_CRITMULT_ADD);
        setCurrAccuracy(getBaseAccuracy());
    }
    
    /**
     * Realiza a transição e a atualização do estado do chefão
     * @return (StateType) o novo estado do chefão (se modificado)
     * @see entities.boss.StateType
     * @see utils.Randomic
     * @see #reachedBerserkThreshold()
     */
    public StateType updateState() {
        if(stateDuration == 0) {
            if(currState == StateType.BASE && reachedBerserkThreshold()) {
                final double _probability = Randomic.between(0.0, 1.0);
                if(_probability < berserkChance) setBerserk();
            }
            else if(currState == StateType.BERSERK) setWeak();
            else if(currState == StateType.WEAK) setBase();  
        }
        else stateDuration--;
        return currState;
    }
    
    /**
     * Executa uma ação contra o jogador, seja atacando-o com 
     * ataques normais ou especiais, ou defendendo-se dos ataques
     * do jogador
     * @param player o jogador para executar a ação
     * @return (int) o dano causado no jogador 
     *   (ou {@link entities.Entity#ATTACK_MISSED ATTACK_MISSED})
     * @see entities.player.Player
     * @see entities.boss.StateType
     * @see utils.Randomic
     * @see entities.Entity#attack(Entity)
     * @see entities.Entity#attackSuper(Entity)
     * @see entities.Entity#setDefend()
     * @see entities.Entity#canSuper()
     */
    public int executeAction(Player player) {
        double _probability = Randomic.between(0.0, 1.0);

        switch (currState) {
            // In BASE state, Boss chooses between attack, special and defending
            case BASE: 
                if(_probability < baseSpecialChance) 
                    return (canSuper()) ? attackSuper(player) : attack(player);
                else if(_probability < (baseSpecialChance + baseDefendChance))
                    setDefend();
                else return attack(player);
                break;

            // When berserk, boss only attacks, always using a super when possible
            case BERSERK: 
                return (canSuper()) ? attackSuper(player) : attack(player);

            // When weak, Boss only attacks or defends
            case WEAK: 
                if(_probability < Number.dPercentage(50))
                    return attack(player);
                setDefend();
                break;

            default: break;
        }
        return 0;
    }  

    /**
     * Retorna uma lista de itens randomicamente escolhidos úteis a classe do jogador
     * @param classType a classe do jogador que receberá os items
     * @return (Item[]) a lista de itens
     * @throws UnknownTypeException gerado por getRandomByClass() em 
     *   {@link items.armor.Armors#getRandomByClass(ClassType) Armors} e 
     *   {@link items.weapon.Weapons#getRandomByClass(ClassType) Weapons}
     * @see items.Item 
     * @see entities.player.ClassType
     * @see items.consumable.Consumables#getRandom()
     * @see items.armor.Armors#getRandomByClass(ClassType)
     * @see items.weapon.Weapons#getRandomByClass(ClassType)
     * @see exceptions.UnknownTypeException
     */
    private Item[] getRandomItemsList(ClassType classType) throws UnknownTypeException {
        final Item[] _items = {
            Consumables.getRandom(),  
            Armors.getRandomByClass(classType), 
            Consumables.getRandom(),
            Weapons.getRandomByClass(classType),
            Consumables.getRandom()
        };
        return _items;
    }

    /**
     * Retorna um item randomicamente escolhido útil a classe do jogador
     * @param classType a classe do jogador que receberá o item
     * @return (Item) o item randomicamente escolhido
     * @throws UnknownTypeException gerado por {@link #getRandomItemsList(ClassType)}
     * @see items.Item 
     * @see utils.Randomic
     * @see entities.player.ClassType
     * @see exceptions.UnknownTypeException
     */
    private Item getRandomItem(ClassType classType) throws UnknownTypeException {
        final Item[] _items = getRandomItemsList(classType);
        final int _randomIndex = Randomic.between(0, _items.length - 1);

        return _items[_randomIndex];
    }

    /**
     * Retorna dois itens randomicamente escolhidos úteis a classe do jogador 
     * como drops pela derrota do chefão
     * @param classType a classe do jogador que receberá o item
     * @return (Item[]) os itns randomicamente escolhidos
     * @throws UnknownTypeException gerado por {@link #getRandomItem(ClassType)}
     * @see items.Item 
     * @see entities.player.ClassType
     * @see exceptions.UnknownTypeException
     */
    public Item[] getDroppedItems(ClassType classType) throws UnknownTypeException {
        final Item[] _items = {
            getRandomItem(classType),
            getRandomItem(classType)
        };
        return _items;
    }
    
    /**
     * Retorna o estado atual do chefão
     * @return (StateType) o estado atual
     * @see entities.boss.StateType
     */
    public StateType getCurrState() { return currState; }

    /**
     * Retorna a descrição/história do chefão
     * @return (String) a descrição
     * @see entities.boss.Bosses
     */
    public String getDescription() { return description; }

    /**
     * Retorna a experiência de recompensa pela derrota do chefão
     * @return (int) a experiência de recompensa
     */
    public int getExpReward() { return this.expReward; }

    /**
     * Checa se o Boss atingiu o ponto de estado BERSERK (o ponto de troca para o estado)
     * @return (boolean) se o Boss pode atingir o estado ou não
     * @see entities.boss.StateType
     */
    private boolean reachedBerserkThreshold() {
        double healthPercent = (double)this.getCurrHP() / (double)this.getMaxHP();
        return healthPercent < berserkThreshold;
    }

    /*
     * Retorna uma lista de textos para mostrar na sua caixa de estatísticas (statbox)
     * @return (String[]) a lista de textos
     */
    @Override
    public String[] getStatboxText() {
        final String[] _textList = {
            getName(),
            Config.STATBOX_TEXT_XP + Integer.toString(getExpReward()), 
        };
        return Text.concat(_textList, super.getStatboxText());
    }
}
