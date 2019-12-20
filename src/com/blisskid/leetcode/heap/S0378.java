package com.blisskid.leetcode.heap;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 *
 * 示例:
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 * 说明:
 * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 *
 *
 * 1 求kth最小的
 * 2 对于k-1，找k-1对应的right和down
 * 3 初始值F(1) = maxtrix[0][0]，放入最小堆，同时将right和down放入最小堆，找出最小的，作为F(2)。
 * 4 一个最小堆，一个set或者数组去重
 */
public class S0378 {

    public int kthSmallest(int[][] matrix, int k) {
        int rowNum = matrix.length;
        if (rowNum == 0) return 0;
        if (k == 1) return matrix[0][0];
        int colNum = matrix[0].length;
        //the result array
        Node[] F = new Node[k];
        //get the min
        MinHeap minHeap = new MinHeap(rowNum * colNum);
        //to define the same node
        boolean[] array = new boolean[rowNum * colNum];
        //the initialization
        F[0] = new Node(0, 0, matrix[0][0]);

        for (int i = 0; i < k - 1; i++) {
            Node rightNode = getRight(matrix, F[i]);
            if (rightNode != null && !array[rightNode.x * colNum + rightNode.y]) {
                array[rightNode.x * colNum + rightNode.y] = true;
                minHeap.insert(rightNode);
            }
            Node downNode = getDown(matrix, F[i]);
            if (downNode != null && !array[downNode.x * colNum + downNode.y]) {
                array[downNode.x * colNum + downNode.y] = true;
                minHeap.insert(downNode);
            }
            F[i + 1]  = minHeap.remove();
        }

        return F[k - 1].data;
    }

    private Node getRight(int[][] matrix, Node node) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        if (node.y < colNum - 1) {
            return new Node(node.x, node.y + 1, matrix[node.x][node.y + 1]);
        } else {
            return null;
        }
    }

    private Node getDown(int[][] matrix, Node node) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        if (node.x < rowNum - 1) {
            return new Node(node.x + 1, node.y, matrix[node.x + 1][node.y]);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        S0378 s = new S0378();
//        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
//        int k = 20;
        int[][] matrix = {{1,5,9},{10,11,13},{12,13,15}};
        int k = 8;
        System.out.println(s.kthSmallest(matrix, k));
    }


    public int kthSmallest1(int[][] matrix, int k) {
        int rowNum = matrix.length;
        if (rowNum == 0) return 0;
        if (k == 1) return matrix[0][0];
        int colNum = matrix[0].length;
        Node[] arr = new Node[k];
        arr[0] = new Node(0, 0, matrix[0][0]);
        Node lastMin = null;
        Node lastNode = arr[0];
        Node lastRight = null;
        Node lastDown = null;
        if (lastNode.y < colNum - 1) {
            lastRight = new Node(lastNode.x, lastNode.y + 1, matrix[lastNode.x][lastNode.y + 1]);
        }
        if (lastNode.x < rowNum - 1) {
            lastDown = new Node(lastNode.x + 1, lastNode.y, matrix[lastNode.x + 1][lastNode.y]);
        }
        if (lastDown != null && lastRight != null) {
            if (lastRight.data < lastDown.data) {
                arr[1] = lastRight;
                lastMin = lastDown;
            } else {
                arr[1] = lastDown;
                lastMin = lastRight;
            }
        } else if (lastDown == null && lastRight != null) {
            arr[1] = lastRight;
        } else if (lastDown != null && lastRight == null) {
            arr[1] = lastDown;
        }

        for (int i = 2; i < k; i++) {
            Node lastNode1 = arr[i - 1];
            Node lastRight1 = null;
            Node lastDown1 = null;
            if (lastNode1.y < colNum - 1) {
                lastRight1 = new Node(lastNode1.x, lastNode1.y + 1, matrix[lastNode1.x][lastNode1.y + 1]);
            }
            if (lastNode1.x < rowNum - 1) {
                lastDown1 = new Node(lastNode1.x + 1, lastNode1.y, matrix[lastNode1.x + 1][lastNode1.y]);
            }
            if (lastDown1 != null && lastRight1 != null) {
                if (lastRight1.data < lastDown1.data) {
                    if (lastMin.data <= lastRight1.data) {
                        arr[i] = lastMin;
                        lastMin = lastRight1;
                    } else {
                        arr[i] = lastRight1;
                        if (lastMin.data > lastDown1.data) {
                            lastMin = lastDown1;
                        }
                    }
                    //lastdown is smaller than lastright
                } else {
                    if (lastMin.data <= lastDown1.data) {
                        arr[i] = lastMin;
                        lastMin = lastDown1;
                    } else {
                        arr[i] = lastDown1;
                        if (lastMin.data > lastRight1.data) {
                            lastMin = lastRight1;
                        }
                    }
                }
            } else if (lastDown1 == null && lastRight1 != null) {
                if (lastMin.data <= lastRight1.data) {
                    arr[i] = lastMin;
                    lastMin = lastRight1;
                } else {
                    arr[i] = lastRight1;
                }
            } else if (lastDown1 != null && lastRight1 == null) {
                if (lastMin.data <= lastDown1.data) {
                    arr[i] = lastMin;
                    lastMin = lastDown1;
                } else {
                    arr[i] = lastDown1;
                }
            }
        }

        return arr[k - 1].data;
    }
}