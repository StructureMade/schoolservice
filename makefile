dev:
	mvn clean
	mvn install -DskipTests=true
	docker build -f Dockerfile -t schoolservice .
	docker-compose up