@ECHO OFF
setlocal

set SUPPORT_HOME=%~dp0
set CAMEL_OPENSHIFT_PRJ_NAME=camel-openshift
set DEPENDENCY_DIR=%SUPPORT_HOME%\deps

echo Removing existing camel-openshift project from deps folder

rmdir /s /q "%DEPENDENCY_DIR%\%CAMEL_OPENSHIFT_PRJ_NAME%"

cd "%DEPENDENCY_DIR%"

call git clone https://github.com/sabre1041/camel-openshift.git %{CAMEL_OPENSHIFT_PRJ_NAME%

cd "%CAMEL_OPENSHIFT_PRJ_NAME%"

call mvn clean install

cd "%SUPPORT_HOME%"

echo.
echo openshift-camel dependency successfully installed in local Maven repository
