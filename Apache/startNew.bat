@echo off
title Apache 2.2.17 Portable Running...
setlocal

rem change working directory
set APACHE_HOME=D:\icipFrameWork\tools\Apache\apache-2.2.17
set APACHE_PRJOECT_HOME=D:\icipFrameWork\workspace\Apache

cd %APACHE_HOME%\bin

rem change conf
xcopy /H /R /Y /Q %APACHE_PRJOECT_HOME%\conf\*.* %APACHE_HOME%\conf

echo Conf Files changed.
echo .
echo Starting Apache
cd %APACHE_HOME%
%APACHE_HOME%\bin\httpd

endlocal