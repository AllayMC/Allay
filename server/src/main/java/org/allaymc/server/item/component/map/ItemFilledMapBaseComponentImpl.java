package org.allaymc.server.item.component.map;

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
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * {@code ItemFilledMapBaseComponentImpl} renders terrain into filled-map
 * images and stores those images with map identifiers. A pixel combines the
 * visible block, biome tint, water depth, and neighboring column height. The
 * renderer looks through transparent water to find terrain, then applies water
 * color and relief shading; deeply submerged columns use water color directly
 * before relief shading can run.
 *
 * <p>
 * The mutable {@code image} is shared with its getter and map delivery.
 * Asynchronous rendering publishes its completed image on the component,
 * including a transparent replacement after a logged rendering failure. Extra
 * tags preserve the map identifier and encode the image as PNG data in an
 * Allay-specific tag.
 *
 * @author daoge_cmd
 */
@Getter
@Slf4j
public class ItemFilledMapBaseComponentImpl extends ItemBaseComponentImpl implements ItemFilledMapBaseComponent {

    // TAG_MAP_UUID preserves the map identifier, while TAG_MAP_IMAGE stores an
    // Allay-specific PNG payload beside the inherited stack extra data.
    /**
     * Persisted map identifier tag.
     */
    protected static final String TAG_MAP_UUID = "map_uuid";
    /**
     * Allay PNG image tag.
     */
    protected static final String TAG_MAP_IMAGE = "map_image";

    // IMAGE_HW fixes the client image dimensions; SEA_LEVEL anchors the depth
    // calculation that moves underwater terrain colors toward biome water colors.
    /**
     * Map image side pixels.
     */
    protected static final int IMAGE_HW = 128;
    /**
     * Water-depth reference block height.
     */
    protected static final int SEA_LEVEL = 62;

    // These colors supply transparent-column output and fixed plant-tint overrides
    // that getPlantColor selects before consulting the climate color textures.
    /**
     * Transparent missing-column map color.
     */
    protected static final Color VOID = new Color(0, 0, 0, 0);
    /**
     * Fixed birch foliage tint.
     */
    protected static final Color BIRCH_FOLIAGE = new Color(0x80a755);
    /**
     * Fixed evergreen foliage tint.
     */
    protected static final Color EVERGREEN_FOLIAGE = new Color(0x619961);
    /**
     * Swamp and forest dry-foliage tint.
     */
    protected static final Color DRY_FOLIAGE_SPECIAL_A = new Color(0x7b5334);
    /**
     * Pale-garden dry-foliage tint.
     */
    protected static final Color DRY_FOLIAGE_SPECIAL_B = new Color(0xa0a69c);
    /**
     * Ordinary swamp foliage tint.
     */
    protected static final Color SWAMP_BIOME_FOLIAGE = new Color(0x6a7039);
    /**
     * Selected swamp grass tint.
     */
    protected static final Color SWAMP_BIOME_GRASS_A = new Color(0x6a7039);
    /**
     * Unselected alternate swamp-grass tint.
     */
    protected static final Color BIOME_SWAMP_GRASS_B = new Color(0x4c763c);
    /**
     * Mangrove swamp foliage tint.
     */
    protected static final Color MANGROVE_SWAMP_BIOME_FOLIAGE = new Color(0x8db127);
    /**
     * Roofed-forest grass tint.
     */
    protected static final Color ROOFED_FOREST_BIOME_GRASS = new Color(0x507a32);
    /**
     * Mesa biome grass tint.
     */
    protected static final Color MESA_BIOME_GRASS = new Color(0x90814d);
    /**
     * Mesa biome foliage tint.
     */
    protected static final Color MESA_BIOME_FOLIAGE = new Color(0x9e814d);
    /**
     * Cherry-grove plant tint.
     */
    protected static final Color CHERRY_GROVE_BIOME_PLANT = new Color(0xb6db61);
    /**
     * Pale-garden ordinary plant tint.
     */
    protected static final Color PALE_GARDEN_BIOME_PLANT = new Color(0x878d76);

