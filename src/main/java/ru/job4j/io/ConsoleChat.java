package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Класс описывает консольный чат
 * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * - при вводе слова «закончить» программа прекращает работу.
 * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
 */
public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * метод содержит логику чата
     */
    public void run() {
        List<String> listAnswer = new ArrayList<>(readPhrases());
        if (listAnswer.size() == 0) {
            throw new IllegalArgumentException("Список ответов пуст.");
        }
        System.out.println("Консольный чат");
        List<String> listLog = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        boolean answer = true;
        while (run) {
            String line = scanner.nextLine();
            System.out.println(line);
            String ans = listAnswer.get((int) (Math.random() * listAnswer.size()));
            listLog.add(line + System.lineSeparator());
            if (OUT.equals(line)) {
                run = false;
                continue;
            } else if (STOP.equals(line)) {
                answer = false;
            } else if (CONTINUE.equals(line)) {
                answer = true;
                continue;
            }
            if (answer) {
                System.out.println(ans);
                listLog.add(ans + System.lineSeparator());
            }
        }
        saveLog(listLog);
    }

    /**
     * метод читает фразы из файла
     * @return возвращает список строк-фраз из файла с ответами
     */
    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            in.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * метод сохраняет лог чата в файл
     * @param log - список строк (лог чата)
     */
    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            for (String str : log) {
                out.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("result11.txt", "even.txt");
        cc.run();
    }
}
