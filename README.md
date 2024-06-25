# Add dummy data on fresh start of the application
1. Get valid jwt token with one of the users:

Send request to: localhost:8080/api/auth

With the following body: {
  "email":"pavel@pavel.com",
  "password": "pp"
}

2. With the valid jwt token you got send the following requests:
   1. To add random images to all the existing properties send request to:
   
   localhost:8080/api/property-image/populate 
   
   with the header: "Authorization: Bearer ${valid_jwt}"

   2. To add random images to all the existing users send request to:
   
   localhost:8080/api/user-images/populate 
   
   with the header: "Authorization: Bearer ${valid_jwt}"\

   3. To add random bookings to random existing properties send request to:
   
   localhost:8080/api/booking/populate 
   
   with the header: "Authorization: Bearer ${valid_jwt}"

   4. To add random reviews to all existing bookings send request to:
   
   localhost:8080/api/reviews/populate 
   
   with the header: "Authorization: Bearer ${valid_jwt}"

# Континуирана инспорака и интеграција проект

## Docker Compose orchestration

```shell
docker compose up
```

## Kubernetes orchestration

### Add *smeshtaj.com* to known hosts

#### Linux

```shell
sudo nano /etc/hosts
```

Then add: 127.0.0.1  smeshtaj.com

#### Linux (alternative)

```shell
sudo echo "127.0.0.1       smeshtaj.com" > hosts.tmp && sudo cat /etc/hosts >> hosts.tmp && sudo mv hosts.tmp /etc/hosts
```

### Create k8s cluster (ex. k3d cluster)

```shell
k3d cluster create smeshtaj -p "80:80@loadbalancer" -s 1 -a 1
```
*if you get ~ 0.0.0.0:80: bind: address already in use -> kill the process running on port 80 or map another free port (-p "8080:80@loadbalancer")*

### If you're using k3d remove traefik and install nginx

#### Install Helm if you don't have it

```shell
curl https://baltocdn.com/helm/signing.asc | gpg --dearmor | sudo tee /usr/share/keyrings/helm.gpg > /dev/null
sudo apt-get install apt-transport-https --yes
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/helm.gpg] https://baltocdn.com/helm/stable/debian/ all main" | sudo tee /etc/apt/sources.list.d/helm-stable-debian.list
sudo apt-get update
sudo apt-get install helm
```

#### Wait for traefik to be running

```shell
kubectl get pods -n kube-system --watch
```

#### Remove traefik

```shell
helm uninstall traefik -n kube-system
```

#### Deploy nginx-ingress

```shell
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
```

#### Verify and wait for ingress-nginx-controller-xxx to be running

```shell
kubectl get pods -n ingress-nginx --watch
```

### Apply namespace then apply deployments, services, stateful set, secrets, config-map and secrets

```shell
kubectl apply -f .kubernetes/smeshtaj-namespace.yaml -f .kubernetes/.
```

### Wait for pods to start

```shell
kubectl get pods -n smeshtaj --watch
```

### Open smeshtaj.com and test it out!
