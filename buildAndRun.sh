#!/bin/sh
mvn clean package && docker build -t com.mycompany/pw-marangon .
docker rm -f pw-marangon || true && docker run -d -p 8080:8080 -p 4848:4848 --name pw-marangon com.mycompany/pw-marangon 
