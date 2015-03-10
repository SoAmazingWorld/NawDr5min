package com.apps.wow.nawdr5min;

/**
 * Created by Alexandr on 10.03.2015.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.androidquery.util.XmlDom;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Alexandr on 03.03.2015.
 *
 * SwipeRefreshLayout is commented due to conflict with NavigationDrawer.
 * а теперь по русски
 * я закомментил в связи конфликтом SwipeRefreshLayout c NavigationDrawer
 */
public class HomePage extends Fragment  {

    private final String[] FEEDS = new String[]{"http://droider.ru/feed/"};
    private AQuery aq;
    private RecyclerView gridView;
    private StaggeredGridLayoutManager mLayoutManager;
    private AdapterMain adapter;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<ClassItem> items = new ArrayList<>();
    private DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zz", Locale.ENGLISH);

    public HomePage()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        aq = new AQuery(getActivity());
        AQUtility.setDebug(true);



        gridView = new RecyclerView(getActivity());
        gridView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        gridView.setLayoutManager(mLayoutManager);
        gridView.setItemAnimator(new DefaultItemAnimator());
        adapter = new AdapterMain(getActivity(),items);
        gridView.setAdapter(adapter);
        getFeeds();

        return gridView;


    }




    public void getFeeds() {
        items.clear();
        Toast.makeText(getActivity(),"Обновлено" , Toast.LENGTH_SHORT ).show();
        for(String feed:FEEDS){
            request(feed);
        }
    }


    public void request(String url) {
        aq.ajax(url, XmlDom.class,this,"onRequest");

    }

    public void onRequest(String url,XmlDom xml, AjaxStatus status) {
        if (status.getCode()==200) {
            String logo = "";
            try {
                logo = xml.tags("url").get(0).text();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            List<XmlDom> xmlItems = xml.tags("item");

            for(XmlDom xmlItem: xmlItems){
                ClassItem item = new ClassItem();
                String description = xmlItem.tag("description").text();

                item.setTitle(xmlItem.tag("title").text());
                item.setDescription(description);
                item.setLink(xmlItem.tag("link").text());
                String pubDate = xmlItem.tag("pubDate").text();
                Date date = new Date();
                try {
                    date = formatter.parse(pubDate);
                }
                catch (Exception e) {
                    AQUtility.debug("errorParsingDate", e.toString());
                }
                item.setDate(date);
                String src ="";
                String tmp = xmlItem.tag("content:encoded").text();
                try {
                    tmp = tmp.replace(".png\">", ".png\"/>").replace(".jpg\">", ".jpg\"/>");
                    src = new XmlDom("<xml>" + tmp + "</xml>").tag("img").attr("src");
                    if (src.startsWith("//") ) {
                        src = "http:"+src;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                item.setImg(src);
                items.add(item);
            }
            Collections.sort(items, new Comparator<ClassItem>() {
                public int compare(ClassItem o1, ClassItem o2) {
                    if (o1.getDate() == null || o2.getDate() == null)
                        return 0;
                    return o2.getDate().compareTo(o1.getDate());
                }
            });

        }
        adapter.notifyDataSetChanged();
    }


}