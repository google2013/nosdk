
if [ "$ALL" != "1" ] && [ "$ALL" != "0" ]; then
    SOURCEDIR="../../proj.android"
    SDKDIR="../project_android/$SDKDIR"
	BUILDDIR2="../$BUILDDIR"
    BUILDDIR="../$BUILDDIR/platform"
    TOOLSDIR="../$TOOLSDIR"
    ExternalDir="../auto_android_src_dir"
    SRCDIR="../proj.android/src/"
    ALL="0"
else
    SDKDIR="../project_android/$SDKDIR"
	BUILDDIR2="../$BUILDDIR"
    BUILDDIR="../$BUILDDIR"
    TOOLSDIR="./$TOOLSDIR"
    ExternalDir="../auto_android_src_dir"
    SRCDIR="../proj.android/src/"
    SOURCEDIR="../proj.android"
fi

if [ "$ALL" == "1" ]; then
    ##使用全局设置
    for data in ${PLATFORMHONG[@]}
    do
        PLATFORMHONE_CHILD+=(
        ${data}
        )
    done
fi

#####不用更改
PLATFORMHONE_CHILD+=(
"$PlatformName"   ##平台类型
)

for data in ${PlatformGameName[@]}
do
PLATFORMHONE_CHILD+=(
"$data"   ##平台类型
)
done

echo "#############################################"
echo -e "\033[31;1mbuild platform --$SDKDIR...\033[0m"

echo "############"
for data in ${PLATFORMHONE_CHILD[@]}
do
echo ${data}
done
echo "############"

source "$TOOLSDIR"/build_platform.sh "$SDKDIR" "$BUILDDIR" "$TOOLSDIR" "$BUILDDIR2" "$SOURCEDIR"