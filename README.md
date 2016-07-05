# rest-prime

## Setup instructions

Use `git clone` to get the source code.

Import the root folder to your favorite IDE as **Maven** project. You have to install [Project Lombok](https://projectlombok.org/) in your IDE in order to compile it without Maven.

Deploy on a servlet container that supports Servlet 3.0 specification. Tested and working on Apache Tomcat 8.

## Docker image

A docker image is available at [DockerHub](https://hub.docker.com/r/egotsev/rest-prime/). You can get it by executing:

`docker pull egotsev/rest-prime`

To run the image use:

`docker run -p 8080:8080 egotsev/rest-prime`

The application will be accessible on http://localhost:8080/rest-prime.

## Live access to the app

http://46.249.88.235:8080/rest-prime/