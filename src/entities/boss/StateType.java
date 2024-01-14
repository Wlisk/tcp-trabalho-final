package entities.boss;

/** 
 * Enum para os estados do chefão.
 * @see java.lang.Enum
 * @see entities.boss.Boss
 */
public enum StateType {
    /** Estado normal do chefão */
    BASE,
    /** Estado enraivecido (berserk) do chefão */
    BERSERK,
    /** Estado fraco do chefão */
    WEAK;
}
