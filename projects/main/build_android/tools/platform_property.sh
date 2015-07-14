PlatformDir="./"
Name="project.properties"
if [ -f "$Name" ]; then
rm -r "$Name"
fi

echo "target=android-15" > "$Name"
echo "android.library.reference.1=../../../cocos2dx/platform/android/java" >> "$Name"
echo "android.library.reference.3=../projects_android/librarys_android/nosdk_library" >> "$Name"
echo "android.library.reference.2=../projects_android/librarys_android/${LIBRARYDIR}" >> "$Name"

# mv "$Name" "./project.properties"