# LegacyWrapper

Launches applet-based Minecraft versions through
`net.technicpack.legacywrapper.Launch`. The player name and session remain the
first two command-line arguments.

## Window options

Version 1.2.2 adds `--maximize`, which starts the frame maximized and resizable.
It takes precedence over `--width` and `--height`; restoring the window uses the
normal 900 by 540 initial size. Maximization is requested through AWT and depends
on window-manager support.

Without `--maximize`, specifying both `--width` and `--height` creates a
fixed-size, non-resizable frame. Otherwise the initial frame is 900 by 540 and
resizable, as before.

## Building and publishing

Use Maven 3.0.5 with JDK 8 selected as `JAVA_HOME`. Shade 2.0 requires
the older Maven dependency resolver and does not work with current Maven.
Configure Maven Central over HTTPS in your Maven settings:

```xml
<settings>
  <mirrors>
    <mirror>
      <id>central-https</id>
      <mirrorOf>central</mirrorOf>
      <url>https://repo.maven.apache.org/maven2</url>
    </mirror>
  </mirrors>
</settings>
```

```sh
mvn -Dmaven.compiler.source=1.6 -Dmaven.compiler.target=1.6 package
```

The existing Shade 2.0 plugin uses ASM 3.3.1; retain Java 6 bytecode for that
toolchain rather than targeting Java 8 bytecode. The resulting wrapper runs on
Java 8. The package phase creates the shaded `target/legacywrapper-1.2.2.jar`;
do not publish the `original-` jar.

There is no Maven deployment configuration or automated release workflow in
this repository. The launcher downloads the shaded jar from the Technic static
library mirror. Publishing requires uploading it through the mirror's
administrative access to
`Technic/lib/net/technicpack/legacywrapper/1.2.2/legacywrapper-1.2.2.jar`,
then updating the launcher's version, download URL, SHA-1 and byte length.