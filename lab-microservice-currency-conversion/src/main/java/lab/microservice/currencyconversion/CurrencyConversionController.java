package lab.microservice.currencyconversion;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Autowired
  private CurrencyExchangeServiceProxy proxy;


  
  @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
      @PathVariable double quantity) {

	//TODO: call retreiveExchangeValue from proxy by given from and to currency
    CurrencyConversionBean currencyRate = null;

    // You may uncomment the line below to print out the response message received from forex-service
    //logger.info("{}", response);

    //TODO: use currencyRate to make conversion and create new CurrencyConversionBean that contains the totalCalculatedAmount to return
    return null;
  }

  
  
}