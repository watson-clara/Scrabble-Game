
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;
import java.util.Collections;


public class Scrabble extends JFrame {
    private JButton playButton;
    private JButton backButton;
    private JButton intstructionButton;
    private JPanel buttonPanel;
    private JPanel gamePanel;
    private JPanel instructionPanel;
    private JLabel instructionLabel;
    private JLabel instructions;
    private JLabel title;

    int numWordsToSelect = 10;
    String[] selectedWords = new String[numWordsToSelect];
    String[] scrambledWords = new String[numWordsToSelect];

    public Scrabble() {
        super("Scrabble");
        

        buttonPanel = new JPanel(new BorderLayout());
        instructionPanel = new JPanel(new BorderLayout());
        gamePanel = new JPanel(new GridLayout(14, 6));
        
        
        
        buttonPanel();
        
        

    
        add(buttonPanel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setVisible(true);

        
    }
    

    private void buttonPanel(){
        title();
        playButton();
        intstructionButton();
    }

    private void instructionPanel() {
        backButton();
        instructionLabel();
        instructionDescription();
    }

    private void title(){
        String instructionString = "Scrabble!";
        title = new JLabel(instructionString, SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(200, 250));
        title.setVerticalAlignment(SwingConstants.CENTER);
        Font labelFont = new Font("Arial", Font.BOLD, 50);
        title.setFont(labelFont);
        buttonPanel.add(title, BorderLayout.NORTH);
    }

    private void playButton(){
        playButton = new JButton("Play");
        playButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playButton.setBorderPainted(true);
        playButton.setFocusPainted(true);
        playButton.setForeground(Color.BLUE);
        playButton.setBackground(Color.RED);
        Font buttonFont = new Font("Arial", Font.BOLD, 34);
        playButton.setFont(buttonFont);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonPanel.setVisible(false);
                instructionPanel.setVisible(false);
                add(gamePanel);
                gamePanel.setVisible(true);
                importWords();
            }
        });
        buttonPanel.add(playButton, BorderLayout.CENTER);
    }

    private void intstructionButton(){
        intstructionButton = new JButton("Instructions");
        intstructionButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        intstructionButton.setPreferredSize(new Dimension(200, 80));
        intstructionButton.setBorderPainted(true);
        intstructionButton.setFocusPainted(true);
        intstructionButton.setForeground(Color.BLUE);
        intstructionButton.setBackground(Color.RED);
        Font buttonFont = new Font("Arial", Font.BOLD, 34);
        intstructionButton.setFont(buttonFont);
        intstructionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                instructionPanel.removeAll();
                instructionPanel.revalidate();
                instructionPanel.repaint();
                buttonPanel.setVisible(false);
                gamePanel.setVisible(false);
                add(instructionPanel);
                instructionPanel.setVisible(true);
                instructionPanel();
            }
        });
        buttonPanel.add(intstructionButton, BorderLayout.SOUTH);
    }

    private void backButton(){
        backButton = new JButton("Back");
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton.setPreferredSize(new Dimension(200, 60));
        backButton.setBorderPainted(true);
        backButton.setFocusPainted(true);
        backButton.setForeground(Color.BLUE);
        backButton.setBackground(Color.RED);
        Font buttonFont = new Font("Arial", Font.BOLD, 34);
        backButton.setFont(buttonFont);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.setVisible(false);
                instructionPanel.setVisible(false);
                buttonPanel.setVisible(true);
                
            }
        });
        instructionPanel.add(backButton, BorderLayout.SOUTH);
    }

    private void instructionLabel(){
        String instructionString = "Instructions";
        instructionLabel = new JLabel(instructionString, SwingConstants.CENTER);
        instructionLabel.setVerticalAlignment(SwingConstants.NORTH);
        Font labelFont = new Font("Arial", Font.BOLD, 50);
        instructionLabel.setFont(labelFont);
        instructionPanel.add(instructionLabel, BorderLayout.NORTH);
    }

    private void instructionDescription(){
        String instructionString = "ajshdbfaljhbrlaerhbe hrblvjaherblvjhaerblvj";
        instructions = new JLabel(instructionString, SwingConstants.CENTER);
        instructions.setVerticalAlignment(SwingConstants.CENTER);
        instructionPanel.add(instructions, BorderLayout.CENTER);
    }

    private void createLabelsAndTextboxes(String[] selectedWords, String[] scrambledWords) {
        gamePanel.add(new JLabel(""));
        gamePanel.add(new JLabel(""));
        gamePanel.add(new JLabel(""));
        gamePanel.add(new JLabel(""));
        gamePanel.add(new JLabel(""));
        gamePanel.add(new JLabel(""));
        for (int i = 0; i < 10; i++) {
            String word = selectedWords[i];
            JLabel label = new JLabel(scrambledWords[i]);
            JLabel nul = new JLabel("");
            JTextField textField = new JTextField();
            JButton checkButton = new JButton("Check");
            JButton answerButton = new JButton("Show Answer");
            JLabel resultLabel = new JLabel();
            
            checkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = textField.getText();
                    if (input.equals(word)){
                        label.setForeground(Color.BLACK);
                        resultLabel.setForeground(Color.BLUE);
                        resultLabel.setText("Correct!");
                    } else {
                        resultLabel.setForeground(Color.RED);
                        label.setForeground(Color.RED);
                        resultLabel.setText("Wrong!");
                    }
                }
            });
            answerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textField.setText(word);
                }
            });
            gamePanel.add(nul);
            gamePanel.add(label);
            gamePanel.add(textField);
            gamePanel.add(checkButton);
            gamePanel.add(answerButton);
            gamePanel.add(resultLabel);
        }
        gamePanel.add(new JLabel(""));
        gamePanel.add(new JLabel(""));
        gamePanel.add(new JLabel(""));
        gamePanel.add(new JLabel(""));
        gamePanel.add(new JLabel(""));
        gamePanel.add(new JLabel(""));
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.removeAll();
                gamePanel.revalidate();
                gamePanel.repaint();
                gamePanel.setVisible(false);
                instructionPanel.setVisible(false);
                buttonPanel.setVisible(true);
            }
        });
        JButton newButton = new JButton("New Words");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.removeAll();
                gamePanel.revalidate();
                gamePanel.repaint();
                instructionPanel.setVisible(false);
                buttonPanel.setVisible(false);
                importWords();
            }
        });
        gamePanel.add(new JPanel()); 
        gamePanel.add(new JPanel()); 
        gamePanel.add(backButton);
        gamePanel.add(newButton);
        gamePanel.add(new JPanel()); 
        


    }

    private void importWords() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
            ArrayList wordsList = new ArrayList();
            String line;
            while ((line = reader.readLine()) != null) {
                wordsList.add(line);
            }
            reader.close();
            String[] words = (String[]) wordsList.toArray(new String[wordsList.size()]);
            Random rand = new Random();
            for (int i = 0; i < numWordsToSelect; i++) {
                int index = rand.nextInt(words.length);
                String word = words[index];
                selectedWords[i] = word;
                char[] letters = word.toCharArray();
                ArrayList shuffledLetters = new ArrayList();
                for (char c : letters) {
                    shuffledLetters.add(c);
                }
                Collections.shuffle(shuffledLetters);
                StringBuilder sb = new StringBuilder();
                for (Object c : shuffledLetters) {
                    sb.append(c);
                }
                scrambledWords[i] = sb.toString();
            }
            createLabelsAndTextboxes(selectedWords, scrambledWords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    



    public static void main(String[] args) {
        new Scrabble();
    }
}

