package ru.gb.student.view;

import ru.gb.student.presenter.Presenter;

import javax.swing.*;
import java.awt.*;

/**
 * Графический интерфейс пользователя
 */
public class GUI extends JFrame implements View {

    private final int frameWidth;
    private final int frameHeight;
    private JTextArea infoArea;

    private JTextField tfAmountOfRound, tfAmountOfRoundToShowDetail;
    private final JPanel panelTop;
    private JScrollPane jScrollPane;
    private final Presenter presenter;
    private int amountOfRound = 1000;
    private int amountOfRoundToShowDetail = 10;

    public GUI() {
        presenter = new Presenter(this);
        Rectangle rectangle = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        frameWidth = rectangle.width/2;
        frameHeight = rectangle.height/2;
        panelTop = new JPanel();
        setting();
    }

    /**
     * Установки основного окна
     */
    private void setting() {
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Парадокс Монти Холла");
        createMainPanel();
        setVisible(true);

    }

    /**
     * Добавление элементов в соновное окно
     */
    private void createMainPanel() {
        add(createPanelTop(), BorderLayout.NORTH);
        add(createInfoArea(), BorderLayout.CENTER);
    }

    /** Создание верхней панели
     * @return верхняя панель
     */
    private Component createPanelTop() {

        tfAmountOfRound = new JTextField();
        tfAmountOfRound.setText(String.valueOf(amountOfRound));
        tfAmountOfRoundToShowDetail = new JTextField();
        tfAmountOfRoundToShowDetail.setText(String.valueOf(amountOfRoundToShowDetail));
        panelTop.add(new JLabel("Введите количество раундов"));
        panelTop.add(tfAmountOfRound);
        panelTop.add(new JLabel("Введите количество детализированных раундов"));
        panelTop.add(tfAmountOfRoundToShowDetail);
        JButton btnStart = new JButton("Старт");
        btnStart.addActionListener(e -> run());
        panelTop.add(btnStart);
        return panelTop;
    }

    /**
     * Создание Зоны вывода информации
     * @return Зона вывода информации
     */
    private Component createInfoArea() {
        infoArea = new JTextArea();
        infoArea.setMargin(new Insets(10, 10, 10, 10));
        infoArea.setText(getTxtAboutGame());
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);
        infoArea.setTabSize(3);
        jScrollPane = new JScrollPane(infoArea);
        return jScrollPane;
    }

    /**
     * Метод передачи презентеру информации о запуске программы
     * с аргументами количества раундов
     * и количества детализированных раундов
     */
    private void run() {
        try {
            amountOfRound = Integer.parseInt(tfAmountOfRound.getText());
            amountOfRoundToShowDetail = Integer.parseInt(tfAmountOfRoundToShowDetail.getText());
            presenter.run(amountOfRound, amountOfRoundToShowDetail);
        } catch (NumberFormatException e) {
            printInfo("\nПроверьте введенные данные");
        }
    }

    /**
     * Вывод информации пользователю
     * @param info
     */
    @Override
    public void printInfo(String info) {
        infoArea.append(info + "\n");
    }

    /**
     * Текстовая информация об игре
     * пока в ткаом виде
     * @return текст с информацией
     */
    private String getTxtAboutGame() {
        return "\tЗадача формулируется как описание игры, основанной на американской телеигре «Let’s Make a Deal», " +
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
