package org.allaymc.server.world.generator.flat;

import org.allaymc.api.block.interfaces.BlockBedrockBehavior;
import org.allaymc.api.block.interfaces.BlockDirtBehavior;
import org.allaymc.api.block.interfaces.BlockGrassBehavior;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.world.generator.ChunkGenerateContext;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.generator.WorldGeneratorType;

import java.util.ArrayList;

/**
 * Allay Project 2023/7/8
 *
 * @author daoge_cmd
 */
public class FlatWorldGenerator extends WorldGenerator {

    protected BlockState[] layers;

    public FlatWorldGenerator(String preset) {
        super(preset);
        parsePreset();
    }

    protected void parsePreset() {
        var list = new ArrayList<BlockState>();
        if (preset.isBlank()) {
            var bedrock = BlockBedrockBehavior.BEDROCK_TYPE.getDefaultState();
            var grass = BlockGrassBehavior.GRASS_TYPE.getDefaultState();
            var dirt = BlockDirtBehavior.DIRT_TYPE.getDefaultState();
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
                var blockState = BlockTypeRegistry.getRegistry().get(new Identifier(blockId)).getDefaultState();
                for (int i = 1; i <= count; i++) {
                    list.add(blockState);
                }
            }
        }
        layers = list.toArray(BlockState[]::new);
    }

    @Override
    public void generate(ChunkGenerateContext context) {
        var flatChunk = context.chunk();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                flatChunk.setHeight(x, z, layers.length + 1);
                for (int y = 0; y < layers.length; y++) {
                    flatChunk.setBlockState(x, y, z, layers[y]);
                }
            }
        }
    }

    @Override
    public String getGeneratorName() {
        return "FLAT";
    }

    @Override
    public WorldGeneratorType getType() {
        return WorldGeneratorType.FLAT;
    }
}
