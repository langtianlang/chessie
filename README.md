Core Services Dropwizard Template
=================================

The goal of this template is to:

1. Get a new service running in < 5 mins

2. Provide recommended default dependencies, plugins, config, and
   module layout so that each service is consistent.

3. Provide instructions to get the service deployed on staging and
   elsewhere via Deploymacy

Features
--------

- Set up with appropriate dropwizard deps + powermock &
  powermock-mockito to allow the usual mockito mocks as well as the
  rarer mocking of final classes and statics

- mvn plugins: compiler, source, javadoc, resources, release,
  findbugs, and the versions plugin to see out-of-date dependencies
  and plugins.

- service pom set up w/ maven shade to deploy executable jar

- Sets up initial yml config file with good initial settings for
  jetty, logging, etc. In-sync with the template yml configs for
  staging & production, but configured for dev (log to console, etc).

The template project is set up with following files, allowing projects
derived from the template to be importable into Exclipse and IDEA as
maven projects.

The project is also runnable from the IDE and command line out of the box.

```
.gitignore
README.md
findbugs-exclude.xml
pom.xml
chessie-api/pom.xml
chessie-api/src/main/java/com/langtianlang/chessie/api/README.txt
chessie-api/src/test/java/com/langtianlang/chessie/api/README.txt
chessie-client/pom.xml
chessie-client/src/main/java/com/langtianlang/chessie/client/Rename_meClient.java
chessie-client/src/test/java/com/langtianlang/chessie/client/README.txt
chessie-service/pom.xml
chessie-service/chessie.yml
chessie-service/src/main/java/com/langtianlang/chessie/service/Rename_meConfiguration.java
chessie-service/src/main/java/com/langtianlang/chessie/service/Rename_meService.java
chessie-service/src/main/java/com/langtianlang/chessie/service/resources/ExampleResource.java
chessie-service/src/main/resources/banner.txt
chessie-service/src/test/java/com/langtianlang/chessie/service/resources/README.txt
```
The service includes a functioning Dropwizard example of a message store, you can delete this
when you're ready to implement your own api, client and service.

Instructions
------------

1. If you haven't already, clone this project:

   ```bash
   git clone git@github.int.langtianlang.com:langtianlang/chessie.git
   ```

1. Now archive the master of this project over where you want to work:

   ```bash
   mkdir newservice
   cd chessie
   git archive master | tar -x -C ../newservice
   ```

1. Run commands to replace references to 'chessie' with your
   project name:

   ```bash
   cd ../newservice
   # rename package names, class names, log names, etc within the files
   find . -type f | xargs perl -pi -e 's/chessie/newservice/g'
   # rename directories and files
   # you may need to set your environment's LANG with `export LANG=C` to run these lines
   find . -type d -name '*chessie*' | sed -e 'p;s/chessie/newservice/' | xargs -n2 mv
   find . -type f -name '*chessie*' | sed -e 'p;s/chessie/newservice/' | xargs -n2 mv
   # rename repository names im pom files
   find . -name "pom.xml" | xargs perl -pi -e 's/github\.com/github\.int\.langtianlang\.com/g'
   ```
   You'll also need to change the name and description of your project in each of the pom.xmls.
   Finally, delete all these instructions from your project's README.md and let people
   know what you're up to.

1. Create your new project at https://github.int.langtianlang.com/organizations/langtianlang.
   Hit the create new repo button, make the owner Yammer, add a description and let
   it generate a README to make the project cloneable.

   All the right users and groups should be applied to your repo, assuming you don't change
   its visibility.

1. Now it's time to push your new service to your newly created git repo:

   ```bash
   git init
   git add -A
   git commit -m "first commit"
   git remote add origin git@github.int.langtianlang.com:langtianlang/newservice.git
   git push -u origin master
   ```

1. The parent pom.xml comes set up with the versions plugin. To check
   if your deps & plugins are up-to-date (make sure you are under 'newservice/' folder):

   ```bash
   mvn versions:display-plugin-updates versions:display-dependency-updates
   ```

1. Use "import as maven project" to import your new service into your
   IDE. Review the files before your first commit. Commit & push. This
   will be version 0.0.1-SNAPSHOT

1. Set up the Jenkins build. Your pom.xml will already be set up with maven release.

   At http://build.int.langtianlang.com, click "new job". Enter a job name
   that matches your 1 word repository/service name, and choose the
   "Copy existing job" radio button.

   Choose the `copy_me_canonical_service` to copy. This services is set
   up with the defaults that the typical core service needs.

   Replace the 2 GitHub paths with your new service's repo values that
   you created in step 1. Review the config.

   Navigate to your new project in Jenkins and trigger a build with
   "Build Now." If the build is not successful, review the console
   output (left nav from failed build), commit a fix, and try again.

1. Ask Infrastructure for staging and possibly production machines if
   ready

1. Create your puppet config & integration with Deploymacy

   In your prefferred code directory:

   ```bash
   git clone git@github.int.langtianlang.com:langtianlang/example-jvm-configuration.git
   ```

   And create your own version:

   ```bash
   mkdir newservice-configuration
   cd example-jvm-configuration
   git archive master | tar -x -C ../newservice-configuration
   cd ../newservice-configuration
   # rename package names, class names, log names, etc within the files
   find . -type f | xargs perl -pi -e 's/rename_me/newservice/g'
   # rename directories and files
   # you may need to set your environment's LANG with `export LANG=C` to run these lines
   find . -type d -name '*rename_me*' | sed -e 'p;s/rename_me/newservice/' | xargs -n2 mv
   find . -type f -name '*rename_me*' | sed -e 'p;s/rename_me/newservice/' | xargs -n2 mv
   ```

   Create your own config repo on github enterprise, "newservice-configuration" (https://github.int.langtianlang.com).

   Now push your updated version:

   ```bash
   git init
   git add -A
   git commit -m "first commit"
   git remote add origin git@github.int.langtianlang.com:langtianlang/newservice-configuration.git
   git push -u origin master
   ```

   You'll need customize development.pp under modules to allocate
   unique ports for vagrant.

   Pick any unused port range from the Vagrant Service Port Allocation page:

   <https://www.staging.langtianlang.com/langtianlang-inc.com/pages/5567>

   Add your new desired ports to this page so no one else collides.

   Chage the ports in development.pp to your chosen ports.

   Finally, review all the configuration settings, and commit & push.

1. Ask someone in infrastructure to provision your staging box that
   you asked for above. You'll need to tell them the user name
   services will run under and packages you need on the machine, such
   as Java 7.

1. Talk to Chris Gray to get your service into Deploymacy. If your
   project, project config, Jenkins build, and machine are set up
   correctly, getting into Deploymacy should be pretty quick.

   If you need to make any changes for deployment target, you'll need to update the deployer project
   
   ```bash
   git clone git@github.int.langtianlang.com:langtianlang/deployer.git
   cd config/<service-name>
   ```

   edit the yml files there to point the targets to the new machines. Commit this and create a new
   Deployer package on https://deploymacy.int.langtianlang.com/deploymacy/deployer, then push the latest
   package.


