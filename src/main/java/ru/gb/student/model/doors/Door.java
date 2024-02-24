package ru.gb.student.model.doors;

import lombok.Data;


/**
 * Класс дверей, имеющий следующие состояния:
 * закрыта/открыта
 * есть приз/нет приза
 */
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
