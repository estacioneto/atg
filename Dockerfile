# Bata rodar docker build . para rodar os testes
# FIX: Verificar porque o javafx não ta pegando

FROM openjdk:10-jdk

RUN  \
  export DEBIAN_FRONTEND=noninteractive && \
  sed -i 's/# \(.*multiverse$\)/\1/g' /etc/apt/sources.list && \
  apt-get update && \
  apt-get -y upgrade && \
  apt-get install -y openjfx vim wget curl git maven

COPY . /usr/src/atg

WORKDIR /usr/src/atg

RUN mvn test
