package g;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JPanel {
    
    Display(int size, Particle[] particles) {
        JFrame frame = new JFrame("G");
        frame.setSize(size, size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.setVisible(true);
        this.size = size;
        this.particles = particles;
    }
    
    private final int size;
    private final Particle[] particles;
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.size, this.size);
        g.setColor(Color.WHITE);
        int i = 0;
        try {
        for(; i < this.particles.length; i = i + 1) {
            if (particles[i].getXPosition() > -this.size / 2 && particles[i].getXPosition() < this.size / 2
                && particles[i].getYPosition() > -this.size / 2 && particles[i].getYPosition() < this.size / 2) {
                
                g.fillRect((int) Math.floor(particles[i].getXPosition() + this.size / 2),
                           (int) Math.floor(particles[i].getYPosition() + this.size / 2), 1, 1);
            }
        } 
        } catch (NullPointerException npe) {
            System.out.println(i);
        }
    }
}