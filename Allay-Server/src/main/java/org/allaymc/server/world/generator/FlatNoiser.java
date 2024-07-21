package org.allaymc.server.world.generator;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.context.NoiseContext;
import org.allaymc.api.world.generator.function.Noiser;

import java.util.ArrayList;

import static org.allaymc.api.block.type.BlockTypes.*;

/**
 * Allay Project 2023/7/8
 *
 * @author daoge_cmd
 */
public class FlatNoiser implements Noiser {

    protected BlockState[] layers;

    @Override
    public void init(WorldGenerator generator) {
        parsePreset(generator.getPreset());
    }

    @Override
    public String getName() {
        return "FLAT_NOISER";
    }

    protected void parsePreset(String preset) {
        var list = new ArrayList<BlockState>();
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
        layers = list.toArray(BlockState[]::new);
    }

    @Override
    public Boolean apply(NoiseContext context) {
        var flatChunk = context.getCurrentChunk();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                flatChunk.setHeight(x, z, layers.length + 1);
                for (int y = 0; y < layers.length; y++) {
                    flatChunk.setBlockState(x, y, z, layers[y]);
                }
            }
        }
        return true;
    }
}
