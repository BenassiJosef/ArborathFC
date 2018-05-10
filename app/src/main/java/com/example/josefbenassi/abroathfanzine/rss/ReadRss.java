package com.example.josefbenassi.abroathfanzine.rss;

import android.app.ProgressDialog;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresPermission;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static android.R.attr.data;

/**
 * Created by josefbenassi on 24/04/2017.
 */

// asynctask enables proper and easy use of the UI thread, this class allows to publish results on the UI thread without
// without having to manipulate UI threads or handlers.
public class ReadRss  extends AsyncTask<Void,Void,Void>{

    Context context;
    ProgressDialog progressDialog;

    String address = "http://feeds.feedburner.com/ScottishDivision2FootballNews?format=xml";
    URL url;

    ArrayList<FeedItem> feedItems;
    RecyclerView recyclerview;




    public ReadRss(Context context, RecyclerView recyclerview){

        this.recyclerview = recyclerview;

        this.context = context;
        progressDialog  = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MyAdapter adapter=new MyAdapter(context,feedItems);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.addItemDecoration(new VerticalSpace(50));
        recyclerview.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {

        ProcessXml (GetData());

        return null;


    }

    private void ProcessXml(Document data) {

    if (data!=null){

        if (data != null) {
             feedItems=new ArrayList<>();
            Element root = data.getDocumentElement();// now xml elements are inside channel
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();//stores items of child elements
            for (int i = 0; i < items.getLength(); i++) {
                Node cureentchild = items.item(i);
                if (cureentchild.getNodeName().equalsIgnoreCase("item")) {
                    FeedItem item=new FeedItem();
                    NodeList itemchilds = cureentchild.getChildNodes();
                    for (int j = 0; j < itemchilds.getLength(); j++) {
                        Node cureent = itemchilds.item(j);
                        if (cureent.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(cureent.getTextContent());
                        }else if (cureent.getNodeName().equalsIgnoreCase("description")){
                            item.setDescription(cureent.getTextContent().replace("<br>"," "));
                        }else if (cureent.getNodeName().equalsIgnoreCase("pubDate")){
                            item.setPubDate(cureent.getTextContent());
                        }else if (cureent.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(cureent.getTextContent());

                        }else if (cureent.getNodeName().equalsIgnoreCase("media:thumbnail")){
                            //this will return us thumbnail url
                            String url=cureent.getAttributes().item(0).getTextContent();
                            item.setThumbnailUrl(url);
                        }
                    }
                    feedItems.add(item);





                }
            }
        }






        }

    }

    public Document GetData(){



        try {

            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

            Document xml = documentBuilder.parse(inputStream);

            return xml;




        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
