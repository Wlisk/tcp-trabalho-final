package entities.boss;

import entities.Entity;
import utils.Number;
import utils.exceptions.NumberOverflowException;

public class Boss extends Entity {
    private int expReward;
    private StateType currState;
    private double percentageDefend;
    private double percentageBerserk;
    private String description;

    public Boss(
        String name, 
        int expReward,
        double percentageBerserk, double percentageDefend, 
        String description
    )
    throws NumberOverflowException
    {
        super(name);

        Number.limitTo(Number.MIN_PERCENTAGE, Number.MAX_PERCENTAGE, percentageBerserk);
        Number.limitTo(Number.MIN_PERCENTAGE, Number.MAX_PERCENTAGE, percentageDefend);

        currState = StateType.IDLE;
        this.expReward = expReward;
        this.percentageBerserk = percentageBerserk;
        this.percentageDefend = percentageDefend;
        this.description = description;
    }

    /*
    - boss starts with IDLE, after being attacked switch to ATTACK
    - after BERSERK the boss becomes WEAK
    - the boss becomes BERSERK after HP becomes less then 30% of total HP
    - the boss switches to DEFENSE if MP bellow 20% of total MP
    */
    public StateType nextState() {
        switch(currState) {
            case IDLE: 
                currState = StateType.ATTACK;
                break;

            case ATTACK: 
                if(reachedStateBerserk())
                    currState = StateType.BERSERK;
                else if(reachedStateDefend())
                    currState = StateType.DEFEND;
                else if(reachedStateAttackSuper())
                    currState = StateType.ATTACK_SUPER;
                break;

            case DEFEND:
                if(reachedStateBerserk())
                    currState = StateType.BERSERK;
                else if(reachedStateAttackSuper())
                    currState = StateType.ATTACK_SUPER;
                else currState = StateType.ATTACK;
                break;

            case BERSERK:
                currState = StateType.WEAK;
                break;

            case WEAK:
                // TODO: cannot defend anymore
                // TODO: cannot berserk anymore
                // TODO: 0.5 must be a settable variable, so defenses can weaken depending of the boss
                setCurrDefense( (int)(getCurrDefense() * 0.5) );
                currState = StateType.ATTACK;
                break;

            // TODO: logic for
            case ATTACK_SUPER:
                break;
            case DEFEND_SUPER:
                break;

            default: 
                break;
        }
        return currState;
    }

    public StateType getCurrState() { return currState; }
    public double getPercentageBerserk() { return percentageBerserk; }
    public double getPercentageDefend() { return percentageDefend; }
    public String getDescription() { return description; }
    public int getExpReward() { return this.expReward; }

    // TODO: BERSERK can only be reached one time
    // or more, depending of the boss logic
    private boolean reachedStateBerserk() {
        int percentageCurrHP = (int)(getCurrHP() * percentageBerserk);
        int percentageMaxHP = (int)(getMaxHP() * percentageBerserk);
        return percentageCurrHP <= percentageMaxHP;
    }

    // TODO: DEFEND can only be reached once every 80% MP loss
    private boolean reachedStateDefend() {
        int percentageCurrMP = (int)(getCurrMP() * percentageDefend);
        int percentageMaxMP = (int)(getMaxMP() * percentageDefend);
        return percentageCurrMP <= percentageMaxMP;
    }

    // TODO: logic for 
    private boolean reachedStateAttackSuper() { 
        // attacks with super when MP greater then X%?
        return false; 
    }
    // There is a need for DefendSuper?
    /*private boolean reachedStateDefendSuper() { 
        // defends super when enemy MP greater then x%?
        return false; 
    }*/
}
