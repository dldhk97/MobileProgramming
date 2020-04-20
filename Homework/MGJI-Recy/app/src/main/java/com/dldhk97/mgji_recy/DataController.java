package com.dldhk97.mgji_recy;

import com.dldhk97.mgji_recy.enums.CafeteriaType;
import com.dldhk97.mgji_recy.enums.ExceptionType;
import com.dldhk97.mgji_recy.logics.Parser;
import com.dldhk97.mgji_recy.models.Menu;

import java.util.ArrayList;
import java.util.Calendar;

// 클래스간 데이터 공유 및 파싱한 데이터 관리함.
public class DataController {
    private static DataController _Instance;
    private ArrayList<Menu> studentMenus;
    private ArrayList<Menu> staffMenus;
    private ArrayList<Menu> snackbarMenus;

    public CafeteriaType currentCafeteriaType = CafeteriaType.STUDENT;

    private int moreOffset = -7;
    private final int MAX_OFFSET = -70;

    // 싱글톤
    public static DataController getInstance(){
        if(_Instance == null){
            _Instance = new DataController();
        }

        return _Instance;
    }

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
            Calendar date = Calendar.getInstance();
            moreOffset = -7;
            
            Parser parser = new Parser();

            // 식당 유형에 맞게 파싱
            ArrayList<Menu> parsedArr = parser.parse(cafeteriaType, date);

            // 기존 리스트 클리어하고, 식당 유형에 맞게 삽입.
            switch (cafeteriaType){
                case STUDENT:
                    if(studentMenus != null)
                        studentMenus.clear();
                    studentMenus = parsedArr;
                    break;
                case STAFF:
                    if(staffMenus != null)
                        staffMenus.clear();
                    staffMenus = parsedArr;
                    break;
                case SNACKBAR:
                    if(snackbarMenus != null)
                        snackbarMenus.clear();
                    snackbarMenus = parsedArr;
                    break;
                default:
                    throw new MyException(ExceptionType.UNKNOWN_CAFETERIA_TYPE, "Unknown current cafeteria type");
            }

            // 저번주거 불러옴
            more();
            // 저저번주거 불러옴
            more();

            //데이터 변했다고 알려줌
            MainActivity.getInstance().recyclerAdapter.notifyDataSetChanged();
        }
        catch(Exception e){
            UIHandler.getInstance().showAlert("[DataController.updateData]\n" + e.getMessage());
        }
    }

    // 더 보기 눌렀을 때 사용함. moreOffset은 -7씩 추가되고, n주일 전으로 돌아감.
    public void more() throws Exception{
        if(moreOffset < MAX_OFFSET){
            throw new MyException(ExceptionType.MORE_OFFSET_MAX, "더 이상은 불러올 수 없습니다.");
        }
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, moreOffset);
        moreOffset -= 7;

        Parser parser = new Parser();

        ArrayList<Menu> parsedArr = parser.parse(currentCafeteriaType, date);

        // 식당 유형에 맞게 합체
        switch (currentCafeteriaType){
            case STUDENT:
                studentMenus.addAll(parsedArr);
                break;
            case STAFF:
                staffMenus.addAll(parsedArr);
                break;
            case SNACKBAR:
                snackbarMenus.addAll(parsedArr);
                break;
            default:
                throw new MyException(ExceptionType.UNKNOWN_CAFETERIA_TYPE, "Unknown current cafeteria type");
        }

        //데이터 변했다고 알려줌
        MainActivity.getInstance().recyclerAdapter.notifyDataSetChanged();

    }
}
