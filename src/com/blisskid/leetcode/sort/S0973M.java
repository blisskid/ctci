package com.blisskid.leetcode.sort;
import java.util.*;

public class S0973M {

    public static void main(String[] args) {
        int[][] points = new int[][]{{3, 3}, {5, -1}, {-2, 4}};
        new S0973M().kClosest(points, 2);
    }

    private class Point {
        int x;
        int y;
        int distance;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = x*x + y*y;
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        List<Point> pList = new ArrayList();
        for (int i = 0; i < points.length; i++) {
            Point p = new Point(points[i][0], points[i][1]);
            pList.add(p);
        }

        List<Point> result = find(pList, K);
        int[][] r = new int[result.size()][2];

        for (int i = 0; i < result.size(); i++) {
            r[i][0] = result.get(i).x;
            r[i][1] = result.get(i).y;
        }

        return r;
    }

    private List<Point> find(List<Point> points, int k) {
        List<Point> left = new ArrayList();
        List<Point> right = new ArrayList();
        int size = points.size();
        if (size == 1) return points;
        int seed = points.get(0).distance;
        //right.add(points.get(0));
        for (int i = 1; i < size; i++) {
            if (points.get(i).distance < seed) {
                left.add(points.get(i));
            } else {
                right.add(points.get(i));
            }
        }
        left.add(points.get(0));
        if (left.size() == k) {
            return left;
        } else if (left.size() > k) {
            return find(left, k);
        } else {
            left.addAll(find(right, k - left.size()));
            return left;
        }
    }
}
