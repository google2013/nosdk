PlatformDir="./"
Name="project.properties"
if [ -f "$Name" ]; then
rm -r "$Name"
fi

echo "target=android-18" > "$Name"
echo "android.library.reference.1=../../../cocos2dx/platform/android/java" >> "$Name"
echo "android.library.reference.2=../library_android/${LIBRARYDIR}" >> "$Name"

# mv "$Name" "./project.properties"