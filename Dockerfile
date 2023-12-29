FROM maven:latest
WORKDIR /usr/schain
COPY . .
RUN ["mvn","clean","install"]
CMD ["mvn","clean","build"]
