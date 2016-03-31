#!/bin/bash

containers=$(docker ps -aq)

if [ -n "$containers" ]
then 
	docker rm -f $containers
fi
