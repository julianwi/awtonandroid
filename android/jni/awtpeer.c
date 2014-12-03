#include <jni.h>
#include "julianwi_awtpeer_WindowActivity.h"
#include "julianwi_awtpeer_ListenerOldApi.h"

#include <android/native_window_jni.h>
#include <android/log.h>

#include <stdlib.h>
#include <stdio.h>

#include <sys/socket.h>
#include <sys/un.h>

#include <pthread.h>

void *connection_handler(void *);

int sock;

JNIEXPORT jobject JNICALL Java_julianwi_awtpeer_WindowActivity_createsock
  (JNIEnv *env, jobject obj){
	int s, len;
	struct sockaddr_un local;
	jclass class_fdesc;
	jmethodID const_fdesc;
	jobject ret;
	jfieldID field_fd;

	__android_log_print(ANDROID_LOG_INFO,"native test","jni called");
	if ((s = socket(AF_UNIX, SOCK_STREAM, 0)) == -1) {
		perror("socket");
		return NULL;
	}

	local.sun_family = AF_UNIX;
	strcpy(local.sun_path, "/data/data/julianwi.awtpeer/socket");
	unlink(local.sun_path);
	len = strlen(local.sun_path) + sizeof(local.sun_family);
	if (bind(s, (struct sockaddr *)&local, len) == -1) {
		perror("bind");
		return NULL;
	}
	chmod(local.sun_path, 0777);

	class_fdesc = (*env)->FindClass(env, "java/io/FileDescriptor");
	if (class_fdesc == NULL) return NULL;

	// construct a new FileDescriptor
	const_fdesc = (*env)->GetMethodID(env, class_fdesc, "<init>", "()V");
	if (const_fdesc == NULL) return NULL;
	ret = (*env)->NewObject(env, class_fdesc, const_fdesc);
	// poke the "fd" field with the file descriptor
	field_fd = (*env)->GetFieldID(env, class_fdesc, "descriptor", "I");
	if (field_fd == NULL) return NULL;
	(*env)->SetIntField(env, ret, field_fd, s);

	// and return it
	return ret;
}

JNIEXPORT void JNICALL Java_julianwi_awtpeer_ListenerOldApi_listsocket
  (JNIEnv *env, jobject obj, jobject surface, jobject fd){
	ANativeWindow* window = ANativeWindow_fromSurface(env, surface);
	jclass class_fdesc = (*env)->FindClass(env, "java/io/FileDescriptor");
	if (class_fdesc == NULL) return;
	jfieldID field_fd = (*env)->GetFieldID(env, class_fdesc, "descriptor", "I");
	if (field_fd == NULL) return;
	sock = (*env)->GetIntField(env, fd, field_fd);
	pthread_t thread_id;
	if( pthread_create( &thread_id , NULL , connection_handler , window) < 0) {
		perror("could not create thread");
		return;
	}
}

void *connection_handler(void *socket_desc){
	ANativeWindow* window = (ANativeWindow*) socket_desc;
	int id;
	while(recv(sock , &id , 1 , 0) > 0) {
		if(id == 8){
			ANativeWindow_Buffer buffer;
			ANativeWindow_lock(window, &buffer, NULL);
			int var;
			for (var = 0; var < (&buffer)->height; ++var) {
				recv(sock, (&buffer)->bits+(var*(&buffer)->stride*4), (&buffer)->width*4, MSG_WAITALL);
			}
			ANativeWindow_unlockAndPost(window);
		}
	}
	exit(0);
	return 0;
}
