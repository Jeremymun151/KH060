package com.example.dto;

public class HomeSearchDTO{

    private String name;
    private String productCategoryCode;
    private String brandCode;
    private String searchByCost;
    private String sortBy;
    private String[] featureSearches = new String[]{};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public String getSearchByCost() {
        return searchByCost;
    }

    public void setSearchByCost(String searchByCost) {
        this.searchByCost = searchByCost;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String[] getFeatureSearches() {
        return featureSearches;
    }

    public void setFeatureSearches(String[] featureSearches) {
        this.featureSearches = featureSearches;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }
}
