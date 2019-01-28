package com.mini.foreController;

import com.mini.entity.Category;
import sun.applet.Main;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author hht
 * @create 2019-01-26 17:34
 */
public class NodeList<E> {

    //结构体
    private static class Node<E> { // 节点类
        E data; // 节点上的数据
        Node<E> next; // 指向下一个节点
        Node(E e) {
            this.data = e;
            this.next = null;
        }
    }

    //定义头结点  尾结点
    private Node<E> head;
    private Node<E> last;

    private Node<E> other = null;

    private int length = 0;

    public NodeList() {
          this.head = new Node<>(null);
    }


    //有参构造
    public NodeList(E data) {
        this.head = new Node<>(data);
        this.last = head;
        length ++;
    }


    public void add(E data){

        Node<E> eNode = new Node<>(data);

        last.next = eNode;

        last = eNode;

    }

    //遍历
       public void print(){

          other = head;
          while (other != null){

              System.out.println(other.data);
              other = other.next;

          }
       }

       public static void main(String[] args){

         Category c = new Category();

         c.setName("2324");

           Category c1 = new Category();

           c1.setName("23241111");


           NodeList<Category> categoryNodeList = new NodeList<>(c);

           categoryNodeList.add(c1);

           categoryNodeList.print();


           Hashtable<Object, Object> hashtable = new Hashtable<>();

           //hashtable.put("ew",null);

           HashMap<Object, Object> map = new HashMap<>();

           map.put(null,"232");


       }
}
