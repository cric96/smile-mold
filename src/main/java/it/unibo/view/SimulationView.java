package it.unibo.view;

import it.unibo.controller.SimulationObserver;
import it.unibo.core.entity.World;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class SimulationView implements SimulationObserver {
    private final WorldPane worldPane;

    public SimulationView(final World world, final int width, final int height) {
        // init the view using the world state
        final JFrame frame = new JFrame("Slime Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
        // Compute the size for each patch
        final int patchSize = Math.min(width / world.getWidth(), height / world.getHeight());
        // Create a JPanel to draw the world
        this.worldPane = new WorldPane(world.getHeight(), world.getWidth(), patchSize);
        frame.add(worldPane);
    }

    @Override
    public void onWorldChanges(World world) {
        try {
            SwingUtilities.invokeAndWait(() -> this.worldPane.update(world));
            // force the repaint of the world
            this.worldPane.repaint();

        } catch (InterruptedException | InvocationTargetException e) {
            throw new RuntimeException("View broken: " + e.getMessage());
        }
    }

}
