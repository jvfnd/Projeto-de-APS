import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorMixerApp {
    public static void main(String[] args) {
        // Definir o Look and Feel do Windows
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Misturador de Cores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.white); // Fundo branco
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Misturador de Cores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        JButton mixButton = createStyledButton("Misturar");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        mainPanel.add(mixButton, gbc);

        JButton playButton = createStyledButton("Jogar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(playButton, gbc);

        JButton exitButton = createStyledButton("Sair");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        mainPanel.add(exitButton, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);

        mixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorMixerDialog dialog = new ColorMixerDialog(frame);
                dialog.setVisible(true);
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorGameDialog dialog = new ColorGameDialog(frame);
                dialog.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
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
