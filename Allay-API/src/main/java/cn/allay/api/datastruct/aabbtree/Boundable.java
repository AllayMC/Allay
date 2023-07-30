package cn.allay.api.datastruct.aabbtree;


import org.joml.primitives.AABBf;

/**
 * Created by pateman.
 */
public interface Boundable {

    AABBf getAABB(AABBf dest);
}
