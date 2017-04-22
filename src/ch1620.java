import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ch1620 extends JFrame {
    Thread t = null;
    Display t_display;
    JButton b1, b2, b3;
    JLabel mm, colon1, ss, colon2, ms;
    JPanel LED;
    ButtonPanel Menu;
    String min, sec, msec;
    int n1 = 0, n2 = 0, n3 = 0, count = 0;
    ButtonAction BA = new ButtonAction();

    class Display extends Thread {
        Display() {
            setTitle("StopWatch");
            setLayout(new BorderLayout());
            setVisible(true);
        }

        public void run() {
            n1 = Integer.parseInt(mm.getText());
            n2 = Integer.parseInt(ss.getText());
            n3 = Integer.parseInt(ms.getText());
            while (t == Thread.currentThread()) {
                n3 = count % 10;
                n2 = (count % 1000) / 10;
                n1 = (count % 100000) / 1000;
                min = String.format("%02d", n1);
                sec = String.format("%02d", n2);
                msec = String.format("%01d", n3);
                mm.setText(min);
                ss.setText(sec);
                ms.setText(msec);
                count++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

    }

    class ButtonPanel extends JPanel {
        ButtonPanel() {
            b1 = new JButton("Start");
            b2 = new JButton("Pause");
            b3 = new JButton("Reset");
            b1.addActionListener(BA);
            b2.addActionListener(BA);
            b3.addActionListener(BA);
            setLayout(new FlowLayout());
            b1.setEnabled(true);
            b2.setEnabled(false);
            b3.setEnabled(false);
            add(b1);
            add(b2);
            add(b3);
        }
    }

    class ButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn.getText().equals("Start")) {
                t_display = new Display();
                t_display.start();
                t = t_display;
                System.out.println("Start");
                b1.setEnabled(false);
                b2.setEnabled(true);
                b3.setEnabled(false);
            }
            if (btn.getText().equals("Pause")) {
                //t.stop();
                t = null;
                System.out.println("Pause");
                b1.setEnabled(true);
                b2.setEnabled(false);
                b3.setEnabled(true);
            }
            if (btn.getText().equals("Reset")) {
                b1.setEnabled(true);
                b2.setEnabled(false);
                b3.setEnabled(false);
                System.out.println("Reset");
                count = 0;
                mm.setText("00");
                ss.setText("00");
                ms.setText("0");
            }
        }
    }

    ch1620() {
        setLayout(new BorderLayout());
        Display t_display = new Display();
        LED = new JPanel(new FlowLayout());
        setLED();
        add(LED, BorderLayout.CENTER);
        Menu = new ButtonPanel();
        add(Menu, BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void setLED() {
        mm = new JLabel();
        mm.setFont(new Font("Serif", Font.BOLD, 50));
        colon1 = new JLabel();
        colon1.setFont(new Font("Serif", Font.BOLD, 50));
        ss = new JLabel();
        ss.setFont(new Font("Serif", Font.BOLD, 50));
        colon2 = new JLabel();
        colon2.setFont(new Font("Serif", Font.BOLD, 50));
        ms = new JLabel();
        ms.setFont(new Font("Serif", Font.BOLD, 50));
        min = String.format("%02d", n1);
        sec = String.format("%02d", n2);
        msec = String.format("%01d", n3);
        mm.setText(min);
        colon1.setText(":");
        ss.setText(sec);
        colon2.setText(":");
        ms.setText(msec);
        LED.add(mm);
        LED.add(colon1);
        LED.add(ss);
        LED.add(colon2);
        LED.add(ms);
    }

    public static void main(String[] args) {
        new ch1620();
    }
}

