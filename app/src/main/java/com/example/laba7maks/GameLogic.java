package com.example.laba7maks;

public class GameLogic {
    private int secretNumber;
    private int attempts;
    private int maxAttempts = 15;
    private int min = 1;
    private int max = 100;
    private boolean isGameOver;

    public GameLogic() {
        resetGameClassic();
        resetGamePlayerNum();
    }

    public void resetGameClassic() {
        secretNumber = (int) (Math.random() * 100) + 1;
        attempts = 0;
        isGameOver = false;
    }
    public void resetGamePlayerNum() {
        min = 1;
        max = 100;
        attempts = 0;
        isGameOver = false;
    }

    public String checkGuess(int guess) {
        attempts++;

        if (guess == secretNumber) {
            isGameOver = true;
            return "Поздравляем! Вы угадали число за " + attempts + " попыток.\nЗагаданное число: "+ secretNumber + "\nТеперь угадайте новое число!";
        }

        if (attempts >= maxAttempts) {
            isGameOver = true;
            return "Вы проиграли! Не угадали число за " + maxAttempts + " попыток.\nЗагаданное число: " + secretNumber + "\nТеперь угадайте новое число!";
        }

        return guess < secretNumber ? "Загаданное число больше" : "Загаданное число меньше";
    }

    public void setSecretNumber(int number) {
        this.secretNumber = number;
        resetGamePlayerNum();
    }


    public int checkNumPlayer(int num, boolean high) {
        attempts++;
        if (num == secretNumber) {
            isGameOver = true;
            return num;
        }
        if (high) {
            min = Math.max(min, num + 1);  // Не выходим за границы
        } else {
            max = Math.min(max, num - 1);
        }
        if (min >= max) {
            isGameOver = true;  // Логическая ошибка (пользователь соврал)
        }
        return (max + min) / 2;
    }

    public int getRemainingAttempts() {
        return maxAttempts - attempts;
    }

    public boolean isGameOver() {
        return isGameOver || attempts >= maxAttempts;
    }

    public int getSecretNumber() {
        return secretNumber;
    }
    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }
}