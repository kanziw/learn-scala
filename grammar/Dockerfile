FROM rainist/sbt:1.1.5

RUN mkdir -p /var/www/app
WORKDIR /var/www/app

ADD ./project /var/www/app/project
ADD ./build.sbt /var/www/app/build.sbt

RUN sbt compile
