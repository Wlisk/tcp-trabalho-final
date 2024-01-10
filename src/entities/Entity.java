package entities;

import static com.raylib.Jaylib.Vector2;
import static com.raylib.Jaylib.Texture;
import com.raylib.Jaylib.Vector2;
import com.raylib.Jaylib;

import utils.Text;
import exceptions.EmptyStringException;
import exceptions.MaxStringSizeException;
import exceptions.NumberOverflowException;
import exceptions.TextureNotLoadedException;

// TODO: setters must be completed with guard statements
// - 'if' must be put to guard against values below 0 or 0.0

public class Entity {
    public static final int MAX_NAME_SIZE = 32;
    public static final int ATTACK_MISSED = 0;
    public static final int DEFENDED = -1;
    public static final double MAX_BASE_CRIT = 1.0;
    public static final double MAX_BASE_ACCUR = 1.0;
    public static final double MAX_CRIT_MULTIPLIER = 10.0;
    public static final double DEFEND_DEFENSEMULT_CHANGE = 0.75;
    public static final double MIN_DAMAGE_POST_REDUCTION = 0.2;
    private static int countEntities = 0;

    private String name;
    private boolean isDead;

    private int maxHP;
    private int maxMP;
    private int baseDamage;
    private int baseDefense;
    private int baseRecRateHP;
    private int baseRecRateMP;
    private double baseCritChance;
    private double baseCritMultiplier;
    private double baseAccuracy;
    private double baseDefenseMultiplier;

    private int currHP;
    private int currMP; 
    private int currDamage;
    private int currDefense;
    private int currRecRateHP;
    private int currRecRateMP;
    private double currCritChance;
    private double currCritMultiplier;
    private double currAccuracy;
    private double currDefenseMultiplier;

    private int defendDuration;
    
    // TODO: variables yet to create and impement
    // Sprite sprite;
    // Vector2D position; 
    // Super[] supers = new Super[MAX_SUPERS];

    // drawing related variables
    private Vector2 position;
    private String textureSrc;
    private Texture texture;
    private float imageScale;

    public Entity(String name, String textureSrc) {
        resetToZero();
        this.name = name;
        this.position = new Vector2(0.0f, 0.0f);
        this.textureSrc = textureSrc;
        this.texture = texture;
        this.imageScale = 1.0f;
        ++countEntities;
    }

    protected void resetToZero() {
        maxHP = maxMP = 0;
        currHP = currMP = 0;
        baseDamage = baseDefense = 0;
        currDefense = currDamage = 0;
        currRecRateHP = currRecRateMP = 0;
        baseRecRateHP = baseRecRateMP = 0;
        baseAccuracy = baseCritMultiplier = baseCritChance = 0.0;
        currAccuracy = currCritMultiplier = currCritChance = 0.0;
        currDefenseMultiplier = baseDefenseMultiplier = 0.0;
        resetBooleans();
    }

    protected void resetToBase() {
        currHP = maxHP;
        currMP = maxMP;
        currDamage = baseDamage;
        currDefense = baseDefense;
        currCritChance = baseCritChance;
        currCritMultiplier = baseCritMultiplier;
        currAccuracy = baseAccuracy;
        currRecRateHP = baseRecRateHP;
        currRecRateMP = baseRecRateMP;
        currDefenseMultiplier = baseDefenseMultiplier;
        resetBooleans();
    }

    public void takeDamage(int damage) {
        int _hpWithLoss = currHP - damage;

        if (_hpWithLoss <= 0) { 
            currHP = 0;
            isDead = true;
        }else {
            currHP = _hpWithLoss;
        }
    }

    // Defending increases an entity's defense multiplier by 75% for 3 turns. The counter is lowered at the end of every turn
    public int defend() {
        if (defendDuration == 0){
            this.currDefenseMultiplier += DEFEND_DEFENSEMULT_CHANGE;
        }
        this.defendDuration = 3;
        return DEFENDED;
    }

