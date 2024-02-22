package ru.gb.student.model.doors;

import lombok.Data;

import java.util.ArrayList;

@Data
public class DoorsArray {
    private ArrayList<Door> doors = new ArrayList<>();
    private int id = 1;

    public void addDoor(Door door) {
        doors.add(door);
        door.setId(id++);
    }

    public Door findDoorByID(int id) {
        return doors.stream()
                .filter(e -> e.getId() == id)
                .findAny().orElseThrow(null);
    }

    @Override
    public String toString() {
        return "Двери {" + doors +
                '}';
    }
}
