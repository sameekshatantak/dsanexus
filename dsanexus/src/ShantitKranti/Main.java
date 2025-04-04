package ShantitKranti;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EmergencyAlertSystem alertSystem = new EmergencyAlertSystem();
        SafeZoneGraph graph = new SafeZoneGraph();

        // Sample Graph Initialization
        graph.addSafeZone("Area A");
        graph.addSafeZone("Hospital");
        graph.addSafeZone("Police Station");
        graph.addSafeZone("Shelter1");
        graph.addSafeZone("Shelter2");
        graph.connectZones("Area A", "Hospital", 2);
        graph.connectZones("Area A", "Police Station", 4);
        graph.connectZones("Area A", "Shelter2", 4);
        graph.connectZones("Area A", "Shelter1", 6);
        graph.connectZones("Hospital", "Shelter1", 3);
        graph.connectZones("Police Station", "Shelter1", 1);
        graph.connectZones("Hospital", "Shelter2", 5);
        graph.connectZones("Police Station", "Shelter2", 6);

        while (true) {
            System.out.println("\nChoose Login Type:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int role = scanner.nextInt();
            scanner.nextLine();

            if (role == 1) {
                // Admin Section
                System.out.print("Enter admin username: ");
                String adminUser = scanner.nextLine();
                if (!adminUser.equals("sameeksha") ) {
                    System.out.println("❌ Invalid admin username.");
                    continue;
                }

                while (true) {
                    System.out.println("\n👮 Admin Menu:");
                    System.out.println("1. Process FIFO Alert");
                    System.out.println("2. Process Highest Priority Alert");
                    System.out.println("3. Show Safe Zone Connections");
                    System.out.println("4. Back to Main Menu");
                    System.out.print("Enter choice: ");
                    int adminChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (adminChoice) {
                        case 1:
                            alertSystem.processFIFOAlert();
                            break;
                        case 2:
                            alertSystem.processPriorityAlert();
                            break;
                        case 3:
                            graph.displayConnectionsWithNearestInfo();
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("❌ Invalid choice.");
                    }

                    if (adminChoice == 4) break;
                }

            } else if (role == 2) {
                // User Section
                System.out.print("Enter your name to register/login: ");
                String name = scanner.nextLine();
                if (!alertSystem.isUserRegistered(name)) {
                    List<String> contacts = new ArrayList<>();
                    for (int i = 1; i <= 3; i++) {
                        System.out.print("Enter trusted contact " + i + ": ");
                        contacts.add(scanner.nextLine());
                    }
                    alertSystem.registerUser(name, contacts);
                    System.out.println("✅ User registered successfully!");
                } else {
                    System.out.println("🔓 Welcome back, " + name + "!");
                }

                while (true) {
                    System.out.println("\n📱 User Menu:");
                    System.out.println("1. Trigger Emergency Alert");
                    System.out.println("2. Show Trusted Contacts");
                    System.out.println("3. Show Safe Zone Connections");
                    System.out.println("4. Back to Main Menu");
                    System.out.print("Enter choice: ");
                    int userChoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (userChoice) {
                        case 1:
                            System.out.print("Enter priority (1-5): ");
                            int priority = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter alert message: ");
                            String message = scanner.nextLine();
                            System.out.print("Enter current location: ");
                            String location = scanner.nextLine();
                            alertSystem.triggerAlert(name, priority, message, location);
                            break;
                        case 2:
                            alertSystem.showTrustedContacts(name);
                            break;
                        case 3:
                            graph.displayConnectionsWithNearestInfo();
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("❌ Invalid choice.");
                    }

                    if (userChoice == 4) break;
                }

            } else if (role == 3) {
                System.out.println("👋 Exiting. Stay safe!");
                break;
            } else {
                System.out.println("❌ Invalid input.");
            }
        }
    }
}

