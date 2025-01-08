package org.example;

import org.example.repositories.MemberRepository;
import org.example.repositories.TrainerRepository;
import org.example.util.GymCli;

public class Main {
    private static final MemberRepository memberRepository = new MemberRepository();
    private static final TrainerRepository trainerRepository = new TrainerRepository();
    private static final GymCli gymCli =new GymCli(memberRepository,trainerRepository);
    public static void main(String[] args) {

        gymCli.start();



    }
}