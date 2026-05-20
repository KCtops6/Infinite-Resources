@echo off
setlocal enabledelayedexpansion

:: Define the target directory path
set "TAGS_DIR=src\main\resources\data\forge\tags\items"

echo =======================================================
echo INFINITE RESOURCES: GENERATING COMPLETE FORGE TAG TREE
echo =======================================================

:: Clean out the old files if they exist to prevent duplicates/ghost data
if exist "%TAGS_DIR%" (
    echo [CLEAN] Removing old tag directory...
    rmdir /s /q "%TAGS_DIR%"
)
echo [INIT] Creating fresh tag directory...
mkdir "%TAGS_DIR%"

:: List of all 17 materials (Modded + Vanilla additions)
set "materials=constantan brass steel bronze electrum invar lead nickel silver tin zinc aluminum iron gold diamond emerald lapis"

:: List of the 4 structural item types
set "types=ingot nugget dust plate"

:: ---------------------------------------------------------
:: PHASE 1: Generate Specific Metal Tags (e.g., forge:ingots/iron)
:: ---------------------------------------------------------
for %%m in (%materials%) do (
    for %%t in (%types%) do (

        :: Handle standard Forge pluralization rules
        set "tagType=%%ts"
        if "%%t"=="dust" set "tagType=dusts"

        :: Create subfolders dynamically
        if not exist "%TAGS_DIR%\!tagType!" mkdir "%TAGS_DIR%\!tagType!"

        :: Define the target JSON file location
        set "FILE_PATH=%TAGS_DIR%\!tagType!\%%m.json"

        :: Write clean, Forge-compliant JSON data straight to the file
        echo {> "!FILE_PATH!"
        echo   "replace": false,>> "!FILE_PATH!"
        echo   "values": [>> "!FILE_PATH!"
        echo     "infinite_resources:%%m_%%t">> "!FILE_PATH!"
        echo   ]>> "!FILE_PATH!"
        echo }>> "!FILE_PATH!"
    )
)

:: ---------------------------------------------------------
:: PHASE 2: Generate Global Category Tags (e.g., forge:ingots)
:: ---------------------------------------------------------
echo [GLOBAL] Compiling category roots...

for %%t in (%types%) do (
    :: Handle pluralization for the master file names
    set "tagType=%%ts"
    if "%%t"=="dust" set "tagType=dusts"

    set "GLOBAL_FILE=%TAGS_DIR%\!tagType!.json"

    echo {> "!GLOBAL_FILE!"
    echo   "replace": false,>> "!GLOBAL_FILE!"
    echo   "values": [>> "!GLOBAL_FILE!"

    :: Loop through items to construct commas cleanly for valid JSON arrays
    set "first=true"
    for %%m in (%materials%) do (
        if "!first!"=="true" (
            echo     "infinite_resources:%%m_%%t">> "!GLOBAL_FILE!"
            set "first=false"
        ) else (
            echo     ,"infinite_resources:%%m_%%t">> "!GLOBAL_FILE!"
        )
    )

    echo   ]>> "!GLOBAL_FILE!"
    echo }>> "!GLOBAL_FILE!"
)

echo =======================================================
echo SUCCESS: Generated 68 metal sub-tags and 4 master lists!
echo Directory updated: %TAGS_DIR%
echo =======================================================
pause