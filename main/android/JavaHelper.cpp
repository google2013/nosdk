#include <stdio.h>
#include "JavaHelper.h"

#define  LOG_TAG    "JavaHelper"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)

static JavaHelper* instance = NULL;

JavaHelper::JavaHelper()
: jniEnv(NULL)
, packageName("")
{

}

JavaHelper::~JavaHelper()
{

}

JavaHelper* JavaHelper::getInstance()
{
	if (instance == NULL)
	{
		instance = new JavaHelper();
	}

	return instance;
}

void JavaHelper::destroyInstance()
{
	if (instance != NULL)
	{
		delete instance;
		instance = NULL;
	}
}

void JavaHelper::setJNIEnv(JNIEnv* env)
{
	jniEnv = env;
}

JNIEnv* JavaHelper::getJNIEnv()
{
	return jniEnv;
}

void JavaHelper::setPackageName(const char* name)
{
	packageName = name;
}

void JavaHelper::callStringFunction1(const char* functionName, const char* data)
{
    jclass classID = jniEnv->FindClass(packageName.c_str());
    jmethodID getInstanceMethodID = jniEnv->GetStaticMethodID(classID, "getInstance", "()Lcom/sdk/common/SDKManager;");
    jobject sdkManager = jniEnv->CallStaticObjectMethod(classID, getInstanceMethodID);
    jmethodID functionMethodID = jniEnv->GetMethodID(classID, functionName, "(Ljava/lang/String;)V");
    jniEnv->CallObjectMethod(sdkManager, functionMethodID, jniEnv->NewStringUTF(data));
}

const char* JavaHelper::callStringFunction2(const char* functionName)
{
    jclass classID = jniEnv->FindClass(packageName.c_str());
    jmethodID getInstanceMethodID = jniEnv->GetStaticMethodID(classID, "getInstance", "()Lcom/sdk/common/SDKManager;");
    jobject sdkManager = jniEnv->CallStaticObjectMethod(classID, getInstanceMethodID);
    jmethodID functionMethodID = jniEnv->GetMethodID(classID, functionName, "()Ljava/lang/String;");
    jstring jresult = (jstring)jniEnv->CallObjectMethod(sdkManager, functionMethodID);
    return jniEnv->GetStringUTFChars(jresult, NULL);
}

void JavaHelper::callVoidFunction(const char* functionName)
{
    jclass classID = jniEnv->FindClass(packageName.c_str());
    jmethodID getInstanceMethodID = jniEnv->GetStaticMethodID(classID, "getInstance", "()Lcom/sdk/common/SDKManager;");
    jobject sdkManager = jniEnv->CallStaticObjectMethod(classID, getInstanceMethodID);
    jmethodID functionMethodID = jniEnv->GetMethodID(classID, functionName, "()V");
    jniEnv->CallVoidMethod(sdkManager, functionMethodID);
}

bool JavaHelper::callBoolFunction(const char* functionName)
{
    jclass classID = jniEnv->FindClass(packageName.c_str());
    jmethodID getInstanceMethodID = jniEnv->GetStaticMethodID(classID, "getInstance", "()Lcom/sdk/common/SDKManager;");
    jobject sdkManager = jniEnv->CallStaticObjectMethod(classID, getInstanceMethodID);
    jmethodID functionMethodID = jniEnv->GetMethodID(classID, functionName, "()Z");
    return jniEnv->CallBooleanMethod(sdkManager, functionMethodID);
}

int JavaHelper::callIntFunction(const char* functionName)
{
    jclass classID = jniEnv->FindClass(packageName.c_str());
    jmethodID getInstanceMethodID = jniEnv->GetStaticMethodID(classID, "getInstance", "()Lcom/sdk/common/SDKManager;");
    jobject sdkManager = jniEnv->CallStaticObjectMethod(classID, getInstanceMethodID);
    jmethodID functionMethodID = jniEnv->GetMethodID(classID, functionName, "()I");
    return jniEnv->CallIntMethod(sdkManager, functionMethodID);
}

string JavaHelper::jstring2string(jstring jstr)
{
    const char* chars = jniEnv->GetStringUTFChars(jstr, NULL);
    string ret(chars);
    jniEnv->ReleaseStringUTFChars(jstr, chars);

    return ret;
}