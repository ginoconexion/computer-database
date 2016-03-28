FROM maven:3.3.9-jdk-8-onbuild
COPY . /usr/src/app
RUN mvn install