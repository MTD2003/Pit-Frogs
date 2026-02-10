package controllers;

import java.awt.Graphics;

public interface State {
    public void step();

    public void draw(Graphics g);
}