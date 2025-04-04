package ShantitKranti;
import java.util.*;

public class EmergencyAlertSystem {
    private Queue<SOSAlert> alertQueue;
    private PriorityQueue<SOSAlert> priorityQueue;
    private Map<String, SOSAlert> registeredUsers;

    public EmergencyAlertSystem() {
        alertQueue = new LinkedList<>();
        priorityQueue = new PriorityQueue<>((a, b) -> b.getPriority() - a.getPriority());
        registeredUsers = new HashMap<>();
    }

    // Register new user with trusted contacts
    public void registerUser(String name, List<String> contacts) {
        if (registeredUsers.containsKey(name)) {
            System.out.println("⚠️ User already registered.");
            return;
        }

        SOSAlert alert = new SOSAlert(name, 1, "N/A");
        alert.setTrustedContacts(contacts);
        registeredUsers.put(name, alert);
        System.out.println("✅ User registered successfully!");
    }

    // Check if a user is registered
    public boolean isUserRegistered(String name) {
        return registeredUsers.containsKey(name);
    }

    // Add a new alert
    public void addAlert(SOSAlert alert) {
        alertQueue.offer(alert);
        priorityQueue.offer(alert);
    }

    // Trigger alert for a registered user
    public void triggerAlert(String name, int priority, String message, String location) {
        if (!registeredUsers.containsKey(name)) {
            System.out.println("❌ User not found!");
            return;
        }

        SOSAlert alert = new SOSAlert(name, priority, message);
        alert.setLocation(location);
        alert.setTrustedContacts(registeredUsers.get(name).getTrustedContacts());

        addAlert(alert);
        System.out.println("🚨 Alert triggered by " + name + " at " + location + " with priority " + priority);
    }

    // Process first alert in queue (FIFO)
    public void processFIFOAlert() {
        if (alertQueue.isEmpty()) {
            System.out.println("📭 No alerts in FIFO queue.");
            return;
        }

        SOSAlert alert = alertQueue.poll();
        System.out.println("📨 Processing FIFO Alert:\n" + alert);
    }

    // Process highest priority alert
    public void processPriorityAlert() {
        if (priorityQueue.isEmpty()) {
            System.out.println("📭 No alerts in priority queue.");
            return;
        }

        SOSAlert alert = priorityQueue.poll();
        System.out.println("🔥 Processing Highest Priority Alert:\n" + alert);
    }

    // Show trusted contacts of a user
    public void showTrustedContacts(String name) {
        if (!registeredUsers.containsKey(name)) {
            System.out.println("❌ User not found.");
            return;
        }

        List<String> contacts = registeredUsers.get(name).getTrustedContacts();
        System.out.println("📞 Trusted Contacts: " + contacts);
    }

    // View all registered users (admin purpose)
    public void showAllUsers() {
        if (registeredUsers.isEmpty()) {
            System.out.println("ℹ️ No users registered yet.");
            return;
        }

        System.out.println("👥 Registered Users:");
        for (String name : registeredUsers.keySet()) {
            System.out.println("- " + name);
        }
    }

    public SOSAlert getUserAlert(String name) {
        return registeredUsers.get(name);
    }
}
