Set fso = CreateObject("Scripting.FileSystemObject")
Dim materials, types, baseDir
materials = Array("iron", "gold", "diamond", "emerald", "lapis", "steel", "brass", "bronze", "tin", "lead", "silver", "nickel", "aluminum", "zinc", "invar", "electrum", "constantan")
types = Array("ingot", "nugget", "dust", "plate")

' Automatically detects the folder you run it in
baseDir = fso.GetAbsolutePathName(".") & "\src\main\resources\assets\infinite_resources\models\item"

' Create the folders if they don't exist
If Not fso.FolderExists(fso.GetAbsolutePathName(".") & "\src") Then fso.CreateFolder(fso.GetAbsolutePathName(".") & "\src")
If Not fso.FolderExists(fso.GetAbsolutePathName(".") & "\src\main") Then fso.CreateFolder(fso.GetAbsolutePathName(".") & "\src\main")
If Not fso.FolderExists(fso.GetAbsolutePathName(".") & "\src\main\resources") Then fso.CreateFolder(fso.GetAbsolutePathName(".") & "\src\main\resources")
If Not fso.FolderExists(fso.GetAbsolutePathName(".") & "\src\main\resources\assets") Then fso.CreateFolder(fso.GetAbsolutePathName(".") & "\src\main\resources\assets")
If Not fso.FolderExists(fso.GetAbsolutePathName(".") & "\src\main\resources\assets\infinite_resources") Then fso.CreateFolder(fso.GetAbsolutePathName(".") & "\src\main\resources\assets\infinite_resources")
If Not fso.FolderExists(fso.GetAbsolutePathName(".") & "\src\main\resources\assets\infinite_resources\models") Then fso.CreateFolder(fso.GetAbsolutePathName(".") & "\src\main\resources\assets\infinite_resources\models")
If Not fso.FolderExists(baseDir) Then fso.CreateFolder(baseDir)

' Generate all 48 files
For Each mat In materials
    For Each t In types
        Dim fileName, fileContent
        fileName = baseDir & "\" & mat & "_" & t & ".json"
        fileContent = "{" & vbCrLf & _
                      "  ""parent"": ""minecraft:item/generated""," & vbCrLf & _
                      "  ""textures"": {" & vbCrLf & _
                      "    ""layer0"": ""infinite_resources:item/" & mat & "_" & t & """" & vbCrLf & _
                      "  }" & vbCrLf & _
                      "}"

        Set outFile = fso.CreateTextFile(fileName, True)
        outFile.Write fileContent
        outFile.Close
    Next
Next

MsgBox "Successfully generated all 48 item model JSON files!", 64, "Done!"