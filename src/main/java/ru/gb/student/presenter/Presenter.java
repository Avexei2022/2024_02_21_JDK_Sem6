package ru.gb.student.presenter;

import ru.gb.student.model.Game;
import ru.gb.student.model.repo.GetInfoFromFile;
import ru.gb.student.view.View;

/**
 * Класс презентера
 */
public class Presenter {
    private final View view;
    private Game game;

    public Presenter(View view) {
        this.view = view;
    }

    /**
     * Метод начала игры
     * Проверяются параметры количества раундов игры и количества детализированных раундов,
     * полученных от пользователя
     * @param amountOfRound  количество раундов игры
     * @param amountOfRoundToShowDetail количество детализированных раундов
     */
    public void run(int amountOfRound, int amountOfRoundToShowDetail) {
        if (amountOfRoundToShowDetail > amountOfRound) {
            amountOfRoundToShowDetail = amountOfRound;
            view.printInfo("\nВведенное количество показа детализированных результатов больше количества раундов,\n " +
                    "в этой связи принудительно приравнено количеству раундов");
        }
        game = new Game(this);
        game.run(amountOfRound, amountOfRoundToShowDetail);
    }

    /**
     * Метод передачи информации пользователю
     * @param info
     */
    public void printInfo(String info) {
        view.printInfo(info);
    }

    public void getTxtAboutGame() {
        String info = new GetInfoFromFile().getGameInfo();
        view.printInfo(info);
    }
}
