package cn.allay.material;

public interface ItemMaterial extends Material {
    /**
     * True if this material represents a playable music disk.
     */
    boolean isRecord();

    /**
     * Checks if this Material is edible.
     */
    boolean isEdible();

    /**
     * Checks if this Material can be used as fuel in a Furnace
     *
     * @return the boolean
     */
    boolean isFuel();
}
