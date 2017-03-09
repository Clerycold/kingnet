package com.kingnet.Data;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.kingnet.R;

/**
 * Created by clery on 2016/11/28.
 */

public class PostListData {

    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    private String l;
    private String i;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {

        return d;
    }

    public void setD(Context context,String d) {
        switch (d){
            case "0":
                d="0";
                break;
            case "1":
                d=  (String) context.getResources().getText(R.string.post_d1);
                break;
            case "2":
                d= (String) context.getResources().getText(R.string.post_d2);
                break;
        }
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        switch (n){
            case "0":
                n="";
                break;
            case "1":
                n="n1";
                break;
            case "2":
                n="n2";
                break;
            case "3":
                n="n3";
                break;
            case "4":
                n="n4";
                break;
            case "5":
                n="n5";
                break;
            case "6":
                n="n6";
                break;
            case "7":
                n="n7";
                break;
            case "8":
                n="n8";
                break;
            case "9":
                n="n9";
                break;
            case "10":
                n="n10";
                break;
            case "11":
                n="n11";
                break;
            case "12":
                n="n12";
                break;
            case "13":
                n="n13";
                break;
            case "14":
                n="n14";
                break;
            case "15":
                n="n15";
                break;

        }
        this.n = n;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        switch (o){
            case "0":
                o="";
                break;
            case "1":
                o="o1";
                break;
            case "2":
                o="o2";
                break;
            case "3":
                o="o3";
                break;
            case "4":
                o="o4";
                break;
            case "5":
                o="o5";
                break;
            case "6":
                o="o6";
                break;
            case "7":
                o="o7";
                break;
            case "8":
                o="o8";
                break;
            case "9":
                o="o9";
                break;
            case "10":
                o="o10";
                break;
            case "11":
                o="o11";
                break;
        }
        this.o = o;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }


}