    // FOLIAGE_COLORMAP, DRY_FOLIAGE_COLORMAP, and GRASS_COLORMAP hold shared climate
    // textures loaded once at class initialization. getPlantColor samples one
    // texture only after species and biome overrides have been considered.
    /**
     * Ordinary foliage climate texture.
     */
    protected static final BufferedImage FOLIAGE_COLORMAP;
    /**
     * Dry-foliage climate texture.
     */
    protected static final BufferedImage DRY_FOLIAGE_COLORMAP;
    /**
     * Grass climate texture.
     */
    protected static final BufferedImage GRASS_COLORMAP;

    // Load the three bundled climate textures before plant sampling uses them. Resource
    // lookup and I/O failures abort initialization, while an undecodable image can leave
    // a null texture that fails during later tint sampling.
    static {
        try {
            FOLIAGE_COLORMAP = ImageIO.read(Utils.getResource("colormap/foliage.png"));
            DRY_FOLIAGE_COLORMAP = ImageIO.read(Utils.getResource("colormap/dry_foliage.png"));
            GRASS_COLORMAP = ImageIO.read(Utils.getResource("colormap/grass.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@code image} holds the mutable stored map pixels, or {@code null}
     * before image creation. The generated getter shares this object with
     * sending and serialization, while rendering completion can replace it.
     */
    protected BufferedImage image;
    /**
     * {@code mapId} holds the signed identifier sent with map pixels. The
     * constructor initializes it from random UUID bits, and a stored map tag
     * can replace that value.
     */
    protected long mapId;

    /**
     * Creates filled-map stack behavior with a randomly initialized signed map
     * identifier. The image starts absent; component initialization can later
     * restore both identifier and image from the supplied extra tags.
     */
    public ItemFilledMapBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.mapId = UUID.randomUUID().getMostSignificantBits();
    }

    /**
     * Returns the terrain color for a column after water blending and relief
     * shading. The selected block supplies its base or plant tint, while water
     * above that block replaces the blue channel and blends the other channels
     * according to depth. Depth greater than fifteen blocks returns the biome
     * water color immediately.
     *
     * <p>
     * For other columns, the preceding {@code z} column supplies the height
     * comparison used to brighten or darken the result. Missing terrain
     * returns transparent color, and a missing neighboring column leaves the
     * computed current color unshaded.
     */
    protected static Color getMapColor(Dimension dimension, int x, int z) {
        var color = VOID;
        var block = getMapColoredBlock(dimension, x, z);
        // A column without map-visible terrain contributes a transparent pixel rather
        // than borrowing a neighboring color for ground that this column does not contain.
        if (block == null) {
            return color;
        }

        color = computeMapColor(block);
        var upperLayer0 = block.offsetPos(BlockFace.UP, 0);
        var upperLayer1 = block.offsetPos(BlockFace.UP, 1);
        if (upperLayer0.getBlockType().hasBlockTag(BlockTags.WATER) ||
            upperLayer1.getBlockType().hasBlockTag(BlockTags.WATER)) {
            var finalRed = color.getRed();
            var finalGreen = color.getGreen();
            var finalBlue = color.getBlue();
            // TODO: Blend neighboring biome water colors instead of using only this column's biome.
            var waterColor = dimension.getBiome(block.getPosition()).getBiomeData().mapWaterColor();
            if (block.getPosition().y() < SEA_LEVEL) {
                // Below SEA_LEVEL, fifteen blocks is the full water-color blend depth;
                // deeper columns return water color before neighboring-height shading.
                var depth = SEA_LEVEL - block.getPosition().y();
                if (depth > 15) {
                    return waterColor;
                }

                finalBlue = waterColor.getBlue();
                // The half-strength floor keeps even shallow submerged terrain visibly water-tinted.
                var radio = depth / 15f;
                if (radio < 0.5f) {
                    radio = 0.5f;
                }
                finalRed += (int) ((waterColor.getRed() - finalRed) * radio);
                finalGreen += (int) ((waterColor.getGreen() - finalGreen) * radio);
            } else {
                // At or above SEA_LEVEL, use a half-strength blend for shallow water bodies.
                finalBlue = waterColor.getBlue();
                finalRed += (int) ((waterColor.getRed() - finalRed) * 0.5f);
                finalGreen += (int) ((waterColor.getGreen() - finalGreen) * 0.5f);
            }
            color = new Color(finalRed, finalGreen, finalBlue);
        }

        // Compare the preceding z column after water blending so land and shallow
        // underwater relief share the same directional brightness convention.
        var neighborBlock = getMapColoredBlock(dimension, x, z - 1);
        // A missing reference column provides no relief comparison. Preserve the
        // current terrain color so missing neighboring terrain does not erase valid pixels.
        if (neighborBlock == null) {
            return color;
        }

        var blockY = block.getPosition().y();
        var neighborBlockY = neighborBlock.getPosition().y();
        if (blockY < neighborBlockY) {
            // A 0.85 channel multiplier darkens the lower column by fifteen percent.
            return darker(color, 0.85);
        }

        if (blockY > neighborBlockY) {
            // Dividing by 0.85 brightens the higher column for the same relief direction.
            return brighter(color, 0.85);
        }

        return color;
    }

    /**
     * Finds the highest map-visible foreground block in a terrain column. The
     * call waits for chunk loading, skips water and fully transparent untinted
     * blocks, and returns {@code null} when no candidate exists at or above
     * minimum height.
     */
    protected static Block getMapColoredBlock(Dimension dimension, int x, int z) {
        var chunk = dimension.getChunkManager().loadChunk(x >> 4, z >> 4).join();
        Preconditions.checkArgument(chunk != null);

        // Sixteen-block chunk coordinates become local indices without losing negative-coordinate alignment.
        var chunkX = x & 0xF;
        var chunkZ = z & 0xF;
        int height = chunk.getHeight(chunkX, chunkZ);
        while (height >= dimension.getDimensionType().getMinHeight()) {
            var block = chunk.getBlockState(chunkX, height, chunkZ);
            var color = block.getBlockStateData().mapColor();
            var tintMethod = block.getBlockStateData().tintMethod();
            // Look through water and fully transparent untinted blocks so the later
            // water blend can incorporate the first visible terrain beneath them.
            if (tintMethod == TintMethod.WATER || (color.getAlpha() == 0 && tintMethod == TintMethod.NONE)) {
                height--;
            } else {
                return new Block(block, new Position3i(x, height, z, dimension), 0);
            }
        }

        return null;
    }

    /**
     * Returns the block's color using its configured tint rule. Untinted
     * blocks and stems use their stored map color, supported plants use
     * {@code getPlantColor}, and an unexpected tint causes
     * {@link IllegalStateException}.
     */
    protected static Color computeMapColor(Block block) {
        var blockStateData = block.getBlockStateData();
        var tintMethod = blockStateData.tintMethod();
        return switch (tintMethod) {
            // TODO: Apply stem-specific tinting rather than reusing the stored base color.
            case NONE, STEM -> blockStateData.mapColor();
            case RED_STONE_WIRE -> Color.RED;
            case DEFAULT_FOLIAGE, BIRCH_FOLIAGE, EVERGREEN_FOLIAGE, DRY_FOLIAGE, GRASS -> getPlantColor(block);
            default -> throw new IllegalStateException("Unexpected tint method: " + tintMethod);
        };
    }

    /**
     * Returns a plant tint using species overrides, biome overrides, or a
     * climate texture. Birch and evergreen rules take precedence over biome
     * selection, while swamp, forest, mesa, cherry-grove, and pale-garden
     * cases can return fixed colors without sampling a texture.
     *
     * <p>
     * For remaining plants, clamped temperature and temperature-scaled
     * downfall select texture coordinates. The tint rule selects the grass,
     * dry-foliage, or ordinary-foliage texture; multiplying downfall by
     * temperature couples the two climate values before their inverted
     * coordinates are sampled.
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
                // TODO: Use biome noise to select the alternate swamp grass color instead of always choosing the first.
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

        // Clamp climate inputs to the texture domain before multiplying downfall by
        // temperature; this keeps the inverted lookup coordinates inside the image.
        var adjTemperature = Math.clamp(biomeType.getBiomeData().temperature(), 0, 1);
        var adjDownfall = Math.clamp(biomeType.getBiomeData().downfall(), 0, 1) * adjTemperature;
        var colormap = switch (tintMethod) {
            case TintMethod.DRY_FOLIAGE -> DRY_FOLIAGE_COLORMAP;
            case TintMethod.GRASS -> GRASS_COLORMAP;
            default -> FOLIAGE_COLORMAP;
        };
        // Inverted unit climate values select indices in the 256-pixel texture axes.
        var x = (int) ((1 - adjTemperature) * 255);
        var y = (int) ((1 - adjDownfall) * 255);
        return new Color(colormap.getRGB(x, y));
    }

    /**
     * Brightens color channels using the supplied divisor while preserving
     * alpha. Relief shading passes {@code 0.85}; black and very low nonzero
     * channels receive a floor so division can produce a visible increase.
     */
    protected static Color brighter(Color source, double factor) {
        int r = source.getRed();
        int g = source.getGreen();
        int b = source.getBlue();
        int alpha = source.getAlpha();

        // Raise black and tiny nonzero channels before division so brightening can
        // escape black instead of repeatedly returning an unchanged dark color.
        int i = (int) (1.0 / (1.0 - factor));
        if (r == 0 && g == 0 && b == 0) {
            return new Color(i, i, i, alpha);
        }
        if (r > 0 && r < i) r = i;
        if (g > 0 && g < i) g = i;
        if (b > 0 && b < i) b = i;

        // Cap brightened channels at the eight-bit color component maximum.
        return new Color(
                Math.min((int) (r / factor), 255),
                Math.min((int) (g / factor), 255),
                Math.min((int) (b / factor), 255),
                alpha
        );
    }

    /**
     * Darkens color channels using the supplied multiplier while preserving
     * alpha. Relief shading passes {@code 0.85}, and each multiplied channel
     * is truncated to an integer before its lower bound is applied.
     */
    protected static Color darker(Color source, double factor) {
        // Integer channel scaling preserves alpha and bounds darkened channels below.
        return new Color(
                Math.max((int) (source.getRed() * factor), 0),
                Math.max((int) (source.getGreen() * factor), 0),
                Math.max((int) (source.getBlue() * factor), 0),
                source.getAlpha()
        );
    }

    /**
     * Scales a non-{@code null} source into a new 128-by-128 image and stores
     * the result. This path also scales exact-size sources, but an exact-size
     * source is assigned to {@code this.image} before replacement allocation.
     * If that source uses {@code BufferedImage.TYPE_CUSTOM}, allocation fails
     * while the preliminary source assignment remains stored.
     */
    @Override
    public void setImage(BufferedImage image) {
        if (image.getHeight() == IMAGE_HW && image.getWidth() == IMAGE_HW) {
            this.image = image;
        }

        // Normalize every source to the client's fixed map dimensions, including
        // exact-size inputs; the completed resized image replaces the stored pixels.
        var resizedImage = new BufferedImage(IMAGE_HW, IMAGE_HW, image.getType());

        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, IMAGE_HW, IMAGE_HW, null);
        g.dispose();
        this.image = resizedImage;
    }

    /**
     * Renders and stores sampled terrain on the server's virtual-thread
     * executor. {@code zoom} is used directly for sampling bounds and spacing
     * without validation; {@code 0} skips sampling and leaves the pixel array
     * transparent. Failures within asynchronous rendering are logged and
     * converted to a transparent image before the returned future publishes
     * that image on this component.
     */
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
                // A terrain lookup failure discards partial samples and supplies transparent
                // pixels, allowing the caller's completion path to send a valid image.
                .exceptionally(t -> {
                    log.error("Error while rendering map!", t);
                    return new BufferedImage(IMAGE_HW, IMAGE_HW, BufferedImage.TYPE_INT_ARGB);
                })
                .thenApply(image -> this.image = image);
    }

    /**
     * Sends the stored image with this map's identifier to one player. An
     * absent {@code image} causes {@link IllegalStateException}; callers can
     * send again after changing shared image pixels or replacing the image.
     */
    @Override
    public void sendToPlayer(Player player) {
        if (this.image == null) {
            throw new IllegalStateException("Image is not set for the filled map.");
        }

        player.sendMapData(this.mapId, this.image);
    }

    /**
     * Loads inherited stack data, then applies any stored map identifier and
     * image bytes. Missing map tags retain current values; image decode I/O
     * failures propagate as runtime failures after earlier data may have
     * changed.
     */
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

    /**
     * Serializes the map identifier and any current image alongside inherited
     * stack extra data. Image pixels are encoded as PNG bytes in
     * {@code TAG_MAP_IMAGE}; image-writing I/O failures abort this save with a
     * runtime failure.
     */
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
