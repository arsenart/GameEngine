package org.example.view;

import org.example.model.Loot;
import org.example.model.NPC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class DialogWindow  extends JFrame {
    public DialogWindow(NPC npc) {

        setTitle("Dialog");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JLabel label = new JLabel("Hello, I am " + npc.name);
        container.add(label, BorderLayout.NORTH);
        JList<Loot> list = new JList(npc.ForSale.toArray());
        JButton closeButton = new JButton("Close");


    }

}
