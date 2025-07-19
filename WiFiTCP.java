import java.util.*;

class WiFiClient {
    String userName;
    String deviceType;
    double cwnd;
    double ssthresh;
    final double alpha = 1.0;
    final double beta = 0.5;
    boolean inFastRecovery = false;
    int dupAcks = 0;
    double lossProbability;
    Random rand = new Random();
    Router router;
    ArrayList<Double> cwndHistory = new ArrayList<>();

    WiFiClient(String userName, String deviceType, Router router, double initialCwnd, double ssthresh, double lossProbability) {
        this.userName = userName;
        this.deviceType = deviceType;
        this.router = router;
        this.cwnd = initialCwnd;
        this.ssthresh = ssthresh;
        this.lossProbability = lossProbability;
    }

    public void transmit(int round) {
        boolean packetLoss = rand.nextDouble() < lossProbability;
        cwndHistory.add(cwnd);

        System.out.printf("\n[ %s (%s) ] cwnd = %.2f | ssthresh = %.2f\n", userName, deviceType, cwnd, ssthresh);

        if (inFastRecovery) {
            cwnd += 1;
            System.out.printf("  In Fast Recovery -> cwnd increased to %.2f\n", cwnd);
            if (!packetLoss) {
                cwnd = ssthresh;
                inFastRecovery = false;
                dupAcks = 0;
                System.out.printf("  ACK received -> Exit Fast Recovery -> cwnd = ssthresh = %.2f\n", cwnd);
            }
        } else {
            if (packetLoss) {
                dupAcks++;
                System.out.printf("  Packet loss detected -> dupACK #%d\n", dupAcks);
                if (dupAcks == 3) {
                    ssthresh = cwnd * beta;
                    cwnd = ssthresh + 3;
                    inFastRecovery = true;
                    System.out.printf("  Fast Retransmit -> ssthresh = %.2f, cwnd = %.2f -> Enter Fast Recovery\n", ssthresh, cwnd);
                }
            } else {
                dupAcks = 0;
                if (cwnd < ssthresh) { // SLOW START - EXPONENTIAL
                    cwnd *= 2;
                    System.out.printf("  Slow Start -> cwnd doubled to %.2f\n", cwnd);
                } else {
                    cwnd += alpha;//CONGESTIONTION AVOIDANCE - LINEAR
                    System.out.printf("  Congestion A.voidance -> cwnd += %.1f -> %.2f\n", alpha, cwnd);
                }
            }
        }

        router.receive(userName, round, cwnd);
    }

    public void printGraph() {
        System.out.println(userName + " (" + deviceType + ")");
        for (int i = 0; i < cwndHistory.size(); i++) {
            int bar = (int) Math.round(cwndHistory.get(i));
            System.out.printf("R%02d | ", i + 1);
            for (int j = 0; j < bar; j++) {
                System.out.print("\u2588");
            }
            System.out.printf("  (%.2f)\n", cwndHistory.get(i));
        }
        System.out.println();
    }
}

class Router {
    double totalData = 0;

    public void receive(String clientName, int round, double cwnd) {
        totalData += cwnd;
    }

    public void printStats() {
        System.out.println("\n=== Simulation Complete ===");
        System.out.println("=== Input Instructions ===");
System.out.println("--cwnd: start with 1 or 2");
System.out.println("-- ssthresh: usually 8 - 12");
System.out.println("-- loss probability: 0.0 = no loss, 0.3 = 30% chance of loss, 1.0 = full loss");

        System.out.printf("Total Data Received by Router: %.2f packets\n", totalData);
    }
}

public class WiFiTCP {

    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Router router = new Router();

    int rounds = 30;
    int numClients = 4;

    WiFiClient[] clients = new WiFiClient[numClients];
    System.out.println("=== Wi-Fi TCP Congestion Control Setup ===");

    System.out.println("\nNote:");
    System.out.println("• Each client simulates TCP congestion control over Wi-Fi.");
    System.out.println("• cwnd (Congestion Window): how many packets can be sent.");
    System.out.println("• ssthresh (Slow Start Threshold): decides slow start vs. congestion avoidance.");
    System.out.println("• loss probability: chance of packet loss (0 = none, 1 = always).\n");

    for (int i = 0; i < numClients; i++) {
        System.out.println("\n--- Client " + (i + 1) + " ---");
        System.out.print("Enter user name: ");
        String userName = sc.nextLine();

        System.out.print("Enter device type (e.g. mobile, laptop, tablet): ");
        String deviceType = sc.nextLine();

        System.out.print("Initial cwnd (e.g. 1): ");
        double initialCwnd = sc.nextDouble();

        System.out.print("ssthresh (e.g. 8): ");
        double ssthresh = sc.nextDouble();

        System.out.print("Packet loss probability (0.0 to 1.0): ");
        double lossProb = sc.nextDouble();
        sc.nextLine(); // consume newline

        clients[i] = new WiFiClient(userName, deviceType, router, initialCwnd, ssthresh, lossProb);
    }

    // Print summary table
    System.out.println("\n=== Simulation Summary ===");
    System.out.printf("%-10s | %-8s | cwnd | ssthresh | lossProb\n", "User", "Device");
    for (WiFiClient c : clients) {
        System.out.printf("%-10s | %-8s | %.1f  | %.1f      | %.2f\n",
            c.userName, c.deviceType, c.cwnd, c.ssthresh, c.lossProbability);
    }

    // Run simulation
    for (int round = 1; round <= rounds; round++) {
        System.out.println("\n=== Round " + round + " ===");
        for (WiFiClient client : clients) {
            client.transmit(round);
        }

        // Show cumulative router data after each round
        System.out.printf("→ Router has received %.2f packets so far.\n", router.totalData);
    }

    router.printStats();

    // ASCII bar chart
    System.out.println("\n=== ASCII Congestion Window Growth Chart ===\n");
    for (WiFiClient client : clients) {
        client.printGraph();
    }

    sc.close();
    }  
}
