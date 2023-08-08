/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Product {
    int id, categoryId, price, count, idStaffCreated, idUpdatedStaff;
    String categoryName, title, description, thumbnail, createdAt, updatedAt, nameStaffCreated, nameStaffUpdated;

    public Product() {
    }

    public Product(int id, int categoryId, int price, int count, int idStaffCreated, int idUpdatedStaff, String categoryName, String title, String description, String thumbnail, String createdAt, String updatedAt, String nameStaffCreated, String nameStaffUpdated) {
        this.id = id;
        this.categoryId = categoryId;
        this.price = price;
        this.count = count;
        this.idStaffCreated = idStaffCreated;
        this.idUpdatedStaff = idUpdatedStaff;
        this.categoryName = categoryName;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nameStaffCreated = nameStaffCreated;
        this.nameStaffUpdated = nameStaffUpdated;
    }

    public Product(int categoryId, int price, int count, int idStaffCreated, int idUpdatedStaff, String title, String description, String thumbnail, String createdAt, String updatedAt) {
        this.categoryId = categoryId;
        this.price = price;
        this.count = count;
        this.idStaffCreated = idStaffCreated;
        this.idUpdatedStaff = idUpdatedStaff;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product(int id, int categoryId, int price, int idUpdatedStaff, String title, String description, String thumbnail, String updatedAt) {
        this.id = id;
        this.categoryId = categoryId;
        this.price = price;
        this.idUpdatedStaff = idUpdatedStaff;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.updatedAt = updatedAt;
    }

    public Product(int id, int count, int idUpdatedStaff, String updatedAt) {
        this.id = id;
        this.count = count;
        this.idUpdatedStaff = idUpdatedStaff;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIdStaffCreated() {
        return idStaffCreated;
    }

    public void setIdStaffCreated(int idStaffCreated) {
        this.idStaffCreated = idStaffCreated;
    }

    public int getIdUpdatedStaff() {
        return idUpdatedStaff;
    }

    public void setIdUpdatedStaff(int idUpdatedStaff) {
        this.idUpdatedStaff = idUpdatedStaff;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNameStaffCreated() {
        return nameStaffCreated;
    }

    public void setNameStaffCreated(String nameStaffCreated) {
        this.nameStaffCreated = nameStaffCreated;
    }

    public String getNameStaffUpdated() {
        return nameStaffUpdated;
    }

    public void setNameStaffUpdated(String nameStaffUpdated) {
        this.nameStaffUpdated = nameStaffUpdated;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", categoryId=" + categoryId + ", price=" + price + ", count=" + count + ", idStaffCreated=" + idStaffCreated + ", idUpdatedStaff=" + idUpdatedStaff + ", categoryName=" + categoryName + ", title=" + title + ", description=" + description + ", thumbnail=" + thumbnail + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", nameStaffCreated=" + nameStaffCreated + ", nameStaffUpdated=" + nameStaffUpdated + '}';
    }
    
    
}
