package com.example.flagquiz;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class DialogFragmentRegions extends DialogFragment {

    ArrayList<String> selectedItems = new ArrayList<String>();

    public interface onMultiChoiceListener{
        void onPositiveButtonClicked(String[] list, ArrayList<String> selectedItemList);
        void onNegativeButtonClicked();
    }

    onMultiChoiceListener miListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            miListener = (onMultiChoiceListener) context;
        }
        catch (Exception e){
            throw new ClassCastException(getActivity().toString() + "onMultiChoiceListener must implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] list = getActivity().getResources().getStringArray(R.array.regions);

        builder.setTitle("Regions").setMultiChoiceItems(list, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                if(isChecked){
                    selectedItems.add(list[i]);
                }
                else{
                    selectedItems.remove(list[i]);
                }
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                miListener.onPositiveButtonClicked(list, selectedItems);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                miListener.onNegativeButtonClicked();
            }
        });
        return builder.create();
    }
}
