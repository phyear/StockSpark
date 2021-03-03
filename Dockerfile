FROM openjdk:8u102
# 创建一个目录
RUN mkdir /demo
# 将jar包copy到指定目录
ADD  /target/app.jar /demo/app.jar
# 启动命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Xmx512m","-Xms512m","/demo/app.jar"]
