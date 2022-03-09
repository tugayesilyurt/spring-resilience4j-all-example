## Docker Compose Spring,Resilience4j,Prometheus,Grafana ( Circuit Breaker pattern - Retry pattern - Ratelimiter pattern - Timelimiter pattern - Bulkhead pattern )

### CUSTOMER-SERVICE`

* RateLimiter
* TimeLimiter
* CircuitBreaker
* Retry
* Bulkhead

### Run the System
First of all create network:

* `docker network create "resilience-net"`

Then the service can be run on the background with command:

* `docker-compose up -d`


### Stop the System
Stopping all the running containers is also simple with a single command:

* `docker-compose down`

If you need to stop and remove all containers, networks, and all images used by any service in <em>docker-compose.yml</em> file, use the command:

* `docker-compose down --rmi all`


- **Eureka**

![Eureka](https://github.com/tugayesilyurt/spring-resilience4j-all-example/blob/main/assets/eureka.PNG)

- **Grafana**

![Grafana](https://github.com/tugayesilyurt/spring-resilience4j-all-example/blob/main/assets/grafana.PNG)
	
- **Docker**

![Docker](https://github.com/tugayesilyurt/spring-resilience4j-all-example/blob/main/assets/docker.PNG)

- **Circuit Breaker**

![Circuit Breaker](https://github.com/tugayesilyurt/spring-resilience4j-all-example/blob/main/assets/circuit-breaker-postman.PNG)