FROM openjdk:8u102
# 创建一个目录
WORKDIR /
# 将jar包copy到指定目录
ADD  /target/app.jar /app.jar
# 启动命令
CMD ["java","-jar","app.jar"]
