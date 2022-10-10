package com.example.Quize_App

object data
{
    fun getdata():ArrayList<datalist>
    {


        val list = ArrayList<datalist>()

        list.add(datalist(1,"which is best IT company in india","TCS","WIPRO","CISCO","INFOSIS",3))
//        tmp = datalist(1,"which is best IT company in world","amazon","google","apple","microsoft",2)
//
//        val tmp1 =  datalist(2,"which is best IT company in india","TCS","WIPRO","CISCO","INFOSIS",1)
        list.add(datalist(2,"which is best IT company in world","amazon","google","apple","microsoft",3))



        return  list
    }
}