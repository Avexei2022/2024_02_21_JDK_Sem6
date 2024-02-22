package ru.gb.student.view;

import ru.gb.student.presenter.Presenter;

public class ConsoleUI implements View{

    private final Presenter presenter;
    private int amountOfRound = 1000;
    private int amountOfRoundToShowDetail = 5;

    public ConsoleUI() {
        presenter = new Presenter(this);
    }

    public void run() {
        presenter.run(amountOfRound, amountOfRoundToShowDetail);
    }

    @Override
    public void printInfo(String info) {
        System.out.println(info);
    }
}
