package ru.gb.student.model;

import ru.gb.student.model.doors.Door;
import ru.gb.student.model.doors.DoorsArray;
import ru.gb.student.model.organizers.MontyHall;
import ru.gb.student.model.players.FirstChoiceForAllPlayers;
import ru.gb.student.model.players.Player;
import ru.gb.student.model.players.PlayersList;
import ru.gb.student.presenter.Presenter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс игры - основной сервисный класс
 * содержит:
 * - ведущего - Монти Холл
 * - список дверей
 * - список игроков
 * - первый выбор двери для всех игроков - уход от глобальной переменной
 * - хранидище результатов игры
 * - количество раундов, данные о которых необходимо получить с детальной информацией
 *
 */
public class Game {
    private final MontyHall montyHall;
    private final DoorsArray doorsArray;
    private final PlayersList playersList;
    private final FirstChoiceForAllPlayers firstChoiceForAllPlayers;
    private final Presenter presenter;
    private final Map<Player, Map<Integer, Boolean>> stat;
    private  int amountOfRoundToShowDetail;


    public Game(Presenter presenter) {
        this.presenter = presenter;
        montyHall = new MontyHall();
        doorsArray = new DoorsArray();
        playersList = new PlayersList();
        firstChoiceForAllPlayers = new FirstChoiceForAllPlayers();
        stat = new HashMap<>();
        amountOfRoundToShowDetail = 1;

    }

    /**
     * Метод начала игры
     * @param amountOfRound количество раундов
     * @param amountOfRoundToShowDetail количество раундов с детализацией результатов
     */
    public void run(int amountOfRound, int amountOfRoundToShowDetail) {
        this.amountOfRoundToShowDetail = amountOfRoundToShowDetail;
        setDoors();
        setPlayersList();
        prepareStat();
        for (int i = 1; i <= amountOfRound; i++) {
            startRound(i);
            clearResultPreviousRound();
        }
        showStat();
    }

    /**
     * Метод формирования списка дверей
     * на данном этапе установлено 3 двери
     */
    private void setDoors() {
        for (int i = 0; i < 3; i++) {
            doorsArray.addDoor(new Door());
        }
    }


    /**
     * Метод фомирования списка игроков
     * с присвоением имени игрока
     * На данном этапе 3 игрока
     */
    private void setPlayersList() {
        for (int i = 0; i < 3; i++) {
            playersList.addPlayer(new Player("Игрок-" + (i+1)));
        }
    }

    /**
     * Метод подготовки хранилища результатов игры - HashMap
     * ключ - Игрок
     * значение - HashMap результатов раундов
     */
    private void prepareStat() {
        for (Player player: playersList.getPlayers()) {
            stat.put(player, new HashMap<>());
        }
    }


    /**
     * Метод одного раунда игры:
     * - ведущий прячет приз
     * - игроки делают свой первый выбор
     * - первый выбор установливается одинаковым для всех игроков
     * - ведущий открывает дверь
     * - игроки делают свой второй выбор
     * @param numOfRound номер раунда для записи результатов
     */
    private void startRound(int numOfRound) {
        montyHall.putPrize(doorsArray);
        firstChoiceForAllPlayers.setRndFirstChoice(doorsArray);
        setFirstChoiceForAllPlayers();
        montyHall.openDoor(doorsArray, firstChoiceForAllPlayers.getRndFirstChoice());
        getResultOfRound(numOfRound);
    }

    /**
     * Метод получения второго выбора игроков
     */
    private void setFirstChoiceForAllPlayers() {
        for (int i = 0; i < playersList.getSize(); i++) {
            playersList.getPlayerByIndex(i).setFirstChoice(firstChoiceForAllPlayers.getRndFirstChoice());
        }
    }

    /**
     * Метод получения результатов раунда
     * подготавливается информация о детализированном раунде о
     * Результатах раунда
     * @param numOfRound Номер раунда
     */
    private void getResultOfRound(int numOfRound) {
        if (amountOfRoundToShowDetail > 0) printInfoAboutDoors(numOfRound);
        for (int i = 0; i < playersList.getSize(); i++) {
            Player player = playersList.getPlayerByIndex(i);
            player.setSecondChoice(doorsArray, i);
            if (amountOfRoundToShowDetail > 0) printInfoAboutResult(player);
            saveResultOfRound(player, numOfRound);
        }
        if (amountOfRoundToShowDetail > 0) amountOfRoundToShowDetail--;
    }

    /**
     * Метод подготовки информации о состоянии дверей
     * и передача информации презентеру
     * @param numOfRound
     */
    private void printInfoAboutDoors(int numOfRound) {
        presenter.printInfo("\nРаунд " + numOfRound);
        for (Door door: doorsArray.getDoors()) {
            presenter.printInfo(door.toString());
        }
    }

    /**
     * Метод подготовки детализированной инфомации о результатах раунда
     * по каждому игроку
     * и передача данной информации презентеру
     * @param player Игрок
     */
    private void printInfoAboutResult(Player player) {
        String string;
        if (doorsArray.findDoorByID(player.getSecondChoice()).isHasPrize()) {
            string = "выйграл";
        } else string = "проиграл";
        presenter.printInfo(player.getName() + " сперва выбрал дверь "
                + player.getFirstChoice() + " затем дверь "
                + player.getSecondChoice() + " в результате "+ string);
    }

    /**
     * Метод записи результата раунда в хранилище
     *
     * @param player Игрок
     * @param numOfRound номер раунда
     */
    private void saveResultOfRound(Player player, int numOfRound) {
        stat.get(player).put(numOfRound
                , doorsArray.findDoorByID(player.getSecondChoice()).isHasPrize());
    }


    /** Метод подготвки итоговой информации по результатам всех раундов
     * и передача информации презентеру
     */
    private void showStat(){
        presenter.printInfo("\nИтог:");
        stat.forEach((k1, v1) -> {
            AtomicInteger i = new AtomicInteger();
            AtomicInteger j = new AtomicInteger();
            v1.forEach((k2, v2) -> {
                if (v2) i.getAndIncrement();
                else j.getAndIncrement();
            });
            String s = switch (k1.getId()) {
                case 0 -> " менял решение";
                case 1 -> " не менял решение";
                case 2 -> " метался с решением";
                default -> "";
            };
            presenter.printInfo(k1.getName() + s + " и выйграл " + i + " раз, проиграл " + j + " раз.");
        });
    }

    /**
     * Метод обнуления результатов раунда
     * Все двери закрываются а приз удаляется
     */
    private void clearResultPreviousRound() {
        doorsArray.getDoors().forEach(e -> {
            e.setHasPrize(false);
            e.setNotOpened(true);});
    }

}
