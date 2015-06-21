APPNAME="sgz15_wdj"
APP_ANDROID_NAME="net.crimoon.sgz15_wdj"
SDK_ROOT="$HOME/Downloads/android-sdk-macosx"
NDK_ROOT="$HOME/Downloads/android-ndk-r8d"

# options

buildexternalsfromsource=

usage(){
cat << EOF
usage: $0 [options]

Build C/C++ code for $APPNAME using Android NDK

OPTIONS:
-s	Build externals from source
-h	this help
EOF
}

while getopts "sh" OPTION; do
case "$OPTION" in
s)
buildexternalsfromsource=1
;;
h)
usage
exit 0
;;
esac
done

# paths

if [ -z "${NDK_ROOT+aaa}" ];then
echo "please define NDK_ROOT"
exit 1
fi

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
# ... use paths relative to current directory
COCOS2DX_ROOT="$DIR/../libs"
APP_ROOT="$DIR/.."
APP_ANDROID_ROOT="$DIR"

echo "NDK_ROOT = $NDK_ROOT"
echo "COCOS2DX_ROOT = $COCOS2DX_ROOT"
echo "APP_ROOT = $APP_ROOT"
echo "APP_ANDROID_ROOT = $APP_ANDROID_ROOT"

# make sure assets is exist
if [ -d "$APP_ANDROID_ROOT"/assets ]; then
    rm -rf "$APP_ANDROID_ROOT"/assets
fi

mkdir "$APP_ANDROID_ROOT"/assets

# copy resources
for file in "$APP_ROOT"/Resources/*
do
if [ -d "$file" ]; then
    cp -rf "$file" "$APP_ANDROID_ROOT"/assets
fi

if [ -f "$file" ]; then
   cp "$file" "$APP_ANDROID_ROOT"/assets
fi
done
 
cp "$APP_ANDROID_ROOT"/backbup/alipay_plugin_20130412msp.apk "$APP_ANDROID_ROOT"/assets



# copy icons (if they exist)
#file="$APP_ANDROID_ROOT"/assets/Icon-72.png
#if [ -f "$file" ]; then
#	cp "$file" "$APP_ANDROID_ROOT"/res/drawable-hdpi/icon.png
#fi
#file="$APP_ANDROID_ROOT"/assets/Icon-48.png
#if [ -f "$file" ]; then
#	cp "$file" "$APP_ANDROID_ROOT"/res/drawable-mdpi/icon.png
#fi
#file="$APP_ANDROID_ROOT"/assets/Icon-32.png
#if [ -f "$file" ]; then
#	cp "$file" "$APP_ANDROID_ROOT"/res/drawable-ldpi/icon.png
#fi
