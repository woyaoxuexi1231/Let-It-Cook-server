FROM eclipse-temurin:8-jre

ARG JAR_FILE=let-it-cook.jar
ENV TZ=Asia/Shanghai
VOLUME /tmp
WORKDIR /
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["sh","-c","java ${JAVA_OPTS:-} -jar /app.jar"]
