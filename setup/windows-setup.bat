@echo off
setlocal

winget source update

:InstallPackage
set packageName=%1
echo Installing %packageName%
winget install %packageName% -e --accept-source-agreements --accept-package-agreements
if %errorlevel% neq 0 (
    echo Failed to install %packageName%
)
goto :eof

:: Install Ollama
call :InstallPackage Ollama.Ollama

:: Install Java 21
call :InstallPackage Oracle.JavaRuntimeEnvironment.21

:: Set JAVA_HOME environment variable
setx JAVA_HOME "C:\Program Files\Java\jdk-21"
set JAVA_HOME=C:\Program Files\Java\jdk-21

:: Add Java to PATH
setx PATH "%PATH%;%JAVA_HOME%\bin"
set PATH=%PATH%;%JAVA_HOME%\bin

:: Install Maven
call :InstallPackage Apache.Maven

:: Set MAVEN_HOME environment variable
setx MAVEN_HOME "C:\Program Files\Apache\maven"
set MAVEN_HOME=C:\Program Files\Apache\maven

:: Add Maven to PATH
setx PATH "%PATH%;%MAVEN_HOME%\bin"
set PATH=%PATH%;%MAVEN_HOME%\bin

:: Install Battle.net
call :InstallPackage Blizzard.BattleNet

:: Install Epic Games
call :InstallPackage EpicGames.EpicGamesLauncher

:: Install Docker
call :InstallPackage Docker.DockerDesktop

:: Install Git
call :InstallPackage Git.Git

:: Install GitHub CLI
call :InstallPackage GitHub.cli

echo Installation script completed.
endlocal
pause
