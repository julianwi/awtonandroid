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
#include FT_GLYPH_H
#include FT_OUTLINE_H
//#include FT_ERROR_DEFINITIONS_H
#include "include/julianwi_awtpeer_FreetypeFontPeer.h"
#include "include/julianwi_awtpeer_FreetypeGlyphVector.h"

FT_Library library;

typedef struct gp
{
  JNIEnv *env;
  jobject obj;
  double px;
  double py;
  double sx;
  double sy;
} generalpath ;

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

JNIEXPORT jint JNICALL Java_julianwi_awtpeer_FreetypeGlyphVector_getglyphindex
  (JNIEnv *env, jobject javaobject, jlong font, jint character){
	int glyph_index = FT_Get_Char_Index( *(FT_Face*) (intptr_t) font, character );
	return glyph_index;
}

/* GetOutline code follows ****************************/
/********* Freetype callback functions *****************************/

static int _moveTo( const FT_Vector* to,
		    void *p)
{
  JNIEnv *env;
  jobject obj;
  jclass cls;
  jmethodID method;
  jvalue values[2];
  generalpath *path = (generalpath *) p;

  env = path->env;
  obj = path->obj;

  values[0].f = (jfloat)(to->x * path->sx + path->px);
  values[1].f = (jfloat)(to->y * path->sy + path->py);

  cls = (*env)->FindClass (env, "java/awt/geom/GeneralPath");
  method = (*env)->GetMethodID (env, cls, "moveTo", "(FF)V");
  (*env)->CallVoidMethodA(env, obj, method, values );

  return 0;
}

static int _lineTo( const FT_Vector*  to,
		    void *p)
{
  JNIEnv *env;
  jobject obj;
  jclass cls;
  jmethodID method;
  jvalue values[2];
  generalpath *path = (generalpath *) p;

  env = path->env;
  obj = path->obj;
  values[0].f = (jfloat)(to->x * path->sx + path->px);
  values[1].f = (jfloat)(to->y * path->sy + path->py);

  cls = (*env)->FindClass (env, "java/awt/geom/GeneralPath");
  method = (*env)->GetMethodID (env, cls, "lineTo", "(FF)V");
  (*env)->CallVoidMethodA(env, obj, method, values );

  return 0;
}

static int _quadTo( const FT_Vector*  cp,
		    const FT_Vector*  to,
		    void *p)
{
  JNIEnv *env;
  jobject obj;
  jclass cls;
  jmethodID method;
  jvalue values[4];
  generalpath *path = (generalpath *) p;

  env = path->env;
  obj = path->obj;
  values[0].f = (jfloat)(cp->x * path->sx + path->px);
  values[1].f = (jfloat)(cp->y * path->sy + path->py);
  values[2].f = (jfloat)(to->x * path->sx + path->px);
  values[3].f = (jfloat)(to->y * path->sy + path->py);

  cls = (*env)->FindClass (env, "java/awt/geom/GeneralPath");
  method = (*env)->GetMethodID (env, cls, "quadTo", "(FFFF)V");
  (*env)->CallVoidMethodA(env, obj, method, values );

  return 0;
}

static int _curveTo( const FT_Vector*  cp1,
		     const FT_Vector*  cp2,
		     const FT_Vector*  to,
		     void *p)
{
  JNIEnv *env;
  jobject obj;
  jclass cls;
  jmethodID method;
  jvalue values[6];
  generalpath *path = (generalpath *) p;

  env = path->env;
  obj = path->obj;
  values[0].f = (jfloat)(cp1->x * path->sx + path->px);
  values[1].f = (jfloat)(cp1->y * path->sy + path->py);
  values[2].f = (jfloat)(cp2->x * path->sx + path->px);
  values[3].f = (jfloat)(cp2->y * path->sy + path->py);
  values[4].f = (jfloat)(to->x * path->sx + path->px);
  values[5].f = (jfloat)(to->y * path->sy + path->py);

  cls = (*env)->FindClass (env, "java/awt/geom/GeneralPath");
  method = (*env)->GetMethodID (env, cls, "curveTo", "(FFFFFF)V");
  (*env)->CallVoidMethodA(env, obj, method, values );

  return 0;
}

JNIEXPORT jobject JNICALL Java_julianwi_awtpeer_FreetypeGlyphVector_getGlyphOutlineNative
  (JNIEnv *env, jobject javaobject, jlong font, jint glyphindex)
{
  generalpath *path;
  jobject gp;
  FT_Outline_Funcs ftCallbacks =
    {
      (FT_Outline_MoveToFunc) _moveTo,
      (FT_Outline_LineToFunc) _lineTo,
      (FT_Outline_ConicToFunc) _quadTo,
      (FT_Outline_CubicToFunc) _curveTo,
      0,
      0
    };
  FT_Face ft_face;
  FT_Glyph glyph;

  ft_face = *(FT_Face*) (intptr_t) font;

  path = malloc (sizeof (generalpath));
  path->env = env;

  path->px = path->py = 0.0;
  path->sx = 1.0/64.0;
  path->sy = -1.0/64.0;

  {  /* create a GeneralPath instance */
    jclass cls;
    jmethodID method;

    cls = (*env)->FindClass (env, "java/awt/geom/GeneralPath");
    method = (*env)->GetMethodID (env, cls, "<init>", "()V");
    gp = path->obj = (*env)->NewObject (env, cls, method);
  }

  if(FT_Load_Glyph(ft_face,
		   (FT_UInt)(glyphindex),
		   FT_LOAD_DEFAULT | FT_LOAD_NO_BITMAP) != 0)
    {
      free(path);
      return NULL;
    }

  FT_Get_Glyph( ft_face->glyph, &glyph );
  if (glyph->format == FT_GLYPH_FORMAT_OUTLINE)
    {
	  FT_Outline_Decompose (&(((FT_OutlineGlyph)glyph)->outline),
			    &ftCallbacks, path);
    }
  else
    {
      char format[5];

      format[0] = (glyph->format & 0xFF000000) >> 24;
      format[1] = (glyph->format & 0x00FF0000) >> 16;
      format[2] = (glyph->format & 0x0000FF00) >> 8;
      format[3] = (glyph->format & 0x000000FF);
      format[4] = '\0';
      printf("WARNING: Unable to create outline for font %s %s of format %s\n",
	     ft_face->family_name, ft_face->style_name, format);
    }
  FT_Done_Glyph( glyph );

  free(path);

  return gp;
}
