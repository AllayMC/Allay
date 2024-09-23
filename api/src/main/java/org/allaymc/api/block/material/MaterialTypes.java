package org.allaymc.api.block.material;

import java.util.HashMap;
import java.util.Map;

/**
 * Automatically generated by {@code org.allaymc.codegen.MaterialTypeGen}
 */
public interface MaterialTypes {
    Map<String, MaterialType> NAME_TO_MATERIAL_TYPE = new HashMap<>();

    MaterialType WATER = create("Water");

    MaterialType SAND = create("Sand");

    MaterialType SCULK = create("Sculk");

    MaterialType SNOW = create("Snow");

    MaterialType DIRT = create("Dirt");

    MaterialType SOLID_PLANT = create("SolidPlant");

    MaterialType CARPET = create("Carpet");

    MaterialType METAL = create("Metal");

    MaterialType DENY = create("Deny");

    MaterialType AMETHYST = create("Amethyst");

    MaterialType STONE = create("Stone");

    MaterialType BUBBLE = create("Bubble");

    MaterialType EXPLOSIVE = create("Explosive");

    MaterialType CACTUS = create("Cactus");

    MaterialType CLOTH = create("Cloth");

    MaterialType PISTON = create("Piston");

    MaterialType TOP_SNOW = create("TopSnow");

    MaterialType CLAY = create("Clay");

    MaterialType LAVA = create("Lava");

    MaterialType ROOT = create("Root");

    MaterialType AIR = create("Air");

    MaterialType SCULK_VEIN = create("SculkVein");

    MaterialType DECORATION = create("Decoration");

    MaterialType CLIENT_REQUEST_PLACEHOLDER = create("ClientRequestPlaceholder");

    MaterialType WEB = create("Web");

    MaterialType SOFT_EGG = create("SoftEgg");

    MaterialType DRIPSTONE = create("Dripstone");

    MaterialType PACKED_ICE = create("PackedIce");

    MaterialType CAKE = create("Cake");

    MaterialType GLASS = create("Glass");

    MaterialType BARRIER = create("Barrier");

    MaterialType NETHERWART = create("Netherwart");

    MaterialType SHULKER = create("Shulker");

    MaterialType LEAVES = create("Leaves");

    MaterialType PLANT = create("Plant");

    MaterialType STONE_DECORATION = create("StoneDecoration");

    MaterialType PORTAL = create("Portal");

    MaterialType STRUCTURE_VOID = create("StructureVoid");

    MaterialType VEGETABLE = create("Vegetable");

    MaterialType BED = create("Bed");

    MaterialType REDSTONE_WIRE = create("RedstoneWire");

    MaterialType EGG = create("Egg");

    MaterialType WOOD = create("Wood");

    MaterialType DECORATION_SOLID = create("DecorationSolid");

    MaterialType ICE = create("Ice");

    MaterialType SLIME = create("Slime");

    MaterialType ALLOW = create("Allow");

    MaterialType CORAL = create("Coral");

    MaterialType REINFORCED_STONE = create("ReinforcedStone");

    MaterialType FIRE = create("Fire");

    MaterialType SPONGE = create("Sponge");

    MaterialType BUILDABLE_GLASS = create("BuildableGlass");

    MaterialType POWDER_SNOW = create("PowderSnow");

    static MaterialType create(String name) {
        var tag = new MaterialType(name);
        NAME_TO_MATERIAL_TYPE.put(name, tag);
        return tag;
    }

    static MaterialType getMaterialTypeByName(String name) {
        return NAME_TO_MATERIAL_TYPE.get(name);
    }
}