package org.allaymc.api.client.movement;

import org.allaymc.api.math.location.Location3fc;

/**
 * Allay Project 2023/8/5
 *
 * @author daoge_cmd
 */
public interface ClientMovementValidator {

    ClientMovementValidator EMPTY_VALIDATOR = new ClientMovementValidator() {
        @Override
        public boolean validate(Location3fc newLoc) {
            return true;
        }

        @Override
        public boolean validateOnGround() {
            return true;
        }
    };

    boolean validate(Location3fc newLoc);

    boolean validateOnGround();
}
