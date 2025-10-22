package controllers;

// Use of a separate Main class is to support encapsulation and modularity.
public class Main {
    public static void main(String[] args) {
        Thread gameThread = new Thread(new Game());
        gameThread.start();
    }
}