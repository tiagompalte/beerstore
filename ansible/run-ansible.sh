#!/bin/bash
echo "Starting ansible..."
ANSIBLE_HOST_KEY_CHECKING=false ansible-playbook -i ../terraform/hosts --private-key ../terraform/key/beerstore_key  beerstore-playbook.yml -v

#COMANDO PARA DEPLOY DA APLICAÇÃO NO CLUSTER
#sudo docker service create --name beerstore -e SPRING_DATASOURCE_URL=jdbc:postgresql://URL_DO_RDS_DA_SUA_CONTA:5432/beerstore -p 8080:8080 --network service <NOME_IMAGEM>:<VERSAO>