package cn.allay.api.client.movement;

import cn.allay.api.math.location.Location3dc;

/**
 * Allay Project 2023/8/5
 *
 * @author daoge_cmd
 */
public interface ClientMovementValidator {

    ClientMovementValidator EMPTY_VALIDATOR = new ClientMovementValidator() {
        @Override
        public boolean validate(Location3dc newLoc) {
            return true;
        }

        @Override
        public boolean validateOnGround() {
            return true;
        }
    };

    boolean validate(Location3dc newLoc);

    boolean validateOnGround();
}
