package be.howest.ti.monopoly.logic.implementation.non_properties;

import java.util.Objects;

public class CommunityCard {
    private String card;

    public CommunityCard(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommunityCard that = (CommunityCard) o;
        return Objects.equals(card, that.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card);
    }
}
