# Features

## Library creation support
Choose a Play distribution directory in the file chooser and the plugin automatically adds appropriate
jars, source-roots to the library.

## Syntax highlighting
* Play template

# How to develop

Get source code of IntelliJ IDEA Community Edition
`git clone git://git.jetbrains.org/idea/community.git idea`

Check out the code with the same version as your IDEA.
I use CE 107.322 for runnning/debugging the plugin, so:
`cd idea`
`git checkout idea/107.322`

Add IDEA SDK.
As SDK contains the jars only, you should manually add the source code obtained above, to the SKD Library.

Useful plugins
* **JFlex Support**, for editing .flex files.
* **Markdown**, for editing this README :D

# How to build

Add the following plugins' jars to the module:

* Groovy (plugins/Groovy)
* Properties Support (plugins/properties)
* Ant Support (plugins/ant) with Ant (idea/lib/ant/lib/*.jar)
* IntelliLang (plugins/IntelliLang)
* Copyright (plugins/copyright)

Right click on the plugin's module, choose "Prepare Plugin Module '<module name here>' For Deployment",
then you will get the plugin's jar.

## A trouble shooting for JFlex Support plugin user

The JFlex support plugin automatically tries to compile .flex files while building the plugin.
If you get *JFlex home path is invalid* error trying to run/debug the plugin,
you should go **Settings > JFlex** and set proper path to JFlex.
Unfortunately, JFlex is not contained in IntelliJ IDEA binaries, but source code.

To find JFlex, you need to checkout IDEA's source code following the above `How to develop` section of this README.
Then, set both *Path to JFlex* and *Skeleton file* in Settings.
For me, a mac user, they were:

*Path to JFlex:*
`/Users/mumoshu/Sources/idea/tools/lexer/jflex-1.4`

*Skeleton file:*
`/Users/mumoshu/Sources/idea/tools/lexer/jflex-1.4`

`/Users/mumoshu/Sources/idea` is the source root.

# How to debug

Go to **"Run > Edit Configurations"** and click **"+"** in upper left of the window to open **"Add New Configuration"**,
then choose "Plugin".

You should add `-XX:MaxPermSize=512m` to *VM Parameters* not to get *"Out of memory error: PermGen"* while debugging.

Finally, go to **"Run > Run"** and choose the configuration you just added in above instruction.
An another instance of IDEA will run with the developing plugin installed.

# How to run tests

Select any test case class you want to run,
then go to **"Run > Run"** and choose the item with the same name as the test case class you have chosen.

# References

[Source for simple language plugin :: JetBrains Developer Community]
(http://devnet.jetbrains.net/message/5306403)

[codingbox/idea-parser3 - GitHub]
(https://github.com/codingbox/idea-parser3)

## List of IDEA's extention points

http://git.jetbrains.org/?p=idea/community.git;a=tree;f=platform/platform-resources/src/META-INF;h=7950420563a22639169abf772572e062a1184226;hb=HEAD

[Language Extention Points]
(http://git.jetbrains.org/?p=idea/community.git;a=blob;f=platform/platform-resources/src/META-INF/LangExtensionPoints.xml;hb=HEAD)

## Advices from Peter Gromov @ Jetbrains implementing Play Template language plugin

[JetBrains Developer Community: Example of a custom language plugin for...]
(http://devnet.jetbrains.com/thread/294172;jsessionid=0161DA9E6DEFD1658F4EA0BAC20D353C?decorator=print&displayFullThread=true)

## La Closure's parser is simple and can be reference implementation of a custom language parser

[How do I write a parser? :: JetBrains Developer Community]
(http://devnet.jetbrains.net/thread/280578)

## Jetbrain's official guide for plugin development

(http://confluence.jetbrains.net/display/IDEADEV/PluginDevelopment)

## Jetbrain's official FAQ for plugin development

(http://confluence.jetbrains.net/display/IDEADEV/Plugin+Development+FAQ)

## How to write plugin.xml

(http://www.jetbrains.com/idea/plugins/plugin_structure.html)

## Finally...

Google everything you want to know with "site:devnet.jetbrains.net".
