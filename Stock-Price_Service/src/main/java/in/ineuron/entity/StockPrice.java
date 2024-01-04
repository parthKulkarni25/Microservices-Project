package in.ineuron.entity;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class StockPrice {

	@Id
	private Integer stockId;
	private String companyName;
	private Double companyPrice;
}
