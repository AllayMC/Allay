package cn.allay.api.math;

import cn.allay.api.world.World;
import org.joml.Vector3ic;

/**
 * Allay Project 7/30/2023
 *
 * @author Cool_Loong
 */
public interface Position3ic extends Vector3ic {
    World world();
}
