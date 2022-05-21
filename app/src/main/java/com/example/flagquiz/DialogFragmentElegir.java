package com.example.flagquiz;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogFragmentElegir extends DialogFragment {

    int posicion = 0;

    public interface SingleChoiseListener{
        void onPositiveButtonClicked(String[] list, int position);
        void onNegativeButtonClicked();

    }

    SingleChoiseListener miListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            miListener = (SingleChoiseListener) context;
        }
        catch (Exception e){
            throw new ClassCastException(getActivity().toString() + "SingleChioceListener must implmented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] list = getActivity().getResources().getStringArray(R.array.items);
        builder.setTitle("Numbre of Choices").setSingleChoiceItems(list, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                posicion = i;
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                miListener.onPositiveButtonClicked(list, posicion);
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
