package org.example.util;


import org.example.entities.*;
import org.example.exceptions.MemberNotFoundException;
import org.example.exceptions.TrainerNotFoundException;
import org.example.exceptions.TrainingSessionNotFoundException;
import org.example.repositories.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class GymCli {
    private static final MemberRepository memberRepository = new MemberRepository();
    private static final TrainerRepository trainerRepository = new TrainerRepository();
    private static final TrainingSessionRepository trainingSessionRepository = new TrainingSessionRepository();
    private static final ProgressRepository progressRepository = new ProgressRepository();
    private static final SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
    private static final EquipmentRepository equipmentRepository = new EquipmentRepository();
    private static final ReservationRepository reservationRepository = new ReservationRepository();

    public GymCli() {
    }

    public void start() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Gym Management System");
            System.out.println("1. Manage Members");
            System.out.println("2. Manage Trainers");
            System.out.println("3. Manage Training Sessions");
            System.out.println("4. Manage Equipment");
            System.out.println("5. Exit");
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
                    manageEquipment(scanner);
                    break;
                case 5:
                    System.out.println("CLI stopping");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private void manageEquipment(Scanner scanner) {
        System.out.println("Equipment Management");
        System.out.println("1. Add new equipment");
        System.out.println("2. Reserve Equipment");
        System.out.println("3. Check equipment availability");
        System.out.println("4. List all equipment ");
        System.out.println("5. Mark equipment under maintenance");
        System.out.println("6. Mark equipment not under maintenance");
        System.out.println("7. Check equipment reservations");
        System.out.println("8. Back");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addEquipment(scanner);
                break;
            case 2:
                reserveEquipment(scanner);
                break;
            case 3:
                checkEquipmentAvailability(scanner);
                break;
            case 4:
                listEquipment(scanner);
                break;
            case 5:
                markEquipmentMaintenance(scanner);
                break;
            case 6:
                markEquipmentNotInMaintenance(scanner);
                break;
                case 7:
                checkEquipmentReservations(scanner);
                break;
            case 8:
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    private void checkEquipmentReservations(Scanner scanner) {
        System.out.println("Enter equipment id");
        int equipmentId = scanner.nextInt();
        scanner.nextLine();
        Equipment tempEquipment = equipmentRepository.getEquipmentById(equipmentId);
        List<Reservation>reservationList=equipmentRepository.getEquipmentReservationById(equipmentId);
        for (Reservation reservation:reservationList){
            System.out.println(reservation);
        }
    }

    private void markEquipmentMaintenance(Scanner scanner) {
        System.out.println("Enter equipment id");
        int equipmentId = scanner.nextInt();
        scanner.nextLine();
        Equipment tempEquipment = equipmentRepository.getEquipmentById(equipmentId);
        tempEquipment.setMaintenanceDate(LocalDate.now());
        equipmentRepository.updateEquipment(tempEquipment);
        System.out.println("Equipment is under Maintenance");
    }

    private void markEquipmentNotInMaintenance(Scanner scanner) {
        System.out.println("Enter equipment id");
        int equipmentId = scanner.nextInt();
        scanner.nextLine();
        Equipment tempEquipment = equipmentRepository.getEquipmentById(equipmentId);
        tempEquipment.setMaintenanceDate(null);
        equipmentRepository.updateEquipment(tempEquipment);
        System.out.println("Equipment is not in Maintenance");
    }

    private void listEquipment(Scanner scanner) {
        List<Equipment> equipmentList = equipmentRepository.getAllEquipment();
        System.out.println("All equipment ");
        for (Equipment equipment : equipmentList) {
            System.out.println(equipment);
        }
    }

    private void checkEquipmentAvailability(Scanner scanner) {
        System.out.println("Enter equipment id");
        int equipmentId = scanner.nextInt();
        scanner.nextLine();
        Equipment tempEquipment = equipmentRepository.getEquipmentById(equipmentId);
        if (tempEquipment.getStatus() == StatusEnum.Available && tempEquipment.getMaintenanceDate() == null) {
            System.out.println("Equipment available!");
        } else if (tempEquipment.getMaintenanceDate() != null) {
            System.out.println("Equipment under maintanance");
        } else {
            System.out.println("Equipment unavailable");
        }
    }


    private void reserveEquipment(Scanner scanner) {
        System.out.println("Member id:");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        Member tempMember = memberRepository.getMemberById(memberId);
        if (tempMember == null) {
            throw new MemberNotFoundException();
        } else {

            List<Equipment> equipmentList = equipmentRepository.getAllEquipment();
            System.out.println("All equipment ");
            for (Equipment equipment : equipmentList) {
                System.out.println(equipment);
            }
            System.out.println("Select the equipment by id ");
            int equipmentId = scanner.nextInt();
            scanner.nextLine();
            Equipment tempEquipment = equipmentRepository.getEquipmentById(equipmentId);
            if (tempEquipment == null || tempEquipment.getMaintenanceDate() != null) {
                System.out.println("Equipment not found or under maintenance");
            } else {

                if (tempEquipment.getStatus() == StatusEnum.Unavailabe) {
                    System.out.println("The equipment is unavailable");
                } else {
                    tempEquipment.setStatus(StatusEnum.Unavailabe);
                    System.out.println("Duration:");
                    int duration = scanner.nextInt();
                    scanner.nextLine();

                    Reservation reservation = new Reservation();
                    reservation.setMember(tempMember);
                    reservation.setDuration(duration);
                    reservation.setEquipment(tempEquipment);
                    reservation.setEquipmentId(equipmentId);
                    reservation.setMemberId(memberId);
                    reservation.setReservationDate(LocalDate.now());

                    reservationRepository.createReservation(reservation);
                    equipmentRepository.updateEquipment(tempEquipment);
                    System.out.println("Equipment reserved");
                }
            }
        }
    }

    private void addEquipment(Scanner scanner) {
        System.out.println("Equipment name: ");
        String equipmentName = scanner.nextLine();


        Equipment tempEquipment = new Equipment();
        tempEquipment.setName(equipmentName);
        tempEquipment.setStatus(StatusEnum.Available);

        equipmentRepository.createEquipment(tempEquipment);
        System.out.println("Equipment added successfully!");
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
        Trainer trainer = trainerRepository.getTrainerSessionById(trainerId);

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
        System.out.println("7. Renew Subscription");
        System.out.println("8. Check Subscription");
        System.out.println("9. Add Progress");
        System.out.println("10. Check Progress");
        System.out.println("11. Back");
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
                renewSubscription(scanner);
                break;
            case 8:
                checkSubscription(scanner);
                break;
            case 9:
                addProgress(scanner);
                break;
            case 10:
                checkProgress(scanner);
                break;
            case 11:
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    private void checkProgress(Scanner scanner) {
        System.out.println("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        if (memberRepository.getMemberById(memberId) != null) {
            List<Progress> progressList = memberRepository.getMemberProgressById(memberId);
            for (Progress p : progressList) {
                System.out.println("Data: " + p.getDate());
                System.out.println("Weight: " + p.getWeight());
                System.out.println("Body fat: " + p.getBodyFatPercentage());
            }
        } else {
            throw new MemberNotFoundException();
        }
    }

    private void addProgress(Scanner scanner) {
        System.out.println("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        if (memberRepository.getMemberById(memberId) != null) {


            System.out.println("Enter Date");
            String date = scanner.nextLine();

            System.out.println("Enter weight");
            int weight = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter body fat ");
            int bf = scanner.nextInt();
            scanner.nextLine();

            Progress tempProgress = new Progress();
            tempProgress.setDate(date);
            tempProgress.setWeight(weight);
            tempProgress.setBodyFatPercentage(bf);


            Member tempMember = memberRepository.addProgressToMemberById(memberId, tempProgress);
            if (tempMember == null) {
                throw new MemberNotFoundException();
            }
            tempProgress.setMember(tempMember);
            progressRepository.createProgress(tempProgress);
            memberRepository.updateMember(tempMember);
            System.out.println("Subscription added successfully!");
        } else {
            throw new MemberNotFoundException();
        }
    }

    private void checkSubscription(Scanner scanner) {
        System.out.println("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        Member tempMember = memberRepository.getMemberById(memberId);

        if (tempMember == null) {
            throw new MemberNotFoundException();
        }
        Member tempMember2 = memberRepository.getMemberSubscriptionById(memberId);
        Subscription subscription = tempMember2.getSubscription();

        if (tempMember2 == null) {
            System.out.println("No subscription found for this member.");
            return;
        }
        System.out.println("Subscription Details:");
        System.out.println("Membership Type: " + subscription.getMembershipType());
        System.out.println("Start Date: " + subscription.getStartDate());
        System.out.println("End Date: " + subscription.getEndDate());
    }

    private void renewSubscription(Scanner scanner) {
        System.out.println("Enter Member ID: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        Member member = memberRepository.getMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        System.out.println("Enter Membership Type");
        String membershipType = scanner.nextLine();

        System.out.println("Enter Start Date");
        String startDate = scanner.nextLine();

        System.out.println("Enter End Date");
        String endDate = scanner.nextLine();

        Subscription subscription = new Subscription();
        subscription.setMembershipType(membershipType);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);
        subscription.setMember(member);

        subscriptionRepository.createSubscription(subscription);
        System.out.println("Subscription added successfully!");

    }

    private void viewMemberSchedule(Scanner scanner) {
        System.out.println("Enter Member ID:");
        int memberId = scanner.nextInt();
        scanner.nextLine();
        Member member = memberRepository.getMemberSessionById(memberId);

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

        if (member != null) {
            TrainingSession session = trainingSessionRepository.addNewMemberToTrainingSessionById(sessionId, member);
            if (session != null) {
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

