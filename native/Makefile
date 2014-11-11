all:
	gcc -m32 -I/usr/lib/jvm/java-6-openjdk-amd64/include -I/usr/include/freetype2 -o libftpeer.so -shared ftpeer.c -lfreetype
	
run:
	adb push libftpeer.so /data/data/julianwi.javainstaller/javafiles/libftpeer.so
	adb shell java -jar /sdcard/test.jar
cairo:
	gcc -m32 -I/usr/lib/jvm/java-6-openjdk-amd64/include -I/usr/include/cairo -I/usr/include/pixman-1 -I/usr/include/freetype2 -I/usr/include/libpng12 -lcairo -o libcairopeer.so -shared cairopeer.c jcl.c
	
runn:
	adb push libcairopeer.so /data/data/julianwi.javainstaller/javafiles/libcairopeer.so
	adb shell java -jar /sdcard/test.jar