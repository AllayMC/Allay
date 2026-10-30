package org.allaymc.server.command.defaults;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.Command;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.Dimension;
import oshi.SystemInfo;
import oshi.util.platform.windows.WmiQueryHandler;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.allaymc.api.math.MathUtils.round;

/**
 * {@code StatusCommand} reports live server, world, JVM, and optional host
 * diagnostics to a command sender. Individual readings are taken as each
 * section is printed, so the output is not a coordinated snapshot. Host
 * discovery uses ordered virtualization heuristics and tolerates selected
 * retrieval failures by printing unavailable information or omitting detail.
 *
 * @author daoge_cmd
 */
@Slf4j
public class StatusCommand extends Command {
    /**
     * {@code UPTIME_FORMAT} orders the whole-day, hour, minute, and second
     * components emitted by {@link #formatUptime(long)}.
     */
    protected static final String UPTIME_FORMAT = "%d days %d hours %d minutes %d seconds";

    // detectVM checks VM_VENDOR before VM_MAC, then VM_MODEL_ARRAY. Each
    // collection provides a separate exact-match or substring heuristic; the
    // first recognized environment wins without consulting later evidence.
    // The shared mutable collections affect later detections when modified.
    /**
     * Processor vendor identifier mappings.
     */
    protected static final Map<String, String> VM_VENDOR = new HashMap<>(10, 0.99f);
    /**
     * Uppercase MAC-prefix environment mappings.
     */
    protected static final Map<String, String> VM_MAC = new HashMap<>(10, 0.99f);
    /**
     * Computer-model substrings identifying environments.
     */
    protected static final String[] VM_MODEL_ARRAY = new String[]{
            "Linux KVM", "Linux lguest", "OpenVZ", "Qemu",
            "Microsoft Virtual PC", "VMWare", "linux-vserver",
            "Xen", "FreeBSD Jail", "VirtualBox", "Parallels",
            "Linux Containers", "LXC", "Bochs"
    };

    /**
     * {@code SYSTEM_INFO} supplies the shared host hardware and operating-system
     * access used by the detailed status sections.
     */
    protected static final SystemInfo SYSTEM_INFO = new SystemInfo();

    // colorizeUsage compares CRIT_USAGE before WARN_USAGE using strict greater
    // comparisons. Values equal to a boundary retain the lower severity color;
    // all callers supply percentages rather than fractions.
    /**
     * Warning usage percentage boundary.
     */
    private static final double WARN_USAGE = 80;
    /**
     * Critical usage percentage boundary.
     */
    private static final double CRIT_USAGE = 90;

    // Populate the exact processor-vendor and MAC-prefix mappings consumed by
    // detectVM before any status command can request environment detection.
    static {
        VM_VENDOR.put("bhyve", "bhyve");
        VM_VENDOR.put("KVM", "KVM");
        VM_VENDOR.put("TCG", "QEMU");
        VM_VENDOR.put("Microsoft Hv", "Microsoft Hyper-V or Windows Virtual PC");
        VM_VENDOR.put("lrpepyh vr", "Parallels");
        VM_VENDOR.put("VMware", "VMware");
        VM_VENDOR.put("XenVM", "Xen HVM");
        VM_VENDOR.put("ACRN", "Project ACRN");
        VM_VENDOR.put("QNXQVMBSQG", "QNX Hypervisor");
        VM_MAC.put("00:50:56", "VMware ESX 3");
        VM_MAC.put("00:0C:29", "VMware ESX 3");
        VM_MAC.put("00:05:69", "VMware ESX 3");
        VM_MAC.put("00:03:FF", "Microsoft Hyper-V");
        VM_MAC.put("00:1C:42", "Parallels Desktop");
        VM_MAC.put("00:0F:4B", "Virtual Iron 4");
        VM_MAC.put("00:16:3E", "Xen or Oracle VM");
        VM_MAC.put("08:00:27", "VirtualBox");
        VM_MAC.put("02:42:AC", "Docker Container");
    }

    /**
     * Creates the {@code /status} command with its built-in authorization permission.
     */
    public StatusCommand() {
        super("status", TrKeys.ALLAY_COMMAND_STATUS_DESCRIPTION, Permissions.COMMAND_STATUS);
    }

