public class Pokemon {
    private int hp;
    private int attack;
    private int defense;
    private int spAttack;
    private int spDefense;
    private int speed;

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHp() {
        return hp;
    }

    public int getSpAttack() {
        return spAttack;
    }

    public int getSpDefense() {
        return spDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setAttack(int userAttack) {
        attack = userAttack;
    }
    public void setDefense(int userDefense) {
        defense = userDefense;

    }
    public void setHp(int userHp) {
        hp = userHp;
    }

    public void setSpAttack(int UserSpAttack) {
        spAttack = UserSpAttack;
    }
    public void setSpDefense(int UserSpDefense) {
        spDefense = UserSpDefense;
    }
    public void setSpeed(int UserSpeed) {
        speed = UserSpeed;
    }

}
