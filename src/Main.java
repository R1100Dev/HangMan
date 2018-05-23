import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    private JPanel panel;
    private JButton STARTButton;
    private JTextArea guess;
    private JLabel word;
    private JLabel w;
    private JLabel rem;
    private JButton check;
    private JLabel man;

    Proccess p = new Proccess();

    public Main() {

        STARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.init();
                p.newWord();
                rem.setText("remaining attempts :" + p.getError());
                man.setIcon(new javax.swing.ImageIcon("13.png"));


                w.setText("wrng guesses :   \n");

                word.setText(p.display());
            }
        });
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    check2();
                }catch(Exception ee){

                }
            }
        });
        guess.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                try {

                    if (e.getKeyChar() == '\n' && guess.getText().length()>1) {
                        guess.setText(guess.getText().substring(0, 1));
                        check2();
                        // guess.setText("");
                    }
                    if (guess.getText().length() > 0) {
                        guess.setText(guess.getText().substring(1, 1));
                    }
                }catch(Exception ee){

                }
            }
        });
    }

    public static void main(String[] args) {




        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
       // Main m = new Main();


    }




    public void check2() {

        if (p.getError() > 0 && !p.getSecrete().equals("")) {
            int i = 0;
            if (p.getSecrete().indexOf(guess.getText().charAt(0)) == -1) {
                if (p.wrong.indexOf(guess.getText()) == -1) {
                    p.setError(p.getError() - 1);
                    rem.setText("remaining attempts :" + p.getError());
                    manUpdate();
                    p.wrong.add(guess.getText());

                    w.setText(w.getText() + " " + guess.getText());
                }
            } else {
                for (char c : p.getSecrete().toCharArray()) {
                    if (guess.getText().equals(c + "")) {
                        p.known.set(i, c + "");

                    }
                    i++;
                }
                if (p.known.indexOf(" _") == -1) {
                    p.setSecrete("");
                    man.setIcon(new javax.swing.ImageIcon("wonMan.png"));
                }
                refresh();
            }
        } else if (p.getError() == 0) {
            man.setIcon(new javax.swing.ImageIcon("lost.png"));
            int i = 0;
            for (char c : p.getSecrete().toCharArray()) {

                p.known.set(i, c + "");


                i++;
            }
        }
        refresh();

        guess.setText("");
    }

    private void manUpdate() {

        int level = (p.getError()) * 13 / (p.getSecrete().length());

        man.setIcon(new javax.swing.ImageIcon(level + ".png"));
    }

    private void refresh() {
        String ss = "";
        for (String s : p.known) {
            ss = ss + " " + s;
        }
        word.setText(ss);
    }
}
