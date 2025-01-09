---
comments: true
---

# Getting Started

Starting an Allay server would be very easy! The following guide will help you to
install and run your first Allay server.

## Install Java

Allay is written and running in java 21, so you need to install java 21. There are several version of java,
and we recommend you to use [GraalVM](https://www.graalvm.org/) for the best performance. [OpenJDK](https://adoptopenjdk.net/) is
also a good choice if you want to have a stable experience.

!!! tip

    If you are going to use GraalVM, please install the lastest LTS version instead of java 21 version.

After you have installed java 21, you can check if it is installed correctly by running the following command:

```shell
java --version
```

And if you have your java 21 installed correctly, the java version will be shown with no error message.

## Use AllayLauncher <small>recommended</small>

[AllayLauncher](https://github.com/AllayMC/AllayLauncher) is a small and fast tool written in c++ for downloading,
updating and managing Allay.

To install it, you just need to run one command:

=== ":fontawesome-brands-windows: Windows"

    ```powershell
    Invoke-Expression (Invoke-WebRequest -Uri "https://raw.githubusercontent.com/AllayMC/AllayLauncher/refs/heads/main/scripts/install_windows.ps1").Content
    ```

=== ":fontawesome-brands-linux: Linux"

    ```bash
    wget -qO- https://raw.githubusercontent.com/AllayMC/AllayLauncher/refs/heads/main/scripts/install_linux.sh | bash
    ```

## Install Allay Manually

### Download Allay

You can download the latest release from [GitHub](https://github.com/AllayMC/Allay/releases/latest).
If you want to take a look at the latest in-development features, you can also try
[Nightly Build](https://github.com/AllayMC/Allay/releases/tag/nightly), which is the latest build of the master branch.

### Run Allay

You should now have a file named `allay-server-<version of allay server>-<git hash>[-dev]-shaded.jar`, and an example
name is
`allay-server-0.1.0-dev-shaded.jar`. If there is a `dev` flag in the file name, it means that this is a development
version.
If your platform has a graphical interface, you can just double-click the jar file to run it, then a window will pop up
if you have installed java correctly:

![installation-p1.png](installation-p1.png)

If you are running on a server which does not have graphical interface, you can run the following command to start the
server:

```shell
java -jar allay-server-*-shaded.jar
```

And the same output will be shown in the console.
