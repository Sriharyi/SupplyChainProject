FROM maven:latest
WORKDIR /usr/schain
COPY . .
# RUN apk add udisks2
# RUN apk add util-linux pciutils usbutils coreutils binutils findutils grep iproute2
# RUN apk add bash bash-completion
# RUN apk update
# RUN apk add --upgrade maven
# ENTRYPOINT [ "mvnw spring-boot:run" ]
CMD ["mvn","spring-boot:run"]