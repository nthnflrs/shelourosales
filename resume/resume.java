package mysystem;

import javax.swing.*;
import javax.swing.text.Document;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class ResumeBuilder extends JFrame {
    private JPanel homePanel;
    private JPanel formPanel;
    private JPanel samplePanel;
    private JScrollPane formScrollPane;
    private JScrollPane sampleScrollPane;
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
        setSize(800, 712);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new CardLayout());

        // Initialize panels
        homePanel = createHomePanel();
        formPanel = createFormPanel();
        samplePanel = new JPanel(); // Placeholder for sample panel

       
        formScrollPane = new JScrollPane(formPanel);
        formScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        formScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        
        sampleScrollPane = new JScrollPane(samplePanel);
        sampleScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sampleScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add panels to the frame
        getContentPane().add(homePanel, "Home");
        getContentPane().add(formScrollPane, "Form"); // Use JScrollPane here
        getContentPane().add(sampleScrollPane, "Sample");

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

        return panel;
    }

    private void addCategory(JPanel panel, String text, int x, int y, int width, int height, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Poppins", Font.BOLD, fontSize));
        label.setBounds(x, y, width, height);
        panel.add(label);
    }

    private JTextField addLabelAndTextField(JPanel panel, String placeholder, int x, int y, int width, int height, String placeholderText) {
        JLabel label = new JLabel(placeholder);
        label.setFont(new Font("Poppins", Font.PLAIN, 10));
        label.setBounds(x, y - 20, width, height);
        panel.add(label);

        JTextField textField = new JTextField(placeholderText);
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Poppins", Font.PLAIN, 10));
        textField.setForeground(Color.GRAY);
        panel.add(textField);

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
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholderText);
                }
            }
        });

        return textField;
    }

    private void addBirthdayDropdowns(JPanel panel, int x, int y) {
        JLabel label = new JLabel("BIRTHDAY");
        label.setFont(new Font("Poppins", Font.PLAIN, 10));
        label.setBounds(x, y - 20, 120, 25);
        panel.add(label);

        dayComboBox = new JComboBox<>(generateDays());
        dayComboBox.setBounds(x, y, 60, 25);
        panel.add(dayComboBox);

        monthComboBox = new JComboBox<>(generateMonths());
        monthComboBox.setBounds(x + 70, y, 60, 25);
        panel.add(monthComboBox);

        yearComboBox = new JComboBox<>(generateYears());
        yearComboBox.setBounds(x + 140, y, 70, 25);
        panel.add(yearComboBox);
    }

    private void addYearDropdowns(JPanel panel, int x, int y) {
        JLabel fromLabel = new JLabel("FROM");
        fromLabel.setFont(new Font("Poppins", Font.PLAIN, 10));
        fromLabel.setBounds(x, y - 20, 120, 25);
        panel.add(fromLabel);

        JComboBox<String> fromYearComboBox = new JComboBox<>(generateYears());
        fromYearComboBox.setBounds(x, y, 60, 25);
        panel.add(fromYearComboBox);

        JLabel toLabel = new JLabel("TO");
        toLabel.setFont(new Font("Poppins", Font.PLAIN, 10));
        toLabel.setBounds(x + 70, y - 20, 120, 25);
        panel.add(toLabel);

        JComboBox<String> toYearComboBox = new JComboBox<>(generateYears());
        toYearComboBox.setBounds(x + 70, y, 60, 25);
        panel.add(toYearComboBox);
    }

    private JTextArea addTextArea(JPanel panel, String placeholder, int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea(placeholder);
        textArea.setBounds(x, y, width, height);
        textArea.setFont(new Font("Poppins", Font.PLAIN, 10));
        textArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(textArea);

        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals(placeholder)) {
                    textArea.setText("");
                    textArea.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textArea.getText().isEmpty()) {
                    textArea.setForeground(Color.GRAY);
                    textArea.setText(placeholder);
                }
            }
        });

        return textArea;
    }

    private void addAttachButton(JPanel panel, String text, int x, int y) {
        JButton attachButton = new JButton(text);
        attachButton.setBackground(new Color(192, 192, 192));
        attachButton.setBounds(466, 461, 120, 25);
        panel.add(attachButton);

        attachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(ResumeBuilder.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    attachedImage = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(200, 150, Image.SCALE_DEFAULT));
                    photoLabel.setIcon(attachedImage);
                }
            }
        });
    }

    private String[] generateDays() {
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.valueOf(i);
        }
        return days;
    }

    private String[] generateMonths() {
        String[] months = new String[12];
        for (int i = 1; i <= 12; i++) {
            months[i - 1] = String.valueOf(i);
        }
        return months;
    }

    private String[] generateYears() {
        String[] years = new String[101];
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = 0; i < 101; i++) {
            years[i] = String.valueOf(currentYear - i);
        }
        return years;
    }

    private void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), panelName);
    }

    private void showSamplePage() {
        samplePanel.removeAll();
        samplePanel.setLayout(null);

        // Create a panel to act as a "long bond paper"
        JPanel paperPanel = new JPanel();
        paperPanel.setLayout(null);
        paperPanel.setBounds(50, 50, 500, 700);
        paperPanel.setBackground(Color.WHITE);
        paperPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add photo
        if (attachedImage != null) {
            JLabel samplePhotoLabel = new JLabel(attachedImage);
            samplePhotoLabel.setBounds(10, 10, 200, 150);
            paperPanel.add(samplePhotoLabel);
        }

        // Add personal information
        JLabel nameLabel = new JLabel(fullNameField.getText());
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        nameLabel.setBounds(220, 10, 300, 25);
        paperPanel.add(nameLabel);

        JLabel contactLabel = new JLabel(contactField.getText());
        contactLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        contactLabel.setBounds(220, 40, 300, 25);
        paperPanel.add(contactLabel);

        JLabel emailLabel = new JLabel(emailField.getText());
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        emailLabel.setBounds(220, 70, 300, 25);
        paperPanel.add(emailLabel);

        JLabel addressLabel = new JLabel(addressField.getText());
        addressLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        addressLabel.setBounds(220, 100, 300, 25);
        paperPanel.add(addressLabel);

        String birthday = dayComboBox.getSelectedItem() + "/" + monthComboBox.getSelectedItem() + "/" + yearComboBox.getSelectedItem();
        JLabel birthdayLabel = new JLabel(birthday);
        birthdayLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        birthdayLabel.setBounds(220, 130, 300, 25);
        paperPanel.add(birthdayLabel);

        // Add horizontal line
        JSeparator separator1 = new JSeparator();
        separator1.setBounds(10, 170, 480, 1);
        paperPanel.add(separator1);

        // Add About Me section
        JLabel aboutMeLabel = new JLabel("ABOUT ME");
        aboutMeLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        aboutMeLabel.setBounds(10, 180, 100, 25);
        paperPanel.add(aboutMeLabel);

        JTextArea aboutMeText = new JTextArea(aboutMeArea.getText());
        aboutMeText.setFont(new Font("Poppins", Font.PLAIN, 12));
        aboutMeText.setBounds(10, 210, 480, 50);
        aboutMeText.setLineWrap(true);
        aboutMeText.setWrapStyleWord(true);
        paperPanel.add(aboutMeText);

        // Add horizontal line
        JSeparator separator2 = new JSeparator();
        separator2.setBounds(10, 270, 480, 1);
        paperPanel.add(separator2);

        // Add Educational Background section
        JLabel educationLabel = new JLabel("EDUCATIONAL BACKGROUND");
        educationLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        educationLabel.setBounds(10, 280, 200, 25);
        paperPanel.add(educationLabel);

        JLabel programLabel = new JLabel("Program: " + programField.getText());
        programLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        programLabel.setBounds(10, 310, 480, 25);
        paperPanel.add(programLabel);

        JLabel schoolYearLabel = new JLabel("School Year: " + schoolYearField.getText());
        schoolYearLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        schoolYearLabel.setBounds(10, 340, 480, 25);
        paperPanel.add(schoolYearLabel);

        JLabel schoolCityLabel = new JLabel("School City: " + schoolCityField.getText());
        schoolCityLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        schoolCityLabel.setBounds(10, 370, 480, 25);
        paperPanel.add(schoolCityLabel);

        // Add horizontal line
        JSeparator separator3 = new JSeparator();
        separator3.setBounds(10, 410, 480, 1);
        paperPanel.add(separator3);

        // Add Work Experience section
        JLabel workExperienceLabel = new JLabel("WORK EXPERIENCE");
        workExperienceLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        workExperienceLabel.setBounds(10, 420, 200, 25);
        paperPanel.add(workExperienceLabel);

        JLabel companyLabel = new JLabel("Company: " + companyField.getText());
        companyLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        companyLabel.setBounds(10, 450, 480, 25);
        paperPanel.add(companyLabel);

        JLabel positionLabel = new JLabel("Position: " + positionField.getText());
        positionLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        positionLabel.setBounds(10, 480, 480, 25);
        paperPanel.add(positionLabel);

        JTextArea workDescriptionText = new JTextArea(workDescriptionArea.getText());
        workDescriptionText.setFont(new Font("Poppins", Font.PLAIN, 12));
        workDescriptionText.setBounds(10, 510, 480, 50);
        workDescriptionText.setLineWrap(true);
        workDescriptionText.setWrapStyleWord(true);
        paperPanel.add(workDescriptionText);

        // Add horizontal line
        JSeparator separator4 = new JSeparator();
        separator4.setBounds(10, 570, 480, 1);
        paperPanel.add(separator4);

        // Add Skills section
        JLabel skillsLabel = new JLabel("SKILLS");
        skillsLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        skillsLabel.setBounds(10, 580, 100, 25);
        paperPanel.add(skillsLabel);

        JLabel skill1Label = new JLabel("Skill 1: " + skill1Field.getText());
        skill1Label.setFont(new Font("Poppins", Font.PLAIN, 12));
        skill1Label.setBounds(10, 610, 480, 25);
        paperPanel.add(skill1Label);

        JLabel skill2Label = new JLabel("Skill 2: " + skill2Field.getText());
        skill2Label.setFont(new Font("Poppins", Font.PLAIN, 12));
        skill2Label.setBounds(10, 640, 480, 25);
        paperPanel.add(skill2Label);

        // Add Print button
        JButton printButton = new JButton("Print");
        printButton.setBounds(220, 670, 80, 25);
        paperPanel.add(printButton);

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printResume(paperPanel);
            }
        });

        // Add Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(320, 670, 80, 25);
        paperPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("Form");
            }
        });

        samplePanel.add(paperPanel);
        samplePanel.revalidate();
        samplePanel.repaint();
        showPanel("Sample");
    }

    private void printResume(JPanel panel) {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("resume.pdf"));
            document.open();
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(panel.getWidth(), panel.getHeight());
            Graphics2D g2d = template.createGraphics(panel.getWidth(), panel.getHeight());
            panel.print(g2d);
            g2d.dispose();
            contentByte.addTemplate(template, 0, 0);
            document.close();

            if (Desktop.isDesktopSupported()) {
                File pdfFile = new File("resume.pdf");
                Desktop.getDesktop().open(pdfFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ResumeBuilder().setVisible(true);
            }
        });
    }
}
