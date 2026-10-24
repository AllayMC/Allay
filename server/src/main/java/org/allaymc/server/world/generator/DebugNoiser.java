package org.allaymc.server.world.generator;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.generator.context.NoiseContext;
import org.allaymc.api.world.generator.function.Noiser;
import org.allaymc.server.network.protocol.NetworkBlockPalette;

import java.util.Comparator;

/**
 * Lays out every block state on a floor, one per second block from {@code (2, 1, 2)} on, like the debug world of Java
 * Edition. The newest blocks come first to make them easy to test:
 * <ol>
 *     <li>blocks added in 1.26.50;</li>
 *     <li>blocks with states added in 1.26.50, such as stair corners and fence connections;</li>
 *     <li>blocks added in 1.26.40;</li>
 *     <li>blocks added since 1.21.90;</li>
 *     <li>all other blocks.</li>
 * </ol>
 * Within a group, blocks are ordered by identifier.
 */
public class DebugNoiser implements Noiser {
    private static final int FLOOR_Y = 0;
    private static final int BLOCK_Y = 1;
    private static final int SPACING = 2;
    private static final int OFFSET = 2;

    protected BlockState floor;
    protected BlockState[] states;
    protected int columns;
    protected int rows;

    @Override
    public void init(String preset) {
        var v2168 = NetworkBlockPalette.of("2168");
        var v1001 = NetworkBlockPalette.of("1001");
        var v818 = NetworkBlockPalette.of("818");
        Comparator<BlockType<?>> newestFirst = Comparator.comparingInt(blockType -> {
            if (!v2168.knows(blockType)) {
                return 0;
            }
            if (blockType.getAllStates().stream().anyMatch(state -> v2168.toNetwork(state) != state.blockStateHash())) {
                return 1;
            }
            if (!v1001.knows(blockType)) {
                return 2;
            }
            return v818.knows(blockType) ? 4 : 3;
        });
        this.states = Registries.BLOCKS.getContent().values().stream()
                .filter(blockType -> blockType != BlockTypes.AIR)
                .sorted(newestFirst.thenComparing(blockType -> blockType.getIdentifier().toString()))
                .flatMap(blockType -> blockType.getAllStates().stream())
                .toArray(BlockState[]::new);
        this.columns = (int) Math.ceil(Math.sqrt(states.length));
        this.rows = (states.length + columns - 1) / columns;
        this.floor = BlockTypes.SMOOTH_STONE.getDefaultState();
    }

    @Override
    public boolean apply(NoiseContext context) {
        var chunk = context.getCurrentChunk();
        int baseX = chunk.getX() << 4;
        int baseZ = chunk.getZ() << 4;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = baseX + x;
                int worldZ = baseZ + z;
                if (worldX < 0 || worldZ < 0 || worldX >= OFFSET + columns * SPACING || worldZ >= OFFSET + rows * SPACING) {
                    continue;
                }
                chunk.setBlockState(x, FLOOR_Y, z, floor);
                int column = worldX - OFFSET;
                int row = worldZ - OFFSET;
                if (column < 0 || row < 0 || column % SPACING != 0 || row % SPACING != 0) {
                    continue;
                }
                int index = (row / SPACING) * columns + column / SPACING;
                if (index < states.length) {
                    chunk.setBlockState(x, BLOCK_Y, z, states[index]);
                }
            }
        }
        return true;
    }
}