    /**
     * Prints physical, virtual, and installed-memory information for the host.
     * Nonpositive virtual capacity prints {@code "N/A"} rather than a usage
     * ratio, and an empty physical-module list omits the hardware subsection.
     */
    protected static void printOperationSystemMemoryInfo(CommandSender sender) {
        sender.sendMessage("--- Memory Info ---");
        var globalMemory = SYSTEM_INFO.getHardware().getMemory();
        var virtualMemory = globalMemory.getVirtualMemory();

        var totalPhys = globalMemory.getTotal();
        var usedPhys = totalPhys - globalMemory.getAvailable();
        sendMemoryUsage(sender, "Physical memory", usedPhys, totalPhys);

        var totalVirt = virtualMemory.getVirtualMax();
        var usedVirt = virtualMemory.getVirtualInUse();
        // A nonpositive virtual capacity cannot supply a meaningful usage
        // ratio. Keep the physical-memory result and mark this section
        // unavailable.
        if (totalVirt > 0) {
            sendMemoryUsage(sender, "Virtual memory", usedVirt, totalVirt);
        } else {
            sender.sendMessage("Virtual memory: " + TextFormat.GREEN + "N/A");
        }

        var physicalMemories = globalMemory.getPhysicalMemory();
        if (!physicalMemories.isEmpty()) {
            sender.sendMessage("Hardware list:");
            for (var each : physicalMemories) {
                sender.sendMessage("- " + TextFormat.GREEN + each.getBankLabel() + " " + formatFreq(each.getClockSpeed())
                                   + TextFormat.WHITE + " " + toMB(each.getCapacity()));
                sender.sendMessage("  " + TextFormat.GREEN + each.getMemoryType() + ", " + each.getManufacturer());
            }
        }
        sender.sendMessage("\n");
    }

    /**
     * Prints processor identity, frequency, core counts, and current JVM thread count.
     */
    protected static void printCPUInfo(CommandSender sender) {
        var cpu = SYSTEM_INFO.getHardware().getProcessor();
        var processorIdentifier = cpu.getProcessorIdentifier();

        sender.sendMessage("--- CPU Info ---");
        sender.sendMessage(
                "CPU: " + TextFormat.GREEN + processorIdentifier.getName().trim() + TextFormat.YELLOW +
                " (" + formatFreq(cpu.getMaxFreq()) + " baseline; " +
                cpu.getPhysicalProcessorCount() + " cores, " + cpu.getLogicalProcessorCount() + " logical cores)"
        );
        sender.sendMessage("Thread count: " + TextFormat.GREEN + Thread.getAllStackTraces().size());
        sender.sendMessage(
                "CPU Features: " + TextFormat.GREEN +
                (processorIdentifier.isCpu64bit() ? "64bit, " : "32bit, ") +
                processorIdentifier.getModel() + ", micro-arch: " + processorIdentifier.getMicroarchitecture()
        );
        sender.sendMessage("\n");
    }

    /**
     * Prints discovered network interfaces, addresses, and available speed values.
     * Missing interfaces produce no section. Retrieval exceptions print a
     * failure message and are logged at debug level, preserving any lines
     * already sent to {@code sender}.
     * The handler passes OSHI's bits-per-second speed to {@code toKB}, so its
     * displayed {@code "KB/s"} rate is numerically kibibits per second rather
     * than a converted byte rate.
     */
    protected static void printNetworkInfo(CommandSender sender) {
        try {
            var networkIFs = SYSTEM_INFO.getHardware().getNetworkIFs();
            if (networkIFs == null || networkIFs.isEmpty()) {
                return;
            }

            sender.sendMessage("--- Network Info ---");
            sender.sendMessage("Network hardware list:");
            for (var nic : networkIFs) {
                var addresses = Stream.concat(
                        Arrays.stream(nic.getIPv4addr()),
                        Arrays.stream(nic.getIPv6addr())
                ).toList();

                // OSHI reports bits per second. toKB divides by 1024 without
                // the factor of eight needed to turn that reading into a byte
                // rate, leaving the existing suffix inconsistent with its unit.
                String speedStr = nic.getSpeed() > 0 ? toKB(nic.getSpeed()) + "/s " : "";
                sender.sendMessage(
                        "- " + TextFormat.GREEN + nic.getDisplayName() + " " + speedStr
                        + TextFormat.YELLOW + String.join(", ", addresses)
                );
            }
            sender.sendMessage("\n");
        } catch (Exception e) {
            // Network diagnostics are optional. Preserve earlier status output
            // and let later sections run after reporting this retrieval failure.
            sender.sendMessage(TextFormat.RED + "Failed to get network info.");
            log.debug("Network info retrieval failed", e);
        }
    }

