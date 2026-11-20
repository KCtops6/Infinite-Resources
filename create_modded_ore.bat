@echo off
setlocal enabledelayedexpansion

:: Prompt for the material name
set /p MAT="Enter the new Modded Ore name (lowercase, e.g. platinum): "
if "%MAT%"=="" (
    echo Material name cannot be empty. Exiting.
    pause
    exit /b
)

set MODID=infinite_resources
set ASSETS=src\main\resources\assets\%MODID%
set DATA_MOD=src\main\resources\data\%MODID%
set DATA_FORGE=src\main\resources\data\forge\tags

echo Creating directories...
mkdir "%ASSETS%\blockstates" 2>nul
mkdir "%ASSETS%\models\block" 2>nul
mkdir "%ASSETS%\models\item" 2>nul
mkdir "%DATA_MOD%\loot_tables\blocks" 2>nul
mkdir "%DATA_FORGE%\blocks\storage_blocks" 2>nul
mkdir "%DATA_FORGE%\items\storage_blocks" 2>nul
mkdir "%DATA_FORGE%\items\ingots" 2>nul
mkdir "%DATA_FORGE%\items\nuggets" 2>nul
mkdir "%DATA_FORGE%\items\dusts" 2>nul
mkdir "%DATA_FORGE%\items\plates" 2>nul
mkdir "%DATA_FORGE%\items\double_plates" 2>nul
mkdir "%DATA_FORGE%\items\rods" 2>nul
mkdir "%DATA_FORGE%\items\wires" 2>nul
mkdir "%DATA_FORGE%\items\gears" 2>nul
mkdir "%DATA_FORGE%\items\raw_materials" 2>nul

echo Generating Blockstates...
echo {"variants": {"": {"model": "%MODID%:block/%MAT%_block"}}} > "%ASSETS%\blockstates\%MAT%_block.json"
echo {"variants": {"": {"model": "%MODID%:block/raw_%MAT%_block"}}} > "%ASSETS%\blockstates\raw_%MAT%_block.json"

echo Generating Block Models...
echo {"parent": "%MODID%:block/tinted_cube", "textures": {"all": "%MODID%:block/template_block_iron"}} > "%ASSETS%\models\block\%MAT%_block.json"
echo {"parent": "%MODID%:block/tinted_cube", "textures": {"all": "%MODID%:block/template_block_iron"}} > "%ASSETS%\models\block\%MAT%_block_iron.json"
echo {"parent": "%MODID%:block/tinted_cube", "textures": {"all": "%MODID%:block/template_block_gold"}} > "%ASSETS%\models\block\%MAT%_block_gold.json"
echo {"parent": "%MODID%:block/tinted_cube", "textures": {"all": "%MODID%:block/template_block_copper"}} > "%ASSETS%\models\block\%MAT%_block_copper.json"
echo {"parent": "%MODID%:block/tinted_cube", "textures": {"all": "%MODID%:block/template_block_raw"}} > "%ASSETS%\models\block\raw_%MAT%_block.json"

echo Generating Item Models...
echo {"parent": "%MODID%:block/%MAT%_block"} > "%ASSETS%\models\item\%MAT%_block.json"
echo {"parent": "%MODID%:block/raw_%MAT%_block"} > "%ASSETS%\models\item\raw_%MAT%_block.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_ingot"}} > "%ASSETS%\models\item\%MAT%_ingot.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_nugget_iron"}} > "%ASSETS%\models\item\%MAT%_nugget.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_nugget_copper"}} > "%ASSETS%\models\item\%MAT%_nugget_copper.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_nugget_gold"}} > "%ASSETS%\models\item\%MAT%_nugget_gold.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_dust"}} > "%ASSETS%\models\item\%MAT%_dust.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_plate"}} > "%ASSETS%\models\item\%MAT%_plate.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_double_plate"}} > "%ASSETS%\models\item\double_%MAT%_plate.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_rod"}} > "%ASSETS%\models\item\%MAT%_rod.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_wire"}} > "%ASSETS%\models\item\%MAT%_wire.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_gear"}} > "%ASSETS%\models\item\%MAT%_gear.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_raw_iron"}} > "%ASSETS%\models\item\raw_%MAT%.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_raw_copper"}} > "%ASSETS%\models\item\raw_%MAT%_copper.json"
echo {"parent": "minecraft:item/generated", "textures": {"layer0": "%MODID%:item/template_raw_gold"}} > "%ASSETS%\models\item\raw_%MAT%_gold.json"