    public void updateEndTurn(){
        if (this.defendDuration == 1){ // If defending has run out, lower the defense multiplier
            this.currDefenseMultiplier -= DEFEND_DEFENSEMULT_CHANGE;
        }

        if (this.defendDuration > 0){
            this.defendDuration--;
        }

        recoverMP(maxMP / 10); // Entities recover 10% of their max mp per turn
    }

    protected int healHP(int amount) {
        if(amount >= 0) {
            int _hpWithHeal = currHP + amount;

            currHP = (_hpWithHeal > maxHP) ? maxHP : _hpWithHeal;
        }

        return currHP;
    }

    protected int recoverMP(int amount) {
        if(amount >= 0) {
            int _mpWithRecover = currMP + amount;

            currMP = (_mpWithRecover > maxMP) ? maxMP : _mpWithRecover;
        }

        return currMP;
    }

    // OUTROS

    /** Loads the Entity texture to memory */
    public Texture loadTexture() {
        Jaylib.LoadTexture(texture);
        return texture;
    }

    /** Unloads the Entity texture from memory */
    public void unloadTexture() {
        Jaylib.UnloadTexture(texture);
    }

    /**
     * Gets the Entity texture
     * @return the texture
     * @throws extureNotLoadedException if the texture is null or not found in memory
     */
    public Texture getTexture() throws TextureNotLoadedException {
        if(texture == null) throw new TextureNotLoadedException("Texture not loaded or found in memory");

        return texture;
    }

    // --------------------------- GETTERS --------------------------- //
    public String getName() { return name; }
    public boolean getIsDead() { return isDead; }

    public int getDefendDuration() { return defendDuration; }
    public int getMaxHP() { return maxHP; }
    public int getMaxMP() { return maxMP; }
    public int getCurrHP() { return currHP; }
    public int getCurrMP() { return currMP; }
    public int getBaseDefense() { return baseDefense; }
    public int getBaseDamage() { return baseDamage; }
    public int getCurrDamage() { return currDamage; }
    public int getCurrDefense() { return currDefense; }
    public int getCurrRecRateHP() { return currRecRateHP; }
    public int getBaseRecRateHP() { return baseRecRateHP; }
    public int getCurrRecRateMP() { return currRecRateMP; }
    public int getBaseRecRateMP() { return baseRecRateMP; }

    public double getBaseCritChance() { return baseCritChance; }
    public double getCurrCritChance() { return currCritChance; }
    public double getBaseAccuracy() { return baseAccuracy; }
    public double getCurrAccuracy() { return currAccuracy; }
    public double getBaseCritMultiplier() { return baseCritMultiplier; }
    public double getCurrCritMultiplier() { return currCritMultiplier; }
    public double getBaseDefenseMultiplier() { return baseDefenseMultiplier; }
    public double getCurrDefenseMultiplier() { return currDefenseMultiplier; }

    public float posX() { return position.x(); }
    public float posY() { return position.y(); }
    public Vector2 pos() { return position; }

    public float setImageScale(float scale) { this.imageScale = scale; return this.imageScale; }
    public float getImageScale() { return this.imageScale; }
    

    // --------------------------- SETTERS --------------------------- //
    protected void setName(String name) throws EmptyStringException, MaxStringSizeException { 
        if (name.length() > MAX_NAME_SIZE) 
            throw new MaxStringSizeException("Max string size is " + MAX_NAME_SIZE);
        else if (Text.stringIsEmpty(name))
            throw new EmptyStringException("Name cannot be null");

        this.name = name;
    }

    protected void setIsDead(boolean isDead){
        this.isDead = isDead;
    }

    protected void setDefendDuration(int defendDuration) {
        this.defendDuration = defendDuration;
    }

    protected void setMaxHP(int maxHP) { 
        this.maxHP = maxHP; 
    }

