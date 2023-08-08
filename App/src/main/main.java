/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.StaffController;
import view.AppCarrier;
import view.AppConsulting;
import view.AppManager;
import view.AppWareHouse;
import view.LogInFrm;


/**
 *
 * @author Dell
 */
public class main {
   public static void main(String[] args) {
       int idStaff = 0;
       int roleID = 0;
       LogInFrm logInFrm = new LogInFrm();
       while(idStaff == 0) {
           logInFrm.setVisible(true);
           idStaff = logInFrm.getStaffId();
       }
       roleID = StaffController.findRoleID(idStaff);
       if(roleID == 1) {
           AppManager appManager = new AppManager(idStaff);
           appManager.setVisible(true);
       } else if(roleID == 2) {
           AppConsulting appConsulting = new AppConsulting(idStaff);
           appConsulting.setVisible(true);
       } else if(roleID == 3) {
           AppWareHouse appWareHouse = new AppWareHouse(idStaff);
           appWareHouse.setVisible(true);
       } else {
           AppCarrier appCarrier = new AppCarrier(idStaff);
           appCarrier.setVisible(true);
       }
   }
    
}
