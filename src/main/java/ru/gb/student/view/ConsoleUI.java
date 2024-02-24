package ru.gb.student.view;

import ru.gb.student.presenter.Presenter;

/**
 * Консоль пользователя
 * На данном этапе Исходные данные от пользователя установлены принудительно:
 * - количество раундов по условиям задания 1000
 * - количество раундо с детализированной информацией
 */
public class ConsoleUI implements View{

    private final Presenter presenter;
    private int amountOfRound = 1000;
    private int amountOfRoundToShowDetail = 10;

    public ConsoleUI() {
        presenter = new Presenter(this);
    }

    /**
     * Метод передачи презентеру информации о начале игры
     * - количество раундов
     * - количество детализированных раундов
     */
    public void run() {
        presenter.run(amountOfRound, amountOfRoundToShowDetail);
    }


    /**
     * Вывод информации в консоль
     * @param info
     */
    @Override
    public void printInfo(String info) {
        System.out.println(info);
    }
}
