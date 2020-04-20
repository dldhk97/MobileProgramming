package com.dldhk97.mgji_recy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dldhk97.mgji_recy.DataController;
import com.dldhk97.mgji_recy.MyException;
import com.dldhk97.mgji_recy.R;
import com.dldhk97.mgji_recy.UIHandler;
import com.dldhk97.mgji_recy.enums.CafeteriaType;
import com.dldhk97.mgji_recy.enums.ExceptionType;
import com.dldhk97.mgji_recy.models.Menu;
import com.dldhk97.mgji_recy.viewholders.CafeteriaRecyclerViewHolder;

import java.util.ArrayList;

public class CafeteriaRecyclerAdapter extends RecyclerView.Adapter<CafeteriaRecyclerViewHolder> {
    private LayoutInflater inflater;
    private DataController dataController;

    private CafeteriaType currentType = CafeteriaType.STUDENT;

    public CafeteriaRecyclerAdapter(Context context, DataController dataController){
        inflater = LayoutInflater.from(context);
        this.dataController = dataController;
    }

    @NonNull
    @Override
    public CafeteriaRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_menu, parent, false);
        return new CafeteriaRecyclerViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CafeteriaRecyclerViewHolder holder, int position) {
        try{
            ArrayList<Menu> targetArray;
            switch (currentType){
                case STUDENT:
                    targetArray = dataController.getStudentMenus();
                    break;
                case STAFF:
                    targetArray = dataController.getStaffMenus();
                    break;
                case SNACKBAR:
                    targetArray = dataController.getSnackbarMenus();
                    break;
                default:
                    throw new MyException(ExceptionType.UNKNOWN_CAFETERIA_TYPE, "Unknown current cafeteria type");
            }
            holder.onBind(targetArray.get(position));
        }
        catch(Exception e){
            UIHandler.getInstance().showToast("[Adapter.onBindViewHolder]\n" + e.getMessage());
        }

    }


    @Override
    public int getItemCount() {
        try{
            switch (currentType){
                case STUDENT:
                    if(dataController.getStudentMenus() != null)
                        return dataController.getStudentMenus().size();
                    break;
                case STAFF:
                    if(dataController.getStaffMenus() != null)
                        return dataController.getStaffMenus().size();
                    break;
                case SNACKBAR:
                    if(dataController.getSnackbarMenus() != null)
                        return dataController.getSnackbarMenus().size();
                    break;
            }
        }
        catch (Exception e){
            UIHandler.getInstance().showToast("[Adapter.getItemCount]\n" + e.getMessage());
        }

        return 0;
    }
}
