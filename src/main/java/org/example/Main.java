package org.example;

import org.example.entities.Progress;
import org.example.repositories.*;
import org.example.util.GymCli;

public class Main {
    private static final GymCli gymCli = new GymCli();
    public static void main(String[] args) {
        gymCli.start();
    }
}