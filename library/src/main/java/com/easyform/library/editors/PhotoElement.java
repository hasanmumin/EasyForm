package com.easyform.library.editors;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.easyform.library.R;
import com.easyform.library.editors.model.PhotoSupport;
import com.easyform.library.util.ObjectUtil;

import java.io.File;


public class PhotoElement extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener, View.OnLongClickListener, Editor<String> {


    private String value;
    private boolean hasTookAphoto;
    private int photoId = -1;

    public PhotoElement(Context context) {
        super(context);
        setPadding(10, 5, 0, 5);
        setOnClickListener(this);
        setOnLongClickListener(this);
        setBackgroundResource(R.drawable.section_bg);
        setImageDrawable(ContextCompat.getDrawable(context, R.drawable.no_image));
        this.setId((int) (Math.random() * 100));
        setMaxWidth(200);
        setMaxHeight(150);
        setScaleType(ScaleType.CENTER_INSIDE);
    }

    public PhotoElement(Context context, Object value) {
        this(context);
        if (ObjectUtil.isNotNull(value))
            setImageBitmap((Bitmap) value);

    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;

    }

    @Override
    public CharSequence getText() {
        return "";
    }

    @Override
    public void onClick(View view) {
        photoId = this.getId();
        String path = ((PhotoSupport) getActivity()).takePhoto(photoId);
        setValue(path);
    }


    private Activity getActivity() {
        Context context = getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    @Override
    public boolean onLongClick(View v) {

        hasTookAphoto = (value != null);

        final int imageId = this.getId();

        final Context context = getContext();

        View view = findViewById(imageId);
        if (ObjectUtil.isNotNull(view)) {

            String path = ((Editor<String>) view).getValue();
            if (ObjectUtil.isNull(path) || !hasTookAphoto) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("Please upload image")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

                builder.create().show();
                return false;
            }

            final AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setMessage("Your choose")
                    .setPositiveButton("Display", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showImage(context);
                        }
                    }).setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setImageDrawable(ContextCompat.getDrawable(context, R.drawable.no_image));
                    setValue(null);
                    setPhotoId(-1);
                }
            });
            builder.create().show();
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public void showImage(Context context) {

        int imageId = this.getId();

        View v = findViewById(imageId);
        if (ObjectUtil.isNotNull(v)) {

            Dialog builder = new Dialog(context);

            builder.requestWindowFeature(Window.FEATURE_NO_TITLE);

            builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    //nothing;
                }
            });

            String path = ((Editor<String>) v).getValue();

            File image = new File(path);

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();

            Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);

            ImageView imageView = new ImageView(context);

            imageView.setImageBitmap(bitmap);

            builder.addContentView(imageView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            builder.show();
        }
    }
}
