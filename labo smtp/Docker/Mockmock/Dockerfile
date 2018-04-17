#Contenu trouvé sur https://github.com/robinong79/docker-mockmock/blob/master/dockerfile
FROM openjdk:8

ENV MOCKMOCK_SMPT_PORT=25
ENV MOCKMOCK_HTTP_PORT=8282

EXPOSE 25 8282

ADD . /opt/src

WORKDIR /opt/src

ENTRYPOINT java -jar MockMock.jar -p $MOCKMOCK_SMPT_PORT -h $MOCKMOCK_HTTP_PORT -m 