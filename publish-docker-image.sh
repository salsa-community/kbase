#!/bin/sh
MVN_VERSION=$(mvn -q \
    -Dexec.executable=echo \
    -Dexec.args='${project.version}' \
    --non-recursive \
    exec:exec)

./mvnw -Pprod -DskipTests verify jib:dockerBuild

cat password.txt | docker login --username conacyt --password-stdin
docker push "conacyt/kbase:${MVN_VERSION}"