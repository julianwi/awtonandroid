/*
 * ftpeer.c
 *
 *  Created on: Aug 8, 2014
 *      Author: julianwi
 */

#include <jni.h>
#include <stdio.h>
#include <ft2build.h>
#include FT_FREETYPE_H
#include "include/julianwi_awtpeer_FreetypeFontPeer.h"

FT_Library library;

JNIEXPORT jint JNICALL Java_julianwi_awtpeer_FreetypeFontPeer_InitFreeType
  (JNIEnv *env, jclass javaclass) {
	int error = FT_Init_FreeType( &library );
	return error;
}
