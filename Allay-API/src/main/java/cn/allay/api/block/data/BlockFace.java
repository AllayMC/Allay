package cn.allay.api.block.data;

import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/8/11
 *
 * @author JukeboxMC | daoge_cmd
 */
public enum BlockFace {

    DOWN( -1, new Vector3i( 0, -1, 0 ) ),
    UP( -1, new Vector3i( 0, 1, 0 ) ),
    NORTH( 2, new Vector3i( 0, 0, -1 ) ),
    SOUTH( 0, new Vector3i( 0, 0, 1 ) ),
    WEST( 1, new Vector3i( -1, 0, 0 ) ),
    EAST( 3, new Vector3i( 1, 0, 0 ) );

    private final int horizontalIndex;
    private final Vector3ic offset;

    BlockFace( int horizontalIndex, Vector3ic offset ) {
        this.horizontalIndex = horizontalIndex;
        this.offset = offset;
    }

    public Vector3ic getOffset() {
        return offset;
    }

    public Vector3ic offsetPos(int x, int y, int z) {
        return offset.add(x, y, z, new Vector3i());
    }

    public Vector3ic offsetPos(Vector3ic pos) {
        return offset.add(pos, new Vector3i());
    }

    public int getHorizontalIndex() {
        return horizontalIndex;
    }

    public BlockFace opposite() {
        return switch ( this ) {
            case DOWN -> UP;
            case UP -> DOWN;
            case NORTH -> SOUTH;
            case WEST -> EAST;
            case EAST -> WEST;
            default -> NORTH;
        };
    }

    public static BlockFace fromId( int value ) {
        return switch ( value ) {
            case 0 -> BlockFace.DOWN;
            case 1 -> BlockFace.UP;
            case 2 -> BlockFace.NORTH;
            case 3 -> BlockFace.SOUTH;
            case 4 -> BlockFace.WEST;
            case 5 -> BlockFace.EAST;
            default -> null;
        };
    }

    public static BlockFace[] getHorizontal() {
        return new BlockFace[]{ NORTH, EAST, SOUTH, WEST };
    }

    public boolean isHorizontal() {
        return this == NORTH || this == EAST || this == SOUTH || this == WEST;
    }

}
