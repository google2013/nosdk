PRE_DIR="com/crimoon/common/"

AndroidSRC=(
"WeiboManager.java"
"Util.java"
"PushService.java"
"PushMessageManager.java"
"ProjectTKApp.java"
"ProjectTK.java"
"JavaToCppHelper.java"
"CommonSdkManager.java"
"AccessTokenKeeper.java"
"WeChatSDkManager.java"
"VideoView.java"
)

echo "${SDKDIR}" 
echo "${SOURCEDIR}"

for file in "${SDKDIR}"/*
do
    if [ -d $file ]; then
        echo $file
        localfile=`basename $file`
        echo "rm -rf ./$localfile"
        echo `pwd`
        rm -rf ./$localfile
        cp -R "${SDKDIR}"/$localfile ./
        # source file_list.sh $1 $2
    elif [ -f $file ]; then
        echo $file
        # echo $file
        localfile=`basename $file`
        rm -rf ./$localfile
        cp -R "${SDKDIR}"/$localfile ./
    fi
done
rm -rf ./src
# rm -r ${ExternalDir}/*
mkdir -p "./src/$PRE_DIR"

for data in ${AndroidSRC[@]}
do

ISCOPY_TMP="1"
for fname in ${EXINCLUDE_SRC[@]}
do
	if [ "$data" == "$fname" ]; then
		echo "$data == $fname"
		ISCOPY_TMP=0
	fi
done

if [ "$ISCOPY_TMP" == "1" ]; then
 	cp "${SRCDIR}/${PRE_DIR}${data}" "./src/$PRE_DIR"
fi
done

rm -rf ./bin
source ./platform_property.sh