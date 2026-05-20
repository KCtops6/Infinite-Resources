@echo off
setlocal enabledelayedexpansion

:: Define materials
set materials=steel brass bronze tin lead silver nickel aluminum zinc invar electrum constantan

:: Get the current folder path where the script is run
set "base_dir=%~dp0src\main\resources\data\forge\tags\items"

echo Generating 48 sub-tag JSON files...

for %%m in (%materials%) do (
    :: 1. Ingots Sub-tag
    mkdir "%base_dir%\ingots" 2>nul
    (
        echo {
        echo   "replace": false,
        echo   "values": [
        echo     "infinite_resources:%%m_ingot"
        echo   ]
        echo }
    ) > "%base_dir%\ingots\%%m.json"

    :: 2. Nuggets Sub-tag
    mkdir "%base_dir%\nuggets" 2>nul
    (
        echo {
        echo   "replace": false,
        echo   "values": [
        echo     "infinite_resources:%%m_nugget"
        echo   ]
        echo }
    ) > "%base_dir%\nuggets\%%m.json"

    :: 3. Dusts Sub-tag
    mkdir "%base_dir%\dusts" 2>nul
    (
        echo {
        echo   "replace": false,
        echo   "values": [
        echo     "infinite_resources:%%m_dust"
        echo   ]
        echo }
    ) > "%base_dir%\dusts\%%m.json"

    :: 4. Plates Sub-tag
    mkdir "%base_dir%\plates" 2>nul
    (
        echo {
        echo   "replace": false,
        echo   "values": [
        echo     "infinite_resources:%%m_plate"
        echo   ]
        echo }
    ) > "%base_dir%\plates\%%m.json"
)

echo Done! Generated all sub-folders and JSON files.
pause