package org.allaymc.server.command;

import lombok.Getter;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.permission.ConstantPermissionCalculator;
import org.allaymc.api.permission.PermissionCalculator;
import org.allaymc.api.permission.Tristate;

import java.util.List;

/**
 * {@code MockCommandSender} supplies an always-permitted command sender with a
 * mutable fake origin for parsing tests. It discards outgoing feedback and
 * permission-calculator replacements, allowing tests to inspect command results
 * without a network session or a real world-backed location.
 *
 * @author daoge_cmd
 */
public final class MockCommandSender implements CommandSender {

    /**
     * {@code fakeLocation} is the shared mutable execution origin exposed by
     * both getters. Its dimension is {@code null}; position tests mutate
     * coordinates without performing dimension-dependent operations.
     */
    @Getter
    private final Location3d fakeLocation = new Location3d(0, 0, 0, null);

    /**
     * Returns an empty sender name for command-parsing fixtures. Tests
     * requiring a real display name must provide another sender because this
     * fixture intentionally contributes no name to feedback or matching.
     */
    @Override
    public String getCommandSenderName() {
        return "";
    }

    /**
     * Returns the same mutable fake location exposed by
     * {@code getFakeLocation()}. Tests can adjust its coordinates before
     * parsing; its {@code null} dimension cannot support selector resolution or
     * world-dependent feedback checks.
     */
    @Override
    public Location3dc getCommandExecuteLocation() {
        return this.fakeLocation;
    }

    /**
     * Discards plain messages because parsing tests do not attach a feedback
     * transport.
     */
    @Override
    public void sendMessage(String message) {
    }

    /**
     * Discards translatable messages because parsing tests inspect results
     * without message delivery.
     */
    @Override
    public void sendTranslatable(String translatable, Object... args) {
    }

    /**
     * Discards command feedback containers because this test sender has no
     * output transport.
     */
    @Override
    public void sendCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
    }

    /**
     * Returns a new calculator that grants every permission checked by the
     * tree. The fixture does not retain calculators, so replacing one through
     * {@code setPermissionCalculator} cannot change this behavior.
     */
    @Override
    public PermissionCalculator getPermissionCalculator() {
        return new ConstantPermissionCalculator(Tristate.TRUE);
    }

    /**
     * Ignores the supplied calculator so command-parsing fixtures remain fully
     * permitted. Subsequent {@code getPermissionCalculator} calls still return
     * a new constant-true calculator rather than the object passed to this
     * setter.
     */
    @Override
    public void setPermissionCalculator(PermissionCalculator calculator) {
    }
}
