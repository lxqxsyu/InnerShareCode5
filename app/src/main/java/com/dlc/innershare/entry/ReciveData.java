package com.dlc.innershare.entry;

import java.util.List;

public class ReciveData {

    public int code;
    public String message;

    public List<PhotoData> result;

    public class PhotoData{

        public int id;
        public String time;
        public String img;
    }

}
