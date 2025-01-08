package org.example;

import org.example.repositories.MemberRepository;
import org.example.repositories.TrainerRepository;
import org.example.repositories.TrainingSessionRepository;
import org.example.util.GymCli;

public class Main {
    private static final MemberRepository memberRepository = new MemberRepository();
    private static final TrainerRepository trainerRepository = new TrainerRepository();
    private static final TrainingSessionRepository trainingSessionRepository = new TrainingSessionRepository();

    private static final GymCli gymCli =new GymCli(memberRepository,trainerRepository,trainingSessionRepository);
    public static void main(String[] args) {

        gymCli.start();



    }
}