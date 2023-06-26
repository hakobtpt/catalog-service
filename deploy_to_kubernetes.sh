# start
minikube delete && minikube start
minikube image load catalog-service:0.0.1-SNAPSHOT
kubectl create deployment catalog-service --image=catalog-service:0.0.1-SNAPSHOT
kubectl get deployment
kubectl get pod
kubectl logs deployment/catalog-service
kubectl expose deployment catalog-service --name=catalog-service --port=8080
kubectl get service catalog-service
kubectl port-forward service/catalog-service 8000:8080
CTL+C
kubectl delete service catalog-service
kubectl delete deployment catalog-service
minikube stop
