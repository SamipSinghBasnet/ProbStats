public class Stadium {
    public void battle(Pikachu pokemon1,Charmander pokemon2) {
        //normally comapres the speed first
        //put this on  aloop until one is knock out
        //p1 attcks p2 subtract Battle math from hp  the difference of attack and defence
        //check other pokemon if hp is 0
        //p2 attcks p1
        while (pokemon1.getHp() > 0 && pokemon2.getHp() > 0) {
            System.out.println(pokemon1.getClass().getSimpleName() + " HP: " + pokemon1.getHp());
            System.out.println(pokemon2.getClass().getSimpleName() + " HP: " + pokemon2.getHp());

            if (pokemon1.getSpeed() > pokemon2.getSpeed()) {
                // Pokemon 1 attacks first
                int damage = ( pokemon1.getAttack() - pokemon2.getDefense());
                pokemon2.setHp(pokemon2.getHp() - damage);
                System.out.println(pokemon1.getClass().getSimpleName() + " attacks " + pokemon2.getClass().getSimpleName() + " for " + damage + " damage!");

                // Check if Pokemon 2 fainted
                if (pokemon2.getHp() <= 0) {
                    System.out.println(pokemon2.getClass().getSimpleName() + " has fainted!");
                    System.out.println(pokemon1.getClass().getSimpleName() + " wins the battle!");
                    break;
                }

                // Pokemon 2 counterattacks
                int damage2 = ( pokemon2.getAttack() - pokemon1.getDefense());
                pokemon1.setHp(pokemon1.getHp() - damage2);
                System.out.println(pokemon2.getClass().getSimpleName() + " attacks " + pokemon1.getClass().getSimpleName() + " for " + damage2 + " damage!");

                // Check if Pokemon 1 fainted
                if (pokemon1.getHp() <= 0) {
                    System.out.println(pokemon1.getClass().getSimpleName() + " has fainted!");
                    System.out.println(pokemon2.getClass().getSimpleName() + " wins the battle!");
                    break;
                }
            } else {
                // Pokemon 2 attacks first
                int damage = ( pokemon2.getAttack() - pokemon1.getDefense());
                pokemon1.setHp(pokemon1.getHp() - damage);
                System.out.println(pokemon2.getClass().getSimpleName() + " attacks " + pokemon1.getClass().getSimpleName() + " for " + damage + " damage!");

                // Check if Pokemon 1 fainted
                if (pokemon1.getHp() <= 0) {
                    System.out.println(pokemon1.getClass().getSimpleName() + " has fainted!");
                    System.out.println(pokemon2.getClass().getSimpleName() + " wins the battle!");
                    break;
                }

                // Pokemon 1 counterattacks
                int damage2 = ( pokemon1.getAttack() - pokemon2.getDefense());
                pokemon2.setHp(pokemon2.getHp() - damage2);
                System.out.println(pokemon1.getClass().getSimpleName() + " attacks " + pokemon2.getClass().getSimpleName() + " for " + damage2 + " damage!");

                // Check if Pokemon 2 fainted
                if (pokemon2.getHp() <= 0) {
                    System.out.println(pokemon2.getClass().getSimpleName() + " has fainted!");
                    System.out.println(pokemon1.getClass().getSimpleName() + " wins the battle!");
                    break;
                }
            }
        }



    }
}











