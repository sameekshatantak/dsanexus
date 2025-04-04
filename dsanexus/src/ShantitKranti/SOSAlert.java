package ShantitKranti;
import java.util.List;

public class SOSAlert {
    private String userName;
    private int priority;
    private String message;
    private String location;
    private List<String> trustedContacts;

    public SOSAlert(String userName, int priority, String message) {
        this.userName = userName;
        this.priority = priority;
        this.message = message;
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public int getPriority() {
        return priority;
    }

    public String getMessage() {
        return message;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getTrustedContacts() {
        return trustedContacts;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTrustedContacts(List<String> contacts) {
        this.trustedContacts = contacts;
    }

    @Override
    public String toString() {
        return "🚨 SOS Alert 🚨\n" +
                "👤 User: " + userName + "\n" +
                "⚠️ Priority: " + priority + "\n" +
                "📍 Location: " + (location != null ? location : "Unknown") + "\n" +
                "💬 Message: " + message + "\n" +
                "📞 Trusted Contacts: " + (trustedContacts != null ? trustedContacts : "None") + "\n";
    }
}
