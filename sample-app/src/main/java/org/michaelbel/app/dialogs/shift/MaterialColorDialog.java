package org.michaelbel.app.dialogs.shift;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.michaelbel.app.R;
import org.michaelbel.material.Utils;
import org.michaelbel.material.widget.ShiftColorPicker;
import org.michaelbel.material.widget.LayoutHelper;
import org.michaelbel.material.widget.Palette;

public class MaterialColorDialog extends DialogFragment {

    private ShiftColorPicker picker1;
    private ShiftColorPicker picker2;

    public static MaterialColorDialog newInstance() {
        return new MaterialColorDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LinearLayout layout = new LinearLayout(getContext());
        layout.setLayoutParams(LayoutHelper.makeLinear(getActivity(), LayoutHelper.MATCH_PARENT,
                LayoutHelper.WRAP_CONTENT));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(Utils.dp(getContext(), 24), Utils.dp(getContext(), 24), Utils.dp(getContext(), 24), 0);

        picker1 = new ShiftColorPicker(getContext());
        picker1.setColors(Palette.PrimaryColors(getContext()));
        picker1.setLayoutParams(LayoutHelper.makeLinear(getContext(), LayoutHelper.MATCH_PARENT, 60));
        layout.addView(picker1);

        picker2 = new ShiftColorPicker(getContext());
        picker2.setLayoutParams(LayoutHelper.makeLinear(getContext(), LayoutHelper.MATCH_PARENT, 40, 0, 10, 0, 0));

        for (int i : picker1.getColors()) {
            for (int i2 : Palette.MaterialColors(getActivity(), i)) {
                if (i2 == 0xff4CaF50) {
                    picker1.setSelectedColor(i);
                    picker2.setColors(Palette.MaterialColors(getContext(), i));
                    picker2.setSelectedColor(i2);
                    break;
                }
            }
        }
        layout.addView(picker2);

        picker1.setOnColorChangedListener(new ShiftColorPicker.OnColorChangedListener() {
            @Override
            public void onColorChanged(int c) {
                picker2.setColors(Palette.MaterialColors(getContext(), picker1.getColor()));
                picker2.setSelectedColor(picker1.getColor());
            }
        });

        builder.setView(layout);
        builder.setTitle("Material Color");
        builder.setNegativeButton(R.string.Cancel, null);
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), Integer.toHexString(picker2.getColor()),
                        Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }
}