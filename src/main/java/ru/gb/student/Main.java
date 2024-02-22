package ru.gb.student;


import ru.gb.student.view.ConsoleUI;
import ru.gb.student.view.GUI;

public class Main {

    public static void main(String[] args) {
        if (args.length !=0 && args[0].equals("-console")) new ConsoleUI().run();
        else new GUI();
    }
}
