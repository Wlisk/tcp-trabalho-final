# PROJECT COMPILATION

Enter the source folder where all project data is contained.

```sh
📦 project
 ┣ 📂 lib
 ┣ 📂 out
 ┣ 📂 resources
 ┗ 📂 src
```

ex: 
```sh
cd project
```

## LINUX

Compilation:

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
src/utils/exceptions/*.java;
```

Execution:

```console
java -cp lib/*:out:. Main
```

## Windows

Compilation:

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
src/utils/exceptions/*.java;
```

Execution:

```console
java -cp lib/*;out;. Main
```