package ru.gb.student.model.players;

import lombok.Data;

import java.util.ArrayList;

/**
 * Список игроков
 */
@Data
public class PlayersList {
    private ArrayList<Player> players = new ArrayList<>();
    private int id = 0;

    /**
     * Метод добавления нового игрока
     * @param player Игрок
     */
    public void addPlayer(Player player) {
        players.add(player);
        player.setId(id++);
    }

    /**
     * Метод получения количества игроков
     * @return
     */
    public int getSize() {
        return players.size();
    }

    /**
     * Метод получения игрока по его порядку в списке игроков
     * @param i Индекс в списке
     * @return Игрок
     */
    public Player getPlayerByIndex(int i) {
        return players.get(i);
    }
}
