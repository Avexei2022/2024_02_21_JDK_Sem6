package ru.gb.student.model.players;

import lombok.Getter;
import ru.gb.student.model.doors.DoorsArray;


import java.util.Random;

@Getter
public class FirstChoiceForAllPlayers {
    private int rndFirstChoice;
    public FirstChoiceForAllPlayers() {
    }

    public void setRndFirstChoice(DoorsArray doorsArray) {
        rndFirstChoice = new Random().nextInt(doorsArray.getDoors().size()) + 1;
    }

}