    protected void setMaxMP(int maxMP) { 
        this.maxMP = maxMP; 
    }

    protected void setCurrHP(int hp) throws NumberOverflowException {
        if (currHP < 0 || currHP > maxHP)
            throw new NumberOverflowException(
                getMessageNumberOverflow("HP", maxHP)
            );

        currHP = hp;
    }

    protected void setCurrMP(int mp) throws NumberOverflowException {
        if (currMP < 0 || currMP > maxMP)
            throw new NumberOverflowException(
                getMessageNumberOverflow("MP", maxMP)
            );

        this.currMP = mp;
    }

    protected void setBaseDefense(int baseDefense) { 
        this.baseDefense = baseDefense; 
    }

    protected void setBaseDamage(int baseDamage) { 
        this.baseDamage = baseDamage; 
    }

    public void setCurrDamage(int currDamage) {
        this.currDamage = currDamage;
    }

    public void setCurrDefense(int currDefense) {
        this.currDefense = currDefense;
    }

    protected void setBaseCritChance(double baseCritChance) {
        if(baseCritChance > MAX_BASE_CRIT) this.baseCritChance = MAX_BASE_CRIT;
        else if(baseCritChance < 0.0) this.baseCritChance = 0.0;
        else this.baseCritChance = baseCritChance;
    }

    public void setCurrCritChance(double currCritChance) {
        this.currCritChance = currCritChance;
    }

    protected void setBaseAccuracy(double baseAccuracy) {
        if(baseAccuracy > MAX_BASE_ACCUR) this.baseAccuracy = MAX_BASE_ACCUR;
        else if(baseAccuracy < 0.0) this.baseAccuracy = 0.0;
        else this.baseAccuracy = baseAccuracy;
    }
    
    public void setCurrAccuracy(double currAccuracy) {
        this.currAccuracy = currAccuracy;
    }

    protected void setCurrRecRateHP(int recRate) {
        this.currRecRateHP = recRate;
    }

    public void setBaseRecRateHP(int baseRecRateHP) {
        this.baseRecRateHP = baseRecRateHP;
    }

    protected void setCurrRecRateMP(int recRate) {
        this.currRecRateMP = recRate;
    }

    public void setBaseRecRateMP(int baseRecRateMP) {
        this.baseRecRateMP = baseRecRateMP;
    }

    protected void setBaseCritMultiplier(double critValue) {
        if(critValue > MAX_CRIT_MULTIPLIER) this.baseCritMultiplier = MAX_CRIT_MULTIPLIER;
        else if(critValue < 0.0) this.baseCritMultiplier = 0.0;
        else this.baseCritMultiplier = critValue;
    }

    protected void setCurrCritMultiplier(double critValue) {
        if(critValue > MAX_CRIT_MULTIPLIER) this.currCritMultiplier = MAX_CRIT_MULTIPLIER;
        else if(critValue < 0.0) this.currCritMultiplier = 0.0;
        else this.currCritMultiplier = critValue;
    }

    public void setBaseDefenseMultiplier(double baseDefenseMultiplier) {
        this.baseDefenseMultiplier = baseDefenseMultiplier;
    }

    public void setCurrDefenseMultiplier(double currDefenseMultiplier) {
        this.currDefenseMultiplier = currDefenseMultiplier;
    }

    public float posX(float x) { return position.x(x).x(); }
    public float posY(float y) { return position.y(y).y(); }
    public Vector2 pos(float x, float y) { 
        position.x(x);
        position.y(y);
        return position;
    }


    // --------------------------- STATIC RELATED --------------------------- //
    public static int getCountEntities() { return countEntities; }


    // --------------------------- PRIVATE --------------------------- //
    private static String getMessageNumberOverflow(String attribute, int maxvalue) {
        return attribute + " cannot be greater then " + maxvalue + " or lower then 0";
    }

    private void resetBooleans() {
        isDead = false;
    }
}