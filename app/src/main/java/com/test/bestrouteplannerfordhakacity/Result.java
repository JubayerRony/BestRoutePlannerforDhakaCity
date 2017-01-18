package com.test.bestrouteplannerfordhakacity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

class Node
{
    public int u, w;

    public Node(int uu, int ww)
    {
        this.u = uu;
        this.w = ww;
    }
}

class Dijkstra
{
    int nodes, edges, i, src, dest, cnt;
    Scanner in = new Scanner(System.in);
    int mx = 200;
    int[] d = new int[mx+5];
    int [] par = new int[mx+5];
    int [] factor = new int[mx+5];
    String [] transport = new String[mx+5];

    Vector<Integer> graph[] = (Vector<Integer>[]) new Vector[mx+5];
    Vector<Integer> cost[] = (Vector<Integer>[]) new Vector[mx+5];
    Vector<Integer> time[] = (Vector<Integer>[]) new Vector[mx+5];
    Vector<Integer> dist[] = (Vector<Integer>[]) new Vector[mx+5];
    Vector<String> tr[] = (Vector<String>[]) new Vector[mx+5];

    Vector<Integer> stoppage = new Vector<>();

    HashMap toInt = new HashMap();
    HashMap toString = new HashMap();


    public void Dijkstra()
    {
    }

    public void setSourceDestination(String s, String d)
    {
        src = (int) toInt.get(s);
        dest = (int) toInt.get(d);
    }

    public void init()
    {
        for(i=1; i<=mx; i++) graph[i] = new Vector<Integer>();
        for(i=1; i<=mx; i++) cost[i] = new Vector<Integer>();
        for(i=1; i<=mx; i++) time[i] = new Vector<Integer>();
        for(i=1; i<=mx; i++) dist[i] = new Vector<Integer>();
        for(i=1; i<=mx; i++) tr[i] = new Vector<String>();

        for(i=1; i<=mx; i++) d[i] = 9999999;
        for(i=1; i<=mx; i++)
        {
            par[i] = -1;
            transport[i] = "";
            factor[i] = -1;
        }

        cnt = 0;
    }

    public void takeInput(Vector <String> input)
    {

        init();

        int cst, tm, ds, uu , vv;
        String u, v, t;

        for(i=0; i<input.size(); i++)
        {
            String[] elements = input.get(i).split("\\s+");

//			System.out.println("in while");

            u = elements[0];
            v = elements[1];
            cst = Integer.parseInt(elements[2]);
            tm =  Integer.parseInt(elements[3]);
            ds = Integer.parseInt(elements[4]);
            t = elements[5];

            if(toInt.get(u) == null)
            {
                toInt.put(u, ++cnt);
                toString.put(cnt, u);
            }
            if(toInt.get(v) == null)
            {
                toInt.put(v, ++cnt);
                toString.put(cnt, v);
            }

            uu = (int) toInt.get(u);
            vv = (int) toInt.get(v);

            graph[uu].add(vv);
            cost[uu].add(cst);
            time[uu].add(tm);
            dist[uu].add(ds);
            tr[uu].add(t);

            graph[vv].add(uu);
            cost[vv].add(cst);
            time[vv].add(tm);
            dist[vv].add(ds);
            tr[vv].add(t);

        }
    }



    public int getMinimumCost()
    {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(mx, new Comparator<Node>() {
            public int compare(Node A, Node B) {
                return A.w - B.w;
            }
        });

        for(i=1; i<=mx; i++) d[i] = 9999999;
        for(i=1; i<=mx; i++)
        {
            par[i] = -1;
            transport[i] = "";
            factor[i] = -1;
        }


        d[src] = 0;
        pq.add(new Node(src, 0));

        while(true)
        {
            Node top = pq.poll();
            if(top == null)
            {
                break;
            }

            int u = top.u;
            if(u == dest) return d[u];

            for(i=0; i<graph[u].size(); i++)
            {
                int v = graph[u].get(i);
                if(d[u] + cost[u].get(i) < d[v])
                {
                    d[v] = d[u] + cost[u].get(i);
                    pq.add(new Node(v, d[v]));
                    par[v] = u;
                    transport[v] = tr[u].get(i);
                    factor[v] = cost[u].get(i);
                }
            }


        }

        return -1;
    }

    public int getMinimumDistance()
    {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(mx, new Comparator<Node>() {
            public int compare(Node A, Node B) {
                return A.w - B.w;
            }
        });

        for(i=1; i<=mx; i++) d[i] = 9999999;
        for(i=1; i<=mx; i++)
        {
            par[i] = -1;
            transport[i] = "";
            factor[i] = -1;
        }

        d[src] = 0;
        pq.add(new Node(src, 0));

        while(true)
        {
            Node top = pq.poll();
            if(top == null)
            {
                break;
            }

            int u = top.u;
            if(u == dest) return d[u];

            for(i=0; i<graph[u].size(); i++)
            {
                int v = graph[u].get(i);
                if(d[u] + dist[u].get(i) < d[v])
                {
                    d[v] = d[u] + dist[u].get(i);
                    pq.add(new Node(v, d[v]));
                    par[v] = u;
                    transport[v] = tr[u].get(i);
                    factor[v] = dist[u].get(i);
                }
            }


        }

        return -1;
    }

