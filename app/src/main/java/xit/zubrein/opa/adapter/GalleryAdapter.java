package xit.zubrein.opa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import xit.zubrein.opa.FullImageActivity;
import xit.zubrein.opa.R;
import xit.zubrein.opa.model.ModelGallery;

public class GalleryAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<ModelGallery> gallery;

    public GalleryAdapter(Context context, List<ModelGallery> gallery) {
        this.mContext = context;
        this.gallery = gallery;
    }

    @Override
    public int getCount() {
        return gallery.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ModelGallery item = gallery.get(position);

        // view holder pattern
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.grid_view_items, null);

            final ImageView img = (ImageView)convertView.findViewById(R.id.imageView);

            final ViewHolder viewHolder = new ViewHolder(img);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();

        Picasso.with(mContext).load(item.getImage()).into(viewHolder.img);
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(mContext, FullImageActivity.class);
                    intent.putExtra("url",item.getImage());
                    mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private final ImageView img;

        public ViewHolder(ImageView img) {
            this.img = img;
        }
    }

}
