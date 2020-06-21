package com.blisskid.leetcode.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


class Coordinate {
    public int row;
    public int col;

    Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class S0994M {

    public static void main(String[] args) {
        //int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
//        int[][] grid = new int[][]{{1, 2}};
        int[][] grid = new int[][]{{1}, {2}, {1}, {2}};
        System.out.println(new S0994M().orangesRotting(grid));
    }

    private Stack<Coordinate> stack;
    //the result
    private int count;
    //array label if the orange is visited
    private int[][] visitArray;

    public int orangesRotting(int[][] grid) {
        stack = new Stack();
        count = 0;
        int lastCount = count;
        int number = 0;
        visitArray = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    stack.push(new Coordinate(i, j));
                    number++;
                    count++;
                } else if (grid[i][j] == 1) {
                    number++;
                }
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            List<Coordinate> list = new ArrayList<>();
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
            lastCount = count;
            for (Coordinate co : list) {
                affectAdj(co.row, co.col, grid);
            }
            if (count > lastCount)
                result++;
            if (count == number)
                break;

        }
        if (count == number)
            return result;
        else
            return -1;
    }

    //affect adj
    private void affectAdj(int row, int col, int[][] grid) {
        if (row == 0) {
            if (col == 0) {
                if (grid.length > 1 && grid[row + 1][col] == 1) {
                    stack.push(new Coordinate(row + 1, col));
                    grid[row + 1][col] = 2;
                    count++;
                }
                if (grid[0].length > 1 && grid[row][col + 1] == 1) {
                    stack.push(new Coordinate(row, col + 1));
                    grid[row][col + 1] = 2;
                    count++;
                }
            } else if (col > 0 && col < grid[0].length - 1) {
                if (grid.length > 1 && grid[row + 1][col] == 1) {
                    stack.push(new Coordinate(row + 1, col));
                    grid[row + 1][col] = 2;
                    count++;
                }
                if (grid[row][col + 1] == 1) {
                    stack.push(new Coordinate(row, col + 1));
                    grid[row][col + 1] = 2;
                    count++;
                }
                if (grid[row][col - 1] == 1) {
                    stack.push(new Coordinate(row, col - 1));
                    grid[row][col - 1] = 2;
                    count++;
                }
            } else if (col == grid[0].length - 1) {
                if (grid.length > 1 && grid[row + 1][col] == 1) {
                    stack.push(new Coordinate(row + 1, col));
                    grid[row + 1][col] = 2;
                    count++;
                }
                if (grid[0].length > 1 && grid[row][col - 1] == 1) {
                    stack.push(new Coordinate(row, col - 1));
                    grid[row][col - 1] = 2;
                    count++;
                }
            }
        } else if (row > 0 && row < grid.length - 1) {
            if (col == 0) {
                if (grid[row + 1][col] == 1) {
                    stack.push(new Coordinate(row + 1, col));
                    grid[row + 1][col] = 2;
                    count++;
                }
                if (grid[0].length > 1 && grid[row][col + 1] == 1) {
                    stack.push(new Coordinate(row, col + 1));
                    grid[row][col + 1] = 2;
                    count++;
                }
                if (grid[row - 1][col] == 1) {
                    stack.push(new Coordinate(row - 1, col));
                    grid[row - 1][col] = 2;
                    count++;
                }
            } else if (col > 0 && col < grid[0].length - 1) {
                if (grid[row + 1][col] == 1) {
                    stack.push(new Coordinate(row + 1, col));
                    grid[row + 1][col] = 2;
                    count++;
                }
                if (grid[row][col + 1] == 1) {
                    stack.push(new Coordinate(row, col + 1));
                    grid[row][col + 1] = 2;
                    count++;
                }
                if (grid[row][col - 1] == 1) {
                    stack.push(new Coordinate(row, col - 1));
                    grid[row][col - 1] = 2;
                    count++;
                }
                if (grid[row - 1][col] == 1) {
                    stack.push(new Coordinate(row - 1, col));
                    grid[row - 1][col] = 2;
                    count++;
                }
            } else if (col == grid[0].length - 1) {
                if (grid[row + 1][col] == 1) {
                    stack.push(new Coordinate(row + 1, col));
                    grid[row + 1][col] = 2;
                    count++;
                }
                if (grid[0].length > 1 && grid[row][col - 1] == 1) {
                    stack.push(new Coordinate(row, col - 1));
                    grid[row][col - 1] = 2;
                    count++;
                }
                if (grid[row - 1][col] == 1) {
                    stack.push(new Coordinate(row - 1, col));
                    grid[row - 1][col] = 2;
                    count++;
                }
            }
        } else if (row == grid.length - 1) {
            if (col == 0) {
                if (grid[0].length > 1 && grid[row][col + 1] == 1) {
                    stack.push(new Coordinate(row, col + 1));
                    grid[row][col + 1] = 2;
                    count++;
                }
                if (grid.length > 1 && grid[row - 1][col] == 1) {
                    stack.push(new Coordinate(row - 1, col));
                    grid[row - 1][col] = 2;
                    count++;
                }
            } else if (col > 0 && col < grid[0].length - 1) {
                if (grid[row][col + 1] == 1) {
                    stack.push(new Coordinate(row, col + 1));
                    grid[row][col + 1] = 2;
                    count++;
                }
                if (grid[row][col - 1] == 1) {
                    stack.push(new Coordinate(row, col - 1));
                    grid[row][col - 1] = 2;
                    count++;
                }
                if (grid.length > 1 && grid[row - 1][col] == 1) {
                    stack.push(new Coordinate(row - 1, col));
                    grid[row - 1][col] = 2;
                    count++;
                }
            } else if (col == grid[0].length - 1) {
                if (grid[0].length > 1 && grid[row][col - 1] == 1) {
                    stack.push(new Coordinate(row, col - 1));
                    grid[row][col - 1] = 2;
                    count++;
                }
                if (grid.length > 1 && grid[row - 1][col] == 1) {
                    stack.push(new Coordinate(row - 1, col));
                    grid[row - 1][col] = 2;
                    count++;
                }
            }
        }
    }
}
