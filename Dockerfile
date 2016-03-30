FROM ginoconexion/cdb-maven
COPY . /usr/app/
RUN apt-get update
RUN apt-get install nano
RUN cp /usr/app/dao.properties /usr/app/src/test/resources/
RUN cp /usr/app/dao.properties /usr/app/src/main/resources/
