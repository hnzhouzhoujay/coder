package com.inc.ycdalgorithm.RBT;

/**
 * @Author Administrator
 * @Date 2019-10-14 09:17
 * @Description TODO
 **/
public class RBT<K extends Comparable<K>,V> {
    int size;

    Node root;

    public RBT() {
        size=0;
    }

    private final static boolean RED = true;

    private final static boolean BLACK = false;

    class Node{
        Node left;
        Node right;
        K key;
        V val;
        boolean color;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.color = RED;
        }
    }



    /**
     * 插入节点.
     * @param node 插入的节点
     * @return 根节点
     */
    public Node add(Node node , K key , V val){
        if(node==null){
            size++;
            return new Node(key, val);
        }
        if(key.compareTo(node.key)<0){
            node.left=add(node.left,key,val);
        }else if(key.compareTo(node.key)>0){
            node.right=add(node.right,key,val);
        }else {
            node.val=val;
        }
        //当出现红节点位置不对的时候，左旋调整位置
        if(isRed(node.right)&&!isRed(node.left)){
            leftRotate(node);
        }
        //当出现连续2红节点，右旋调整位置
        if(isRed(node.left)&&isRed(node.left.left)){
            rightRotate(node);
        }
        //当出现左右2个都是红节点时,等效于2-3树分裂后向上融合
        if(isRed(node.left)&&isRed(node.right)){
            flipColor(node);
        }
        return node;
    }

    private boolean isRed(Node node){
        if(node==null){
            return BLACK;
        }
        return node.color;
    }

    /**
     * 左旋 当出现红节点位置不对的时候，左旋调整位置，(在定义红节点为左节点的前提下)
     * @param y
     * @return
     */
    private Node leftRotate(Node y){
        Node x = y.right;
        Node t2 = x.left;
        x.left = y;
        y.right = t2;
        //x和y的高度变了需要重新计算高度
        x.color=y.color;//保持原颜色
        y.color=RED;//调整后左节点为红
        return x;
    }

    //翻转颜色,当出现左右都是红节点时需要翻转
    private void flipColor(Node node){
        node.color=RED;
        node.left.color=BLACK;
        node.right.color=BLACK;
    }


    /**
     * 右旋 当出现连续2红节点，右旋调整位置，(在定义红节点为左节点的前提下)
     * @param y
     * @return
     */
    private Node rightRotate(Node y){
        Node x = y.left;
        Node t2 = x.right;
        x.right = y;
        y.left = t2;
        //x和y的高度变了需要重新计算高度
        x.color=y.color;//保持原颜色
        y.color=RED;//调整后节点为红
        return x;
    }

}
