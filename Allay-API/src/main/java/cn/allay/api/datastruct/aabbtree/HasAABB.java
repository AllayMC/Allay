package cn.allay.api.datastruct.aabbtree;


import org.joml.primitives.AABBd;

/**
 * Created by pateman.
 */
public interface HasAABB {

    AABBd copyOffsetAABBTo(AABBd dest);
}
