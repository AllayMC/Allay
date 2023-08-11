package cn.allay.api.block.data;

import lombok.Getter;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * @author LucGamesYT
 * @version 1.0
 */
@Getter
public enum BlockFace {

    WEST(new Vector3i( -1, 0, 0 ) ),
    EAST(new Vector3i( 1, 0, 0 ) ),
    DOWN(new Vector3i( 0, -1, 0 ) ),
    UP(new Vector3i( 0, 1, 0 ) ),
    NORTH(new Vector3i( 0, 0, -1 ) ),
    SOUTH(new Vector3i( 0, 0, 1 ) );

    private final Vector3ic offset;

    BlockFace(Vector3ic offset) {
        this.offset = offset;
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

    public Vector3ic offsetPos(Vector3ic pos) {
        return pos.add(this.offset, new Vector3i());
    }

    public Vector3ic offsetPos(int x, int y, int z) {
        return new Vector3i(x, y, z).add(offset);
    }

    public static BlockFace[] getHorizontal() {
        return new BlockFace[]{ NORTH, EAST, SOUTH, WEST };
    }

    public static BlockFace[] getVertical() {
        return new BlockFace[]{ UP, DOWN };
    }

    public boolean isHorizontal() {
        return this == NORTH || this == EAST || this == SOUTH || this == WEST;
    }

}
