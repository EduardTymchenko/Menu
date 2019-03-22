package Model;

public class Criterion {
    private Integer priceFrom;
    private Integer priceTo;
    private Boolean discount;
    private Boolean weightTo;

    public Criterion(Integer priceFrom, Integer priceTo, Boolean discount, Boolean weightTo) {
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.discount = discount;
        this.weightTo = weightTo;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    public Boolean getWeightTo() {
        return weightTo;
    }

    public void setWeightTo(Boolean weightTo) {
        this.weightTo = weightTo;
    }
    public boolean isCriterion(){
        return priceFrom != null || priceTo != null || discount || weightTo ;
    }

    public String toRequest(String nameClass){
        StringBuilder sb = new StringBuilder();
        sb.append(" " + nameClass);
        sb.append(" WHERE ");
        if (priceFrom != null) sb.append(nameClass + ".price >= " + priceFrom + " AND ");
        if (priceTo != null) sb.append(nameClass + ".price <= " + priceTo + " AND ");
        if (discount) sb.append(nameClass + ".discount > 0 AND ");
        if (weightTo) sb.append(nameClass + ".weight <= 1000 AND ");
        return sb.toString().substring(0,sb.length() - 5);
    }

}
