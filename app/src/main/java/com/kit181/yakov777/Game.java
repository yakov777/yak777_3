package com.kit181.yakov777;

public class Game {

    private Gamer gamer1, gamer2;
    private Gamer activeGamer, firstGamer;
    private int step;
    private Checking checking;

    public Game(Gamer gamer1, Gamer gamer2) {
        this.gamer1 = gamer1;
        this.gamer2 = gamer2;
        firstGamer = gamer2;
        activeGamer = gamer2;
        checking = new Checking();
    }

    private void swapActivePlayer() {
        activeGamer = activeGamer.equals(gamer1) ? gamer2 : gamer1;
    }

    public Gamer getActiveGamer() {
        return activeGamer;
    }

    public String touch(int position) {
        swapActivePlayer();
        if (checkGamerWin(position)) {
            return activeGamer.getName() + " win!";
        } else if (step == 8) {
            return "Draw";
        }
        step++;
        return "";
    }

    private boolean checkGamerWin(int position) {
        activeGamer.setHits(position);
        if (checking.isWin(activeGamer.getHits())) {
            activeGamer.clearHits();
            activeGamer.addWins();
            return true;
        }
        return false;
    }

    public void restart() {
        step = 0;
        gamer1.clearHits();
        gamer2.clearHits();
        checking.restart();
        activeGamer = firstGamer = firstGamer.equals(gamer1) ? gamer2 : gamer1;
        if (firstGamer.equals(gamer2)) {
            gamer2.setSide("O");
            gamer1.setSide("X");
        } else {
            gamer2.setSide("X");
            gamer1.setSide("O");
        }
    }

    public void swapPlayerWithBot(Gamer gamer) {
        gamer2 = gamer;
        activeGamer = firstGamer = gamer2;
        gamer1.clearWins();
        gamer1.setSide("X");
    }


}
