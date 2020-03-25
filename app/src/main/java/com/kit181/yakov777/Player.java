package com.kit181.yakov777;

import java.util.ArrayList;
import java.util.Objects;

public class Player implements Gamer {

    private String name, side;
    private ArrayList<Integer> hits;
    private int wins;
    private int color;

    public Player(String name, String side, int color) {
        this.name = name;
        this.side = side;
        this.color = color;
        clearHits();
    }

    @Override
    public void clearHits() {
        hits = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            hits.add(0);
        }
    }

    @Override
    public ArrayList<Integer> getHits() {
        return hits;
    }

    @Override
    public void setHits(int position) {
        hits.set(position, 1);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSide() {
        return side;
    }

    @Override
    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public void addWins() {
        wins++;
    }

    @Override
    public void clearWins() {
        wins = 0;
    }


    @Override
    public int getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(side, player.side);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, side);
    }
}
