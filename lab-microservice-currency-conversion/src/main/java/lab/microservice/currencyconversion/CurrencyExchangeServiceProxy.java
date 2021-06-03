package lab.microservice.currencyconversion;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CurrencyExchangeServiceProxy {
	
 
  public CurrencyConversionBean retrieveExchangeValue( String from,  String to);
}