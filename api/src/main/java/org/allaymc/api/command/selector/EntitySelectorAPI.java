package org.allaymc.api.command.selector;

import org.allaymc.api.AllayAPI;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;

import java.util.List;
import java.util.regex.Pattern;

/**
 * {@code EntitySelectorAPI} resolves selector tokens into entities relative to
 * a command sender's location and dimension. Registered arguments supply
 * ordered predicates or whole-list filters. Structural validation checks
 * selector shape only; successful validation does not guarantee that argument
 * names or values can be parsed.
 *
 * @author daoge_cmd
 */
public interface EntitySelectorAPI {

    /**
     * {@code API} holds the installed selector implementation. Server
     * initialization must supply it before callers use {@code getAPI} to
     * resolve selectors.
     */
    AllayAPI.APIInstanceHolder<EntitySelectorAPI> API = AllayAPI.APIInstanceHolder.create();

    /**
     * {@code ENTITY_SELECTOR} recognizes supported selector letters and an
     * optional bracketed argument suffix. Argument names and values are
     * validated separately during entity matching.
     */
    Pattern ENTITY_SELECTOR = Pattern.compile("^@([aeprs])(?:\\[(.*)])?$");

    /**
     * {@code ARGUMENT_JOINER} separates a selector argument key from its value.
     * Parsing splits only the first separator so later separators remain in the
     * value.
     */
    String ARGUMENT_JOINER = "=";

    /**
     * Returns the installed selector implementation from the shared {@code API}
     * holder.
     */
    static EntitySelectorAPI getAPI() {
        return API.get();
    }

    /**
     * Resolves a selector token against the sender's execution origin and
     * registered handlers. The result may be empty, and its entity references
     * remain live server objects. Structural and evaluated argument failures
     * raise {@code SelectorSyntaxException}; an empty candidate list can skip
     * later argument-value validation.
     *
     * <p>
     * The server rejects unknown keys when parsing uncached text. Its parsed
     * maps are shared across selector instances, so cached text accepted under
     * another handler registry can bypass the current instance's unknown-key
     * check.
     */
    List<Entity> matchEntities(CommandSender sender, String token) throws SelectorSyntaxException;

    /**
     * Returns whether the token has a supported selector letter and bracket
     * shape. This check does not parse argument names or values, so
     * {@code matchEntities} can still reject a token that passes it.
     */
    boolean checkValid(String token);

    /**
     * Registers an argument handler under its key and inserts it into priority
     * order. An existing key returns {@code false} without replacement; callers
     * configure handlers before concurrent selector matching begins.
     */
    boolean registerArgument(SelectorArgument argument);
}
