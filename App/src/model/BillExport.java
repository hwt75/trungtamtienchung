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
public class BillExport {
    int id, total, idStaffCreated, idStaffUpdated, idCustom, idCarrier;
    String createdAt, nameStaffCreated, updatedAt, nameStaffUpdated, nameCustom, 
            gender, email, address, phoneNumber, nameCarrier, note, 
            timeStartedBorrowed, timeEndBorrowed;

    public BillExport() {
    }

    public BillExport(int id, int total, int idStaffCreated, int idStaffUpdated, int idCustom, int idCarrier, String createdAt, String nameStaffCreated, String updatedAt, String nameStaffUpdated, String nameCustom, String gender, String email, String address, String phoneNumber, String nameCarrier, String note, String timeStartedBorrowed, String timeEndBorrowed) {
        this.id = id;
        this.total = total;
        this.idStaffCreated = idStaffCreated;
        this.idStaffUpdated = idStaffUpdated;
        this.idCustom = idCustom;
        this.idCarrier = idCarrier;
        this.createdAt = createdAt;
        this.nameStaffCreated = nameStaffCreated;
        this.updatedAt = updatedAt;
        this.nameStaffUpdated = nameStaffUpdated;
        this.nameCustom = nameCustom;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nameCarrier = nameCarrier;
        this.note = note;
        this.timeStartedBorrowed = timeStartedBorrowed;
        this.timeEndBorrowed = timeEndBorrowed;
    }

    public BillExport(int total, int idStaffCreated, int idStaffUpdated, int idCustom, int idCarrier, String createdAt, String updatedAt, String note, String timeStartedBorrowed, String timeEndBorrowed) {
        this.total = total;
        this.idStaffCreated = idStaffCreated;
        this.idStaffUpdated = idStaffUpdated;
        this.idCustom = idCustom;
        this.idCarrier = idCarrier;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.note = note;
        this.timeStartedBorrowed = timeStartedBorrowed;
        this.timeEndBorrowed = timeEndBorrowed;
    }

    public BillExport(int id, int idStaffUpdated, int idCustom, int idCarrier, String updatedAt, String note, String timeStartedBorrowed, String timeEndBorrowed) {
        this.id = id;
        this.idStaffUpdated = idStaffUpdated;
        this.idCustom = idCustom;
        this.idCarrier = idCarrier;
        this.updatedAt = updatedAt;
        this.note = note;
        this.timeStartedBorrowed = timeStartedBorrowed;
        this.timeEndBorrowed = timeEndBorrowed;
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

    public int getIdCustom() {
        return idCustom;
    }

    public void setIdCustom(int idCustom) {
        this.idCustom = idCustom;
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

    public String getNameCustom() {
        return nameCustom;
    }

    public void setNameCustom(String nameCustom) {
        this.nameCustom = nameCustom;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNameCarrier() {
        return nameCarrier;
    }

    public void setNameCarrier(String nameCarrier) {
        this.nameCarrier = nameCarrier;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimeStartedBorrowed() {
        return timeStartedBorrowed;
    }

    public void setTimeStartedBorrowed(String timeStartedBorrowed) {
        this.timeStartedBorrowed = timeStartedBorrowed;
    }

    public String getTimeEndBorrowed() {
        return timeEndBorrowed;
    }

    public void setTimeEndBorrowed(String timeEndBorrowed) {
        this.timeEndBorrowed = timeEndBorrowed;
    }

    @Override
    public String toString() {
        return "BillExport{" + "id=" + id + ", total=" + total + ", idStaffCreated=" + idStaffCreated + ", idStaffUpdated=" + idStaffUpdated + ", idCustom=" + idCustom + ", idCarrier=" + idCarrier + ", createdAt=" + createdAt + ", nameStaffCreated=" + nameStaffCreated + ", updatedAt=" + updatedAt + ", nameStaffUpdated=" + nameStaffUpdated + ", nameCustom=" + nameCustom + ", gender=" + gender + ", email=" + email + ", address=" + address + ", phoneNumber=" + phoneNumber + ", nameCarrier=" + nameCarrier + ", note=" + note + ", timeStartedBorrowed=" + timeStartedBorrowed + ", timeEndBorrowed=" + timeEndBorrowed + '}';
    }

    
    
}
