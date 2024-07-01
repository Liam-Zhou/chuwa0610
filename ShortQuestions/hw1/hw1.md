# hw1

## 2. practice git using the platform. list the git commands you learned

- git commit
- git branch, git checkout 
- git merge
- git rebase

## 3. What is the basic steps to init a git repo in you local ?

- git bash
- git init

## 4. How to clone a repo from Github ?

- git clone [link]

## 5. How to create a new branch and checkout to that branch ?

``` 
git branch newBranch
git checkout -b newBranch
```

## 6. How to merge the branch_test to master branch in command ? show me the commands

```
git checkout master
git merge branch_test
```

## 7. How to **stash** your new code before leaving branch **branch_learn_stash** and pop your stash when you checkout back to branch_learn_stash ? try commands way and intellij way

```
git stash
giit checkout branch_learn_stash
git stash pop
```

## 8. How do you understand **PR is based on Branch**?

- When developing new functions, it's usually necessary to create a new branch to make changes

- changes from this branch need to be reviewed and potentially merged into another branch

## 9. What is **maven** role ? what it be used to do ?

Maven is a project management tool, can manage dependencies, build project, generate documentation and build lifecycle management.

## 10. What is the **lifecycle** of maven? could you tell me the details ?

```
validate: Check the project is correct and complete.
compile: Compile the source code.
test: Run unit tests.
package: Package the compiled code (e.g., into a JAR or WAR).
verify: Run checks on the package to ensure quality.
install: Install the package into the local repository.
deploy: Copy the package to a remote repository for sharing.
```

## 11. what is the difference between **package** and **install** in maven lifecycle 

- `package` phase creates the distributable artifact (e.g., JAR, WAR)
- `install` phase makes this artifact available in the local Maven repository for other projects to use

## 12.  What is **plugins** in maven, list some plugins

- plugins provide more specific functions for maven
- maven-compiler-plugin

