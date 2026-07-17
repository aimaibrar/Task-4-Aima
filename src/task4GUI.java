import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class task4GUI extends JFrame {

    private JPasswordField passwordField;
    private JLabel passwordResultLabel;

    private JRadioButton osUpdateYes, osUpdateNo;
    private JTextField daysSinceUpdateField;
    private JRadioButton appUpdateYes, appUpdateNo;

    private JCheckBox reusePasswordBox, clickLinksBox, publicWifiBox, sharePasswordBox, unlockedDeviceBox;

    private JTextArea reportArea;

    private int riskScore = 0;

    public task4GUI() {
        setTitle("System Vulnerability Checklist");
        setSize(650, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("1. Password", buildPasswordPanel());
        tabs.addTab("2. Software Updates", buildUpdatePanel());
        tabs.addTab("3. User Practices", buildPracticesPanel());
        tabs.addTab("Report", buildReportPanel());

        add(tabs);
    }

    private JPanel buildPasswordPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Password Strength Check");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title);
        panel.add(Box.createVerticalStrut(15));

        panel.add(new JLabel("Enter a password to test:"));
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 30));
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(passwordField);
        panel.add(Box.createVerticalStrut(10));

        JButton checkBtn = new JButton("Check Password");
        checkBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkBtn.addActionListener(e -> checkPassword());
        panel.add(checkBtn);
        panel.add(Box.createVerticalStrut(15));

        passwordResultLabel = new JLabel("<html></html>");
        panel.add(passwordResultLabel);

        return panel;
    }

    private void checkPassword() {
        String password = new String(passwordField.getPassword());
        List<String> issues = new ArrayList<>();

        if (password.length() < 8) issues.add("Too short (Password should be more than 8 Char)");
        if (!password.matches(".*[A-Z].*")) issues.add("Password should contain at-least one Uppercase");
        if (!password.matches(".*[a-z].*")) issues.add("Password should contain at-least one Lowercase");
        if (!password.matches(".*[0-9].*")) issues.add("Password Should contain at-least one Digit");
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) issues.add("Password Should contain At-least one Special Character");
        if (isCommonPassword(password)) issues.add("Matches a commonly used password");

        StringBuilder sb = new StringBuilder("<html>");
        if (issues.isEmpty()) {
            sb.append("<b style='color:green'>STRONG password. No issues found.</b><br>");
            sb.append("Good");
        } else {
            sb.append("<b style='color:red'>WEAK password. Not Good. </b><br>");
            for (String issue : issues) sb.append("&bull; ").append(issue).append("<br>");
        }
        sb.append("</html>");
        passwordResultLabel.setText(sb.toString());
    }

    private boolean isCommonPassword(String password) {
        String[] common = {"123456", "password", "123456789", "qwerty",
                "abc123", "hello123", "111111", "admin", "87654321"};
        for (String p : common) {
            if (password.equalsIgnoreCase(p)) return true;
        }
        return false;
    }

    private JPanel buildUpdatePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Software Update Status");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title);
        panel.add(Box.createVerticalStrut(15));

        panel.add(new JLabel("Is your OS set to auto-update?"));
        osUpdateYes = new JRadioButton("Yes");
        osUpdateNo = new JRadioButton("No");
        ButtonGroup osGroup = new ButtonGroup();
        osGroup.add(osUpdateYes);
        osGroup.add(osUpdateNo);
        JPanel osPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        osPanel.add(osUpdateYes);
        osPanel.add(osUpdateNo);
        panel.add(osPanel);

        panel.add(new JLabel("Days since last OS update:"));
        daysSinceUpdateField = new JTextField();
        daysSinceUpdateField.setMaximumSize(new Dimension(100, 30));
        daysSinceUpdateField.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(daysSinceUpdateField);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Are your apps updated regularly? (Yes👍/No👎)"));
        appUpdateYes = new JRadioButton("Yes");
        appUpdateNo = new JRadioButton("No");
        ButtonGroup appGroup = new ButtonGroup();
        appGroup.add(appUpdateYes);
        appGroup.add(appUpdateNo);
        JPanel appPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        appPanel.add(appUpdateYes);
        appPanel.add(appUpdateNo);
        panel.add(appPanel);

        return panel;
    }

    private JPanel buildPracticesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Unsafe User Practices (check all that apply)");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title);
        panel.add(Box.createVerticalStrut(15));

        reusePasswordBox = new JCheckBox("I reuse the same password across multiple accounts");
        clickLinksBox = new JCheckBox("I click links from unknown emails");
        publicWifiBox = new JCheckBox("I use public Wi-Fi without a VPN for sensitive tasks");
        sharePasswordBox = new JCheckBox("I share my passwords with others");
        unlockedDeviceBox = new JCheckBox("I leave my device unlocked when away");

        for (JCheckBox box : new JCheckBox[]{reusePasswordBox, clickLinksBox, publicWifiBox, sharePasswordBox, unlockedDeviceBox}) {
            box.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(box);
            panel.add(Box.createVerticalStrut(5));
        }

        return panel;
    }

    private JPanel buildReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JButton generateBtn = new JButton("Generate Final Report");
        generateBtn.addActionListener(e -> generateReport());

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        panel.add(generateBtn, BorderLayout.NORTH);
        panel.add(new JScrollPane(reportArea), BorderLayout.CENTER);

        return panel;
    }

    private void generateReport() {
        riskScore = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("=====================================\n");
        sb.append("----------------------------------\n");
        sb.append("           FINAL RISK REPORT\n");
        sb.append("----------------------------------\n");
        sb.append("=====================================\n\n");

        String password = new String(passwordField.getPassword());
        int passwordIssues = 0;
        if (password.isEmpty()) {
            sb.append("Password: not tested.\n\n");
        } else {
            if (password.length() < 8) passwordIssues++;
            if (!password.matches(".*[A-Z].*")) passwordIssues++;
            if (!password.matches(".*[a-z].*")) passwordIssues++;
            if (!password.matches(".*[0-9].*")) passwordIssues++;
            if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) passwordIssues++;
            if (isCommonPassword(password)) passwordIssues++;
            riskScore += passwordIssues;
            sb.append("Password issues found: ").append(passwordIssues).append("\n\n");
        }

        int updateRisk = 0;
        if (osUpdateNo.isSelected()) updateRisk++;
        if (appUpdateNo.isSelected()) updateRisk++;
        try {
            int days = Integer.parseInt(daysSinceUpdateField.getText().trim());
            if (days > 30) updateRisk++;
        } catch (NumberFormatException ignored) { }
        riskScore += updateRisk;
        sb.append("Software update risks found: ").append(updateRisk).append("\n\n");

        int practiceRisk = 0;
        for (JCheckBox box : new JCheckBox[]{reusePasswordBox, clickLinksBox, publicWifiBox, sharePasswordBox, unlockedDeviceBox}) {
            if (box.isSelected()) practiceRisk++;
        }
        riskScore += practiceRisk;
        sb.append("Unsafe practices selected: ").append(practiceRisk).append(" out of 5\n\n");

        sb.append("-------------------------------------\n");
        sb.append("Total Risk Score: ").append(riskScore).append("\n");

        String verdict;
        if (riskScore <= 2) verdict = "LOW RISK — good security hygiene overall.";
        else if (riskScore <= 5) verdict = "MODERATE RISK — some improvements recommended.";
        else verdict = "HIGH RISK — significant vulnerabilities present.";

        sb.append("Verdict: ").append(verdict).append("\n");
        sb.append("=====================================\n");

        reportArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            task4GUI gui = new task4GUI();
            gui.setVisible(true);
        });
    }
}