package ru.gb.student.model.players;

import lombok.Getter;
import lombok.Setter;
import ru.gb.student.model.doors.DoorsArray;

import java.util.Random;

@Getter
@Setter
public class Player {
    @Setter
    private int id = -1;
    private final String name;
        private int firstChoice = -1;
    private int secondChoice = -1;

    public Player(String name) {
        this.name = name;
    }

    public void setSecondChoice(DoorsArray doors, int choiceOption) {
        switch (choiceOption) {
            case 0: {
                secondChoice = findSecondDoor(doors);
            }
             break;
            case 1: secondChoice = firstChoice;
             break;
            case 2: {
                secondChoice = new Random().nextBoolean() ? firstChoice:
                        findSecondDoor(doors);

            }
        }
    }

    private int findSecondDoor(DoorsArray doors) {
        return doors.getDoors().stream()
                .filter(door -> door.isNotOpened() && door.getId() != firstChoice)
                .findAny().orElseThrow().getId();
    }



}
