package com.lunzi.camry.leecode;

import lombok.Data;

import java.util.Objects;

/**
 * Created by lunzi on 2019/2/13 10:25 PM
 */
@Data
public class BinaryNode {

    private Integer data;
    private BinaryNode leftNode;
    private BinaryNode rightNode;

    //构造方法
    public BinaryNode(Integer data) {
        this(data, null, null);
    }

    public BinaryNode(Integer data, BinaryNode leftNode, BinaryNode rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public BinaryNode() {

    }

    //查找操作 返回对应的BinaryNode
    public BinaryNode getNodeByData(Integer searchData) {
        if (Objects.equals(searchData, data)) {
            return this;
        }
        if (leftNode != null && searchData < data) {
            return leftNode.getNodeByData(searchData);
        }
        if (rightNode != null && searchData > data) {
            return rightNode.getNodeByData(searchData);
        }
        return null;
    }

    //插入操作
    public BinaryNode insertNode(BinaryNode node, Integer newData) {
        if (node == null) {
            return new BinaryNode(newData);
        }
        if (newData < node.getData()) {
            node.leftNode = insertNode(node.leftNode, newData);
        }
        if (newData > node.getData()) {
            node.rightNode = insertNode(node.rightNode, newData);
        }
        return node;
    }

    //max 获取最右边的节点 递归获取
    public BinaryNode getMaxNode(BinaryNode node) {
        if (node.rightNode != null) {
            return getMaxNode(node.rightNode);
        }
        return node;
    }

    //min 获取最左边的节点 递归获取
    public BinaryNode getMinNode(BinaryNode node) {
        if (node.leftNode != null) {
            return getMinNode(node.leftNode);
        }
        return node;
    }

    //max 获取最右边的节点 迭代获取
    public BinaryNode getMaxNodeV2(BinaryNode node) {
        while (node.rightNode != null) {
            node = node.rightNode;
        }
        return rightNode;
    }

    //min 获取最左边的节点 迭代获取
    public BinaryNode getMinNodeV2(BinaryNode node) {
        while (node.leftNode != null) {
            node = node.leftNode;
        }
        return leftNode;
    }

    //查找指定的值，没有就返回null
    public BinaryNode searchNode(BinaryNode node, Integer data) {
        if (node.getData().equals(data)) {
            return node;
        }
        if (node.leftNode != null && data <= node.leftNode.getData()) {
            return searchNode(node.leftNode, data);
        }
        if (node.rightNode != null && data >= node.rightNode.getData()) {
            return searchNode(node.rightNode, data);
        }
        return null;
    }

    //删除指定的节点
    //叶子节点直接删除
    //单枝节点删除，拼接
    //有双枝节点的，有两种策略
    public BinaryNode deleteNode(BinaryNode node, Integer data) {
        //先查到指定的节点
        if (node.getData().equals(data)) {
            if (node.leftNode == null && node.rightNode == null) {
                node = null;
            }
            else if(node.leftNode==null&&node.rightNode!=null){
                node=node.rightNode;
            }else if (node.rightNode==null&&node.leftNode!=null){
                node=node.leftNode;
            }else{
                //找出左子树的最大值
                BinaryNode binaryNode=getMaxNode(node.leftNode);
                node.data=binaryNode.data;
                node.leftNode=deleteNode(node.leftNode,node.data);
            }
        } else if (data <node.getData()) {
            node.leftNode = deleteNode(node.leftNode, data);
        } else if (data > node.getData()) {
            node.rightNode = deleteNode(node.rightNode, data);
        }
        return node;
    }


    public static void main(String[] args) {
        BinaryNode binaryNode = new BinaryNode(10);
        binaryNode.insertNode(binaryNode, 8);
        binaryNode.insertNode(binaryNode, 12);
        binaryNode.insertNode(binaryNode, 4);
        binaryNode.insertNode(binaryNode, 9);
        binaryNode.insertNode(binaryNode, 3);
        binaryNode.insertNode(binaryNode, 7);
        //System.out.println(binaryNode);
        System.out.println(binaryNode.deleteNode(binaryNode, 8));
        //System.out.println(binaryNode.getMaxNodeV2(binaryNode));
        // System.out.println(binaryNode.getMinNode(binaryNode));
        // System.out.println(binaryNode.searchNode(binaryNode,1));

    }
}
