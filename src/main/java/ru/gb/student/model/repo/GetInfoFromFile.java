package ru.gb.student.model.repo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GetInfoFromFile {
    private final File file;



    public GetInfoFromFile() {
        file = new File("about_game.txt");
    }

    public String getGameInfo() {
        try (FileInputStream fis = new FileInputStream(file)) {
            return new String(fis.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "Информация об игре во внешнем файле не найдена. " + e.getMessage() + getInfo();
        }

    }

    private String getInfo() {
        return "\n\tЗадача формулируется как описание игры, основанной на американской телеигре «Let’s Make a Deal», " +
                "и названа в честь продюсера и первого ведущего этой передачи Монти Холла.\n" +
                "\tНаиболее распространённая формулировка этой задачи звучит следующим образом:\n" +
                "\tПредставьте, что вы стали участником игры, в которой вам нужно выбрать одну из трёх дверей." +
                "За одной из дверей находится автомобиль, за двумя другими дверями — козы.\n" +
                "Вы выбираете одну из дверей, например, номер 1, после этого ведущий, " +
                "который знает, где находится автомобиль, а где — козы, открывает одну из оставшихся дверей, " +
                "например, номер 3, за которой находится коза. После этого он спрашивает вас — " +
                "не желаете ли вы изменить свой выбор и выбрать дверь номер 2?\n" +
                "\tУвеличатся ли ваши шансы выиграть автомобиль, если вы примете предложение ведущего и измените свой выбор?\n";
    }

}
