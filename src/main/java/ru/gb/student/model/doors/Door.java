package ru.gb.student.model.doors;

import lombok.Data;

@Data
public class Door {
    private int id = -1;
    private boolean isHasPrize = false;
    private boolean isNotOpened = true;

    @Override
    public String toString() {
        return  "Дверь №" + id +
                ", Приз - " + isHasPrize +
                ", Закрыта - " + isNotOpened;
    }
}
