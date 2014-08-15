/*
 * ftpeer.c
 *
 *  Created on: Aug 8, 2014
 *      Author: julianwi
 */

#include <jni.h>
#include <stdio.h>
#include <stdint.h>
#include <ft2build.h>
#include FT_FREETYPE_H
//#include FT_ERROR_DEFINITIONS_H
#include "include/julianwi_awtpeer_FreetypeFontPeer.h"

FT_Library library;

JNIEXPORT jint JNICALL Java_julianwi_awtpeer_FreetypeFontPeer_initFreeType
  (JNIEnv *env, jclass javaclass) {
	int error = FT_Init_FreeType( &library );
	return error;
}

JNIEXPORT jlong JNICALL Java_julianwi_awtpeer_FreetypeFontPeer_createfont
  (JNIEnv *env, jobject javaobject) {
	void *ptr = malloc (sizeof (FT_Face));
	return (intptr_t) ptr;
}

JNIEXPORT jint JNICALL Java_julianwi_awtpeer_FreetypeFontPeer_initFont
  (JNIEnv *env, jobject javaobject, jlong font, jstring file) {
	const char *filepath = (*env)->GetStringUTFChars(env, file, 0);
	int error = FT_New_Face( library, filepath, 0, (FT_Face*) (intptr_t) font );
	(*env)->ReleaseStringUTFChars(env, file, filepath);
	return error;
}

JNIEXPORT jint JNICALL Java_julianwi_awtpeer_FreetypeFontPeer_setsize
  (JNIEnv *env, jobject javaobject, jlong font, jint size) {
	int error = error = FT_Set_Pixel_Sizes( *(FT_Face*) (intptr_t) font, 0, size );
	return error;
}
