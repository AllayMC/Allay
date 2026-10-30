package org.allaymc.api.world.chunk;

/**
 * {@code ChunkState} represents
 * the ordered generation stages through which a chunk becomes
 * usable. Declaration order defines predecessor, successor, and comparison
 * results; generation code uses these relationships to request neighboring
 * chunks at the stage required by each generation step.
 *
 * @author daoge_cmd
 */
public enum ChunkState {
    /**
     * Initial stage before terrain generation begins.
     */
    NEW,
    /**
     * Base terrain and biome generation stage.
     */
    NOISE,
    /**
     * Population stage for features that can affect neighboring chunks.
     */
    POPULATE,
    /**
     * {@code FULL} marks completed generation. Runtime load hooks and normal
     * chunk lookup select this stage before exposing a usable chunk.
     */
    FULL;

    /**
     * {@code VALUES} caches declaration order for predecessor and successor
     * lookup without allocating another enum array for each request.
     */
    private static final ChunkState[] VALUES = values();

    /**
     * Returns the immediately preceding generation stage, or {@code null} for
     * {@link #NEW}. The result follows enum declaration order and does not
     * describe the state of any particular chunk.
     */
    public ChunkState getParent() {
        var parentIndex = ordinal() - 1;
        return parentIndex != -1 ? VALUES[parentIndex] : null;
    }

    /**
     * Returns the immediately following generation stage, or {@code null} for
     * {@link #FULL}. The result follows enum declaration order and does not
     * start generation or change a chunk.
     */
    public ChunkState getNext() {
        var nextIndex = ordinal() + 1;
        return nextIndex != VALUES.length ? VALUES[nextIndex] : null;
    }

    /**
     * Returns whether this generation stage is at least as advanced as the
     * supplied stage.
     */
    public boolean isOrAfter(ChunkState state) {
        return ordinal() >= state.ordinal();
    }

    /**
     * Returns whether this generation stage is strictly more advanced than the
     * supplied stage.
     */
    public boolean isAfter(ChunkState state) {
        return ordinal() > state.ordinal();
    }

    /**
     * Returns whether this generation stage is no more advanced than the
     * supplied stage.
     */
    public boolean isOrBefore(ChunkState state) {
        return ordinal() <= state.ordinal();
    }

    /**
     * Returns whether this generation stage is strictly earlier than the
     * supplied stage.
     */
    public boolean isBefore(ChunkState state) {
        return ordinal() < state.ordinal();
    }
}
