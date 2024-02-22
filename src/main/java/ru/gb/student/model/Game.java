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
    private void setDoors() {
        for (int i = 0; i < 3; i++) {
            doorsArray.addDoor(new Door());
        }
    }

    private void setPlayersList() {
        for (int i = 0; i < 3; i++) {
            playersList.addPlayer(new Player("Игрок-" + (i+1)));
        }
    }

    private void prepareStat() {
        for (Player player: playersList.getPlayers()) {
            stat.put(player, new HashMap<>());
        }
    }

    private void startRound(int numOfRound) {
        montyHall.putPrize(doorsArray);
        firstChoiceForAllPlayers.setRndFirstChoice(doorsArray);
        setFirstChoiceForAllPlayers();
        montyHall.openDoor(doorsArray, firstChoiceForAllPlayers.getRndFirstChoice());
        getResultOfRound(numOfRound);
    }

    private void setFirstChoiceForAllPlayers() {
        for (int i = 0; i < playersList.getSize(); i++) {
            playersList.getPlayerByIndex(i).setFirstChoice(firstChoiceForAllPlayers.getRndFirstChoice());
        }
    }
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

    private void printInfoAboutDoors(int numOfRound) {
        presenter.printInfo("\nРаунд " + numOfRound);
        for (Door door: doorsArray.getDoors()) {
            presenter.printInfo(door.toString());
        }
    }

    private void printInfoAboutResult(Player player) {
        String string;
        if (doorsArray.findDoorByID(player.getSecondChoice()).isHasPrize()) {
            string = "выйграл";
        } else string = "проиграл";
        presenter.printInfo(player.getName() + " сперва выбрал дверь "
                + player.getFirstChoice() + " затем дверь "
                + player.getSecondChoice() + " в результате "+ string);
    }

    private void saveResultOfRound(Player player, int numOfRound) {
        stat.get(player).put(numOfRound
                , doorsArray.findDoorByID(player.getSecondChoice()).isHasPrize());
    }

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

    private void clearResultPreviousRound() {
        doorsArray.getDoors().forEach(e -> {
            e.setHasPrize(false);
            e.setNotOpened(true);});
    }

}
