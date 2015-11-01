##############################################
##平台数据,需要更改
##############################################
PLATFORMHONE_CHILD=()
SDKDIR="game_xiaomi"
BUILDDIR="build_android"
LIBRARYDIR="nosdk_xiaomi"
TOOLSDIR="tools"
PlatformTarget="libcocos2dcpp"
# PlatformName="PLATFORM_XX"
PlatformGameName=(
"NAME_MYGAME"
)

if [ "$ALL" != "1" ] && [ "$ALL" != "0" ]; then
    source ../tools/platform_path.sh
    ISBUILD_SET="0"    
    ISREFRESHMK_SET="0"          
    ISCOPYRESOURCE_SET="0"           
    ISCOPYICON_SET="0"            
    ISPACKAGE_SET="0" 
    ##使用自定义设置
    PLATFORMHONE_CHILD=(
    #    "DEBUG"         ##编译模式
    )
fi

source platform_input.sh $*

source platform_param.sh