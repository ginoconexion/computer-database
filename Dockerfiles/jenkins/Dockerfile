FROM jenkins:latest

ENV DEBIAN_FRONTEND noninteractive

USER root

# permet de connect docker à jenkins
RUN curl -sSL https://get.docker.com/ | sh \
    && rm -rf /var/lib/apt/lists/*

# specifie la variable d'environnement DOCKER_HOST : <protocole>://<nom-du-container-docker-in-docker>:<port-par-defaut>
ENV DOCKER_HOST tcp://dind:2375

# fichiers qui liste le plugin git et ses dépendances
COPY plugins.txt /usr/share/jenkins/plugins.txt

# copy le fichier de config du projet dans le docker jenkins et crée ainsi le projet avec la bonne configuration
COPY /var/lib/jenkins/jobs/Computerdatabase/config.xml /var/jenkins_home/jobs/Computerdatabase/config.xml

# run le bash qui installe les plugins
RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt
