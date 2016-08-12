package ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.android.lavamusic.R;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import bean.Item;
import callback.callback.MyItemTouchCallback;
import callback.callback.OnRecyclerItemClickListener;


import ui.adapter.DividerGridItemDecoration;
import ui.adapter.RecyclerAdapter;
import ui.base.BaseFragment;
import utils.AbJsonUtil;
import utils.AbSharedUtil;

/**
 * Created by Administrator on 2016/4/12.
 */
public class MyGridFragment extends BaseFragment implements MyItemTouchCallback.OnDragListener{


    private List<Item> results = new ArrayList<Item>();
    private RecyclerView recyclerView;

    private ItemTouchHelper itemTouchHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ////////////////////////////////////////////////////////
        /////////初始化数据，如果缓存中有就使用缓存中的
      //  ArrayList<Item> items = (ArrayList<Item>) ACache.get(getActivity()).getAsObject("items");
        String itemsJson=AbSharedUtil.getString(getActivity(),"items");

       List<Item> items= (List<Item>) AbJsonUtil.fromJson(itemsJson,new TypeToken<List<Item>>(){});
        if (items!=null) {
            results.clear();
            results.addAll(items);
        }
        else{

                results.add(new Item("全部歌曲", R.drawable.mymusic_icon_allsongs_highlight));
                results.add(new Item(getString(R.string.lavamusic_download), R.drawable.mymusic_icon_download_highlight));
                results.add(new Item(getString(R.string.lavamusic_recent),R.drawable.mymusic_icon_history_highlight));
                results.add(new Item(getString(R.string.lavamusic_like), R.drawable.mymusic_icon_favorite_highlight));
                results.add(new Item(getString(R.string.lavamusic_mv), R.drawable.mymusic_icon_mv_highlight));
                results.add(new Item(getString(R.string.lavamusic_listensong), R.drawable.mymusic_icon_recognizer_highlight));
                results.add(new Item(getString(R.string.lavamusic_download), R.drawable.mymusic_icon_download_highlight));
                results.add(new Item(getString(R.string.lavamusic_mv), R.drawable.mymusic_icon_mv_highlight));
                results.add(new Item(getString(R.string.lavamusic_more), R.drawable.takeout_ic_more));





        }


        ////////////////////////////////////////////////////////
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return LayoutInflater.from(getActivity()).inflate(R.layout.lavamusic_frist_fragment,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        RecyclerAdapter adapter = new RecyclerAdapter(R.layout.item_grid,results);
        recyclerView = (RecyclerView)view.findViewById(R.id.frist_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.addItemDecoration(new DividerGridItemDecoration());
        recyclerView.setAdapter(adapter);



        itemTouchHelper = new ItemTouchHelper(new MyItemTouchCallback(adapter).setOnDragListener(this));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                if (vh.getLayoutPosition()!=results.size()-1) {
                    itemTouchHelper.startDrag(vh);
               //     VibratorUtil.Vibrate(getActivity(), 70);   //震动70ms
                }
            }
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Item item = results.get(vh.getLayoutPosition());
                Toast.makeText(getActivity(),item.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFinishDrag() {
        //存入缓存
        String jsons2String= AbJsonUtil.toJson(results);
        AbSharedUtil.putString(getActivity(),"items",jsons2String);

    }
}