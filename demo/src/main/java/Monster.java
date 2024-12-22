import java.util.Objects;

public final class Monster {
    private final int attackPower;

    public Monster(int attackPower) {
        if (attackPower <= 0) {
            throw new IllegalArgumentException("Сила атаки должна быть положительной.");
        }
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public Unit attack(Unit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Юнит не может быть null.");
        }
        return unit.takeDamage(attackPower);
    }

    @Override
    public String toString() {
        return "Monster{attackPower=" + attackPower + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return attackPower == monster.attackPower;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attackPower);
    }

    public static void main(String[] args) {
        Unit unit = new Unit(100);
        Monster monster = new Monster(30);

        System.out.println("Initial unit: " + unit);
        System.out.println("Monster: " + monster);

        unit = monster.attack(unit);
        System.out.println("After attack: " + unit);

        unit = monster.attack(unit);
        System.out.println("After second attack: " + unit);
    }
}
