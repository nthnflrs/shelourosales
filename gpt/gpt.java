package mysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class ResumeBuilder extends JFrame {
    private JPanel homePanel;
    private JPanel formPanel;
    private JPanel samplePanel;
    private JLabel photoLabel;
    private JTextField fullNameField;
    private JTextField contactField;
    private JTextField emailField;
    private JTextField addressField;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JTextArea aboutMeArea;
    private JTextField programField;
    private JTextField schoolYearField;
    private JTextField schoolCityField;
    private JTextField companyField;
    private JTextField positionField;
    private JTextArea workDescriptionArea;
    private JTextField skill1Field;
    private JTextField skill2Field;
    private ImageIcon attachedImage;

    public ResumeBuilder() {
        // Set the frame properties
        setTitle("Online Resume Builder");
        setSize(800, 641);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new CardLayout());

        // Initialize panels
        homePanel = createHomePanel();
        formPanel = createFormPanel();
        samplePanel = new JPanel(); // Placeholder for sample panel

        // Add panels to the frame
        getContentPane().add(homePanel, "Home");
        getContentPane().add(formPanel, "Form");
        getContentPane().add(samplePanel, "Sample");

        // Show home panel initially
        showPanel("Home");
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(193, 222, 221);
                Color color2 = new Color(232, 232, 232);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        panel.setLayout(null);

        // Add the main title label
        JLabel titleLabel = new JLabel("ONLINE RESUME BUILDER");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 42));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(100, 86, 600, 80);
        panel.add(titleLabel);

        // Add the create button
        JButton createButton = new JButton("Create");
        createButton.setFont(new Font("Poppins", Font.PLAIN, 18));
        createButton.setBounds(357, 261, 100, 40);
        createButton.setBackground(new Color(173, 216, 230));
        createButton.setForeground(Color.BLACK);
        createButton.setFocusPainted(false);
        createButton.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 1, true));
        panel.add(createButton);

        // Add the footer label
        JLabel footerLabel = new JLabel("Build your Resume with us...");
        footerLabel.setFont(new Font("Poppins", Font.ITALIC, 14));
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setBounds(300, 400, 200, 30);
        panel.add(footerLabel);

        // Add action listener for the create button
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("Form");
            }
        });

        return panel;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204, 224, 214));
        panel.setLayout(null);

        // Add categories and text fields
        addCategory(panel, "PERSONAL INFORMATION", 16, 16, 200, 18, 15);
        fullNameField = addLabelAndTextField(panel, "FULL NAME", 40, 50, 300, 25, "Enter your full name");
        addBirthdayDropdowns(panel, 40, 90);
        emailField = addLabelAndTextField(panel, "GMAIL", 40, 130, 300, 25, "Enter your email");
        contactField = addLabelAndTextField(panel, "CONTACT NO.", 40, 170, 300, 25, "Enter your contact number");
        addressField = addLabelAndTextField(panel, "ADDRESS", 40, 210, 300, 25, "Enter your address");

        addCategory(panel, "EDUCATIONAL BACKGROUND", 16, 250, 300, 15, 15);
        programField = addLabelAndTextField(panel, "PROGRAM", 40, 280, 300, 25, "Enter your program");
        schoolYearField = addLabelAndTextField(panel, "SCHOOL YEAR", 40, 320, 300, 25, "Enter your school year");
        schoolCityField = addLabelAndTextField(panel, "SCHOOL CITY", 40, 360, 300, 25, "Enter your school city");

        addCategory(panel, "WORK EXPERIENCE", 16, 400, 300, 15, 15);
        companyField = addLabelAndTextField(panel, "COMPANY", 40, 430, 300, 25, "Enter your company");
        positionField = addLabelAndTextField(panel, "POSITION", 40, 470, 300, 25, "Enter your position");
        addYearDropdowns(panel, 40, 510);
        workDescriptionArea = addTextArea(panel, "Enter your description", 40, 550, 300, 60);

        addCategory(panel, "SKILLS", 400, 16, 200, 18, 15);
        skill1Field = addLabelAndTextField(panel, "Skill 1", 420, 50, 300, 25, "Enter your skill");
        skill2Field = addLabelAndTextField(panel, "Skill 2", 420, 90, 300, 25, "Enter your skill");

        addCategory(panel, "ABOUT ME", 400, 130, 200, 18, 15);
        aboutMeArea = addTextArea(panel, "Tell us about yourself", 420, 160, 300, 100);

        addCategory(panel, "PHOTO", 400, 270, 200, 18, 15);
        photoLabel = new JLabel();
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        photoLabel.setBounds(420, 300, 200, 150);
        panel.add(photoLabel);
        addAttachButton(panel, "Attach Photo", 440, 460);

        // Add Go button
        JButton goButton = new JButton("Go>>>");
        goButton.setBackground(new Color(50, 205, 50));
        goButton.setFont(new Font("Poppins", Font.PLAIN, 14));
        goButton.setBounds(604, 520, 84, 32);
        panel.add(goButton);

        // Add Back button
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setFont(new Font("Poppins", Font.PLAIN, 14));
        backButton.setBounds(490, 520, 90, 32);
        panel.add(backButton);

        // Add action listener for go button
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSamplePage();
            }
        });

        // Add action listener for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("Home");
            }
        });

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel container = new JPanel(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);

        return container;
    }

    private void addCategory(JPanel panel, String text, int x, int y, int width, int height, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Poppins", Font.BOLD, fontSize));
        label.setBounds(x, y, width, height);
        panel.add(label);
    }

    private JTextField addLabelAndTextField(JPanel panel, String placeholder, int x, int y, int width, int height, String placeholderText) {
        JLabel label = new JLabel(placeholder);
        label.setFont(new Font("Poppins", Font.PLAIN, 12));
        label.setBounds(x, y - 15, width, height);
        panel.add(label);

        JTextField textField = new JTextField(placeholderText);
        textField.setFont(new Font("Poppins", Font.PLAIN, 12));
        textField.setBounds(x, y, width, height);
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholderText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholderText);
                    textField.setForeground(Color.GRAY);
                }
            }
        });

        panel.add(textField);
        return textField;
    }

    private JTextArea addTextArea(JPanel panel, String placeholderText, int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea(placeholderText);
        textArea.setFont(new Font("Poppins", Font.PLAIN, 12));
        textArea.setBounds(x, y, width, height);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(Color.GRAY);

        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals(placeholderText)) {
                    textArea.setText("");
                    textArea.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textArea.getText().isEmpty()) {
                    textArea.setText(placeholderText);
                    textArea.setForeground(Color.GRAY);
                }
            }
        });

        panel.add(textArea);
        return textArea;
    }

    private void addAttachButton(JPanel panel, String buttonText, int x, int y) {
        JButton attachButton = new JButton(buttonText);
        attachButton.setFont(new Font("Poppins", Font.PLAIN, 12));
        attachButton.setBounds(x, y, 120, 25);
        attachButton.setBackground(new Color(173, 216, 230));
        attachButton.setForeground(Color.BLACK);
        attachButton.setFocusPainted(false);
        attachButton.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 1, true));

        attachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    attachedImage = new ImageIcon(selectedFile.getAbsolutePath());
                    Image image = attachedImage.getImage().getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(), Image.SCALE_SMOOTH);
                    photoLabel.setIcon(new ImageIcon(image));
                }
            }
        });

        panel.add(attachButton);
    }

    private void addBirthdayDropdowns(JPanel panel, int x, int y) {
        JLabel label = new JLabel("BIRTHDAY");
        label.setFont(new Font("Poppins", Font.PLAIN, 12));
        label.setBounds(x, y - 15, 100, 25);
        panel.add(label);

        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.valueOf(i);
        }

        String[] months = {
                "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        };

        String[] years = new String[100];
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(2023 - i);
        }

        dayComboBox = new JComboBox<>(days);
        dayComboBox.setFont(new Font("Poppins", Font.PLAIN, 12));
        dayComboBox.setBounds(x + 100, y, 60, 25);

        monthComboBox = new JComboBox<>(months);
        monthComboBox.setFont(new Font("Poppins", Font.PLAIN, 12));
        monthComboBox.setBounds(x + 170, y, 100, 25);

        yearComboBox = new JComboBox<>(years);
        yearComboBox.setFont(new Font("Poppins", Font.PLAIN, 12));
        yearComboBox.setBounds(x + 280, y, 80, 25);

        panel.add(dayComboBox);
        panel.add(monthComboBox);
        panel.add(yearComboBox);
    }

    private void addYearDropdowns(JPanel panel, int x, int y) {
        JLabel label = new JLabel("WORK YEAR");
        label.setFont(new Font("Poppins", Font.PLAIN, 12));
        label.setBounds(x, y - 15, 100, 25);
        panel.add(label);

        String[] years = new String[100];
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(2023 - i);
        }

        JComboBox<String> startYearComboBox = new JComboBox<>(years);
        startYearComboBox.setFont(new Font("Poppins", Font.PLAIN, 12));
        startYearComboBox.setBounds(x + 100, y, 80, 25);

        JComboBox<String> endYearComboBox = new JComboBox<>(years);
        endYearComboBox.setFont(new Font("Poppins", Font.PLAIN, 12));
        endYearComboBox.setBounds(x + 190, y, 80, 25);

        panel.add(startYearComboBox);
        panel.add(endYearComboBox);
    }

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), panelName);
    }

    private void showSamplePage() {
        // Collect input values
        String fullName = fullNameField.getText();
        String contact = contactField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String day = (String) dayComboBox.getSelectedItem();
        String month = (String) monthComboBox.getSelectedItem();
        String year = (String) yearComboBox.getSelectedItem();
        String aboutMe = aboutMeArea.getText();
        String program = programField.getText();
        String schoolYear = schoolYearField.getText();
        String schoolCity = schoolCityField.getText();
        String company = companyField.getText();
        String position = positionField.getText();
        String workDescription = workDescriptionArea.getText();
        String skill1 = skill1Field.getText();
        String skill2 = skill2Field.getText();

        // Validate inputs and handle empty fields
        if (fullName.isEmpty() || contact.isEmpty() || email.isEmpty() || address.isEmpty() ||
                day.isEmpty() || month.isEmpty() || year.isEmpty() || aboutMe.isEmpty() ||
                program.isEmpty() || schoolYear.isEmpty() || schoolCity.isEmpty() ||
                company.isEmpty() || position.isEmpty() || workDescription.isEmpty() ||
                skill1.isEmpty() || skill2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create sample panel content
        samplePanel.setLayout(null);
        samplePanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Full Name: " + fullName);
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        nameLabel.setBounds(20, 20, 300, 25);
        samplePanel.add(nameLabel);

        JLabel contactLabel = new JLabel("Contact: " + contact);
        contactLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        contactLabel.setBounds(20, 50, 300, 25);
        samplePanel.add(contactLabel);

        JLabel emailLabel = new JLabel("Email: " + email);
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        emailLabel.setBounds(20, 80, 300, 25);
        samplePanel.add(emailLabel);

        JLabel addressLabel = new JLabel("Address: " + address);
        addressLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        addressLabel.setBounds(20, 110, 300, 25);
        samplePanel.add(addressLabel);

        JLabel birthdayLabel = new JLabel("Birthday: " + month + " " + day + ", " + year);
        birthdayLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        birthdayLabel.setBounds(20, 140, 300, 25);
        samplePanel.add(birthdayLabel);

        JLabel aboutMeLabel = new JLabel("About Me: " + aboutMe);
        aboutMeLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        aboutMeLabel.setBounds(20, 170, 600, 25);
        samplePanel.add(aboutMeLabel);

        JLabel programLabel = new JLabel("Program: " + program);
        programLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        programLabel.setBounds(20, 200, 300, 25);
        samplePanel.add(programLabel);

        JLabel schoolYearLabel = new JLabel("School Year: " + schoolYear);
        schoolYearLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        schoolYearLabel.setBounds(20, 230, 300, 25);
        samplePanel.add(schoolYearLabel);

        JLabel schoolCityLabel = new JLabel("School City: " + schoolCity);
        schoolCityLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        schoolCityLabel.setBounds(20, 260, 300, 25);
        samplePanel.add(schoolCityLabel);

        JLabel companyLabel = new JLabel("Company: " + company);
        companyLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        companyLabel.setBounds(20, 290, 300, 25);
        samplePanel.add(companyLabel);

        JLabel positionLabel = new JLabel("Position: " + position);
        positionLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        positionLabel.setBounds(20, 320, 300, 25);
        samplePanel.add(positionLabel);

        JLabel workDescriptionLabel = new JLabel("Work Description: " + workDescription);
        workDescriptionLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        workDescriptionLabel.setBounds(20, 350, 600, 25);
        samplePanel.add(workDescriptionLabel);

        JLabel skillsLabel = new JLabel("Skills: " + skill1 + ", " + skill2);
        skillsLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        skillsLabel.setBounds(20, 380, 300, 25);
        samplePanel.add(skillsLabel);

        JLabel photoSampleLabel = new JLabel();
        photoSampleLabel.setBounds(650, 20, 120, 150);
        if (attachedImage != null) {
            Image image = attachedImage.getImage().getScaledInstance(photoSampleLabel.getWidth(), photoSampleLabel.getHeight(), Image.SCALE_SMOOTH);
            photoSampleLabel.setIcon(new ImageIcon(image));
        }
        samplePanel.add(photoSampleLabel);

        // Display the sample panel
        getContentPane().add(samplePanel, "Sample");
        showPanel("Sample");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserProfileGUI().setVisible(true);
            }
        });
    }
}
