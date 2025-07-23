package org.allaymc.server.item.component;

import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.component.ItemFilledMapBaseComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.world.biome.BiomeData;
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

/**
 * @author daoge_cmd
 */
@Getter
public class ItemFilledMapBaseComponentImpl extends ItemBaseComponentImpl implements ItemFilledMapBaseComponent {

    protected static final String TAG_MAP_UUID = "map_uuid";
    // NOTICE: this tag doesn't exist in vanilla
    protected static final String TAG_MAP_IMAGE = "map_image";
    // Image height and width
    protected static final int IMAGE_HW = 128;
    protected static final int SEA_LEVEL = 62;
    protected static final Color VOID_COLOR = new Color(0, 0, 0, 0);

    protected BufferedImage image;
    protected long mapId;

    public ItemFilledMapBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.mapId = UUID.randomUUID().getMostSignificantBits();
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
    public void renderMap(Dimension dimension, int startX, int startZ, int zoom) {
        int[] pixels = new int[IMAGE_HW * IMAGE_HW];
        for (int z = 0; z < IMAGE_HW * zoom; z += zoom) {
            for (int x = 0; x < IMAGE_HW * zoom; x += zoom) {
                pixels[(z * IMAGE_HW + x) / zoom] = computeMapColor(dimension, startX + x, startZ + z).getRGB();
            }
        }
        var renderedImage = new BufferedImage(IMAGE_HW, IMAGE_HW, BufferedImage.TYPE_INT_ARGB);
        renderedImage.setRGB(0, 0, IMAGE_HW, IMAGE_HW, pixels, 0, IMAGE_HW);
        this.image = renderedImage;
    }

    @Override
    public void sendToPlayer(EntityPlayer player) {
        if (image == null) {
            throw new IllegalStateException("Image is not set for the filled map.");
        }

        var pk = new ClientboundMapItemDataPacket();
        pk.setUniqueMapId(mapId);
        // Required since 1.19.20
        pk.setOrigin(Vector3i.ZERO);
        // Required as of 1.19.50
        pk.getTrackedEntityIds().add(mapId);
        pk.setHeight(IMAGE_HW);
        pk.setWidth(IMAGE_HW);
        var colors = new int[IMAGE_HW * IMAGE_HW];
        int index = 0;
        for (int y = 0; y < IMAGE_HW; y++) {
            for (int x = 0; x < IMAGE_HW; x++) {
                colors[index++] = toABGR(image.getRGB(x, y));
            }
        }
        pk.setColors(colors);
        player.sendPacket(pk);
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
        var extraTag = super.saveExtraTag();
        var builder = extraTag != null ? extraTag.toBuilder() : NbtMap.builder();
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

    protected static Color computeMapColor(Dimension dimension, int x, int z) {
        // TODO: TintMethod
        var color = VOID_COLOR;
        var block = getMapColoredBlock(dimension, x, z);
        if (block == null) {
            return color;
        }

        color = block.getBlockStateData().mapColor();
        var upperLayer0 = block.offsetPos(BlockFace.UP, 0);
        var upperLayer1 = block.offsetPos(BlockFace.UP, 1);
        if (upperLayer0.getBlockType().hasBlockTag(BlockTags.WATER) ||
            upperLayer1.getBlockType().hasBlockTag(BlockTags.WATER)) {
            // The block is underwater
            var finalRed = color.getRed();
            var finalGreen = color.getGreen();
            var finalBlue = color.getBlue();
            var waterColor = BiomeData.getBiomeData(dimension.getBiome(block.getPos())).mapWaterColor();
            if (block.getPos().y() < SEA_LEVEL) {
                // Under sea level, the farther away from sea level, the closer the color is to the ocean color
                var depth = SEA_LEVEL - block.getPos().y();
                if (depth > 96) {
                    return waterColor;
                }

                finalBlue = waterColor.getBlue();
                var radio = depth / 96f;
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

        var blockY = block.getPos().y();
        var neighborBlockY = neighborBlock.getPos().y();
        if (blockY < neighborBlockY) {
            return darker(color, 0.875f - Math.min(5, neighborBlockY - blockY) * 0.05f);
        }

        if (blockY > neighborBlockY) {
            return brighter(color, 0.875f - Math.min(5, blockY - neighborBlockY) * 0.05f);
        }

        return color;
    }

    protected static BlockStateWithPos getMapColoredBlock(Dimension dimension, int x, int z) {
        var chunk = dimension.getChunkService().getChunkByDimensionPos(x, z);
        if (chunk == null) {
            return null;
        }

        var chunkX = x & 0xF;
        var chunkZ = z & 0xF;
        int height = chunk.getHeight(chunkX, chunkZ);
        while (height >= dimension.getDimensionInfo().minHeight()) {
            var block = chunk.getBlockState(chunkX, height, chunkZ);
            var color = block.getBlockStateData().mapColor();
            if (color.getAlpha() == 0) {
                height--;
            } else {
                return new BlockStateWithPos(block, new Position3i(x, height, z, dimension), 0);
            }
        }

        return null;
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
}
