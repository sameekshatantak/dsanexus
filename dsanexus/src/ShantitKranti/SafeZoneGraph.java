package ShantitKranti;
import java.util.*;

public class SafeZoneGraph {
    private Map<String, List<Edge>> graph = new HashMap<>();

    static class Edge {
        String dest;
        int weight;

        Edge(String dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public void addSafeZone(String zone) {
        graph.putIfAbsent(zone, new ArrayList<>());
    }

    public void connectZones(String from, String to, int distance) {
        graph.get(from).add(new Edge(to, distance));
        graph.get(to).add(new Edge(from, distance));
    }

    // Dijkstra's Algorithm to find shortest path from source
    private String findNearest(String source, String targetKeyword) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        Set<String> visited = new HashSet<>();

        for (String zone : graph.keySet()) {
            distances.put(zone, Integer.MAX_VALUE);
        }

        distances.put(source, 0);
        pq.add(new Edge(source, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (visited.contains(current.dest)) continue;
            visited.add(current.dest);

            for (Edge neighbor : graph.get(current.dest)) {
                int newDist = distances.get(current.dest) + neighbor.weight;
                if (newDist < distances.get(neighbor.dest)) {
                    distances.put(neighbor.dest, newDist);
                    pq.add(new Edge(neighbor.dest, newDist));
                }
            }
        }

        // Find closest node with keyword like "Hospital", "Police", "Shelter"
        String nearest = null;
        int minDist = Integer.MAX_VALUE;
        for (String node : graph.keySet()) {
            if (node.toLowerCase().contains(targetKeyword.toLowerCase()) && distances.get(node) < minDist) {
                nearest = node;
                minDist = distances.get(node);
            }
        }

        return nearest + " (Distance: " + minDist + ")";
    }

    public void displayConnectionsWithNearestInfo() {
        for (String zone : graph.keySet()) {
            System.out.println("\n🔹 Zone: " + zone);
            for (Edge edge : graph.get(zone)) {
                System.out.println("  ↪ Connected to: " + edge.dest + " (Distance: " + edge.weight + ")");
            }

            System.out.println("  🏥 Nearest Hospital: " + findNearest(zone, "hospital"));
            System.out.println("  🚓 Nearest Police Station: " + findNearest(zone, "police"));
            System.out.println("  🛡️ Nearest Shelter: " + findNearest(zone, "shelter"));
        }
    }
}
