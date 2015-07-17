#!/bin/bash

#拷贝工程相关的文件
source "./platform_copy.sh"

#工程名
ProjectName=`basename "$1"`
#执行ant以后得到的包得名称，可以在build.xml处更改
ProjectSourceName="MyGame"

#清理打包数据
ant clean
#打包
ant release >> ./log.txt
version="051"
BUILDDATE=`date +%Y%m%d%H%M%S`

mkdir "$out_path"

if [ -e bin/${ProjectSourceName}-release-unsigned.apk ]; then
	echo mygame | jarsigner -verbose -keystore ~/keystore/keystore -signedjar bin/${ProjectName}_${version}_signed.apk bin/${ProjectSourceName}-release-unsigned.apk mygame
fi

mv bin/${ProjectName}_${version}_signed.apk "$out_path"/${ProjectName}_${version}_${BUILDDATE}.apk