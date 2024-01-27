#!/bin/bash
docker build -f docker/Dockerfile -t fvmoraes/applanchonetetok8s:latest .
docker login
docker push fvmoraes/applanchonetetok8s:latest
minikube start
kubectl apply -f k8s/namespace.yaml
kubectl apply -k k8s

kubectl port-forward svc/pgadmin-service 54321:80 -n 3soatg53 &
kubectl port-forward svc/applanchonete-service 8080:8080 -n 3soatg53 &