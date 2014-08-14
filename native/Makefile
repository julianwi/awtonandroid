all:
	gcc -m32 -I/usr/lib/jvm/java-6-openjdk-amd64/include -I/usr/include/freetype2 -o libftpeer.so -shared ftpeer.c -lfreetype
	
run:
	adb push libftpeer.so /data/data/julianwi.javainstaller/java/lib/libftpeer.so
	adb shell java -jar /sdcard/test.jar