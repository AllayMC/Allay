package org.allaymc.server.block.type;

import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.type.BlockType;

/**
 * BlockDefinitionGenerator is used to generate a {@link BlockDefinition} based on a {@link BlockType}.
 * <p>
 * The generator receives the fully constructed BlockType and the BlockStateData from its default state,
 * allowing it to read physical properties (hardness, friction, etc.) without requiring duplicate configuration.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface BlockDefinitionGenerator {
    /**
     * Generates a {@link BlockDefinition} based on the specified {@link BlockType} and its default {@link BlockStateData}.
     *
     * @param blockType      the {@link BlockType} to generate the {@link BlockDefinition} for
     * @return the generated {@link BlockDefinition} representing the given {@code blockType}
     */
    BlockDefinition generate(BlockType<?> blockType);
}
