package controllers;

import utilities.InputMirror;

import java.awt.Graphics;

public interface State {
    public void step();

    public void draw(Graphics g);
}

/* TODO:
 * Figure out scaling system (relevant for hitboxes +  visuals).
 * --> Hitbox scaling is unique.
 * --> Get screensize from gameobject and do scaling here. If I  need to change approach later I can.
 * Figure out inputs (direct panel access? two middlemen? getInputs() function in main class?)
 * --> Input object seems like the best option right now.
 */