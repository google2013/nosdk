#!/bin/bash

# project & lib
# main android project path, you can change as you need
MAIN_PROJECT="main_project"
# lib project path, you can change as you need
LIB_PROJECT="lib_project"
ANT_LOG="./log.txt"

# dir

source "./platform_copy.sh"

#you can change the base dir for your need

ProjectName=`basename "$1"`
echo "  ant release2"
ProjectSourceName="sgz15_source"
ant clean
ant release >> $ANT_LOG
version="192"
#echo 2013weiwubaqi |
BUILDDATE=`date +%Y%m%d%H%M%S`
#&& [ "$ISPACKAGE_SIGNED" == "1" ]
if [ -e bin/${ProjectSourceName}-release-unsigned.apk ]; then
	if [ "${ProjectName}" == "sgz15_360" ]; then
		echo crimoon2013 | jarsigner -verbose -keystore ~/keystore/360_crimoon123/crimoonkey -signedjar bin/${ProjectName}_${version}_signed.apk bin/${ProjectSourceName}-release-unsigned.apk crimoon123
	elif [ "${ProjectName}" == "sgz15_wdj" ] || [ "${ProjectName}" == "sgz15_wdj" ]; then
		echo crimoon2013 | jarsigner -verbose -keystore ~/keystore/wdj/crimoonkey1234 -signedjar bin/${ProjectName}_${version}_signed.apk bin/${ProjectSourceName}-release-unsigned.apk crimoon
	else
		echo 2013weiwubaqi | jarsigner -verbose -keystore ~/keystore/key_sgz15 -signedjar bin/${ProjectName}_${version}_signed.apk bin/${ProjectSourceName}-release-unsigned.apk sgz15
	fi

	echo "$4/$version"	
	if [ -d "$4/$version" ]; then
		echo "----------"
		mv bin/${ProjectName}_${version}_signed.apk ~/Desktop/apk/${ProjectName}_${version}_${BUILDDATE}.apk
	else
		echo "---------==="
		#mkdir "$4"/$version
		mv bin/${ProjectName}_${version}_signed.apk ~/Desktop/apk/${ProjectName}_${version}_${BUILDDATE}.apk
	fi
else
	mv bin/${ProjectSourceName}-release-unsigned.apk ~/Desktop/apk/${ProjectName}_${version}_"unsiged"_${BUILDDATE}.apk
fi 
echo "打包"${ProjectName}"完成"