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
 * {@code PaintingType} identifies a painting motif by its serialized title and
 * dimensions in blocks. The constants supply titles for entity NBT and client
 * presentation; {@code getAABB} places each motif's width and height around
 * the entity's anchor according to its horizontal facing.
 *
 * @author PMMP Team | daoge_cmd
 */
@AllArgsConstructor
@Getter
public enum PaintingType {
    /**
     * Painting motif serialized as {@code "Kebab"}, spanning 1 by 1 blocks.
     */
    KEBAB("Kebab", 1, 1),
    /**
     * Painting motif serialized as {@code "Aztec"}, spanning 1 by 1 blocks.
     */
    AZTEC("Aztec", 1, 1),
    /**
     * Painting motif serialized as {@code "Alban"}, spanning 1 by 1 blocks.
     */
    ALBAN("Alban", 1, 1),
    /**
     * Painting motif serialized as {@code "Aztec2"}, spanning 1 by 1 blocks.
     */
    AZTEC2("Aztec2", 1, 1),
    /**
     * Painting motif serialized as {@code "Bomb"}, spanning 1 by 1 blocks.
     */
    BOMB("Bomb", 1, 1),
    /**
     * Painting motif serialized as {@code "Plant"}, spanning 1 by 1 blocks.
     */
    PLANT("Plant", 1, 1),
    /**
     * Painting motif serialized as {@code "Wasteland"}, spanning 1 by 1
     * blocks.
     */
    WASTELAND("Wasteland", 1, 1),
    /**
     * Painting motif serialized as {@code "meditative"}, spanning 1 by 1
     * blocks.
     */
    MEDITATIVE("meditative", 1, 1),
    /**
     * Painting motif serialized as {@code "Wanderer"}, spanning 1 by 2
     * blocks.
     */
    WANDERER("Wanderer", 1, 2),
    /**
     * Painting motif serialized as {@code "Graham"}, spanning 1 by 2 blocks.
     */
    GRAHAM("Graham", 1, 2),
    /**
     * Painting motif serialized as {@code "prairie_ride"}, spanning 1 by 2
     * blocks.
     */
    PRAIRIE_RIDE("prairie_ride", 1, 2),
    /**
     * Painting motif serialized as {@code "Pool"}, spanning 2 by 1 blocks.
     */
    POOL("Pool", 2, 1),
    /**
     * Painting motif serialized as {@code "Courbet"}, spanning 2 by 1 blocks.
     */
    COURBET("Courbet", 2, 1),
    /**
     * Painting motif serialized as {@code "Sunset"}, spanning 2 by 1 blocks.
     */
    SUNSET("Sunset", 2, 1),
    /**
     * Painting motif serialized as {@code "Sea"}, spanning 2 by 1 blocks.
     */
    SEA("Sea", 2, 1),
    /**
     * Painting motif serialized as {@code "Creebet"}, spanning 2 by 1 blocks.
     */
    CREEBET("Creebet", 2, 1),
    /**
     * Painting motif serialized as {@code "Match"}, spanning 2 by 2 blocks.
     */
    MATCH("Match", 2, 2),
    /**
     * Painting motif serialized as {@code "Bust"}, spanning 2 by 2 blocks.
     */
    BUST("Bust", 2, 2),
    /**
     * Painting motif serialized as {@code "Stage"}, spanning 2 by 2 blocks.
     */
    STAGE("Stage", 2, 2),
    /**
     * Painting motif serialized as {@code "Void"}, spanning 2 by 2 blocks.
     */
    VOID("Void", 2, 2),
    /**
     * Painting motif serialized as {@code "SkullAndRoses"}, spanning 2 by 2
     * blocks.
     */
    SKULL_AND_ROSES("SkullAndRoses", 2, 2),
    /**
     * Painting motif serialized as {@code "Wither"}, spanning 2 by 2 blocks.
     */
    WITHER("Wither", 2, 2),
    /**
     * Painting motif serialized as {@code "baroque"}, spanning 2 by 2 blocks.
     */
    BAROQUE("baroque", 2, 2),
    /**
     * Painting motif serialized as {@code "humble"}, spanning 2 by 2 blocks.
     */
    HUMBLE("humble", 2, 2),
    /**
     * Painting motif serialized as {@code "bouquet"}, spanning 3 by 3 blocks.
     */
    BOUQUET("bouquet", 3, 3),
    /**
     * Painting motif serialized as {@code "cavebird"}, spanning 3 by 3
     * blocks.
     */
    CAVEBIRD("cavebird", 3, 3),
    /**
     * Painting motif serialized as {@code "cotan"}, spanning 3 by 3 blocks.
     */
    COTAN("cotan", 3, 3),
    /**
     * Painting motif serialized as {@code "endboss"}, spanning 3 by 3 blocks.
     */
    ENDBOSS("endboss", 3, 3),
    /**
     * Painting motif serialized as {@code "fern"}, spanning 3 by 3 blocks.
     */
    FERN("fern", 3, 3),
    /**
     * Painting motif serialized as {@code "owlemons"}, spanning 3 by 3
     * blocks.
     */
    OWLEMONS("owlemons", 3, 3),
    /**
     * Painting motif serialized as {@code "sunflowers"}, spanning 3 by 3
     * blocks.
     */
    SUNFLOWERS("sunflowers", 3, 3),
    /**
     * Painting motif serialized as {@code "tides"}, spanning 3 by 3 blocks.
     */
    TIDES("tides", 3, 3),
    /**
     * Painting motif serialized as {@code "backyard"}, spanning 3 by 4
     * blocks.
     */
    BACKYARD("backyard", 3, 4),
    /**
     * Painting motif serialized as {@code "pond"}, spanning 3 by 4 blocks.
     */
    POND("pond", 3, 4),
    /**
     * Painting motif serialized as {@code "Fighters"}, spanning 4 by 2
     * blocks.
     */
    FIGHTERS("Fighters", 4, 2),
    /**
     * Painting motif serialized as {@code "changing"}, spanning 4 by 2
     * blocks.
     */
    CHANGING("changing", 4, 2),
    /**
     * Painting motif serialized as {@code "finding"}, spanning 4 by 2 blocks.
     */
    FINDING("finding", 4, 2),
    /**
     * Painting motif serialized as {@code "lowmist"}, spanning 4 by 2 blocks.
     */
    LOWMIST("lowmist", 4, 2),
    /**
     * Painting motif serialized as {@code "passage"}, spanning 4 by 2 blocks.
     */
    PASSAGE("passage", 4, 2),
    /**
     * Painting motif serialized as {@code "Skeleton"}, spanning 4 by 3
     * blocks.
     */
    SKELETON("Skeleton", 4, 3),
    /**
     * Painting motif serialized as {@code "DonkeyKong"}, spanning 4 by 3
     * blocks.
     */
    DONKEY_KONG("DonkeyKong", 4, 3),
    /**
     * Painting motif serialized as {@code "Pointer"}, spanning 4 by 4 blocks.
     */
    POINTER("Pointer", 4, 4),
    /**
     * Painting motif serialized as {@code "Pigscene"}, spanning 4 by 4
     * blocks.
     */
    PIG_SCENE("Pigscene", 4, 4),
    /**
     * Painting motif serialized as {@code "BurningSkull"}, spanning 4 by 4
     * blocks.
     */
    BURNING_SKULL("BurningSkull", 4, 4),
    /**
     * Painting motif serialized as {@code "orb"}, spanning 4 by 4 blocks.
     */
    ORB("orb", 4, 4),
    /**
     * Painting motif serialized as {@code "unpacked"}, spanning 4 by 4
     * blocks.
     */
    UNPACKED("unpacked", 4, 4);

