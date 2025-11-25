package org.allaymc.server.item.component;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.data.TintMethod;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFilledMapBaseComponent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.biome.BiomeTypes;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.ClientboundMapItemDataPacket;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author daoge_cmd
 */
@Getter
@Slf4j
public class ItemFilledMapBaseComponentImpl extends ItemBaseComponentImpl implements ItemFilledMapBaseComponent {

    protected static final String TAG_MAP_UUID = "map_uuid";
    // NOTICE: this tag doesn't exist in vanilla
    protected static final String TAG_MAP_IMAGE = "map_image";

    // Image height and width
    protected static final int IMAGE_HW = 128;
    protected static final int SEA_LEVEL = 62;

    // Color constants
    protected static final Color VOID = new Color(0, 0, 0, 0);
    protected static final Color BIRCH_FOLIAGE = new Color(0x80a755);
    protected static final Color EVERGREEN_FOLIAGE = new Color(0x619961);
    protected static final Color DRY_FOLIAGE_SPECIAL_A = new Color(0x7b5334);
    protected static final Color DRY_FOLIAGE_SPECIAL_B = new Color(0xa0a69c);
    protected static final Color SWAMP_BIOME_FOLIAGE = new Color(0x6a7039);
    protected static final Color SWAMP_BIOME_GRASS_A = new Color(0x6a7039);
    protected static final Color BIOME_SWAMP_GRASS_B = new Color(0x4c763c);
    protected static final Color MANGROVE_SWAMP_BIOME_FOLIAGE = new Color(0x8db127);
    protected static final Color ROOFED_FOREST_BIOME_GRASS = new Color(0x507a32);
    protected static final Color MESA_BIOME_GRASS = new Color(0x90814d);
    protected static final Color MESA_BIOME_FOLIAGE = new Color(0x9e814d);
    protected static final Color CHERRY_GROVE_BIOME_PLANT = new Color(0xb6db61);
    protected static final Color PALE_GARDEN_BIOME_PLANT = new Color(0x878d76);

    // Colormaps
    protected static final BufferedImage FOLIAGE_COLORMAP;
    protected static final BufferedImage DRY_FOLIAGE_COLORMAP;
    protected static final BufferedImage GRASS_COLORMAP;

