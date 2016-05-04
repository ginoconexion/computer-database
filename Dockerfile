FROM java:openjdk-8-jdk

ENV MAVEN_VERSION 3.3.9

RUN mkdir -p /usr/share/maven \
  && curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
    | tar -xzC /usr/share/maven --strip-components=1 \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven

# on copie le projet maven dans /usr/app/
COPY . /usr/app/
RUN apt-get update
RUN apt-get install nano

# on place le workdir dans /usr/app
WORKDIR /usr/app/

# on remplace le fichier de configuration 
RUN cp Dockerfiles/jdk-maven/dao.properties src/test/resources/
RUN cp Dockerfiles/jdk-maven/dao.properties src/main/resources/
CMD ["mvn", "install"]
