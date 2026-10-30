package org.allaymc.server.world.generator;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.generator.context.NoiseContext;
import org.allaymc.api.world.generator.function.Noiser;

import java.util.ArrayList;

import static org.allaymc.api.block.type.BlockTypes.*;

/**
 * {@code FlatNoiser} fills every horizontal column of a chunk with a configured
 * sequence of block layers starting at absolute Y zero. A blank preset selects
 * bedrock, three dirt layers, and grass; explicit entries repeat registered
 * default block states according to their requested counts.
 *
 * @author daoge_cmd
 */
public class FlatNoiser implements Noiser {

    /**
     * {@code layers} holds the sequence published after successful preset
     * parsing. Generation reads these shared states and requires initialization
     * first.
     */
    protected BlockState[] layers;

    /**
     * Parses the supplied preset and replaces this function's layer sequence
     * before generation. The preset must be non-{@code null}, and malformed entries or
     * unknown block identifiers propagate failure to generator initialization.
     */
    @Override
    public void init(String preset) {
        parsePreset(preset);
    }

    /**
     * Builds a layer sequence from semicolon-separated {@code identifierxcount}
     * entries, splitting each at its first literal {@code x}. A blank preset
     * selects the built-in five-layer ground sequence. Counts at or below zero
     * contribute no layers; unknown identifiers, missing counts, and invalid
     * integers fail parsing. The previous sequence remains assigned until every
     * entry succeeds, and no dimension-height validation occurs before the
     * resulting array is published.
     */
    protected void parsePreset(String preset) {
        var list = new ArrayList<BlockState>();
        // A blank preset supplies conventional flat ground rather than an empty
        // chunk; explicit entries instead determine the entire sequence without
        // adding default layers.
        if (preset.isBlank()) {
            var bedrock = BEDROCK.getDefaultState();
            var grass = GRASS_BLOCK.getDefaultState();
            var dirt = DIRT.getDefaultState();
            list.add(bedrock);
            list.add(dirt);
            list.add(dirt);
            list.add(dirt);
            list.add(grass);
        } else {
            for (var layer : AllayStringUtils.fastSplit(preset, ";")) {
                var entry = AllayStringUtils.fastTwoPartSplit(layer, "x", "");
                var blockId = entry[0];
                var count = Integer.parseInt(entry[1]);
                var blockState = Registries.BLOCKS.get(new Identifier(blockId)).getDefaultState();
                for (int i = 1; i <= count; i++) {
                    list.add(blockState);
                }
            }
        }
        // Publish the new sequence only after all entries parse, so a failed
        // reinitialization leaves the previously configured layer array
        // available to later generation calls.
        layers = list.toArray(BlockState[]::new);
    }

    /**
     * Fills every local horizontal column with the configured layers starting
     * at absolute Y zero and returns {@code true}. The function must be initialized
     * first, and the dimension must support every addressed height; failures
     * can leave a partially filled chunk.
     */
    @Override
    public boolean apply(NoiseContext context) {
        var flatChunk = context.getCurrentChunk();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                // Layers start at absolute zero, independent of the dimension's
                // minimum height.
                for (int y = 0; y < layers.length; y++) {
                    flatChunk.setBlockState(x, y, z, layers[y]);
                }
            }
        }
        return true;
    }
}
