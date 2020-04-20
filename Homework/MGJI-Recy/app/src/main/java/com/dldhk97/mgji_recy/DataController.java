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
            // 식당 유형에 맞게 배열 선택
            switch (cafeteriaType){
                case STUDENT:
                    studentMenus = new Parser().parse(cafeteriaType);
                    break;
                case STAFF:
                    staffMenus = new Parser().parse(cafeteriaType);
                    break;
                case SNACKBAR:
                    snackbarMenus = new Parser().parse(cafeteriaType);
                    break;
                default:
                    throw new MyException(ExceptionType.UNKNOWN_CAFETERIA_TYPE, "Unknown current cafeteria type");
            }

            //데이터 변했다고 알려줌
            MainActivity.getInstance().recyclerAdapter.notifyDataSetChanged();
        }
        catch(Exception e){
            UIHandler.getInstance().showToast("[DataController.updateData]\n" + e.getMessage());
        }
    }
}
