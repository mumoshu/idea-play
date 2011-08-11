# Features

## Library creation support
Choose a Play distribution directory in the file chooser and the plugin automatically adds appropriate
jars, source-roots to the library.

## Syntax highlighting
* Play template

# How to build

Add the following plugins' jars to the module:
* Groovy (plugins/Groovy)
* Properties Support (plugins/properties)
* Ant Support (plugins/ant) with Ant (idea/lib/ant/lib/*.jar)
* IntelliLang (plugins/IntelliLang)
* Copyright (plugins/copyright)

# How to debug

Go to "Run > Edit Configurations" and click '+' in upper left of the window to open "Add New Configuration",
then choose "Plugin".

You should add "-XX:MaxPermSize=512m" to "VM Parameters" not to get "Out of memory error: PermGen" while debugging.

Finally, go to "Run > Run" and choose the configuration you just added in above instruction.
An another instance of IDEA will run with the developing plugin installed.

# How to run tests

Select any test case class you want to run,
then go to "Run > Run" and choose the item with the same name as the test case class you have chosen.

# References

Source for simple language plugin :: JetBrains Developer Community
http://devnet.jetbrains.net/message/5306403

codingbox/idea-parser3 - GitHub
https://github.com/codingbox/idea-parser3
