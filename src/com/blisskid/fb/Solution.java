package com.blisskid.fb;
import java.util.*;

class Solution {

    public static void main(String[] args) {

    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res=accounts;
        while(check(res)){
            res=f(res);
        }

        List<List<String>> realRes=new ArrayList();
        for(List<String> l:res){
            String name=l.get(0);
            ArrayList<String> last=new ArrayList();
            for(int i=1;i<l.size();i++){
                last.add(l.get(i));
            }
            Collections.sort(last);
            ArrayList<String> temp=new ArrayList();
            temp.add(name);
            for(String str:last){
                if(!temp.contains(str)){
                    temp.add(str);
                }
            }
            realRes.add(temp);
        }

        return realRes;
    }

    private List<List<String>> f(List<List<String>> accounts) {
        HashMap<String,ArrayList<ArrayList<String>>> map=new HashMap();
        for(int i=0;i<accounts.size();i++){
            ArrayList<String> account=new ArrayList();
            account.addAll(accounts.get(i));
            //Collections.sort(account);
            String name=account.get(0);
            account.remove(0);
            if(map.containsKey(name)){
                //check if the same person
                ArrayList<ArrayList<String>> emailList=map.get(name);
                boolean needBreak=false;
                for(ArrayList<String> emails:emailList){
                    for(String email:emails){
                        if(account.contains(email)){
                            account.remove(email);
                            emails.addAll(account);
                            //Collections.sort(emails);
                            needBreak=true;
                            break;
                        }
                    }
                    if(needBreak){
                        break;
                    }
                }
                if(!needBreak){
                    emailList.add(account);
                    //needBreak=false;
                }
            }else{
                ArrayList<ArrayList<String>> value=new ArrayList();
                value.add(account);
                map.put(name,value);
            }
        }

        List<List<String>> res=new ArrayList();
        for(Map.Entry e:map.entrySet()){
            ArrayList<ArrayList<String>> value=(ArrayList<ArrayList<String>>)e.getValue();
            String name=(String)e.getKey();
            for(ArrayList<String> l:value){

                ArrayList<String> temp=new ArrayList();
                temp.add(name);
                for(String str:l){
                    if(!temp.contains(str)){
                        temp.add(str);
                    }
                }
                res.add(temp);
            }
        }
        System.out.println(res);
        return res;
    }

    private boolean check(List<List<String>> accounts) {
        for(int i=0;i<accounts.size()-1;i++){
            for(int j=i+1;j<accounts.size();j++){
                for(int k=1;k<accounts.get(j).size();k++){
                    if(accounts.get(i).contains(accounts.get(j).get(k))){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
