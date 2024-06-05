import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ColorGameDialog extends JDialog {
    private Color color1;
    private Color color2;
    private Color correctColor;

    public ColorGameDialog(JFrame parent) {
        super(parent, "Jogo de Mistura de Cores", true);
        setSize(600, 500);
        setLayout(new BorderLayout());

        // Gerar duas cores aleatórias
        color1 = generateRandomColor();
        color2 = generateRandomColor();
        correctColor = mixColors(color1, color2);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(600, 150));
        topPanel.setLayout(new BorderLayout());

        JPanel colorsPanel = new JPanel();
        colorsPanel.setLayout(new GridLayout(1, 3, 10, 10));
        JPanel color1Panel = new JPanel();
        color1Panel.setBackground(color1);
        JPanel plusPanel = new JPanel();
        plusPanel.setBackground(Color.WHITE);
        JLabel plusLabel = new JLabel("+");
        plusLabel.setFont(new Font("Arial", Font.BOLD, 48));
        plusPanel.add(plusLabel);
        JPanel color2Panel = new JPanel();
        color2Panel.setBackground(color2);

        colorsPanel.add(color1Panel);
        colorsPanel.add(plusPanel);
        colorsPanel.add(color2Panel);

        topPanel.add(colorsPanel, BorderLayout.CENTER);

        JLabel instructionLabel = new JLabel("Qual a cor resultante das duas cores acima somadas?");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(instructionLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setLayout(new GridLayout(3, 3, 10, 10));

        // Gerar 9 cores aleatórias, incluindo a cor correta
        Random random = new Random();
        int correctIndex = random.nextInt(9);
        for (int i = 0; i < 9; i++) {
            Color color;
            if (i == correctIndex) {
                color = correctColor;
            } else {
                color = generateRandomColor();
            }

            JButton colorButton = createColorButton(color);
            bottomPanel.add(colorButton);
        }

        add(bottomPanel, BorderLayout.CENTER);

        JButton backButton = createStyledButton("Voltar");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.WHITE);
        controlPanel.add(backButton);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private Color generateRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    private Color mixColors(Color color1, Color color2) {
        int red = (color1.getRed() + color2.getRed()) / 2;
        int green = (color1.getGreen() + color2.getGreen()) / 2;
        int blue = (color1.getBlue() + color2.getBlue()) / 2;
        return new Color(red, green, blue);
    }

    private JButton createColorButton(Color color) {
        JButton button = new JButton();
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(100, 100));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (color.equals(correctColor)) {
                    JOptionPane.showMessageDialog(null, "Correta! Parabéns!");
                } else {
                    JOptionPane.showMessageDialog(null, "Errado! Tente novamente.");
                }
            }
        });
        return button;
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 130, 180)); // Cor azul
        button.setForeground(Color.black);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBorder(BorderFactory.createLineBorder(new Color(60, 120, 170), 2, true)); // Borda arredondada
        return button;
    }
}
