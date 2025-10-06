package org.allaymc.api.entity.data;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.math.MathUtils;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.HashMap;
import java.util.Map;

/**
 * PaintingType represents the type of painting.
 *
 * @author PMMP Team | daoge_cmd
 */
@AllArgsConstructor
@Getter
public enum PaintingType {
    KEBAB("Kebab", 1, 1),
    AZTEC("Aztec", 1, 1),
    ALBAN("Alban", 1, 1),
    AZTEC2("Aztec2", 1, 1),
    BOMB("Bomb", 1, 1),
    PLANT("Plant", 1, 1),
    WASTELAND("Wasteland", 1, 1),
    MEDITATIVE("meditative", 1, 1),
    WANDERER("Wanderer", 1, 2),
    GRAHAM("Graham", 1, 2),
    PRAIRIE_RIDE("prairie_ride", 1, 2),
    POOL("Pool", 2, 1),
    COURBET("Courbet", 2, 1),
    SUNSET("Sunset", 2, 1),
    SEA("Sea", 2, 1),
    CREEBET("Creebet", 2, 1),
    MATCH("Match", 2, 2),
    BUST("Bust", 2, 2),
    STAGE("Stage", 2, 2),
    VOID("Void", 2, 2),
    SKULL_AND_ROSES("SkullAndRoses", 2, 2),
    WITHER("Wither", 2, 2),
    BAROQUE("baroque", 2, 2),
    HUMBLE("humble", 2, 2),
    BOUQUET("bouquet", 3, 3),
    CAVEBIRD("cavebird", 3, 3),
    COTAN("cotan", 3, 3),
    ENDBOSS("endboss", 3, 3),
    FERN("fern", 3, 3),
    OWLEMONS("owlemons", 3, 3),
    SUNFLOWERS("sunflowers", 3, 3),
    TIDES("tides", 3, 3),
    BACKYARD("backyard", 3, 4),
    POND("pond", 3, 4),
    FIGHTERS("Fighters", 4, 2),
    CHANGING("changing", 4, 2),
    FINDING("finding", 4, 2),
    LOWMIST("lowmist", 4, 2),
    PASSAGE("passage", 4, 2),
    SKELETON("Skeleton", 4, 3),
    DONKEY_KONG("DonkeyKong", 4, 3),
    POINTER("Pointer", 4, 4),
    PIG_SCENE("Pigscene", 4, 4),
    BURNING_SKULL("BurningSkull", 4, 4),
    ORB("orb", 4, 4),
    UNPACKED("unpacked", 4, 4);

    private static final Map<String, PaintingType> BY_TITLE = new HashMap<>();

    static {
        for (var type : PaintingType.values()) {
            BY_TITLE.put(type.getTitle(), type);
        }
    }

    /**
     * The title of the painting.
     */
    private final String title;
    /**
     * The width and height of the painting.
     */
    private final int width, height;

    /**
     * Returns the {@code PaintingType} corresponding to the given title.
     *
     * @param title the title of the painting to look up
     * @return the {@code PaintingType} associated with the given title,
     * or {@code null} if no matching type is found
     */
    public static PaintingType fromTitle(String title) {
        return BY_TITLE.get(title);
    }

    /**
     * Calculates and returns the axis-aligned bounding box (AABB) based on the given block face direction.
     * The method modifies the AABB dimensions based on the painting's width, height, and the specified block face orientation.
     *
     * @param blockFace the block face direction to determine the orientation of the AABB; must be horizontal.
     * @return an {@code AABBdc} object representing the axis-aligned bounding box for the specified face and dimensions.
     * @throws IllegalStateException if the provided {@code blockFace} is not horizontal.
     */
    public AABBdc getAABB(BlockFace blockFace) {
        Preconditions.checkState(blockFace.isHorizontal());

        var horizontalStart = (int) (Math.ceil(this.width / 2d) - 1);
        var verticalStart = (int) (Math.ceil(this.height / 2d) - 1);

        var aabb = new AABBd(0, 0, 0, 1, 1, 1);
        MathUtils.grow(aabb, blockFace, -15d / 16d);
        MathUtils.grow(aabb, blockFace.rotateY(), horizontalStart);
        MathUtils.grow(aabb, blockFace.rotateYCCW(), -horizontalStart + this.width - 1);
        MathUtils.grow(aabb, BlockFace.DOWN, verticalStart);
        MathUtils.grow(aabb, BlockFace.UP, -verticalStart + this.height - 1);

        return aabb;
    }
}
