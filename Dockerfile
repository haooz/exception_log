FROM openjdk:8-jdk-alpine
#时区
RUN echo "Asia/Shanghai" > /etc/timezone
#kaptcher 字体包
RUN set -xe \
&& apk --no-cache add ttf-dejavu fontconfig
COPY target/*.jar /opt/app.jar
WORKDIR /opt
# CMD ["java", "-jar","app.jar"]
ENV JAVA_OPTS=""
EXPOSE 506
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]
