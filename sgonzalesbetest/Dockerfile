#Compile graalvm image
FROM gradle:8.7.0-jdk21-graal as graalimage

ADD . /build
WORKDIR /build

RUN gradle nativeCompile

CMD ["/build/build/native/nativeCompile/sgonzalesbetest"]