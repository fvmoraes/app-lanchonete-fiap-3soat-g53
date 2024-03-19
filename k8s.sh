!/bin/bash
docker build -f docker/Dockerfile -t fvmoraes/applanchonetetok8s:latest .
docker login
docker push fvmoraes/applanchonetetok8s:latest
minikube start
kubectl apply -f k8s/namespace.yaml
kubectl apply -k k8s
