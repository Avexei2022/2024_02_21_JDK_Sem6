package ru.gb.student.model.players;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PlayersList {
    private ArrayList<Player> players = new ArrayList<>();
    private int id = 0;

    public void addPlayer(Player player) {
        players.add(player);
        player.setId(id++);
    }

    public int getSize() {
        return players.size();
    }

    public Player getPlayerByIndex(int i) {
        return players.get(i);
    }
}
