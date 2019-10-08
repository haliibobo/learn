# learn --- 元注解
1.@Inherited 允许子类继承父类的注解,子类中可以获取并使用父类注解
2.@Retention 表明注解保留范围,CLASS default
  2.1 SOURCE 不被编译器保留
  2.2 CLASS  不被JVM保留
  2.3 RUNTIME 被编译器和JVM保留
3.@Target 表明注解应用范围
  3.1 TYPE
  3.2 FIELD
  3.3 METHOD
  3.4 PARAMETER
  3.5 CONSTRUCTOR
 4.@Documented 标记该注解被javadoc工具 记录
