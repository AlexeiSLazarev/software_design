import java.util.Objects;

public final class Unit {
    private final int health;

    public Unit(int health) {
        if (health < 0) {
            throw new IllegalArgumentException("Здоровье не может быть отрицательным.");
        }
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public Unit takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Урон не может быть отрицательным.");
        }
        return new Unit(Math.max(0, this.health - damage));
    }

    @Override
    public String toString() {
        return "Unit{health=" + health + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return health == unit.health;
    }

    @Override
    public int hashCode() {
        return Objects.hash(health);
    }
}

