package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandNode;

import java.util.Locale;

/**
 * {@code EnumClassNode} maps textual enum names to their original enum
 * constants for command executors. It derives lowercase advertised spellings
 * from {@code name()} and accepts matching input without case sensitivity.
 * Executors therefore receive enum objects rather than needing to interpret the
 * string representation advertised to the client.
 *
 * <p> The conversion uses {@code name()} on both sides instead of
 * {@code toString()}, so an enum's custom display text does not change the
 * accepted command vocabulary. Each node keeps the enum class used to generate
 * its values and scans that class during result conversion. An unmatched
 * conversion returns {@code null}; inherited matching normally calls the hook
 * only after a generated spelling has matched.
 *
 * @author daoge_cmd
 */
public class EnumClassNode extends EnumIgnoreCaseNode {

    /**
     * Enum class supplying accepted constant names and converted result
     * objects.
     */
    protected Class<? extends Enum<?>> enumClass;

    /**
     * Creates an unattached enum-constant node with a derived network
     * enumeration name. A new values array is generated from {@code enumClass};
     * the supplied constant default is retained without enabling optionality.
     */
    public <T extends Enum<?>> EnumClassNode(String name, CommandNode parent, T defaultValue, Class<T> enumClass) {
        super(name, parent, defaultValue, toStringArray(enumClass));
        this.enumClass = enumClass;
    }

    /**
     * Creates an unattached enum-constant node with an explicit network
     * enumeration name. The enum class supplies a new advertised values array,
     * and the constant default remains separate from optionality configuration.
     */
    public <T extends Enum<?>> EnumClassNode(String name, CommandNode parent, T defaultValue, String enumName, Class<T> enumClass) {
        super(name, parent, defaultValue, enumName, toStringArray(enumClass));
        this.enumClass = enumClass;
    }

    /**
     * Returns a new array of lowercase enum constant names in declaration
     * order. The method uses {@code name()} rather than display text, allowing
     * the result converter to recover constants even when {@code toString()} is
     * customized.
     */
    protected static String[] toStringArray(Class<? extends Enum<?>> enumClass) {
        var enumConstants = enumClass.getEnumConstants();
        var values = new String[enumConstants.length];
        for (int index = 0; index < enumConstants.length; index++) {
            var e = enumConstants[index];
            values[index] = e.name().toLowerCase(Locale.ROOT);
        }
        return values;
    }

    /**
     * Returns the enum constant whose declared name matches the argument
     * without case sensitivity. The method scans {@code enumClass} rather than
     * parsing an enum's display text, keeping the accepted lowercase vocabulary
     * tied to stable constant names.
     *
     * <p> An unmatched argument returns {@code null} instead of raising an
     * enum-lookup exception. Inherited matching normally calls this method only
     * after an advertised name matches, but subclasses can invoke the
     * conversion hook directly and must account for that absent result.
     */
    @Override
    protected Object argToResult(String arg) {
        for (var e : enumClass.getEnumConstants()) {
            if (e.name().equalsIgnoreCase(arg)) {
                return e;
            }
        }
        // A conversion hook can be invoked outside inherited matching, so preserve
        // the absent-result convention instead of manufacturing a different enum value.
        return null;
    }
}
