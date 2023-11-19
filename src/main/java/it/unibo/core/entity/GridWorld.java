package it.unibo.core.entity;

import it.unibo.core.Position;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GridWorld implements World {
    private final int width;
    private final int height;
    private final Patch[][] patches;
    private final Map<Slime, Position> slimes;

    private GridWorld(final int width, final int height, final int numSlimes, final int seed) {
        if(width <= 0 || height <= 0)
            throw new IllegalArgumentException("Width and height must be positive");
        if(numSlimes <= 0)
            throw new IllegalArgumentException("Number of slimes must be positive");
        if(seed <= 0)
            throw new IllegalArgumentException("Seed must be positive");
        if(numSlimes > width * height)
            throw new IllegalArgumentException("Number of slimes must be less than the number of patches");

        this.width = width;
        this.height = height;
        this.patches = new Patch[width][height];
        this.slimes = new HashMap<>();
        this.initPatches(seed);
        this.initSlimes(numSlimes, seed);
    }

    private void initPatches(final int seed) {
        final Random random = new Random(seed);
        for(int x = 0; x < this.width; x++)
            for(int y = 0; y < this.height; y++)
                this.patches[x][y] = new Patch(new Position(x, y), 0.0);
    }

    private void initSlimes(final int numSlimes, final int seed) {
        // ensure that the position of each slime is unique
        final Random random = new Random(seed);
        for(int i = 0; i < numSlimes; i++) {
            Position position;
            do {
                position = new Position(random.nextInt(this.width), random.nextInt(this.height));
            } while(this.slimes.containsKey(position));
            this.slimes.put(new Slime(this), position);
        }
    }

    public static GridWorld create(final int width, final int height, final int numSlimes) {
        return new GridWorld(width, height, numSlimes, new Random().nextInt());
    }

    public static GridWorld create(final int width, final int height, final int numSlimes, final int seed) {
        return new GridWorld(width, height, numSlimes, seed);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public Position getSlimePosition(Slime slime) {
        return this.slimes.get(slime);
    }

    @Override
    public void moveSlime(Slime slime, Position position) {
        this.slimes.put(slime, position);
    }

    @Override
    public Collection<Slime> getSlimes() {
        return this.slimes.keySet();
    }

    @Override
    public Collection<Patch> getPatches() {
        return Arrays.stream(this.patches).flatMap(Arrays::stream).collect(Collectors.toList());
    }

    public Patch getPatch(Position position) {
        return this.patches[position.x()][position.y()];
    }

    @Override
    public Collection<Patch> getNeighborhood(Patch patch) {
        final Position position = patch.getPosition();
        final int x = position.x();
        final int y = position.y();
        // Generate all x considering the boundaries that are wrapped (x - 1) % width
        final int[] xs = IntStream.of(x - 1, x, x + 1).map(i -> (i + this.width) % this.width).toArray();
        // Generate all y considering the boundaries that are wrapped (y - 1) % height
        final int[] ys = IntStream.of(y - 1, y, y + 1).map(i -> (i + this.height) % this.height).toArray();
        return Arrays.stream(xs).boxed()
                .flatMap(x1 -> Arrays.stream(ys).boxed().map(y1 -> this.patches[x1][y1]))
                .filter(p -> !p.equals(patch))
                .collect(Collectors.toList());

    }
}