echo Generating Loot Tables...
echo {"type": "minecraft:block", "pools": [{"rolls": 1.0, "bonus_rolls": 0.0, "entries": [{"type": "minecraft:item", "name": "%MODID%:%MAT%_block"}], "conditions": [{"condition": "minecraft:survives_explosion"}]}]} > "%DATA_MOD%\loot_tables\blocks\%MAT%_block.json"
echo {"type": "minecraft:block", "pools": [{"rolls": 1.0, "bonus_rolls": 0.0, "entries": [{"type": "minecraft:item", "name": "%MODID%:raw_%MAT%_block"}], "conditions": [{"condition": "minecraft:survives_explosion"}]}]} > "%DATA_MOD%\loot_tables\blocks\raw_%MAT%_block.json"

echo Generating Forge Tags...
echo {"replace": false, "values": ["%MODID%:%MAT%_block"]} > "%DATA_FORGE%\blocks\storage_blocks\%MAT%.json"
echo {"replace": false, "values": ["%MODID%:raw_%MAT%_block"]} > "%DATA_FORGE%\blocks\storage_blocks\raw_%MAT%.json"
echo {"replace": false, "values": ["%MODID%:%MAT%_block"]} > "%DATA_FORGE%\items\storage_blocks\%MAT%.json"
echo {"replace": false, "values": ["%MODID%:raw_%MAT%_block"]} > "%DATA_FORGE%\items\storage_blocks\raw_%MAT%.json"
echo {"replace": false, "values": ["%MODID%:%MAT%_ingot"]} > "%DATA_FORGE%\items\ingots\%MAT%.json"
echo {"replace": false, "values": ["%MODID%:%MAT%_nugget"]} > "%DATA_FORGE%\items\nuggets\%MAT%.json"
echo {"replace": false, "values": ["%MODID%:%MAT%_dust"]} > "%DATA_FORGE%\items\dusts\%MAT%.json"
echo {"replace": false, "values": ["%MODID%:%MAT%_plate"]} > "%DATA_FORGE%\items\plates\%MAT%.json"
echo {"replace": false, "values": ["%MODID%:double_%MAT%_plate"]} > "%DATA_FORGE%\items\double_plates\%MAT%.json"
echo {"replace": false, "values": ["%MODID%:%MAT%_rod"]} > "%DATA_FORGE%\items\rods\%MAT%.json"
echo {"replace": false, "values": ["%MODID%:%MAT%_wire"]} > "%DATA_FORGE%\items\wires\%MAT%.json"
echo {"replace": false, "values": ["%MODID%:%MAT%_gear"]} > "%DATA_FORGE%\items\gears\%MAT%.json"
echo {"replace": false, "values": ["%MODID%:raw_%MAT%"]} > "%DATA_FORGE%\items\raw_materials\%MAT%.json"

echo.
echo ========================================================
echo Successfully generated 37 JSON files for "%MAT%"!
echo ========================================================
echo.
echo REMINDER - Don't forget to manually update:
echo  1. ModItems.java (add "%MAT%" to MODDED_ORES)
echo  2. MaterialColors.java (add "%MAT%" hex color)
echo  3. en_us.json (add translation keys)
echo  4. Aggregated tag files (ingots.json, nuggets.json, storage_blocks.json, pickaxe.json, beacon_base_blocks.json, etc.)
echo.
pause