    /**
     * {@code BY_TITLE} indexes every painting constant by its exact serialized
     * title for NBT loading. Title lookup preserves case rather than
     * normalizing legacy motif names.
     */
    private static final Map<String, PaintingType> BY_TITLE = new HashMap<>();

    // Index the exact stored titles once so entity loading can resolve a motif
    // without changing the spelling used by existing painting NBT.
    static {
        for (var type : PaintingType.values()) {
            BY_TITLE.put(type.getTitle(), type);
        }
    }

    /**
     * Exact painting title used by NBT and client presentation.
     */
    private final String title;
    /**
     * Painting width and height measured in whole blocks.
     */
    private final int width, height;

    /**
     * Returns the painting constant with the exact supplied serialized title.
     * Lookup is case-sensitive and returns {@code null} for an unknown title,
     * including {@code null}, rather than selecting a default motif.
     */
    public static PaintingType fromTitle(String title) {
        return BY_TITLE.get(title);
    }

    /**
     * Returns a new bounding box for this motif facing a horizontal block
     * face. Width and height extend around the painting anchor, and the box
     * remains one sixteenth of a block thick along its facing axis.
     *
     * @throws IllegalStateException if {@code blockFace} is not horizontal
     */
    public AABBdc getAABB(BlockFace blockFace) {
        Preconditions.checkState(blockFace.isHorizontal());

        // Even motif sizes extend one block farther on the positive local side;
        // this anchor split matches the width and height used for painting placement.
        var horizontalStart = (int) (Math.ceil(this.width / 2d) - 1);
        var verticalStart = (int) (Math.ceil(this.height / 2d) - 1);

        var aabb = new AABBd(0, 0, 0, 1, 1, 1);
        // Reduce the anchor cube to the painting's one-sixteenth-block thickness.
        MathUtils.grow(aabb, blockFace, -15d / 16d);
        MathUtils.grow(aabb, blockFace.rotateY(), horizontalStart);
        MathUtils.grow(aabb, blockFace.rotateYCCW(), -horizontalStart + this.width - 1);
        MathUtils.grow(aabb, BlockFace.DOWN, verticalStart);
        MathUtils.grow(aabb, BlockFace.UP, -verticalStart + this.height - 1);

        return aabb;
    }
}
