package lab.microservice.currencyconversion;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userrepo-service")
@LoadBalancerClient(name = "userrepo-service",configuration=LoadBalancerConfiguration.class)
@EnableDiscoveryClient

public interface UserRepoServiceProxy {

	@GetMapping("/api/users/{id}")
	public UserMessageBean retrieveUsername(@PathVariable("id") Long id);
}