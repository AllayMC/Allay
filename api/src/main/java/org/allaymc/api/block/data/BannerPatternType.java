package org.allaymc.api.block.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code BannerPatternType} identifies a banner pattern by its short NBT identifier
 * and optional crafting item. The constants distinguish the patterns used by banner
 * layers, and the item lookup separates freely available patterns from patterns
 * that require a dedicated item.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum BannerPatternType {
    /**
     * Rectangular border pattern.
     */
    BORDER("bo"),
    /**
     * Brick wall pattern.
     */
    BRICKS("bri"),
    /**
     * Central circular pattern.
     */
    CIRCLE("mc"),
    /**
     * Creeper face pattern.
     */
    CREEPER("cre"),
    /**
     * Diagonal cross pattern.
     */
    CROSS("cr"),
    /**
     * Indented border pattern.
     */
    CURLY_BORDER("cbo"),
    /**
     * Left diagonal pattern.
     */
    DIAGONAL_LEFT("lud"),
    /**
     * Right diagonal pattern.
     */
    DIAGONAL_RIGHT("rd"),
    /**
     * Upper left diagonal pattern.
     */
    DIAGONAL_UP_LEFT("ld"),
    /**
     * Upper right diagonal pattern.
     */
    DIAGONAL_UP_RIGHT("rud"),
    /**
     * Flower charge pattern.
     */
    FLOWER("flo"),
    /**
     * Globe charge pattern.
     */
    GLOBE("glb"),
    /**
     * Downward color gradient.
     */
    GRADIENT("gra"),
    /**
     * Upward color gradient.
     */
    GRADIENT_UP("gru"),
    /**
     * Upper horizontal half pattern.
     */
    HALF_HORIZONTAL("hh"),
    /**
     * Lower horizontal half pattern.
     */
    HALF_HORIZONTAL_BOTTOM("hhb"),
    /**
     * Left vertical half pattern.
     */
    HALF_VERTICAL("vh"),
    /**
     * Right vertical half pattern.
     */
    HALF_VERTICAL_RIGHT("vhr"),
    /**
     * Mojang logo pattern.
     */
    MOJANG("moj"),
    /**
     * Piglin snout pattern.
     */
    PIGLIN("pig"),
    /**
     * Central rhombus pattern.
     */
    RHOMBUS("mr"),
    /**
     * Skull charge pattern.
     */
    SKULL("sku"),
    /**
     * Small vertical stripes.
     */
    SMALL_STRIPES("ss"),
    /**
     * Bottom left square pattern.
     */
    SQUARE_BOTTOM_LEFT("bl"),
    /**
     * Bottom right square pattern.
     */
    SQUARE_BOTTOM_RIGHT("br"),
    /**
     * Top left square pattern.
     */
    SQUARE_TOP_LEFT("tl"),
    /**
     * Top right square pattern.
     */
    SQUARE_TOP_RIGHT("tr"),
    /**
     * Horizontal and vertical cross.
     */
    STRAIGHT_CROSS("sc"),
    /**
     * Bottom horizontal stripe.
     */
    STRIPE_BOTTOM("bs"),
    /**
     * Central vertical stripe.
     */
    STRIPE_CENTER("cs"),
    /**
     * Descending left diagonal stripe.
     */
    STRIPE_DOWNLEFT("dls"),
    /**
     * Descending right diagonal stripe.
     */
    STRIPE_DOWNRIGHT("drs"),
    /**
     * Left vertical stripe.
     */
    STRIPE_LEFT("ls"),
    /**
     * Middle horizontal stripe.
     */
    STRIPE_MIDDLE("ms"),
    /**
     * Right vertical stripe.
     */
    STRIPE_RIGHT("rs"),
    /**
     * Top horizontal stripe.
     */
    STRIPE_TOP("ts"),
    /**
     * Bottom triangular pattern.
     */
    TRIANGLE_BOTTOM("bt"),
    /**
     * Top triangular pattern.
     */
    TRIANGLE_TOP("tt"),
    /**
     * Bottom triangular border.
     */
    TRIANGLES_BOTTOM("bts"),
    /**
     * Top triangular border.
     */
    TRIANGLES_TOP("tts"),
    /**
     * Flow charge pattern.
     */
    FLOW("flw"),
    /**
     * Guster charge pattern.
     */
    GUSTER("gus");

    /**
     * {@code BY_ID} maps the short NBT identifiers to constants after enum
     * initialization for case-sensitive layer decoding.
     */
    private static final Map<String, BannerPatternType> BY_ID = new HashMap<>();

    // Populate identifier lookup after every enum constant has its serialization
    // identifier.
    static {
        for (var type : values()) {
            BY_ID.put(type.id, type);
        }
    }

    /**
     * Short NBT pattern identifier.
     */
    private final String id;

    /**
     * Returns the pattern registered for the exact supplied short identifier.
     * Unknown identifiers, including {@code null}, return {@code null}, and this
     * lookup does not normalize identifier case.
     */
    public static BannerPatternType fromId(String id) {
        return BY_ID.get(id);
    }

    /**
     * Returns the dedicated crafting item required by this pattern when one exists.
     * Basic patterns return {@code null}, allowing callers to distinguish patterns
     * that need no special pattern item.
     */
    public ItemType<?> getRequiredPatternItem() {
        return switch (this) {
            case BRICKS -> ItemTypes.FIELD_MASONED_BANNER_PATTERN;
            case CREEPER -> ItemTypes.CREEPER_BANNER_PATTERN;
            case CURLY_BORDER -> ItemTypes.BORDURE_INDENTED_BANNER_PATTERN;
            case FLOWER -> ItemTypes.FLOWER_BANNER_PATTERN;
            case MOJANG -> ItemTypes.MOJANG_BANNER_PATTERN;
            case SKULL -> ItemTypes.SKULL_BANNER_PATTERN;
            case GLOBE -> ItemTypes.GLOBE_BANNER_PATTERN;
            case PIGLIN -> ItemTypes.PIGLIN_BANNER_PATTERN;
            case FLOW -> ItemTypes.FLOW_BANNER_PATTERN;
            case GUSTER -> ItemTypes.GUSTER_BANNER_PATTERN;
            default -> null;
        };
    }
}
