all:
	gcc -m32 -I/usr/lib/jvm/java-6-openjdk-amd64/include -I/usr/include/freetype2 -I/usr/include/cairo -I/usr/include/pixman-1 -I/usr/include/libpng12 -o libawtonandroid.so -shared ftpeer.c cairopeer.c jcl.c -lfreetype -lcairo
	
run:
	adb push libawtonandroid.so /data/data/julianwi.javainstaller/javafiles/libawtonandroid.so
	adb shell /data/data/julianwi.javainstaller/java -jar /sdcard/test.jar
