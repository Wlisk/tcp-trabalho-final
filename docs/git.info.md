# Git Command Line Info

## Create new branch

It creates and enter the new created branch

```sh
git checkout -b <new-branch-name>
```

## Switch branchs

Enter an existing branch

```sh
git checkout <branch-to-switch>
```

## Update Github repository

```sh
git add <directory/files>
git commit -m "some comment"
git push
```

## Update main branch from Github

Updates the self hosted main branch (computer) with updates from remote main branch from (Github)

```sh
git pull
```

## Update other branch from main branch in Github

Updates the self hosted [branch-name] (computer) with updates from remote main branch from (Github)

```sh
git checkout <branch-name>
git fetch origin
git merge origin/main
```