    static {
        try {
            FOLIAGE_COLORMAP = ImageIO.read(Utils.getResource("colormap/foliage.png"));
            DRY_FOLIAGE_COLORMAP = ImageIO.read(Utils.getResource("colormap/dry_foliage.png"));
            GRASS_COLORMAP = ImageIO.read(Utils.getResource("colormap/grass.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected BufferedImage image;
    protected long mapId;

    public ItemFilledMapBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.mapId = UUID.randomUUID().getMostSignificantBits();
    }

    protected static Color getMapColor(Dimension dimension, int x, int z) {
        var color = VOID;
        var block = getMapColoredBlock(dimension, x, z);
        if (block == null) {
            return color;
        }

        color = computeMapColor(block);
        var upperLayer0 = block.offsetPos(BlockFace.UP, 0);
        var upperLayer1 = block.offsetPos(BlockFace.UP, 1);
        if (upperLayer0.getBlockType().hasBlockTag(BlockTags.WATER) ||
            upperLayer1.getBlockType().hasBlockTag(BlockTags.WATER)) {
            // The block is underwater
            var finalRed = color.getRed();
            var finalGreen = color.getGreen();
            var finalBlue = color.getBlue();
            // TODO: mix neighbor biomes' water color
            var waterColor = dimension.getBiome(block.getPosition()).getBiomeData().mapWaterColor();
            if (block.getPosition().y() < SEA_LEVEL) {
                // Under sea level, the farther away from sea level, the closer the color is to the ocean color
                var depth = SEA_LEVEL - block.getPosition().y();
                if (depth > 15) {
                    return waterColor;
                }

                finalBlue = waterColor.getBlue();
                var radio = depth / 15f;
                if (radio < 0.5f) {
                    radio = 0.5f;
                }
                finalRed += (int) ((waterColor.getRed() - finalRed) * radio);
                finalGreen += (int) ((waterColor.getGreen() - finalGreen) * radio);
            } else {
                // Block is under lake or river
                finalBlue = waterColor.getBlue();
                finalRed += (int) ((waterColor.getRed() - finalRed) * 0.5f);
                finalGreen += (int) ((waterColor.getGreen() - finalGreen) * 0.5f);
            }
            color = new Color(finalRed, finalGreen, finalBlue);
        }

        // If there is a height difference on the z-axis, the color will become darker or lighter
        var neighborBlock = getMapColoredBlock(dimension, x, z - 1);
        if (neighborBlock == null) {
            return color;
        }

        var blockY = block.getPosition().y();
        var neighborBlockY = neighborBlock.getPosition().y();
        if (blockY < neighborBlockY) {
            return darker(color, 0.85);
        }

        if (blockY > neighborBlockY) {
            return brighter(color, 0.85);
        }

        return color;
    }

    protected static Block getMapColoredBlock(Dimension dimension, int x, int z) {
        var chunk = dimension.getChunkManager().loadChunk(x >> 4, z >> 4).join();
        Preconditions.checkArgument(chunk != null);

        var chunkX = x & 0xF;
        var chunkZ = z & 0xF;
        int height = chunk.getHeight(chunkX, chunkZ);
        while (height >= dimension.getDimensionInfo().minHeight()) {
            var block = chunk.getBlockState(chunkX, height, chunkZ);
            var color = block.getBlockStateData().mapColor();
            var tintMethod = block.getBlockStateData().tintMethod();
            if (tintMethod == TintMethod.WATER || (color.getAlpha() == 0 && tintMethod == TintMethod.NONE)) {
                height--;
            } else {
                return new Block(block, new Position3i(x, height, z, dimension), 0);
            }
        }

        return null;
    }

    protected static Color computeMapColor(Block block) {
        var blockStateData = block.getBlockStateData();
        var tintMethod = blockStateData.tintMethod();
        return switch (tintMethod) {
            // TODO: find out how to handle STEM
            case NONE, STEM -> blockStateData.mapColor();
            case RED_STONE_WIRE -> Color.RED;
            // TODO: calculate mixed color
            case DEFAULT_FOLIAGE, BIRCH_FOLIAGE, EVERGREEN_FOLIAGE, DRY_FOLIAGE, GRASS -> getPlantColor(block);
            // Shouldn't reach here
            default -> throw new IllegalStateException("Unexpected tint method: " + tintMethod);
        };
    }

    /**
     * See <a href="https://minecraft.wiki/w/Color#Biome_colors">Biome Colors</a>
     */
    protected static Color getPlantColor(Block block) {
        var tintMethod = block.getBlockStateData().tintMethod();
        if (tintMethod == TintMethod.BIRCH_FOLIAGE) {
            return BIRCH_FOLIAGE;
        }

        if (tintMethod == TintMethod.EVERGREEN_FOLIAGE) {
            return EVERGREEN_FOLIAGE;
        }

        var biomeType = block.getDimension().getBiome(block.getPosition());
        if (biomeType == BiomeTypes.SWAMPLAND || biomeType == BiomeTypes.SWAMPLAND_MUTATED || biomeType == BiomeTypes.MANGROVE_SWAMP) {
            if (tintMethod == TintMethod.DRY_FOLIAGE) {
                return DRY_FOLIAGE_SPECIAL_A;
            }

            if (tintMethod == TintMethod.GRASS) {
                // TODO: The color of grass in (mangrove) swamp biome is based on perlin noise,
                // and we should use SWAMP_BIOME_GRASS_COLOR_B if perlin noise value < -0.1
                return SWAMP_BIOME_GRASS_A;
            }

            return biomeType == BiomeTypes.MANGROVE_SWAMP ? MANGROVE_SWAMP_BIOME_FOLIAGE : SWAMP_BIOME_FOLIAGE;
        }

        if (biomeType == BiomeTypes.ROOFED_FOREST || biomeType == BiomeTypes.ROOFED_FOREST_MUTATED) {
            if (tintMethod == TintMethod.GRASS) {
                return ROOFED_FOREST_BIOME_GRASS;
            }

            if (tintMethod == TintMethod.DRY_FOLIAGE) {
                return DRY_FOLIAGE_SPECIAL_A;
            }
        }

        if (biomeType == BiomeTypes.MESA ||
            biomeType == BiomeTypes.MESA_BRYCE ||
            biomeType == BiomeTypes.MESA_PLATEAU ||
            biomeType == BiomeTypes.MESA_PLATEAU_MUTATED ||
            biomeType == BiomeTypes.MESA_PLATEAU_STONE ||
            biomeType == BiomeTypes.MESA_PLATEAU_STONE_MUTATED
        ) {
            if (tintMethod == TintMethod.GRASS) {
                return MESA_BIOME_GRASS;
            }

            return MESA_BIOME_FOLIAGE;
        }

        if (biomeType == BiomeTypes.CHERRY_GROVE) {
            return CHERRY_GROVE_BIOME_PLANT;
        }

        if (biomeType == BiomeTypes.PALE_GARDEN) {
            if (tintMethod == TintMethod.DRY_FOLIAGE) {
                return DRY_FOLIAGE_SPECIAL_B;
            }

            return PALE_GARDEN_BIOME_PLANT;
        }

        var adjTemperature = Math.clamp(biomeType.getBiomeData().temperature(), 0, 1);
        var adjDownfall = Math.clamp(biomeType.getBiomeData().downfall(), 0, 1) * adjTemperature;
        var colormap = switch (tintMethod) {
            case TintMethod.DRY_FOLIAGE -> DRY_FOLIAGE_COLORMAP;
            case TintMethod.GRASS -> GRASS_COLORMAP;
            default -> FOLIAGE_COLORMAP;
        };
        var x = (int) ((1 - adjTemperature) * 255);
        var y = (int) ((1 - adjDownfall) * 255);
        return new Color(colormap.getRGB(x, y));
    }

    protected static Color brighter(Color source, double factor) {
        int r = source.getRed();
        int g = source.getGreen();
        int b = source.getBlue();
        int alpha = source.getAlpha();

        int i = (int) (1.0 / (1.0 - factor));
        if (r == 0 && g == 0 && b == 0) {
            return new Color(i, i, i, alpha);
        }
        if (r > 0 && r < i) r = i;
        if (g > 0 && g < i) g = i;
        if (b > 0 && b < i) b = i;

        return new Color(
                Math.min((int) (r / factor), 255),
                Math.min((int) (g / factor), 255),
                Math.min((int) (b / factor), 255),
                alpha
        );
    }

    protected static Color darker(Color source, double factor) {
        return new Color(
                Math.max((int) (source.getRed() * factor), 0),
                Math.max((int) (source.getGreen() * factor), 0),
                Math.max((int) (source.getBlue() * factor), 0),
                source.getAlpha()
        );
    }

    protected static int toABGR(int argb) {
        return ((argb >> 16) & 0xFF) |      // Blue
               ((argb >> 8) & 0xFF) << 8 |  // Green
               ((argb) & 0xFF) << 16 |      // Red
               ((argb >> 24) & 0xFF) << 24; // Alpha
    }

    @Override
    public void setImage(BufferedImage image) {
        if (image.getHeight() == IMAGE_HW && image.getWidth() == IMAGE_HW) {
            this.image = image;
        }

        // Image size is not 128x128, and we need to resize it
        var resizedImage = new BufferedImage(IMAGE_HW, IMAGE_HW, image.getType());

        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, IMAGE_HW, IMAGE_HW, null);
        g.dispose();
        this.image = resizedImage;
    }

    @Override
    public CompletableFuture<BufferedImage> renderMap(Dimension dimension, int startX, int startZ, int zoom) {
        return CompletableFuture
                .supplyAsync(() -> {
                    int[] pixels = new int[IMAGE_HW * IMAGE_HW];
                    for (int z = 0; z < IMAGE_HW * zoom; z += zoom) {
                        for (int x = 0; x < IMAGE_HW * zoom; x += zoom) {
                            pixels[(z * IMAGE_HW + x) / zoom] = getMapColor(dimension, startX + x, startZ + z).getRGB();
                        }
                    }

                    var image = new BufferedImage(IMAGE_HW, IMAGE_HW, BufferedImage.TYPE_INT_ARGB);
                    image.setRGB(0, 0, IMAGE_HW, IMAGE_HW, pixels, 0, IMAGE_HW);
                    return image;
                }, Server.getInstance().getVirtualThreadPool())
                .exceptionally(t -> {
                    log.error("Error while rendering map!", t);
                    return new BufferedImage(IMAGE_HW, IMAGE_HW, BufferedImage.TYPE_INT_ARGB);
                })
                .thenApply(image -> this.image = image);
    }

    @Override
    public void sendToPlayer(Player player) {
        if (image == null) {
            throw new IllegalStateException("Image is not set for the filled map.");
        }

        var packet = new ClientboundMapItemDataPacket();
        packet.setUniqueMapId(mapId);
        // Required since 1.19.20
        packet.setOrigin(Vector3i.ZERO);
        // Required as of 1.19.50
        packet.getTrackedEntityIds().add(mapId);
        packet.setHeight(IMAGE_HW);
        packet.setWidth(IMAGE_HW);
        var colors = new int[IMAGE_HW * IMAGE_HW];
        int index = 0;
        for (int y = 0; y < IMAGE_HW; y++) {
            for (int x = 0; x < IMAGE_HW; x++) {
                colors[index++] = toABGR(image.getRGB(x, y));
            }
        }
        packet.setColors(colors);
        player.sendPacket(packet);
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForLong(TAG_MAP_UUID, mapId -> this.mapId = mapId);
        extraTag.listenForByteArray(TAG_MAP_IMAGE, data -> {
            try {
                this.image = ImageIO.read(new ByteArrayInputStream(data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        builder.putLong(TAG_MAP_UUID, mapId);
        if (image != null) {
            try {
                var out = new ByteArrayOutputStream();
                ImageIO.write(image, "png", out);
                builder.putByteArray(TAG_MAP_IMAGE, out.toByteArray());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return builder.build();
    }
}
