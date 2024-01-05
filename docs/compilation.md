# PROJECT COMPILATION

Enter the source folder where all project data is contained.

```sh
📦 project
 ┣ 📂 lib
 ┣ 📂 out
 ┣ 📂 resources
 ┗ 📂 src
```

Example:

```sh
cd project
```

## LINUX

### Lnx Compilation

```console
javac -cp lib/*:. -d out -sourcepath . src/Main.java \
src/entities/*.java \
src/entities/player/*.java \
src/entities/boss/*.java \
src/game/*.java \
src/interfaces/*.java \
src/items/*.java \
src/items/armor/*.java \
src/items/consumable/*java \
src/items/weapon/*.java \
src/scene/*.java \
src/scene/button/*.java \
src/utils/*.java \
src/exceptions/*.java;
```

### Lnx Execution

```console
java -cp lib/*:out:. Main
```

### Lnx Documentation

```console
javadoc -cp lib/*:. \
-d ../../../docs/doc \
-sourcepath . src/Main.java \
src/entities/*.java \
src/entities/player/*.java \
src/entities/boss/*.java \
src/game/*.java \
src/interfaces/*.java \
src/items/*.java \
src/items/armor/*.java \
src/items/consumable/*java \
src/items/weapon/*.java \
src/scene/*.java \
src/scene/button/*.java \
src/utils/*.java \
src/exceptions/*.java;
```

## Windows

### Win Compilation

```console
javac -cp lib/*;. -d out -sourcepath . src/Main.java \
src/entities/*.java \
src/entities/player/*.java \
src/entities/boss/*.java \
src/game/*.java \
src/interfaces/*.java \
src/items/*.java \
src/items/armor/*.java \
src/items/consumable/*java \
src/items/weapon/*.java \
src/scene/*.java \
src/scene/button/*.java \
src/utils/*.java \
src/exceptions/*.java;
```

### Win Execution

```console
java -cp lib/*;out;. Main
```
