package be.howest.ti.monopoly.logic.implementation;

import java.util.Objects;

public class Property extends Tile {
    private int cost;
    private int mortgage;
    private int groupSize;
    private int rent;

    public Property(int position, String name, int cost, int mortgage, int groupSize, int rent) {
        super(position, name);
        this.cost = cost;
        this.mortgage = mortgage;
        this.groupSize = groupSize;
        this.rent = rent;
    }

    public int getCost() {
        return cost;
    }

    public int getMortgage() {
        return mortgage;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public int getRent() {
        return rent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Property property = (Property) o;
        return cost == property.cost && mortgage == property.mortgage && groupSize == property.groupSize && rent == property.rent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cost, mortgage, groupSize, rent);
    }
}