    public int getMinimumTime()
    {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(mx, new Comparator<Node>() {
            public int compare(Node A, Node B) {
                return A.w - B.w;
            }
        });

        for(i=1; i<=mx; i++) d[i] = 9999999;
        for(i=1; i<=mx; i++)
        {
            par[i] = -1;
            transport[i] = "";
            factor[i] = -1;
        }

        d[src] = 0;
        pq.add(new Node(src, 0));

        while(true)
        {
            Node top = pq.poll();
            if(top == null)
            {
                break;
            }

            int u = top.u;
            if(u == dest) return d[u];

            for(i=0; i<graph[u].size(); i++)
            {
                int v = graph[u].get(i);
                if(d[u] + time[u].get(i) < d[v])
                {
                    d[v] = d[u] + time[u].get(i);
                    pq.add(new Node(v, d[v]));
                    par[v] = u;
                    transport[v] = tr[u].get(i);
                    factor[v] = time[u].get(i);
                }
            }


        }

        return -1;
    }

    public void getPath(int dst)
    {
        if (par[dst] == -1){
//	         System.out.print(toString.get(d));
            return;
        }

        getPath(par[dst]);

        stoppage.add(dst);
    }

    public String printPathCost()
    {
        int steps=0;
        String ret = "";
        ret += ++steps + ". Start From " + toString.get(src) + "\n\n";
        //System.out.println(++steps + ". Start From " + toString.get(src));
        for(i=0; i<stoppage.size(); i++)
        {
            ret += ++steps + ". Take " + transport[stoppage.get(i)] + " Poribohon to " + toString.get(stoppage.get(i)) + " Fare: " + factor[stoppage.get(i)] + " TK\n\n";
        }
        ret += ++steps + ". You have reached " + toString.get(dest) + "\n\n";

        return  ret;
    }

    public String printPathDistance()
    {
        int steps=0;
        String ret = "";
        ret += ++steps + ". Start From " + toString.get(src) + "\n\n";
        //System.out.println(++steps + ". Start From " + toString.get(src));
        for(i=0; i<stoppage.size(); i++)
        {
            ret += ++steps + ". Take " + transport[stoppage.get(i)] + " Poribohon to " + toString.get(stoppage.get(i)) + ". Distance: " + factor[stoppage.get(i)] + " KM\n\n";
        }
        ret += ++steps + ". You have reached " + toString.get(dest) + "\n\n";

        return  ret;
    }


    public String printPathTime()
    {
        int steps=0;
        String ret = "";
        ret += ++steps + ". Start From " + toString.get(src) + "\n\n";
        //System.out.println(++steps + ". Start From " + toString.get(src));
        for(i=0; i<stoppage.size(); i++)
        {
            ret += ++steps + ". Take " + transport[stoppage.get(i)] + " Poribohon to " + toString.get(stoppage.get(i)) + " Time: " + factor[stoppage.get(i)] + " Minutes\n\n";
        }
        ret += ++steps + ". You have reached " + toString.get(dest) + "\n\n";

        return  ret;
    }


}

public class Result extends AppCompatActivity {

    String src;
    String dest;
    String method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        src = intent.getStringExtra("src");
        dest = intent.getStringExtra("dest");
        method = intent.getStringExtra("method");


        Vector <String> input = readFileFromAssets("in.txt");
        TextView tv = (TextView)findViewById(R.id.resultView);
        String path;

        Dijkstra a = new Dijkstra();
        a.takeInput(input);
        a.setSourceDestination(src, dest);
        if(method.equals("Cost"))
        {
            int ans = a.getMinimumCost();
            if(ans != -1)
            {
                a.getPath(a.dest);
                path = a.printPathCost();
                path += "The total cost is: " + ans + " TK\n";
            }
            else path = "There is no path from " + src + " " + dest + "\n";
            tv.setText(path);
        }

        else if(method.equals("Distance"))
        {
            int ans = a.getMinimumDistance();
            if(ans != -1)
            {
                a.getPath(a.dest);
                path = a.printPathDistance();
                path += "The total distance is: " + ans + " KM\n";
            }
            else path = "There is no path from " + src + " " + dest + "\n";
            tv.setText(path);
        }

        else if(method.equals("Time"))
        {
            int ans = a.getMinimumTime();
            if(ans != -1)
            {
                a.getPath(a.dest);
                path = a.printPathTime();
                path += "The total time needed is: " + ans + " Minutes\n";
            }
            else path = "There is no path from " + src + " " + dest + "\n";
            tv.setText(path);
        }


    }


    public Vector<String> readFileFromAssets(String filename)
    {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        Vector<String> ret = new Vector<>();

        System.out.println("GetFile");

        try {
            reader = new BufferedReader(new InputStreamReader(
                    getAssets().open(filename)));

            String line;
            while ((line = reader.readLine()) != null)
            {
                line = line + "\n";
                ret.add(line);
            }

            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }





}
