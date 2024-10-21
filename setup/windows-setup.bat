@echo off
echo Updating winget sources...
winget source update

echo Installing Ollama...
winget install Ollama.Ollama --silent || echo Failed to install Ollama

echo Installing Java 21...
winget install Oracle.JavaRuntimeEnvironment --version 21 --silent || echo Failed to install Java 21

echo Setting JAVA_HOME environment variable...
setx JAVA_HOME "C:\Program Files\Java\jre-21" /M
set "PATH=%PATH%;%JAVA_HOME%\bin"

echo Installing Maven...
winget install Apache.Maven --silent || echo Failed to install Maven

echo Setting MAVEN_HOME environment variable...
setx MAVEN_HOME "C:\Program Files\Apache\maven" /M
set "PATH=%PATH%;%MAVEN_HOME%\bin"

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

echo Script execution completed.
pause
