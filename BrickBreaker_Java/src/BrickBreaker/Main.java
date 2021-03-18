package BrickBreaker;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Domain gamePlay = new Domain();
        obj.setBounds(10, 10, 707, 600);
        obj.setLocation(380, 100);
        obj.setTitle("Brick Break Rock Roll");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
    }
}
