mvn clean install
sudo docker build -t egotsev/rest-prime .
sudo docker login -u $1
sudo docker push egotsev/rest-prime
