package main.java.dev.codenmore.juegorpg.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.java.dev.codenmore.juegorpg.Context;
import main.java.dev.codenmore.juegorpg.entities.GameObject;
import main.java.dev.codenmore.juegorpg.entities.player.Doctor;
import main.java.dev.codenmore.juegorpg.entities.player.Gunner;
import main.java.dev.codenmore.juegorpg.entities.player.Sniper;

public class CharacterSelectionPanel implements GameObject {

    private float x;
    private float y;

    public CharacterSelectionPanel(float x, float y, float sizeX, float sizeY) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    private float sizeX;
    private float sizeY;


    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
    }
}