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
public class BillImport {
    int id, total, idStaffCreated, idStaffUpdated, idSupplier, idCarrier;
    String createdAt, updatedAt, nameStaffCreated, nameStaffUpdated, nameSupplier, nameCarrier;

    public BillImport() {
    }

    public BillImport(int id, int total, int idStaffCreated, int idStaffUpdated, int idSupplier, int idCarrier, String createdAt, String updatedAt, String nameStaffCreated, String nameStaffUpdated, String nameSupplier, String nameCarrier) {
        this.id = id;
        this.total = total;
        this.idStaffCreated = idStaffCreated;
        this.idStaffUpdated = idStaffUpdated;
        this.idSupplier = idSupplier;
        this.idCarrier = idCarrier;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.nameStaffCreated = nameStaffCreated;
        this.nameStaffUpdated = nameStaffUpdated;
        this.nameSupplier = nameSupplier;
        this.nameCarrier = nameCarrier;
    }

    public BillImport(int total, int idStaffCreated, int idStaffUpdated, int idSupplier, int idCarrier, String createdAt, String updatedAt) {
        this.total = total;
        this.idStaffCreated = idStaffCreated;
        this.idStaffUpdated = idStaffUpdated;
        this.idSupplier = idSupplier;
        this.idCarrier = idCarrier;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BillImport(int id, int idStaffUpdated, int idSupplier, int idCarrier, String updatedAt) {
        this.id = id;
        this.idStaffUpdated = idStaffUpdated;
        this.idSupplier = idSupplier;
        this.idCarrier = idCarrier;
        this.updatedAt = updatedAt;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public int getIdCarrier() {
        return idCarrier;
    }

    public void setIdCarrier(int idCarrier) {
        this.idCarrier = idCarrier;
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

    public String getNameSupplier() {
        return nameSupplier;
    }

    public void setNameSupplier(String nameSupplier) {
        this.nameSupplier = nameSupplier;
    }

    public String getNameCarrier() {
        return nameCarrier;
    }

    public void setNameCarrier(String nameCarrier) {
        this.nameCarrier = nameCarrier;
    }

    @Override
    public String toString() {
        return "BillImport{" + "id=" + id + ", total=" + total + ", idStaffCreated=" + idStaffCreated + ", idStaffUpdated=" + idStaffUpdated + ", idSupplier=" + idSupplier + ", idCarrier=" + idCarrier + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", nameStaffCreated=" + nameStaffCreated + ", nameStaffUpdated=" + nameStaffUpdated + ", nameSupplier=" + nameSupplier + ", nameCarrier=" + nameCarrier + '}';
    }

    
}
