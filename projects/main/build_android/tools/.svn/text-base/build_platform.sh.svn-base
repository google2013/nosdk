
#执行刷新mk，编译，拷贝资源

cp "$3"/build_native.sh "$5"/
cp "$3"/resource_copy.sh "$5"/
cp "$3"/platform_refresh.sh "$5"/
cp "$3"/platform_package.sh "$5"/
cp "$3"/platform_copy.sh "$5"/
cp "$3"/icon_copy.sh "$5"/
cp "$3"/platform_property.sh "$5"/
cd "$5"

#### 注意 == 两边必须有空格
#刷新Android.mk
if [ "${ISREFRESHMK_SET}" == "1" ]; then
    if [ -f "./platform_refresh.sh" ]; then
        echo -e "\033[31;1m开始刷新 Android.mk\033[0m"
        source "./platform_refresh.sh" >> null
    fi
fi

#编译
if [ "${ISBUILD_SET}" == "1" ]; then
    if [ -f "./build_native.sh" ]; then
        echo -e "\033[31;1m开始编译libcocos2dcpp.so\033[0m"
        source "./build_native.sh" >> null
    fi
fi

#拷贝资源
if [ "${ISCOPYRESOURCE_SET}" == "1" ]; then
    if [ -f "./resource_copy.sh" ]; then
        echo -e "\033[31;1m开始拷贝资源\033[0m"
        source "./resource_copy.sh" >> null
    fi
fi

#打包
if [ "${ISPACKAGE_SET}" == "1" ]; then
	if [ -f "./platform_package.sh" ]; then
		echo -e "\033[31;1m开始打包\033[0m"
        source "./platform_package.sh" "$1" "$2" "$3" "$4" "$5" >> null
	fi
fi

if [ "${ISCOPYICON_SET}" == "1" ]; then
    if [ -f "./icon_copy.sh" ]; then
        echo -e "\033[31;1m开始拷贝icon\033[0m"
        source "./icon_copy.sh" "$1" "$2" "$3" "$4" "$5" >> null
    fi
fi

# source "./icon_copy.sh" "$1" "$2" "$3" "$4"

echo -e "\033[31;1m$SDKDIR build finished \033[0m"
echo "#############################################"
cd "$2"

