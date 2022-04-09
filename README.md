# Flappy Bird 4K

## Inspiration

This is a rewrite of the classic game [Flappy Bird](https://en.wikipedia.org/wiki/Flappy_Bird).
Especially this code base is heavily inspired by [Flappy Bird in Java](https://github.com/BB-Store/FlappyBirdClone-LibGDX/blob/main/core/src/screens/GameScreen.java).

But I pretend that this version is easier to read as it is written in Kotlin and more OOP.

## Base

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/tommyettinger/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Gradle

This project uses [Gradle](http://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.

# Export to native executables

## MacOS X executable

1. Download OpenJDK 15 to the root folder of this project: [OpenJDK 15](https://www.oracle.com/java/technologies/javase/jdk15-archive-downloads.html).
2. Download `<pacr*.jar>` to the root folder of this project: [Packr](https://github.com/libgdx/packr).
3. Copy `export_mac.json.template` to `export.json` and replace `<OpenJDK15.tar.gz>` with the  path to your downloaded OpenJDK15.tar.gz file.
4. Run `gradle lwjgl3:jar` to build the application's runnable jar.
5. Run `java -jar <path to <pacr*.jar>> export_mac.json.json` to generate the Flappy.app macOS executable.

Instead of 4. and 5. you can also use `sudo bash export_macos_executable.sh`  to run the application.
NB: for that to work you need to modify line 5 so your local version of `<pacr*.jar>` is used.

6. After executing all the steps above, you can find the executable at `out-mac/Contents/MacOS/FlappyBird.app`.