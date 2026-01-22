package org.allaymc.server.block.type;

import org.cloudburstmc.nbt.NbtMap;

/**
 * Block definition sent to the client for custom block registration.
 * <p>
 * The NBT data structure contains:
 * <ul>
 *   <li>{@code components} - Block components defining behavior and appearance</li>
 *   <li>{@code menu_category} - Creative inventory category and group</li>
 *   <li>{@code vanilla_block_data} - Contains the unique block_id</li>
 *   <li>{@code properties} - Block state property definitions</li>
 *   <li>{@code permutations} - State-specific component overrides with Molang conditions</li>
 *   <li>{@code molangVersion} - Molang version for condition evaluation</li>
 * </ul>
 *
 * @param data the NBT data containing the block definition
 * @author daoge_cmd
 * @see CustomBlockDefinitionGenerator
 * @see <a href="https://wiki.bedrock.dev/blocks/block-components">Bedrock Block Components</a>
 */
public record BlockDefinition(NbtMap data) {
    /**
     * Default definition for vanilla blocks.
     */
    public static final BlockDefinition DEFAULT = new BlockDefinition(NbtMap.EMPTY);
}
