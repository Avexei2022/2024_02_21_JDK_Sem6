package ru.gb.student.model.organizers;

import lombok.Data;
import ru.gb.student.model.doors.Door;
import ru.gb.student.model.doors.DoorsArray;

import java.util.List;
import java.util.Random;

@Data
public class MontyHall {
    private String name = "Monty Hall";
    private int numDoorWithPrize = -1;
    private int numDoorToOpen = - 1;
    private Random random = new Random();

    public void putPrize (DoorsArray doors) {
        int doorIdToPutPrize = random.nextInt(doors.getDoors().size()) + 1;
        Door doorPrized = doors.getDoors().stream()
                .filter(door -> door.getId()==doorIdToPutPrize)
                .findAny().orElseThrow();
        doorPrized.setHasPrize(true);
    }

    public void openDoor(DoorsArray doors, int firstChoiceForAllPlayers) {
        List<Door> doorListToOpen = doors.getDoors().stream()
                        .filter(door -> !door.isHasPrize()
                                && door.getId() != firstChoiceForAllPlayers)
                        .toList();
        if (doorListToOpen.size() ==1) doorListToOpen.getFirst().setNotOpened(false);
        else {
            int rndInt = random.nextInt(doorListToOpen.size());
            doorListToOpen.get(rndInt).setNotOpened(false);
        }

    }
}
