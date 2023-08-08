/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class BillExportDetail {
    int id, idBillExport, idProduct, price, count;
    String nameProduct;

    public BillExportDetail() {
    }

    public BillExportDetail(int id, int idBillExport, int idProduct, int price, int count, String nameProduct) {
        this.id = id;
        this.idBillExport = idBillExport;
        this.idProduct = idProduct;
        this.price = price;
        this.count = count;
        this.nameProduct = nameProduct;
    }

    public BillExportDetail(int id, int idBillExport, int idProduct, int count) {
        this.id = id;
        this.idBillExport = idBillExport;
        this.idProduct = idProduct;
        this.count = count;
    }

    public BillExportDetail(int idBillExport, int idProduct, int count) {
        this.idBillExport = idBillExport;
        this.idProduct = idProduct;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBillExport() {
        return idBillExport;
    }

    public void setIdBillExport(int idBillExport) {
        this.idBillExport = idBillExport;
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
        return "BillExportDetail{" + "id=" + id + ", idBillExport=" + idBillExport + ", idProduct=" + idProduct + ", price=" + price + ", count=" + count + ", nameProduct=" + nameProduct + '}';
    }

    
      
}
