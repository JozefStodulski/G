/*

Your nested loop was causing the problem. I've also placed gravity *really* low to slow things down enough to see what's happening.

Also, if your particles collide head-on, they "merge" (become hidden behind one-another)

Have fun debugging!

*/

package g;

import java.util.Arrays;

public class Universe {

    static final Double g = .0001;
    static final int canvasSize = 700;
    static final int particlesN = 30;
    
    public final Particle[] particles = new Particle[particlesN];
    final Double[][][] weights = new Double[particlesN][particlesN][2];
    final Display display = new Display(canvasSize, particles);
    
    Universe() {
        
        //initialize particles & weights
        for (int i = 0; i < particles.length; i = i + 1) {
            particles[i] = new Particle(canvasSize);
            for (int j = 0; j < particlesN; j = j + 1) {
                weights[i][j][0] = 0.0;
                weights[i][j][1] = 0.0;
            }
        }
        
        //main loop
        while (true) {
            
            //calculate weights loop
            for (int p1 = 0; p1 < particlesN - 1; ++p1) {
                for (int p2 = 0; p2 < particlesN; ++p2) {
                    if (p2 == p1) {
                        continue;
                    }
                    Double xDisplacement = particles[p1].getXPosition() - particles[p2].getXPosition();
                    Double yDisplacement = particles[p1].getYPosition() - particles[p2].getYPosition();

                    Double displacement = Math.sqrt(Math.pow(xDisplacement, 2.0)
                                                    + Math.pow(yDisplacement, 2.0));
                    
                    Double weight = g / Math.pow(displacement, 2.0);

                    Double xWeight;
                    Double yWeight;
                    Double angleI;
                    if (xDisplacement != 0.0) {
                        angleI = Math.atan(yDisplacement / xDisplacement);
                        xWeight = weight * Math.cos(angleI);
                        if (xDisplacement < 0.0) {
                            xWeight = -xWeight;
                        }
                        yWeight = weight * Math.sin(angleI);
                        if (yDisplacement < 0.0) {
                            yWeight = -yWeight;
                        }
                    } else {
                        xWeight = 0.0;
                        yWeight = weight;
                        if (yDisplacement < 0.0) {
                            yWeight = -yWeight;
                        }
                    }
                    this.weights[p1][p2][0] = xWeight;
                    this.weights[p1][p2][1] = yWeight;
                    this.weights[p2][p1][0] = -xWeight;
                    this.weights[p2][p1][1] = -yWeight;
                }
            }
            
            //update particle velocities & positions loop
            for (int i = 0; i < particlesN; i = i + 1) {
                for (int j = 0; j < particlesN; j = j + 1) {
                    particles[i].changeVelocity(weights[i][j][0], weights[i][j][1]);
                }
                particles[i].updatePosition();
            }
            
            display.repaint();
        }
    }
}
