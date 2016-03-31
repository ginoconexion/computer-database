# 1 - Etape : Docker mysql + maven connecté dans le même network
	# creation du network 
	docker network create cdb-mysql
	
	# dans jenkins

	# build & run de l'image maven
	docker build -t <path to mysql dockerfile>
	docker run -it --name=cdb-maven --net=cdb-net  -d ginoconexion/cdb-maven

	# build & run de l'image mysql
	docker build -t ginoconexion/cdb-mysql .
	docker run --name=cdb-mysql --net=cdb-net -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=computer-database-db  -d ginoconexion/cdb-mysql

# 2 - Etape Docker in Docker
	docker network create d-net
	
	# on build & run l'image jenkins en ouvrant le port en connectant le 8080 du docker au port 7080 de la machine host
	docker build -t ginoconexion/cdb-jenkins <path to jenkins Dockerfile>
	docker run -p 7080:8080 --name=cdb-jenkins --net=d-net ginoconexion/cdb-jenkins

	# on run le docker dind en le mettant dans le bon network (port ouvert par default du dind : 2375)
	docker run --privileged --net=d-net --name=dind -d docker:dind
	
	# se connecter à jenkins sur localhost:7080 -> lancer le build 
	# la configuration contient le même genre de commande que précédemment

	

	
