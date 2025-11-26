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
 * @author daoge_cmd
 */
@Slf4j
public class StatusCommand extends Command {
    protected static final String UPTIME_FORMAT = "%d days %d hours %d minutes %d seconds";

    protected static final Map<String, String> VM_VENDOR = new HashMap<>(10, 0.99f);
    protected static final Map<String, String> VM_MAC = new HashMap<>(10, 0.99f);
    protected static final String[] VM_MODEL_ARRAY = new String[]{
            "Linux KVM", "Linux lguest", "OpenVZ", "Qemu",
            "Microsoft Virtual PC", "VMWare", "linux-vserver",
            "Xen", "FreeBSD Jail", "VirtualBox", "Parallels",
            "Linux Containers", "LXC", "Bochs"
    };

    protected static final SystemInfo SYSTEM_INFO = new SystemInfo();

    private static final double WARN_USAGE = 80;
    private static final double CRIT_USAGE = 90;

    static {
        // VM VENDOR
        VM_VENDOR.put("bhyve", "bhyve");
        VM_VENDOR.put("KVM", "KVM");
        VM_VENDOR.put("TCG", "QEMU");
        VM_VENDOR.put("Microsoft Hv", "Microsoft Hyper-V or Windows Virtual PC");
        VM_VENDOR.put("lrpepyh vr", "Parallels");
        VM_VENDOR.put("VMware", "VMware");
        VM_VENDOR.put("XenVM", "Xen HVM");
        VM_VENDOR.put("ACRN", "Project ACRN");
        VM_VENDOR.put("QNXQVMBSQG", "QNX Hypervisor");
        // VM MAC
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

    public StatusCommand() {
        super("status", TrKeys.ALLAY_COMMAND_STATUS_DESCRIPTION, Permissions.COMMAND_STATUS);
    }

    protected static void printOperationSystemMemoryInfo(CommandSender sender) {
        sender.sendMessage("--- Memory Info ---");
        var globalMemory = SYSTEM_INFO.getHardware().getMemory();
        var virtualMemory = globalMemory.getVirtualMemory();

        // Physical Memory
        var totalPhys = globalMemory.getTotal();
        var usedPhys = totalPhys - globalMemory.getAvailable();
        sendMemoryUsage(sender, "Physical memory", usedPhys, totalPhys);

        // Virtual Memory
        var totalVirt = virtualMemory.getVirtualMax();
        var usedVirt = virtualMemory.getVirtualInUse();
        // Some hosts report 0 virtual max; avoid div/0
        if (totalVirt > 0) {
            sendMemoryUsage(sender, "Virtual memory", usedVirt, totalVirt);
        } else {
            sender.sendMessage("Virtual memory: " + TextFormat.GREEN + "N/A");
        }

        // Hardware
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

                String speedStr = nic.getSpeed() > 0 ? toKB(nic.getSpeed()) + "/s " : "";
                sender.sendMessage(
                        "- " + TextFormat.GREEN + nic.getDisplayName() + " " + speedStr
                        + TextFormat.YELLOW + String.join(", ", addresses)
                );
            }
            sender.sendMessage("\n");
        } catch (Exception e) {
            sender.sendMessage(TextFormat.RED + "Failed to get network info.");
            log.debug("Network info retrieval failed", e);
        }
    }

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
            sender.sendMessage("Virtual environment: " + TextFormat.GREEN + "N/A");
            log.debug("VM detection error", e);
        }
        sender.sendMessage("\n");
    }

    protected static void printWorldInfo(CommandSender sender) {
        sender.sendMessage("--- Worlds Status ---");
        for (var world : Server.getInstance().getWorldPool().getWorlds().values()) {
            sender.sendMessage("- " + world.getWorldData().getDisplayName());
            sender.sendMessage("  TPS: " + TextFormat.GREEN + world.getTPS());
            sender.sendMessage("  MSPT: " + TextFormat.GREEN + world.getMSPT());
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

    protected static void printUpTimeInfo(CommandSender sender) {
        var time = System.currentTimeMillis() - Server.getInstance().getStartTime();
        sender.sendMessage("Uptime: " + TextFormat.GREEN + formatUptime(time));
    }

    protected static void printMemoryUsageInfo(CommandSender sender) {
        var runtime = Runtime.getRuntime();
        var totalMB = bytesToMB(runtime.totalMemory());
        var usedMB = bytesToMB(runtime.totalMemory() - runtime.freeMemory());
        var maxMB = bytesToMB(runtime.maxMemory());
        var usagePct = maxMB > 0 ? (usedMB / maxMB) * 100d : 0d;

        sender.sendMessage("Used VM memory: " + colorizeUsage(usagePct) + round(usedMB, 2) + " MB (" + round(usagePct, 2) + "%)");
        sender.sendMessage("Total VM memory: " + TextFormat.GREEN + round(totalMB, 2) + " MB");
        sender.sendMessage("Maximum JVM memory: " + TextFormat.GREEN + round(maxMB, 2) + " MB");
    }

    protected static void printOnlinePlayerInfo(CommandSender sender) {
        var server = Server.getInstance();
        var ps = server.getPlayerManager();

        var online = ps.getPlayerCount();
        var maxPlayerCount = ps.getMaxPlayerCount();
        var ratio = maxPlayerCount > 0 ? (float) online / (float) maxPlayerCount : 0f;

        var color = TextFormat.GREEN;
        if (ratio > 0.90f) {
            color = TextFormat.GOLD;
        } else if (online == maxPlayerCount && maxPlayerCount > 0) {
            color = TextFormat.RED;
        }

        sender.sendMessage("Players: " + color + online + "/" + maxPlayerCount);
    }

    protected static String detectVM() {
        var hardware = SYSTEM_INFO.getHardware();

        // CPU model detection
        var vmVendor = VM_VENDOR.get(hardware.getProcessor().getProcessorIdentifier().getVendor().trim());
        if (vmVendor != null) {
            return vmVendor;
        }

        // MAC address detection
        var networkIFs = hardware.getNetworkIFs();
        for (var nif : networkIFs) {
            var mac = nif.getMacaddr().toUpperCase(Locale.ROOT);
            var oui = mac.length() > 7 ? mac.substring(0, 8) : mac;
            var vmMac = VM_MAC.get(oui);
            if (vmMac != null) {
                return vmMac;
            }
        }

        // Model detection
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

        // Memory manufacturer detection
        if ("QEMU".equals(hardware.getMemory().getPhysicalMemory().getFirst().getManufacturer())) {
            return "QEMU";
        }

        // Check Windows system parameters
        // WMI virtual machine query only on Windows
        var osName = System.getProperties().getProperty("os.name", "").toUpperCase(Locale.ROOT);
        if (osName.contains("WINDOWS")) {
            var wmiQuery = new WbemcliUtil.WmiQuery<>("Win32_ComputerSystem", ComputerSystemEntry.class);
            var result = WmiQueryHandler.createInstance().queryWMI(wmiQuery);
            var present = result.getValue(ComputerSystemEntry.HYPERVISORPRESENT, 0);
            if (present != null && "true".equalsIgnoreCase(present.toString())) {
                return "Hyper-V";
            }
        } else {
            // Check for Docker container
            // Docker check only on non-Windows systems
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
                    log.error("Error checking /proc/1/cgroup for containerization", e);
                }
            }
        }

        return null;
    }

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

    private static TextFormat colorizeUsage(double usagePercent) {
        if (usagePercent > CRIT_USAGE) return TextFormat.RED;
        if (usagePercent > WARN_USAGE) return TextFormat.GOLD;
        return TextFormat.GREEN;
    }

    protected static String toKB(long bytes) {
        return round(bytes / 1024d, 2) + " KB";
    }

    protected static String toMB(long bytes) {
        return round(bytesToMB(bytes), 2) + " MB";
    }

    private static double bytesToMB(long bytes) {
        return bytes / (1024d * 1024d);
    }

    protected static String formatFreq(long hz) {
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

    protected static String formatUptime(long uptime) {
        long days = TimeUnit.MILLISECONDS.toDays(uptime);
        uptime -= TimeUnit.DAYS.toMillis(days);

        long hours = TimeUnit.MILLISECONDS.toHours(uptime);
        uptime -= TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(uptime);
        uptime -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(uptime);
        return String.format(UPTIME_FORMAT, days, hours, minutes, seconds);
    }

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

    protected enum ComputerSystemEntry {
        HYPERVISORPRESENT
    }
}