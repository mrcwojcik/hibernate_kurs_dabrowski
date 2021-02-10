package pl.mrcwojcik.hibernate.entity;

public class ProductInCategoryCounterDto {

    private Long categoryId;
    private Long productInCategoryCounter;

    public ProductInCategoryCounterDto(Long categoryId, Long productInCategoryCounter) {
        this.categoryId = categoryId;
        this.productInCategoryCounter = productInCategoryCounter;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductInCategoryCounter() {
        return productInCategoryCounter;
    }

    public void setProductInCategoryCounter(Long productInCategoryCounter) {
        this.productInCategoryCounter = productInCategoryCounter;
    }
}
