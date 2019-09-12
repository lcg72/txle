PROJECT_NAME  = txle
PROJECT_VERSION=2.19.10.0
DOCKER        := $(shell which docker)
DOCKER_IMAGE  := docker-registry:5000/maven:3.6.0-jdk-8

docker_mvn_txle:
	$(DOCKER) run -v $(shell pwd)/:/opt/code --rm -w /opt/code $(DOCKER_IMAGE) /bin/bash -c "mvn clean package -DskipTests; mvn install -DskipTests -Pdev"

upload_txle:
	curl -T $(shell pwd)/target/txle_${PROJECT_VERSION}.tar.gz -u admin:ftpadmin ftp://release-ftpd/actiontech-${PROJECT_NAME}/qa/${PROJECT_VERSION}/txle_${PROJECT_VERSION}.tar.gz
