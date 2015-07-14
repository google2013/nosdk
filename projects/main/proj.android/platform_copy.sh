
# copy resources
for file in ../projects_android/${LIBRARYDIR}/*
do
	echo "$file"
    if [ -e "${file}" ] && [ "${file}" != "backup" ]; then
        cp -rf  "$file" "./"
    fi
done

if [ -d ../projects_android/${LIBRARYDIR}/backup/assets ]; then
    cp -R ../projects_android/${LIBRARYDIR}/backup/assets/* ./assets/
fi


if [ -d ../projects_android/${LIBRARYDIR}/backup/libs ]; then
    cp -R ../projects_android/${LIBRARYDIR}/backup/libs/* ./libs/
fi
source ./platform_property.sh