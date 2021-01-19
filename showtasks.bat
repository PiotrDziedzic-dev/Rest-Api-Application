call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openBrowser
echo.
echo GRADLEW BUILD has errors – breaking work
goto fail

:openBrowser
google-chrome http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors
