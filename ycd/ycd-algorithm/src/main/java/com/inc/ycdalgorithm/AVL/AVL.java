package com.inc.ycdalgorithm.AVL;

import com.inc.ycdalgorithm.BST.BST;

/**
 * @Author Administrator
 * @Date 2019-10-10 14:35
 * @Description AVL 平衡二叉线索树
 *
 **/
public class AVL<K extends Comparable<K>,V>{

    int size;

    Node root;

    public AVL() {
        size=0;
    }

    class Node{
       Node left;
       Node right;
       K key;
       V val;
       int height;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.height = 1;
        }
    }

    /**
     * 获取树的高度.
     * @param node
     * @return
     */
    private int getHeight(Node node){
        if(node==null){
            return 0;
        }
        return node.height;
    }

    /**
     * 得到树的平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 左旋
      // RR: T1<Y<T2< X <T3<Z<T4                      //
      //    y                              x          //
      //  /  \                           /   \        //
      // T1   x      向左旋转 (y)        y     z       //
      //     / \   - - - - - - - ->    / \   / \      //
      //   T2   z                     T1 T2 T3 T4     //
      //       / \                                    //
      //      T3 T4                                   //
      //////////////////////////////////////////////////
     * @param y 旋转的根节点
     * @return 新根节点
     */
    private Node leftRotate(Node y){
        Node x = y.right;
        Node t2 = x.left;
        x.left = y;
        y.right = t2;
        //x和y的高度变了需要重新计算高度
        y.height = Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right))+1;
        return x;

    }


    /**
     * 右旋
     /////////////////////////////////////////////
     // LL T1<Z<T2< X <T3<Y<T4                        //
     //        y                              x       //
     //       / \                           /   \     //
      //      x   T4     向右旋转 (y)        z     y    //
      //     / \       - - - - - - - ->    / \   / \   //
      //    z   T3                        T1 T2 T3 T4  //
      //   / \                                         //
      // T1   T2                                       //
      ///////////////////////////////////////////////////
     * @param y 旋转的根节点
     * @return 新根节点
     */
    private Node rightRotate(Node y){
        Node x = y.left;
        Node t3 = x.right;
        x.right = y;
        y.left = t3;
        //x和y的高度变了需要重新计算高度
        y.height = Math.max(getHeight(y.left),getHeight(y.right))+1;
        y.height = Math.max(getHeight(y.left),getHeight(y.right))+1;
        return x;

    }


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
        //重新计算高度
        node.height = Math.max(getHeight(node.left),getHeight(node.right))+1;
        //检查平衡因子
        int balanceFactor = getBalanceFactor(node);
        //旋转分为LL ,LR, RL ,RR 这四种情况 , LL 左子树的左子树不满足 ，LR 左子树的右子树不满足 ,RL 右子树的左子树不满足,RR 右子树的右子树不满足
        //其中LL根节点右旋即可,RR根节点左旋即可,LR 要先左旋转成LL 再右旋 ，RL 要先右旋转成RR再左旋
        if(balanceFactor>1&&getBalanceFactor(node.left)>=0){//LL的情况
            return rightRotate(node);
        }
        if(balanceFactor>1&&getBalanceFactor(node.left)<0){//LR的情况
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if(balanceFactor<-1&&getBalanceFactor(node.right)<=0){//RR的情况
            return leftRotate(node);
        }
        if(balanceFactor<-1&&getBalanceFactor(node.right)>0){//RL的情况
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }


    public Node remove(Node node , K key){
        if(node==null){
            return null;
        }
        if(key.compareTo(node.key)<0){
            node.left = remove(node.left,key);
            return node;
        }else if(key.compareTo(node.key)>0){
            node.right = remove(node.right,key);
            return node;
        }else {
            if(node.left==null){
                size--;
                Node rightNode = node.right;
                node.right=null;
                return rightNode;
            }else if(node.right==null){
                size--;
                Node leftNode = node.left;
                node.left=null;
                return leftNode;
            }else{
                Node middleNode = findMin(node.right);
                middleNode.right   = removeMin(node.right);
                middleNode.left = node.left;
                node.right=node.left=null;
            }
        }
        //重新计算高度
        node.height = Math.max(getHeight(node.left),getHeight(node.right))+1;
        //检查平衡因子
        int balanceFactor = getBalanceFactor(node);
        //旋转分为LL ,LR, RL ,RR 这四种情况 , LL 左子树的左子树不满足 ，LR 左子树的右子树不满足 ,RL 右子树的左子树不满足,RR 右子树的右子树不满足
        //其中LL根节点右旋即可,RR根节点左旋即可,LR 要先左旋转成LL 再右旋 ，RL 要先右旋转成RR再左旋
        if(balanceFactor>1&&getBalanceFactor(node.left)>=0){//LL的情况
            return rightRotate(node);
        }
        if(balanceFactor>1&&getBalanceFactor(node.left)<0){//LR的情况
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if(balanceFactor<-1&&getBalanceFactor(node.right)<=0){//RR的情况
            return leftRotate(node);
        }
        if(balanceFactor<-1&&getBalanceFactor(node.right)>0){//RL的情况
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }



    private Node findMin(Node node) {
        if(node==null){
            return null;
        }
        if(node.left==null){
            return node;
        }
        return findMin(node.left);

    }

    private Node removeMin(Node node) {
        if(node==null){
            return null;
        }
        if(node.left==null){
            size--;
            Node rightNode = node.right;
            node.right=null;
            return rightNode;
        }
        node.left=removeMin(node.left);
        return node;
    }

    /**
     * 先序遍历
     */
    public void preVisit(Node node){
        if(node==null){
            return;
        }
        preVisit(node.left);
        System.out.print(node.key+":"+node.val+" ");
        preVisit(node.right);

    }

    public static void main(String[] args) {
        AVL<Integer,String> avl = new AVL<>();
        avl.root = avl.add(avl.root,1,"first");
        System.out.println();
        avl.preVisit(avl.root);
        avl.root = avl.add(avl.root,2,"second");
        System.out.println();
        avl.preVisit(avl.root);
        avl.root = avl.add(avl.root,3,"third");
        System.out.println();
        avl.preVisit(avl.root);
        avl.root = avl.add(avl.root,4,"four");
        System.out.println();
        avl.preVisit(avl.root);
        avl.root = avl.add(avl.root,5,"five");
        System.out.println();
        avl.preVisit(avl.root);
        avl.root = avl.add(avl.root,6,"six");
        System.out.println();
        avl.preVisit(avl.root);
        avl.root = avl.add(avl.root,7,"seven");
        System.out.println();
        avl.preVisit(avl.root);
        avl.root = avl.add(avl.root,8,"eight");
        System.out.println();
        avl.preVisit(avl.root);

    }


}
