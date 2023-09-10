package cn.allay.server.world.generator.flat;

import cn.allay.api.block.interfaces.BlockBedrockBehavior;
import cn.allay.api.block.interfaces.BlockGrassBehavior;
import cn.allay.api.world.GeneratorType;
import cn.allay.api.world.generator.ChunkGenerateContext;
import cn.allay.api.world.generator.WorldGenerator;

/**
 * Allay Project 2023/7/8
 *
 * @author daoge_cmd
 */
public class FlatWorldGenerator implements WorldGenerator {
    @Override
    public void generate(ChunkGenerateContext context) {
        var bedrock = BlockBedrockBehavior.BEDROCK_TYPE.getDefaultState();
        var grass = BlockGrassBehavior.GRASS_TYPE.getDefaultState();
        var flatChunk = context.chunk();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 5; y++) {
                    if (y == 0) {
                        flatChunk.setBlockState(x, y, z, bedrock);
                    } else flatChunk.setBlockState(x, y, z, grass);
                }
            }
        }
    }

    @Override
    public void populate(ChunkGenerateContext context) {
        //do nothing
    }

    @Override
    public String getGeneratorName() {
        return "FLAT";
    }

    @Override
    public GeneratorType getType() {
        return GeneratorType.FLAT;
    }
}
