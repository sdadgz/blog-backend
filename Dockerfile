FROM openjdk:17-jdk

# 设置环境变量，例如时区、端口等
ENV TZ=Asia/Shanghai
ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8
ENV JAVA_OPTS=""

# 将本地jar文件复制到容器中
# 假设你的jar文件名为`app.jar`，并且位于当前目录下
COPY ./blog-backend-0.0.1-SNAPSHOT.jar /usr/local/springboot/blog-backend-0.0.1-SNAPSHOT.jar

WORKDIR /usr/local/springboot

# 暴露端口，假设应用运行在8080端口
EXPOSE 8000

# 启动应用
CMD ["java", "-jar", "./blog-backend-0.0.1-SNAPSHOT.jar", "${JAVA_OPTS}"]