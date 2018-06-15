call mvn install:install-file -Dfile="%CD%/huelocalsdk.jar" -DgroupId=com.philips.hue -DartifactId=huelocalsdk -Dversion=1.22.0 -Dpackaging=jar
call mvn install:install-file -Dfile="%CD%/huesdkresources.jar" -DgroupId=com.philips.hue -DartifactId=huesdkresources -Dversion=1.220 -Dpackaging=jar
