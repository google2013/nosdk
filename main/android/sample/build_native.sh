APPNAME="mygame"
SDK_ROOT="$HOME/Documents/program/android-sdk-macosx"
NDK_ROOT="$HOME/Documents/program/android-ndk-r10b"

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
APP_ROOT="$DIR/.."
APP_ANDROID_ROOT="$DIR"

echo "NDK_ROOT = $NDK_ROOT"
echo "APP_ROOT = $APP_ROOT"
echo "APP_ANDROID_ROOT = $APP_ANDROID_ROOT"

echo "#############################"
echo "$*"

if [[ "$buildexternalsfromsource" ]]; then
    echo "Building external dependencies from source"
    "$NDK_ROOT"/ndk-build -j4 -C "$APP_ANDROID_ROOT" "NDK_MODULE_PATH=${APP_ANDROID_ROOT}/../ui/"
else
    echo "Using prebuilt externals"
    "$NDK_ROOT"/ndk-build -j4 -C "$APP_ANDROID_ROOT" "NDK_MODULE_PATH=${APP_ANDROID_ROOT}/../ui/"
fi

if [ -d "$APP_ANDROID_ROOT"/backup/libs ]; then
    cp -R "$APP_ANDROID_ROOT"/backup/libs/ "$APP_ANDROID_ROOT"/libs/
fi