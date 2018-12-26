@echo off
rem ======================================================================
rem windows startup script
rem
rem ======================================================================

rem startup jar
java -jar ../boot/dubbo-monitor-assembly.jar --spring.config.location=../config/

pause