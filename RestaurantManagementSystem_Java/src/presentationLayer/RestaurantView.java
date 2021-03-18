package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RestaurantView extends JFrame{
    private JButton adminButton = new JButton("Admin");
    private JButton chefButton = new JButton("Chef");
    private JButton waiterButton = new JButton("Waiter");

    public RestaurantView(){
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(4,0));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,0));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,0));

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,0));

        JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1,0));

        JLabel text = new JLabel("Welcome! Choose your role:",SwingConstants.CENTER);
        panel1.add(text);

        adminButton.setPreferredSize(new Dimension(400,50));
        chefButton.setPreferredSize(new Dimension(400,50));
        waiterButton.setPreferredSize(new Dimension(400,50));

        panel2.add(adminButton);
        panel3.add(chefButton);
        panel4.add(waiterButton);

        panelMain.add(panel1);
        panelMain.add(panel2);
        panelMain.add(panel3);
        panelMain.add(panel4);

        this.setContentPane(panelMain);
        this.setTitle("Restaurant System Manager");
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    void adminButtonListener(ActionListener l1){
        adminButton.addActionListener(l1);
    }

    void chefButtonListener(ActionListener l2){
        chefButton.addActionListener(l2);
    }

    void waiterButtonListener(ActionListener l3){
        waiterButton.addActionListener(l3);
    }

}
