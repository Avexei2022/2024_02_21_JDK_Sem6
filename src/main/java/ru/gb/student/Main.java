package ru.gb.student;


import ru.gb.student.view.ConsoleUI;
import ru.gb.student.view.GUI;

/** точка входа
 * при поступлении аргумента -console
 * взаимодействие с пользователем через консоль
 * В остальных случаях через графический интерфейс
 */
public class Main {

    public static void main(String[] args) {
        if (args.length !=0 && args[0].equals("-console")) new ConsoleUI().run();
        else new GUI();
    }
}
