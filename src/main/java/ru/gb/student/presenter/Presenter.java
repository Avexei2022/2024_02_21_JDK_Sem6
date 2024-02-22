package ru.gb.student.presenter;

import ru.gb.student.model.Game;
import ru.gb.student.view.View;

public class Presenter {
    private final View view;
    private Game game;

    public Presenter(View view) {
        this.view = view;
    }

    public void run(int amountOfRound, int amountOfRoundToShowDetail) {
        if (amountOfRoundToShowDetail > amountOfRound) {
            amountOfRoundToShowDetail = amountOfRound;
            view.printInfo("\nВведенное количество показа детализированных результатов больше количества раундов,\n " +
                    "в этой связи принудительно приравнено количеству раундов");
        }
        game = new Game(this);
        game.run(amountOfRound, amountOfRoundToShowDetail);
    }

    public void printInfo(String info) {
        view.printInfo(info);
    }

}
