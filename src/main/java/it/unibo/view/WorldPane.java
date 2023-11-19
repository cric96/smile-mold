package it.unibo.view;

import it.unibo.core.Position;
import it.unibo.core.entity.World;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WorldPane extends JPanel {
    private static final int MAX_COLOR = 150;
    private static final int DELTA_PATCH = 1;
    private final double[][] pheromones;
    private List<Position> slime;

    private final int patchSize;

    public WorldPane(final int row, final int columns, int patchSize) {
        // Init the pheromones matrix
        this.pheromones = new double[row][columns];
        // Init the slime list
        this.slime = List.of();
        this.patchSize = patchSize;
    }

    public void update(final World world){
        // Update the pheromones matrix
        world.getPatches().forEach(patch -> {
            final Position position = patch.getPosition();
            this.pheromones[position.y()][position.x()] = patch.getPheromone();
        });

        // Update the slime list
        this.slime = world.getSlimes().stream().map(world::getSlimePosition).toList();
        // Repaint the panel
        this.repaint();
    }

    // Draw the world
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        // Draw the pheromones
        for (int i = 0; i < this.pheromones.length; i++) {
            for (int j = 0; j < this.pheromones[i].length; j++) {
                final double pheromone = this.pheromones[i][j];
                final int color = (pheromone) < MAX_COLOR ? (int) (pheromone): MAX_COLOR;
                final float pheromoneLevel = (float) (color / (float) MAX_COLOR);
                g.setColor(java.awt.Color.getHSBColor(0.33f, pheromoneLevel, pheromoneLevel/*color / (float) MAX_COLOR)*/));
                g.fillRect(j * patchSize, i * patchSize, patchSize - DELTA_PATCH, patchSize - DELTA_PATCH);
            }
        }
        // Draw the slime
        this.slime.forEach(position -> {
            g.setColor(Color.RED);
            g.fillOval(position.x() * patchSize, position.y() * patchSize, patchSize, patchSize);
        });
    }
}
