#include <jni.h>
#include "julianwi_awtpeer_WindowActivity.h"

#include <android/native_window_jni.h>
#include <android/log.h>

#include <stdlib.h>
#include <stdio.h>

#include <sys/socket.h>
#include <sys/un.h>

JNIEXPORT jobject JNICALL Java_julianwi_awtpeer_WindowActivity_createsock
  (JNIEnv *env, jobject obj){
	int s, s2, t, len;
	struct sockaddr_un local, remote;
	jclass class_fdesc, class_sock;
	jmethodID const_fdesc, const_sock;
	jobject fdesc, sockimpl, ret;
	jfieldID field_fd;

	__android_log_print(ANDROID_LOG_INFO,"native test","jni called");
	if ((s = socket(AF_UNIX, SOCK_STREAM, 0)) == -1) {
		perror("socket");
		exit(1);
	}

	local.sun_family = AF_UNIX;
	strcpy(local.sun_path, "/data/data/julianwi.awtpeer/socket");
	unlink(local.sun_path);
	len = strlen(local.sun_path) + sizeof(local.sun_family);
	if (bind(s, (struct sockaddr *)&local, len) == -1) {
		perror("bind");
		exit(1);
	}

	if (listen(s, 5) == -1) {
		perror("listen");
		exit(1);
	}
	chmod(local.sun_path, 0777);

	__android_log_print(ANDROID_LOG_INFO,"native test","Waiting for a connection...");
	t = sizeof(remote);
	if ((s2 = accept(s, (struct sockaddr *)&remote, &t)) == -1) {
		perror("accept");
		exit(1);
	}

	__android_log_print(ANDROID_LOG_INFO,"native test","Connected.");

	class_fdesc = (*env)->FindClass(env, "java/io/FileDescriptor");
	if (class_fdesc == NULL) return NULL;

	// construct a new FileDescriptor
	const_fdesc = (*env)->GetMethodID(env, class_fdesc, "<init>", "()V");
	if (const_fdesc == NULL) return NULL;
	fdesc = (*env)->NewObject(env, class_fdesc, const_fdesc);
	// poke the "fd" field with the file descriptor
	field_fd = (*env)->GetFieldID(env, class_fdesc, "descriptor", "I");
	if (field_fd == NULL) return NULL;
	(*env)->SetIntField(env, fdesc, field_fd, s2);

	class_sock = (*env)->FindClass(env, "android/net/LocalSocketImpl");
	if (class_sock == NULL) return NULL;
	__android_log_print(ANDROID_LOG_INFO,"native test","found class");
	const_sock = (*env)->GetMethodID(env, class_sock, "<init>", "(Ljava/io/FileDescriptor;)V");
	if (const_sock == NULL) return NULL;
	__android_log_print(ANDROID_LOG_INFO,"native test","found descriptor");
	sockimpl = (*env)->NewObject(env, class_sock, const_sock, fdesc);

	class_sock = (*env)->FindClass(env, "android/net/LocalSocket");
	if (class_sock == NULL) return NULL;
	__android_log_print(ANDROID_LOG_INFO,"native test","found class");
	const_sock = (*env)->GetMethodID(env, class_sock, "<init>", "(Landroid/net/LocalSocketImpl;)V");
	if (const_sock == NULL) return NULL;
	__android_log_print(ANDROID_LOG_INFO,"native test","found descriptor");
	ret = (*env)->NewObject(env, class_sock, const_sock, sockimpl);

	// and return it
	return ret;
}
