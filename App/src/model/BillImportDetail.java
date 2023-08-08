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
public class BillImportDetail {
    int id, idBillImport, idProduct, price, count;
    String nameProduct;

    public BillImportDetail() {
    }

    public BillImportDetail(int id, int idBillImport, int idProduct, int price, int count, String nameProduct) {
        this.id = id;
        this.idBillImport = idBillImport;
        this.idProduct = idProduct;
        this.price = price;
        this.count = count;
        this.nameProduct = nameProduct;
    }

    public BillImportDetail(int id, int idBillImport, int idProduct, int price, int count) {
        this.id = id;
        this.idBillImport = idBillImport;
        this.idProduct = idProduct;
        this.price = price;
        this.count = count;
    }

    public BillImportDetail(int idBillImport, int idProduct, int price, int count) {
        this.idBillImport = idBillImport;
        this.idProduct = idProduct;
        this.price = price;
        this.count = count;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBillImport() {
        return idBillImport;
    }

    public void setIdBillImport(int idBillImport) {
        this.idBillImport = idBillImport;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    @Override
    public String toString() {
        return "BillImportDetail{" + "id=" + id + ", idBillImport=" + idBillImport + ", idProduct=" + idProduct + ", price=" + price + ", count=" + count + ", nameProduct=" + nameProduct + '}';
    }

    
    
}
