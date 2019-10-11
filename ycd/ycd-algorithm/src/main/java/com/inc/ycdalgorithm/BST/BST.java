package com.inc.ycdalgorithm.BST;

/**
 * @Author Administrator
 * @Date 2019-10-09 09:45
 * @Description 二叉搜索树
 **/
public class BST<E extends Comparable> {

    /* 根节点 */
    Node<E> root;

    /* 数据大小 */
    Integer size;

    public BST() {
        size=0;
        root=null;
    }

    private class Node<E>{

        Node<E> left;

        Node<E> right;

        E value;

        public Node(E value) {
            this.value = value;
        }
    }

    /**
     * 插入节点.
     * @param node 插入的节点
     * @param e
     * @return 根节点
     */
    public Node add(Node node , E e){
        if(node==null){
            size++;
            return new Node<>(e);
        }
        if(e.compareTo(node.value)>0){//插入右子树
            node.right = add(node.right,e);
        }else if(e.compareTo(node.value)<0){//插入左子树
            node.left = add(node.left,e);
        }
        //注意这里返回的是递归进来的根节点,因为node.right = add(node.right,e);
        // node.right不为空，返回的是原来的node.right,为空返回新增的节点.
        return node;
    }


    /**
     * 删除节点.
     * @param node 删除的节点
     * @param e
     * @return 根节点
     */
    public Node remove(Node node , E e){
        if(node == null){
            return null;
        }
        if(e.compareTo(node.value)>0){
            node.right = remove(node.right,e);
            return node;
        }else if(e.compareTo(node.value)<0){
            node.left = remove(node.left,e);
            return node;
        }else{
           if(node.left==null){//只有右孩子
               Node rightNode = node.right;
               size--;
               node.right=null;
               return rightNode;
           }else if(node.right==null){//只有左孩子
                Node leftNode = node.left;
                size--;
                node.left=null;
                return leftNode;
           }else {
               //既有左孩子又有右孩子,要找个节点替代，根据规则 左子树 < 父节点 < 右子树，要保证这个可以选
               //左子树最大的节点或者右子树最小的
               Node middleNode = findMin(node.right);
               //这里找右子树最小的,左边不变
               middleNode.left=node.left;
               //删除右子树最小的
               middleNode.right=removeMin(node.right);
//               size--; 这里在removmin时已经减1了
               node.right=node.left=null;
           }
        }
        return node;
    }

    /**
     * 删除最小节点.
     * @param node
     * @return 根节点
     */
    private Node removeMin(Node node){
        if(node.left==null){//找到最小节点
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    /**
     * 删除最大节点.
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        if(node.right==null){//找到最小节点
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }




    /**
     * 查找某个节点.
     * @param e
     * @return
     */
    public Node find(Node node ,E e){
        if(node==null){
            return null;
        }
        if(e.compareTo(node.value)<0){
            return find(node.left,e);
        }else if(e.compareTo(node.right)<0){
            return find(node.right,e);
        }
        return node;
    }


    /**
     * 查找最小的节点
     * @return
     */
    private Node findMin(Node node){
        if(node.left==null){
            return node;
        }
        return findMin(node);
    }

    /**
     * 查找最大的节点
     * @return
     */
    private Node findMax(Node node){
        if(node.right==null){
            return node;
        }
        return findMax(node);
    }





    /**
     * 先序遍历
     */
    public void preVisit(Node node){
        if(node==null){
            return;
        }
        preVisit(node.left);
        System.out.print(node.value+" ");
        preVisit(node.right);

    }


    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.root = bst.add(bst.root,5);
        bst.add(bst.root,4);
        bst.add(bst.root,10);
        bst.add(bst.root,2);
        bst.add(bst.root,6);
        bst.add(bst.root,8);
        bst.preVisit(bst.root);
        System.out.println();
        bst.remove(bst.root,10);
        bst.preVisit(bst.root);
    }






}
