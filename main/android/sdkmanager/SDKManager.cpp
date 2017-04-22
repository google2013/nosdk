#include "SDKManager.h"
#include "JavaHelper.h"
#include <android/log.h>

#define  LOG_TAG    "JavaHelper"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)

static SDKManager* instance = NULL;

SDKManager::SDKManager()
: packageName("com/sdk/common/SDKManager")
, channelLabel("")
{
	JavaHelper::getInstance()->setPackageName(packageName.c_str());
}

SDKManager::~SDKManager()
{

}

SDKManager* SDKManager::getInstance()
{
	if (instance == NULL)
	{
		instance = new SDKManager();
	}

	return instance;
}

void SDKManager::init(string param)
{
	LOGD("SDKManager::init");
	JavaHelper::getInstance()->callStringFunction1("init", param.c_str());
}

void SDKManager::login(string param)
{
	LOGD("SDKManager::login");
	JavaHelper::getInstance()->callStringFunction1("login", param.c_str());
}

void SDKManager::switchAccount(string param)
{
	LOGD("SDKManager::switchAccount");
	JavaHelper::getInstance()->callStringFunction1("switchAccount", param.c_str());
}

void SDKManager::logout(string param)
{
	LOGD("SDKManager::logout");
	JavaHelper::getInstance()->callStringFunction1("logout", param.c_str());
}

void SDKManager::pay(string param)
{
	LOGD("SDKManager::pay");
	JavaHelper::getInstance()->callStringFunction1("pay", param.c_str());
}

void SDKManager::exit(string param)
{
	LOGD("SDKManager::exit");
	JavaHelper::getInstance()->callStringFunction1("exit", param.c_str());
}

void SDKManager::sendExtraData(string data)
{
	LOGD("SDKManager::sendExtraData");
	JavaHelper::getInstance()->callStringFunction1("sendExtraData", data.c_str());
}

const char* SDKManager::getChannel()
{
	LOGD("SDKManager::getChannel %s", channelLabel.c_str());
	
	if (channelLabel.length() != 0)
	{
		return channelLabel.c_str();
	}

	channelLabel = JavaHelper::getInstance()->callStringFunction2("getChannel");
}

void SDKManager::onLogout(string result)
{
	LOGD("SDKManager::onLogout");
}

void SDKManager::onLoginResult(string result)
{
	LOGD("SDKManager::onLoginResult");
}

void SDKManager::onPayResult(string result)
{
	LOGD("SDKManager::onPayResult");
}

extern "C" 
{
void Java_com_sdk_common_SDKManager_onInit(JNIEnv* env, jobject thiz, jstring param)
{
	LOGD("SDKManager::onInit");
	JavaHelper::getInstance()->setJNIEnv(env);
}

void Java_com_sdk_common_SDKManager_onLogout(JNIEnv* env, jobject thiz,jstring param)
{
	std::string str = JavaHelper::getInstance()->jstring2string(param);
	SDKManager::getInstance()->onLogout(str);
}

void Java_com_sdk_common_SDKManager_onLoginResult(JNIEnv* env, jobject thiz, jstring param)
{
	std::string str = JavaHelper::getInstance()->jstring2string(param);
    SDKManager::getInstance()->onLoginResult(str);
}

void Java_com_sdk_common_SDKManager_onPayResult(JNIEnv* env, jobject thiz, jstring param)
{
	std::string str = JavaHelper::getInstance()->jstring2string(param);
    SDKManager::getInstance()->onPayResult(str);
}
}