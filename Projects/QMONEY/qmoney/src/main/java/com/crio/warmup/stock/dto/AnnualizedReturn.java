
package com.crio.warmup.stock.dto;

public class AnnualizedReturn implements Comparable<AnnualizedReturn> {

  private final String symbol;
  private final Double annualizedReturn;
  private final Double totalReturns;

  public AnnualizedReturn(String symbol, Double annualizedReturn, Double totalReturns) {
    this.symbol = symbol;
    this.annualizedReturn = annualizedReturn;
    this.totalReturns = totalReturns;
  }

  public String getSymbol() {
    return symbol;
  }

  public Double getAnnualizedReturn() {
    return annualizedReturn;
  }

  public Double getTotalReturns() {
    return totalReturns;
  }
  
  @Override
  public int compareTo(AnnualizedReturn otherStock) {
    // TODO Auto-generated method stub
    return (this.annualizedReturn > otherStock.annualizedReturn) ? -1 :
       (this.annualizedReturn < otherStock.annualizedReturn) ? 1 : 0;
  }
}
