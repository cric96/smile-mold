package it.unibo;

import it.unibo.controller.Simulation;
import it.unibo.core.behaviour.*;
import it.unibo.core.entity.GridWorld;
import it.unibo.core.entity.World;
import it.unibo.view.SimulationView;

import java.util.List;

public class Main {
    private static final int SIMULATION_DELTA_TIME = 16;
    public static void main(String[] args) {
        final World world = GridWorld.create(100, 100, 500, 42);
        final WorldBehaviour diffusion = new Diffusion(1 / 16.0);
        final WorldBehaviour evaporation = new Evaporation(0.6);
        final SlimeBehaviour slimeBehaviour = new Aggregation(42, 20, 1);
        final SimulationView view = new SimulationView(world, 800, 800);
        final Simulation simulation = new Simulation(world, List.of(diffusion, evaporation), List.of(slimeBehaviour), List.of(view));
        while (true) {
            simulation.update();
            try {
                Thread.sleep(SIMULATION_DELTA_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
