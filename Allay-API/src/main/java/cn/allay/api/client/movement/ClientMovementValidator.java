package cn.allay.api.client.movement;

import cn.allay.api.math.Location3dc;

/**
 * Allay Project 2023/8/5
 *
 * @author daoge_cmd
 */
public interface ClientMovementValidator {

    ClientMovementValidator EMPTY_VALIDATOR = newLoc -> true;

    boolean validate(Location3dc newLoc);
}
