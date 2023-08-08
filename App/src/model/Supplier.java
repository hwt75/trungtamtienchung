/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell
 */
public class Supplier {
    int id, idStaffCreated, idStaffUpdated;
    String name, phoneNumber, email, address, createdAt, nameStaffCreated, updatedAt, nameStaffUpdated;

    public Supplier() {
    }

    public Supplier(int id, int idStaffCreated, int idStaffUpdated, String name, String phoneNumber, String email, String address, String createdAt, String nameStaffCreated, String updatedAt, String nameStaffUpdated) {
        this.id = id;
        this.idStaffCreated = idStaffCreated;
        this.idStaffUpdated = idStaffUpdated;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
        this.nameStaffCreated = nameStaffCreated;
        this.updatedAt = updatedAt;
        this.nameStaffUpdated = nameStaffUpdated;
    }

    public Supplier(int idStaffCreated, int idStaffUpdated, String name, String phoneNumber, String email, String address, String createdAt, String updatedAt) {
        this.idStaffCreated = idStaffCreated;
        this.idStaffUpdated = idStaffUpdated;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Supplier(int id, int idStaffUpdated, String name, String phoneNumber, String email, String address, String updatedAt) {
        this.id = id;
        this.idStaffUpdated = idStaffUpdated;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.updatedAt = updatedAt;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStaffCreated() {
        return idStaffCreated;
    }

    public void setIdStaffCreated(int idStaffCreated) {
        this.idStaffCreated = idStaffCreated;
    }

    public int getIdStaffUpdated() {
        return idStaffUpdated;
    }

    public void setIdStaffUpdated(int idStaffUpdated) {
        this.idStaffUpdated = idStaffUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getNameStaffCreated() {
        return nameStaffCreated;
    }

    public void setNameStaffCreated(String nameStaffCreated) {
        this.nameStaffCreated = nameStaffCreated;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNameStaffUpdated() {
        return nameStaffUpdated;
    }

    public void setNameStaffUpdated(String nameStaffUpdated) {
        this.nameStaffUpdated = nameStaffUpdated;
    }

    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", idStaffCreated=" + idStaffCreated + ", idStaffUpdated=" + idStaffUpdated + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", createdAt=" + createdAt + ", nameStaffCreated=" + nameStaffCreated + ", updatedAt=" + updatedAt + ", nameStaffUpdated=" + nameStaffUpdated + '}';
    }
    
}
