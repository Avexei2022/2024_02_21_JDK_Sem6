package ru.gb.student.model.players;

import lombok.Getter;
import ru.gb.student.model.doors.DoorsArray;
import java.util.Random;


/**
 * Класс первого выбора игрока.
 * Вынесен отдельно для синхронизации выбора всех игроков.
 */
@Getter
public class FirstChoiceForAllPlayers {
    private int rndFirstChoice;
    public FirstChoiceForAllPlayers() {
    }

    /**
     * Случайный выбор из списка дверей
     * @param doorsArray
     */
    public void setRndFirstChoice(DoorsArray doorsArray) {
        rndFirstChoice = new Random().nextInt(doorsArray.getDoors().size()) + 1;
    }

}
