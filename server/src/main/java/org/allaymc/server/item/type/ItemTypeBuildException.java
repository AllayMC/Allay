package org.allaymc.server.item.type;

import lombok.experimental.StandardException;

/**
 * {@code ItemTypeBuildException} reports item-type configuration failures and
 * failures from prepared stack factories. The builder uses it for missing
 * vanilla data, incompatible custom definitions, and reflected constructor
 * preparation. The prepared factory wraps later stack-construction failures and
 * retains their underlying cause.
 *
 * @author daoge_cmd
 */
@StandardException
public class ItemTypeBuildException extends RuntimeException {
}
