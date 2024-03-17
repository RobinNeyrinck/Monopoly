package be.howest.ti.monopoly.logic.implementation.non_properties;

import java.util.Objects;

public class ChanceCard {
    private String chance;

    public ChanceCard(String chance) {
        this.chance = chance;
    }

    public String getChance() {
        return chance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChanceCard chance1 = (ChanceCard) o;
        return Objects.equals(chance, chance1.chance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chance);
    }
}
