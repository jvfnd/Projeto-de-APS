import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorMixerDialog extends JDialog {
    private Color color1;
    private Color color2;

    public ColorMixerDialog(JFrame parent) {
        super(parent, "Misturador de Cores", true);
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel colorPanel = new JPanel(new GridBagLayout());
        colorPanel.setBackground(Color.WHITE); // Fundo branco
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton chooseColor1Button = createStyledButton("Escolher Cor 1");
        gbc.gridx = 0;
        gbc.gridy = 0;
        colorPanel.add(chooseColor1Button, gbc);

        JButton chooseColor2Button = createStyledButton("Escolher Cor 2");
        gbc.gridx = 1;
        gbc.gridy = 0;
        colorPanel.add(chooseColor2Button, gbc);

        JButton mixColorsButton = createStyledButton("Misturar Cores");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        colorPanel.add(mixColorsButton, gbc);

        JButton backButton = createStyledButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        colorPanel.add(backButton, gbc);

        add(colorPanel, BorderLayout.CENTER);

        JPanel resultPanel = new JPanel();
        resultPanel.setBorder(BorderFactory.createTitledBorder("Resultado"));
        resultPanel.setPreferredSize(new Dimension(450, 100));
        resultPanel.setBackground(Color.WHITE); // Fundo branco

        JLabel resultLabel = new JLabel("Resultado: ");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        resultPanel.add(resultLabel);
        add(resultPanel, BorderLayout.SOUTH);

        chooseColor1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color1 = JColorChooser.showDialog(null, "Escolha a primeira cor", Color.WHITE);
            }
        });

        chooseColor2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color2 = JColorChooser.showDialog(null, "Escolha a segunda cor", Color.WHITE);
            }
        });

        mixColorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (color1 != null && color2 != null) {
                    Color mixedColor = mixColors(color1, color2);
                    resultLabel.setText("Resultado: ");
                    resultPanel.setBackground(mixedColor);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, escolha ambas as cores.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private Color mixColors(Color color1, Color color2) {
        int red = (color1.getRed() + color2.getRed()) / 2;
        int green = (color1.getGreen() + color2.getGreen()) / 2;
        int blue = (color1.getBlue() + color2.getBlue()) / 2;
        return new Color(red, green, blue);
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
