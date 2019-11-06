package ru.ifmo.se.testing.zavoduben.lab2;

import javafx.util.Pair;
import ru.ifmo.se.testing.zavoduben.lab2.logarithm.NaturalLogarithm;
import ru.ifmo.se.testing.zavoduben.lab2.trigonometric.Sine;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String dir = "logs/";
        int numTerms = 50;
        Main main = new Main();
        main.printResult(new NaturalLogarithm(numTerms), -10.0 ,10.0, 0.01, dir + "ln.csv");
        main.printResult(new Sine(numTerms), -10.0 ,10.0, 0.01, dir + "sin.csv");
        main.printResult(new LogarithmExpression(numTerms), -10.0 ,10.0, 0.01, dir + "log_expr.csv");
        main.printResult(new TrigonometricExpression(numTerms), -10.0 ,10.0, 0.01, dir + "trg_expr.csv");
        main.printResult(new SystemSolver(numTerms), -10.0 ,10.0, 0.01, dir + "result.csv");
    }

    public void printResult(
            SolverModule module,
            Double start,
            Double end,
            Double step,
            String filePath
    ) {
        File file = new File(filePath);
        if (file.exists()){
            boolean st = file.delete();
        }
        try {
            boolean created = file.createNewFile();
            Logger logger = new Logger(file);
            Double i = start;
            while (i < end) {
                Pair<String, Double> res = module.evaluate(i);
                logger.write(res.getKey(), i, res.getValue());
                i += step;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