    /**
     * Prints operating-system, JVM, and detected virtualization information.
     * A missing virtualization match or detection exception displays
     * {@code "N/A"}; detection failures are logged without discarding the
     * operating-system and JVM details already sent.
     */
    protected static void printOperationSystemAndJVMInfo(CommandSender sender) {
        var os = SYSTEM_INFO.getOperatingSystem();
        var mxBean = ManagementFactory.getRuntimeMXBean();

        sender.sendMessage("--- OS & JVM Info ---");
        var versionInfo = os.getVersionInfo();
        sender.sendMessage(
                "OS: " + TextFormat.GREEN +
                os.getFamily() + " " + os.getManufacturer() + " " +
                versionInfo.getVersion() + " " + versionInfo.getCodeName() + " " +
                os.getBitness() + "bit, build " + versionInfo.getBuildNumber()
        );
        sender.sendMessage("JVM: " + TextFormat.GREEN + mxBean.getVmName() + " " + mxBean.getVmVendor() + " " + mxBean.getVmVersion());
        try {
            String vm = detectVM();
            sender.sendMessage("Virtual environment: " + TextFormat.GREEN + (vm == null ? "N/A" : vm));
        } catch (Exception e) {
            // Environment detection is optional. Retain the operating-system
            // and JVM details while replacing only the failed environment result.
            sender.sendMessage("Virtual environment: " + TextFormat.GREEN + "N/A");
            log.debug("VM detection error", e);
        }
        sender.sendMessage("\n");
    }

    /**
     * Prints tick metrics and dimension-wide object counts for every loaded world.
     */
    protected static void printWorldInfo(CommandSender sender) {
        sender.sendMessage("--- Worlds Status ---");
        for (var world : Server.getInstance().getWorldPool().getWorlds().values()) {
            sender.sendMessage("- " + world.getWorldData().getDisplayName());
            sender.sendMessage("  TPS: " + TextFormat.GREEN + world.getTPS());
            sender.sendMessage("  MSPT: " + TextFormat.GREEN + world.getMSPT());
            // World tick usage is a fraction; feedback displays a percentage.
            sender.sendMessage("  TickUsage: " + TextFormat.GREEN + (world.getTickUsage() * 100f) + "%");

            var dims = world.getDimensions().values();
            var chunks = dims.stream().mapToInt(d -> d.getChunkManager().getLoadedChunks().size()).sum();
            var entities = dims.stream().mapToInt(Dimension::getEntityCount).sum();
            var blockEntities = dims.stream().mapToInt(Dimension::getBlockEntityCount).sum();

            sender.sendMessage("  Chunks: " + TextFormat.GREEN + chunks);
            sender.sendMessage("  Entities: " + TextFormat.GREEN + entities);
            sender.sendMessage("  BlockEntities: " + TextFormat.GREEN + blockEntities);
            sender.sendMessage("\n");
        }
    }

    /**
     * Prints elapsed wall-clock time since the server's recorded start timestamp.
     */
    protected static void printUpTimeInfo(CommandSender sender) {
        var time = System.currentTimeMillis() - Server.getInstance().getStartTime();
        sender.sendMessage("Uptime: " + TextFormat.GREEN + formatUptime(time));
    }

    /**
     * Prints used, committed, and maximum JVM heap memory in mebibytes.
     * The usage color compares used memory with the maximum heap; a nonpositive
     * maximum produces a zero percentage, and displayed values are rounded to
     * two decimals.
     */
    protected static void printMemoryUsageInfo(CommandSender sender) {
        var runtime = Runtime.getRuntime();
        var totalMB = bytesToMB(runtime.totalMemory());
        var usedMB = bytesToMB(runtime.totalMemory() - runtime.freeMemory());
        var maxMB = bytesToMB(runtime.maxMemory());
        // A nonpositive maximum cannot define heap utilization. Use zero for
        // the percentage while retaining the measured memory totals.
        var usagePct = maxMB > 0 ? (usedMB / maxMB) * 100d : 0d;

        sender.sendMessage("Used VM memory: " + colorizeUsage(usagePct) + round(usedMB, 2) + " MB (" + round(usagePct, 2) + "%)");
        sender.sendMessage("Total VM memory: " + TextFormat.GREEN + round(totalMB, 2) + " MB");
        sender.sendMessage("Maximum JVM memory: " + TextFormat.GREEN + round(maxMB, 2) + " MB");
    }

