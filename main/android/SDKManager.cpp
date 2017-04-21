#include "SDKManager.h"
#include "JavaHelper.h"
static SDKManager* instance = NULL;

SDKManager::SDKManager()
: packageName("com.sdk.common")
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
	JavaHelper::getInstance()->callStringFunction1("init", param.c_str());
}

void SDKManager::login(string param)
{
	JavaHelper::getInstance()->callStringFunction1("login", param.c_str());
}

void SDKManager::switchAccount(string param)
{
	JavaHelper::getInstance()->callStringFunction1("switchAccount", param.c_str());
}

void SDKManager::logout(string param)
{
	JavaHelper::getInstance()->callStringFunction1("logout", param.c_str());
}

void SDKManager::pay(string param)
{
	JavaHelper::getInstance()->callStringFunction1("pay", param.c_str());
}

void SDKManager::exit(string param)
{
	JavaHelper::getInstance()->callStringFunction1("exit", param.c_str());
}

void SDKManager::sendExtraData(string data)
{
	JavaHelper::getInstance()->callStringFunction1("sendExtraData", data.c_str());
}

const char* SDKManager::getChannel()
{
	if (channelLabel.length() != 0)
	{
		return channelLabel.c_str();
	}

	channelLabel = JavaHelper::getInstance()->callStringFunction2("getChannel");
}

void SDKManager::onLogout(string result)
{

}

void SDKManager::onLoginResult(string result)
{

}

void SDKManager::onPayResult(string result)
{

}

extern "C" 
{
void Java_com_sdk_common_SDKManager_onInit(JNIEnv* env, jobject thiz, jstring param)
{
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