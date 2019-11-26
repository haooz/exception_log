FROM openjdk:8

#RUN apt-get update
#RUN rm /bin/sh && ln -s /bin/bash /bin/sh
#RUN apt-get -qq -y install curl wget unzip zip

#RUN curl -s "https://get.sdkman.io" | bash
#RUN source "$HOME/.sdkman/bin/sdkman-init.sh"

COPY target/*.jar /opt/app.jar
WORKDIR /opt
# CMD ["java", "-jar","app.jar"]

ENV JAVA_OPTS=""
EXPOSE 506
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar" ]
#ENTRYPOINT [ "spring","--version"]