    /**
     * Prints connected-player occupancy against the live capacity limit.
     * Ratios above ninety percent use gold, including full positive capacity;
     * a nonpositive capacity produces a zero ratio for this display.
     */
    protected static void printOnlinePlayerInfo(CommandSender sender) {
        var server = Server.getInstance();
        var ps = server.getPlayerManager();

        var online = ps.getPlayerCount();
        var maxPlayerCount = ps.getMaxPlayerCount();
        var ratio = maxPlayerCount > 0 ? (float) online / (float) maxPlayerCount : 0f;

        var color = TextFormat.GREEN;
        // The ninety-percent branch also selects gold for fully occupied capacity.
        if (ratio > 0.90f) {
            color = TextFormat.GOLD;
        } else if (online == maxPlayerCount && maxPlayerCount > 0) {
            color = TextFormat.RED;
        }

        sender.sendMessage("Players: " + color + online + "/" + maxPlayerCount);
    }

    /**
     * Returns the first environment recognized by the ordered host heuristics.
     * Processor vendor, MAC prefixes, model, manufacturer, and memory evidence
     * precede platform-specific checks. No match returns {@code null}; callers
     * must handle discovery exceptions, including an empty physical-memory list.
     */
    protected static String detectVM() {
        var hardware = SYSTEM_INFO.getHardware();

        var vmVendor = VM_VENDOR.get(hardware.getProcessor().getProcessorIdentifier().getVendor().trim());
        if (vmVendor != null) {
            return vmVendor;
        }

        // Unrecognized processor vendors fall through to interface prefixes,
        // allowing network metadata to identify environments absent from the
        // vendor table.
        var networkIFs = hardware.getNetworkIFs();
        for (var nif : networkIFs) {
            var mac = nif.getMacaddr().toUpperCase(Locale.ROOT);
            // Eight characters retain three colon-separated MAC-address octets.
            var oui = mac.length() > 7 ? mac.substring(0, 8) : mac;
            var vmMac = VM_MAC.get(oui);
            if (vmMac != null) {
                return vmMac;
            }
        }

        // An unmatched interface set leaves computer-model evidence available,
        // so machines without recognized virtual MAC prefixes can still
        // be identified.
        var model = hardware.getComputerSystem().getModel();
        for (var vm : VM_MODEL_ARRAY) {
            if (model.contains(vm)) {
                return vm;
            }
        }

        var manufacturer = hardware.getComputerSystem().getManufacturer();
        if ("Microsoft Corporation".equals(manufacturer) && "Virtual Machine".equals(model)) {
            return "Microsoft Hyper-V";
        }

        // Earlier heuristics may miss an environment. The first memory module
        // supplies another manufacturer signal before platform-specific probes run.
        if ("QEMU".equals(hardware.getMemory().getPhysicalMemory().getFirst().getManufacturer())) {
            return "QEMU";
        }

        // Native WMI classes are used only on Windows; other platforms use
        // filesystem markers after shared hardware heuristics fail.
        var osName = System.getProperties().getProperty("os.name", "").toUpperCase(Locale.ROOT);
        if (osName.contains("WINDOWS")) {
            var wmiQuery = new WbemcliUtil.WmiQuery<>("Win32_ComputerSystem", ComputerSystemEntry.class);
            var result = WmiQueryHandler.createInstance().queryWMI(wmiQuery);
            var present = result.getValue(ComputerSystemEntry.HYPERVISORPRESENT, 0);
            if (present != null && "true".equalsIgnoreCase(present.toString())) {
                return "Hyper-V";
            }
        } else {
            if (new File("/.dockerenv").exists()) {
                return "Docker Container";
            }

            var cgroupFile = new File("/proc/1/cgroup");
            if (cgroupFile.exists()) {
                try (var lines = Files.lines(cgroupFile.toPath())) {
                    var containerized = lines.anyMatch(line -> line.contains("docker") || line.contains("lxc"));
                    if (containerized) {
                        return "Docker Container";
                    }
                } catch (IOException e) {
                    // A failed cgroup read provides no container evidence.
                    // Log that diagnostic and leave the unmatched result
                    // available to the caller.
                    log.error("Error checking /proc/1/cgroup for containerization", e);
                }
            }
        }

        return null;
    }

