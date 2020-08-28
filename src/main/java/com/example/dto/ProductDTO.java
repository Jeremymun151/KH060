package com.example.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class ProductDTO extends AbstractDTO<ProductDTO> {

    private static final long serialVersionUID = -605223606014371190L;

    private String name;
    private String code;
    private String shortDescription;
    private String specification;
    private String content;
    private String productCategoryCode;
    private String images;
    private Integer price;
    private String feature;
    private String[] featureArrays = new String[]{};
    private Integer quantity;
    private List<String> imageProducts = new ArrayList<>();
    private String[] base64Images;
    private String[] nameImages;
    private Map<String, String> imageMaps = new HashMap<>();
    private String thumbnail;
    private String priceDescription;
    private String seoUrl;
    private String eventCode;
    private Integer priceDiscount;
    private String brandCode;

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

    public List<String> getImageProducts() {
        return imageProducts;
    }

    public void setImageProducts(List<String> imageProducts) {
        this.imageProducts = imageProducts;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String[] getBase64Images() {
        if (base64Images != null) {
            List<String> results = new ArrayList<>();
            for (String item: base64Images) {
                results.add(item.split(",")[1]);
            }
            return results.stream().toArray(String[]::new);
        }
        return base64Images;
    }

    public void setBase64Images(String[] base64Images) {
        this.base64Images = base64Images;
    }

    public String[] getNameImages() {
        return nameImages;
    }

    public void setNameImages(String[] nameImages) {
        this.nameImages = nameImages;
    }

    public Map<String, String> getImageMaps() {
        if (this.base64Images != null) {
            return IntStream.range(0, this.nameImages.length).boxed()
                    .collect(toMap(i -> this.nameImages[i], i -> getBase64Images()[i]));
        }
        return imageMaps;
    }

    public void setImageMaps(Map<String, String> imageMaps) {
        this.imageMaps = imageMaps;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String[] getFeatureArrays() {
        return featureArrays;
    }

    public void setFeatureArrays(String[] featureArrays) {
        this.featureArrays = featureArrays;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPriceDescription() {
        return priceDescription;
    }

    public void setPriceDescription(String priceDescription) {
        this.priceDescription = priceDescription;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public Integer getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(Integer priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }
}
