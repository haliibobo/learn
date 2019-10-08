# learn
1.直接类的不需要显示，接口类的必须显示bind，有两种方式
  1.1 通过注解@ImplementedBy
  1.2 通过bind函数
2. 注入方式有三种
   普通类型注入 需要@Named注解，以下三种都适用
  2.1 field
  2.2 method
  2.3 constructor
3.bind 
  3.1 link blind 接口绑定类、父类绑定子类，可进行链式绑定
  3.2 

        