package cn.allay.api.world;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/7/8
 *
 * @author daoge_cmd
 */
public interface WorldPool {
    World getWorld(String name);

    @UnmodifiableView
    Map<String, World> getWorlds();

    void addWorld(World world);

    void setDefaultWorld(World world);

    World getDefaultWorld();
}
