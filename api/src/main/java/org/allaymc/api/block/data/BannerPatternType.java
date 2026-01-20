package org.allaymc.api.block.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the different pattern types that can be applied to banners.
 * Each pattern type has a unique string identifier used for NBT serialization.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum BannerPatternType {
    BORDER("bo"),
    BRICKS("bri"),
    CIRCLE("mc"),
    CREEPER("cre"),
    CROSS("cr"),
    CURLY_BORDER("cbo"),
    DIAGONAL_LEFT("lud"),
    DIAGONAL_RIGHT("rd"),
    DIAGONAL_UP_LEFT("ld"),
    DIAGONAL_UP_RIGHT("rud"),
    FLOWER("flo"),
    GLOBE("glb"),
    GRADIENT("gra"),
    GRADIENT_UP("gru"),
    HALF_HORIZONTAL("hh"),
    HALF_HORIZONTAL_BOTTOM("hhb"),
    HALF_VERTICAL("vh"),
    HALF_VERTICAL_RIGHT("vhr"),
    ILLAGER("sku"),
    MOJANG("moj"),
    PIGLIN("pig"),
    RHOMBUS("mr"),
    SKULL("sku"),
    SMALL_STRIPES("ss"),
    SQUARE_BOTTOM_LEFT("bl"),
    SQUARE_BOTTOM_RIGHT("br"),
    SQUARE_TOP_LEFT("tl"),
    SQUARE_TOP_RIGHT("tr"),
    STRAIGHT_CROSS("sc"),
    STRIPE_BOTTOM("bs"),
    STRIPE_CENTER("cs"),
    STRIPE_DOWNLEFT("dls"),
    STRIPE_DOWNRIGHT("drs"),
    STRIPE_LEFT("ls"),
    STRIPE_MIDDLE("ms"),
    STRIPE_RIGHT("rs"),
    STRIPE_TOP("ts"),
    TRIANGLE_BOTTOM("bt"),
    TRIANGLE_TOP("tt"),
    TRIANGLES_BOTTOM("bts"),
    TRIANGLES_TOP("tts"),
    FLOW("flw");

    private static final Map<String, BannerPatternType> BY_ID = new HashMap<>();

    static {
        for (var type : values()) {
            BY_ID.put(type.id, type);
        }
    }

    private final String id;

    /**
     * Gets a BannerPatternType by its string identifier.
     *
     * @param id the pattern identifier
     * @return the BannerPatternType, or {@code null} if not found
     */
    public static BannerPatternType fromId(String id) {
        return BY_ID.get(id);
    }
}
