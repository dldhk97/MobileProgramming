package com.dldhk97.mgji_recy;

import com.dldhk97.mgji_recy.enums.CafeteriaType;
import com.dldhk97.mgji_recy.enums.ExceptionType;
import com.dldhk97.mgji_recy.logics.Parser;
import com.dldhk97.mgji_recy.models.Menu;

import java.util.ArrayList;

// 클래스간 데이터 공유 및 파싱한 데이터 관리함.
public class DataController {
    private ArrayList<Menu> studentMenus;
    private ArrayList<Menu> staffMenus;
    private ArrayList<Menu> snackbarMenus;

    public void setStudentMenus(ArrayList<Menu> studentMenus) {
        this.studentMenus = studentMenus;
    }

    public void setStaffMenus(ArrayList<Menu> staffMenus) {
        this.staffMenus = staffMenus;
    }

    public void setSnackbarMenus(ArrayList<Menu> snackbarMenus) {
        this.snackbarMenus = snackbarMenus;
    }

    public ArrayList<Menu> getStudentMenus() {
        return studentMenus;
    }

    public ArrayList<Menu> getStaffMenus() {
        return staffMenus;
    }

    public ArrayList<Menu> getSnackbarMenus() {
        return snackbarMenus;
    }

    public void updateData(CafeteriaType cafeteriaType){
        try{
            ArrayList<Menu> targetArray;
            switch (cafeteriaType){
                case STUDENT:
                    targetArray = studentMenus;
                    break;
                case STAFF:
                    targetArray = staffMenus;
                    break;
                case SNACKBAR:
                    targetArray = snackbarMenus;
                    break;
                default:
                    throw new MyException(ExceptionType.UNKNOWN_CAFETERIA_TYPE, "Unknown current cafeteria type");
            }
            Parser parser = new Parser();
//            targetArray = parser.parse(cafeteriaType);
        }
        catch(Exception e){
            UIHandler.getInstance().showToast("[DataController.updateData]\n" + e.getMessage());
        }
    }
}
