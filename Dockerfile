FROM openjdk:17
COPY ./out/production/DockerHelloWorld/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java","HelloWorld"]