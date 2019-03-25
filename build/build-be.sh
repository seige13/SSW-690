#!/usr/bin/env bash

# Run this from the root directory of the project
# Gets latest changes and build the code on the server

echo "Pulling latest changes from the server..."
git pull

cd ./hobbymatcher
echo "Generating latest JAR file..."
mvn install
mv ./target/hobbymatcher.war /var/lib/tomcat/webapps

echo "Restarting Tomcat..."
systemctl restart tomcat

echo "Latest code deployed."
