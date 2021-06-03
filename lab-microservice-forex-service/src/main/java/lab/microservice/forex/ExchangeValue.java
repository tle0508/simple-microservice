package lab.microservice.forex;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exchange_value")
public class ExchangeValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public ExchangeValue() {

	}

	public ExchangeValue(String from, String to, double conversionMultiple) {
		super();
		// TODO: complete this constructor to set values to properties

	}

	// TODO: Write Getter/Setter for each properties

}