    /**
     * Sends a labeled memory total and usage percentage from byte counts.
     * Nonpositive {@code totalBytes} prints {@code "N/A"} without dividing;
     * positive totals use mebibyte display values and the shared utilization
     * color thresholds.
     */
    private static void sendMemoryUsage(CommandSender sender, String label, long usedBytes, long totalBytes) {
        if (totalBytes <= 0) {
            sender.sendMessage(label + ": " + TextFormat.GREEN + "N/A");
            return;
        }

        var usagePct = usedBytes * 100d / totalBytes;
        sender.sendMessage(
                label + ": " + colorizeUsage(usagePct) +
                toMB(usedBytes) + " / " + toMB(totalBytes) +
                " (" + round(usagePct, 2) + "%)"
        );
    }

    /**
     * Returns the display color for a supplied usage percentage.
     * Values strictly above {@link #CRIT_USAGE} are red; values strictly above
     * {@link #WARN_USAGE} are gold, and all remaining values are green.
     */
    private static TextFormat colorizeUsage(double usagePercent) {
        if (usagePercent > CRIT_USAGE) return TextFormat.RED;
        if (usagePercent > WARN_USAGE) return TextFormat.GOLD;
        return TextFormat.GREEN;
    }

    /**
     * Returns bytes formatted as kibibytes with the {@code " KB"} suffix.
     */
    protected static String toKB(long bytes) {
        // Binary scaling preserves the display's 1024-byte unit convention.
        return round(bytes / 1024d, 2) + " KB";
    }

    /**
     * Returns bytes formatted as rounded mebibytes with the {@code " MB"} suffix.
     */
    protected static String toMB(long bytes) {
        return round(bytesToMB(bytes), 2) + " MB";
    }

    /**
     * Converts the supplied byte count to an unrounded mebibyte value.
     */
    private static double bytesToMB(long bytes) {
        // Each mebibyte contains 1024 squared bytes before display rounding.
        return bytes / (1024d * 1024d);
    }

    /**
     * Formats a frequency using the largest supported decimal unit.
     * Positive inputs select gigahertz, megahertz, kilohertz, or hertz, with
     * scaled values rounded to two decimals; nonpositive inputs return
     * {@code "N/A"}.
     */
    protected static String formatFreq(long hz) {
        // Frequency labels use decimal powers rather than binary memory units.
        if (hz >= 1_000_000_000L) {
            return String.format("%.2fGHz", hz / 1_000_000_000d);
        } else if (hz >= 1_000_000L) {
            return String.format("%.2fMHz", hz / 1_000_000d);
        } else if (hz >= 1_000L) {
            return String.format("%.2fKHz", hz / 1_000d);
        } else if (hz > 0) {
            return hz + "Hz";
        } else {
            return "N/A";
        }
    }

    /**
     * Formats an elapsed millisecond duration as whole days, hours, minutes, and
     * seconds. Each component is removed before extracting the next smaller
     * unit, and subsecond remainder is omitted from the returned text.
     */
    protected static String formatUptime(long uptime) {
        // Successive whole-unit extraction leaves only subsecond precision undisplayed.
        long days = TimeUnit.MILLISECONDS.toDays(uptime);
        uptime -= TimeUnit.DAYS.toMillis(days);

        long hours = TimeUnit.MILLISECONDS.toHours(uptime);
        uptime -= TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(uptime);
        uptime -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(uptime);
        return String.format(UPTIME_FORMAT, days, hours, minutes, seconds);
    }

    /**
     * Installs status reporting with optional detailed host diagnostics.
     * {@code full} defaults to {@code false}; uptime, JVM heap, players, and
     * worlds are always printed. Detailed sections run afterward and preserve
     * output already sent when a later retrieval fails.
     */
    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().bool("full", false).optional().exec(context -> {
            boolean full = context.getResult(0);
            var sender = context.getSender();

            sender.sendMessage("--- Server Status ---");
            printUpTimeInfo(sender);
            printMemoryUsageInfo(sender);
            printOnlinePlayerInfo(sender);
            sender.sendMessage("\n");

            printWorldInfo(sender);
            if (full) {
                printOperationSystemAndJVMInfo(sender);
                printNetworkInfo(sender);
                printCPUInfo(sender);
                printOperationSystemMemoryInfo(sender);
            }
            return context.success();
        });
    }

    /**
     * {@code ComputerSystemEntry} names the WMI property queried for Windows hypervisor detection.
     */
    protected enum ComputerSystemEntry {
        /**
         * {@code HYPERVISORPRESENT} selects the WMI hypervisor flag consumed by
         * {@link StatusCommand#detectVM()} when shared hardware heuristics find no match.
         */
        HYPERVISORPRESENT
    }
}
