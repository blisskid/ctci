package com.blisskid.fb;

import com.blisskid.leetcode.other.TreeNode;
import java.util.*;

public class S0297H {
    public static void main(String[] args) {
        S0297H s=new S0297H();
        System.out.println(s.serialize(s.deserialize("1,2,#,#,3,4,#,#,5,#,#,")));
    }
    // Encodes a tree to a single string.
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        if(root==null){
            sb.append("#").append(",");
        }else{
            sb.append(serialize(root.left));
            sb.append(serialize(root.right));
            sb.append(new Integer(root.val)).append(",");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        System.out.println(data);
        if(data.length()==0)
            return null;
        String[] arr=data.split(",");
        LinkedList<String> l=new LinkedList();
        for(int i=0;i<arr.length;i++){
            if(!arr[i].isEmpty()){
                //System.out.println(arr[i]);
                l.add(arr[i]);
            }
        }
        return f(l);
    }

    private TreeNode f(LinkedList<String> l){
        if(l==null||l.size()==0)
            return null;
        String s=l.removeLast();
        if(s.equals("#"))
            return null;
        Integer last=Integer.parseInt(s);
        TreeNode root=new TreeNode(last);
        root.right=f(l);
        root.left=f(l);
        return root;
    }
}
