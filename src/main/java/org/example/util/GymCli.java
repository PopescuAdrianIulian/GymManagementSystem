package org.example.util;


import org.example.entities.Member;
import org.example.entities.Trainer;
import org.example.entities.TrainingSession;
import org.example.exceptions.MemberNotFoundException;
import org.example.exceptions.TrainerNotFoundException;
import org.example.exceptions.TrainingSessionNotFoundException;
import org.example.repositories.MemberRepository;
import org.example.repositories.TrainerRepository;
import org.example.repositories.TrainingSessionRepository;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Set;

public class GymCli {
    private final MemberRepository memberRepository;
    private final TrainerRepository trainerRepository;
    private final TrainingSessionRepository trainingSessionRepository;


    public GymCli(MemberRepository memberRepository, TrainerRepository trainerRepository, TrainingSessionRepository trainingSessionRepository) {
        this.memberRepository = memberRepository;
        this.trainerRepository = trainerRepository;
        this.trainingSessionRepository = trainingSessionRepository;
    }

    public void start() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Gym Management System");
            System.out.println("1. Manage Members");
            System.out.println("2. Manage Trainers");
            System.out.println("3. Manage Training Sessions");
            System.out.println("4. Exit");
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
                    manageTrainingSession(scanner);
                    break;
                case 4:
                    System.out.println("CLI stopping");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private void manageTrainingSession(Scanner scanner) {
        System.out.println("Training Session Management");
        System.out.println("1. Add Training Session");
        System.out.println("2. Update Training Session");
        System.out.println("3. Delete Training Session");
        System.out.println("4. Assign Trainer for Training Session");
        System.out.println("5. View all members");
        System.out.println("6. Back");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addTrainingSession(scanner);
                break;
            case 2:
                updateTrainingSession(scanner);
                break;
            case 3:
                deleteTrainingSession(scanner);
                break;
            case 4:
                assignTrainer(scanner);
                break;
            case 5:
                viewAllMembers(scanner);
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    private void viewAllMembers(Scanner scanner) {
        System.out.println("Enter Session ID:");
        int sesionId = scanner.nextInt();
        scanner.nextLine();
        TrainingSession trainingSession = trainingSessionRepository.getTrainingSessionById(sesionId);

        if (trainingSession != null) {
            System.out.println("Members for " + trainingSession.getSessionName());
            for (Member m : trainingSession.getMembers()) {
                System.out.println(m.getId() + " " + m.getFirstName() + " " + m.getLastName() + " " + m.getMembershipType());
            }
        } else {
            System.out.println("Session not found!");
        }
    }

    private void assignTrainer(Scanner scanner) {

        System.out.println("Enter Trainer ID:");
        int trainerId = scanner.nextInt();
        System.out.println("Enter Session ID:");
        int sessionId = scanner.nextInt();
        scanner.nextLine();

        Trainer tempTrainer = trainerRepository.getTrainerById(trainerId);
        TrainingSession session = trainingSessionRepository.getTrainingSessionById(sessionId);

        if (tempTrainer != null && session != null) {
            session.setTrainer(tempTrainer);
            trainingSessionRepository.updateTrainingSession(session);
            System.out.println("Trainer enrolled successfully!");
        } else {
            throw new TrainingSessionNotFoundException();
        }
    }

    private void deleteTrainingSession(Scanner scanner) {
        System.out.println("Enter the Training Session id");
        int tempId = scanner.nextInt();
        scanner.nextLine();
        TrainingSession tempTrainingSession = trainingSessionRepository.getTrainingSessionById(tempId);
        if (tempTrainingSession == null) {
            throw new TrainingSessionNotFoundException();
        } else {
            trainingSessionRepository.deleteTrainingSession(tempTrainingSession);
            System.out.println("Deleted successfully!");
        }
    }

    private void updateTrainingSession(Scanner scanner) {
        System.out.println("Enter the Training Session id");
        int tempId = scanner.nextInt();
        scanner.nextLine();
        TrainingSession tempTrainingSession = trainingSessionRepository.getTrainingSessionById(tempId);
        if (tempTrainingSession == null) {
            throw new TrainingSessionNotFoundException();
        } else {

            System.out.println("New Session Name: ");
            String sessionName = scanner.nextLine();

            tempTrainingSession.setSessionName(sessionName);

            trainingSessionRepository.updateTrainingSession(tempTrainingSession);
            System.out.println("Training Session updated successfully!");


        }
    }

    private void addTrainingSession(Scanner scanner) {
        System.out.println("Session Name: ");
        String sessionName = scanner.nextLine();


        TrainingSession tempTrainingSession = new TrainingSession();
        tempTrainingSession.setSessionName(sessionName);
        tempTrainingSession.setSchedule(LocalDateTime.now());

        trainingSessionRepository.createTrainingSession(tempTrainingSession);
        System.out.println("Training session added successfully!");
    }


    private void manageTrainers(Scanner scanner) {
        System.out.println("Trainer Management");
        System.out.println("1. Add Trainer");
        System.out.println("2. Update Trainer");
        System.out.println("3. View Trainers");
        System.out.println("4. Delete Trainer");
        System.out.println("5. view Trainer Schedule");
        System.out.println("6. Back");
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
                viewTrainerSchedule(scanner);
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    private void viewTrainerSchedule(Scanner scanner) {
        System.out.println("Enter Trainer ID:");
        int trainerId = scanner.nextInt();
        scanner.nextLine();
        Trainer trainer = trainerRepository.getTrainerById(trainerId);

        if (trainer != null) {
            System.out.println("Schedule for " + trainer.getFirstName() + " " + trainer.getLastName() + ":");
            for (TrainingSession session : trainer.getTrainingSessions()) {
                System.out.println(session.getSessionName() + " - " + session.getSchedule());
            }
        } else {
            System.out.println("Trainer not found!");
        }
    }


    private void manageMembers(Scanner scanner) {
        System.out.println("Members Management");
        System.out.println("1. Add Members");
        System.out.println("2. Update Members");
        System.out.println("3. View Members");
        System.out.println("4. Delete Members");
        System.out.println("5. Assign Member to Training Session");
        System.out.println("6. View Member Schedule");
        System.out.println("7. Back");
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
                assignMember(scanner);
                break;
            case 6:
                viewMemberSchedule(scanner);
                break;
            case 7:
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    private void viewMemberSchedule(Scanner scanner) {
        System.out.println("Enter Member ID:");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        Member member = memberRepository.getMemberById(memberId);

        if (member != null) {
            System.out.println("schedule for " + member.getFirstName() + " " + member.getLastName() + ":");
            for (TrainingSession session : member.getTrainingSessions()) {
                System.out.println(session.getSessionName() + " - " + session.getSchedule());
            }
        } else {
            System.out.println("Member not found!");
        }
    }

    private void assignMember(Scanner scanner) {

        System.out.println("Enter Member ID:");
        int memberId = scanner.nextInt();
        System.out.println("Enter Session ID:");
        int sessionId = scanner.nextInt();
        scanner.nextLine();

        Member member = memberRepository.getMemberById(memberId);

        if (member != null ) {
            TrainingSession session = trainingSessionRepository.addNewMemberToTrainingSessionById(sessionId, member);
            if(session != null){
                trainingSessionRepository.updateTrainingSession(session);
            }
            System.out.println("Member enrolled successfully!");
        } else {
            throw new TrainingSessionNotFoundException();
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

