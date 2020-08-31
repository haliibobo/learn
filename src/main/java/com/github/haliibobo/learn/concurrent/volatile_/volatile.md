volatile

The Java volatile keyword is used to mark a Java variable as "being stored in main memory".
 More precisely that means, 
 that every read of a volatile variable will be read from the computer's main memory, and not from the CPU cache, 
 and that every write to a volatile variable will be written to main memory, and not just to the CPU cache.

这个实际上相当于是一个内存屏障，该内存屏障会为指令的执行提供如下保障：

确保指令重排序时不会将其后面的代码排到内存屏障之前。

同样也会确保重排序是不会将其前面的代码排到内存屏障之后。

确保在执行到内存屏障修饰的指令时前面的代码全部执行完成。

被volatile关键字修饰的对象作为类变量或实例变量时，其对象中携带的类变量和实例变量也相当于被volatile关键字修饰了

强制将线程的工作内存中值的修改刷新至主内存中。