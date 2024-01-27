#!/bin/bash
kubectl port-forward svc/pgadmin-service 54321:80 -n 3soatg53 &
kubectl port-forward svc/applanchonete-service 8080:8080 -n 3soatg53 &