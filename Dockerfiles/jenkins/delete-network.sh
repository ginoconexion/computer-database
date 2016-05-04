#!/bin/bash

network=$(docker network ls -f name=cdb-net -q)

# true if $network is not an empty string
if [ -n "$network" ]
then
	docker network rm cdb-net
	echo "network deleted"
fi
docker network create cdb-net
