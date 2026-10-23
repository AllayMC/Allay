package org.allaymc.api.entity.action;

/**
 * {@code ArrowShakeAction} describes an arrow shake animation with a client
 * event data value. An entity applies this value through its action pipeline,
 * and the protocol encoder forwards {@code times} as the {@code ARROW_SHAKE}
 * event data.
 *
 * @param times client shake count
 * @author daoge_cmd
 */
public record ArrowShakeAction(int times) implements EntityAction {
}
