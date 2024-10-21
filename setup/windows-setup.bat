@echo off
echo Updating winget sources...
winget source update

echo Installing Logi Options+...
winget install Logitech.OptionsPlus --silent || echo Failed to install Logi Options+

:: Setup Java
echo Installing Java 21...
winget install Oracle.JDK.21 --silent || echo Failed to install Java 21

echo Installing Java Runtime Environment...
winget install Oracle.JavaRuntimeEnvironment --silent || echo Failed to install JRE

echo Setting JAVA_HOME environment variable...
setx JAVA_HOME "C:\Program Files\Java\jre-21" /M
set "PATH=%PATH%;%JAVA_HOME%\bin"

:: Setup Maven
echo Setting MAVEN_HOME environment variable...
setx MAVEN_HOME "C:\Program Files\Apache\maven" /M
set "PATH=%PATH%;%MAVEN_HOME%\bin"

echo Installing Ollama...
winget install Ollama.Ollama --silent || echo Failed to install Ollama

echo Installing IntelliJ IDEA Ultimate...
winget install JetBrains.IntelliJIDEA.Ultimate --silent || echo Failed to install IntelliJ IDEA Ultimate

echo Installing Battle.net...
winget install Blizzard.BattleNet --silent || echo Failed to install Battle.net

echo Installing Epic Games Launcher...
winget install EpicGames.EpicGamesLauncher --silent || echo Failed to install Epic Games Launcher

echo Installing Docker Desktop...
winget install Docker.DockerDesktop --silent || echo Failed to install Docker Desktop

echo Installing Git...
winget install Git.Git --silent || echo Failed to install Git

echo Installing GitHub CLI...
winget install GitHub.cli --silent || echo Failed to install GitHub CLI

echo installing RARLab.WinRAR
winget install RARLab.WinRAR --silent || echo Failed to install RARLab.WinRAR

echo Script execution completed.
pause