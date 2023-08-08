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
public class Custom {
    int id, idCreatedStaff, idUpdatedStaff;
    String fullName, gender, email, phoneNumber, address, created_at, nameCreatedStaff ,updated_at, nameUpdatedStaff;

    public Custom() {
    }

    public Custom(int id, int idCreatedStaff, int idUpdatedStaff, String fullName, String gender, String email, String phoneNumber, String address, String created_at, String nameCreatedStaff, String updated_at, String nameUpdatedStaff) {
        this.id = id;
        this.idCreatedStaff = idCreatedStaff;
        this.idUpdatedStaff = idUpdatedStaff;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.created_at = created_at;
        this.nameCreatedStaff = nameCreatedStaff;
        this.updated_at = updated_at;
        this.nameUpdatedStaff = nameUpdatedStaff;
    }

    public Custom(int id, int idUpdatedStaff, String fullName, String gender, String email, String phoneNumber, String address, String updated_at) {
        this.id = id;
        this.idUpdatedStaff = idUpdatedStaff;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.updated_at = updated_at;
    }

    
    public Custom(int idCreatedStaff, int idUpdatedStaff, String fullName, String gender, String email, String phoneNumber, String address, String created_at, String updated_at) {
        this.idCreatedStaff = idCreatedStaff;
        this.idUpdatedStaff = idUpdatedStaff;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
  
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCreatedStaff() {
        return idCreatedStaff;
    }

    public void setIdCreatedStaff(int idCreatedStaff) {
        this.idCreatedStaff = idCreatedStaff;
    }

    public int getIdUpdatedStaff() {
        return idUpdatedStaff;
    }

    public void setIdUpdatedStaff(int idUpdatedStaff) {
        this.idUpdatedStaff = idUpdatedStaff;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getNameCreatedStaff() {
        return nameCreatedStaff;
    }

    public void setNameCreatedStaff(String nameCreatedStaff) {
        this.nameCreatedStaff = nameCreatedStaff;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getNameUpdatedStaff() {
        return nameUpdatedStaff;
    }

    public void setNameUpdatedStaff(String nameUpdatedStaff) {
        this.nameUpdatedStaff = nameUpdatedStaff;
    }

    @Override
    public String toString() {
        return "Custom{" + "id=" + id + ", idCreatedStaff=" + idCreatedStaff + ", idUpdatedStaff=" + idUpdatedStaff + ", fullName=" + fullName + ", gender=" + gender + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", created_at=" + created_at + ", nameCreatedStaff=" + nameCreatedStaff + ", updated_at=" + updated_at + ", nameUpdatedStaff=" + nameUpdatedStaff + '}';
    }
    
    
}
