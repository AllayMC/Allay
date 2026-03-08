package org.allaymc.server.world.feature.fungus;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.allaymc.server.world.feature.AbstractWorldFeature;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Huge fungus (nether tree) feature.
 * Parameterized for both crimson and warped fungus.
 *
 * @author daoge_cmd
 */
public class HugeFungusFeature extends AbstractWorldFeature {

    public static final Identifier CRIMSON_IDENTIFIER = new Identifier("minecraft:huge_crimson_fungus");
    public static final Identifier WARPED_IDENTIFIER = new Identifier("minecraft:huge_warped_fungus");

    private final BlockType<?> stemType;
    private final BlockType<?> wartBlockType;

    public HugeFungusFeature(Identifier identifier, BlockType<?> stemType, BlockType<?> wartBlockType) {
        super(identifier);
        this.stemType = stemType;
        this.wartBlockType = wartBlockType;
    }

    @Override
    public boolean place(WorldFeatureContext context, int x, int y, int z) {
        var random = ThreadLocalRandom.current();
        int height = 4 + random.nextInt(9); // 4-12

        if (!canGenerate(context, x, y, z, height)) {
            return false;
        }

        placeTrunk(context, x, y, z, height);
        placeCanopy(context, x, y, z, height, random);

        return true;
    }

    private boolean canReplace(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockType == BlockTypes.AIR ||
               blockType.hasBlockTag(BlockTags.REPLACEABLE) ||
               blockType.hasBlockTag(BlockTags.LEAVES);
    }

    private boolean canGenerate(WorldFeatureContext context, int x, int y, int z, int height) {
        // Check ground is nylium
        var groundType = context.getBlockState(x, y - 1, z).getBlockType();
        if (groundType != BlockTypes.CRIMSON_NYLIUM && groundType != BlockTypes.WARPED_NYLIUM) {
            return false;
        }

        // Check trunk column is clear
        for (int dy = 0; dy < height; dy++) {
            if (!canReplace(context.getBlockState(x, y + dy, z))) {
                return false;
            }
        }

        return true;
    }

    private void placeTrunk(WorldFeatureContext context, int x, int y, int z, int height) {
        var stemState = stemType.getDefaultState();
        for (int dy = 0; dy < height; dy++) {
            if (canReplace(context.getBlockState(x, y + dy, z))) {
                context.setBlockState(x, y + dy, z, stemState);
            }
        }
    }

    private void placeCanopy(WorldFeatureContext context, int x, int y, int z, int height, ThreadLocalRandom random) {
        // Canopy layers: from height-3 to height-1 (5x5 outline), plus top cap at height (3x3)
        for (int layer = height - 3; layer < height; layer++) {
            int canopyY = y + layer;
            placeCanopyLayer(context, x, canopyY, z, 2, true, random);
        }

        // Top cap (3x3 filled)
        int topY = y + height;
        placeCanopyLayer(context, x, topY, z, 1, false, random);
    }

    private void placeCanopyLayer(WorldFeatureContext context, int x, int y, int z, int radius, boolean hollow, ThreadLocalRandom random) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                // For hollow layers, skip interior blocks
                if (hollow && Math.abs(dx) < radius && Math.abs(dz) < radius) {
                    continue;
                }

                // 50% chance to skip corners
                if (Math.abs(dx) == radius && Math.abs(dz) == radius) {
                    if (random.nextBoolean()) {
                        continue;
                    }
                }

                if (!canReplace(context.getBlockState(x + dx, y, z + dz))) {
                    continue;
                }

                // 5% chance for shroomlight instead of wart block
                if (random.nextInt(20) == 0) {
                    context.setBlockState(x + dx, y, z + dz, BlockTypes.SHROOMLIGHT.getDefaultState());
                } else {
                    context.setBlockState(x + dx, y, z + dz, wartBlockType.getDefaultState());
                }

                // Hanging vines: 1/3 chance per edge position on hollow layers
                if (hollow && isEdge(dx, dz, radius) && random.nextInt(3) == 0) {
                    int vineLength = random.nextInt(5); // 0-4
                    for (int vy = 1; vy <= vineLength; vy++) {
                        if (canReplace(context.getBlockState(x + dx, y - vy, z + dz))) {
                            context.setBlockState(x + dx, y - vy, z + dz, wartBlockType.getDefaultState());
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean isEdge(int dx, int dz, int radius) {
        return Math.abs(dx) == radius || Math.abs(dz) == radius;
    }
}
