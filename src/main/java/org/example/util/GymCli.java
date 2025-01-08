package org.example.util;


import org.example.entities.Member;
import org.example.entities.Trainer;
import org.example.exceptions.MemberNotFoundException;
import org.example.exceptions.TrainerNotFoundException;
import org.example.repositories.MemberRepository;
import org.example.repositories.TrainerRepository;

import java.util.List;
import java.util.Scanner;

public class GymCli {
    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;


    public GymCli(MemberRepository memberRepository, TrainerRepository trainerRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
    }

    public void start() {
       while(true) {
           Scanner scanner = new Scanner(System.in);
           System.out.println("Gym Management System");
           System.out.println("1. Manage Members");
           System.out.println("2. Manage Trainers");
           System.out.println("3. Exit");
           System.out.print("Choose an option: ");
           int choice = scanner.nextInt();
           scanner.nextLine();

           switch (choice) {
               case 1:
                   manageMembers(scanner);
                   break;
               case 2:
                   manageTrainers(scanner);
                   break;
               case 3:
                   System.out.println("CLI stopping");
                   scanner.close();
                   return;
               default:
                   System.out.println("Invalid choice!");
                   break;
           }
       }
    }


    private void manageTrainers(Scanner scanner) {
        System.out.println("Trainer Management");
        System.out.println("1. Add Trainer");
        System.out.println("2. Update Trainer");
        System.out.println("3. View Trainers");
        System.out.println("4. Delete Trainer");
        System.out.println("5. Back");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addTrainer(scanner);
                break;
            case 2:
                updateTrainer(scanner);
                break;
            case 3:
                viewTrainer(scanner);
                break;
            case 4:
                deleteTrainer(scanner);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }


    private void manageMembers(Scanner scanner) {
        System.out.println("Members Management");
        System.out.println("1. Add Members");
        System.out.println("2. Update Members");
        System.out.println("3. View Members");
        System.out.println("4. Delete Members");
        System.out.println("5. Back");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addMembers(scanner);
                break;
            case 2:
                updateMembers(scanner);
                break;
            case 3:
                viewMember(scanner);
                break;
            case 4:
                deleteMembers(scanner);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    private void deleteMembers(Scanner scanner) {
        System.out.println("Enter the Member id");
        int tempId = scanner.nextInt();
        scanner.nextLine();
        Member tempMember = memberRepository.getMemberById(tempId);
        if (tempMember == null) {
            throw new MemberNotFoundException();
        } else {
            memberRepository.deleteMember(tempMember);
            System.out.println("Deleted successfully!");
        }
    }


    private void viewMember(Scanner scanner) {
        System.out.println("Enter the Member id");
        int tempId = scanner.nextInt();
        scanner.nextLine();
        Member tempMember = memberRepository.getMemberById(tempId);
        if (tempMember == null) {
            throw new MemberNotFoundException();
        } else {
            System.out.println(tempMember.getId());
            System.out.println(tempMember.getFirstName());
            System.out.println(tempMember.getLastName());
            System.out.println(tempMember.getMembershipType());
        }


    }

    private void updateMembers(Scanner scanner) {
        System.out.println("Enter the Member id");
        int tempId = scanner.nextInt();
        scanner.nextLine();
        Member tempMember = memberRepository.getMemberById(tempId);
        if (tempMember == null) {
            throw new MemberNotFoundException();
        } else {

            System.out.println("New First Name: ");
            String firstName = scanner.nextLine();
            System.out.println("New Last Name: ");
            String lastName = scanner.nextLine();
            System.out.println("New membership Type: ");
            String membership = scanner.nextLine();

            tempMember.setFirstName(firstName);
            tempMember.setLastName(lastName);
            tempMember.setMembershipType(membership);

            memberRepository.updateMember(tempMember);
            System.out.println("Member updated successfully!");


        }
    }

    private void addMembers(Scanner scanner) {
        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.println("Membership Type: ");
        String membershipType = scanner.nextLine();

        Member newMember = new Member();
        newMember.setFirstName(firstName);
        newMember.setLastName(lastName);
        newMember.setMembershipType(membershipType);

        memberRepository.createMember(newMember);
        System.out.println("Member added successfully!");

    }


    private void addTrainer(Scanner scanner) {
        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.println("specialization Type: ");
        String specialization = scanner.nextLine();

        Trainer newTrainer = new Trainer();
        newTrainer.setFirstName(firstName);
        newTrainer.setLastName(lastName);
        newTrainer.setSpecialization(specialization);

        trainerRepository.createTrainer(newTrainer);
        System.out.println("Member added successfully!");

    }

    private void updateTrainer(Scanner scanner) {
        System.out.println("Enter the Trainer id");
        int tempId = scanner.nextInt();
        scanner.nextLine();
        Trainer tempTrainer = trainerRepository.getTrainerById(tempId);
        if (tempTrainer == null) {
            throw new TrainerNotFoundException();
        } else {

            System.out.println("New First Name: ");
            String firstName = scanner.nextLine();
            System.out.println("New Last Name: ");
            String lastName = scanner.nextLine();
            System.out.println("New specialization Type: ");
            String specialization = scanner.nextLine();

            tempTrainer.setFirstName(firstName);
            tempTrainer.setLastName(lastName);
            tempTrainer.setSpecialization(specialization);

            trainerRepository.updateTrainer(tempTrainer);
            System.out.println("Member updated successfully!");
        }
    }

    private void viewTrainer(Scanner scanner) {
        System.out.println("Enter the Trainer id");
        int tempId = scanner.nextInt();
        scanner.nextLine();
        Trainer tempTrainer = trainerRepository.getTrainerById(tempId);
        if (tempTrainer == null) {
            throw new TrainerNotFoundException();
        } else {

            System.out.println(tempTrainer.getId());
            System.out.println(tempTrainer.getFirstName());
            System.out.println(tempTrainer.getLastName());
            System.out.println(tempTrainer.getSpecialization());
        }
    }

    private void deleteTrainer(Scanner scanner) {
        System.out.println("Enter the Trainer id");
        int tempId = scanner.nextInt();
        scanner.nextLine();
        Trainer tempTrainer = trainerRepository.getTrainerById(tempId);
        if (tempTrainer == null) {
            throw new TrainerNotFoundException();
        } else {

            trainerRepository.deleteTrainer(tempTrainer);
        }
    }
}

