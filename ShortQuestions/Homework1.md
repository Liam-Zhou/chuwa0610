# Homework 1

### Leo Wang, 2024-06-10

#### 2. Practice git using the platform. list the git commands you learned
```
git add .
git add [file_name]
git commit -m 'commit messages'
git branch
git status
git checkout -b [your_branch_name]
git git clone <url>
git rebase [branch]
git remote -v
git stash
git stash pop
git pull <remote>
git push <remote> <branch>
```

#### 3. What is the basic steps to init a git repo in you local?
```
git init
git add .
git commit -m 'repo init'
```
#### 4. How to clone a repo from github?
```
cd working_directory
git clone <repo-url>
```
#### 5. How to create a new branch and checkout to that branch
```
git checkout -b my_new_branch
```
#### 6. How to merge the branch_test to master branch in command?
```
git checkout master
git pull origin master
git merge branch_test 
```
#### 7. How to stash your new code before leaving branch branch_learn_stash and pop your stash when you checkout back to branch_learn_stash? try commands way and intellij way.
```
git stash
git checkout new_feature_branch
git checkout branch_learn_stash
git stash pop
```
#### 8. How do you understand PR is based on Branch?
That is coopreated work from different developers, each feature or change of software has its own developed process under certain branch. After development done, PR is raised and code reivew to merge to main software.
#### 9. What is Maven role? What it be used to?
Maven is a powerful project management and comprehension tool that provides a way to manage a project's build, reporting, and documentation from a central piece of information called the Project Object Model (POM). It is primarily used for Java projects.
- Project Build Management
- Dependency Management
- Project Lifecyle Management
- Project Configuration & Management
- Plugin Support
- Integration with CI/CD
#### 10. What is the lifecycle of Maven? Could you say the details?
The Maven lifecycle is a series of phases that define the steps involved in building and deploying a Maven project. Each phase represents a stagein the building process, from compiling the code to deploying the final package. By defining a standard lifecycle, Maven ensures that the projects are built in a consistent and repeatable manner.
- Prepare resources: Resource copying
    - Resource copying can be customized in this phase
- Validate: Validating the information
    - Validates if the project is correct and if all necessary information is available
- Compile
    - Source code compliation is done in this phase
- Test
    - Test the complied source code suitable for the teting framework
- Package
    - This phase creates the JAR/WAR package as mentioned in the packaging in POM.xml
- Install
    - This ohase installs the package in local/remote maven repository
- Deploy 
    - Copies the final package to the remote repository
#### 11. What is the difference between package and install in maven lifecycle ?
- Package: Creates the final artifact (JAR) and places it in the target directory
- Install: Creates the final artifact and places it in the local Maven repo (~/.m2/repository), making it available for other local projects.
#### 12. What is plugins in maven, list some plugins.
In Maven, plugins are the primary means of extending the build system. They provide additional functionalities to the Maven build process, such as compiling code, running tests, creating JAR files, and deploying artifacts. Plugins are typically configured in the pom.xml file and can be used to execute specific tasks during different phases of the Maven lifecycle.
- maven-compiler-plugin: Compiles the project’s source code.
- maven-surefire-plugin: Runs unit tests.
- maven-jar-plugin: Creates JAR files.
- maven-war-plugin: Packages web applications as WAR files.
- maven-install-plugin: Installs the artifact in the local repository.
- maven-deploy-plugin: Deploys the artifact to a remote repository.
- maven-site-plugin: Generates project documentation.
