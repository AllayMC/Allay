package org.allaymc.api.world.chunk;

/**
 * ChunkState represents the state of a chunk.
 *
 * @author daoge_cmd
 */
public enum ChunkState {
    /**
     * The chunk is newly created and does not contain any things.
     */
    NEW,
    /**
     * The chunk has had noise generated for it, including base terrains and biomes.
     */
    NOISE,
    /**
     * The chunk is populated, including trees, ores, and other features that may across multiple chunks.
     */
    POPULATE,
    /**
     * The chunk is post-processed, entities will be spawned in this state. The chunk is fully generated
     * and ready to be used.
     */
    FULL;

    private static final ChunkState[] VALUES = values();

    /**
     * Returns the parent state of the state, or {@code null} if this is the first state.
     *
     * @return The parent state, or {@code null} if there is no parent state.
     */
    public ChunkState getParent() {
        var parentIndex = ordinal() - 1;
        return parentIndex != -1 ? VALUES[parentIndex] : null;
    }

    /**
     * Returns the next state of the state, or {@code null} if this is the last state.
     *
     * @return The next state, or {@code null} if there is no next state.
     */
    public ChunkState getNext() {
        var nextIndex = ordinal() + 1;
        return nextIndex != VALUES.length ? VALUES[nextIndex] : null;
    }

    /**
     * Check if this state is the same or after the given state.
     *
     * @param state The state to compare to.
     *
     * @return {@code true} if this state is the same or after the given state, {@code false} otherwise.
     */
    public boolean isOrAfter(ChunkState state) {
        return ordinal() >= state.ordinal();
    }

    /**
     * Check if this state is after the given state.
     *
     * @param state The state to compare to.
     *
     * @return {@code true} if this state is after the given state, {@code false} otherwise.
     */
    public boolean isAfter(ChunkState state) {
        return ordinal() > state.ordinal();
    }

    /**
     * Check if this state is the same or before the given state.
     *
     * @param state The state to compare to.
     *
     * @return {@code true} if this state is the same or before the given state, {@code false} otherwise.
     */
    public boolean isOrBefore(ChunkState state) {
        return ordinal() <= state.ordinal();
    }

    /**
     * Check if this state is before the given state.
     *
     * @param state The state to compare to.
     *
     * @return {@code true} if this state is before the given state, {@code false} otherwise.
     */
    public boolean isBefore(ChunkState state) {
        return ordinal() < state.ordinal();
    }
}
