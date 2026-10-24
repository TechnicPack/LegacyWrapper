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

Publish through the authoritative `technic/mod-repo` repository on
`git.technicpack.net` (local checkout: `~/Repos/technic-mod-repo`), not by
uploading directly to the production mirror.

With Git LFS enabled, add the shaded jar at
`lib/net/technicpack/legacywrapper/1.2.2/legacywrapper-1.2.2.jar`, commit it,
and push to `main`. Follow that repository's contribution instructions and
wait for its deployment pipeline to succeed. The pipeline updates the mirror;
its R2 upload and cache-purge jobs apply only to `mods/`.

Download the published jar through HTTPS and verify its checksum and byte
length before updating the launcher's dependency pin. Never overwrite an
existing version with different bytes.