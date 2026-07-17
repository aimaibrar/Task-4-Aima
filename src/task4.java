import java.util.*;

public class task4 {

    static Scanner sc = new Scanner(System.in);
    static int riskScore = 0;

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   SYSTEM VULNERABILITY CHECKLIST");
        System.out.println("=====================================\n");

        checkPasswordStrength();
        checkSoftwareUpdateStatus();
        checkUserPractices();

        printFinalReport();
    }


    static void checkPasswordStrength() {
        System.out.println("---- 1. Password Strength Check ----");
        System.out.print("Enter a password to test: ");
        String password = sc.nextLine();

        List<String> issues = new ArrayList<>();

        if (password.length() < 8) {
            issues.add("Too short (Password should be more than 8 Char)");
        }
        if (!password.matches(".*[A-Z].*")) {
            issues.add("Password should contain at-least one Uppercase");
        }
        if (!password.matches(".*[a-z].*")) {
            issues.add("Password should contain at-least one Lowercase");
        }
        if (!password.matches(".*[0-9].*")) {
            issues.add("Password Should contain at-least one Digit");
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            issues.add("Password Should contain At-least one Special Character");
        }
        if (isCommonPassword(password)) {
            issues.add("Matches a commonly used password");
        }

        if (issues.isEmpty()) {
            System.out.println("Result: STRONG password. No issues found.\n");
            System.out.println("Good.");
        } else {
            System.out.println("Result: WEAK password. Issues found:");
            System.out.println("Not Good.");
            for (String issue : issues) {
                System.out.println("  - " + issue);
            }
            riskScore += issues.size();
            System.out.println();
        }
    }

    static boolean isCommonPassword(String password) {
        String[] common = {"123456", "password", "123456789", "qwerty",
                "abc123", "hello123", "111111", "admin","87654321"};
        for (String p : common) {
            if (password.equalsIgnoreCase(p)) return true;
        }
        return false;
    }


    static void checkSoftwareUpdateStatus() {
        System.out.println("---- 2. Software Update Status ----");
        System.out.print("Is your OS set to auto-update? (yes/no): ");
        String osUpdate = sc.nextLine().trim().toLowerCase();

        System.out.print("How many days since your last OS update? ");
        int daysSinceUpdate = readInt();

        System.out.print("Are your installed apps updated regularly? (Yes\uD83D\uDC4D/No\n" + "\uD83D\uDC4E): ");
        String appUpdate = sc.nextLine().trim().toLowerCase();

        int localRisk = 0;

        if (osUpdate.equals("no")) {
            System.out.println("  - Risk: OS auto-update is disabled.");
            localRisk++;
        }
        if (daysSinceUpdate > 30) {
            System.out.println("  - Risk: OS hasn't been updated in over 30 days.");
            localRisk++;
        }
        if (appUpdate.equals("no")) {
            System.out.println("  - Risk: Applications are not kept up to date.");
            localRisk++;
        }

        if (localRisk == 0) {
            System.out.println("Result: Software is well maintained.\n");
        } else {
            System.out.println("Result: Update practices need improvement.\n");
        }
        riskScore += localRisk;
    }


    static void checkUserPractices() {
        System.out.println("---- 3. Unsafe User Practices ----");

        String[] questions = {
                "Do you reuse the same password across multiple accounts? (Yes or No)",
                "Do you click links from unknown emails? (Yes or No): ",
                "Do you use public Wi-Fi without a VPN for sensitive tasks? (Yes or No): ",
                "Do you share your passwords with others? (Yes or No): ",
                "Do you leave your device unlocked when away? (Yes or No): "
        };

        int localRisk = 0;
        for (String q : questions) {
            System.out.print(q);
            String answer = sc.nextLine().trim().toLowerCase();
            if (answer.equals("yes")) {
                localRisk++;
            }
        }

        System.out.println("Unsafe practices detected: " + localRisk + " out of " + questions.length);
        riskScore += localRisk;
        System.out.println();
    }


    static void printFinalReport() {
        System.out.println("=====================================");
        System.out.println("=====================================");
        System.out.println("----------------------------------");
        System.out.println("           FINAL RISK REPORT");
        System.out.println("----------------------------------");
        System.out.println("======================================");
        System.out.println("=====================================");
        System.out.println("Total Risk Score: " + riskScore);

        String verdict;
        if (riskScore <= 2) {
            verdict = "LOW RISK — good security hygiene overall.";
        } else if (riskScore <= 5) {
            verdict = "MODERATE RISK — some improvements recommended.";
        } else {
            verdict = "HIGH RISK — significant vulnerabilities present.";
        }
        System.out.println("Verdict: " + verdict);
        System.out.println("=====================================");
    }

    static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
